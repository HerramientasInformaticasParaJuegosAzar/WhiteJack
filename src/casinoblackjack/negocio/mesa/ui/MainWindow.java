/*
 * Copyright (C) 2015 Vik
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
package casinoblackjack.negocio.mesa.ui;

import casinoblackjack.negocio.cartas.Carta;
import casinoblackjack.negocio.jugador.SA.SAJugador;
import casinoblackjack.negocio.jugador.SA.imp.SAJugadorImp;
import casinoblackjack.negocio.jugador.estrategias.Estrategia;
import casinoblackjack.negocio.mesa.Mesa;
import casinoblackjack.negocio.mesa.obs.Observador;
import casinoblackjack.negocio.mesa.simulacion.Simulacion;
import casinoblackjack.negocio.mesa.ui.player.JugadorUI;

import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JTextField;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JProgressBar;
import javax.swing.JSeparator;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 *
 * @author Vik
 */
public class MainWindow extends Thread implements Observador {

	/**
	 * 
	 */
	private Mesa mesa;
	
	private JFrame frame;
	
	private ArrayList<Estrategia> estrategias;

	private JProgressBar progressBar;

	public void setMesa(Mesa mesa) {
		this.mesa = mesa;
	}

	public MainWindow(ArrayList<Estrategia> estrategias, Mesa mesa2) {
		this.mesa = mesa2;
		this.estrategias = estrategias;
		this.numTurno = 0;
	}

	/**
	 * @wbp.parser.entryPoint
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initComponents() {
		this.frame = new JFrame();
		setLookAndFeel();

		this.frame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		this.frame.setTitle("WhiteJack (BlackJack Analyzer)");
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{346, 484, 0};
		gridBagLayout.rowHeights = new int[]{451, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		jPanel1 = new javax.swing.JPanel();
		jLabel4 = new javax.swing.JLabel();
		comboBoxJugada = new javax.swing.JComboBox();
		jLabel5 = new javax.swing.JLabel();
		sliderSimulaciones = new javax.swing.JSlider();
		jPanel3 = new javax.swing.JPanel();
		jLabel7 = new javax.swing.JLabel();
		jTextField1 = new javax.swing.JTextField();
		jLabel8 = new javax.swing.JLabel();
		tfGanadas = new javax.swing.JTextField();
		jLabel9 = new javax.swing.JLabel();
		tfPerdidas = new javax.swing.JTextField();
		jLabel10 = new javax.swing.JLabel();
		tfPercVictorias = new javax.swing.JTextField();
		botonJugar = new javax.swing.JButton();
		jLabel11 = new javax.swing.JLabel();
		spinnerJugadores = new javax.swing.JSpinner();

		jLabel4.setText("Estragegias posibles");

		comboBoxJugada.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));

		jLabel5.setText("N\u00FAmero de Simulaciones");

		sliderSimulaciones.setMajorTickSpacing(1000);
		sliderSimulaciones.setMaximum(10000);
		sliderSimulaciones.setMinimum(1000);
		sliderSimulaciones.setPaintLabels(true);
		sliderSimulaciones.setPaintTicks(true);

		jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

		jLabel7.setText("Dinero Ganado:");

		jTextField1.setText("0");

		jLabel8.setText("Partidas Ganadas:");

		tfGanadas.setText("0");

		jLabel9.setText("Partidas Perdidas:");

		tfPerdidas.setText("0");

		jLabel10.setText("Porcentaje de Victorias");

		tfPercVictorias.setText("0%");

		botonJugar.setText("Jugar");
		botonJugar.setActionCommand("");
		botonJugar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jugar();
			}
		});
		jLabel6 = new javax.swing.JLabel();

		jLabel6.setText("Resultados");

		javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
		jPanel3Layout.setHorizontalGroup(
				jPanel3Layout.createParallelGroup(Alignment.LEADING)
				.addGroup(jPanel3Layout.createSequentialGroup()
						.addGroup(jPanel3Layout.createParallelGroup(Alignment.LEADING)
								.addGroup(jPanel3Layout.createSequentialGroup()
										.addContainerGap()
										.addGroup(jPanel3Layout.createParallelGroup(Alignment.LEADING)
												.addGroup(jPanel3Layout.createSequentialGroup()
														.addGap(14)
														.addComponent(jLabel7)
														.addPreferredGap(ComponentPlacement.RELATED)
														.addComponent(jTextField1, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE))
														.addGroup(jPanel3Layout.createParallelGroup(Alignment.LEADING, false)
																.addGroup(jPanel3Layout.createSequentialGroup()
																		.addComponent(jLabel10)
																		.addPreferredGap(ComponentPlacement.UNRELATED)
																		.addComponent(tfPercVictorias))
																		.addGroup(jPanel3Layout.createSequentialGroup()
																				.addComponent(jLabel9)
																				.addPreferredGap(ComponentPlacement.RELATED)
																				.addComponent(tfPerdidas))
																				.addGroup(jPanel3Layout.createSequentialGroup()
																						.addComponent(jLabel8)
																						.addPreferredGap(ComponentPlacement.RELATED)
																						.addComponent(tfGanadas, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE)))))
																						.addComponent(botonJugar, GroupLayout.DEFAULT_SIZE, 312, Short.MAX_VALUE)
																						.addGroup(jPanel3Layout.createSequentialGroup()
																								.addContainerGap()
																								.addComponent(jLabel6)))
																								.addContainerGap())
				);
		jPanel3Layout.setVerticalGroup(
				jPanel3Layout.createParallelGroup(Alignment.LEADING)
				.addGroup(jPanel3Layout.createSequentialGroup()
						.addContainerGap()
						.addComponent(jLabel6)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(jPanel3Layout.createParallelGroup(Alignment.BASELINE)
								.addComponent(jLabel7)
								.addComponent(jTextField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addGroup(jPanel3Layout.createParallelGroup(Alignment.BASELINE)
										.addComponent(jLabel8)
										.addComponent(tfGanadas, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addGroup(jPanel3Layout.createParallelGroup(Alignment.BASELINE)
												.addComponent(jLabel9)
												.addComponent(tfPerdidas, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
												.addPreferredGap(ComponentPlacement.UNRELATED)
												.addGroup(jPanel3Layout.createParallelGroup(Alignment.BASELINE)
														.addComponent(jLabel10)
														.addComponent(tfPercVictorias, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
														.addPreferredGap(ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
														.addComponent(botonJugar, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
														.addContainerGap())
				);
		jPanel3.setLayout(jPanel3Layout);

		jLabel11.setText("Numero de Jugadores en la mesa:");

		spinnerJugadores.setModel(new SpinnerNumberModel(0, 0, 6, 1));
		spinnerJugadores.setVerifyInputWhenFocusTarget(false);

		JButton btnNewButton = new JButton("Simular");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				simular();
			}
		});

		progressBar = new JProgressBar();

		JButton btnNewButton_1 = new JButton("Establecer Jugadores");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				establecerJugadores();
			}
		});

		JSeparator separator = new JSeparator();

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
		jPanel1Layout.setHorizontalGroup(
			jPanel1Layout.createParallelGroup(Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup()
					.addContainerGap()
					.addGroup(jPanel1Layout.createParallelGroup(Alignment.TRAILING)
						.addGroup(jPanel1Layout.createSequentialGroup()
							.addComponent(separator, GroupLayout.DEFAULT_SIZE, 353, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(jPanel1Layout.createSequentialGroup()
							.addGroup(jPanel1Layout.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnNewButton, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 353, Short.MAX_VALUE)
								.addComponent(progressBar, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 353, Short.MAX_VALUE)
								.addComponent(comboBoxJugada, Alignment.LEADING, 0, 353, Short.MAX_VALUE)
								.addGroup(Alignment.LEADING, jPanel1Layout.createSequentialGroup()
									.addComponent(jLabel4)
									.addGap(18, 256, Short.MAX_VALUE))
								.addComponent(jLabel5, Alignment.LEADING)
								.addComponent(sliderSimulaciones, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 353, Short.MAX_VALUE)
								.addComponent(jPanel3, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 353, Short.MAX_VALUE)
								.addGroup(Alignment.LEADING, jPanel1Layout.createSequentialGroup()
									.addComponent(jLabel11)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(spinnerJugadores, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 143, Short.MAX_VALUE)))
							.addContainerGap())))
		);
		jPanel1Layout.setVerticalGroup(
			jPanel1Layout.createParallelGroup(Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup()
					.addContainerGap()
					.addComponent(jLabel4)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(comboBoxJugada, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(jLabel5)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(sliderSimulaciones, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
						.addGroup(jPanel1Layout.createSequentialGroup()
							.addGap(21)
							.addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
								.addComponent(jLabel11)
								.addComponent(spinnerJugadores, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(jPanel1Layout.createSequentialGroup()
							.addGap(18)
							.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(8)
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
					.addGap(4)
					.addComponent(progressBar, GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(jPanel3, GroupLayout.PREFERRED_SIZE, 218, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		jPanel1.setLayout(jPanel1Layout);
		GridBagConstraints gbc_jPanel1 = new GridBagConstraints();
		gbc_jPanel1.anchor = GridBagConstraints.NORTHWEST;
		gbc_jPanel1.insets = new Insets(0, 0, 0, 5);
		gbc_jPanel1.gridx = 0;
		gbc_jPanel1.gridy = 0;
		frame.getContentPane().add(jPanel1, gbc_jPanel1);
		jPanel2 = new javax.swing.JPanel();
		GridBagLayout gbl_jPanel2 = new GridBagLayout();
		gbl_jPanel2.columnWidths = new int[]{0, 0, 0, 0};
		gbl_jPanel2.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_jPanel2.columnWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_jPanel2.rowWeights = new double[]{0.0, 1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		jPanel2.setLayout(gbl_jPanel2);

		lblCartasDelJugador = new JLabel("Cartas del Jugador");
		GridBagConstraints gbc_lblCartasDelJugador = new GridBagConstraints();
		gbc_lblCartasDelJugador.insets = new Insets(0, 0, 5, 5);
		gbc_lblCartasDelJugador.gridx = 0;
		gbc_lblCartasDelJugador.gridy = 0;
		jPanel2.add(lblCartasDelJugador, gbc_lblCartasDelJugador);

		lblCartasDelDealer = new JLabel("Cartas del Dealer");
		GridBagConstraints gbc_lblCartasDelDealer = new GridBagConstraints();
		gbc_lblCartasDelDealer.insets = new Insets(0, 0, 5, 5);
		gbc_lblCartasDelDealer.gridx = 1;
		gbc_lblCartasDelDealer.gridy = 0;
		jPanel2.add(lblCartasDelDealer, gbc_lblCartasDelDealer);

		lblCartasDeLa = new JLabel("Cartas de la Mesa");
		GridBagConstraints gbc_lblCartasDeLa = new GridBagConstraints();
		gbc_lblCartasDeLa.insets = new Insets(0, 0, 5, 0);
		gbc_lblCartasDeLa.gridx = 2;
		gbc_lblCartasDeLa.gridy = 0;
		jPanel2.add(lblCartasDeLa, gbc_lblCartasDeLa);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 1;
		jPanel2.add(scrollPane, gbc_scrollPane);

		taCartasJugador = new JTextArea();
		scrollPane.setViewportView(taCartasJugador);

		JScrollPane scrollPane_1 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridx = 1;
		gbc_scrollPane_1.gridy = 1;
		jPanel2.add(scrollPane_1, gbc_scrollPane_1);

		taCartasDealer = new JTextArea();
		scrollPane_1.setViewportView(taCartasDealer);

		JScrollPane scrollPane_2 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_2 = new GridBagConstraints();
		gbc_scrollPane_2.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane_2.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_2.gridx = 2;
		gbc_scrollPane_2.gridy = 1;
		jPanel2.add(scrollPane_2, gbc_scrollPane_2);

		taCartasEnMesa = new JTextArea();
		scrollPane_2.setViewportView(taCartasEnMesa);
		labelApuesta = new javax.swing.JLabel();

		labelApuesta.setText("Apuesta del jugador: 0");
		labelApuesta.setToolTipText("");
		GridBagConstraints gbc_labelApuesta = new GridBagConstraints();
		gbc_labelApuesta.gridwidth = 3;
		gbc_labelApuesta.fill = GridBagConstraints.HORIZONTAL;
		gbc_labelApuesta.anchor = GridBagConstraints.NORTH;
		gbc_labelApuesta.insets = new Insets(0, 0, 5, 0);
		gbc_labelApuesta.gridx = 0;
		gbc_labelApuesta.gridy = 2;
		jPanel2.add(labelApuesta, gbc_labelApuesta);

		JLabel lblLog = new JLabel("Log:");
		GridBagConstraints gbc_lblLog = new GridBagConstraints();
		gbc_lblLog.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblLog.insets = new Insets(0, 0, 5, 5);
		gbc_lblLog.gridx = 0;
		gbc_lblLog.gridy = 3;
		jPanel2.add(lblLog, gbc_lblLog);

		scrollPane_3 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_3 = new GridBagConstraints();
		gbc_scrollPane_3.gridwidth = 3;
		gbc_scrollPane_3.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane_3.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_3.gridx = 0;
		gbc_scrollPane_3.gridy = 4;
		jPanel2.add(scrollPane_3, gbc_scrollPane_3);

		taLog = new JTextArea();
		scrollPane_3.setViewportView(taLog);
		GridBagConstraints gbc_jPanel2 = new GridBagConstraints();
		gbc_jPanel2.fill = GridBagConstraints.BOTH;
		gbc_jPanel2.gridx = 1;
		gbc_jPanel2.gridy = 0;
		frame.getContentPane().add(jPanel2, gbc_jPanel2);
		setPosiblesEstrategias(estrategias);
		this.frame.setLocationRelativeTo(null);
		this.frame.pack();
	}// </editor-fold>//GEN-END:initComponents



	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton botonJugar;
	@SuppressWarnings("rawtypes")
	private javax.swing.JComboBox comboBoxJugada;
	private javax.swing.JLabel jLabel10;
	private javax.swing.JLabel jLabel11;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JLabel jLabel6;
	private javax.swing.JLabel jLabel7;
	private javax.swing.JLabel jLabel8;
	private javax.swing.JLabel jLabel9;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JPanel jPanel3;
	private javax.swing.JSlider sliderSimulaciones;
	private javax.swing.JTextField jTextField1;
	private static JTextField tfGanadas;
	private static JTextField tfPerdidas;
	private static JTextField tfPercVictorias;
	private javax.swing.JLabel labelApuesta;
	private javax.swing.JSpinner spinnerJugadores;
	private JTextArea taCartasJugador;
	private JTextArea taCartasEnMesa;
	private JTextArea taCartasDealer;
	// End of variables declaration//GEN-END:variables

	protected void simular() {
		int numSimulaciones = sliderSimulaciones.getValue();
		addEstrategia();
                progressBar.setValue(0);
		new Simulacion(mesa, progressBar, numSimulaciones).start();
	}

	protected void establecerJugadores() {
		// TODO Auto-generated method stub
		int numJugadores = (int) spinnerJugadores.getValue();
		ArrayList<SAJugador> jugadores = new ArrayList<>();
		for (int i = 0; i <numJugadores ; i++) {
			jugadores.add(new SAJugadorImp());
		}
		this.mesa.setPlayers(jugadores);
	}
	
	private void jugar() {
		configurarMesaJugador();
		numTurno++;
		this.mesa.jugarTurno();
	}

	private int apuestaEstrategia;
	private JLabel lblCartasDelJugador;
	private JLabel lblCartasDelDealer;
	private JLabel lblCartasDeLa;

	@Override
	public void actualizarApuestas() {
		apuestaEstrategia = mesa.getApuestaJugadorPrincipal();
		labelApuesta.setText("Apuesta del jugador: "+apuestaEstrategia);
	}
	private int numTurno;
	private JScrollPane scrollPane_3;
	private static JTextArea taLog;

	public static void Log(String s){
		MainWindow.taLog.append(s+"\n");;
	}

	@Override
	public void actualizarCartas() {
		mesa.getCartasEnMesa();
		String cartas="------Turno "+numTurno+"------\n";
		for(Carta carta : mesa.getCartasEstrategias()){
			cartas+= " "+carta.toString()+"\n";
		}
		this.taCartasJugador.setText("");
		this.taCartasJugador.append(cartas+"\n");

		cartas="------Turno "+numTurno+"------\n";
		for(Carta carta : mesa.getCartasDealer()){
			cartas+= " "+carta.toString()+"\n";
		}
		this.taCartasDealer.setText("");
		this.taCartasDealer.append(cartas+"\n");

		cartas="------Turno "+numTurno+"------\n";
		for(Carta carta : mesa.getCartasEnMesa()){
			cartas+= " "+carta.toString()+"\n";
		}
		this.taCartasEnMesa.setText("");
		this.taCartasEnMesa.append(cartas+"\n");
	}

	private void configurarMesaJugador() {
		addJugadorUI();
	}


	private static int partidasGanadas = 0;
	private static int partidasPerdidas = 0;

	public static void jugadorGana(){
		partidasGanadas++;
		MainWindow.tfGanadas.setText(partidasGanadas+"");
		actualizarPerc();
	}

	public static void jugadorPierde(){
		partidasPerdidas++;
		MainWindow.tfPerdidas.setText(partidasPerdidas+"");
		actualizarPerc();
	}

	private static void actualizarPerc(){
		if(partidasGanadas == 0)
			MainWindow.tfPercVictorias.setText("0%");
		else{
			Float total = (partidasGanadas+partidasPerdidas)*1f ;
			Float victorias = partidasGanadas*1f;
			Float aux = victorias/total*100;
			MainWindow.tfPercVictorias.setText(aux+"%");
		}
	}

	private void addEstrategia() {
		SAJugadorImp jugador = new SAJugadorImp((Estrategia) comboBoxJugada.getModel().getSelectedItem());
		this.mesa.addMainPlayer(jugador, false);
	}
	
	private void addJugadorUI() {
		if(!mesa.isUIPlayer()){
			JugadorUI jugador = new JugadorUI((Estrategia) comboBoxJugada.getModel().getSelectedItem());
			this.mesa.addMainPlayer(jugador, true);
		}
	}


	@SuppressWarnings("unchecked")
	public void setPosiblesEstrategias(ArrayList<Estrategia> estrategias){
		@SuppressWarnings("rawtypes")
		DefaultComboBoxModel<Estrategia> model = new  DefaultComboBoxModel();
		for(Estrategia estrategia: estrategias){
			model.addElement(estrategia);
		}
		this.comboBoxJugada.setModel(model);
	}

	public void run(){
		initComponents();
		frame.setVisible(true);
	}
	private void setLookAndFeel() {
		try {
			UIManager.setLookAndFeel(
					UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}