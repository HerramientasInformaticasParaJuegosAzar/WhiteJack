package casinoblackjack.negocio.mesa.simulacion;

import javax.swing.JProgressBar;

import casinoblackjack.negocio.mesa.Mesa;

public class Simulacion extends Thread {

	private JProgressBar progressBar;
	private Mesa mesa;
	private int numSimulaciones;

	public Simulacion(Mesa mesa, JProgressBar progressBar,int numSimulaciones ){
		this.mesa = mesa;
		this.progressBar = progressBar;
		this.numSimulaciones = numSimulaciones;
	}
	
	public void run(){
		this.progressBar.setMinimum(0);
		this.progressBar.setMaximum(numSimulaciones);
		for(int i = 0 ; i< numSimulaciones; i++){
			mesa.jugarTurno();
			this.progressBar.setValue(this.progressBar.getValue()+1);
		}
	}
	
}
