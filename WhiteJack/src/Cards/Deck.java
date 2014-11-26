/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cards;

import java.util.ArrayList;
import Cards.enums.Palos;
import Cards.enums.Numeros;
import java.util.Random;

/**
 *
 * @author Ivan
 */
public class Deck {
    
    static final int numCartas=52;
	private ArrayList<Carta> pilaCartas;

	public Deck(){
		pilaCartas = new ArrayList<Carta>();
		for (int j= 1; j<5;j++){
			for (int i=1; i<14;i++){
				pilaCartas.add(new Carta(i,j));
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public void baraja(){
		ArrayList<Carta> pilaCopia = (ArrayList<Carta>) pilaCartas.clone();
		ArrayList<Carta> pilaAux = new ArrayList<Carta>();
		Random random = new Random();
		while (!pilaCopia.isEmpty()){
			int i = random.nextInt(pilaCopia.size());
			pilaAux.add(pilaCopia.get(i));
			pilaCopia.remove(i);
		}
		pilaCartas=pilaAux;	
		
	}
	
	public void ensena(){
		System.out.println("Mostrando");
		for(int i = 0;i<pilaCartas.size();i++){
			System.out.println(this.pilaCartas.get(i).toString());
		}
	}
	public Carta darCarta(){
		Carta aux = this.pilaCartas.get(0);
		this.pilaCartas.remove(0);
		return aux;
	}
	public void addCarta(Carta carta){
		this.pilaCartas.add(carta);
	}

	public boolean esvacia(){
		int cartas =this.pilaCartas.size();
		if (cartas == 0)
			return true;
		else 
			return false;
	}
	
    
}
