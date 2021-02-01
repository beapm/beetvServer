/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.ausiasmarch.beetvServer.repository;

import net.ausiasmarch.beetvServer.entity.SerieEntity;
import net.ausiasmarch.beetvServer.entity.TemporadaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TemporadaRepository extends JpaRepository<TemporadaEntity, Long> {

    @Query(value = "SELECT * FROM temporada t WHERE t.id_serie= :id_serie", nativeQuery = true)
    Page<TemporadaEntity> findByTemporadaXSerie(Long id_serie, Pageable pageable);
}
