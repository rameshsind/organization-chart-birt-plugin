package org.eclipse.birt.orgchart.chart.swt.series;

import org.eclipse.birt.chart.exception.ChartException;
import org.eclipse.birt.chart.log.ILogger;
import org.eclipse.birt.chart.log.Logger;
import org.eclipse.birt.chart.model.attribute.ColorDefinition;
import org.eclipse.birt.chart.model.attribute.LineStyle;
import org.eclipse.birt.chart.model.component.Series;
import org.eclipse.birt.chart.ui.extension.i18n.Messages;
import org.eclipse.birt.chart.ui.plugin.ChartUIExtensionPlugin;
import org.eclipse.birt.chart.ui.swt.composites.LineAttributesComposite;
import org.eclipse.birt.chart.ui.swt.wizard.ChartWizardContext;
import org.eclipse.birt.chart.ui.util.ChartHelpContextIds;
import org.eclipse.birt.chart.ui.util.ChartUIConstants;
import org.eclipse.birt.chart.ui.util.ChartUIUtil;
import org.eclipse.birt.orgchart.chart.model.OrgSeries.OrgSeries;
import org.eclipse.birt.orgchart.chart.model.OrgSeries.impl.OrgSeriesImpl;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Listener;

public class OrgSeriesAttributeComposite extends Composite implements Listener {

	private transient Group grpLine = null;

	private transient LineAttributesComposite liacLine = null;

	private transient Series series = null;

	private transient ChartWizardContext context;

	public static final String SUBTASK_YSERIES_ORGCHART = ChartHelpContextIds.PREFIX
			+ "FormatOrgChartSeries_ID"; //$NON-NLS-1$

	private static ILogger logger = Logger
			.getLogger("org.eclipse.birt.orgchart.chart.swt.series.OrgSeriesAttributeComposite"); //$NON-NLS-1$

	/**
	 * @param parent
	 * @param style
	 */
	public OrgSeriesAttributeComposite(Composite parent, int style,
			ChartWizardContext context, Series series) {
		super(parent, style);
		if (!(series instanceof OrgSeriesImpl)) {
			try {
				throw new ChartException(
						ChartUIExtensionPlugin.ID,
						ChartException.VALIDATION,
						"OrgSeriesAttributeComposite.Exception.IllegalArgument", new Object[] { series.getClass().getName() }, Messages.getResourceBundle()); //$NON-NLS-1$
			} catch (ChartException e) {
				logger.log(e);
				e.printStackTrace();
			}
		}
		this.series = series;
		context.setEnabled(ChartUIConstants.SUBTASK_SERIES_Y
				+ ChartUIConstants.BUTTON_CURVE, false);
		this.context = context;
		init();
		placeComponents();

		ChartUIUtil.bindHelp(parent, SUBTASK_YSERIES_ORGCHART);
	}

	private void init() {
		this.setSize(getParent().getClientArea().width, getParent()
				.getClientArea().height);
	}

	private void placeComponents() {
		// Main content composite
		this.setLayout(new GridLayout());

		grpLine = new Group(this, SWT.NONE);
		GridLayout glLine = new GridLayout(2, false);
		glLine.horizontalSpacing = 0;
		grpLine.setLayout(glLine);
		grpLine.setLayoutData(new GridData(GridData.FILL_BOTH));
		grpLine.setText(Messages
				.getString("LineSeriesAttributeComposite.Lbl.Line")); //$NON-NLS-1$

		Composite cmpLine = new Composite(grpLine, SWT.NONE);
		{
			GridLayout gl = new GridLayout(2, false);
			gl.marginHeight = 0;
			gl.marginWidth = 0;
			gl.horizontalSpacing = 0;
			gl.verticalSpacing = 0;
			cmpLine.setLayout(gl);
			cmpLine.setLayoutData(new GridData(GridData.FILL_BOTH));
		}

		liacLine = new LineAttributesComposite(cmpLine, SWT.NONE, context,
				((OrgSeries) series).getLineAttributes(), true, true, true,
				false, false, ((OrgSeries) series).getLineAttributes());
		GridData gdLIACLine = new GridData(GridData.FILL_HORIZONTAL);
		gdLIACLine.horizontalSpan = 2;
		liacLine.setLayoutData(gdLIACLine);
		liacLine.addListener(this);

		enableLineSettings(((OrgSeries) series).getLineAttributes().isVisible());
	}

	public Point getPreferredSize() {
		return new Point(400, 200);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.swt.events.SelectionListener#widgetDefaultSelected(org.eclipse
	 * .swt.events.SelectionEvent)
	 */
	public void widgetDefaultSelected(SelectionEvent e) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.swt.widgets.Listener#handleEvent(org.eclipse.swt.widgets.
	 * Event)
	 */
	public void handleEvent(Event event) {
		if (event.widget.equals(liacLine)) {
			if (event.type == LineAttributesComposite.VISIBILITY_CHANGED_EVENT) {
				((OrgSeries) series).getLineAttributes().setVisible(
						((Boolean) event.data).booleanValue());
				enableLineSettings(((OrgSeries) series).getLineAttributes()
						.isVisible());
			} else if (event.type == LineAttributesComposite.STYLE_CHANGED_EVENT) {
				((OrgSeries) series).getLineAttributes().setStyle(
						(LineStyle) event.data);
			} else if (event.type == LineAttributesComposite.WIDTH_CHANGED_EVENT) {
				((OrgSeries) series).getLineAttributes().setThickness(
						((Integer) event.data).intValue());
			} else if (event.type == LineAttributesComposite.COLOR_CHANGED_EVENT) {
				((OrgSeries) series).getLineAttributes().setColor(
						(ColorDefinition) event.data);
			}
		}

	}

	private void enableLineSettings(boolean isEnabled) {

	}

}
