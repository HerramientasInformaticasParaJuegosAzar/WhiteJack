package casinoblackjack.integracion.DAO.factoriaDAO;

import casinoblackjack.integracion.DAO.turno.DAOTurno;
import casinoblackjack.integracion.DAO.factoriaDAO.Imp.FactoriaDAOImp;

/** 
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * 
 * @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public abstract class FactoriaDAO {
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private static FactoriaDAO _instance = null;

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @return
	 * @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public abstract DAOTurno getDAOTurno();

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @return
	 * @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	
	public static FactoriaDAO obtenerInstancia() {
		// begin-user-code
		// TODO Apéndice de método generado automáticamente
            if (_instance == null)
                _instance = new FactoriaDAOImp();
            return _instance;
		// end-user-code
	}

}