/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package casinoblackjack.negocio.mesa;

import casinoblackjack.negocio.cartas.Carta;
import casinoblackjack.negocio.jugador.Decision;
import casinoblackjack.negocio.cartas.barajeador.Barajeador;
import casinoblackjack.negocio.cuentas.SA.SABanca;
import casinoblackjack.negocio.cuentas.SA.imp.SABancaImp;
import casinoblackjack.negocio.dealer.Dealer;
import casinoblackjack.negocio.jugador.Jugador;
import casinoblackjack.negocio.jugador.SA.SAJugador;
import casinoblackjack.negocio.jugador.SA.imp.SAJugadorImp;
import casinoblackjack.negocio.jugador.estrategias.Estrategia;
import casinoblackjack.negocio.mesa.obs.Observable;
import casinoblackjack.negocio.mesa.ui.MainWindow;
import casinoblackjack.negocio.turno.Turnos;

import java.util.ArrayList;

/**
 *
 * @author Krnx
 */
public class Mesa extends Observable {

    private int id;
    private int apuestaMin;
    private int apuestaMax;
    private final Dealer dealer;
    
    private ArrayList<SAJugador> jugadores;
    
    private SAJugador jugadorPrincipal;
    
    private int apuestaJugadorPrincipal;
    
    private Barajeador baraja;

    private Turnos turno;

    private ArrayList<Carta> cartasQuemadas = new ArrayList<>();

    private SABanca banca;
    
	private boolean uiActiva;
    
    private ArrayList<Integer> apuestas;
    
    private int numBarajas;

    public Mesa(int barajas) {
    	this.numBarajas = barajas;
        this.dealer = new Dealer();
        this.baraja = new Barajeador(barajas);
        this.baraja.barajear();
        this.apuestaMin = 1;
        this.apuestaMax = Integer.MAX_VALUE;
        this.jugadores =  new ArrayList<>();
        this.apuestas = new ArrayList<>();
        this.banca = new  SABancaImp();
    }
    
    public Mesa(int barajas, int apuestaMin, int apuestaMax) {
        this.dealer = new Dealer();
        this.baraja = new Barajeador(barajas);
        this.apuestaMin = apuestaMin;
        this.apuestaMax = apuestaMax;
    }
    
    public boolean isUIPlayer(){
    	return this.uiActiva;
    }
    
	public void setPlayers(ArrayList<SAJugador> jugadores2) {
		this.jugadores = jugadores2;
		for(int i = 0 ; i< jugadores.size();i++){
			this.apuestas.add(0);	
		}
	}
    public void addMainPlayer(SAJugador jugadorprincipal, boolean isUIActive) {
    	this.uiActiva = isUIActive;
        this.jugadorPrincipal= jugadorprincipal;
    }

    public int getApuestaJugadorPrincipal() {
       return apuestaJugadorPrincipal;
    }

    public ArrayList<Carta> getCartasEstrategias() {
        return this.jugadorPrincipal.getCartas();
    }
    
    public ArrayList<Carta> getCartasDealer() {
        return this.dealer.getCartas();
    }

    public ArrayList<Carta> getCartasEnMesa() {
        ArrayList<Carta> cartas;
        cartas = new ArrayList<>();
        if (jugadores != null) {
            for (SAJugador jugador : jugadores) {
                cartas.addAll(jugador.getCartas());
            }
        }
        cartas.addAll(jugadorPrincipal.getCartas());
        cartas.addAll(this.dealer.getCartas());
        return cartas;
    }

    /*   puedeApostar(SAJugador jugador, int posibleApuesta)
     *
     *   Pregunta a la banca si el jugador tiene suficiente dinero en alguna de sus cuentas:
     *       para ello le pregunta a la banca por sus cuentas 
     *       y luego comprueba si con alguna de ellas puede apostar.
     */
    private boolean puedeApostar(SAJugador jugador, int posibleApuesta) {
        return true;/*
        int indexJugador = this.jugadores.indexOf((SAJugador) jugador);
        Jugador aux  = new Jugador();
        aux.setIdjugadores(jugador.getIDJugador());
        ArrayList<Integer> cuentas = banca.obtenerCuentasJugador(aux);
        for (Integer numCuenta : cuentas) {
            //this.banca.consultarSaldoCuenta(numCuenta)
            if (this.banca.consultarSaldoCuenta(1) >= posibleApuesta) {
                this.apuestas.set(indexJugador, posibleApuesta * 2);
                return true;
            }
        }
        return false;*/
    }

    /*   puedeApostar(SAJugador jugador)
     *   
     *   Metodo para hacer "double" o "split", consulta la apuesta y si tiene dinero, la dobla
     *   
     *   Pregunta a la banca si el jugador tiene suficiente dinero en alguna de sus cuentas:
     *       para ello le pregunta a la banca por sus cuentas 
     *       y luego comprueba si con alguna de ellas puede apostar.
     */
    private boolean puedeApostar(SAJugador jugador) {
        //busca la apuesta del jugador 
        return true;
        /*int indexJugador = this.jugadores.indexOf((SAJugador) jugador);
        int posibleApuesta = this.apuestas.get(indexJugador);
        Jugador aux  = new Jugador();
        aux.setIdjugadores(jugador.getIDJugador());
        ArrayList<Integer> cuentas = banca.obtenerCuentasJugador(aux);
        for (Integer numCuenta : cuentas) {
            //this.banca.consultarSaldoCuenta(numCuenta)
            if (this.banca.consultarSaldoCuenta(1) >= posibleApuesta) {
                this.apuestas.set(indexJugador, posibleApuesta * 2);
                return true;
            }
        }
        return false;*/
    }

    /*   recogerApuestas()
     *
     *   Pregunta por una apuesta a cada jugador y lo añade al arraylist de apuestas.
     *   Comprueba que el jugador en cuestión tiene dinero en alguna cuenta.
     */
    private void recogerApuestas() {
        int posibleApuesta;
        if (jugadores != null) {
            for (int i = 0; i < jugadores.size(); i++) {
                posibleApuesta = this.jugadores.get(i).apostar(this.apuestaMin, this.apuestaMax);
                if (puedeApostar(this.jugadores.get(i), posibleApuesta)) {
                    this.apuestas.set(i, posibleApuesta);
                    log("El jugador "+i+" ha realizado una apuesta de "+posibleApuesta);
                } else /*
                 * Si el jugador no tiene dinero para apostar, su apuesta será 0
                 * si el jugador no apuesta no podrá jugar este turno.
                 */ {
                    this.apuestas.set(i, 0);
                }

            }
            posibleApuesta = this.jugadorPrincipal.apostar(apuestaMin, apuestaMax);
            if (puedeApostar(this.jugadorPrincipal, posibleApuesta)) {
                apuestaJugadorPrincipal = posibleApuesta;
                log("El jugador principal ha realizado una apuesta de "+posibleApuesta);
            }
            else apuestaJugadorPrincipal = 0;
        }
        actualizarApuestas();
    }

    /*   preguntarDecisiones()
     *
     *   Pregunta a cada jugador por una decisión siempre que haya apostado algo.
     */
    private void preguntarDecisiones() {
        for (int i = 0; i < jugadores.size(); i++) {
            if (this.apuestas.get(i) > 0) {
            	log("---Turno de decidir del jugador "+i+"---");
                this.jugarTurno(jugadores.get(i), true, 0);
            }
        }
        log("---Turno de decidir del jugador principal---");
        jugarTurno(jugadorPrincipal, true, 0);
        
        log("---Turno de decidir del dealer---");
        jugarTurno(dealer, true, 0);
    }

    /*   jugarTurno(SAJugador jugador)
     *
     *   Bucle para que un jugador decida si sigue pidiendo cartas o se planta 
     *   
     *   si la variable "esPrimerTurno" está a true, podrá hacer split, si no, no.
     *
     *   si la variable "esSplit" esta a 1 habra que consultar el segundo array de cartas
     *   del jugador.
     */
    private void jugarTurno(SAJugador jugador, boolean esPrimerTurno, int esSplit) {
        Decision decision = jugador.makeDecision(this.dealer.getCartas().get(0), this.getCartasEnMesa(), esSplit);
        switch (decision) 
        {
            case HIT:
            	log("El jugador hace hit");
                this.jugadorHit(jugador, esSplit);
                break;
            case DOUBLE:
                if (esPrimerTurno) {
                	
                    this.jugadorDouble(jugador, esSplit);
                }
                break;
            case SPLIT:
                 this.jugadorSplit(jugador, esSplit);
                
                break;
            case STAND:
            	log("El jugador hace stand");
                break;
        }
    }

    /*   comprobarCartas(SAJugador jugador)
     *
     *   calcula el valor de una mano
     */
    private int calcularValor(SAJugador jugador, int esSplit)  {
        ArrayList<Carta> cartas =  jugador.getCartas(esSplit);
        int valor = 0;
        int numAses = 0;
        for (Carta carta : cartas) {
            if(carta.getValor() == 11)
            	numAses++;
            valor += carta.getValor(); 
        }
        for(int i = 1; i<=numAses;i++){
        	if (valor>21) valor-=10;
        }
        return valor;
    }

    /*   jugadorHit(SAJugador jugador, int esSplit)
     *
     *   añade una carta al jugador:
     *  si es split, se la añade al conjunto de cartas correspondiente
     *  si tiene menos de 22, puede volver a decidir y vuelve a "jugarTurno"
     */
    private void jugadorHit(SAJugador jugador, int esSplit) {
        jugador.addCarta(this.baraja.obtenerCarta(), esSplit);
        actualizarCartas();
        if (this.calcularValor(jugador, esSplit) <= 21) {
            this.jugarTurno(jugador, false, esSplit);
        }
        else{
        	log("El jugador hace se ha pasado");
        }
    }

    /*   jugadorDouble(SAJugador jugador, int esSplit)
     *
     *   añade una carta al jugador despues de preguntar si dobla la apuesta:
     *  si es split, se la añade al conjunto de cartas correspondiente
     *  luego deja de pedir cartas
     */
    private void jugadorDouble(SAJugador jugador, int esSplit) {
        if (puedeApostar(jugador)) {
        	log("El jugador hace double");
            jugador.addCarta(this.baraja.obtenerCarta(), esSplit);
            actualizarCartas();
        }
    }

    /*   jugadorSplit(SAJugador jugador)
     *
     *   comprueba que puede hacer split y que puede doblar la apuesta
     */
    private void jugadorSplit(SAJugador jugador, int esSplit) {
        if (puedeApostar(jugador)) {
            if (puedeHacerSplit(jugador, esSplit)) {
            	log("El jugador hace split");
                jugador.split(esSplit);
                actualizarCartas();
                this.jugarTurno(jugador, false, 0);
                this.jugarTurno(jugador, false, 1);
            }
        }
    }

    /*   puedeHacerSplit(SAJugador jugador) 
     *
     *   comprueba que puede hacer split el jugador
     */
    private boolean puedeHacerSplit(SAJugador jugador, int esSplit) {
        ArrayList<Carta> cartas = jugador.getCartas(esSplit);
        
        return cartas.size() == 2 && cartas.get(0).getValor() == cartas.get(1).getValor();
    }

    /*   repartirCartas()
     *
     *   reparte dos cartas a cada jugador y una al dealer
     */
    private void repartirCartas() {
        for (SAJugador jugador : jugadores) {
            jugador.addCarta(this.baraja.obtenerCarta(), 0);
            jugador.addCarta(this.baraja.obtenerCarta(), 0);
        }
        jugadorPrincipal.addCarta(this.baraja.obtenerCarta(), 0);
        jugadorPrincipal.addCarta(this.baraja.obtenerCarta(), 0);
        dealer.addCarta(this.baraja.obtenerCarta(), 0);
        actualizarCartas();

    }

    /*   decidirGanador()
     *
     *   método para decidir ganador,
     *   recorre los jugadores para ver quien gana y despues reparte el dinero
     */
    private void decidirGanador() {
        int valorManoDealer = this.calcularValor(dealer, 0);
        if (valorManoDealer == 21) {
            ganaDealer();
        } else {
            int valorManoJugador;
            for (SAJugador jugador : jugadores) 
            {
                for(int i = 0; i < jugador.numSplits(); i++)
                {
                    valorManoJugador = calcularValor(jugador, 0);
                    if (valorManoJugador == 21) {
                        darDinero(jugador, 2.5);
                    } else if (valorManoDealer >= valorManoJugador) {
                        darDinero(jugador, 2.5);
                    } else {
                        darDinero(jugador, 0);
                    }
                }
            }
            
            for(int i = 0; i < jugadorPrincipal.numSplits(); i++)
            {
                valorManoJugador = calcularValor(jugadorPrincipal, 0);
                    if (valorManoJugador == 21) {
                        darDineroJugadorPrincipal(2.5);
                    } else if (valorManoDealer <= valorManoJugador) {
                        darDineroJugadorPrincipal(2.5);
                    } else {
                        darDineroJugadorPrincipal( 0);
                    }
            }
        }
    }

    /*   ganaDealer()
     *
     *   método para  quitar el dinero a los jugadores, porque el dealer tiene 21
     */
    private void ganaDealer() {
    	log("El dealer gana con blackjack");
        for (int i = 0; i < apuestas.size(); i++) {
            for(int j =0;j < jugadores.get(i).numSplits();j++){
            if (calcularValor(jugadores.get(i), j) ==21) {
            	log("El jugador "+i+" hace empata con el dealer");
                darDinero(jugadores.get(i), 1);
                
            } else {
                apuestas.set(i, 0);
            }
            actualizarCartas();
            }
        }
    }

    /*   darDinero(SAJugador jugador, double d) 
     *
     *   da dinero al jugador en funcion del multiplicador
     *   en caso de hacer blackjack es 2.5
     *   en caso contrario será 2
     *   si el jugador pierde será 0
     */
    private void darDinero(SAJugador jugador, double multiplicador) {
        if (multiplicador == 0) {
        	log("Un jugador ha perdido contra el dealer");
            apuestas.set(jugadores.indexOf(jugador), 0);
        } else {
        	log("Un jugador ha ganado esta partida");
            int indexJugador = this.jugadores.indexOf((SAJugador) jugador);
            //banca.incrementarSaldo(1, 1);
        }
    }
    
    private void darDineroJugadorPrincipal(double multiplicador) {
        if (multiplicador == 0) {
        	log("El jugador principal ha perdido");
        	MainWindow.jugadorPierde();
            this.apuestaJugadorPrincipal = 0;
        } else {
        	MainWindow.jugadorGana();
        	log("El jugador principal ha ganado esta partida");
            //banca.incrementarSaldo(1, multiplicador*apuestaJugadorPrincipal);
        }
    }

    private void limpiarMesa() {
        for (int i = 0; i < apuestas.size(); i++) {
            apuestas.set(i, 0);
        }
        cartasQuemadas.addAll(getCartasEnMesa());
        cartasQuemadas.addAll(jugadorPrincipal.getCartas());
        cartasQuemadas.addAll(dealer.getCartas());
        for (SAJugador jugador : jugadores) {
            jugador.quemarCartas();
            jugador.verCartasEnMesa(cartasQuemadas);
        }
        jugadorPrincipal.quemarCartas();
        dealer.quemarCartas();
    }


    public void log(String s){
        MainWindow.Log(s);
    }
    
    /*   jugarTurno()
     *
     *   bucle del juego
     */
    public void jugarTurno() {
    	log("-----Nuevo Turno-----");
    	log("Pidiendo Apuestas: ");
        recogerApuestas();
        
        log("Repartiendo cartas a los jugadores: ");
        repartirCartas();
        
        log("Preguntado decisiones a los jugadores: ");
        preguntarDecisiones();
        
        log("Decidiendo ganadores: ");
        decidirGanador();
        
        log("Limpiando Mesa...");
        limpiarMesa();
        
        if(dealer.decidirBarajar(cartasQuemadas, numBarajas)){
        	this.baraja = new Barajeador(numBarajas);
        	this.baraja.barajear();
        }
        log("Turno terminado");
    }


}
