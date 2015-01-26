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
package casinoblackjack.negocio.mesa.ui;

import casinoblackjack.negocio.cartas.Carta;
import casinoblackjack.negocio.cartas.Decision;
import casinoblackjack.negocio.jugador.SA.imp.SAJugadorImp;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Vik
 */
public class JugadorUI extends SAJugadorImp {

    MainWindow ventana;

    public JugadorUI(EntityManagerFactory emf, MainWindow mw) {
        super(emf);
        this.ventana = ventana;
    }

    /*   apostarJugadorUI()
     *
     *   Le pregunta a la interfaz grafica por una apuesta.
     */
    public int apostar() {

        int apuesta = -1;
        while (apuesta < 0) {
            apuesta = ventana.getApuestaJugadorUI();
            try {
                Thread.currentThread().sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(JugadorUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return apuesta;

    }

    public Decision makeDecision(Carta cartaDealer, ArrayList<Carta> cartasEnMesa) {

    }

    public void addCarta(Carta carta, int esSplit) {

    }
}
