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
package casinoblackjack;

import casinoblackjack.exceptions.IllegalOrphanException;
import casinoblackjack.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import casinoblackjack.negocio.cuentas.Cuenta;
import casinoblackjack.negocio.jugador.Jugador;
import casinoblackjack.negocio.turno.Turnos;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Krnx
 */
public class JugadorJpaController implements Serializable {

    public JugadorJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Jugador jugador) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cuenta cuentas = jugador.getCuentas();
            if (cuentas != null) {
                cuentas = em.getReference(cuentas.getClass(), cuentas.getIdcuentas());
                jugador.setCuentas(cuentas);
            }
            Turnos turnos = jugador.getTurnos();
            if (turnos != null) {
                turnos = em.getReference(turnos.getClass(), turnos.getTurnosPK());
                jugador.setTurnos(turnos);
            }
            em.persist(jugador);
            if (cuentas != null) {
                Jugador oldJugadorOfCuentas = cuentas.getJugador();
                if (oldJugadorOfCuentas != null) {
                    oldJugadorOfCuentas.setCuentas(null);
                    oldJugadorOfCuentas = em.merge(oldJugadorOfCuentas);
                }
                cuentas.setJugador(jugador);
                cuentas = em.merge(cuentas);
            }
            if (turnos != null) {
                Jugador oldJugador1OfTurnos = turnos.getJugador1();
                if (oldJugador1OfTurnos != null) {
                    oldJugador1OfTurnos.setTurnos(null);
                    oldJugador1OfTurnos = em.merge(oldJugador1OfTurnos);
                }
                turnos.setJugador1(jugador);
                turnos = em.merge(turnos);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Jugador jugador) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Jugador persistentJugador = em.find(Jugador.class, jugador.getIdjugadores());
            Cuenta cuentasOld = persistentJugador.getCuentas();
            Cuenta cuentasNew = jugador.getCuentas();
            Turnos turnosOld = persistentJugador.getTurnos();
            Turnos turnosNew = jugador.getTurnos();
            List<String> illegalOrphanMessages = null;
            if (cuentasOld != null && !cuentasOld.equals(cuentasNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain Cuenta " + cuentasOld + " since its jugador field is not nullable.");
            }
            if (turnosOld != null && !turnosOld.equals(turnosNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain Turnos " + turnosOld + " since its jugador1 field is not nullable.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (cuentasNew != null) {
                cuentasNew = em.getReference(cuentasNew.getClass(), cuentasNew.getIdcuentas());
                jugador.setCuentas(cuentasNew);
            }
            if (turnosNew != null) {
                turnosNew = em.getReference(turnosNew.getClass(), turnosNew.getTurnosPK());
                jugador.setTurnos(turnosNew);
            }
            jugador = em.merge(jugador);
            if (cuentasNew != null && !cuentasNew.equals(cuentasOld)) {
                Jugador oldJugadorOfCuentas = cuentasNew.getJugador();
                if (oldJugadorOfCuentas != null) {
                    oldJugadorOfCuentas.setCuentas(null);
                    oldJugadorOfCuentas = em.merge(oldJugadorOfCuentas);
                }
                cuentasNew.setJugador(jugador);
                cuentasNew = em.merge(cuentasNew);
            }
            if (turnosNew != null && !turnosNew.equals(turnosOld)) {
                Jugador oldJugador1OfTurnos = turnosNew.getJugador1();
                if (oldJugador1OfTurnos != null) {
                    oldJugador1OfTurnos.setTurnos(null);
                    oldJugador1OfTurnos = em.merge(oldJugador1OfTurnos);
                }
                turnosNew.setJugador1(jugador);
                turnosNew = em.merge(turnosNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = jugador.getIdjugadores();
                if (findJugador(id) == null) {
                    throw new NonexistentEntityException("The jugador with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Jugador jugador;
            try {
                jugador = em.getReference(Jugador.class, id);
                jugador.getIdjugadores();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The jugador with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Cuenta cuentasOrphanCheck = jugador.getCuentas();
            if (cuentasOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Jugador (" + jugador + ") cannot be destroyed since the Cuenta " + cuentasOrphanCheck + " in its cuentas field has a non-nullable jugador field.");
            }
            Turnos turnosOrphanCheck = jugador.getTurnos();
            if (turnosOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Jugador (" + jugador + ") cannot be destroyed since the Turnos " + turnosOrphanCheck + " in its turnos field has a non-nullable jugador1 field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(jugador);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Jugador> findJugadorEntities() {
        return findJugadorEntities(true, -1, -1);
    }

    public List<Jugador> findJugadorEntities(int maxResults, int firstResult) {
        return findJugadorEntities(false, maxResults, firstResult);
    }

    private List<Jugador> findJugadorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Jugador.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Jugador findJugador(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Jugador.class, id);
        } finally {
            em.close();
        }
    }

    public int getJugadorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Jugador> rt = cq.from(Jugador.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
