package org.unimag.vista.pasajero;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.unimag.dto.PasajeroDto;
import org.unimag.servicio.PasajeroServicio;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

public class VistaPasajeroCrear {

    public static void mostrar() {
        Stage stage = new Stage();
        stage.setTitle("Crear Pasajero");

        GridPane root = new GridPane();
        root.setPadding(new Insets(10));
        root.setHgap(10);
        root.setVgap(10);

        Label lblCedula = new Label("Cédula:");
        TextField tfCedula = new TextField();

        Label lblNombre = new Label("Nombre:");
        TextField tfNombre = new TextField();

        Label lblEdad = new Label("Edad:");
        TextField tfEdad = new TextField();

        Label lblGenero = new Label("Género:");
        ToggleGroup groupGenero = new ToggleGroup();
        RadioButton rbMasculino = new RadioButton("Masculino");
        rbMasculino.setToggleGroup(groupGenero);
        RadioButton rbFemenino = new RadioButton("Femenino");
        rbFemenino.setToggleGroup(groupGenero);
        HBox hboxGenero = new HBox(10, rbMasculino, rbFemenino);

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
            String cedula = tfCedula.getText();
            String nombre = tfNombre.getText();
            String edadStr = tfEdad.getText();
            RadioButton generoSeleccionado = (RadioButton) groupGenero.getSelectedToggle();

            if (cedula.isEmpty() || nombre.isEmpty() || edadStr.isEmpty() || generoSeleccionado == null || archivoImagen[0] == null) {
                // TODO: Show alert
                return;
            }

            int edad;
            try {
                edad = Integer.parseInt(edadStr);
            } catch (NumberFormatException ex) {
                // TODO: Show alert
                return;
            }

            boolean esMasculino = generoSeleccionado.getText().equals("Masculino");
            String idImagen = UUID.randomUUID().toString() + ".jpg";

            try {
                File dest = new File("recursos/imagenes/" + idImagen);
                Files.copy(archivoImagen[0].toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException ex) {
                // TODO: show alert
                return;
            }
            PasajeroServicio pasajeroServicio = new PasajeroServicio();
            PasajeroDto pasajeroDto = new PasajeroDto(cedula, nombre, edad, esMasculino, idImagen);
            pasajeroServicio.inserInto(pasajeroDto, null);

            tfCedula.clear();
            tfNombre.clear();
            tfEdad.clear();
            groupGenero.selectToggle(null);
            iwImagen.setImage(null);
            archivoImagen[0] = null;
        });

        root.add(lblCedula, 0, 0);
        root.add(tfCedula, 1, 0);
        root.add(lblNombre, 0, 1);
        root.add(tfNombre, 1, 1);
        root.add(lblEdad, 0, 2);
        root.add(tfEdad, 1, 2);
        root.add(lblGenero, 0, 3);
        root.add(hboxGenero, 1, 3);
        root.add(btnSeleccionarImagen, 0, 4);
        root.add(iwImagen, 1, 4);
        root.add(btnGuardar, 1, 5);

        Scene scene = new Scene(root, 400, 400);
        stage.setScene(scene);
        stage.show();
    }
}
