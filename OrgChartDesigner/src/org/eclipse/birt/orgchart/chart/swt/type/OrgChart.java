package org.eclipse.birt.orgchart.chart.swt.type;

import java.util.Collection;
import java.util.Vector;

import org.eclipse.birt.chart.model.Chart;
import org.eclipse.birt.chart.model.ChartWithAxes;
import org.eclipse.birt.chart.model.ChartWithoutAxes;
import org.eclipse.birt.chart.model.attribute.AxisType;
import org.eclipse.birt.chart.model.attribute.ChartDimension;
import org.eclipse.birt.chart.model.attribute.LegendItemType;
import org.eclipse.birt.chart.model.attribute.Orientation;
import org.eclipse.birt.chart.model.attribute.Text;
import org.eclipse.birt.chart.model.component.Axis;
import org.eclipse.birt.chart.model.component.Series;
import org.eclipse.birt.chart.model.component.impl.SeriesImpl;
import org.eclipse.birt.chart.model.data.BaseSampleData;
import org.eclipse.birt.chart.model.data.DataFactory;
import org.eclipse.birt.chart.model.data.OrthogonalSampleData;
import org.eclipse.birt.chart.model.data.SampleData;
import org.eclipse.birt.chart.model.data.SeriesDefinition;
import org.eclipse.birt.chart.model.data.impl.SeriesDefinitionImpl;
import org.eclipse.birt.chart.model.impl.ChartWithoutAxesImpl;
import org.eclipse.birt.chart.ui.swt.ChartPreviewPainter;
import org.eclipse.birt.chart.ui.swt.DefaultChartSubTypeImpl;
import org.eclipse.birt.chart.ui.swt.DefaultChartTypeImpl;
import org.eclipse.birt.chart.ui.swt.HelpContentImpl;
import org.eclipse.birt.chart.ui.swt.interfaces.IChartSubType;
import org.eclipse.birt.chart.ui.swt.interfaces.IChartType;
import org.eclipse.birt.chart.ui.swt.interfaces.IHelpContent;
import org.eclipse.birt.chart.ui.swt.interfaces.ISelectDataComponent;
import org.eclipse.birt.chart.ui.swt.interfaces.ISelectDataCustomizeUI;
import org.eclipse.birt.chart.ui.swt.wizard.ChartWizardContext;
import org.eclipse.birt.chart.ui.swt.wizard.data.DefaultBaseSeriesComponent;
import org.eclipse.birt.chart.ui.util.ChartCacheManager;
import org.eclipse.birt.chart.ui.util.ChartUIUtil;
import org.eclipse.birt.orgchart.chart.i18n.Messages;
import org.eclipse.birt.orgchart.chart.model.OrgSeries.OrgSeries;
import org.eclipse.birt.orgchart.chart.model.OrgSeries.impl.OrgSeriesImpl;
import org.eclipse.birt.orgchart.chart.render.OrgChartRenderer;
import org.eclipse.birt.orgchart.chart.ui.view.util.UIHelper;
import org.eclipse.emf.common.util.EList;
import org.eclipse.swt.graphics.Image;

public class OrgChart extends DefaultChartTypeImpl {

	private static final String sStandardDescription = Messages
			.getString("OrgChart.Txt.Description"); //$NON-NLS-1$

	private static final String[] saDimensions = new String[] { TWO_DIMENSION_TYPE };

	public OrgChart() {
		chartTitle = Messages.getString("OrgChart.Txt.DefaultOrgChartTitle"); //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.birt.chart.ui.swt.IChartType#getTypeName()
	 */
	@Override
	public String getName() {
		return OrgChartRenderer.TYPE_LITERAL;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.birt.chart.ui.swt.IChartType#getTypeName()
	 */
	@Override
	public Image getImage() {
		return UIHelper.getImage("icons/obj16/orgchart16.gif"); //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.birt.chart.ui.swt.DefaultChartTypeImpl#getDisplayName()
	 */
	@Override
	public String getDisplayName() {
		return Messages.getString("OrgChart.Txt.DisplayName"); //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.birt.chart.ui.swt.IChartType#getHelp()
	 */
	@Override
	public IHelpContent getHelp() {
		return new HelpContentImpl(OrgChartRenderer.TYPE_LITERAL,
				Messages.getString("OrgChart.Txt.HelpText")); //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.birt.chart.ui.swt.interfaces.IChartType#getChartSubtypes(
	 * java.lang.String)
	 */
	@Override
	public Collection<IChartSubType> getChartSubtypes(String sDimension,
			Orientation orientation) {
		Vector<IChartSubType> vSubTypes = new Vector<IChartSubType>();

		if (sDimension.equals(TWO_DIMENSION_TYPE)
				|| sDimension.equals(ChartDimension.TWO_DIMENSIONAL_LITERAL
						.getName())) {

			vSubTypes.add(new DefaultChartSubTypeImpl(
					OrgChartRenderer.STANDARD_SUBTYPE_LITERAL, UIHelper
							.getImage("icons/wizban/orgchart71.gif"), //$NON-NLS-1$
					sStandardDescription, Messages
							.getString("OrgChart.SubType.Standard"))); //$NON-NLS-1$

		}
		return vSubTypes;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.birt.chart.ui.swt.interfaces.IChartType#getModel(java.lang
	 * .String, java.lang.String, java.lang.String)
	 */
	@Override
	public Chart getModel(String sSubType, Orientation orientation,
			String sDimension, Chart currentChart) {
		ChartWithoutAxes newChart = null;

		if (currentChart != null) {
			newChart = (ChartWithoutAxes) getConvertedChart(currentChart,
					sSubType, sDimension);
			if (newChart != null) {
				return newChart;
			}
		}
		newChart = ChartWithoutAxesImpl.createDefault();
		newChart.setType("Org Chart");
		newChart.setSubType(sSubType);
		newChart.setDimension(ChartDimension.TWO_DIMENSIONAL_LITERAL);
		if (newChart.getDimension().equals(
				ChartDimension.TWO_DIMENSIONAL_WITH_DEPTH_LITERAL)) {
			newChart.setSeriesThickness(15);
		}
		newChart.getLegend().setItemType(LegendItemType.SERIES_LITERAL);

		SeriesDefinition sdX = SeriesDefinitionImpl.createDefault();
		Series categorySeries = SeriesImpl.createDefault();
		sdX.getSeries().add(categorySeries);
		sdX.getQuery().setDefinition("Base Series"); //$NON-NLS-1$

		SeriesDefinition sdY = SeriesDefinitionImpl.createDefault();
		OrgSeries valueSeries = (OrgSeries) getSeries();
		valueSeries.getLabel().getInsets().set(0, 3, 0, 3);
		valueSeries.getLabel().setVisible(true);
		valueSeries.getLabel().getOutline().setVisible(true);
		valueSeries.setSeriesIdentifier("Series 1"); //$NON-NLS-1$

		sdY.getSeries().add(valueSeries);

		sdX.getSeriesDefinitions().add(sdY);

		newChart.getSeriesDefinitions().add(sdX);

		addSampleData(newChart);

		return newChart;
	}

	private void addSampleData(Chart newChart) {
		SampleData sd = DataFactory.eINSTANCE.createSampleData();
		sd.getBaseSampleData().clear();
		sd.getOrthogonalSampleData().clear();

		// Create Base Sample Data
		BaseSampleData sdBase = DataFactory.eINSTANCE.createBaseSampleData();
		sdBase.setDataSetRepresentation("X, Y, Z, D, E, F, G"); //$NON-NLS-1$
		sd.getBaseSampleData().add(sdBase);

		// Create Orthogonal Sample Data (with simulation count of 2)
		OrthogonalSampleData oSample1 = DataFactory.eINSTANCE
				.createOrthogonalSampleData();
		oSample1.setDataSetRepresentation("I1 P0,I2 P1,I3 P1,I4 P2,I5 P2,I6 P3,I7 P3"); //$NON-NLS-1$
		oSample1.setSeriesDefinitionIndex(0);
		sd.getOrthogonalSampleData().add(oSample1);
		/*OrthogonalSampleData oSample2 = DataFactory.eINSTANCE
				.createOrthogonalSampleData();
		oSample2.setDataSetRepresentation("0, 1, 1, 2, 2, 3, 3"); //$NON-NLS-1$
		oSample2.setSeriesDefinitionIndex(0);
		sd.getOrthogonalSampleData().add(oSample2);*/

		newChart.setSampleData(sd);
	}

	private Chart getConvertedChart(Chart currentChart, String sNewSubType,
			String sNewDimension) {
		if (currentChart instanceof ChartWithoutAxes
				&& currentChart.getType().equals("Org Chart")) {

			currentChart.setSubType(sNewSubType);
			// ( (DialChart) currentChart ).setDialSuperimposition(
			// sNewSubType.equals( SUPERIMPOSED_SUBTYPE_LITERAL ) );
			if (!currentChart.getDimension().equals(
					ChartDimension.TWO_DIMENSIONAL_LITERAL)) {
				currentChart
						.setDimension(ChartDimension.TWO_DIMENSIONAL_LITERAL);
			}
			return currentChart;
		}
		return null;
	}

	private Series getConvertedSeries(Series series, int seriesIndex) {
		// Do not convert base series
		if (series.getClass().getName().equals(SeriesImpl.class.getName())) {
			return series;
		}

		OrgSeries orgSeries = (OrgSeries) ChartCacheManager.getInstance()
				.findSeries(OrgSeriesImpl.class.getName(), seriesIndex);
		if (orgSeries == null) {
			orgSeries = (OrgSeries) OrgSeriesImpl.createDefault();
		}

		// Copy generic series properties
		ChartUIUtil.copyGeneralSeriesAttributes(series, orgSeries);

		return orgSeries;
	}

	public ISelectDataComponent getBaseUI(Chart chart,
			ISelectDataCustomizeUI selectDataUI, ChartWizardContext context,
			String sTitle) {
		DefaultBaseSeriesComponent component = new DefaultBaseSeriesComponent(
				ChartUIUtil.getBaseSeriesDefinitions(chart).get(0), context,
				sTitle);
		component.setLabelText(Messages
				.getString("OrgChart.Txt.DefaultOrgChartTitle")); //$NON-NLS-1$
		return component;
	}

	public String getValueDefinitionName() {
		return Messages.getString("OrgLeftAreaComponent.Label.NodeLink"); //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.birt.chart.ui.swt.interfaces.IChartType#getSeries()
	 */
	@Override
	public Series getSeries() {
		return getSeries(true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.birt.chart.ui.swt.DefaultChartTypeImpl#getSeries(boolean)
	 */
	public Series getSeries(boolean needInitialing) {
		if (needInitialing) {
			return OrgSeriesImpl.create();
		} else {
			return OrgSeriesImpl.createDefault();
		}
	}

	@Override
	public boolean isChartWithAxes() {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.birt.chart.ui.swt.interfaces.IChartType#getSupportedDimensions
	 * ()
	 */
	@Override
	public String[] getSupportedDimensions() {
		return saDimensions;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.birt.chart.ui.swt.interfaces.IChartType#getDefaultDimension()
	 */
	@Override
	public String getDefaultDimension() {
		return saDimensions[0];
	}

	@Override
	public boolean canExpand() {
		return true;
	}
}
