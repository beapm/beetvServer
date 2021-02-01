/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.ausiasmarch.beetvServer.repository;

import net.ausiasmarch.beetvServer.entity.ComentarioEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ComentarioRepository extends JpaRepository<ComentarioEntity, Long> {

    @Query(value = "SELECT * FROM comentario c WHERE c.id_usuario= :id_usuario", nativeQuery = true)
    Page<ComentarioEntity> findByComentarioXUsuario(Long id_usuario, Pageable pageable);

    @Query(value = "SELECT * FROM comentario c WHERE c.id_capitulo= :id_capitulo", nativeQuery = true)
    Page<ComentarioEntity> findByComentarioXCapitulo(Long id_capitulo, Pageable pageable);
}
