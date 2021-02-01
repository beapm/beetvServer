/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.ausiasmarch.beetvServer.repository;

import net.ausiasmarch.beetvServer.entity.CapituloEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CapituloRepository extends JpaRepository<CapituloEntity, Long> {

    @Query(value = "SELECT * FROM capitulo c WHERE c.id_temporada= :id_temporada", nativeQuery = true)
    Page<CapituloEntity> findByCapituloXTemporada(Long id_temporada, Pageable pageable);
}
