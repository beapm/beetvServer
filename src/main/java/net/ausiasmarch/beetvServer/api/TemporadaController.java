/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.ausiasmarch.beetvServer.api;

import java.util.List;
import javax.servlet.http.HttpSession;
import net.ausiasmarch.beetvServer.entity.TemporadaEntity;
import net.ausiasmarch.beetvServer.entity.UsuarioEntity;
import net.ausiasmarch.beetvServer.repository.TemporadaRepository;
import net.ausiasmarch.beetvServer.repository.TipousuarioRepository;
import net.ausiasmarch.beetvServer.repository.UsuarioRepository;
import net.ausiasmarch.beetvServer.service.FillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/temporada")
public class TemporadaController {

    @Autowired
    HttpSession oHttpSession;

    @Autowired
    UsuarioRepository oUsuarioRepository;

    @Autowired
    TemporadaRepository oTemporadaRepository;

    @Autowired
    TipousuarioRepository oTipousuarioRepository;

    @Autowired
    FillService oFillService;

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable(value = "id") Long id) {

        if (oTemporadaRepository.existsById(id)) {
            return new ResponseEntity<TemporadaEntity>(oTemporadaRepository.getOne(id), HttpStatus.OK);
        } else {
            return new ResponseEntity<TemporadaEntity>(oTemporadaRepository.getOne(id), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> all() {
        if (oTemporadaRepository.count() <= 1000) {
            return new ResponseEntity<List<TemporadaEntity>>(oTemporadaRepository.findAll(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.PAYLOAD_TOO_LARGE);
        }
    }

    @GetMapping("/count")
    public ResponseEntity<?> count() {
        return new ResponseEntity<Long>(oTemporadaRepository.count(), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<?> create(@RequestBody TemporadaEntity oTemporadaEntity) { // solo puede el admin

        UsuarioEntity oUsuarioEntity = (UsuarioEntity) oHttpSession.getAttribute("usuario");

        if (oUsuarioEntity == null) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        } else {
            if (oUsuarioEntity.getTipousuario().getId() == 1) {

                if (oTemporadaEntity.getId() == null) {
                    return new ResponseEntity<TemporadaEntity>(oTemporadaRepository.save(oTemporadaEntity), HttpStatus.OK);
                } else {
                    return new ResponseEntity<Long>(0L, HttpStatus.NOT_MODIFIED);
                }
            } else {
                return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
            }
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody TemporadaEntity oTemporadaEntity) { // solo puede el admin

        UsuarioEntity oUsuarioEntity = (UsuarioEntity) oHttpSession.getAttribute("usuario");

        if (oUsuarioEntity == null) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        } else {
            if (oUsuarioEntity.getTipousuario().getId() == 1) {
                oTemporadaEntity.setId(id);
                if (oTemporadaRepository.existsById(id)) {
                    return new ResponseEntity<TemporadaEntity>(oTemporadaRepository.save(oTemporadaEntity), HttpStatus.OK);
                } else {
                    return new ResponseEntity<Long>(0L, HttpStatus.NOT_MODIFIED);
                }
            } else {
                return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
            }
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {  // solo puede el admin

        UsuarioEntity oUsuarioEntity = (UsuarioEntity) oHttpSession.getAttribute("usuario");

        if (oUsuarioEntity == null) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        } else {
            if (oUsuarioEntity.getTipousuario().getId() == 1) {
                if (oTemporadaRepository.existsById(id)) {
                    oTemporadaRepository.deleteById(id);
                    return new ResponseEntity<Long>(id, HttpStatus.NOT_MODIFIED);
                } else {
                    return new ResponseEntity<Long>(0L, HttpStatus.OK);
                }
            } else {
                return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
            }
        }
    }

    @GetMapping("/page")
    public ResponseEntity<?> getPage(@PageableDefault(page = 0, size = 10, direction = Direction.ASC) Pageable oPageable) {

        Page<TemporadaEntity> oPage = oTemporadaRepository.findAll(oPageable);
        return new ResponseEntity<Page<TemporadaEntity>>(oPage, HttpStatus.OK);

    }

    @PostMapping("/fill/{amount}")
    public ResponseEntity<?> fill(@PathVariable(value = "amount") Long amount) {

        UsuarioEntity oUsuarioEntity = (UsuarioEntity) oHttpSession.getAttribute("usuario");

        if (oUsuarioEntity == null) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        } else {
            if (oUsuarioEntity.getTipousuario().getId() == 1) { 
                return new ResponseEntity<Long>(oFillService.temporadaFill(amount), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
            }
        }

    }
}
