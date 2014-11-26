/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cards;

import Cards.enums.Numeros;
import Cards.enums.Palos;
import java.util.Objects;

/**
 *
 * @author vjacynycz
 */
public class Carta{
    
    private Numeros numero;
    
    private Palos palo;
    
    
    /*
    Crea una carta con un:
        numero 1->as .. 13-rey
        palo -> 1: diamantes, 2: corazones, 3: picas, 4: treboles
    */
    public Carta(int num,int palo){
        this.numero=Numeros.values()[num-1];
        this.palo=Palos.values()[palo-1];
    }
    
    public Carta(Numeros num, Palos palo){
        this.numero=num;
        this.palo=palo;
    }
    
    public Carta(String s){
        if(s.length()!=2) return;
        this.numero = parseNumero(s);
        this.palo = parsePalo(s);
    }
    
    @Override
    public String toString(){
        return this.numero+" de "+this.palo;
    }
    
    
    /*
    Devuelve >1 si this es menor que c
             =0 si this es igual que c
             <1 si this es mayor que c
    */
    public int esMejorCarta(Carta c){
        return this.numero.compareTo(c.numero);
    }

    private Numeros parseNumero(String s) {
        if(s.startsWith("A")){
            return Numeros.as;
        }else if(s.startsWith("J")){
            return Numeros.jack;
        }else if(s.startsWith("Q")){
            return Numeros.reina;
        }else if(s.startsWith("K")){
            return Numeros.rey;
        }else if(s.startsWith("2")){
            return Numeros.dos;
        }else if(s.startsWith("3")){
            return Numeros.tres;
        }else if(s.startsWith("4")){
            return Numeros.cuatro;
        }else if(s.startsWith("5")){
            return Numeros.cinco;
        }else if(s.startsWith("6")){
            return Numeros.seis;
        }else if(s.startsWith("7")){
            return Numeros.siete;
        }else if(s.startsWith("8")){
            return Numeros.ocho;
        }else if(s.startsWith("9")){
            return Numeros.nueve;
        }else if(s.startsWith("T")){
            return Numeros.diez;
        }
        return null;
    }

    private Palos parsePalo(String s) {
       if(s.endsWith("d")){
            return Palos.diamantes;
        }else if(s.endsWith("h")){
            return Palos.corazones;
        }else if(s.endsWith("c")){
            return Palos.treboles;
        }else if(s.endsWith("s")){
            return Palos.picas;
        } return null;
    }
    
    public Numeros getNumero(){
        return this.numero;
    }
    
    public Palos getPalo(){
        return this.palo;
    }
  
    @Override
    public boolean equals(Object o){
        
        if(this == o)
            return true;
        if(o == null)
            return false;
        if(!(o instanceof Carta))
            return false;
        final Carta other = (Carta) o;
        return this.numero.equals(other.numero) && this.palo.equals(other.palo);
    }
}
