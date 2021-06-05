/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.ausiasmarch.beetvServer.repository;

import net.ausiasmarch.beetvServer.entity.ContenidolistaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ContenidolistaRepository extends JpaRepository<ContenidolistaEntity, Long> {

    @Query(value = "SELECT * FROM contenidolista c WHERE c.id_serie= :id_serie", nativeQuery = true)
    Page<ContenidolistaEntity> findByContenidolistaXSerie(Long id_serie, Pageable pageable);

    @Query(value = "SELECT * FROM contenidolista c WHERE c.id_usuario= :id_usuario", nativeQuery = true)
    Page<ContenidolistaEntity> findByContenidolistaXUsuario(Long id_usuario, Pageable pageable);
    
    @Query(value = "SELECT * FROM contenidolista c WHERE c.id_lista = :id_lista", nativeQuery = true)
    Page<ContenidolistaEntity> findByContenidolistaXLista(Long id_lista, Pageable pageable);
}
