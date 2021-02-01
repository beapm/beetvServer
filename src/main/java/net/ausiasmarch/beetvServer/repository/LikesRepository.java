/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.ausiasmarch.beetvServer.repository;

import net.ausiasmarch.beetvServer.entity.LikesEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LikesRepository extends JpaRepository<LikesEntity, Long> {

    @Query(value = "SELECT * FROM likes l WHERE l.id_usuario= :id_usuario", nativeQuery = true)
    Page<LikesEntity> findByLikesXUsuario(Long id_usuario, Pageable pageable);

    @Query(value = "SELECT * FROM likes l WHERE l.id_capitulo= :id_capitulo", nativeQuery = true)
    Page<LikesEntity> findByLikesXCapitulo(Long id_capitulo, Pageable pageable);
}
