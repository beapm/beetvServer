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
public class TemporadaEntity {
    
    private Long id_temporada;	
    private Long id_serie;
    private LocalDateTime fecha_inicio;
    private LocalDateTime fecha_fin;
    private Long id_file;
}
