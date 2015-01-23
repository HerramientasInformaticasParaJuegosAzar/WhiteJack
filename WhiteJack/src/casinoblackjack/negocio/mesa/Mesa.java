/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package casinoblackjack.negocio.mesa;

import casinoblackjack.negocio.cartas.Carta;
import casinoblackjack.negocio.cartas.barajeador.Barajeador;
import casinoblackjack.negocio.dealer.Dealer;
import casinoblackjack.negocio.jugador.Jugador;
import casinoblackjack.negocio.turno.Turno;
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
    private Barajeador baraja;
    private Turno turno;
    
    
    public Mesa(int barajas){
        this.dealer = new Dealer();
        this.baraja = new Barajeador(barajas);
    }
    
    public void addPlayer(Jugador jugador){
        this.jugadores.add(jugador);
    }
    
    
    
    public ArrayList<Carta> getCartasEnMesa(){
        ArrayList<Carta> cartas = new ArrayList<Carta>();
        for(Jugador jugador:jugadores){
            cartas.addAll(jugador.getCartas());
        }
        cartas.addAll(this.dealer.getCartas());
        return cartas;
    }
    
    
    
    
}
