/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package casinoblackjack.negocio.mesa;

import casinoblackjack.negocio.cartas.barajeador.Barajeador;
import casinoblackjack.negocio.dealer.Dealer;
import casinoblackjack.negocio.jugador.Jugador;
import java.util.ArrayList;

/**
 *
 * @author Krnx
 */
public class Mesa 
{

    private int id;
    private Dealer dealer;
    private ArrayList<Jugador> jugadores;
    private Barajeador barajeador;
    
    
    // metodoPrincipal
    // while(dealerNotBurn)
    // recorre lista de jugadores
    // jugador.juegaTurno(....);
    // registra la pasta ganada o perdida
    // guarda informacion del turno
    // lo registra en la bbdd
    // 
}
