package net.ausiasmarch.beetvServer.api;

import java.util.List;
import javax.servlet.http.HttpSession;
import net.ausiasmarch.beetvServer.entity.ListaEntity;
import net.ausiasmarch.beetvServer.entity.UsuarioEntity;
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
import net.ausiasmarch.beetvServer.repository.ListaRepository;

@RestController
@RequestMapping("/lista")
public class ListaController {

    @Autowired
    HttpSession oHttpSession;

    @Autowired
    UsuarioRepository oUsuarioRepository;

    @Autowired
    TipousuarioRepository oTipousuarioRepository;

    @Autowired
    ListaRepository oListaseriesRepository;

    @Autowired
    FillService oFillService;

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable(value = "id") Long id) {

        UsuarioEntity oUsuarioEntity = (UsuarioEntity) oHttpSession.getAttribute("usuario");
        ListaEntity oListaseriesEntity = new ListaEntity();

        if (oUsuarioEntity == null) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        } else {
            if (oUsuarioEntity.getTipousuario().getId() == 1) { //administrador
                if (oListaseriesRepository.existsById(id)) {
                    return new ResponseEntity<ListaEntity>(oListaseriesRepository.getOne(id), HttpStatus.OK);
                } else {
                    return new ResponseEntity<ListaEntity>(oListaseriesRepository.getOne(id), HttpStatus.NOT_FOUND);
                }
            } else {  //usuario registrado
                oListaseriesEntity = oListaseriesRepository.getOne(id);
                if (oListaseriesEntity.getUsuario().getId().equals(oUsuarioEntity.getId())) {  // id_usuario coincide con el id del usuario que tiene la sesión activa
                    return new ResponseEntity<ListaEntity>(oListaseriesRepository.getOne(id), HttpStatus.OK);
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
                if (oListaseriesRepository.count() <= 1000) {
                    return new ResponseEntity<List<ListaEntity>>(oListaseriesRepository.findAll(), HttpStatus.OK);
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
                return new ResponseEntity<Long>(oListaseriesRepository.count(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
            }
        }
    }

    @PostMapping("/")
    public ResponseEntity<?> create(@RequestBody ListaEntity oListaseriesEntity) { // solo puede el admin

        UsuarioEntity oUsuarioEntity = (UsuarioEntity) oHttpSession.getAttribute("usuario");

        if (oUsuarioEntity == null) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        } else {
            if (oUsuarioEntity.getTipousuario().getId() == 1) {

                if (oListaseriesEntity.getId() == null) {
                    return new ResponseEntity<ListaEntity>(oListaseriesRepository.save(oListaseriesEntity), HttpStatus.OK);
                } else {
                    return new ResponseEntity<Long>(0L, HttpStatus.NOT_MODIFIED);
                }
            } else {
                return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
            }
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody ListaEntity oListaseriesEntity) { // solo puede el admin

        UsuarioEntity oUsuarioEntity = (UsuarioEntity) oHttpSession.getAttribute("usuario");

        if (oUsuarioEntity == null) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        } else {
            if (oUsuarioEntity.getTipousuario().getId() == 1) {
                oListaseriesEntity.setId(id);
                if (oListaseriesRepository.existsById(id)) {
                    return new ResponseEntity<ListaEntity>(oListaseriesRepository.save(oListaseriesEntity), HttpStatus.OK);
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
                if (oListaseriesRepository.existsById(id)) {
                    oListaseriesRepository.deleteById(id);
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

        Page<ListaEntity> oPage = oListaseriesRepository.findAll(oPageable);
        return new ResponseEntity<Page<ListaEntity>>(oPage, HttpStatus.OK);

    }

    @PostMapping("/fill/{amount}")
    public ResponseEntity<?> fill(@PathVariable(value = "amount") Long amount) {

        UsuarioEntity oUsuarioEntity = (UsuarioEntity) oHttpSession.getAttribute("usuario");

        if (oUsuarioEntity == null) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        } else {
            if (oUsuarioEntity.getTipousuario().getId() == 1) {
                return new ResponseEntity<Long>(oFillService.listaFill(amount), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
            }
        }

    }
    
       /*
    Devuelve la lista de listas de un usuario
    */
   @GetMapping("/usuario/{id}")
    public ResponseEntity<?> getListas(@PathVariable(value = "id") Long id, @PageableDefault(page = 0, size = 10, direction = Sort.Direction.ASC) Pageable oPageable) {     
        return new ResponseEntity<Page>(oListaseriesRepository.findByListaXUsuario(id, oPageable), HttpStatus.OK);
    }

}
