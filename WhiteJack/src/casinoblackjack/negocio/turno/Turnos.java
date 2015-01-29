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

import casinoblackjack.negocio.jugador.Jugador;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Krnx
 */
@Entity
@Table(name = "turnos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Turnos.findAll", query = "SELECT t FROM Turnos t"),
    @NamedQuery(name = "Turnos.findByIdturnos", query = "SELECT t FROM Turnos t WHERE t.turnosPK.idturnos = :idturnos"),
    @NamedQuery(name = "Turnos.findByJugador", query = "SELECT t FROM Turnos t WHERE t.turnosPK.jugador = :jugador"),
    @NamedQuery(name = "Turnos.findByResultado", query = "SELECT t FROM Turnos t WHERE t.resultado = :resultado")})
public class Turnos implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TurnosPK turnosPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "resultado")
    private Double resultado;
    @JoinColumn(name = "jugador", referencedColumnName = "idjugadores", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Jugador jugador1;

    public Turnos() {
    }

    public Turnos(TurnosPK turnosPK) {
        this.turnosPK = turnosPK;
    }

    public Turnos(int idturnos, int jugador) {
        this.turnosPK = new TurnosPK(idturnos, jugador);
    }

    public TurnosPK getTurnosPK() {
        return turnosPK;
    }

    public void setTurnosPK(TurnosPK turnosPK) {
        this.turnosPK = turnosPK;
    }

    public Double getResultado() {
        return resultado;
    }

    public void setResultado(Double resultado) {
        this.resultado = resultado;
    }

    public Jugador getJugador1() {
        return jugador1;
    }

    public void setJugador1(Jugador jugador1) {
        this.jugador1 = jugador1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (turnosPK != null ? turnosPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Turnos)) {
            return false;
        }
        Turnos other = (Turnos) object;
        if ((this.turnosPK == null && other.turnosPK != null) || (this.turnosPK != null && !this.turnosPK.equals(other.turnosPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "casinoblackjack.negocio.turno.Turnos[ turnosPK=" + turnosPK + " ]";
    }
    
}
