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

package casinoblackjack.negocio.jugador;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author usuario_local
 */
@Entity
@Table(name = "jugador")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Jugador_1.findAll", query = "SELECT j FROM Jugador_1 j"),
    @NamedQuery(name = "Jugador_1.findByIdjugador", query = "SELECT j FROM Jugador_1 j WHERE j.idjugador = :idjugador"),
    @NamedQuery(name = "Jugador_1.findByUsuario", query = "SELECT j FROM Jugador_1 j WHERE j.usuario = :usuario"),
    @NamedQuery(name = "Jugador_1.findByPassword", query = "SELECT j FROM Jugador_1 j WHERE j.password = :password"),
    @NamedQuery(name = "Jugador_1.findByActivo", query = "SELECT j FROM Jugador_1 j WHERE j.activo = :activo")})

public class Jugador
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idjugador")
    private Integer idjugador;
    @Basic(optional = false)
    @Column(name = "usuario")
    private String usuario;
    @Basic(optional = false)
    @Column(name = "password")
    private String password;
    @Basic(optional = false)
    @Column(name = "activo")
    private boolean activo;

    public Jugador() {
    }

    public Jugador(Integer idjugador) {
        this.idjugador = idjugador;
    }

    public Jugador(Integer idjugador, String usuario, String password, boolean activo) {
        this.idjugador = idjugador;
        this.usuario = usuario;
        this.password = password;
        this.activo = activo;
    }

    public Integer getIdjugador() {
        return idjugador;
    }

    public void setIdjugador(Integer idjugador) {
        this.idjugador = idjugador;
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

    public boolean getActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idjugador != null ? idjugador.hashCode() : 0);
        return hash;
    }


    @Override
    public String toString() {
        return "casinoblackjack.negocio.jugador.Jugador_1[ idjugador=" + idjugador + " ]";
    }
    
    
}
