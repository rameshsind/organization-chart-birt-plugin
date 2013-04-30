package org.eclipse.birt.orgchart.chart.model.data.impl;

import org.eclipse.birt.chart.model.data.DataPackage;
import org.eclipse.birt.chart.model.data.impl.DataSetImpl;
import org.eclipse.birt.orgchart.chart.model.data.OrgDataSet;
import org.eclipse.emf.ecore.EClass;

public class OrgDataSetImpl extends DataSetImpl implements OrgDataSet {

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected OrgDataSetImpl( )
	{
		super( );
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass( )
	{
		//TODO: change this to ORG_DATA_SET
		return DataPackage.Literals.STOCK_DATA_SET;
	}

	/**
	 * A convenience method to create an initialized 'StockDataSet' instance
	 * 
	 * @param oValues
	 *            The Collection (of StockEntry) or StockEntry[] of values associated with this dataset
	 * 
	 * @return
	 */
	public static final OrgDataSet create( Object oValues )
	{
		final OrgDataSet sds = new OrgDataSetImpl();
		( (OrgDataSetImpl) sds ).initialize( );
		sds.setValues( oValues );
		return sds;
	}

	/**
	 * This method performs any initialization of the instance when created
	 * 
	 * Note: Manually written
	 */
	protected void initialize( )
	{
	}

	/**
	 * @generated
	 */
	public OrgDataSet copyInstance( )
	{
		OrgDataSetImpl dest = new OrgDataSetImpl( );
		dest.set( this );
		return dest;
	}

	/**
	 * @generated
	 */
	protected void set( OrgDataSet src )
	{

		super.set( src );

	}

}
