/*
 * Copyright (C) 2015 usuario
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
package casinoblackjack.negocio.jugador.estrategias;

import casinoblackjack.negocio.cartas.Carta;
import casinoblackjack.negocio.cartas.Decision;
import java.util.ArrayList;

/**
 *
 * @author usuario
 */
public class Estrategia 
{
    
    public int apostar(int apuestaMin, int apuestaMax)
    {
        return apuestaMin;
    } 
    
    public void contarCartas(ArrayList<Carta> cartas)
    {}
    
    public Decision makeDecision(Carta cartaDealer, ArrayList<Carta> cartasEnMesa, ArrayList<Carta> mano)
    {
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
            return Decision.PASS;
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
            else return Decision.PASS;
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
            else return Decision.PASS;
        }
        else if(suma == 16)
        {
            if(dealer >= 2 && dealer <= 9 && split)
            {
                return Decision.SPLIT;
            }
            else if(dealer >= 4 && dealer <= 6 && dd)
            {
                return Decision.DOUBLE;
            }
            else if(dealer >= 7 && !debil || debil)
            {
                return Decision.HIT;
            }
            else return Decision.PASS;
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
            else return Decision.PASS;
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
            else return Decision.PASS;
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
            else return Decision.PASS;
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
                return Decision.PASS;
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
