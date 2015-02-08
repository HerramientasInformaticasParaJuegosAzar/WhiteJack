/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package casinoblackjack.negocio.dealer;

import java.util.ArrayList;

import casinoblackjack.negocio.cartas.Carta;
import casinoblackjack.negocio.jugador.Decision;
import casinoblackjack.negocio.jugador.SA.imp.SAJugadorImp;
import java.util.Date;



public class Dealer extends SAJugadorImp{

    public Dealer(String usuario, String password, Date fechaRegistro) 
    {
        super(usuario, password, fechaRegistro);
    }


	
	
    private int calcularValor() {
        ArrayList<Carta> cartas = this.getCartas();
        int valor = 0;
        int numAses = 0;
        for (Carta carta : cartas) {
            if(carta.getValor() == 11)
            	numAses++;
            valor += carta.getValor(); 
        }
        for(int i = 1; i<=numAses;i++){
        	if (valor>21) valor-=10;
        }
        return valor;
    }
    

	
	public Decision makeDecision(Carta cartaDealer, ArrayList<Carta> cartasEnMesa, int nSplit){
		int valorMano = calcularValor();
		if(valorMano<17) return Decision.HIT;
		else return Decision.STAND;
	}
	
	public boolean decidirBarajar(ArrayList<Carta> cartasQuemadas,int numBarajas){
		if(cartasQuemadas.size()>numBarajas*52*0.4) return true;
		int numDieces=0;
		for(Carta carta : cartasQuemadas){
			if (carta.getValor()>10)
				numDieces++;
		}
		if (numDieces>6){
			return true;
		}
		else return false;
	}
   
}
