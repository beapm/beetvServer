package net.ausiasmarch.beetvServer.api;

import java.util.List;
import javax.servlet.http.HttpSession;
import net.ausiasmarch.beetvServer.entity.PuntuacionserieEntity;
import net.ausiasmarch.beetvServer.entity.UsuarioEntity;
import net.ausiasmarch.beetvServer.repository.PuntuacionserieRepository;
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
@RequestMapping("/puntuacionserie")
public class PuntuacionserieController {
    
    @Autowired
    HttpSession oHttpSession;

    @Autowired
    UsuarioRepository oUsuarioRepository;

    @Autowired
    TipousuarioRepository oTipousuarioRepository;

    @Autowired
    PuntuacionserieRepository oPuntuacionserieRepository;

    @Autowired
    FillService oFillService;

    
    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable(value = "id") Long id) {

        if (oPuntuacionserieRepository.existsById(id)) {
            return new ResponseEntity<PuntuacionserieEntity>(oPuntuacionserieRepository.getOne(id), HttpStatus.OK);
        } else {
            return new ResponseEntity<PuntuacionserieEntity>(oPuntuacionserieRepository.getOne(id), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> all() {
        if (oPuntuacionserieRepository.count() <= 1000) {
            return new ResponseEntity<List<PuntuacionserieEntity>>(oPuntuacionserieRepository.findAll(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.PAYLOAD_TOO_LARGE);
        }
    }

    @GetMapping("/count")
    public ResponseEntity<?> count() {
        return new ResponseEntity<Long>(oPuntuacionserieRepository.count(), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<?> create(@RequestBody PuntuacionserieEntity oPuntuacionserieEntity) { // puede admin y usuario registrado

        UsuarioEntity oUsuarioEntity = (UsuarioEntity) oHttpSession.getAttribute("usuario");

        if (oUsuarioEntity == null) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        } else {
            if (oUsuarioEntity.getTipousuario().getId() == 1 || oUsuarioEntity.getTipousuario().getId() == 2) {

                if (oPuntuacionserieEntity.getId() == null) {
                    return new ResponseEntity<PuntuacionserieEntity>(oPuntuacionserieRepository.save(oPuntuacionserieEntity), HttpStatus.OK);
                } else {
                    return new ResponseEntity<Long>(0L, HttpStatus.NOT_MODIFIED);
                }
            } else {
                return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
            }
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody PuntuacionserieEntity oPuntuacionserieEntity) { // solo puede el admin

        UsuarioEntity oUsuarioEntity = (UsuarioEntity) oHttpSession.getAttribute("usuario");

        if (oUsuarioEntity == null) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        } else {
            if (oUsuarioEntity.getTipousuario().getId() == 1) {
                oPuntuacionserieEntity.setId(id);
                if (oPuntuacionserieRepository.existsById(id)) {
                    return new ResponseEntity<PuntuacionserieEntity>(oPuntuacionserieRepository.save(oPuntuacionserieEntity), HttpStatus.OK);
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
                if (oPuntuacionserieRepository.existsById(id)) {
                    oPuntuacionserieRepository.deleteById(id);
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

        Page<PuntuacionserieEntity> oPage = oPuntuacionserieRepository.findAll(oPageable);
        return new ResponseEntity<Page<PuntuacionserieEntity>>(oPage, HttpStatus.OK);

    }

    @PostMapping("/fill/{amount}")
    public ResponseEntity<?> fill(@PathVariable(value = "amount") Long amount) {

        UsuarioEntity oUsuarioEntity = (UsuarioEntity) oHttpSession.getAttribute("usuario");

        if (oUsuarioEntity == null) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        } else {
            if (oUsuarioEntity.getTipousuario().getId() == 1) { 
                return new ResponseEntity<Long>(oFillService.puntuacionserieFill(amount), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
            }
        }

    }
}
