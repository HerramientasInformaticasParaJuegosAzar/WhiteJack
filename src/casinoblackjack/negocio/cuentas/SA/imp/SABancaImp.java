/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package casinoblackjack.negocio.cuentas.SA.imp;

import casinoblackjack.negocio.Conexion.Conexion;
import casinoblackjack.negocio.cuentas.Cuenta;
import casinoblackjack.negocio.cuentas.SA.SABanca;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


public class SABancaImp implements SABanca
{

    @Override
    public boolean altaCuenta(int idJugador, double dinero) 
    {
        boolean correcto = true;
        
        Connection conexion = Conexion.getInstancia();
        
        try
        {
            Statement st = conexion.createStatement();
        
            ResultSet rs = st.executeQuery("SELECT * FROM jugadores WHERE idJugadores = '" + idJugador+"'");
        
            // Si existe el jugador
            if (rs.next())
            {
                // Si el jugador esta activo se le crea la cuenta.
                if (rs.getBoolean("activo"))
                {
                    st.executeUpdate("INSERT INTO cuentas(jugador,saldo) VALUES ('"+idJugador+"','"+dinero+"')");
                    
                }
                else
                {
                    correcto = false;
                    System.err.println("El jugador no esta activo y no se le puede crear una cuenta.");
                }
                
            }
        }
        catch(Exception e)
        {
           e.printStackTrace();
        }
        
        
        return correcto;
    
    }

    @Override
    public double consultarSaldoCuenta(int idCuenta) 
    {
        double saldo = 0;
        
        Connection conexion = Conexion.getInstancia();
        
        try
        {
            Statement st = conexion.createStatement();
        
            ResultSet rs = st.executeQuery("SELECT * FROM cuentas WHERE idcuentas = '" + idCuenta +"'");
        
            // Si existe la cuenta
            if (rs.next())
            {
                // Obtenemos el saldo   
                saldo = rs.getDouble("saldo");            
            }
            else
            {  
                System.err.println("La cuenta no existe");
            }
        
        }
        catch(Exception e)
        {
           e.printStackTrace();
        }
        
        
        return saldo;
    }

    @Override
    public boolean incrementarSaldo(int idCuenta, double incremento) 
    {
        boolean correcto = true;
        
        Connection conexion = Conexion.getInstancia();
        
        try
        {
            Statement st = conexion.createStatement();
        
            ResultSet rs = st.executeQuery("SELECT * FROM cuentas WHERE idCuentas = '" + idCuenta +"'");
        
            
            // Si existe la cuenta y esta activa
            if (rs.next() && rs.getBoolean("activa"))
            {
                
                incremento += rs.getDouble("saldo");
                st.executeUpdate("UPDATE cuentas SET saldo = " + incremento);          
            }
            else
            {
                correcto = false;
                System.err.println("La cuenta no existe o no esta activa.");
            }
        
        }
        catch(Exception e)
        {
           e.printStackTrace();
        }
        
        return correcto;
    }

    @Override
    public boolean decrementarSaldo(int idCuenta, double decremento) 
    {
        boolean correcto = true;
        
        Connection conexion = Conexion.getInstancia();
        
        try
        {
            Statement st = conexion.createStatement();
        
            ResultSet rs = st.executeQuery("SELECT * FROM cuentas WHERE idCuentas = '" + idCuenta +"'");
        
            
            // Si existe la cuenta
            if (rs.next() && rs.getBoolean("activa") &&(rs.getDouble("saldo") - decremento >= 0))
            {
                decremento = rs.getDouble("saldo") - decremento;
                
                st.executeUpdate("UPDATE cuentas SET saldo = " + decremento);          
            }
            else
            {
                correcto = false;
                System.err.println("La cuenta no existe o no esta activa o no tienes saldo suficiente para ejecutar esta accion.");
            }
        
        }
        catch(Exception e)
        {
           e.printStackTrace();
        }
        
        return correcto;
    }

    
    public ArrayList<Integer> obtenerCuentasJugador(int idJugador)
    {
        ArrayList<Integer> cuentasDelJugador = new ArrayList<>();
        
        Connection conexion = Conexion.getInstancia();
        
        try
        {
            Statement st = conexion.createStatement();
        
            ResultSet rs = st.executeQuery("SELECT * FROM cuentas WHERE jugador = '" + idJugador + "'");
        
            // Mientras el jugador tiene cuentas asociadas.
            while (rs.next())
            {
                // Si la cuenta esta activa
                if (rs.getBoolean("activa"))
                {
                    cuentasDelJugador.add(rs.getInt("idCuentas"));
                }
        
            }
        }
        catch(Exception e)
        {
           e.printStackTrace();
        }
        
        return cuentasDelJugador;
    }
    /*
     public static void main(String args[])
    {
        Cuenta a = new Cuenta(20);
        SABanca b = new SABancaImp();
        
        b.altaCuenta(5, 500);
        ArrayList<Integer> cuentas = b.obtenerCuentasJugador(5);
        for(int i = 0; i < cuentas.size();i++)
        {
            System.out.println("La cuenta "+cuentas.get(i)+" tiene " + b.consultarSaldoCuenta(cuentas.get(i)) + " de saldo.");
        }
        b.incrementarSaldo(cuentas.get(0), 25);
        b.decrementarSaldo(cuentas.get(0), 20);
    }
    */
    /*
    public static void main(String args[])
    {
        SABancaImp sa = new SABancaImp();
        SAJugadorImp saJ = new SAJugadorImp();
        Cuenta a = new Cuenta();
        
        
        a.setJugador(saJ.mostrarJugador(1));
        a.setSaldo(500);
        sa.altaCuenta(a);
                
        
        Jugador jugador = new Jugador();
        jugador.setUsuario("hooo84");
        jugador.setPassword("hoooo2");
        jugador.setFechaRegistro(null);
        jugador.setActivo(true);
        saJ.altaJugador(jugador);
        System.out.println(jugador.getIdjugadores());
        
       
        
        ArrayList<Integer> b = sa.obtenerCuentasJugador(jugador);
        for (int i = 0; i < b.size();i++)
            System.out.println(b.get(i));
    }
    */
}
