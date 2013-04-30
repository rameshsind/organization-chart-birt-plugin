package org.eclipse.birt.orgchart.chart.extension.datafeed;

import org.eclipse.birt.chart.computation.ValueFormatter;
import org.eclipse.birt.chart.datafeed.IDataPointEntry;
import org.eclipse.birt.chart.exception.ChartException;
import org.eclipse.birt.chart.extension.datafeed.StockDataPointDefinition;
import org.eclipse.birt.chart.log.Logger;
import org.eclipse.birt.chart.model.attribute.FormatSpecifier;

import com.ibm.icu.util.ULocale;

public class OrgEntry implements IDataPointEntry {

	private int id;

	private int parentId;

	/**
	 * 
	 * @param dOpen
	 * @param dLow
	 * @param dHigh
	 * @param dClose
	 */
	public OrgEntry( int id, int parentId)
	{
		this.id = id;
		this.parentId = parentId;
	}

	/**
	 * 
	 * @param oaTwoComponents
	 */
	public OrgEntry( Object[] oaTwoComponents )
	{
		assert oaTwoComponents.length == 2;
		this.id = ( oaTwoComponents[0] instanceof Number ) ? ( (Number) oaTwoComponents[0] ).intValue() : Integer.MAX_VALUE;
		this.parentId = ( oaTwoComponents[1] instanceof Number ) ? ( (Number) oaTwoComponents[1] ).intValue( ): Integer.MAX_VALUE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString( )
	{
		return getFormattedString( null, ULocale.getDefault( ) );
	}

	/**
	 * @return Returns the id.
	 */
	public final int getId( )
	{
		return id;
	}

	/**
	 * @param close
	 *            The id to set.
	 */
	public final void setId( int id )
	{
		this.id = id;
	}

	/**
	 * @return Returns the parentId.
	 */
	public final int getParentId( )
	{
		return parentId;
	}

	/**
	 * @param parentId
	 *            The parentId to set.
	 */
	public final void setParentId( int parentId )
	{
		this.parentId = parentId;
	}

	public String getFormattedString( String type, FormatSpecifier formatter,
			ULocale locale )
	{
		String str = null;
		try
		{
			int iValue = Integer.MAX_VALUE;
			if ( OrgDataPointDefinition.TYPE_ID.equals( type ) )
			{
				iValue = id;
			}
			else if ( OrgDataPointDefinition.TYPE_PARENT_ID.equals( type ) )
			{
				iValue = parentId;
			}
			else
			{
				return null;
			}

			if ( formatter == null )
			{
				str = Integer.toString( iValue );
			}
			else
			{
				str = ValueFormatter.format( new Integer( iValue ),
						formatter,
						locale,
						null );
			}

		}
		catch ( ChartException e )
		{
			Logger.getLogger( "org.eclipse.birt.chart.engine/exception" ) //$NON-NLS-1$
					.log( e );
		}
		return str;
	}

	public String getFormattedString( FormatSpecifier formatter, ULocale locale )
	{
		StringBuilder sb = new StringBuilder( );
		sb.append( 'I' );
		sb.append( getFormattedString( OrgDataPointDefinition.TYPE_ID,
				formatter,
				locale ) );
		sb.append( " P" ); //$NON-NLS-1$
		sb.append( getFormattedString( OrgDataPointDefinition.TYPE_PARENT_ID,
				formatter,
				locale ) );
		return sb.toString( );
	}

	public boolean isValid( )
	{
		return ( !( Integer.MAX_VALUE == id	|| Integer.MAX_VALUE == parentId ) );
	}
}
