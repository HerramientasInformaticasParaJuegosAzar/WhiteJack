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
package casinoblackjack.negocio.jugador;

import casinoblackjack.negocio.cuentas.Cuenta;
import casinoblackjack.negocio.turno.Turnos;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import casinoblackjack.negocio.cartas.Carta;

/**
 *
 * @author Krnx
 */
@Entity
@Table(name = "jugadores")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Jugador.findAll", query = "SELECT j FROM Jugador j"),
    @NamedQuery(name = "Jugador.findByIdjugadores", query = "SELECT j FROM Jugador j WHERE j.idjugadores = :idjugadores"),
    
    @NamedQuery(name = "Jugador.findByPassword", query = "SELECT j FROM Jugador j WHERE j.password = :password"),
    @NamedQuery(name = "Jugador.findByFechaRegistro", query = "SELECT j FROM Jugador j WHERE j.fechaRegistro = :fechaRegistro"),
    @NamedQuery(name = "Jugador.findByActivo", query = "SELECT j FROM Jugador j WHERE j.activo = :activo")})
public class Jugador implements Serializable {
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "jugador1")
    private Turnos turnos;
    @Basic(optional = false)
    @Column(name = "activo")
    private boolean activo;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "jugador")
    private Cuenta cuentas;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idjugadores")
    private Integer idjugadores;
    @Basic(optional = false)
    @Column(name = "usuario")
    private String usuario;
    @Basic(optional = false)
    @Column(name = "password")
    private String password;
    @Basic(optional = true)
    @Column(name = "fechaRegistro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    

    public Jugador() {
    }

    public Jugador(Integer idjugadores) {
        this.idjugadores = idjugadores;
    }

    public Jugador(Integer idjugadores, String usuario, String password, Date fechaRegistro, boolean activo) {
        this.idjugadores = idjugadores;
        this.usuario = usuario;
        this.password = password;
        this.fechaRegistro = fechaRegistro;
        this.activo = activo;
    }

    public Integer getIdjugadores() {
        return idjugadores;
    }

    public void setIdjugadores(Integer idjugadores) {
        this.idjugadores = idjugadores;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }


    public Cuenta getCuentas() {
        return cuentas;
    }

    public void setCuentas(Cuenta cuentas) {
        this.cuentas = cuentas;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idjugadores != null ? idjugadores.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Jugador)) {
            return false;
        }
        Jugador other = (Jugador) object;
        if ((this.idjugadores == null && other.idjugadores != null) || (this.idjugadores != null && !this.idjugadores.equals(other.idjugadores))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "casinoblackjack.negocio.banca.Jugadores[ idjugadores=" + idjugadores + " ]";
    }

    public boolean getActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Turnos getTurnos() {
        return turnos;
    }

    public void setTurnos(Turnos turnos) {
        this.turnos = turnos;
    }

   
    
  
}
