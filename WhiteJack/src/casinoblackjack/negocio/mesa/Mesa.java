/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package casinoblackjack.negocio.mesa;

import casinoblackjack.negocio.banca.SABanca;
import casinoblackjack.negocio.cartas.Carta;
import casinoblackjack.negocio.cartas.Decision;
import casinoblackjack.negocio.cartas.barajeador.Barajeador;
import casinoblackjack.negocio.dealer.Dealer;
import casinoblackjack.negocio.jugador.SA.SAJugador;
import casinoblackjack.negocio.mesa.obs.Observable;
import casinoblackjack.negocio.turno.Turno;
import java.util.ArrayList;

/**
 *
 * @author Krnx
 */
public class Mesa extends Observable {

    private int id;
    private final Dealer dealer;
    private ArrayList<SAJugador> jugadores;
    private Barajeador baraja;
    private ArrayList<Carta> cartasQuemadas;
    private Turno turno;
    private SABanca banca;
    private ArrayList<Integer> apuestas;

    public Mesa(int barajas) {
        this.dealer = new Dealer(null);
        this.baraja = new Barajeador(barajas);
    }

    public void addPlayer(SAJugador jugador) {
        this.jugadores.add(jugador);
    }

    public ArrayList<Carta> getCartasEnMesa() {
        ArrayList<Carta> cartas;
        cartas = new ArrayList<>();
        for (SAJugador jugador : jugadores) {
            cartas.addAll(jugador.getCartas());
        }
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
        ArrayList<Integer> cuentas = banca.getCuentasJugador(jugador.getIDJugador());
        for (Integer numCuenta : cuentas) {
            if (this.banca.consultarSaldoCuenta(numCuenta) >= posibleApuesta) {
                return true;
            }
        }
        return false;
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
        int indexJugador = this.jugadores.indexOf((SAJugador) jugador);
        int posibleApuesta = this.apuestas.get(indexJugador);

        ArrayList<Integer> cuentas = banca.getCuentasJugador(jugador.getIDJugador());
        for (Integer numCuenta : cuentas) {
            if (this.banca.consultarSaldoCuenta(numCuenta) >= posibleApuesta) {
                this.apuestas.set(indexJugador, posibleApuesta * 2);
                return true;
            }
        }
        return false;
    }

    /*   recogerApuestas()
     *
     *   Pregunta por una apuesta a cada jugador y lo añade al arraylist de apuestas.
     *   Comprueba que el jugador en cuestión tiene dinero en alguna cuenta.
     */
    private void recogerApuestas() {
        int posibleApuesta;
        for (int i = 0; i < jugadores.size(); i++) {
            posibleApuesta = this.jugadores.get(i).apostar();
            if (puedeApostar(this.jugadores.get(i), posibleApuesta)) {
                this.apuestas.set(i, posibleApuesta);
            } else /*
             * Si el jugador no tiene dinero para apostar, su apuesta será 0
             * si el jugador no apuesta no podrá jugar este turno.
             */ {
                this.apuestas.set(i, 0);
            }
            notificarObservadores();
        }
    }

    /*   preguntarDecisiones()
     *
     *   Pregunta a cada jugador por una decisión siempre que haya apostado algo.
     */
    private void preguntarDecisiones() {
        for (int i = 0; i < jugadores.size(); i++) {
            if (this.apuestas.get(i) > 0) {
                this.jugarTurno(jugadores.get(i), true, 0);
            }
        }
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
        Decision decision = jugador.makeDecision(this.dealer.getCartas().get(0), this.getCartasEnMesa());
        switch (decision) {
            case HIT:
                this.jugadorHit(jugador, esSplit);
                break;
            case DOUBLE:
                if (esPrimerTurno) {
                    this.jugadorDouble(jugador, esSplit);
                }
                break;
            case SPLIT:
                if (esPrimerTurno) {
                    this.jugadorSplit(jugador);
                }
                break;
            case PASS:
                break;
        }
    }

    /*   comprobarCartas(SAJugador jugador)
     *
     *   calcula el valor de una mano
     */
    private int calcularValor(SAJugador jugador) {
        ArrayList<Carta> cartas = jugador.getCartas();
        int valor = 0;
        for (Carta carta : cartas) {
            valor += carta.getValor(); //FALTA HACER SI TIENE UN AS
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
        notificarObservadores();
        if (this.calcularValor(jugador) <= 21) {
            this.jugarTurno(jugador, false, esSplit);
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
            jugador.addCarta(this.baraja.obtenerCarta(), esSplit);
            notificarObservadores();
        }
    }

    /*   jugadorSplit(SAJugador jugador)
     *
     *   comprueba que puede hacer split y que puede doblar la apuesta
     */
    private void jugadorSplit(SAJugador jugador) {
        if (puedeApostar(jugador)) {
            if (puedeHacerSplit(jugador)) {
                jugador.split();
                notificarObservadores();
                this.jugarTurno(jugador, false, 0);
                this.jugarTurno(jugador, false, 1);
            }
        }
    }

    /*   puedeHacerSplit(SAJugador jugador) 
     *
     *   comprueba que puede hacer split el jugador
     */
    private boolean puedeHacerSplit(SAJugador jugador) {
        ArrayList<Carta> cartas = jugador.getCartas();
        if (cartas.size() != 2) {
            return false;
        }
        return (cartas.get(0) == cartas.get(1));
    }

    /*   repartirCartas()
     *
     *   reparte dos cartas a cada jugador y una al dealer
     */
    private void repartirCartas() {
        for (SAJugador jugador : jugadores) {
            jugador.addCarta(this.baraja.obtenerCarta());
            jugador.addCarta(this.baraja.obtenerCarta());
            notificarObservadores();
        }
        dealer.addCarta(this.baraja.obtenerCarta());
        notificarObservadores();
    }

    /*   decidirGanador()
     *
     *   método para decidir ganador,
     *   recorre los jugadores para ver quien gana y despues reparte el dinero
     */
    private void decidirGanador() {
        int valorManoDealer = this.calcularValor(dealer);
        if (valorManoDealer == 21) {
            ganaDealer();
        } else {
            int valorManoJugador;
            for (SAJugador jugador : jugadores) {
                valorManoJugador = calcularValor(jugador);
                if (valorManoJugador == 21) {
                    darDinero(jugador, 2.5);
                } else if (valorManoDealer >= valorManoJugador) {
                    darDinero(jugador, 2.5);
                } else {
                    darDinero(jugador, 0);
                }
                notificarObservadores();
            }
        }
    }

    /*   ganaDealer()
     *
     *   método para  quitar el dinero a los jugadores, porque el dealer tiene 21
     */
    private void ganaDealer() {
        for (int i = 0; i < apuestas.size(); i++) {
            if (calcularValor(jugadores.get(i)) == 21) {
                darDinero(jugadores.get(i), 1);
            } else {
                apuestas.set(i, 0);
            }
            notificarObservadores();
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
            apuestas.set(jugadores.indexOf(jugador), 0);
        } else {
            banca.incrementarSaldo(multiplicador);
        }
    }

    private void limpiarMesa() {
        for (int i = 0; i < apuestas.size(); i++) {
            apuestas.set(i, 0);
        }
        cartasQuemadas.addAll(getCartasEnMesa());
        cartasQuemadas.addAll(dealer.getCartas());
        for (SAJugador jugador : jugadores) {
            jugador.quemarCartas();
        }
        dealer.quemarCartas();
        notificarObservadores();
    }


    /*   jugarTurno()
     *
     *   bucle del juego
     */
    public void jugarTurno() {
        recogerApuestas();
        repartirCartas();
        preguntarDecisiones();
        decidirGanador();
        limpiarMesa();
    }

}
