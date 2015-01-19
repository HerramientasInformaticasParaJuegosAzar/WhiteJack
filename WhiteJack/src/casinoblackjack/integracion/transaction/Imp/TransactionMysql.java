/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package casinoblackjack.integracion.transaction.Imp;

import integracion.transaction.Transaction;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 *
 * 
 */
public class TransactionMysql implements Transaction
{
   
    private Connection _connection;
    
    // Inicializa la conexion de la base de datos.
    @Override
    public void start() throws Exception
    {
        Properties props = new Properties();
        try 
        {
            // Cargamos la configuracion de la BBDD.
            InputStream fichero = getClass().getResourceAsStream("database.properties");
            props.load(fichero);
	}
	catch(IOException ioe) 
        {
            throw new RuntimeException("No se ha podido leer el fichero de configuración");
        }
        try 
        {
            _connection = DriverManager.getConnection(props.getProperty("jdbc.url"), props.getProperty("jdbc.username"), props.getProperty("jdbc.password"));
	}
        
	
        catch (SQLException e) 
        {
            throw new SQLException("No se ha podido establecer la conexón con la base de datos.");
	}
		
	try 
        {
            _connection.setAutoCommit(false);
	} 
        catch (SQLException e) 
        {
            e.printStackTrace();
	}
        
    }

    @Override
    public void commit() throws SQLException 
    {
        _connection.commit();
        _connection.close();
	
        
    }

    @Override
    public void rollback()  throws SQLException
    {
        _connection.rollback();
        _connection.close();
    }

    @Override
    public Connection getResource() 
    {
        return _connection;
    }

    @Override
    public void lock(String tabla)  throws SQLException
    {
        Statement query = null;
        query = _connection.createStatement();
	query.execute("LOCK TABLES '"+tabla+"' WRITE");
    }
    
}
