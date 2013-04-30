package org.eclipse.birt.orgchart.chart.swt.series;

import org.eclipse.birt.chart.model.data.SeriesDefinition;
import org.eclipse.birt.chart.ui.swt.DefaultSelectDataComponent;
import org.eclipse.birt.chart.ui.swt.interfaces.ISelectDataComponent;
import org.eclipse.birt.chart.ui.swt.wizard.ChartWizardContext;
import org.eclipse.birt.chart.ui.swt.wizard.data.BaseDataDefinitionComponent;
import org.eclipse.birt.chart.ui.util.ChartUIConstants;
import org.eclipse.birt.chart.ui.util.ChartUIUtil;
import org.eclipse.birt.orgchart.chart.ui.view.util.UIHelper;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public class OrgDataDefinitionComponent extends DefaultSelectDataComponent {


	public static final String SERIES_CLASS = "org.eclipse.birt.orgchart.chart.model.OrgSeries.impl.OrgSeriesImpl"; //$NON-NLS-1$

	private transient Label[] labelArray;
	private transient ISelectDataComponent[] dataComArray;

	private transient Composite cmpSeries = null;

	private transient SeriesDefinition seriesDefn = null;

	private transient ChartWizardContext context = null;

	private transient String sTitle = null;

	public OrgDataDefinitionComponent( SeriesDefinition seriesDefn,
			ChartWizardContext context, String sTitle )
	{
		super( );
		this.seriesDefn = seriesDefn;
		this.context = context;
		this.sTitle = sTitle;

		init( );
	}

	private void init( )
	{
		labelArray = new Label[2];
		dataComArray = new ISelectDataComponent[2];

		for ( int i = 0; i < dataComArray.length; i++ )
		{
			dataComArray[i] = new BaseDataDefinitionComponent( BaseDataDefinitionComponent.BUTTON_NONE,
					ChartUIConstants.QUERY_VALUE,
					seriesDefn,
					ChartUIUtil.getDataQuery( seriesDefn, i ),
					context,
					sTitle );
		}
	}

	public Composite createArea( Composite parent )
	{
		cmpSeries = new Composite( parent, SWT.NONE );
		{
			GridData gridData = new GridData( GridData.FILL_BOTH );
			cmpSeries.setLayoutData( gridData );

			GridLayout gridLayout = new GridLayout( 2, false );
			gridLayout.marginWidth = 0;
			gridLayout.marginHeight = 0;
			cmpSeries.setLayout( gridLayout );
		}

		for ( int i = 0; i < dataComArray.length; i++ )
		{
			labelArray[i] = new Label( cmpSeries, SWT.NONE );
			labelArray[i].setText( UIHelper.getOrgTitle(i ) + "*" ); //$NON-NLS-1$
			Composite cmpData = dataComArray[i].createArea( cmpSeries );
			cmpData.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ) );
		}
		return cmpSeries;
	}

	public void selectArea( boolean selected, Object data )
	{
		if ( data instanceof Integer )
		{
			int queryIndex = ( (Integer) data ).intValue( );
			// ChartUIUtil.setBackgroundColor( labelArray[queryIndex],
			// selected,
			// color );
			dataComArray[queryIndex].selectArea( selected, data );
		}
		else if ( data instanceof Object[] )
		{
			Object[] array = (Object[]) data;
			SeriesDefinition seriesdefinition = (SeriesDefinition) array[0];
			for ( int i = 0; i < dataComArray.length; i++ )
			{
				dataComArray[i].selectArea( selected, new Object[]{
						seriesdefinition,
						ChartUIUtil.getDataQuery( seriesdefinition, i )
				} );
			}
		}
		else
		{
			for ( int i = 0; i < dataComArray.length; i++ )
			{
				dataComArray[i].selectArea( selected, null );
			}
		}
	}

	public void dispose( )
	{
		for ( int i = 0; i < dataComArray.length; i++ )
		{
			dataComArray[i].dispose( );
		}
		super.dispose( );
	}
}
