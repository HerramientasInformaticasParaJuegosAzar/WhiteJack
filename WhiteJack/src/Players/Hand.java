/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Players;

import Cards.Carta;
import java.util.ArrayList;

/**
 *
 * @author Ivan
 */
public class Hand {
    private ArrayList<Carta> mano;
	
	public Hand(){
		this.mano = new ArrayList<Carta>();
	}	
        
        public void addCarta(Carta carta){
		this.mano.add(carta);
	}
        
        public void cambiarPosCarta(int pos1, int pos2){
		Carta aux = this.mano.get(pos1);
		this.mano.set(pos1, this.mano.get(pos2));
		this.mano.set(pos2, aux);
		
 	}
        
        public void ordenar(){
		for(int i = 0; i< this.mano.size();i++){
			for(int j = i+1; j<this.mano.size();j++){
				if(this.mano.get(i).getNumero().ordinal() < this.mano.get(j).getNumero().ordinal())
					cambiarPosCarta(i,j);
			}
		}
	}
        
        public void mostrarCartas()
	{
		this.ordenar();
		for (int i = 0; i < mano.size(); i++){
			System.out.println(i+1 +".- "+this.mano.get(i));
		}
		
	}
}
