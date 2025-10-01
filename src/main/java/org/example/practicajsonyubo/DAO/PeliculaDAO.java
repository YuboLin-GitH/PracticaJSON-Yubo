package org.example.practicajsonyubo.DAO;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.practicajsonyubo.domain.Pelicula;
import org.example.practicajsonyubo.util.AlertUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * ClassName: PeliculaDAO
 * Package: org.example.practicajsonyubo.DAO
 * Description:
 *
 * @Author Yubo
 * @Create 01/10/2025 16:58
 * @Version 1.0
 */
public class PeliculaDAO {
    private static final ObjectMapper JSON_MAPPER = new ObjectMapper();

    public static List<Pelicula> obtenerPeliculas() throws IOException {
            ArrayList<Pelicula> peliculas =
                    JSON_MAPPER.readValue(new File("src/main/resources/datos/peliculas.json"),
                            JSON_MAPPER.getTypeFactory().constructCollectionType
                                    (ArrayList.class, Pelicula.class));

            return peliculas;
    }
}
