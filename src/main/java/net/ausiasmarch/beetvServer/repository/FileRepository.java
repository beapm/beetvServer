/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.ausiasmarch.beetvServer.repository;

import java.util.Optional;
import net.ausiasmarch.beetvServer.entity.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author beapm
 */

@Repository 
public interface FileRepository extends JpaRepository<FileEntity, Long> {
    
    Optional<FileEntity> findByName(String name);
    
}
