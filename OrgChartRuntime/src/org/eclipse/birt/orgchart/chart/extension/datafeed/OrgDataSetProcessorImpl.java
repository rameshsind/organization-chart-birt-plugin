package org.eclipse.birt.orgchart.chart.extension.datafeed;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.birt.chart.datafeed.DataSetAdapter;
import org.eclipse.birt.chart.datafeed.IResultSetDataSet;
import org.eclipse.birt.chart.exception.ChartException;
import org.eclipse.birt.chart.model.data.DataSet;
import org.eclipse.birt.chart.plugin.ChartEngineExtensionPlugin;
import org.eclipse.birt.orgchart.chart.i18n.Messages;
import org.eclipse.birt.orgchart.chart.model.data.impl.OrgDataSetImpl;

import com.ibm.icu.util.StringTokenizer;

public class OrgDataSetProcessorImpl extends DataSetAdapter {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.birt.chart.datafeed.IDataSetProcessor#populate(java.lang.Object,
	 *      org.eclipse.birt.chart.model.data.DataSet)
	 */
	public final DataSet populate( Object oResultSetDef, DataSet ds )
			throws ChartException
	{
		if ( oResultSetDef instanceof IResultSetDataSet )
		{
			final IResultSetDataSet rsds = (IResultSetDataSet) oResultSetDef;
			final long lRowCount = rsds.getSize( );
			
			if ( lRowCount <= 0 )
			{
				throw new ChartException( ChartEngineExtensionPlugin.ID,
						ChartException.ZERO_DATASET,
						"exception.empty.dataset",//$NON-NLS-1$
						Messages.getResourceBundle( getULocale( ) ) );
			}

			int i = 0;

			final OrgEntry[] oea = new OrgEntry[(int) lRowCount];
			while ( rsds.hasNext( ) )
			{
				Object[] oTwoComponents = rsds.next( );

				validateOrgEntryData( oTwoComponents );

				oea[i++] = new OrgEntry( oTwoComponents );
			}
			if ( ds == null )
			{
				ds = OrgDataSetImpl.create( oea );
			}
			else
			{
				ds.setValues( oea );
			}
		}
		else
		{
			throw new ChartException( ChartEngineExtensionPlugin.ID,
					ChartException.DATA_SET,
					"exception.unknown.custom.dataset",//$NON-NLS-1$
					new Object[]{
							ds, oResultSetDef
					},
					Messages.getResourceBundle( getULocale( ) ) );
		}
		return ds;
	}

	private void validateOrgEntryData( Object[] obja ) throws ChartException
	{
		boolean valid = true;

		if ( obja == null )
		{
			valid = false;
		}
		else if ( obja.length != 2 )
		{
			throw new ChartException( ChartEngineExtensionPlugin.ID,
					ChartException.DATA_SET,
					"exception.dataset.orgseries",//$NON-NLS-1$
					Messages.getResourceBundle( getULocale( ) ) );
		}
		// !ignore this check, we can handle the invalid case now.
		// else
		// {
		// for ( int i = 0; i < obja.length; i++ )
		// {
		// if ( !( obja[i] instanceof Number ) )
		// {
		// // valid = false;
		// break;
		// }
		// }
		// }

		if ( !valid )
		{
			throw new ChartException( ChartEngineExtensionPlugin.ID,
					ChartException.VALIDATION,
					"exception.dataset.null.orgentry", //$NON-NLS-1$
					Messages.getResourceBundle( getULocale( ) ) );
		}
	}

	
	/**
	 * This method takes the data in String form and populates the DataSet
	 * (creating one if necessary). For the OrgDataElement, the data should be
	 * provided in the form: 'I <node id> P <parent id> ' i.e. 'I', 'P' 
	 *  are used to designate a value as either the node id, parent node id
	 * component of the data element. DataElements should be separated by commas
	 * (,). Components within the data element are separated by a space and
	 * their sequence is not important.
	 * 
	 * @return DataSet populated by the entries in the String or null if the
	 *         String is null.
	 * @throws ChartException
	 *             if there is any problem parsing the String passed in.
	 * @see org.eclipse.birt.chart.datafeed.IDataSetProcessor#fromString(java.lang.String,
	 *      org.eclipse.birt.chart.model.data.DataSet)
	 */
	public final DataSet fromString( String sDataSetRepresentation, DataSet ds )
			throws ChartException
	{
		// Do NOT create a DataSet if the content string is null
		if ( sDataSetRepresentation == null )
		{
			return ds;
		}
		// Create an EMPTY DataSet if the content string is an empty string
		if ( ds == null )
		{
			ds = OrgDataSetImpl.create( null );
		}
		//Sample data
		sDataSetRepresentation = "I1 P0,I2 P1,I3 P1,I4 P2,I5 P2";
		StringTokenizer strTokDataElement = new StringTokenizer( sDataSetRepresentation,
				"," ); //$NON-NLS-1$
		StringTokenizer strTokComponents = null;
		String strDataElement = null;
		String strComponent = null;
		List<OrgEntry> vData = new ArrayList<OrgEntry>( );
		while ( strTokDataElement.hasMoreTokens( ) )
		{
			strDataElement = strTokDataElement.nextToken( ).trim( );

			// Build a StockDataElement from this token
			strTokComponents = new StringTokenizer( strDataElement );
			
			// Compatible with other sample data
			if ( strTokComponents.countTokens( ) == 1 )
			{
				int iComponent = Integer.parseInt( strDataElement );
				vData.add( new OrgEntry( iComponent, 0 ) );
				continue;
			}
			
			OrgEntry entry = new OrgEntry( Integer.MAX_VALUE, 0 );
			while ( strTokComponents.hasMoreTokens( ) )
			{
				strComponent = strTokComponents.nextToken( )
						.trim( )
						.toUpperCase( );
				int iComponent = Integer.parseInt( strComponent.substring( 1 ) );
				if ( strComponent.startsWith( "I" ) ) //$NON-NLS-1$
				{
					entry.setId( iComponent );
				}
				else if ( strComponent.startsWith( "P" ) ) //$NON-NLS-1$
				{
					entry.setParentId( iComponent );
				}
			}
			vData.add( entry );
		}
		ds.setValues( vData );
		return ds;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.birt.chart.datafeed.DataSetProcessor#getExpectedStringFormat()
	 */
	public String getExpectedStringFormat( )
	{
		return Messages.getString( "info.orgchart.sample.format", //$NON-NLS-1$
				getULocale( ) );
	}
	
	public String toString( Object[] columnData ) throws ChartException
	{
		if ( columnData == null || columnData.length == 0 )
		{
			throw new ChartException( ChartEngineExtensionPlugin.ID,
					ChartException.DATA_SET,
					"exception.base.orthogonal.null.datadefinition", //$NON-NLS-1$
					Messages.getResourceBundle( getULocale( ) ) );
		}
		StringBuffer buffer = new StringBuffer( );
		for ( int i = 0; i < columnData.length; i++ )
		{
			if ( columnData[i] == null )
			{
				throw new ChartException( ChartEngineExtensionPlugin.ID,
						ChartException.DATA_SET,
						"exception.base.orthogonal.null.datadefinition", //$NON-NLS-1$
						Messages.getResourceBundle( getULocale( ) ) );
			}
			if ( columnData[i] instanceof Object[] )
			{
				buffer.append( toOrgString( (Object[]) columnData[i] ) );
			}
			if ( i < columnData.length - 1 )
			{
				buffer.append( "," ); //$NON-NLS-1$
			}
		}
		return buffer.toString( );
	}

	private StringBuffer toOrgString( Object[] orgArray )
			throws ChartException
	{
		if ( orgArray.length != 2 || orgArray[0] == null || orgArray[1] == null )
		{
			throw new ChartException( ChartEngineExtensionPlugin.ID,
					ChartException.DATA_SET,
					"Invalid data set column" ); //$NON-NLS-1$
		}
		StringBuffer buffer = new StringBuffer( );
		buffer.append( "I" + String.valueOf( orgArray[0] ) + " " ); //$NON-NLS-1$ //$NON-NLS-2$
		buffer.append( "P" + String.valueOf( orgArray[1] ) + " " ); //$NON-NLS-1$ //$NON-NLS-2$
		return buffer;
	}
}
