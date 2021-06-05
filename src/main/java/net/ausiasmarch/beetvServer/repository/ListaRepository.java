/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.ausiasmarch.beetvServer.repository;

import net.ausiasmarch.beetvServer.entity.ListaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author beapm
 */

@Repository
public interface ListaRepository extends JpaRepository<ListaEntity, Long>  {
    
    @Query(value = "SELECT * FROM lista l WHERE l.id_usuario= :id_usuario", nativeQuery = true)
    Page<ListaEntity> findByListaXUsuario(Long id_usuario, Pageable pageable);
}
