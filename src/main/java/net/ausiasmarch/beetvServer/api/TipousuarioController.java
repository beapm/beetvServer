package net.ausiasmarch.beetvServer.api;

import java.util.List;
import javax.servlet.http.HttpSession;
import net.ausiasmarch.beetvServer.entity.TipousuarioEntity;
import net.ausiasmarch.beetvServer.entity.UsuarioEntity;
import net.ausiasmarch.beetvServer.repository.TipousuarioRepository;
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
@RequestMapping("/tipousuario")
public class TipousuarioController {

    @Autowired
    HttpSession oHttpSession;

    @Autowired
    TipousuarioRepository oTipousuarioRepository;

    @Autowired
    FillService oFillService;

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable(value = "id") Long id) {
        if (oTipousuarioRepository.existsById(id)) {
            return new ResponseEntity<TipousuarioEntity>(oTipousuarioRepository.getOne(id), HttpStatus.OK);
        } else {
            return new ResponseEntity<TipousuarioEntity>(oTipousuarioRepository.getOne(id), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> all() {
        if (oTipousuarioRepository.count() <= 1000) {
            return new ResponseEntity<List<TipousuarioEntity>>(oTipousuarioRepository.findAll(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.PAYLOAD_TOO_LARGE);
        }
    }

    @GetMapping("/count")
    public ResponseEntity<?> count() {
        return new ResponseEntity<Long>(oTipousuarioRepository.count(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        oTipousuarioRepository.deleteById(id);
        if (oTipousuarioRepository.existsById(id)) {
            return new ResponseEntity<Long>(id, HttpStatus.NOT_MODIFIED);
        } else {
            return new ResponseEntity<Long>(0L, HttpStatus.OK);
        }
    }

    @PostMapping("/")
    public ResponseEntity<?> create(@RequestBody TipousuarioEntity oTipousuarioEntity) {

        UsuarioEntity oUsuarioEntity = (UsuarioEntity) oHttpSession.getAttribute("usuario");
        if (oUsuarioEntity == null) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        } else {
            if (oUsuarioEntity.getTipousuario().getId() == 1 || oUsuarioEntity.getTipousuario().getId() == 2) { //administrador o usuario serán rechazados
                return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
            } else {
                if (oTipousuarioEntity.getId() == null) {
                    return new ResponseEntity<TipousuarioEntity>(oTipousuarioRepository.save(oTipousuarioEntity), HttpStatus.OK);
                } else {
                    return new ResponseEntity<Long>(0L, HttpStatus.NOT_MODIFIED);
                }
            }
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody TipousuarioEntity oTipousuarioEntity) {
        oTipousuarioEntity.setId(id);
        if (oTipousuarioRepository.existsById(id)) {
            return new ResponseEntity<TipousuarioEntity>(oTipousuarioRepository.save(oTipousuarioEntity), HttpStatus.OK);
        } else {
            return new ResponseEntity<Long>(0L, HttpStatus.NOT_MODIFIED);
        }
    }

    @GetMapping("/page")
    public ResponseEntity<?> getPage(@PageableDefault(page = 0, size = 10, direction = Direction.ASC) Pageable oPageable) {

        Page<TipousuarioEntity> oPage = oTipousuarioRepository.findAll(oPageable);
        return new ResponseEntity<Page<TipousuarioEntity>>(oPage, HttpStatus.OK);
    }

    @PostMapping("/fill")
    public ResponseEntity<?> fill() {
        return new ResponseEntity<Long>(oFillService.tipousuarioFill(), HttpStatus.OK);
    }
}
