/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package casinoblackjack.negocio.cuentas.SA;

import casinoblackjack.negocio.cuentas.Cuenta;
import java.util.ArrayList;

/**
 *
 * @author Krnx
 */
public interface SABanca 
{
    public int altaCuenta(Cuenta cuenta);
    public double consultarSaldoCuenta(int idCuenta);
    public double consultarSaldoCuentas(int idJugador);
    public boolean incrementarSaldo(int idCuenta, double incremento);
    public boolean decrementarSaldo(int idCuenta, double decremento);
    public boolean bajaCuenta(int idCuenta);
}
