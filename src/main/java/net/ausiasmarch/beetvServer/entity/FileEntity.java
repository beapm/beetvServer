package net.ausiasmarch.beetvServer.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.awt.image.BufferedImage;
import java.sql.Blob;

@Entity
@Table(name = "file")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class FileEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_file")
    private Long id_file;

    @Column(name = "nombre")
    private String nombre;
    @Column(name = "tipo")
    private String tipo;
    //image bytes can have large lengths so we specify a value
    //which is more than the default length for picByte column
    @Column(name = "file")
    //private byte[] file;
    private Blob file;

    public FileEntity() {
    }

    public FileEntity(String name, String type) {
        this.nombre = nombre;
        this.tipo = tipo;
    }

    public Long getId_File() {
        return id_file;
    }

    public void setId_File(Long id_file) {
        this.id_file = id_file;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String type) {
        this.tipo = tipo;
    }

    public Blob getFile() {
        return file;
    }

    public void setFile(Blob fileBlob) {
        this.file = fileBlob;
    }



}
