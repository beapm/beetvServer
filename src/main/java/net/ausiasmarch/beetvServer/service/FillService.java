package net.ausiasmarch.beetvServer.service;

import java.time.ZoneId;
import java.util.Optional;
import java.util.Random;
import net.ausiasmarch.beetvServer.entity.CapituloEntity;
import net.ausiasmarch.beetvServer.entity.CapitulosvistosEntity;
import net.ausiasmarch.beetvServer.entity.ContenidolistaEntity;
import net.ausiasmarch.beetvServer.entity.GeneroEntity;
import net.ausiasmarch.beetvServer.entity.ListaEntity;
import net.ausiasmarch.beetvServer.entity.PuntuacionserieEntity;
import net.ausiasmarch.beetvServer.entity.SerieEntity;
import net.ausiasmarch.beetvServer.entity.TemporadaEntity;
import net.ausiasmarch.beetvServer.entity.TipousuarioEntity;
import net.ausiasmarch.beetvServer.entity.UsuarioEntity;
import net.ausiasmarch.beetvServer.helper.RandomHelper;
import net.ausiasmarch.beetvServer.repository.CapituloRepository;
import net.ausiasmarch.beetvServer.repository.CapitulosvistosRepository;
import net.ausiasmarch.beetvServer.repository.ContenidolistaRepository;
import net.ausiasmarch.beetvServer.repository.GeneroRepository;
import net.ausiasmarch.beetvServer.repository.PuntuacionserieRepository;
import net.ausiasmarch.beetvServer.repository.TemporadaRepository;
import net.ausiasmarch.beetvServer.repository.TipousuarioRepository;
import net.ausiasmarch.beetvServer.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.ausiasmarch.beetvServer.repository.ListaRepository;

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

    @Autowired
    TemporadaRepository oTemporadaRepository;

    @Autowired
    PuntuacionserieRepository oPuntuacionserieRepository;

    @Autowired
    ListaRepository oListaRepository;

    @Autowired
    ContenidolistaRepository oContenidolistaRepository;

    @Autowired
    CapitulosvistosRepository oCapitulosvistosRepository;

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

    public Long generoFill() {

        GeneroEntity oGeneroEntity = new GeneroEntity();
        oGeneroEntity.setNombre("Animación");
        oGeneroRepository.save(oGeneroEntity);

        oGeneroEntity = new GeneroEntity();
        oGeneroEntity.setNombre("Ciencia Ficción");
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

    public Long temporadaFill(Long cantidad) {
        for (int i = 1; i <= cantidad; i++) {
            TemporadaEntity oTemporadaEntity = new TemporadaEntity();

            oTemporadaEntity.setFecha_inicio(RandomHelper.getRadomDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            oTemporadaEntity.setFecha_fin(RandomHelper.getRadomDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            //    oTemporadaEntity.setId_serie(Long.valueOf(RandomHelper.getRandomInt(1, 15)));
            oTemporadaEntity.setId_file(1L);

            oTemporadaRepository.save(oTemporadaEntity);
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

    public String getSinopsis01() {
        String nombre = "";
        switch (RandomHelper.getRandomInt(1, 5)) {
            case 1:
                nombre = "En este capítulo ";
                break;
            case 2:
                nombre = "En el episodio de esta semana ";
                break;
            case 3:
                nombre = "En esta nueva entrega ";
                break;
            case 4:
                nombre = "Tras el episodio anterior, en el nuevo  ";
                break;
            case 5:
                nombre = "La historia continúa y ";
                break;
        }
        return nombre;
    }

    public String getSinopsis02() {
        String nombre = "";
        switch (RandomHelper.getRandomInt(1, 8)) {
            case 1:
                nombre = "los personajes tendrán que enfrentarse a un nuevo reto.";
                break;
            case 2:
                nombre = "conoceremos uno de los misterios más sorprendentes de la serie.";
                break;
            case 3:
                nombre = "se produce un altercado que no dejará indiferente a nadie.";
                break;
            case 4:
                nombre = "dos de los personajes más importantes de la serie tendrán un encuentro inolvidable.";
                break;
            case 5:
                nombre = "la protagonista vivirá una situación de vida o muerte.";
                break;
            case 6:
                nombre = "sabremos por fin la identidad del asesino.";
                break;
            case 7:
                nombre = "conoceremos la infancia de los protagonistas gracias a distintos flashbacks.";
                break;
            case 8:
                nombre = "llegamos al especial navideño con muchas incógnitas por resolver.";
                break;
        }
        return nombre;
    }

    public String getSinopsis03() {
        String nombre = "";
        switch (RandomHelper.getRandomInt(1, 5)) {
            case 1:
                nombre += " Al final hay una revelación que nadie se espera.";
                break;
            case 2:
                nombre += " Habrá que esperar al próximo episodio para ver cómo continúa la historia.";
                break;
            case 3:
                nombre += " ¿Qué pasará próximamente?";
                break;
            case 4:
                nombre += " El desenlace abierto no dejará indiferente a nadie";
                break;
            case 5:
                nombre += " Presta atención a los detalles de la escena final.";
                break;
        }
        return nombre;
    }

    public Long capituloFill(Long cantidad) {
        for (int i = 1; i <= cantidad; i++) {
            CapituloEntity oCapituloEntity = new CapituloEntity();

            oCapituloEntity.setNombre(this.getCapitulo01() + this.getCapitulo02() + this.getCapitulo03());
            oCapituloEntity.setSinopsis_capitulo(this.getSinopsis01() + this.getSinopsis02() + this.getSinopsis03());
            oCapituloEntity.setFecha_emision(RandomHelper.getRadomDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            oCapituloEntity.setDuracion(RandomHelper.getRandomInt(21, 78));
            //    oCapituloEntity.setId_temporada(Long.valueOf(RandomHelper.getRandomInt(1, 100)));
            oCapituloEntity.setId_file(1L);

            oCapituloRepository.save(oCapituloEntity);
        }
        return cantidad;
    }

    public Long puntuacionserieFill(Long cantidad) {
        for (int i = 1; i <= cantidad; i++) {
            PuntuacionserieEntity oPuntuacionserieEntity = new PuntuacionserieEntity();

            //    oPuntuacionserieEntity.setId_serie(Long.valueOf(RandomHelper.getRandomInt(1, 20)));
            //    oPuntuacionserieEntity.setId_usuario(Long.valueOf(RandomHelper.getRandomInt(1, 200)));
            oPuntuacionserieEntity.setPuntuacion(RandomHelper.getRandomInt(1, 5));

            oPuntuacionserieRepository.save(oPuntuacionserieEntity);
        }
        return cantidad;
    }

    public Long listaFill(Long cantidad) {

        String[] nombres = {"Acción", "Aventuras", "Favoritas", "Favs", "Pendientes", "Animación", "Divertidas", "Lista de pendientes",
            "Lista de favoritas"};
        for (int i = 1; i <= cantidad; i++) {
            ListaEntity oListaEntity = new ListaEntity();

            UsuarioEntity oUsuarioEntity = new UsuarioEntity();
            oUsuarioEntity.setId(Long.valueOf(RandomHelper.getRandomInt(1, 301)));
            oListaEntity.setUsuario(oUsuarioEntity);

            String nombre = nombres[(int) (Math.floor(Math.random() * ((nombres.length - 1) - 0 + 1) + 0))];
            oListaEntity.setNombre(nombre);

            oListaRepository.save(oListaEntity);
        }
        return cantidad;
    }

    public Long contenidolistaFill(Long cantidad) {

        for (int i = 1; i <= cantidad; i++) {

            ContenidolistaEntity oContenidolistaEntity = new ContenidolistaEntity();

            ListaEntity oListaseriesEntity = new ListaEntity();
            oListaseriesEntity.setId(Long.valueOf(RandomHelper.getRandomInt(1, 30)));
            oContenidolistaEntity.setLista(oListaseriesEntity);

            SerieEntity oSerieEntity = new SerieEntity();
            oSerieEntity.setId(Long.valueOf(RandomHelper.getRandomInt(1, 20)));

            oContenidolistaEntity.setSerie(oSerieEntity);

            Random rand = new Random(); // para obtener un booleano aleatorio
            Boolean value = rand.nextBoolean();
            oContenidolistaEntity.setSiguiendo(value);

            oContenidolistaRepository.save(oContenidolistaEntity);
        }
        return cantidad;
    }

    public Long capitulosvistosFill(Long cantidad) {

        for (int i = 1; i <= cantidad; i++) {

            CapitulosvistosEntity oCapitulosvistosEntity = new CapitulosvistosEntity();

            CapituloEntity oCapituloEntity = new CapituloEntity();
            oCapituloEntity.setId(Long.valueOf(RandomHelper.getRandomInt(1, 500)));
            oCapitulosvistosEntity.setCapitulo(oCapituloEntity);

            UsuarioEntity oUsuarioEntity = new UsuarioEntity();
            oUsuarioEntity.setId(Long.valueOf(RandomHelper.getRandomInt(2, 300)));
            oCapitulosvistosEntity.setUsuario(oUsuarioEntity);

            Random rand = new Random(); // para obtener un booleano aleatorio
            Boolean value = rand.nextBoolean();
            oCapitulosvistosEntity.setVisto(value);

            oCapitulosvistosRepository.save(oCapitulosvistosEntity);
        }
        return cantidad;
    }
}
