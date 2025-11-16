package org.unimag.vista.equipaje;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.unimag.dto.EquipajeDto;
import org.unimag.dto.PasajeroDto;
import org.unimag.servicio.EquipajeServicio;
import org.unimag.servicio.PasajeroServicio;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class VistaEquipajeCrear {

    public static void mostrar() {
        Stage stage = new Stage();
        stage.setTitle("Crear Equipaje");

        GridPane root = new GridPane();
        root.setPadding(new Insets(10));
        root.setHgap(10);
        root.setVgap(10);

        Label lblPeso = new Label("Peso:");
        TextField tfPeso = new TextField();

        Label lblDuenio = new Label("Dueño:");
        ComboBox<String> cbDuenio = new ComboBox<>();
        PasajeroServicio pasajeroServicio = new PasajeroServicio();
        List<PasajeroDto> pasajeros = pasajeroServicio.selectFrom();
        cbDuenio.setItems(FXCollections.observableArrayList(
                pasajeros.stream().map(PasajeroDto::getNombrePasajero).collect(Collectors.toList())
        ));

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
            String pesoStr = tfPeso.getText();
            String duenioSeleccionadoStr = cbDuenio.getValue();

            if (pesoStr.isEmpty() || duenioSeleccionadoStr == null || archivoImagen[0] == null) {
                // TODO: Show alert
                return;
            }

            double peso;
            try {
                peso = Double.parseDouble(pesoStr);
            } catch (NumberFormatException ex) {
                // TODO: Show alert
                return;
            }

            PasajeroDto duenioSeleccionado = pasajeros.stream()
                    .filter(pasajero -> pasajero.getNombrePasajero().equals(duenioSeleccionadoStr))
                    .findFirst()
                    .orElse(null);

            if (duenioSeleccionado != null) {
                String idImagen = UUID.randomUUID().toString() + ".jpg";

                try {
                    File dest = new File("recursos/imagenes/" + idImagen);
                    Files.copy(archivoImagen[0].toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException ex) {
                    // TODO: show alert
                    return;
                }

                EquipajeServicio equipajeServicio = new EquipajeServicio();
                int id = equipajeServicio.getSerial();
                EquipajeDto equipajeDto = new EquipajeDto(id, peso, duenioSeleccionado.getCedulaPasajero(), idImagen);
                equipajeServicio.inserInto(equipajeDto, null);

                tfPeso.clear();
                cbDuenio.getSelectionModel().clearSelection();
                iwImagen.setImage(null);
                archivoImagen[0] = null;
            }
        });

        root.add(lblPeso, 0, 0);
        root.add(tfPeso, 1, 0);
        root.add(lblDuenio, 0, 1);
        root.add(cbDuenio, 1, 1);
        root.add(btnSeleccionarImagen, 0, 2);
        root.add(iwImagen, 1, 2);
        root.add(btnGuardar, 1, 3);

        Scene scene = new Scene(root, 400, 300);
        stage.setScene(scene);
        stage.show();
    }
}
