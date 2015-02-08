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
package casinoblackjack.negocio.juego;

import casinoblackjack.negocio.jugador.estrategias.imp.Viktor;
import casinoblackjack.negocio.jugador.estrategias.Estrategia;
import casinoblackjack.negocio.jugador.estrategias.imp.Ivan;
import casinoblackjack.negocio.mesa.Mesa;
import casinoblackjack.negocio.mesa.ui.MainWindow;
import java.awt.EventQueue;
import java.util.ArrayList;

/**
 *
 * @author Vik
 */
public class Juego{

	private Mesa mesa;

	public Juego(){
		this.mesa = new Mesa(2);
		MainWindow mw = new MainWindow(crearPosiblesEstrategias(), mesa);
		mw.start();
		mesa.setObservador(mw);
	}

	public Mesa getMesa() {
		return mesa;
	}

	public ArrayList<Estrategia> crearPosiblesEstrategias(){
		ArrayList<Estrategia> e = new ArrayList<>();
		e.add(new Viktor());
                e.add(new Ivan());
		return e;
	}

}
