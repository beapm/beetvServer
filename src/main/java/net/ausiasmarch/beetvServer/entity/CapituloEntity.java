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
public class CapituloEntity {
    
    private Long id_capitulo;
    private String nombre;
    private String sinopsis_capitulo;
    private LocalDateTime fecha_emision;
    private int duracion;
    private Long id_temporada;
    private Long id_file;
    
}
