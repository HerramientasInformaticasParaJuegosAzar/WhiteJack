/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package casinoblackjack.negocio.Conexion;

import java.sql.Connection;
import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Krnx
 */
public class Conexion 
{
    private static Connection instance = null;
    
    public static Connection getInstancia()
    {
        if (instance == null)
        {
            Properties props = new Properties();
            try 
            {
            // Cargamos la configuracion de la BBDD.
                  
                InputStream fichero = Conexion.class.getResourceAsStream("database.properties");
                props.load(fichero);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            try {
                // Nueva conexion
                instance = DriverManager.getConnection(props.getProperty("jdbc.url"), props.getProperty("jdbc.username"), props.getProperty("jdbc.password"));
            } catch (SQLException ex) 
            {
                Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return instance;
    }
    
    public static void cerrarConexion()
    {
        try 
        {
            if (instance != null && !instance.isClosed())
                instance.close();
            
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
