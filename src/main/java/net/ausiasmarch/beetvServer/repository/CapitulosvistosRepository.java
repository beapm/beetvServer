/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.ausiasmarch.beetvServer.repository;

import net.ausiasmarch.beetvServer.entity.CapitulosvistosEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CapitulosvistosRepository extends JpaRepository<CapitulosvistosEntity, Long> {

    @Query(value = "SELECT * FROM capitulosvistos c WHERE c.id_usuario= :id_usuario", nativeQuery = true)
    Page<CapitulosvistosEntity> findByCapitulosvistosXUsuario(Long id_usuario, Pageable pageable);

}
