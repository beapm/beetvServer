/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.ausiasmarch.beetvServer.api;

import java.util.List;
import javax.servlet.http.HttpSession;
import net.ausiasmarch.beetvServer.entity.LikesEntity;
import net.ausiasmarch.beetvServer.entity.UsuarioEntity;
import net.ausiasmarch.beetvServer.repository.LikesRepository;
import net.ausiasmarch.beetvServer.repository.TipousuarioRepository;
import net.ausiasmarch.beetvServer.repository.UsuarioRepository;
import net.ausiasmarch.beetvServer.service.FillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
@RequestMapping("/likes")
public class LikesController {

    @Autowired
    HttpSession oHttpSession;

    @Autowired
    UsuarioRepository oUsuarioRepository;

    @Autowired
    TipousuarioRepository oTipousuarioRepository;

    @Autowired
    LikesRepository oLikesRepository;

    @Autowired
    FillService oFillService;

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable(value = "id") Long id) {

        UsuarioEntity oUsuarioEntity = (UsuarioEntity) oHttpSession.getAttribute("usuario");
        LikesEntity oLikesEntity = new LikesEntity();

        if (oUsuarioEntity == null) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        } else {
            if (oUsuarioEntity.getTipousuario().getId() == 1) { //administrador
                if (oLikesRepository.existsById(id)) {
                    return new ResponseEntity<LikesEntity>(oLikesRepository.getOne(id), HttpStatus.OK);
                } else {
                    return new ResponseEntity<LikesEntity>(oLikesRepository.getOne(id), HttpStatus.NOT_FOUND);
                }
            } else {  //usuario registrado
                oLikesEntity = oLikesRepository.getOne(id);
                if (oLikesEntity.getUsuario().getId().equals(oUsuarioEntity.getId())) {  // id_usuario coincide con el id del usuario que tiene la sesión activa
                    return new ResponseEntity<LikesEntity>(oLikesRepository.getOne(id), HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
                }
            }
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> all() {

        UsuarioEntity oUsuarioEntity = (UsuarioEntity) oHttpSession.getAttribute("usuario");

        if (oUsuarioEntity == null) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        } else {
            if (oUsuarioEntity.getTipousuario().getId() == 1) {
                if (oLikesRepository.count() <= 1000) {
                    return new ResponseEntity<List<LikesEntity>>(oLikesRepository.findAll(), HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(null, HttpStatus.PAYLOAD_TOO_LARGE);
                }
            } else {
                return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
            }
        }
    }

    @GetMapping("/count")
    public ResponseEntity<?> count() {

        UsuarioEntity oUsuarioEntity = (UsuarioEntity) oHttpSession.getAttribute("usuario");

        if (oUsuarioEntity == null) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        } else {
            if (oUsuarioEntity.getTipousuario().getId() == 1) {
        return new ResponseEntity<Long>(oLikesRepository.count(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
            }
        }
    }

    @PostMapping("/")
    public ResponseEntity<?> create(@RequestBody LikesEntity oLikesEntity) { // solo puede el admin

        UsuarioEntity oUsuarioEntity = (UsuarioEntity) oHttpSession.getAttribute("usuario");

        if (oUsuarioEntity == null) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        } else {
            if (oUsuarioEntity.getTipousuario().getId() == 1) {

                if (oLikesEntity.getId() == null) {
                    return new ResponseEntity<LikesEntity>(oLikesRepository.save(oLikesEntity), HttpStatus.OK);
                } else {
                    return new ResponseEntity<Long>(0L, HttpStatus.NOT_MODIFIED);
                }
            } else {
                return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
            }
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody LikesEntity oLikesEntity) { // solo puede el admin

        UsuarioEntity oUsuarioEntity = (UsuarioEntity) oHttpSession.getAttribute("usuario");

        if (oUsuarioEntity == null) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        } else {
            if (oUsuarioEntity.getTipousuario().getId() == 1) {
                oLikesEntity.setId(id);
                if (oLikesRepository.existsById(id)) {
                    return new ResponseEntity<LikesEntity>(oLikesRepository.save(oLikesEntity), HttpStatus.OK);
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
                if (oLikesRepository.existsById(id)) {
                    oLikesRepository.deleteById(id);
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
    public ResponseEntity<?> getPage(@PageableDefault(page = 0, size = 10, direction = Sort.Direction.ASC) Pageable oPageable) {

        Page<LikesEntity> oPage = oLikesRepository.findAll(oPageable);
        return new ResponseEntity<Page<LikesEntity>>(oPage, HttpStatus.OK);

    }

    @PostMapping("/fill/{amount}")
    public ResponseEntity<?> fill(@PathVariable(value = "amount") Long amount) {

        UsuarioEntity oUsuarioEntity = (UsuarioEntity) oHttpSession.getAttribute("usuario");

        if (oUsuarioEntity == null) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        } else {
            if (oUsuarioEntity.getTipousuario().getId() == 1) {
                return new ResponseEntity<Long>(oFillService.likesFill(amount), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
            }
        }

    }

}
