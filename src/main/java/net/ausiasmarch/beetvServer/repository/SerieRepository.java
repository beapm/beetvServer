/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.ausiasmarch.beetvServer.repository;

import java.util.List;
import net.ausiasmarch.beetvServer.entity.SerieEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SerieRepository extends JpaRepository<SerieEntity, Long> {

    @Query(value = "SELECT * FROM serie s WHERE s.id_genero= :id_genero", nativeQuery = true)
    Page<SerieEntity> findBySerieXGenero(Long id_genero, Pageable pageable);

    @Query(value = "select (sum(puntuacion)/(select count(*) FROM puntuacionserie p WHERE p.id_serie= :id_serie)) from puntuacionserie p where p.id_serie= :id_serie", nativeQuery = true)
    public double puntuacionMedia(Long id_serie);

    @Query(value = "select s.nombre, sum(puntuacion)/count(puntuacion) as 'media' FROM puntuacionserie p inner join serie s on p.id_serie=s.id group by id_serie order by media desc", nativeQuery = true)
    public List<SerieEntity> seriesMasPuntuadas();
}
