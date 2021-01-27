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
@Table(name = "likes")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class LikesEntity implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private Boolean like_type;
    
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "id_usuario")
    private UsuarioEntity usuario;
    
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "id_comentario")
    private ComentarioEntity comentario;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getLike_type() {
        return like_type;
    }

    public void setLike_type(Boolean like_type) {
        this.like_type = like_type;
    }

    public UsuarioEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }

    public ComentarioEntity getComentario() {
        return comentario;
    }

    public void setComentario(ComentarioEntity comentario) {
        this.comentario = comentario;
    }
    
}
