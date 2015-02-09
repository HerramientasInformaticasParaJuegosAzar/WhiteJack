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
import casinoblackjack.negocio.jugador.Decision;
import casinoblackjack.negocio.jugador.estrategias.Estrategia;
import casinoblackjack.negocio.jugador.SA.SAJugador;
import java.util.ArrayList;
import java.util.Date;



/**
 *
 * @author usuario_local
 */

public class SAJugadorImp extends SAJugador 
{
    ArrayList<ArrayList<Carta>> cartas = new ArrayList<>();
    Estrategia estrategia;
    
    public SAJugadorImp(String usuario, String password, Date fechaRegistro, Estrategia est) 
    {
        super(usuario,password,fechaRegistro);
        this.estrategia = est;
    }
    
    public SAJugadorImp(String usuario, String password, Date fechaRegistro) 
    {
        super(usuario,password,fechaRegistro);
        this.estrategia = new Estrategia();
    }
    

    public void setEstrategia(Estrategia estrategia)
    {
        this.estrategia = estrategia;
    }
     
    

    public ArrayList<Carta> getCartas(int nSplit) 
    {
        return this.cartas.get(nSplit);
    }
    

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
        Carta carta = this.cartas.get(nSplit).get(1);
        this.cartas.get(nSplit).remove(1);
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
    
    /*
    public static void main(String [] args)
    {
        // Probemos si da de alta un jugador.
        SAJugador jugador = new SAJugadorImp("hola","hola3",null);
        System.out.println(jugador.getIdjugadores());
    
    }
    */
    /*
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
    */
}
