package net.ausiasmarch.beetvServer.service;

import java.time.ZoneId;
import java.util.Optional;
import net.ausiasmarch.beetvServer.entity.CapituloEntity;
import net.ausiasmarch.beetvServer.entity.GeneroEntity;
import net.ausiasmarch.beetvServer.entity.TipousuarioEntity;
import net.ausiasmarch.beetvServer.entity.UsuarioEntity;
import net.ausiasmarch.beetvServer.helper.RandomHelper;
import net.ausiasmarch.beetvServer.repository.CapituloRepository;
import net.ausiasmarch.beetvServer.repository.GeneroRepository;
import net.ausiasmarch.beetvServer.repository.TipousuarioRepository;
import net.ausiasmarch.beetvServer.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FillService {

    @Autowired
    TipousuarioRepository oTipousuarioRepository;

    @Autowired
    UsuarioRepository oUsuarioRepository;
    
    @Autowired
    CapituloRepository oCapituloRepository;
    
    @Autowired
    GeneroRepository oGeneroRepository;

    public Long tipousuarioFill() {

        TipousuarioEntity oTipousuarioEntity = new TipousuarioEntity();
        oTipousuarioEntity.setNombre("Administrador");
        oTipousuarioRepository.save(oTipousuarioEntity);

        oTipousuarioEntity = new TipousuarioEntity();
        oTipousuarioEntity.setNombre("Usuario");
        oTipousuarioRepository.save(oTipousuarioEntity);

        oTipousuarioEntity = new TipousuarioEntity();
        oTipousuarioEntity.setNombre("Visitante");
        oTipousuarioRepository.save(oTipousuarioEntity);

        return 3L;

    }

    public Long usuarioFill(Long cantidad) {

        String[] nombres = {"Andrea", "David", "Daniela", "Pablo", "Elena", "Sergio", "Alba", "Adrián",
            "Julia", "Diego", "Patricia", "Jorge", "Nuria", "Samuel", "Paula", "Jaime", "Alicia",
            "Eduardo", "Laura", "Marcos"};
        String[] apellidos = {"Gómez", "Guerrero", "Crespo", "Pascual", "Vega", "Flores", "Molina", "Cruz",
            "García", "Rodríguez", "Rubio", "Castro", "Grande", "Álvarez", "Esteban", "Martín", "Gil",
            "Ruiz"};

        for (int i = 1; i <= cantidad; i++) {

            UsuarioEntity oUsuarioEntity = new UsuarioEntity();

            String nombre = nombres[(int) (Math.floor(Math.random() * ((nombres.length - 1) - 0 + 1) + 0))];
            String apellido1 = apellidos[(int) (Math.floor(Math.random() * ((apellidos.length - 1) - 0 + 1) + 0))];
            String apellido2 = apellidos[(int) (Math.floor(Math.random() * ((apellidos.length - 1) - 0 + 1) + 0))];
            oUsuarioEntity.setNombre(nombre);
            oUsuarioEntity.setApellido1(apellido1);
            oUsuarioEntity.setApellido2(apellido2);

            oUsuarioEntity.setLogin(nombre.substring(0, 2).toLowerCase() + apellido1.substring(0, 2).toLowerCase() + apellido2.substring(0, 2).toLowerCase() + String.valueOf(RandomHelper.getRandomInt(1, 10)));
            oUsuarioEntity.setPassword("fabd876645a6fbf92a2a5852ba6d5c9d748c573c35eade5ace28a5b3edd17829");
            oUsuarioEntity.setEmail(nombre + apellido1.charAt(0) + "@beetv.com");
            TipousuarioEntity oTipousuarioEntity = new TipousuarioEntity();
            oTipousuarioEntity.setId(2L);
            oUsuarioEntity.setTipousuario(oTipousuarioEntity);
            oUsuarioEntity.setToken("");
            oUsuarioEntity.setValidado(true);
            oUsuarioEntity.setActivo(true);
            oUsuarioEntity.setId_file(1L);

            oUsuarioRepository.save(oUsuarioEntity);
        }
        return cantidad;
    }

    public String getCapitulo01() {
        String nombre = "";
        switch (RandomHelper.getRandomInt(1, 6)) {
            case 1:
                nombre = "El de ";
                break;
            case 2:
                nombre = "La historia de ";
                break;
            case 3:
                nombre = "Érase una vez ";
                break;
            case 4:
                nombre = "Había una vez  ";
                break;
            case 5:
                nombre = "Aquel en el que ";
                break;
            case 6:
                nombre = "El misterio de ";
                break;
        }
        return nombre;
    }

    public String getCapitulo02() {
        String nombre = "";
        switch (RandomHelper.getRandomInt(1, 7)) {
            case 1:
                nombre = "un perro en";
                break;
            case 2:
                nombre = "un gato en";
                break;
            case 3:
                nombre = "un cuerpo en";
                break;
            case 4:
                nombre = "la fiesta de";
                break;
            case 5:
                nombre = "la persecución de";
                break;
            case 6:
                nombre = "la aparición de";
                break;
            case 7:
                nombre = "la desaparición de";
                break;
        }
        return nombre;
    }

    public String getCapitulo03() {
        String nombre = "";
        switch (RandomHelper.getRandomInt(1, 9)) {
            case 1:
                nombre += " la oscuridad.";
                break;
            case 2:
                nombre += " la noche.";
                break;
            case 3:
                nombre += " Robert.";
                break;
            case 4:
                nombre += " Cristine";
                break;
            case 5:
                nombre += " Lex";
                break;
            case 6:
                nombre += " las luciérnagas.";
                break;
            case 7:
                nombre += " la felicidad.";
                break;
            case 8:
                nombre += " la tristeza.";
                break;
            case 9:
                nombre += " la llave.";
                break;
        }
        return nombre;
    }

    public Long capituloFill(Long cantidad) {
        for (int i = 1; i <= cantidad; i++) {
            CapituloEntity oCapituloEntity = new CapituloEntity();

            oCapituloEntity.setNombre(this.getCapitulo01() + this.getCapitulo02() + this.getCapitulo03());
            oCapituloEntity.setSinopsis_capitulo("Sinopsis");
            oCapituloEntity.setFecha_emision(RandomHelper.getRadomDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            oCapituloEntity.setDuracion(RandomHelper.getRandomInt(21, 78));
            oCapituloEntity.setId_temporada(1L);
            oCapituloEntity.setId_file(1L);
            
        oCapituloRepository.save(oCapituloEntity);
        }
        return cantidad;
    }

    public Long generoFill () {
        
        GeneroEntity oGeneroEntity = new GeneroEntity();
        oGeneroEntity.setNombre("Animación");
        oGeneroRepository.save(oGeneroEntity);

        
        oGeneroEntity = new GeneroEntity();
        oGeneroEntity.setNombre("Cienia Ficción");
        oGeneroRepository.save(oGeneroEntity);

        
        oGeneroEntity = new GeneroEntity();
        oGeneroEntity.setNombre("Comedia");
        oGeneroRepository.save(oGeneroEntity);

        
        oGeneroEntity = new GeneroEntity();
        oGeneroEntity.setNombre("Thriller");
        oGeneroRepository.save(oGeneroEntity);

        
        oGeneroEntity = new GeneroEntity();
        oGeneroEntity.setNombre("Drama");
        oGeneroRepository.save(oGeneroEntity);

        
        oGeneroEntity = new GeneroEntity();
        oGeneroEntity.setNombre("Acción y Aventuras");
        oGeneroRepository.save(oGeneroEntity);

        
        oGeneroEntity = new GeneroEntity();
        oGeneroEntity.setNombre("Crimen");
        oGeneroRepository.save(oGeneroEntity);

        return 7L;
    }
}
