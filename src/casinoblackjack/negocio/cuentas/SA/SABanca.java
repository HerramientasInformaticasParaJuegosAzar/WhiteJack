/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package casinoblackjack.negocio.cuentas.SA;

import java.util.ArrayList;

/**
 *
 * @author Krnx
 */
public interface SABanca 
{
    public boolean altaCuenta(int idJugador, double dinero);
    public double consultarSaldoCuenta(int idCuenta);
    public boolean incrementarSaldo(int idCuenta, double incremento);
    public boolean decrementarSaldo(int idCuenta, double decremento);
    public ArrayList<Integer> obtenerCuentasJugador(int idJugador);
}
