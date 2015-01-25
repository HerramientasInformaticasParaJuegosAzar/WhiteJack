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
import casinoblackjack.negocio.cartas.Decision;
import casinoblackjack.negocio.jugador.Jugador;
import java.util.ArrayList;

/**
 *
 * @author usuario_local
 */
public interface SAJugador 
{
    public void altaJugador(Jugador jugador);
    public void modificarJugador(Jugador jugador);
    public void bajaJugador(Integer id);
    public Jugador mostrarJugador(Integer id);
    
    public abstract void addCarta(Carta carta);
    public abstract ArrayList<Carta> getCartas();
    public abstract int getIDJugador();

    public abstract int apostar();

    public abstract Decision makeDecision(Carta cartaDealer, ArrayList<Carta> cartasEnMesa);

    public abstract void addCarta(Carta carta, int esSplit);
}
