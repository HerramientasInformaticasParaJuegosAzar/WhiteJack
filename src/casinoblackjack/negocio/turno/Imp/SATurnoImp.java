/*
 * Copyright (C) 2015 Krnx
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
package casinoblackjack.negocio.turno.Imp;


import casinoblackjack.negocio.Conexion.Conexion;
import casinoblackjack.negocio.turno.SATurno;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;


/**
 *
 * @author Krnx
 */
public class SATurnoImp implements SATurno
{

    @Override
    public boolean altaTurno(int idTurno, int idJugador, double resultado) 
    {
        
        boolean correcto = true;
        
        Connection conexion = Conexion.getInstancia();
        
        try
        {
            Statement st = conexion.createStatement();
               
            st.executeUpdate("INSERT INTO turnos(idturnos,jugador,resultado) VALUES ('"+idTurno+"','"+idJugador+"','"+resultado+"')");           
        }
        catch(Exception e)
        {
           e.printStackTrace();
        }
        
        
        return correcto;
    }
    
    @Override
    public int obtenerSiguienteTurno() 
    {
        
        int ultimoTurno = 0;
        
        Connection conexion = Conexion.getInstancia();
        
        try
        {
            Statement st = conexion.createStatement();
        
            ResultSet rs = st.executeQuery("SELECT MAX(idturnos) AS idtur FROM turnos");
        
            // Si existe el jugador
            if (rs.next())
            {
               ultimoTurno = rs.getInt("idtur");
            }
        }
        catch(Exception e)
        {
           e.printStackTrace();
        }
        
        
        return ultimoTurno + 1;
    }
    /*
    public static void main(String args[])
    {
        SATurnosImp sa = new SATurnosImp();
        int ultimoTurno = sa.obtenerUltimoTurno() + 1;
        
        sa.altaTurno(ultimoTurno, 5, 20);
        ultimoTurno++;
        sa.altaTurno(ultimoTurno, 5, 15);
        ultimoTurno++;
        sa.altaTurno(ultimoTurno, 5, 10);
        sa.altaTurno(ultimoTurno, 6, 25);
        
    }
    */
}
