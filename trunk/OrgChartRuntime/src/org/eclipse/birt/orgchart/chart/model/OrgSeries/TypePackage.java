/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.birt.orgchart.chart.model.OrgSeries;

import org.eclipse.birt.chart.model.component.ComponentPackage;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.eclipse.birt.orgchart.chart.model.OrgSeries.TypeFactory
 * @model kind="package"
 *        extendedMetaData="qualified='false'"
 * @generated
 */
public interface TypePackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "OrgSeries";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.birt.eclipse.org/OrgChartModelType";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "OrgSeries";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	TypePackage eINSTANCE = org.eclipse.birt.orgchart.chart.model.OrgSeries.impl.TypePackageImpl.init();

	/**
	 * The meta object id for the '{@link org.eclipse.birt.orgchart.chart.model.OrgSeries.impl.OrgSeriesImpl <em>Org Series</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.birt.orgchart.chart.model.OrgSeries.impl.OrgSeriesImpl
	 * @see org.eclipse.birt.orgchart.chart.model.OrgSeries.impl.TypePackageImpl#getOrgSeries()
	 * @generated
	 */
	int ORG_SERIES = 0;

	/**
	 * The feature id for the '<em><b>Visible</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORG_SERIES__VISIBLE = ComponentPackage.SERIES__VISIBLE;

	/**
	 * The feature id for the '<em><b>Label</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORG_SERIES__LABEL = ComponentPackage.SERIES__LABEL;

	/**
	 * The feature id for the '<em><b>Data Definition</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORG_SERIES__DATA_DEFINITION = ComponentPackage.SERIES__DATA_DEFINITION;

	/**
	 * The feature id for the '<em><b>Series Identifier</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORG_SERIES__SERIES_IDENTIFIER = ComponentPackage.SERIES__SERIES_IDENTIFIER;

	/**
	 * The feature id for the '<em><b>Data Point</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORG_SERIES__DATA_POINT = ComponentPackage.SERIES__DATA_POINT;

	/**
	 * The feature id for the '<em><b>Data Sets</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORG_SERIES__DATA_SETS = ComponentPackage.SERIES__DATA_SETS;

	/**
	 * The feature id for the '<em><b>Label Position</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORG_SERIES__LABEL_POSITION = ComponentPackage.SERIES__LABEL_POSITION;

	/**
	 * The feature id for the '<em><b>Stacked</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORG_SERIES__STACKED = ComponentPackage.SERIES__STACKED;

	/**
	 * The feature id for the '<em><b>Triggers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORG_SERIES__TRIGGERS = ComponentPackage.SERIES__TRIGGERS;

	/**
	 * The feature id for the '<em><b>Translucent</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORG_SERIES__TRANSLUCENT = ComponentPackage.SERIES__TRANSLUCENT;

	/**
	 * The feature id for the '<em><b>Curve Fitting</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORG_SERIES__CURVE_FITTING = ComponentPackage.SERIES__CURVE_FITTING;

	/**
	 * The feature id for the '<em><b>Cursor</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORG_SERIES__CURSOR = ComponentPackage.SERIES__CURSOR;

	/**
	 * The feature id for the '<em><b>Line Attributes</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORG_SERIES__LINE_ATTRIBUTES = ComponentPackage.SERIES_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Org Series</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORG_SERIES_FEATURE_COUNT = ComponentPackage.SERIES_FEATURE_COUNT + 1;


	/**
	 * Returns the meta object for class '{@link org.eclipse.birt.orgchart.chart.model.OrgSeries.OrgSeries <em>Org Series</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Org Series</em>'.
	 * @see org.eclipse.birt.orgchart.chart.model.OrgSeries.OrgSeries
	 * @generated
	 */
	EClass getOrgSeries();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.birt.orgchart.chart.model.OrgSeries.OrgSeries#getLineAttributes <em>Line Attributes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Line Attributes</em>'.
	 * @see org.eclipse.birt.orgchart.chart.model.OrgSeries.OrgSeries#getLineAttributes()
	 * @see #getOrgSeries()
	 * @generated
	 */
	EReference getOrgSeries_LineAttributes();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	TypeFactory getTypeFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link org.eclipse.birt.orgchart.chart.model.OrgSeries.impl.OrgSeriesImpl <em>Org Series</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.birt.orgchart.chart.model.OrgSeries.impl.OrgSeriesImpl
		 * @see org.eclipse.birt.orgchart.chart.model.OrgSeries.impl.TypePackageImpl#getOrgSeries()
		 * @generated
		 */
		EClass ORG_SERIES = eINSTANCE.getOrgSeries();

		/**
		 * The meta object literal for the '<em><b>Line Attributes</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ORG_SERIES__LINE_ATTRIBUTES = eINSTANCE.getOrgSeries_LineAttributes();

	}

} //TypePackage
