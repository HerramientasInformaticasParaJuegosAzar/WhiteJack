/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package casinoblackjack.negocio.factoriaSA.Imp;

import casinoblackjack.negocio.cuentas.SA.SABanca;
import casinoblackjack.negocio.cuentas.SA.imp.SABancaImp;
import casinoblackjack.negocio.factoriaSA.FactoriaSA;
import casinoblackjack.negocio.turno.Imp.SATurnoImp;
import casinoblackjack.negocio.turno.SATurno;

/**
 *
 * @author Krnx
 */
public class FactoriaSAImp extends FactoriaSA
{

    @Override
    public SATurno obtenerSATurno() 
    {
        return new SATurnoImp();
    }

    @Override
    public SABanca obtenerSABanca() 
    {
        return new SABancaImp();
    }
    
}
