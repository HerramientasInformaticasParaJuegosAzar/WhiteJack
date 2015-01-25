/*
 * Copyright (C) 2015 usuario_local
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

package casinoblackjack.negocio.jugador.SA.imp;

import casinoblackjack.exceptions.NonexistentEntityException;
import casinoblackjack.negocio.cartas.Carta;
import casinoblackjack.negocio.cartas.Decision;
import casinoblackjack.negocio.jugador.Jugador;
import casinoblackjack.negocio.jugador.SA.SAJugador;
import casinoblackjack.negocio.mesa.Mesa;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author usuario_local
 */
public class SAJugadorImp implements SAJugador {

    public SAJugadorImp(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public void altaJugador(Jugador jugador) 
    {
        EntityManagerFactory ef = Persistence.createEntityManagerFactory("blackJackPU");
        
        EntityManager em = ef.createEntityManager();
        
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(jugador);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
            
            if (ef != null)
            {
                ef.close();
            }
            
        }
    }

    @Override
    public void modificarJugador(Jugador jugador)
    {
        
        EntityManagerFactory ef = Persistence.createEntityManagerFactory("blackJackPU");
        EntityManager em = ef.createEntityManager();
        
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            jugador = em.merge(jugador);
            em.getTransaction().commit();
        } catch (Exception ex) 
        {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = jugador.getIdjugador();
                
                if (mostrarJugador(id) == null) 
                {
                    System.out.println("The jugador_1 with id " + id + " no longer exists.");
                }
            }
            
        } 
        finally 
        {
            if (em != null) {
                em.close();
            }
            
            if (ef != null)
            {
                ef.close();
            }
        }
    }

    @Override
    public void bajaJugador(Integer id)
    {
        
        EntityManagerFactory ef = Persistence.createEntityManagerFactory("blackJackPU");
        EntityManager em = ef.createEntityManager();
        
        try 
        {
            em = getEntityManager();
            em.getTransaction().begin();
            Jugador jugador = null;
            try {
                jugador = em.getReference(Jugador.class, id);
                jugador.getIdjugador();
            } catch (EntityNotFoundException enfe) 
            {
                System.out.println("The jugador_1 with id " + id + " no longer exists.");
            }
            jugador.setActivo(false);
            em.merge(jugador);
            em.getTransaction().commit();
        } 
        finally 
        {
            if (em != null) 
            {
                em.close();
            }
            
            if (ef != null)
            {
                ef.close();
            }
        }
    }

    @Override
    public Jugador mostrarJugador(Integer id) 
    {
        EntityManagerFactory ef = Persistence.createEntityManagerFactory("blackJackPU");
        EntityManager em = ef.createEntityManager();
        
        Jugador jugador = null;
        try 
        {
            jugador = em.find(Jugador.class, id);
        } 
        finally 
        {
            if (em != null) 
            {
                em.close();
            }
            
            if (ef != null)
            {
                ef.close();
            }
        }
        
        return jugador;
    }

    @Override
    public void addCarta(Carta carta) 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Carta> getCartas() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getIDJugador() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int apostar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Decision makeDecision(Carta cartaDealer, ArrayList<Carta> cartasEnMesa) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addCarta(Carta carta, int esSplit) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
