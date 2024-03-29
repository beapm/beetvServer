package net.ausiasmarch.beetvServer.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "usuario")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class UsuarioEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private String email;
    private String login;

    private String password;

    @JsonIgnore
        @JsonProperty(access =Access.READ_ONLY)
    private String token;

    @JsonIgnore
        @JsonProperty(access =Access.READ_ONLY)
    private boolean validado;

    @JsonIgnore
        @JsonProperty(access =Access.READ_ONLY)
    private boolean activo;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "id_tipousuario")
    @JsonProperty(access =Access.READ_ONLY)
    private TipousuarioEntity tipousuario;

    @JsonProperty(access = Access.READ_ONLY)
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario", cascade = {CascadeType.REFRESH})
    private List<ListaEntity> listas = new ArrayList<>();

    @JsonProperty(access = Access.READ_ONLY)
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario", cascade = {CascadeType.REFRESH})
    private List<PuntuacionserieEntity> puntuaciones = new ArrayList<>();

    @JsonProperty(access = Access.READ_ONLY)
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario", cascade = {CascadeType.REFRESH})
    private List<CapitulosvistosEntity> capitulosvistos = new ArrayList<>();

    private Long id_file;

    public UsuarioEntity() {
    }

    public UsuarioEntity(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isValidado() {
        return validado;
    }

    public void setValidado(boolean validado) {
        this.validado = validado;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public TipousuarioEntity getTipousuario() {
        return tipousuario;
    }

    public void setTipousuario(TipousuarioEntity tipousuario) {
        this.tipousuario = tipousuario;
    }

    public Long getId_file() {
        return id_file;
    }

    public void setId_file(Long id_file) {
        this.id_file = id_file;
    }

    public int getListas() {
        return listas.size();
    }

    public int getPuntuaciones() {
        return puntuaciones.size();
    }

    public int getCapitulosvistos() {
        return capitulosvistos.size();
    }
}
