package casinoblackjack.negocio.mesa.ui.player;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import casinoblackjack.negocio.cartas.Carta;
import casinoblackjack.negocio.jugador.Decision;
import casinoblackjack.negocio.jugador.SA.imp.SAJugadorImp;
import casinoblackjack.negocio.jugador.estrategias.Estrategia;
import casinoblackjack.negocio.mesa.ui.MainWindow;

public class JugadorUI extends SAJugadorImp {
	
	private Estrategia estrategia;

	public JugadorUI(Estrategia e){
		super();
		this.estrategia=e;
	}
	


    public int apostar(int apuestaMin, int apuestaMax){
    	int apuesta = -1;
    	int apuestaSugerida = this.estrategia.apostar(apuestaMin, apuestaMax);
    	PreguntarApuesta frame = new PreguntarApuesta(null, apuestaSugerida);
    	frame.pack();
    	frame.setVisible(true);
    	 apuesta = frame.getValidatedBet();
         if (apuesta == -1) {
           	apuesta = 0;
         }
    	return apuesta;
    }
    
  
    public Decision makeDecision(Carta cartaDealer, ArrayList<Carta> cartasEnMesa, int nSplit){
    	casinoblackjack.negocio.jugador.Decision decision;
    	Decision decisionSugerida = this.estrategia.makeDecision(cartaDealer, cartasEnMesa, getCartas());
    	PreguntarDecision frame = new PreguntarDecision(null, decisionSugerida);
       	frame.pack();
    	frame.setVisible(true);
    	decision = frame.getValidatedDecision();
         if (decision == null) {
        	 decision = Decision.STAND;
         }
    	return decision;
    }
	
}
