/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.ausiasmarch.beetvServer.repository;

import net.ausiasmarch.beetvServer.entity.PuntuacionserieEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PuntuacionserieRepository extends JpaRepository<PuntuacionserieEntity, Long> {

    @Query(value = "SELECT * FROM puntuacionserie p WHERE p.id_usuario= :id_usuario", nativeQuery = true)
    Page<PuntuacionserieEntity> findByPuntuacionXUsuario(Long id_usuario, Pageable pageable);

    @Query(value = "SELECT * FROM puntuacionserie p WHERE p.id_serie= :id_serie", nativeQuery = true)
    Page<PuntuacionserieEntity> findByPuntuacionXSerie(Long id_serie, Pageable pageable);
}
