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

import casinoblackjack.negocio.EntityFactory.EntityFactorySingleton;
import casinoblackjack.negocio.cartas.Carta;
import casinoblackjack.negocio.cartas.Decision;
import casinoblackjack.negocio.cartas.barajeador.Barajeador;
import casinoblackjack.negocio.cartas.enumerados.Palo;
import casinoblackjack.negocio.cartas.enumerados.Valor;
import casinoblackjack.negocio.jugador.estrategias.Estrategia;
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
    ArrayList<ArrayList<Carta>> cartas;
    Estrategia estrategia;
    
    
    public SAJugadorImp()
    {
        this.cartas = new ArrayList<ArrayList<Carta>>();
        this.estrategia = new Estrategia();
    }
    
    public SAJugadorImp(Estrategia est)
    {
        this.cartas = new ArrayList<ArrayList<Carta>>();
        this.estrategia = est;
    }
    
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
            
            List results = em.createNamedQuery("Jugador.findByIdUsuario")
            .setParameter("usuario", jugador.getUsuario())
            .getResultList();
            
            if (results.size() > 0)
                jugadorAux = (Jugador)results.get(0);
            
            if (jugadorAux != null)
            {
                if (!jugadorAux.getActivo() && jugadorAux.getPassword().equalsIgnoreCase(jugador.getPassword()))
                {
                    jugadorAux.setActivo(true);
                    em.merge(jugadorAux);
                    jugador.setIdjugadores(jugadorAux.getIdjugadores());
                    em.getTransaction().commit();
                }
                else
                {
                    if (jugadorAux.getPassword().equalsIgnoreCase(jugador.getPassword()))
                    jugador.setIdjugadores(jugadorAux.getIdjugadores());
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
    public void setEstrategia(Estrategia estrategia)
    {
        this.estrategia = estrategia;
    }
     
    
    @Override
    public ArrayList<Carta> getCartas(int nSplit) 
    {
        return this.cartas.get(nSplit);
    }
    
    @Override
    public ArrayList<Carta> getCartas()
    {
        ArrayList<Carta> lista = new ArrayList<Carta>();
        
        for(int i = 0; i < this.cartas.size(); i++)
        {
            lista.addAll(this.cartas.get(i));
        }
        
        return lista;
    }

    @Override
    public int getIDJugador() 
    {
        return 1;
    }

    @Override
    public int apostar(int apuestaMin, int apuestaMax) 
    {
        return this.estrategia.apostar(apuestaMin, apuestaMax);
    }

    @Override
    public Decision makeDecision(Carta cartaDealer, ArrayList<Carta> cartasEnMesa, int nSplit)
    {
        return this.estrategia.makeDecision(cartaDealer, cartasEnMesa, this.cartas.get(nSplit)); 
    }


    @Override
    public void addCarta(Carta carta, int nSplit) 
    {
       if(this.cartas.size() < nSplit + 1)
       {
           this.cartas.add(new ArrayList<Carta>());
       }
       
       this.cartas.get(nSplit).add(carta);
    }

    @Override
    public void split(int nSplit) 
    {
        Carta carta = this.cartas.get(nSplit).remove(1);
        addCarta(carta, this.cartas.size());
    }

    @Override
    public void quemarCartas() 
    {
        this.cartas = new ArrayList<ArrayList<Carta>>(); 
    }

    
    @Override
    public int numSplits()
    {
        return this.cartas.size();
    }
    
    @Override
    public void verCartasEnMesa(ArrayList<Carta> cartasEnMesa)
    {
        this.estrategia.contarCartas(cartasEnMesa);
    }
    
    
    public static void main(String [] args)
    {
        SAJugadorImp sa = new SAJugadorImp();
        Barajeador baraja = new Barajeador(4);
        
        baraja.barajear();
        
        Carta carta1 = new Carta(Valor.UNO, Palo.CLUBS);
        sa.addCarta(carta1, 0);
        Carta carta2 = new Carta(Valor.UNO, Palo.CLUBS);
        sa.addCarta(carta2, 0);
        Carta carta3 = new Carta(Valor.DOS, Palo.CLUBS);
        
        
        
        Decision d = sa.makeDecision(carta3, null, 0);
        System.out.println(carta1.toString());
        System.out.println(carta2.toString());
        System.out.println(carta3.toString());
        System.out.println(d);
        
        sa.split(0);
        Carta carta4 = new Carta(Valor.DIEZ, Palo.CLUBS);
        sa.addCarta(carta4, 0);
        Carta carta5 = new Carta(Valor.CUATRO, Palo.CLUBS);
        sa.addCarta(carta5, 1);
        System.out.println(carta1.toString() + " " + carta4.toString());
        System.out.println(carta2.toString() + " " + carta5.toString());
        d = sa.makeDecision(carta3, null, 0);
        System.out.println(d);
        d = sa.makeDecision(carta3, null, 1);
        System.out.println(d);
        
        ArrayList<Carta> lista = sa.getCartas();
        String str = "";
        for(Carta cart : lista)
        {
            str = str + " " + cart.toString();
        }
        System.out.println(str);
        
        for(int i = 0; i < sa.numSplits(); i++)
        {
            str = "";
            ArrayList<Carta> list = sa.getCartas(i);
            for(int j = 0; j < list.size(); j++)
            {
                str = str + " " + list.get(j);
            }
            System.out.println(str);
        }
    }
}
