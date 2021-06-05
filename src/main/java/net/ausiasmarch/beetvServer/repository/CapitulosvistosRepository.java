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

    @Query(value = "select cv.id, cv.id_usuario, cv.id_capitulo, cv.visto from capitulosvistos cv inner join capitulo c on cv.id_capitulo=c.id "
            + "inner join temporada t on t.id=c.id_temporada inner join serie s on s.id=t.id_serie where cv.id_usuario=:id_usuario", nativeQuery = true)
    Page<CapitulosvistosEntity> findByCapitulosvistosXUsuario(Long id_usuario, Pageable pageable);
    
    @Query(value = "select cv.visto from capitulosvistos cv where cv.id_usuario=:id_usuario and cv.id_capitulo=:id_capitulo", nativeQuery = true)
    public Boolean checkCapitulosvistos (Long id_usuario, Long id_capitulo);
    
    @Query(value = "select count(*) from capitulosvistos cv where cv.id_usuario=:id_usuario and cv.id_capitulo=:id_capitulo", nativeQuery = true)
    public Integer existeCapitulosvistos (Long id_usuario, Long id_capitulo);

}
