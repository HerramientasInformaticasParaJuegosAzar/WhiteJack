package casinoblackjack.integracion.transaction.transactionManager;

import integracion.transaction.Transaction;
import casinoblackjack.integracion.transaction.transactionManager.Imp.TransactionManagerImp;

/** 
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * @author Ruben
 * @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public abstract class TransactionManager 
{
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @author Ruben
	 * @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
		private static TransactionManager _instance = null;

		/** 
		 * <!-- begin-UML-doc -->
		 * <!-- end-UML-doc -->
		 * @return
		 * @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
		 */
		public static TransactionManager obtenerInstanacia() 
                {
                    if (_instance == null)
                        _instance = new TransactionManagerImp();
			// begin-user-code
			// TODO Apéndice de método generado automáticamente
			return _instance;
			// end-user-code
		}

		/** 
		 * <!-- begin-UML-doc -->
		 * <!-- end-UML-doc -->
		 * @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
		 */
		public abstract void nuevaTransaccion();

		/** 
		 * <!-- begin-UML-doc -->
		 * <!-- end-UML-doc -->
		 * @return
		 * @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
		 */
		public abstract Transaction getTransaccion();

		/** 
		 * <!-- begin-UML-doc -->
		 * <!-- end-UML-doc -->
		 * @return
		 * @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
		 */
		public abstract void eliminaTransaccion();
	
}