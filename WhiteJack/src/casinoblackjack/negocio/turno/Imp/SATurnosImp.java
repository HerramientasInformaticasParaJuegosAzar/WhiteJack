/*
 * Copyright (C) 2015 Krnx
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package casinoblackjack.negocio.turno.Imp;


import casinoblackjack.negocio.turno.SATurno;
import casinoblackjack.negocio.turno.Turnos;
import casinoblackjack.negocio.turno.TurnosPK;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Krnx
 */
public class SATurnosImp implements SATurno
{

    @Override
    public int altaTurno(int idJugador, double resultado) 
    {
        
        EntityManager em = null;
        EntityManagerFactory ef = null;
        TurnosPK tpk;
        Turnos turno;
        
        try 
        {
            tpk = new TurnosPK(idJugador);
            turno = new Turnos(tpk);
            
            ef = Persistence.createEntityManagerFactory("WhiteJackPU");
            em = ef.createEntityManager();
            
            em.getTransaction().begin();
            turno.setResultado(resultado);
            
            em.persist(turno);
            em.getTransaction().commit();
            
            
           
        } 
        finally 
        {
            if (em != null)             
                em.close();           
            if (ef != null)
                ef.close();
        }
        
        return turno.getTurnosPK().getIdturnos();
    }
    
    @Override
    public int obtenerUltimoTurno() 
    {
        
        EntityManager em = null;
        EntityManagerFactory ef = null;
        
        int turno = 0;
        
        try 
        {
            ef = Persistence.createEntityManagerFactory("WhiteJackPU");
            em = ef.createEntityManager();
            
            em.getTransaction().begin();
            
           
            
            List results = em.createNamedQuery("Turnos.obtenerUltimoTurno")
            .getResultList();
            
            if (results.size() > 0)
                turno = (Integer)results.get(0);
            
            
        }
        catch(Exception e)
        {
            System.out.println("muy tarde para excepciones");
        }
        return turno;
    }
    
    public static void main(String args[])
    {
        SATurnosImp sa = new SATurnosImp();
        sa.altaTurno(1, 500);
    }
    
}
