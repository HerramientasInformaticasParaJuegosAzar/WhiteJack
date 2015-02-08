/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package casinoblackjack.negocio.factoriaSA;

import casinoblackjack.negocio.cuentas.SA.SABanca;
import casinoblackjack.negocio.factoriaSA.Imp.FactoriaSAImp;
import casinoblackjack.negocio.turno.SATurno;

/**
 *
 * @author Krnx
 */
public abstract class FactoriaSA 
{
    private static FactoriaSAImp instance = null;
    
    public static FactoriaSAImp getInstancia()
    {
        if (instance == null)
            instance = new FactoriaSAImp();
        
        return instance;
    }
    
    public abstract SATurno obtenerSATurno();
    public abstract SABanca obtenerSABanca();
    
}
