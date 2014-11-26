/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Board;

import Players.Player;
import java.util.ArrayList;

/**
 *
 * @author Ivan
 */
public class Board {
    
    private ArrayList<Player> jugadores;
    private Player dealer;
    private int numJug;
    
    public Board(ArrayList<Player> jug, Player deal){
        this.jugadores = jug;
        this.dealer = deal;
        this.numJug = jug.size();
    }
    
}
