package org.eclipse.birt.orgchart.chart.model;

import org.eclipse.birt.chart.model.IExtChartModelLoader;
import org.eclipse.birt.orgchart.chart.model.OrgSeries.TypePackage;
import org.eclipse.emf.ecore.EPackage;

public class OrgChartModelLoader implements IExtChartModelLoader {

	@Override
	public EPackage getChartTypePackage() {
		return TypePackage.eINSTANCE;
	}

}
