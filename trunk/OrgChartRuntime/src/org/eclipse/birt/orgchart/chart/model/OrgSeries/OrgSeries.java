/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.birt.orgchart.chart.model.OrgSeries;

import org.eclipse.birt.chart.model.attribute.LineAttributes;

import org.eclipse.birt.chart.model.component.Series;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Org Series</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 
 * 				Node as Lable and Connectors will use LineAttributes
 * 			
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.birt.orgchart.chart.model.OrgSeries.OrgSeries#getLineAttributes <em>Line Attributes</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.birt.orgchart.chart.model.OrgSeries.TypePackage#getOrgSeries()
 * @model extendedMetaData="name='OrgSeries' kind='elementOnly'"
 * @generated
 */
public interface OrgSeries extends EObject, Series {
	/**
	 * Returns the value of the '<em><b>Line Attributes</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Line Attributes</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Line Attributes</em>' containment reference.
	 * @see #setLineAttributes(LineAttributes)
	 * @see org.eclipse.birt.orgchart.chart.model.OrgSeries.TypePackage#getOrgSeries_LineAttributes()
	 * @model containment="true" required="true"
	 *        extendedMetaData="kind='element' name='LineAttributes' namespace='##targetNamespace'"
	 * @generated
	 */
	LineAttributes getLineAttributes();

	/**
	 * Sets the value of the '{@link org.eclipse.birt.orgchart.chart.model.OrgSeries.OrgSeries#getLineAttributes <em>Line Attributes</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Line Attributes</em>' containment reference.
	 * @see #getLineAttributes()
	 * @generated
	 */
	void setLineAttributes(LineAttributes value);

} // OrgSeries
