/*
 * Copyright (C) 2014 usuario_local
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

package model.set.card;

import model.set.card.enums.Suit;
import model.set.card.enums.Value;

/**
 *
 * @author usuario_local
 */
public class Card {
    
    private Value value;
    
    private Suit suit;
    
    
    /*
    Crea una carta con un:
        value 1->2 .. 12-K 13-A
        suit -> 1: diamantes, 2: corazones, 3: picas, 4: treboles
    */
    public Card(int value,int suit){
        this.value=Value.values()[value-1];
        this.suit=Suit.values()[suit-1];
    }
    
    public Card(Value value, Suit suit){
        this.value=value;
        this.suit=suit;
    }
    
    public Card(String s){
        if(s.length()!=2) return;
        this.value = parseNumero(s);
        this.suit = parsePalo(s);
    }
    
    @Override
    public String toString(){
        return this.value+" de "+this.suit;
    }
    
    
    /*
    Devuelve >1 si this es menor que c
             =0 si this es igual que c
             <1 si this es mayor que c
    */
    public int esMejorCard(Card c){
        return this.value.compareTo(c.value);
    }

    private Value parseNumero(String s) {
        if(s.startsWith("A")){
            return Value._A;
        }else if(s.startsWith("J")){
            return Value._J;
        }else if(s.startsWith("Q")){
            return Value._Q;
        }else if(s.startsWith("K")){
            return Value._K;
        }else if(s.startsWith("2")){
            return Value._2;
        }else if(s.startsWith("3")){
            return Value._3;
        }else if(s.startsWith("4")){
            return Value._4;
        }else if(s.startsWith("5")){
            return Value._5;
        }else if(s.startsWith("6")){
            return Value._6;
        }else if(s.startsWith("7")){
            return Value._7;
        }else if(s.startsWith("8")){
            return Value._8;
        }else if(s.startsWith("9")){
            return Value._9;
        }else if(s.startsWith("T")){
            return Value._T;
        }
        return null;
    }

    private Suit parsePalo(String s) {
       if(s.endsWith("d")){
            return Suit.d;
        }else if(s.endsWith("h")){
            return Suit.h;
        }else if(s.endsWith("c")){
            return Suit.c;
        }else if(s.endsWith("s")){
            return Suit.s;
        } return null;
    }
    
    public Value getNumero(){
        return this.value;
    }
    
    public Suit getPalo(){
        return this.suit;
    }
  
    @Override
    public boolean equals(Object o){
        
        if(this == o)
            return true;
        if(o == null)
            return false;
        if(!(o instanceof Card))
            return false;
        final Card other = (Card) o;
        return this.value.equals(other.value) && this.suit.equals(other.suit);
    }
    
}
