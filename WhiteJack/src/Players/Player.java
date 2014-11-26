/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Players;

/**
 *
 * @author Ivan
 */
public class Player {
        private String nombre;
        private Hand mano;
        private boolean humano;

        public Player(){
            this.mano = new Hand();
            this.humano = false;
            this.nombre = "";
        }

        public Player(boolean humano, String nombre){
            this.mano = new Hand();
            this.humano = true;
            this.nombre = nombre;
        }    
}
