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

package casinoblackjack.negocio.jugador.SA;

import casinoblackjack.negocio.cartas.Carta;
import casinoblackjack.negocio.jugador.Decision;
import casinoblackjack.negocio.jugador.Jugador;
import casinoblackjack.negocio.jugador.estrategias.Estrategia;
import java.util.ArrayList;

/**
 *
 * @author usuario_local
 */
public interface SAJugador 
{
    public int altaJugador(Jugador jugador);
    public boolean modificarJugador(Jugador jugador);
    public boolean bajaJugador(Integer id);
    public Jugador mostrarJugador(Integer id);
    
    
    /*
    Configura una estrategia para el jugador
    */
    public abstract void setEstrategia(Estrategia estrategia);
    
    /*
    Devuelve las cartas de un jugador, de su montón número nSplit (0,1,2...)
    */
    public abstract ArrayList<Carta> getCartas(int nSplit);
    
    /*
    Devuelve todas las cartas de un jugador
    */
    public abstract ArrayList<Carta> getCartas();
    
    /*
    Devuelve la ID del jugador
    */
    public abstract int getIDJugador();

    /*
    Devuelve la apuesta del jugador
    */
    public abstract int apostar(int apuestaMin, int apuestaMax);
    
    /*
    Devuelve la decision del jugador
    */
    public abstract Decision makeDecision(Carta cartaDealer, ArrayList<Carta> cartasEnMesa, int nSplit);

    /*
    Añade una carta a uno de los montones del jugador (0,1,2...)
    */
    public abstract void addCarta(Carta carta, int nSplit);

    /*
    Splitea el monton nSplit(0,1,2...) de un jugador
    */
    public void split(int nSplit);

    /*
    Quema las cartas del jugador, eliminando las cartas que tiene
    */
    public void quemarCartas();
    
    /*
    Devuelve el número de montones que tiene un jugador. P ej, si ha hecho split 2 times, devuelve 3
    */
    public int numSplits();
    
    /*
    Permite al jugador observar todas las cartas que hay en la mesa después de que el dealer haga su jugada
    */
    public void verCartasEnMesa(ArrayList<Carta> cartasEnMesa);
}
