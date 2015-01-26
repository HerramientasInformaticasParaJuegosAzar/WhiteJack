/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package casinoblackjack.negocio.cuentas.SA.imp;

import casinoblackjack.negocio.cuentas.Cuenta;
import casinoblackjack.negocio.cuentas.SA.SABanca;
import casinoblackjack.negocio.jugador.Jugador;
import casinoblackjack.negocio.jugador.SA.imp.SAJugadorImp;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Krnx
 */
public class SABancaImp implements SABanca
{

    @Override
    public int altaCuenta(Cuenta cuenta) 
    {
        cuenta.setIdcuentas(-1);
        
        EntityManager em = null;
        EntityManagerFactory ef = null;
        try 
        {
            ef = Persistence.createEntityManagerFactory("WhiteJackPU");
            em = ef.createEntityManager();
            
            em.getTransaction().begin();
            
            Cuenta cuentaAux = null;
            
            List results = em.createNamedQuery("Cuenta.findByIdcuentas")
            .setParameter("idcuentas", cuenta.getIdcuentas())
            .getResultList();
            
            if (results.size() > 0)
                cuentaAux = (Cuenta)results.get(0);
            
            if (cuentaAux != null)
            {
                if (!cuentaAux.getActiva())
                {
                    cuentaAux.setActiva(true);
                    em.merge(cuentaAux);
                    cuenta.setIdcuentas(cuentaAux.getIdcuentas());
                    em.getTransaction().commit();
                }
                else
                {
                    System.out.println("Ya existe la cuenta");
                    em.getTransaction().rollback();
                }
            }            
            else
            {
                em.persist(cuenta);
                em.getTransaction().commit();
            }
            
           
        } 
        finally 
        {
            if (em != null)             
                em.close();           
            if (ef != null)
                ef.close();
        }
        
        return cuenta.getIdcuentas();
    }

    @Override
    public double consultarSaldoCuenta(int idCuenta) 
    {
        double saldo = 0;
        EntityManager em = null;
        EntityManagerFactory ef = null;
        try 
        {
            ef= Persistence.createEntityManagerFactory("WhiteJackPU");
            em = ef.createEntityManager();
            Cuenta c = em.find(Cuenta.class, idCuenta);
            saldo = c.getSaldo();
        }
        catch (Exception ex) 
        {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) 
            {    
                System.out.println("La cuenta con id " + idCuenta + " no existe.");      
            }
           
        } 
        finally 
        {
            if (em != null) 
            {
                em.close();
            }
            
            if (ef!=null) ef.close();
        }
        return saldo;
    }

    @Override
    public double consultarSaldoCuentas(int idJugador) 
    {
        double saldo = 0;
        
        EntityManager em = null;
        EntityManagerFactory ef = null;
        try 
        {
            ef = Persistence.createEntityManagerFactory("WhiteJackPU");
            em = ef.createEntityManager();
            
            em.getTransaction().begin();
            
           Query sumQuery = em.createQuery("SELECT SUM(saldo) FROM cuentas WHERE jugador = "+idJugador);
            
           saldo = (double)sumQuery.getSingleResult();
           
           
        } 
        finally 
        {
            if (em != null)             
                em.close();           
            if (ef != null)
                ef.close();
        }
        
        return saldo;
    }

    @Override
    public boolean incrementarSaldo(int idCuenta, double incremento) 
    {
        EntityManager em = null;
        boolean correcto = false;
        
        try 
        {
            EntityManagerFactory ef = Persistence.createEntityManagerFactory("WhiteJackPU");
            em = ef.createEntityManager();
            em.getTransaction().begin();
            Cuenta persistentCuenta = em.find(Cuenta.class, idCuenta);
            
           
            
            if (persistentCuenta != null)
            {
                persistentCuenta.setSaldo(persistentCuenta.getSaldo() + incremento);
                em.merge(persistentCuenta);
                em.getTransaction().commit();
                correcto = true;
            }
            else
            {
                System.out.println("No existe la cuenta");
                em.getTransaction().rollback();
            }
        } 
        catch (Exception ex) 
        {
            correcto = false;
            ex.printStackTrace();
            
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return correcto;
    }

    @Override
    public boolean decrementarSaldo(int idCuenta, double decremento) 
    {
        EntityManager em = null;
        boolean correcto = false;
        
        try 
        {
            EntityManagerFactory ef = Persistence.createEntityManagerFactory("WhiteJackPU");
            em = ef.createEntityManager();
            em.getTransaction().begin();
            Cuenta persistentCuenta = em.find(Cuenta.class, idCuenta);
            
           
            
            if (persistentCuenta != null && persistentCuenta.getSaldo() - decremento >= 0)
            {
                persistentCuenta.setSaldo(persistentCuenta.getSaldo() - decremento);
                em.merge(persistentCuenta);
                em.getTransaction().commit();
                correcto = true;
            }
            else
            {
                System.out.println("No existe la cuenta o no se puede retirar dicha apuesta");
                em.getTransaction().rollback();
            }
        } 
        catch (Exception ex) 
        {
            correcto = false;
            ex.printStackTrace();
            
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return correcto;
    }

    @Override
    public boolean bajaCuenta(int idCuenta) 
    {
        EntityManager em = null;
        boolean correcto = false;
        
        try 
        {
            EntityManagerFactory ef = Persistence.createEntityManagerFactory("WhiteJackPU");
            em = ef.createEntityManager();
            em.getTransaction().begin();
            Cuenta persistentCuenta = em.find(Cuenta.class, idCuenta);
            
           
            
            if (persistentCuenta != null)
            {
                persistentCuenta.setActiva(false);
                em.merge(persistentCuenta);
                em.getTransaction().commit();
                correcto = true;
            }
            else
            {
                System.out.println("No existe la cuenta");
                em.getTransaction().rollback();
            }
        } 
        catch (Exception ex) 
        {
            correcto = false;
            ex.printStackTrace();
            
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return correcto;
    }

    public static void main(String args[])
    {
        SABancaImp sa = new SABancaImp();
        SAJugadorImp saJ = new SAJugadorImp();
        Cuenta a = new Cuenta();
        
        /*
        a.setJugador(saJ.mostrarJugador(1));
        a.setSaldo(500);
        sa.altaCuenta(a);
                */
        
        Jugador jugador = new Jugador();
        jugador.setUsuario("hooo4");
        jugador.setPassword("hoooo2");
        jugador.setFechaRegistro(null);
        jugador.setActivo(true);
        saJ.altaJugador(jugador);
    }
}
