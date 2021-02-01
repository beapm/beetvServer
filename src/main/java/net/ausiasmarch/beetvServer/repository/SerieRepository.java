/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.ausiasmarch.beetvServer.repository;

import net.ausiasmarch.beetvServer.entity.GeneroEntity;
import net.ausiasmarch.beetvServer.entity.SerieEntity;
import net.ausiasmarch.beetvServer.entity.UsuarioEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SerieRepository extends JpaRepository<SerieEntity, Long> {

    @Query(value = "SELECT * FROM serie s WHERE s.id_genero= :id_genero", nativeQuery = true)
    Page<SerieEntity> findBySerieXGenero(Long id_genero, Pageable pageable);
}
