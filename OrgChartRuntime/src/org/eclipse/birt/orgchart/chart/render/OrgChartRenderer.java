package org.eclipse.birt.orgchart.chart.render;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.eclipse.birt.chart.computation.DataPointHints;
import org.eclipse.birt.chart.computation.withoutaxes.SeriesRenderingHints;
import org.eclipse.birt.chart.device.IDeviceRenderer;
import org.eclipse.birt.chart.device.IPrimitiveRenderer;
import org.eclipse.birt.chart.device.IStructureDefinitionListener;
import org.eclipse.birt.chart.event.EventObjectCache;
import org.eclipse.birt.chart.event.LineRenderEvent;
import org.eclipse.birt.chart.event.TextRenderEvent;
import org.eclipse.birt.chart.event.WrappedStructureSource;
import org.eclipse.birt.chart.exception.ChartException;
import org.eclipse.birt.chart.model.attribute.Bounds;
import org.eclipse.birt.chart.model.attribute.Fill;
import org.eclipse.birt.chart.model.attribute.LineAttributes;
import org.eclipse.birt.chart.model.attribute.Location;
import org.eclipse.birt.chart.model.attribute.Position;
import org.eclipse.birt.chart.model.component.Label;
import org.eclipse.birt.chart.model.component.Series;
import org.eclipse.birt.chart.model.data.SeriesDefinition;
import org.eclipse.birt.chart.model.layout.Legend;
import org.eclipse.birt.chart.model.layout.Plot;
import org.eclipse.birt.chart.plugin.ChartEngineExtensionPlugin;
import org.eclipse.birt.chart.render.BaseRenderer;
import org.eclipse.birt.chart.render.ISeriesRenderingHints;
import org.eclipse.birt.chart.script.AbstractScriptHandler;
import org.eclipse.birt.chart.script.ScriptHandler;
import org.eclipse.birt.chart.util.FillUtil;
import org.eclipse.birt.orgchart.chart.extension.datafeed.OrgEntry;
import org.eclipse.birt.orgchart.chart.i18n.Messages;
import org.eclipse.birt.orgchart.chart.model.OrgSeries.OrgSeries;
import org.eclipse.emf.common.util.EList;

public class OrgChartRenderer extends BaseRenderer {

	Map<Integer, List<Node>> parentChidrenMap = null;
	public static final String TYPE_LITERAL = "Org Chart"; //$NON-NLS-1$

	public static final String STANDARD_SUBTYPE_LITERAL = "Standard Org Chart"; //$NON-NLS-1$

	private double rootLableHeight = 10;

	@Override
	public void compute(Bounds arg0, Plot p, ISeriesRenderingHints isrh)
			throws ChartException {

		final SeriesRenderingHints srh = (SeriesRenderingHints) isrh;

		// VALIDATE CONSISTENT DATASET COUNT BETWEEN BASE AND ORTHOGONAL
		try {
			validateDataSetCount(isrh);
		} catch (ChartException vex) {
			throw new ChartException(ChartEngineExtensionPlugin.ID,
					ChartException.GENERATION, vex);
		}
		try {
			DataPointHints[] dpha = srh.getDataPoints();
			if (dpha == null || dpha.length < 1) {
				throw new ChartException(ChartEngineExtensionPlugin.ID,
						ChartException.RENDERING,
						"exception.invalid.datapoint.orgchart", //$NON-NLS-1$
						Messages.getResourceBundle(getRunTimeContext()
								.getULocale()));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ChartException(ChartEngineExtensionPlugin.ID,
					ChartException.GENERATION, ex);
		}

	}

	@Override
	public void renderLegendGraphic(IPrimitiveRenderer ipr, Legend l, Fill f,
			Bounds bo) throws ChartException {

	}

	@Override
	public void renderSeries(IPrimitiveRenderer ipr, Plot p,
			ISeriesRenderingHints isrh) throws ChartException {

		SeriesRenderingHints srh = (SeriesRenderingHints) isrh;
		populateParentChildrenMap(srh);
		Bounds currentArea = getCellBounds();
		OrgSeries currentSeries = (OrgSeries) getSeries();

		SeriesDefinition sd = getSeriesDefinition();

		render(ipr, getDevice(), currentArea, currentSeries, sd, p, isrh);

	}

	private final void render(IPrimitiveRenderer ipr, IDeviceRenderer idr,
			Bounds bo, OrgSeries se, SeriesDefinition sd, Plot p,
			ISeriesRenderingHints isrh) throws ChartException {

		List<Series> rts = sd.getRunTimeSeries();
		int iThisSeriesIndex = rts.indexOf(se);
		if (iThisSeriesIndex == -1)
			iThisSeriesIndex = getSeriesIndex();
		final EList<Fill> elPalette = sd.getSeriesPalette().getEntries();
		final AbstractScriptHandler<?> sh = getRunTimeContext()
				.getScriptHandler();
		dc = getDeferredCache();

		double screenWidth = bo.getWidth();
		int rootParent = 0;
		List<Node> list = parentChidrenMap.get(rootParent);
		if (list.isEmpty()) {
			return;
		}
		int count = 1;
		while (true) {
			if (count == 1) {
				rootLableHeight += bo.getTop()
						+ (se.getLabel().getInsets().getTop() + se.getLabel()
								.getInsets().getBottom())
						* 1.4
						+ (se.getLabel().getCaption().getFont().getSize() * 1.8);
				drawNode(isrh, ipr, se, elPalette, iThisSeriesIndex,
						bo.getLeft(), bo.getLeft() + screenWidth,
						rootLableHeight, list);
				count++;
				continue;
			}
			List<Node> childrenList = new ArrayList<Node>();

			for (Node parentNode : list) {
				List<Node> nodeList = parentChidrenMap.get(parentNode.getId());
				if (nodeList != null) {
					for (Node childNode : nodeList) {
						childNode.setParentWidth(parentNode.getWidth());
						childNode.setParentHiegth(parentNode.getHiegth());
						childrenList.add(childNode);
					}
				}
			}
			if (childrenList.isEmpty()) {
				break;
			} else {
				drawNode(isrh, ipr, se, elPalette, iThisSeriesIndex,
						bo.getLeft(), bo.getLeft() + screenWidth,
						rootLableHeight + (70 * (count - 1)), childrenList);
			}
			list = childrenList;
			count++;
		}
	}

	private void drawNode(ISeriesRenderingHints isrh, IPrimitiveRenderer ipr,
			OrgSeries se, EList<Fill> elPalette, int iThisSeriesIndex,
			double screenStartWidth, double screenEndWidth, double hieght,
			List<Node> list) throws ChartException {

		if (list.size() == 1) {

			Node node = list.get(0);

			final boolean bPaletteByCategory = isPaletteByCategory();
			Fill fPaletteEntry = null;
			if (!bPaletteByCategory) {
				fPaletteEntry = FillUtil.getPaletteFill(elPalette,
						iThisSeriesIndex);
				updateTranslucency(fPaletteEntry, se);

			} else if (bPaletteByCategory) {
				fPaletteEntry = FillUtil.getPaletteFill(elPalette,
						node.getPosition());
			}

			LineAttributes lia = se.getLineAttributes();
			lia.setColor(FillUtil.getColor(fPaletteEntry));
			LineAttributes liaCopied = null;
			if (bPaletteByCategory) {
				liaCopied = lia.copyInstance();
			}

			final AbstractScriptHandler<?> sh = getRunTimeContext()
					.getScriptHandler();
			// draw labels
			Label seriesLable = se.getLabel();
			Label laDataPoint = seriesLable.copyInstance();
			Location loDataPoint = null;
			laDataPoint.getCaption().setValue(node.getName());
			if (liaCopied != null) {
				laDataPoint.setOutline(liaCopied);
			}
			Position pDataPoint = Position.ABOVE_LITERAL;

			double mid = (screenEndWidth - screenStartWidth) / 2;
			loDataPoint = goFactory.createLocation(screenStartWidth + mid,
					hieght);
			node.setHiegth(hieght);
			node.setWidth(screenStartWidth + mid);

			ScriptHandler.callFunction(sh,
					ScriptHandler.BEFORE_DRAW_DATA_POINT_LABEL, node.getDph(),
					laDataPoint, getRunTimeContext().getScriptContext());
			getRunTimeContext().notifyStructureChange(
					IStructureDefinitionListener.BEFORE_DRAW_DATA_POINT_LABEL,
					laDataPoint);

			renderLabel(
					WrappedStructureSource.createSeriesDataPoint(se,
							node.getDph()),
					TextRenderEvent.RENDER_TEXT_AT_LOCATION, laDataPoint,
					pDataPoint, loDataPoint, null);

			ScriptHandler.callFunction(sh,
					ScriptHandler.AFTER_DRAW_DATA_POINT_LABEL, node.getDph(),
					laDataPoint, getRunTimeContext().getScriptContext());
			getRunTimeContext().notifyStructureChange(
					IStructureDefinitionListener.AFTER_DRAW_DATA_POINT_LABEL,
					laDataPoint);

			if (node.getParentId() != 0) {

				double nodeHeight = (laDataPoint.getInsets().getTop() + laDataPoint
						.getInsets().getBottom())
						* 1.4
						+ (laDataPoint.getCaption().getFont().getSize() * 1.8);

				ScriptHandler.callFunction(sh,
						ScriptHandler.BEFORE_DRAW_DATA_POINT, node.getDph(),
						fPaletteEntry, getRunTimeContext().getScriptContext());
				getRunTimeContext().notifyStructureChange(
						IStructureDefinitionListener.BEFORE_DRAW_DATA_POINT,
						node.getDph());

				final LineRenderEvent lre1 = ((EventObjectCache) ipr)
						.getEventObject(WrappedStructureSource
								.createSeriesDataPoint(se, node.getDph()),
								LineRenderEvent.class);
				lre1.setStart(goFactory.createLocation(node.getParentWidth(),
						node.getParentHiegth()));
				lre1.setEnd(goFactory.createLocation(node.getWidth(),
						node.getHiegth() - nodeHeight));
				lre1.setLineAttributes(lia);

				ScriptHandler.callFunction(sh,
						ScriptHandler.AFTER_DRAW_DATA_POINT, node.getDph(),
						fPaletteEntry, getRunTimeContext().getScriptContext());
				getRunTimeContext().notifyStructureChange(
						IStructureDefinitionListener.AFTER_DRAW_DATA_POINT,
						node.getDph());
				ipr.drawLine(lre1);
			}
			return;
		}
		if (list.size() % 2 == 1) {
			List<Node> list1 = new ArrayList<Node>();
			List<Node> list2 = new ArrayList<Node>();
			List<Node> list3 = new ArrayList<Node>();
			int count = 1;
			for (Node element : list) {
				if (count <= (list.size() - 1) / 2) {
					list1.add(element);
				} else if (count == ((list.size() - 1) / 2) + 1) {
					list2.add(element);
				} else {
					list3.add(element);
				}
				count++;
			}
			double diff = screenEndWidth - screenStartWidth;
			double middleStartWidth = screenStartWidth + (diff / 2) - 50;
			drawNode(isrh, ipr, se, elPalette, iThisSeriesIndex,
					screenStartWidth, middleStartWidth, hieght, list1);
			diff = screenEndWidth - screenStartWidth;
			middleStartWidth = screenStartWidth + (diff / 2) - 50;
			drawNode(isrh, ipr, se, elPalette, iThisSeriesIndex,
					middleStartWidth, middleStartWidth + 100, hieght, list2);
			drawNode(isrh, ipr, se, elPalette, iThisSeriesIndex,
					middleStartWidth + 100, screenEndWidth, hieght, list3);

		} else {

			List<Node> list1 = new ArrayList<Node>();
			List<Node> list2 = new ArrayList<Node>();
			int count = 1;
			for (Node element : list) {
				if (count <= (list.size()) / 2) {
					list1.add(element);
				} else {
					list2.add(element);
				}
				count++;
			}

			double diff = screenEndWidth - screenStartWidth;
			double middleWidth = screenStartWidth + (diff / 2);
			drawNode(isrh, ipr, se, elPalette, iThisSeriesIndex,
					screenStartWidth, middleWidth, hieght, list1);
			drawNode(isrh, ipr, se, elPalette, iThisSeriesIndex, middleWidth,
					screenEndWidth, hieght, list2);

		}

	}

	private void populateParentChildrenMap(SeriesRenderingHints srh)
			throws ChartException {
		parentChidrenMap = new HashMap<Integer, List<Node>>();
		HashSet<Integer> nodeIdSet = new HashSet<Integer>();
		boolean isRootNodeAvailable = false;
		int position = 0;
		for (DataPointHints dataPointHints : srh.getDataPoints()) {
			String name = dataPointHints.getBaseDisplayValue();
			OrgEntry oea = (OrgEntry) dataPointHints.getOrthogonalValue();
			if (oea.getParentId() == 0) {
				isRootNodeAvailable = true;
			}
			if (oea.getId() == oea.getParentId()) {
				throw new ChartException(ChartEngineExtensionPlugin.ID,
						ChartException.RENDERING,
						"exception.nodeid.rootid.same.orgchart", //$NON-NLS-1$
						Messages.getResourceBundle(getRunTimeContext()
								.getULocale()));
			}
			if(!nodeIdSet.add(oea.getId()))
			{
				throw new ChartException(ChartEngineExtensionPlugin.ID,
						ChartException.RENDERING,
						"exception.nodeid.duplicate.orgchart", //$NON-NLS-1$
						Messages.getResourceBundle(getRunTimeContext()
								.getULocale()));
			}
			List<Node> nodeList = parentChidrenMap.get(oea.getParentId());
			if (nodeList == null) {
				nodeList = new ArrayList<Node>();
				parentChidrenMap.put(oea.getParentId(), nodeList);
			}
			nodeList.add(new Node(name, oea.getId(), oea.getParentId(),
					dataPointHints, position));
			position++;
		}
		if (!isRootNodeAvailable) {
			throw new ChartException(ChartEngineExtensionPlugin.ID,
					ChartException.RENDERING,
					"exception.root.notavailable.orgchart", //$NON-NLS-1$
					Messages.getResourceBundle(getRunTimeContext().getULocale()));
		}
	}

	class Node {
		int id;
		String name;
		int parentId;
		double width;
		double hiegth;
		double parentWidth;
		double parentHiegth;
		DataPointHints dph;
		int position;

		public Node(String name, int id, int parentId, DataPointHints dph,
				int position) {
			super();
			this.name = name;
			this.id = id;
			this.parentId = parentId;
			this.dph = dph;
			this.position = position;
		}

		public double getParentWidth() {
			return parentWidth;
		}

		public void setParentWidth(double parentWidth) {
			this.parentWidth = parentWidth;
		}

		public double getParentHiegth() {
			return parentHiegth;
		}

		public void setParentHiegth(double parentHiegth) {
			this.parentHiegth = parentHiegth;
		}

		public double getWidth() {
			return width;
		}

		public void setWidth(double width) {
			this.width = width;
		}

		public double getHiegth() {
			return hiegth;
		}

		public void setHiegth(double hiegth) {
			this.hiegth = hiegth;
		}

		public String getName() {
			return name;
		}

		public int getId() {
			return id;
		}

		public int getParentId() {
			return parentId;
		}

		public DataPointHints getDph() {
			return dph;
		}

		public int getPosition() {
			return position;
		}
	}

}
