/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package casinoblackjack.negocio.banca;

/**
 *
 * @author Krnx
 */
public interface Banca 
{
    public int altaCuenta(int idJugador);
    public double consultarSaldoCuenta(int idCuenta);
    public double consultarSaldoCuentas();
    public boolean incrementarSaldo(double incremento);
    public boolean decrementarSaldo(double decremento);
    public boolean bajaCuenta(int idCuenta);
}
