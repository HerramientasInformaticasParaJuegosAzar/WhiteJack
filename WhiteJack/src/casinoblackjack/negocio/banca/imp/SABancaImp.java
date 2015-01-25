/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package casinoblackjack.negocio.banca.imp;

import casinoblackjack.integracion.DAO.factoriaDAO.FactoriaDAO;
import casinoblackjack.integracion.transaction.transactionManager.TransactionManager;
import casinoblackjack.negocio.banca.SABanca;
import java.util.ArrayList;

/**
 *
 * @author Krnx
 */
public class SABancaImp implements SABanca
{

    @Override
    public int altaCuenta(int idJugador, double cantidadPredefinida) 
    {
         int idBanca;
        
        TransactionManager.obtenerInstancia().nuevaTransaccion();
        try
        {
            TransactionManager.obtenerInstancia().getTransaccion().start();
        
            
            //Si el producto no existe lo insertamos
                
                
                idBanca = FactoriaDAO.obtenerInstancia().getDAOBanca().altaCuenta(idJugador, cantidadPredefinida);
                
                // Se hizo la insercion con exito.
                if(idBanca > 0)
                {
                    // Se hace el commit.
                        try
                        {
                            TransactionManager.obtenerInstancia().getTransaccion().commit();
                        }
                        // Si falla el commit.
                        catch(Exception e)
                        {
                           idBanca = -1;
                           TransactionManager.obtenerInstancia().getTransaccion().rollback(); 
                        }
                        
                    
                }                                
            
            
            // Eliminamos la transaccion.
            TransactionManager.obtenerInstancia().eliminaTransaccion();
        }
        catch(Exception e)
        {
            idBanca = -1;
            try 
             {
                 TransactionManager.obtenerInstancia().getTransaccion().rollback();
             } catch (Exception ex) 
             {
                 ex.printStackTrace();
             }
            TransactionManager.obtenerInstancia().eliminaTransaccion();
        }
        return idBanca;     
    }

    @Override
    public double consultarSaldoCuenta(int idCuenta) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double consultarSaldoCuentas() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean incrementarSaldo(double incremento) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean decrementarSaldo(double decremento) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean bajaCuenta(int idCuenta) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Integer> getCuentasJugador(int idJugador) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
