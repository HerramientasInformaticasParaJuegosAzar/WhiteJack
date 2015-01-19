/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package casinoblackjack.integracion.DAO.factoriaDAO.Imp;

import casinoblackjack.integracion.DAO.turno.DAOTurno;
import casinoblackjack.integracion.DAO.turno.Imp.DAOTurnoImp;
import casinoblackjack.integracion.DAO.factoriaDAO.FactoriaDAO;

/**
 *
 * @author Ruben
 */
public class FactoriaDAOImp extends FactoriaDAO
{
    
    @Override
    public DAOTurno getDAOTurno() {
        return new DAOTurnoImp();
    }
     
}