package org.unimag.vista.empresa;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.unimag.dto.EmpresaDto;
import org.unimag.servicio.EmpresaServicio;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

public class VistaEmpresaCrear {

    public static void mostrar() {
        Stage stage = new Stage();
        stage.setTitle("Crear Empresa");

        GridPane root = new GridPane();
        root.setPadding(new Insets(10));
        root.setHgap(10);
        root.setVgap(10);

        Label lblNombre = new Label("Nombre de la empresa:");
        TextField tfNombre = new TextField();

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
            String nombre = tfNombre.getText();
            if (nombre.isEmpty() || archivoImagen[0] == null) {
                // TODO: Show alert
                return;
            }

            EmpresaServicio empresaServicio = new EmpresaServicio();
            int id = empresaServicio.getSerial();
            String idImagen = UUID.randomUUID().toString() + ".jpg";

            try {
                File dest = new File("recursos/imagenes/" + idImagen);
                Files.copy(archivoImagen[0].toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException ex) {
                // TODO: show alert
                return;
            }

            EmpresaDto empresaDto = new EmpresaDto(id, nombre, idImagen, "");
            empresaServicio.inserInto(empresaDto, null);

            tfNombre.clear();
            iwImagen.setImage(null);
            archivoImagen[0] = null;
        });

        root.add(lblNombre, 0, 0);
        root.add(tfNombre, 1, 0);
        root.add(btnSeleccionarImagen, 0, 1);
        root.add(iwImagen, 1, 1);
        root.add(btnGuardar, 1, 2);

        Scene scene = new Scene(root, 400, 250);
        stage.setScene(scene);
        stage.show();
    }
}
