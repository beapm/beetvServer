package net.ausiasmarch.beetvServer.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "tipousuario")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
public class TipousuarioEntity implements Serializable {
    
     private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String nombre;
    
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @OneToMany(fetch=FetchType.LAZY,mappedBy="tipousuario", cascade={CascadeType.REFRESH})
    private List<UsuarioEntity> usuarios = new ArrayList<>();
    

    public TipousuarioEntity() {
    }

    public TipousuarioEntity(Long id_tipousuario) {
        this.id = id_tipousuario;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id_tipousuario) {
        this.id = id_tipousuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public int getUsuarios() {
        return usuarios.size();
    }
    
}
