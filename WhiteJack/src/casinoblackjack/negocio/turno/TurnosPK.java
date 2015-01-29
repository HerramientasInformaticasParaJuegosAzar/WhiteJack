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

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Krnx
 */
@Embeddable
public class TurnosPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "idturnos")
    private int idturnos;
    @Basic(optional = false)
    @Column(name = "jugador")
    private int jugador;

    public TurnosPK() {
    }

    public TurnosPK(int idturnos, int jugador) {
        this.idturnos = idturnos;
        this.jugador = jugador;
    }
    public TurnosPK(int jugador) 
    {
        this.jugador = jugador;
    }

    public int getIdturnos() {
        return idturnos;
    }

    public void setIdturnos(int idturnos) {
        this.idturnos = idturnos;
    }

    public int getJugador() {
        return jugador;
    }

    public void setJugador(int jugador) {
        this.jugador = jugador;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idturnos;
        hash += (int) jugador;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TurnosPK)) {
            return false;
        }
        TurnosPK other = (TurnosPK) object;
        if (this.idturnos != other.idturnos) {
            return false;
        }
        if (this.jugador != other.jugador) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "casinoblackjack.negocio.turno.TurnosPK[ idturnos=" + idturnos + ", jugador=" + jugador + " ]";
    }
    
}
