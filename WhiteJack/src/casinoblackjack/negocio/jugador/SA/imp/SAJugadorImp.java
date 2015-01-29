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

import casinoblackjack.negocio.cartas.Carta;
import casinoblackjack.negocio.cartas.Decision;
import casinoblackjack.negocio.jugador.Jugador;
import casinoblackjack.negocio.jugador.SA.SAJugador;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.LockModeType;
import javax.persistence.Persistence;


/**
 *
 * @author usuario_local
 */
public class SAJugadorImp implements SAJugador 
{
    ArrayList<Carta> cartas;
    int suma;
    boolean dd;
    boolean split;
    
    
    @Override
    public int altaJugador(Jugador jugador) 
    {
        jugador.setIdjugadores(-1);
        
        EntityManager em = null;
        EntityManagerFactory ef = null;
        try 
        {
            ef = Persistence.createEntityManagerFactory("WhiteJackPU");
            em = ef.createEntityManager();
            
            em.getTransaction().begin();
            
            Jugador jugadorAux = null;
            
            List results = em.createNamedQuery("Jugador.findByIdjugadores")
            .setParameter("idjugadores", jugador.getIdjugadores())
            .getResultList();
            
            if (results.size() > 0)
                jugadorAux = (Jugador)results.get(0);
            
            if (jugadorAux != null)
            {
                if (!jugadorAux.getActivo())
                {
                    jugadorAux.setActivo(true);
                    em.merge(jugadorAux);
                    jugador.setIdjugadores(jugadorAux.getIdjugadores());
                    em.getTransaction().commit();
                }
                else
                {
                    System.out.println("Ya existe el jugador");
                    em.getTransaction().rollback();
                }
            }            
            else
            {
                em.persist(jugador);
                em.getTransaction().commit();
            }
            
           
        } 
        finally 
        {
            if (em != null)             
                em.close();           
            if (ef != null)
                ef.close();
        }
        
        return jugador.getIdjugadores();
    }

    @Override
    public boolean modificarJugador(Jugador jugador)
    {
        
        boolean correcto = true;
        EntityManager em = null;
        EntityManagerFactory ef =null;
        try {
           
            ef= Persistence.createEntityManagerFactory("WhiteJackPU");
            em = ef.createEntityManager();
        
            em.getTransaction().begin();
            Jugador d= em.find(Jugador.class, jugador.getIdjugadores(),LockModeType.OPTIMISTIC);
            
            if (d!=null)
            {
                em.lock(d, LockModeType.OPTIMISTIC_FORCE_INCREMENT);
                
                em.merge(jugador);
                em.getTransaction().commit();
                
            }
          
        } 
        catch (Exception ex) 
        {
            correcto = false;
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) 
            {
                Integer id = jugador.getIdjugadores();
                if (mostrarJugador(id) == null) 
                {
                    System.out.println("El jugador con id " + id + " no existe.");
                }
            }
           
        } 
        finally 
        {
            if (em != null) 
            {
                em.close();
            }
            
            if (ef!=null) ef.close();
        }
        return correcto;
    }

    @Override
    public boolean bajaJugador(Integer id)
    {
        
       EntityManager em = null;
        boolean correcto = true;
        try {
            EntityManagerFactory ef = Persistence.createEntityManagerFactory("WhiteJackPU");
            em = ef.createEntityManager();
            em.getTransaction().begin();
            Jugador persistentJugador = em.find(Jugador.class, id);
            
            persistentJugador.setActivo(false); 
            em.merge(persistentJugador);
            em.getTransaction().commit();
        } 
        catch (Exception ex) 
        {
            correcto = false;
            ex.printStackTrace();
            
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return correcto;
    }

    @Override
    public Jugador mostrarJugador(Integer id) 
    {
        EntityManager em = null;
        EntityManagerFactory ef = null;
        
        ef = Persistence.createEntityManagerFactory("WhiteJackPU");
        em = ef.createEntityManager();
        
        Jugador jugador = null;
        try 
        {
            jugador = em.find(Jugador.class, id);
        }
        catch(Exception e)
        {
            e.printStackTrace();
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
        if(this.cartas.isEmpty())
        {
            this.suma = carta.getValor();          
            this.dd = this.suma == 11;
        }
        else
        {
            this.suma = this.suma + carta.getValor();
            this.dd = (this.dd || carta.getValor() == 11) && this.suma <= 21;
        }
       
        this.cartas.add(carta);
        this.split = this.cartas.size() == 2 && this.cartas.get(0).getValor() == this.cartas.get(1).getValor();
    }

    @Override
    public ArrayList<Carta> getCartas() 
    {
        return this.cartas;
    }

    @Override
    public int getIDJugador() 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int apostar(int apuestaMin, int apuestaMax) 
    {
        return apuestaMin;
    }

    @Override
    public Decision makeDecision(Carta cartaDealer, ArrayList<Carta> cartasEnMesa) 
    {
        int dealer = cartaDealer.getValor();
        
        if (this.suma > 18) 
        {
            return Decision.PASS;
        }
        else if (this.suma == 18)
        {
            if(dealer >= 2 && dealer <= 9 && dealer != 7 && this.split)
            {
                return Decision.SPLIT;
            }
            else if(dealer >= 9 && dealer <= 11 && this.dd)
            {
                return Decision.HIT;
            }
            else if(dealer >= 3 && dealer <= 6 && this.dd)
            {
                return Decision.DOUBLE;
            }
            else return Decision.PASS;
        }
        else if(this.suma == 17)
        {
            if(dealer >= 3 && dealer <= 6 && this.dd)
            {
                return Decision.DOUBLE;
            }
            else if (this.dd)
            {
                return Decision.HIT;
            }
            else return Decision.PASS;
        }
        else if(this.suma == 16)
        {
            if(dealer >= 2 && dealer <= 9 && this.split)
            {
                return Decision.SPLIT;
            }
            else if(dealer >= 4 && dealer <= 6 && this.dd)
            {
                return Decision.DOUBLE;
            }
            else if(dealer >= 7 && !this.dd || this.dd)
            {
                return Decision.HIT;
            }
            else return Decision.PASS;
        }
        else if (this.suma == 15)
        {
            if(dealer >= 4 && dealer <= 6 && this.dd)
            {
                return Decision.DOUBLE;
            }
            else if(dealer >= 7 || this.dd)
            {
                return Decision.HIT;
            }
            else return Decision.PASS;
        }
        else if (this.suma == 14)
        {
            if((dealer == 5 || dealer == 6) && this.dd)
            {
                return Decision.DOUBLE;
            }
            else if (dealer >= 2 && dealer <= 7 && this.split)
            {
                return Decision.SPLIT;
            }
            else if (this.dd || dealer > 7)
            {
                return Decision.HIT;
            }
            else return Decision.PASS;
        }
        else if(this.suma == 13)
        {
            if((dealer == 5 || dealer == 6) && this.dd)
            {
                return Decision.DOUBLE;
            }
            else if ((!this.dd && dealer > 7) || this.dd)
            {
                return Decision.HIT;
            }
            else return Decision.PASS;
        }
        else if(this.suma == 12)
        {
            if(dealer >= 3 && dealer <= 6 && this.split)
            {
                return Decision.SPLIT;
            }
            else if(dealer != 11 && this.dd)
            {
                return Decision.SPLIT;
            }
            else if(dealer >= 4 && dealer <= 6 && !this.dd)
            {
                return Decision.PASS;
            }
            else return Decision.HIT;
        }
        else if (this.suma == 11)
        {
            if(this.dd && dealer <= 9)
            {
                return Decision.DOUBLE;
            }
            else return Decision.HIT;
        }
        else if (this.suma == 10)
        {
            if(this.dd && dealer <= 9)
            {
                return Decision.DOUBLE;
            }
            else return Decision.HIT;
        }
        else if (this.suma == 9)
        {
            if(this.dd && dealer >= 3 && dealer <= 6)
            {
                return Decision.DOUBLE;
            }
            else return Decision.HIT;
        }
        else return Decision.HIT;   
    }

    @Override
    public void addCarta(Carta carta, int esSplit) 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void split() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void quemarCartas() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    public static void main(String [ ] args){
        
        Date date = new Date();
        Jugador jugador = new Jugador(1, "Pepe", "123", date, true);
        SAJugadorImp sa = new SAJugadorImp();
        int i = sa.altaJugador(jugador);
    }
    
}