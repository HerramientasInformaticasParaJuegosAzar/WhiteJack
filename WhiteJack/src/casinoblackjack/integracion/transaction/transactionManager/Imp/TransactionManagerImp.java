/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package casinoblackjack.integracion.transaction.transactionManager.Imp;

import integracion.transaction.Transaction;
import casinoblackjack.integracion.transaction.transactionManager.TransactionManager;
import casinoblackjack.integracion.transaction.transactionFactory.TransactionFactory;
import java.util.HashMap;

/**
 *
 * @author Ruben
 */
public class TransactionManagerImp extends TransactionManager 
{
    private HashMap<Thread, Transaction> _mapa_transacciones = new HashMap<Thread, Transaction>();

    @Override
    public void nuevaTransaccion() 
    {
        if (!_mapa_transacciones.containsKey(Thread.currentThread()))
            _mapa_transacciones.put(Thread.currentThread(), TransactionFactory.getInstance().generaTransaccion());
    }

    @Override
    public Transaction getTransaccion() 
    {
        return _mapa_transacciones.get(Thread.currentThread());
    }

    @Override
    public void eliminaTransaccion() 
    {
        _mapa_transacciones.remove(Thread.currentThread());
    }
    
}
