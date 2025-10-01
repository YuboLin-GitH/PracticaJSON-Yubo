package org.example.practicajsonyubo.Controller;

import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import org.example.practicajsonyubo.domain.Pelicula;
import org.example.practicajsonyubo.util.AlertUtils;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PeliculaController {


    private static final Object JSON_MAPPER = new Object();
    public TextField tfTitulo;
    public TextField tfFecha;
    public TextField tfGenero;
    public TextField tfDirector;
    public ListView<Pelicula> lvListaPelicula;
    public Button btImportar;



    public void cargarDatos() {
        modoEdicion(false);

        lvListaPelicula.getItems().clear();
        try {
            List<Pelicula> peliculas = obtenerPeliculas();
            lvListaPelicula.setItems(FXCollections.observableList(peliculas));

            String[] tipos = new String[]{"<Selecciona tipo>", "Familiar", "Monovolumen", "Deportivo", "SUV"};
          //tengo que buscar solucion
           //  btImportar.setItems(FXCollections.observableArrayList(tipos));
        } catch (SQLException sqle) {
            AlertUtils.mostrarError("Error cargando los datos de la aplicación");
        }
    }

    public List<Pelicula> obtenerPeliculas() throws SQLException {
        List<Pelicula> peliculas = new ArrayList<>();
        ArrayList<Pelicula> personas =
                JSON_MAPPER.readValue(new File("src/main/resources/datos/peliculas.json"),
                        JSON_MAPPER.getTypeFactory().constructCollectionType
                                (ArrayList.class, Pelicula.class));

        return peliculas;
    }

    private void modoEdicion(boolean activar) {
        btImportar.setDisable(activar);

        tfTitulo.setEditable(activar);
        tfFecha.setEditable(activar);
        tfGenero.setEditable(activar);


        lvListaPelicula.setDisable(activar);
    }

}
