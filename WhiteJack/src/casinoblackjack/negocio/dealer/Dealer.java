/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package casinoblackjack.negocio.dealer;

import casinoblackjack.negocio.jugador.SA.imp.SAJugadorImp;
import javax.persistence.EntityManagerFactory;


/**
 *
 * @author Krnx
 */
public class Dealer extends SAJugadorImp{

    public Dealer(EntityManagerFactory emf) {
        super(emf);
    }
    
}
