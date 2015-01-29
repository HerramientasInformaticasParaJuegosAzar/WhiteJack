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
package casinoblackjack.negocio.cuentas;

import casinoblackjack.negocio.jugador.Jugador;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "cuentas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cuenta.findAll", query = "SELECT c FROM Cuenta c"),
    @NamedQuery(name = "Cuenta.findByIdcuentas", query = "SELECT c FROM Cuenta c WHERE c.idcuentas = :idcuentas"),
    @NamedQuery(name = "Cuenta.findBySaldo", query = "SELECT c FROM Cuenta c WHERE c.saldo = :saldo"),
    @NamedQuery(name = "Cuenta.findByActiva", query = "SELECT c FROM Cuenta c WHERE c.activa = :activa")})
public class Cuenta implements Serializable {
    @Basic(optional = false)
    @Column(name = "activa")
    private boolean activa;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcuentas")
    private Integer idcuentas;
    @Basic(optional = false)
    @Column(name = "saldo")
    private double saldo;
    @JoinColumn(name = "jugador", referencedColumnName = "idjugadores")
    @OneToOne(optional = false)
    private Jugador jugador;

    public Cuenta() {
    }

    public Cuenta(Integer idcuentas) {
        this.idcuentas = idcuentas;
    }

    public Cuenta(Integer idcuentas, double saldo, boolean activa) {
        this.idcuentas = idcuentas;
        this.saldo = saldo;
        this.activa = activa;
    }

    public Integer getIdcuentas() {
        return idcuentas;
    }

    public void setIdcuentas(Integer idcuentas) {
        this.idcuentas = idcuentas;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }


    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcuentas != null ? idcuentas.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cuenta)) {
            return false;
        }
        Cuenta other = (Cuenta) object;
        if ((this.idcuentas == null && other.idcuentas != null) || (this.idcuentas != null && !this.idcuentas.equals(other.idcuentas))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "casinoblackjack.negocio.cuentas.Cuentas[ idcuentas=" + idcuentas + " ]";
    }

    public boolean getActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }
    
}
