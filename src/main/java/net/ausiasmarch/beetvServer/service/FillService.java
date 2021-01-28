package net.ausiasmarch.beetvServer.service;

import java.time.ZoneId;
import java.util.Optional;
import java.util.Random;
import net.ausiasmarch.beetvServer.entity.ActuacionEntity;
import net.ausiasmarch.beetvServer.entity.CapituloEntity;
import net.ausiasmarch.beetvServer.entity.ComentarioEntity;
import net.ausiasmarch.beetvServer.entity.GeneroEntity;
import net.ausiasmarch.beetvServer.entity.LikesEntity;
import net.ausiasmarch.beetvServer.entity.ListaseriesEntity;
import net.ausiasmarch.beetvServer.entity.PersonajeEntity;
import net.ausiasmarch.beetvServer.entity.PuntuacionserieEntity;
import net.ausiasmarch.beetvServer.entity.TemporadaEntity;
import net.ausiasmarch.beetvServer.entity.TipousuarioEntity;
import net.ausiasmarch.beetvServer.entity.UsuarioEntity;
import net.ausiasmarch.beetvServer.helper.RandomHelper;
import net.ausiasmarch.beetvServer.repository.ActuacionRepository;
import net.ausiasmarch.beetvServer.repository.CapituloRepository;
import net.ausiasmarch.beetvServer.repository.ComentarioRepository;
import net.ausiasmarch.beetvServer.repository.GeneroRepository;
import net.ausiasmarch.beetvServer.repository.LikesRepository;
import net.ausiasmarch.beetvServer.repository.ListaseriesRepository;
import net.ausiasmarch.beetvServer.repository.PersonajeRepository;
import net.ausiasmarch.beetvServer.repository.PuntuacionserieRepository;
import net.ausiasmarch.beetvServer.repository.TemporadaRepository;
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

    @Autowired
    TemporadaRepository oTemporadaRepository;

    @Autowired
    PersonajeRepository oPersonajeRepository;

    @Autowired
    ActuacionRepository oActuacionRepository;

    @Autowired
    PuntuacionserieRepository oPuntuacionserieRepository;

    @Autowired
    LikesRepository oLikesRepository;

    @Autowired
    ComentarioRepository oComentarioRepository;

    @Autowired
    ListaseriesRepository oListaseriesRepository;

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

    public Long personajeFill(Long cantidad) {

        String[] nombres = {"Samantha", "Flavio", "Claudia", "Cristina", "Meredith", "Paula", "Raquel", "Alex",
            "Guillermo", "Victoria", "Charles", "Carla", "Carlos", "Anya", "John", "Rachel", "Jennifer",
            "Pablo", "Richard", "Tom"};
        String[] apellidos = {"Brooks", "González", "Smith", "Brown", "Taylor", "Pérez", "Sánchez", "García",
            "Fernández", "Rodríguez", "Cox", "Howard", "Anniston", "Murphy", "Hernández", "Bradford", "Williams",
            "Díaz", "Navarro", "Vázquez"};

        for (int i = 1; i <= cantidad; i++) {

            PersonajeEntity oPersonajeEntity = new PersonajeEntity();

            String nombre = nombres[(int) (Math.floor(Math.random() * ((nombres.length - 1) - 0 + 1) + 0))];
            String apellido1 = apellidos[(int) (Math.floor(Math.random() * ((apellidos.length - 1) - 0 + 1) + 0))];
            String apellido2 = apellidos[(int) (Math.floor(Math.random() * ((apellidos.length - 1) - 0 + 1) + 0))];
            oPersonajeEntity.setNombre(nombre);
            oPersonajeEntity.setApellido1(apellido1);
            oPersonajeEntity.setApellido2(apellido2);
            oPersonajeEntity.setId_file(1L);

            oPersonajeRepository.save(oPersonajeEntity);
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

    public Long actuacionFill(Long cantidad) {
        for (int i = 1; i <= cantidad; i++) {
            ActuacionEntity oActuacionEntity = new ActuacionEntity();

            //    oActuacionEntity.setId_capitulo(Long.valueOf(RandomHelper.getRandomInt(1, 500)));
            //    oActuacionEntity.setId_personaje(Long.valueOf(RandomHelper.getRandomInt(1, 300)));
            oActuacionRepository.save(oActuacionEntity);
        }
        return cantidad;
    }

    public Long puntuacionserieFill(Long cantidad) {
        for (int i = 1; i <= cantidad; i++) {
            PuntuacionserieEntity oPuntuacionserieEntity = new PuntuacionserieEntity();

            oPuntuacionserieEntity.setId_serie(Long.valueOf(RandomHelper.getRandomInt(1, 20)));
            oPuntuacionserieEntity.setId_usuario(Long.valueOf(RandomHelper.getRandomInt(1, 200)));
            oPuntuacionserieEntity.setPuntuacion(RandomHelper.getRandomInt(1, 5));

            oPuntuacionserieRepository.save(oPuntuacionserieEntity);
        }
        return cantidad;
    }

    public Long likesFill(Long cantidad) {
        for (int i = 1; i <= cantidad; i++) {

            LikesEntity oLikesEntity = new LikesEntity();

            //    oLikesEntity.setId_comentario(Long.valueOf(RandomHelper.getRandomInt(1, 200)));
            //    oLikesEntity.setId_usuario(Long.valueOf(RandomHelper.getRandomInt(1, 200)));
            Random rand = new Random(); // para obtener un booleano aleatorio
            Boolean value = rand.nextBoolean();

            oLikesEntity.setLike_type(value);

            oLikesRepository.save(oLikesEntity);
        }
        return cantidad;
    }

    public Long comentarioFill(Long cantidad) {

        String[] coments = {"Muy buen capítulo.", "No me ha gustado nada.", "¡Gran final de temporada!", "Me habría gustado que tuviese un poco más de acción...",
            "¡Ojalá se salve!", "Muy divertido, ¡me he reído mucho!"};

        for (int i = 1; i <= cantidad; i++) {
            ComentarioEntity oComentarioEntity = new ComentarioEntity();

            String coment = coments[(int) (Math.floor(Math.random() * ((coments.length - 1) - 0 + 1) + 0))];

            // oComentarioEntity.setId_capitulo(Long.valueOf(RandomHelper.getRandomInt(1, 500)));
            // oComentarioEntity.setId_usuario(Long.valueOf(RandomHelper.getRandomInt(1, 300)));
            oComentarioEntity.setTexto(coment);

            oComentarioRepository.save(oComentarioEntity);
        }
        return cantidad;
    }

    public Long listaseriesFill(Long cantidad) {

        for (int i = 1; i <= cantidad; i++) {
            ListaseriesEntity oListaseriesEntity = new ListaseriesEntity();

            oListaseriesEntity.setId_serie(Long.valueOf(RandomHelper.getRandomInt(1, 20)));
            oListaseriesEntity.setId_usuario(Long.valueOf(RandomHelper.getRandomInt(1, 301)));

            Random rand = new Random(); // para obtener un booleano aleatorio
            Boolean value = rand.nextBoolean();

            oListaseriesEntity.setSiguiendo(value);

            oListaseriesRepository.save(oListaseriesEntity);
        }
        return cantidad;
    }
}
