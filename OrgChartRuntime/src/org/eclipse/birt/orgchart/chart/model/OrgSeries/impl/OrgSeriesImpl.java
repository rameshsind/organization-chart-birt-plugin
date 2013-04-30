/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.birt.orgchart.chart.model.OrgSeries.impl;

import org.eclipse.birt.chart.model.attribute.AttributeFactory;
import org.eclipse.birt.chart.model.attribute.LineAttributes;
import org.eclipse.birt.chart.model.attribute.LineStyle;
import org.eclipse.birt.chart.model.attribute.Position;
import org.eclipse.birt.chart.model.attribute.impl.ColorDefinitionImpl;
import org.eclipse.birt.chart.model.attribute.impl.LineAttributesImpl;
import org.eclipse.birt.chart.model.component.Series;
import org.eclipse.birt.chart.model.component.impl.SeriesImpl;
import org.eclipse.birt.orgchart.chart.i18n.Messages;
import org.eclipse.birt.orgchart.chart.model.OrgSeries.OrgSeries;
import org.eclipse.birt.orgchart.chart.model.OrgSeries.TypeFactory;
import org.eclipse.birt.orgchart.chart.model.OrgSeries.TypePackage;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Org Series</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.birt.orgchart.chart.model.OrgSeries.impl.OrgSeriesImpl#getLineAttributes <em>Line Attributes</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class OrgSeriesImpl extends SeriesImpl implements OrgSeries {
	/**
	 * The cached value of the '{@link #getLineAttributes() <em>Line Attributes</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLineAttributes()
	 * @generated
	 * @ordered
	 */
	protected LineAttributes lineAttributes;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected OrgSeriesImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TypePackage.Literals.ORG_SERIES;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LineAttributes getLineAttributes() {
		return lineAttributes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetLineAttributes(LineAttributes newLineAttributes, NotificationChain msgs) {
		LineAttributes oldLineAttributes = lineAttributes;
		lineAttributes = newLineAttributes;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, TypePackage.ORG_SERIES__LINE_ATTRIBUTES, oldLineAttributes, newLineAttributes);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLineAttributes(LineAttributes newLineAttributes) {
		if (newLineAttributes != lineAttributes) {
			NotificationChain msgs = null;
			if (lineAttributes != null)
				msgs = ((InternalEObject)lineAttributes).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - TypePackage.ORG_SERIES__LINE_ATTRIBUTES, null, msgs);
			if (newLineAttributes != null)
				msgs = ((InternalEObject)newLineAttributes).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - TypePackage.ORG_SERIES__LINE_ATTRIBUTES, null, msgs);
			msgs = basicSetLineAttributes(newLineAttributes, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TypePackage.ORG_SERIES__LINE_ATTRIBUTES, newLineAttributes, newLineAttributes));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case TypePackage.ORG_SERIES__LINE_ATTRIBUTES:
				return basicSetLineAttributes(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case TypePackage.ORG_SERIES__LINE_ATTRIBUTES:
				return getLineAttributes();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case TypePackage.ORG_SERIES__LINE_ATTRIBUTES:
				setLineAttributes((LineAttributes)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case TypePackage.ORG_SERIES__LINE_ATTRIBUTES:
				setLineAttributes((LineAttributes)null);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case TypePackage.ORG_SERIES__LINE_ATTRIBUTES:
				return lineAttributes != null;
		}
		return super.eIsSet(featureID);
	}
	
	
		//manual
		protected void initialize() {
			super.initialize();
			final LineAttributes lia = AttributeFactory.eINSTANCE.createLineAttributes( );
			( (LineAttributesImpl) lia ).set( ColorDefinitionImpl.GREY( ),
					LineStyle.SOLID_LITERAL,
					1 );
			lia.setVisible( true );	
			setLineAttributes( lia );

			setLabelPosition( Position.ABOVE_LITERAL );

		}
		
		//manual
		public String getDisplayName() {
			return Messages.getString("OrgSeriesImpl.displayName");
		}
		//manual
		public static Series create() {
			final OrgSeries rs = TypeFactory.eINSTANCE.createOrgSeries();
			((OrgSeriesImpl) rs).initialize();
			return rs;
		}
		
		//manual
				public static Series createDefault() {
					final OrgSeries rs = TypeFactory.eINSTANCE.createOrgSeries();
					((OrgSeriesImpl) rs).initialize();
					return rs;
				}
		//manual
		public OrgSeries copyInstance() {
			OrgSeriesImpl dest = new OrgSeriesImpl();
			dest.set(this);
			return dest;
		}
		
		protected void set( OrgSeries src )
		{
			super.set( src );

			if ( src.getLineAttributes( ) != null )
			{
				setLineAttributes( src.getLineAttributes( ).copyInstance( ) );
			}

		}	
		
		//manual
		public boolean canBeStacked( )
		{
			return false;
		}
		//manual
		public boolean canParticipateInCombination( )
		{
			return false;
		}
		
		@Override
		public int[] getDefinedDataDefinitionIndex( )
		{
			return new int[]{
					0, 1
			};
		}

} //OrgSeriesImpl
