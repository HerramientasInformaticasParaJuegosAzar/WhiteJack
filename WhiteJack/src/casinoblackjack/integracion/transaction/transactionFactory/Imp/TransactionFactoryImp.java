/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package casinoblackjack.integracion.transaction.transactionFactory.Imp;

import casinoblackjack.integracion.transaction.Imp.TransactionMysql;
import integracion.transaction.Transaction;
import casinoblackjack.integracion.transaction.transactionFactory.TransactionFactory;

/**
 *
 * @author Ruben
 */
public class TransactionFactoryImp extends TransactionFactory
{

    @Override
    public Transaction generaTransaccion() 
    {
        return new TransactionMysql();
    }
    
}
