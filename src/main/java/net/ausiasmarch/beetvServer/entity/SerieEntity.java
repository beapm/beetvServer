/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.ausiasmarch.beetvServer.entity;

import java.time.LocalDateTime;

/**
 *
 * @author beapm
 */
public class SerieEntity {
    
    private Long id_serie;
    private String nombre;
    private String sinopsis_serie;
    private String idioma;
    private int duracion_media;
    private LocalDateTime fecha_inicio;
    private LocalDateTime fecha_fin;
    private Long id_genero;
    private Long id_file;
    
}
