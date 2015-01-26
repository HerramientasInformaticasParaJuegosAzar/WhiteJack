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
package casinoblackjack.negocio.turno;

import casinoblackjack.exceptions.IllegalOrphanException;
import casinoblackjack.exceptions.NonexistentEntityException;
import casinoblackjack.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import casinoblackjack.negocio.jugador.Jugador;
import casinoblackjack.negocio.turno.Turnos;
import casinoblackjack.negocio.turno.TurnosPK;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Krnx
 */
public class TurnosJpaController implements Serializable {

    public TurnosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Turnos turnos) throws IllegalOrphanException, PreexistingEntityException, Exception {
        if (turnos.getTurnosPK() == null) {
            turnos.setTurnosPK(new TurnosPK());
        }
        turnos.getTurnosPK().setJugador(turnos.getJugador1().getIdjugadores());
        List<String> illegalOrphanMessages = null;
        Jugador jugador1OrphanCheck = turnos.getJugador1();
        if (jugador1OrphanCheck != null) {
            Turnos oldTurnosOfJugador1 = jugador1OrphanCheck.getTurnos();
            if (oldTurnosOfJugador1 != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The Jugador " + jugador1OrphanCheck + " already has an item of type Turnos whose jugador1 column cannot be null. Please make another selection for the jugador1 field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Jugador jugador1 = turnos.getJugador1();
            if (jugador1 != null) {
                jugador1 = em.getReference(jugador1.getClass(), jugador1.getIdjugadores());
                turnos.setJugador1(jugador1);
            }
            em.persist(turnos);
            if (jugador1 != null) {
                jugador1.setTurnos(turnos);
                jugador1 = em.merge(jugador1);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTurnos(turnos.getTurnosPK()) != null) {
                throw new PreexistingEntityException("Turnos " + turnos + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Turnos turnos) throws IllegalOrphanException, NonexistentEntityException, Exception {
        turnos.getTurnosPK().setJugador(turnos.getJugador1().getIdjugadores());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Turnos persistentTurnos = em.find(Turnos.class, turnos.getTurnosPK());
            Jugador jugador1Old = persistentTurnos.getJugador1();
            Jugador jugador1New = turnos.getJugador1();
            List<String> illegalOrphanMessages = null;
            if (jugador1New != null && !jugador1New.equals(jugador1Old)) {
                Turnos oldTurnosOfJugador1 = jugador1New.getTurnos();
                if (oldTurnosOfJugador1 != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The Jugador " + jugador1New + " already has an item of type Turnos whose jugador1 column cannot be null. Please make another selection for the jugador1 field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (jugador1New != null) {
                jugador1New = em.getReference(jugador1New.getClass(), jugador1New.getIdjugadores());
                turnos.setJugador1(jugador1New);
            }
            turnos = em.merge(turnos);
            if (jugador1Old != null && !jugador1Old.equals(jugador1New)) {
                jugador1Old.setTurnos(null);
                jugador1Old = em.merge(jugador1Old);
            }
            if (jugador1New != null && !jugador1New.equals(jugador1Old)) {
                jugador1New.setTurnos(turnos);
                jugador1New = em.merge(jugador1New);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                TurnosPK id = turnos.getTurnosPK();
                if (findTurnos(id) == null) {
                    throw new NonexistentEntityException("The turnos with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(TurnosPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Turnos turnos;
            try {
                turnos = em.getReference(Turnos.class, id);
                turnos.getTurnosPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The turnos with id " + id + " no longer exists.", enfe);
            }
            Jugador jugador1 = turnos.getJugador1();
            if (jugador1 != null) {
                jugador1.setTurnos(null);
                jugador1 = em.merge(jugador1);
            }
            em.remove(turnos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Turnos> findTurnosEntities() {
        return findTurnosEntities(true, -1, -1);
    }

    public List<Turnos> findTurnosEntities(int maxResults, int firstResult) {
        return findTurnosEntities(false, maxResults, firstResult);
    }

    private List<Turnos> findTurnosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Turnos.class));
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

    public Turnos findTurnos(TurnosPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Turnos.class, id);
        } finally {
            em.close();
        }
    }

    public int getTurnosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Turnos> rt = cq.from(Turnos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
