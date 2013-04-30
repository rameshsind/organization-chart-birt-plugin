package org.eclipse.birt.orgchart.chart.extension.datafeed;

import org.eclipse.birt.chart.computation.IConstants;
import org.eclipse.birt.chart.datafeed.AbstractDataPointDefinition;
import org.eclipse.birt.orgchart.chart.i18n.Messages;


public class OrgDataPointDefinition extends AbstractDataPointDefinition {
	
	public final static String TYPE_ID = "node_id"; //$NON-NLS-1$

	public final static String TYPE_PARENT_ID = "node_parent_id"; //$NON-NLS-1$

	private final String[] saTypeNames = {
			TYPE_ID, TYPE_PARENT_ID
	};

	private final int[] iaTypeCompatibles = {
			IConstants.NUMERICAL,
			IConstants.NUMERICAL,
	};

	public String[] getDataPointTypes( )
	{
		return new String[]{
				TYPE_ID, TYPE_PARENT_ID
		};
	}

	public String getDisplayText( String type )
	{
		if ( TYPE_ID.equals( type ) )
		{
			return Messages.getString( "info.datapoint.id" ); //$NON-NLS-1$
		}
		else if ( TYPE_PARENT_ID.equals( type ) )
		{
			return Messages.getString( "info.datapoint.parentid" ); //$NON-NLS-1$
		}
		
		return null;
	}

	public int getCompatibleDataType( String type )
	{
		for ( int i = 0; i < saTypeNames.length; i++ )
		{
			if ( saTypeNames[i].equals( type ) )
			{
				return this.iaTypeCompatibles[i];
			}
		}

		// no match, return the default value
		return 0;
	}

}
