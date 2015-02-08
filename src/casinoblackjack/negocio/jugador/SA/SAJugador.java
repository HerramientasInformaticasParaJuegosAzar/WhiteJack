/*
 * Copyright (C) 2015 usuario_local
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

package casinoblackjack.negocio.jugador.SA;

import casinoblackjack.negocio.Conexion.Conexion;
import casinoblackjack.negocio.cartas.Carta;
import casinoblackjack.negocio.jugador.Decision;
import casinoblackjack.negocio.jugador.SA.imp.SAJugadorImp;
import casinoblackjack.negocio.jugador.estrategias.Estrategia;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.LockModeType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Persistence;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;



public abstract class SAJugador 
{
        
        
        private boolean activo;
        
        private Integer idjugadores;
       
        private String usuario;
       
        private String password;
       
        private Date fechaRegistro;
    
        public SAJugador(String usuario, String password, Date fechaRegistro) 
        {
            this.usuario = usuario;
            this.password = password;
            this.fechaRegistro = fechaRegistro;
            this.activo = true;
            altaJugador();
        }

    
    public final boolean altaJugador() 
    {   
        boolean correcto = true;
        
        Connection conexion = Conexion.getInstancia();
        
        try
        {
            Statement st = conexion.createStatement();
        
            ResultSet rs = st.executeQuery("SELECT * FROM jugadores WHERE usuario = '" + this.usuario+"'");
        
            // ID, user, pass, fecha, activo
            // Si existe el jugador
            if (rs.next())
            {
                // 
                if (rs.getBoolean("activo") && rs.getString("password").equalsIgnoreCase(password))
                {
                    this.idjugadores = rs.getInt("idJugadores");
                }
                else
                {
                    correcto = false;
                    System.err.println("La password del usuario introducido no coincide o esta desactivado.");
                }
                
            }
            else
            {
                st.executeUpdate("INSERT INTO jugadores(usuario,password,fechaRegistro,activo) VALUES ('"+usuario+"','"+password+"',"+this.fechaRegistro+","+this.activo+")");
                rs = st.executeQuery("SELECT * FROM jugadores WHERE usuario = '" + this.usuario+"'");
                if (rs.next())
                    this.idjugadores = rs.getInt("idJugadores");
                
            }
        
        }
        catch(Exception e)
        {
           e.printStackTrace();
        }
        
        
        return correcto;
    }

 
    
    
    /*
    Configura una estrategia para el jugador
    */
    public abstract void setEstrategia(Estrategia estrategia);
    
    /*
    Devuelve las cartas de un jugador, de su montón número nSplit (0,1,2...)
    */
    public abstract ArrayList<Carta> getCartas(int nSplit);
    
    /*
    Devuelve todas las cartas de un jugador
    */
    public abstract ArrayList<Carta> getCartas();
    
    /*
    Devuelve la apuesta del jugador
    */
    public abstract int apostar(int apuestaMin, int apuestaMax);
    
    /*
    Devuelve la decision del jugador
    */
    public abstract Decision makeDecision(Carta cartaDealer, ArrayList<Carta> cartasEnMesa, int nSplit);

    /*
    Añade una carta a uno de los montones del jugador (0,1,2...)
    */
    public abstract void addCarta(Carta carta, int nSplit);

    /*
    Splitea el monton nSplit(0,1,2...) de un jugador
    */
    public abstract void split(int nSplit);

    /*
    Quema las cartas del jugador, eliminando las cartas que tiene
    */
    public abstract void quemarCartas();
    
    /*
    Devuelve el número de montones que tiene un jugador. P ej, si ha hecho split 2 times, devuelve 3
    */
    public abstract int numSplits();
    
    /*
    Permite al jugador observar todas las cartas que hay en la mesa después de que el dealer haga su jugada
    */
    public abstract void verCartasEnMesa(ArrayList<Carta> cartasEnMesa);
    
    
    
    
    public final Integer getIdjugadores() 
    {
        return idjugadores;
    }

    public final void setIdjugadores(Integer idjugadores) 
    {
        this.idjugadores = idjugadores;
    }

    public final String getUsuario() 
    {
        return usuario;
    }

    public final void setUsuario(String usuario) 
    {
        this.usuario = usuario;
    }

    public final String getPassword() 
    {
        return password;
    }

    public final void setPassword(String password)
    {
        this.password = password;
    }

    public final Date getFechaRegistro() 
    {
        return fechaRegistro;
    }

    public final void setFechaRegistro(Date fechaRegistro) 
    {
        this.fechaRegistro = fechaRegistro;
    }
     
    public final boolean getActivo() 
    {
        return activo;
    }

    public final void setActivo(boolean activo) 
    {
        this.activo = activo;
    }
}
