package org.unimag.vista.asiento;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.unimag.dto.AsientoDto;
import org.unimag.dto.BusDto;
import org.unimag.servicio.AsientoServicio;
import org.unimag.servicio.BusServicio;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class VistaAsientoCrear {

    public static void mostrar() {
        Stage stage = new Stage();
        stage.setTitle("Crear Asiento");

        GridPane root = new GridPane();
        root.setPadding(new Insets(10));
        root.setHgap(10);
        root.setVgap(10);

        Label lblBus = new Label("Bus:");
        ComboBox<String> cbBus = new ComboBox<>();
        BusServicio busServicio = new BusServicio();
        List<BusDto> buses = busServicio.selectFrom();
        cbBus.setItems(FXCollections.observableArrayList(
                buses.stream().map(bus -> bus.getIdBus() + ", " + bus.getModeloBus()).collect(Collectors.toList())
        ));

        Label lblEstado = new Label("Estado:");
        ToggleGroup groupEstado = new ToggleGroup();
        RadioButton rbActivo = new RadioButton("Activo");
        rbActivo.setToggleGroup(groupEstado);
        RadioButton rbInactivo = new RadioButton("Inactivo");
        rbInactivo.setToggleGroup(groupEstado);
        HBox hboxEstado = new HBox(10, rbActivo, rbInactivo);

        Button btnSeleccionarImagen = new Button("Seleccionar imagen");
        ImageView iwImagen = new ImageView();
        iwImagen.setFitHeight(100);
        iwImagen.setFitWidth(100);
        final File[] archivoImagen = {null};

        btnSeleccionarImagen.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif")
            );
            File selectedFile = fileChooser.showOpenDialog(stage);
            if (selectedFile != null) {
                archivoImagen[0] = selectedFile;
                iwImagen.setImage(new javafx.scene.image.Image(selectedFile.toURI().toString()));
            }
        });

        Button btnGuardar = new Button("Guardar");
        btnGuardar.setOnAction(e -> {
            String busSeleccionadoStr = cbBus.getValue();
            RadioButton estadoSeleccionado = (RadioButton) groupEstado.getSelectedToggle();

            if (busSeleccionadoStr == null || estadoSeleccionado == null || archivoImagen[0] == null) {
                // TODO: Show alert
                return;
            }

            int idBus = Integer.parseInt(busSeleccionadoStr.split(",")[0].trim());
            boolean esActivo = estadoSeleccionado.getText().equals("Activo");
            String idImagen = UUID.randomUUID().toString() + ".jpg";

            try {
                File dest = new File("recursos/imagenes/" + idImagen);
                Files.copy(archivoImagen[0].toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException ex) {
                // TODO: show alert
                return;
            }

            AsientoServicio asientoServicio = new AsientoServicio();
            int id = asientoServicio.getSerial();
            AsientoDto asientoDto = new AsientoDto(id, idBus, esActivo, idImagen);
            asientoServicio.inserInto(asientoDto, null);

            cbBus.getSelectionModel().clearSelection();
            groupEstado.selectToggle(null);
            iwImagen.setImage(null);
            archivoImagen[0] = null;
        });

        root.add(lblBus, 0, 0);
        root.add(cbBus, 1, 0);
        root.add(lblEstado, 0, 1);
        root.add(hboxEstado, 1, 1);
        root.add(btnSeleccionarImagen, 0, 2);
        root.add(iwImagen, 1, 2);
        root.add(btnGuardar, 1, 3);

        Scene scene = new Scene(root, 400, 300);
        stage.setScene(scene);
        stage.show();
    }
}
