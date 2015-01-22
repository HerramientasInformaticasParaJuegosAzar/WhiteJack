/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package casinoblackjack.negocio.jugador;

import casinoblackjack.negocio.cartas.Carta;

/**
 *
 * @author Krnx
 */
public abstract class Jugador 
{
    private String user;
    private String pass;
    private int cantidadApuesta;
    
    
    public abstract Decision juegoTurno(Carta[] cartasMesa, Decision posiblesDecisiones);
    // Decision juegaTurno(Cartas....)
    
    // atributos: user, pass, cantidadApuesta
    //jugador.getCAntida
}
