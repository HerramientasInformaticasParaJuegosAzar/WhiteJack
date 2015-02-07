/*
 * Copyright (C) 2015 Vik
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
package casinoblackjack.negocio.jugador.estrategias.imp;

import java.util.ArrayList;

import casinoblackjack.negocio.cartas.Carta;
import casinoblackjack.negocio.jugador.Decision;
import casinoblackjack.negocio.jugador.estrategias.Estrategia;

/**
 *
 * @author Vik
 */
public class Viktor extends Estrategia{
    
    public Viktor(){
        super();
    }
    
    public Decision makeDecision(Carta cartaDealer, ArrayList<Carta> cartasEnMesa, int nSplit){
    	return Decision.HIT;
    }
}
