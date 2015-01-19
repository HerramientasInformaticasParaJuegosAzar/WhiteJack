/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package casinoblackjack.negocio.cartas.barajeador;

import casinoblackjack.negocio.cartas.Carta;
import casinoblackjack.negocio.cartas.enumerados.Palo;
import casinoblackjack.negocio.cartas.enumerados.Valor;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Krnx
 */
public class Barajeador 
{
    private ArrayList<Carta> cartas;
    private boolean cartasInfinitas = false;
    
    // Constructor del barajeador, recibimos el numero de barajas y si se van a barajear cada vez que se reparte una carta.
    
    public Barajeador(int numeroBarajas)
    {
        this.cartas = new ArrayList<>();
        
        if(numeroBarajas > 0)
            inicializarBaraja(numeroBarajas);
        else
            cartasInfinitas = true;
    }
    
    // Mete n barajas en el barajeador.
    // Coste: O(n);
    private void inicializarBaraja(int numeroBarajas)
    {
        for (int b = 0 ; b < numeroBarajas; b++)
            for (int i = 0; i < 13;i++)
                for (int j = 0; j < 4; j++)
                    this.cartas.add(new Carta(Valor.values()[i],Palo.values()[j]));      
    }
    
    public void barajear()
    {        
        for (int i = 0; i < cartas.size(); i++)
        {
            // asigno a la variable a un numero aleatorio en el rango [0,cartasTotales)
            int a = new Random().nextInt(cartas.size());
            // guardo la carta en la posicion a de la lista en una variable auxiliar.
            Carta cartaTemp = cartas.get(a);
            
            // Cambio las cartas de posicion.
            cartas.set(a, cartas.get(i));
            cartas.set(i, cartaTemp);
        }
    }
    
    // Devuelve la primera carta de la lista, si no hay mas cartas se devuelve null.
    public Carta obtenerCarta()
    {
        Carta cartaSacada = null;
        
        if (cartasInfinitas)
        {
            Random r = new Random();
            cartaSacada = new Carta(Valor.values()[r.nextInt(13)] , Palo.values()[r.nextInt(4)]);
        }
        else
        {
            if (cartas.size() > 0)
            {
                cartaSacada = cartas.get(0);
                cartas.remove(0);
            }
        }
        return cartaSacada;
    }
}
