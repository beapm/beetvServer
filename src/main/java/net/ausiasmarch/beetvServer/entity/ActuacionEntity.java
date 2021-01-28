/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.ausiasmarch.beetvServer.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "actuacion")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ActuacionEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "id_capitulo")
    private CapituloEntity capitulo;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "id_personaje")
    private PersonajeEntity personaje;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CapituloEntity getCapitulo() {
        return capitulo;
    }

    public void setCapitulo(CapituloEntity capitulo) {
        this.capitulo = capitulo;
    }

    public PersonajeEntity getPersonaje() {
        return personaje;
    }

    public void setPersonaje(PersonajeEntity personaje) {
        this.personaje = personaje;
    }
}
