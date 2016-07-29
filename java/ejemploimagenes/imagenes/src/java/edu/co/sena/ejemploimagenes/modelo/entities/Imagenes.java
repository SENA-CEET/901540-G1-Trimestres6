/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.co.sena.ejemploimagenes.modelo.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Enrique
 */
@Entity
@Table(name = "imagenes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Imagenes.findAll", query = "SELECT i FROM Imagenes i"),
    @NamedQuery(name = "Imagenes.findByIdimagenes", query = "SELECT i FROM Imagenes i WHERE i.idimagenes = :idimagenes"),
    @NamedQuery(name = "Imagenes.findByNombre", query = "SELECT i FROM Imagenes i WHERE i.nombre = :nombre")})
public class Imagenes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idimagenes")
    private Integer idimagenes;
    @Size(max = 45)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Column(name = "imagen")
    private byte[] imagen;

    public Imagenes() {
    }

    public Imagenes(Integer idimagenes) {
        this.idimagenes = idimagenes;
    }

    public Imagenes(Integer idimagenes, byte[] imagen) {
        this.idimagenes = idimagenes;
        this.imagen = imagen;
    }

    public Integer getIdimagenes() {
        return idimagenes;
    }

    public void setIdimagenes(Integer idimagenes) {
        this.idimagenes = idimagenes;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idimagenes != null ? idimagenes.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Imagenes)) {
            return false;
        }
        Imagenes other = (Imagenes) object;
        if ((this.idimagenes == null && other.idimagenes != null) || (this.idimagenes != null && !this.idimagenes.equals(other.idimagenes))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.co.sena.ejemploimagenes.modelo.entities.Imagenes[ idimagenes=" + idimagenes + " ]";
    }
    
}
