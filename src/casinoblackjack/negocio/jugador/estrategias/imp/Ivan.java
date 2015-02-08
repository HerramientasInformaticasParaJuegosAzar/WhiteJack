/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package casinoblackjack.negocio.jugador.estrategias.imp;

import casinoblackjack.negocio.cartas.Carta;
import casinoblackjack.negocio.jugador.Decision;
import casinoblackjack.negocio.jugador.estrategias.Estrategia;
import java.util.ArrayList;

/**
 *
 * @author Ivan
 */
public class Ivan extends Estrategia{
    
    
     int numDieces;
     
    public Ivan (){
        this.numDieces = 0;
    }
   
    
    public int apostar(int apuestaMin, int apuestaMax)
    {
     
        return apuestaMin + apuestaMin*(this.numDieces/2);
    } 
    
    public void contarCartas(ArrayList<Carta> cartas)
    {
        for(Carta card : cartas){
            if(card.getValor() >= 10)
                this.numDieces++;
        }
    }
    
    public Decision makeDecision(Carta cartaDealer, ArrayList<Carta> cartasEnMesa, ArrayList<Carta> mano)
    {
        contarCartas(cartasEnMesa);
        int dealer = cartaDealer.getValor();
        int suma = 0;
        boolean debil = false;
        boolean debil2;
        boolean dd;
        boolean split;
        
        for(Carta carta : mano)
        {
            suma = suma + carta.getValor();
            debil2 = debil && carta.getValor() == 11;
            debil = debil || carta.getValor() == 11;
            
            if(suma > 21 && debil)
            {
                suma = suma - 10;
                debil = debil2;
            }
        }
        
        dd = mano.size() == 2 && suma >= 9 && suma <= 11;
        split = mano.size() == 2 && mano.get(0).getValor() == mano.get(1).getValor();

        
        if (suma >= 19) 
        {
            return Decision.STAND;
        }
        else if (suma == 18)
        {
            if(dealer >= 2 && dealer <= 9 && dealer != 7 && split)
            {
                return Decision.SPLIT;
            }
            else if(dealer >= 9 && dealer <= 11 && debil)
            {
                return Decision.HIT;
            }
            else if(dealer >= 3 && dealer <= 6 && dd)
            {
                return Decision.DOUBLE;
            }
            else return Decision.STAND;
        }
        else if(suma == 17)
        {
            if(dealer >= 3 && dealer <= 6 && dd)
            {
                return Decision.DOUBLE;
            }
            else if (debil)
            {
                return Decision.HIT;
            }
            else return Decision.STAND;
        }
        else if(suma == 16)
        {
             return Decision.STAND;
        }
        else if (suma == 15)
        {
            if(dealer >= 4 && dealer <= 6 && dd)
            {
                return Decision.DOUBLE;
            }
            else if(dealer >= 7 || debil)
            {
                return Decision.HIT;
            }
            else return Decision.STAND;
        }
        else if (suma == 14)
        {
            if((dealer == 5 || dealer == 6) && dd)
            {
                return Decision.DOUBLE;
            }
            else if (dealer >= 2 && dealer <= 7 && split)
            {
                return Decision.SPLIT;
            }
            else if (debil || dealer > 7)
            {
                return Decision.HIT;
            }
            else return Decision.STAND;
        }
        else if(suma == 13)
        {
            if((dealer == 5 || dealer == 6) && dd)
            {
                return Decision.DOUBLE;
            }
            else if ((!debil && dealer > 7) || debil)
            {
                return Decision.HIT;
            }
            else return Decision.STAND;
        }
        else if(suma == 12)
        {
            if(dealer >= 3 && dealer <= 6 && split)
            {
                return Decision.SPLIT;
            }
            else if(dealer != 11 && split && debil)
            {
                return Decision.SPLIT;
            }
            else if(dealer >= 4 && dealer <= 6 && !debil)
            {
                return Decision.STAND;
            }
            else return Decision.HIT;
        }
        else if (suma == 11)
        {
            if(dd && dealer <= 9)
            {
                return Decision.DOUBLE;
            }
            else return Decision.HIT;
        }
        else if (suma == 10)
        {
            if(dd && dealer <= 9)
            {
                return Decision.DOUBLE;
            }
            else return Decision.HIT;
        }
        else if (suma == 9)
        {
            if(dd && dealer >= 3 && dealer <= 6)
            {
                return Decision.DOUBLE;
            }
            else return Decision.HIT;
        }
        else return Decision.HIT;
    }
    
    
}
