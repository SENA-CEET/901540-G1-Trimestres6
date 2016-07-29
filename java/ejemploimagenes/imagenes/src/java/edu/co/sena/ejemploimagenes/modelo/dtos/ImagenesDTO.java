/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.co.sena.ejemploimagenes.modelo.dtos;

import edu.co.sena.ejemploimagenes.modelo.entities.*;
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
import org.primefaces.model.StreamedContent;

/**
 *
 * @author Enrique
 */

public class ImagenesDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer idimagenes;
    private String nombre;
    private StreamedContent imagen;

    public ImagenesDTO() {
    }

    public ImagenesDTO(Integer idimagenes) {
        this.idimagenes = idimagenes;
    }

    public ImagenesDTO(Integer idimagenes, StreamedContent imagen) {
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

    public StreamedContent getImagen() {
        return imagen;
    }

    public void setImagen(StreamedContent imagen) {
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
        if (!(object instanceof ImagenesDTO)) {
            return false;
        }
        ImagenesDTO other = (ImagenesDTO) object;
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
