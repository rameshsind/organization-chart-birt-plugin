/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.birt.orgchart.chart.model.OrgSeries;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.birt.orgchart.chart.model.OrgSeries.TypePackage
 * @generated
 */
public interface TypeFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	TypeFactory eINSTANCE = org.eclipse.birt.orgchart.chart.model.OrgSeries.impl.TypeFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Org Series</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Org Series</em>'.
	 * @generated
	 */
	OrgSeries createOrgSeries();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	TypePackage getTypePackage();

} //TypeFactory
