package org.unimag.vista.ruta;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.unimag.dto.RutaDto;
import org.unimag.servicio.RutaServicio;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

public class VistaRutaCrear {

    public static void mostrar() {
        Stage stage = new Stage();
        stage.setTitle("Crear Ruta");

        GridPane root = new GridPane();
        root.setPadding(new Insets(10));
        root.setHgap(10);
        root.setVgap(10);

        Label lblCiudadOrigen = new Label("Ciudad de origen:");
        TextField tfCiudadOrigen = new TextField();

        Label lblCiudadDestino = new Label("Ciudad de destino:");
        TextField tfCiudadDestino = new TextField();

        Label lblTarifa = new Label("Tarifa:");
        TextField tfTarifa = new TextField();

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
            String ciudadOrigen = tfCiudadOrigen.getText();
            String ciudadDestino = tfCiudadDestino.getText();
            String tarifaStr = tfTarifa.getText();

            if (ciudadOrigen.isEmpty() || ciudadDestino.isEmpty() || tarifaStr.isEmpty() || archivoImagen[0] == null) {
                // TODO: Show alert
                return;
            }

            double tarifa;
            try {
                tarifa = Double.parseDouble(tarifaStr);
            } catch (NumberFormatException ex) {
                // TODO: Show alert
                return;
            }

            RutaServicio rutaServicio = new RutaServicio();
            int id = rutaServicio.getSerial();
            String idImagen = UUID.randomUUID().toString() + ".jpg";

            try {
                File dest = new File("recursos/imagenes/" + idImagen);
                Files.copy(archivoImagen[0].toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException ex) {
                // TODO: show alert
                return;
            }

            RutaDto rutaDto = new RutaDto(id, ciudadOrigen, ciudadDestino, tarifa, idImagen);
            rutaServicio.inserInto(rutaDto, null);

            tfCiudadOrigen.clear();
            tfCiudadDestino.clear();
            tfTarifa.clear();
            iwImagen.setImage(null);
            archivoImagen[0] = null;
        });

        root.add(lblCiudadOrigen, 0, 0);
        root.add(tfCiudadOrigen, 1, 0);
        root.add(lblCiudadDestino, 0, 1);
        root.add(tfCiudadDestino, 1, 1);
        root.add(lblTarifa, 0, 2);
        root.add(tfTarifa, 1, 2);
        root.add(btnSeleccionarImagen, 0, 3);
        root.add(iwImagen, 1, 3);
        root.add(btnGuardar, 1, 4);

        Scene scene = new Scene(root, 400, 350);
        stage.setScene(scene);
        stage.show();
    }
}
