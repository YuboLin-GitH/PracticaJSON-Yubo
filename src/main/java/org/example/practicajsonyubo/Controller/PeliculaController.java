package org.example.practicajsonyubo.Controller;


import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import org.example.practicajsonyubo.DAO.PeliculaDAO;
import org.example.practicajsonyubo.domain.Pelicula;
import org.example.practicajsonyubo.util.AlertUtils;

import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import java.io.IOException;
import java.util.List;

public class PeliculaController {

    private final PeliculaDAO peliculaDAO = new PeliculaDAO();

    @FXML
    public TextField tfTitulo;
    @FXML
    public TextField tfFecha;
    @FXML
    public TextField tfGenero;
    @FXML
    public TextField tfDirector;
    @FXML
    public ListView<Pelicula> lvListaPelicula;
    @FXML
    public Button btImportar;


    public  void initialize() {
        lvListaPelicula.setCellFactory(param -> new ListCell<Pelicula>() {
            @Override
            protected void updateItem(Pelicula pelicula, boolean empty) {
                super.updateItem(pelicula, empty);
                if (empty || pelicula == null) {
                    setText(null);
                }else {
                    setText(pelicula.getTitulo());
                }
            }
        });

        lvListaPelicula.setOnMouseClicked(this::onPeliculaSeleccionada);
    }

    public void onImportarClick(ActionEvent event) {
        cargarDatos();
    }


    public void cargarDatos() {
        modoEdicion(false);
        lvListaPelicula.getItems().clear();

        try {
            List<Pelicula> peliculas = PeliculaDAO.obtenerPeliculas();
            lvListaPelicula.setItems(FXCollections.observableList(peliculas));

        } catch (IOException e) {
            AlertUtils.mostrarError("Error cargando los datos de la JSON");
        }
    }



    private void onPeliculaSeleccionada(MouseEvent event) {
        Pelicula p = lvListaPelicula.getSelectionModel().getSelectedItem();
        if (p != null) {
            mostrarPelicula(p);
        }
    }

    private void mostrarPelicula(Pelicula p) {
        tfTitulo.setText(p.getTitulo());
        tfFecha.setText(p.getFecha());
        tfGenero.setText(p.getGenero());
        tfDirector.setText(p.getDirector());
    }


    private void modoEdicion(boolean activar) {
        btImportar.setDisable(activar);

        tfTitulo.setEditable(activar);
        tfFecha.setEditable(activar);
        tfGenero.setEditable(activar);
        tfDirector.setEditable(activar);

        lvListaPelicula.setDisable(activar);
    }

}
