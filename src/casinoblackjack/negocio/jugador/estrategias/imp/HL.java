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
package casinoblackjack.negocio.jugador.estrategias.imp;

import casinoblackjack.negocio.cartas.Carta;
import casinoblackjack.negocio.jugador.estrategias.Estrategia;
import java.util.ArrayList;

/**
 *
 * @author usuario
 */
public class HL extends Estrategia{
    
    private int conteo = 0;
    private int numCartasTotal = 312;
    private int numCartas = 0;
    
    public HL()
    {
        super();
    }
    
    @Override
    public void contarCartas(ArrayList<Carta> cartas)
    {
        for(Carta carta : cartas)
        {
            int valor = carta.getValor();
            
            if(valor >= 2 && valor <= 6)
            {
                this.conteo = this.conteo + 1;
            }
            else if(valor == 10 || valor == 11)
            {
                this.conteo = this.conteo - 1;
            }
        }
        
        this.numCartas = this.numCartas + cartas.size();
    }
    
    @Override
    public int apostar(int apuestaMin, int apuestaMax)
    {
        double decks = (this.numCartasTotal - this.numCartas) / 52.0;
        double truecount = this.conteo / decks;
        truecount = truecount - 1.5;
        double producto = apuestaMin * truecount;
        
        if(producto < apuestaMin)
        {
            return apuestaMin;
        }
        else if (producto > apuestaMax)
        {
            return apuestaMax;
        }
        
        return (int) producto;
    }
}
