package net.ausiasmarch.beetvServer.api;

import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpSession;
import net.ausiasmarch.beetvServer.entity.TipousuarioEntity;
import net.ausiasmarch.beetvServer.entity.UsuarioEntity;
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
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    HttpSession oHttpSession;

    @Autowired
    UsuarioRepository oUsuarioRepository;

    @Autowired
    TipousuarioRepository oTipousuarioRepository;

    @Autowired
    FillService oFillService;

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable(value = "id") Long id) {

        UsuarioEntity oUsuarioEntity = (UsuarioEntity) oHttpSession.getAttribute("usuario");

        if (oUsuarioEntity == null) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        } else {
            if (oUsuarioEntity.getTipousuario().getId() == 1) { //administrador
                if (oUsuarioRepository.existsById(id)) {
                    return new ResponseEntity<UsuarioEntity>(oUsuarioRepository.getOne(id), HttpStatus.OK);
                } else {
                    return new ResponseEntity<UsuarioEntity>(oUsuarioRepository.getOne(id), HttpStatus.NOT_FOUND);
                }
            } else {  //cliente
                if (id.equals(oUsuarioEntity.getId())) {  //los datos pedidos por el cliente son sus propios datos?
                    return new ResponseEntity<UsuarioEntity>(oUsuarioRepository.getOne(id), HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
                }
            }
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> get() {

        UsuarioEntity oUsuarioEntity = (UsuarioEntity) oHttpSession.getAttribute("usuario");

        if (oUsuarioEntity == null) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        } else {

            if (oUsuarioEntity.getTipousuario().getId() == 1) {
                if (oUsuarioRepository.count() <= 1000) {
                    return new ResponseEntity<List<UsuarioEntity>>(oUsuarioRepository.findAll(), HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(null, HttpStatus.PAYLOAD_TOO_LARGE);
                }
            } else {
                return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
            }
        }
    }

    @PostMapping("/")
    public ResponseEntity<?> create(@RequestBody UsuarioEntity oUsuarioEntity) {

        TipousuarioEntity oTipousuarioEntity = new TipousuarioEntity();
        oTipousuarioEntity.setId(2L);
        oUsuarioEntity.setTipousuario(oTipousuarioEntity);
        return new ResponseEntity<UsuarioEntity>(oUsuarioRepository.save(oUsuarioEntity), HttpStatus.OK);
    }

    @GetMapping("/count")
    public ResponseEntity<?> count() {

        UsuarioEntity oUsuarioEntity = (UsuarioEntity) oHttpSession.getAttribute("usuario");

        if (oUsuarioEntity == null) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

        } else {

            if (oUsuarioEntity.getTipousuario().getId() == 1) { //administrador

                return new ResponseEntity<Long>(oUsuarioRepository.count(), HttpStatus.OK);

            } else {  //cliente

                return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
            }
        }
    }

    @PostMapping("/fill/{amount}")
    public ResponseEntity<?> fill(@PathVariable(value = "amount") Long amount) {

        UsuarioEntity oUsuarioEntity = (UsuarioEntity) oHttpSession.getAttribute("usuario");
        if (oUsuarioEntity == null) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        } else {
            if (oUsuarioEntity.getTipousuario().getId() == 1) {
                return new ResponseEntity<Long>(oFillService.usuarioFill(amount), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
            }
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {

        UsuarioEntity oUsuarioEntity = (UsuarioEntity) oHttpSession.getAttribute("usuario");

        if (oUsuarioEntity == null) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        } else {
            if (oUsuarioEntity.getTipousuario().getId() == 1) { //administrador
                oUsuarioRepository.deleteById(id);
                if (oUsuarioRepository.existsById(id)) {
                    return new ResponseEntity<Long>(id, HttpStatus.NOT_MODIFIED);
                } else {
                    return new ResponseEntity<Long>(0L, HttpStatus.OK);
                }
            } else {  //cliente
                return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
            }
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody UsuarioEntity oUsuarioEntity) {

        UsuarioEntity oUsuarioEntity2 = (UsuarioEntity) oHttpSession.getAttribute("usuario"); // para ver si ingresas como admin o cliente

        if (oUsuarioEntity2 == null) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        } else {
            if (oUsuarioEntity2.getTipousuario().getId() == 1) { //administrador
                if (oUsuarioRepository.existsById(id)) {
                    UsuarioEntity oUsuarioEntity3 = oUsuarioRepository.getOne(id);
                    oUsuarioEntity.setPassword(oUsuarioEntity3.getPassword());
                    oUsuarioEntity.setToken(oUsuarioEntity3.getToken());
                    oUsuarioEntity.setActivo(oUsuarioEntity3.isActivo());
                    oUsuarioEntity.setValidado(oUsuarioEntity3.isValidado());
                    return new ResponseEntity<UsuarioEntity>(oUsuarioRepository.save(oUsuarioEntity), HttpStatus.OK);
                } else {
                    return new ResponseEntity<UsuarioEntity>(oUsuarioRepository.getOne(id), HttpStatus.NOT_FOUND);
                }
            } else {  //cliente
                if (oUsuarioEntity2.getId() == id) {
                    UsuarioEntity oUsuarioEntity3 = oUsuarioRepository.getOne(id);
                    oUsuarioEntity.setPassword(oUsuarioEntity3.getPassword());
                    oUsuarioEntity.setToken(oUsuarioEntity3.getToken());
                    oUsuarioEntity.setActivo(oUsuarioEntity3.isActivo());
                    oUsuarioEntity.setValidado(oUsuarioEntity3.isValidado());
                    return new ResponseEntity<UsuarioEntity>(oUsuarioRepository.save(oUsuarioEntity), HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
                }
            }
        }
    }

    @GetMapping("/page")
    public ResponseEntity<?> getPage(@PageableDefault(page = 0, size = 10, direction = Direction.ASC) Pageable oPageable) {

        UsuarioEntity oUsuarioEntity = (UsuarioEntity) oHttpSession.getAttribute("usuario");

        Page<UsuarioEntity> oPage = oUsuarioRepository.findAll(oPageable);

        if (oUsuarioEntity == null) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        } else {
            if (oUsuarioEntity.getTipousuario().getId() == 1) { //administrador
                return new ResponseEntity<Page<UsuarioEntity>>(oPage, HttpStatus.OK);
            } else {  //cliente
                return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
            }

        }
    }

    @GetMapping("/page/tipousuario/{id}")
    public ResponseEntity<?> getPageXTipousuario(@PageableDefault(page = 0, size = 10, direction = Direction.ASC) Pageable oPageable, @PathVariable(value = "id") Long id) {

        if (oTipousuarioRepository.existsById(id)) {
            TipousuarioEntity oTipousuarioEntity = oTipousuarioRepository.getOne(id);
            Page<UsuarioEntity> oPage = oUsuarioRepository.findByTipousuario(oTipousuarioEntity, oPageable);
            return new ResponseEntity<Page<UsuarioEntity>>(oPage, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.OK);
        }

    }
}
