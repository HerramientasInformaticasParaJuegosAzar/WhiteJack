package casinoblackjack.integracion.transaction.transactionFactory;

import integracion.transaction.Transaction;
import casinoblackjack.integracion.transaction.transactionFactory.Imp.TransactionFactoryImp;

/** 
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * 
 * @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public abstract class TransactionFactory 
{
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private static TransactionFactory _instance = null;

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public abstract Transaction generaTransaccion();

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @return
	 * @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public static TransactionFactory getInstance() 
        {
            if (_instance == null)
                _instance = new TransactionFactoryImp();
            
		// begin-user-code
		// TODO Apéndice de método generado automáticamente
		return _instance;
		// end-user-code
	}
}