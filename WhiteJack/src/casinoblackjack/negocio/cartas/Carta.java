/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package casinoblackjack.negocio.cartas;

import casinoblackjack.negocio.cartas.enumerados.Palo;
import casinoblackjack.negocio.cartas.enumerados.Valor;

/**
 *
 * @author Krnx
 */
public class Carta 
{
    private Valor valor;
    private Palo palo;
    
    public Carta(Valor valor, Palo palo)
    {
        this.valor = valor;
        this.palo = palo;
    }

    public int getValor() 
    {
        if(this.valor.ordinal()==1) return 11;
        if(this.valor.ordinal()>10) return 10;
        return this.valor.ordinal();
    }

    public void setValor(Valor valor) 
    {
        this.valor = valor;
    }

    public Palo getPalo() 
    {
        return palo;
    }

    public void setPalo(Palo palo) 
    {
        this.palo = palo;
    }
    
    @Override
    public String toString()
    {
        return this.valor + " de " + this.palo;
    }
    
}
