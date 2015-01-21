/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package casinoblackjack.negocio.jugador;

import casinoblackjack.negocio.cartas.Carta;
import casinoblackjack.negocio.cartas.Decision;
import casinoblackjack.negocio.mesa.Mesa;
import java.util.ArrayList;

/**
 *
 * @author Krnx
 */
public interface Jugador 
{
    public void addCarta(Carta carta);
    public ArrayList<Carta> getCartas();
    public Decision makeDecision(Mesa mesa);
    
}
