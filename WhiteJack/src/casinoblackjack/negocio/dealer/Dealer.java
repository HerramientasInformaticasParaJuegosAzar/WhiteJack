/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package casinoblackjack.negocio.dealer;

import casinoblackjack.negocio.cartas.Carta;
import casinoblackjack.negocio.cartas.Decision;
import casinoblackjack.negocio.jugador.Jugador;
import casinoblackjack.negocio.mesa.Mesa;
import java.util.ArrayList;

/**
 *
 * @author Krnx
 */
public class Dealer implements Jugador{
    
    int dieces;//controla la aparicion de dieces para barajar
    
    public Decision makeDecision(Mesa mesa) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addCarta(Carta carta) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Carta> getCartas() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
