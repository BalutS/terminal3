package org.unimag.vista.tiquete;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.unimag.dto.AsientoDto;
import org.unimag.dto.PasajeroDto;
import org.unimag.dto.TiqueteDto;
import org.unimag.dto.ViajeDto;
import org.unimag.servicio.AsientoServicio;
import org.unimag.servicio.PasajeroServicio;
import org.unimag.servicio.TiqueteServicio;
import org.unimag.servicio.ViajeServicio;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class VistaTiqueteCrear {

    public static void mostrar() {
        Stage stage = new Stage();
        stage.setTitle("Crear Tiquete");

        GridPane root = new GridPane();
        root.setPadding(new Insets(10));
        root.setHgap(10);
        root.setVgap(10);

        Label lblPasajero = new Label("Pasajero:");
        ComboBox<String> cbPasajero = new ComboBox<>();
        PasajeroServicio pasajeroServicio = new PasajeroServicio();
        List<PasajeroDto> pasajeros = pasajeroServicio.selectFrom();
        cbPasajero.setItems(FXCollections.observableArrayList(
                pasajeros.stream().map(PasajeroDto::getNombrePasajero).collect(Collectors.toList())
        ));

        Label lblViaje = new Label("Viaje:");
        ComboBox<String> cbViaje = new ComboBox<>();
        ViajeServicio viajeServicio = new ViajeServicio();
        List<ViajeDto> viajes = viajeServicio.selectFrom();
        cbViaje.setItems(FXCollections.observableArrayList(
                viajes.stream().map(viaje -> viaje.getIdViaje() + ", " + viaje.getFechaViaje() + ", " + viaje.getHoraViaje()).collect(Collectors.toList())
        ));

        Label lblAsiento = new Label("Asiento:");
        ComboBox<Integer> cbAsiento = new ComboBox<>();
        AsientoServicio asientoServicio = new AsientoServicio();
        List<AsientoDto> asientos = asientoServicio.selectFrom();
        cbAsiento.setItems(FXCollections.observableArrayList(
                asientos.stream().map(AsientoDto::getIdAsiento).collect(Collectors.toList())
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
            String pasajeroSeleccionadoStr = cbPasajero.getValue();
            String viajeSeleccionadoStr = cbViaje.getValue();
            Integer asientoSeleccionado = cbAsiento.getValue();

            if (pasajeroSeleccionadoStr == null || viajeSeleccionadoStr == null || asientoSeleccionado == null || archivoImagen[0] == null) {
                // TODO: Show alert
                return;
            }

            PasajeroDto pasajeroSeleccionado = pasajeros.stream()
                    .filter(pasajero -> pasajero.getNombrePasajero().equals(pasajeroSeleccionadoStr))
                    .findFirst()
                    .orElse(null);

            int idViaje = Integer.parseInt(viajeSeleccionadoStr.split(",")[0].trim());

            if (pasajeroSeleccionado != null) {
                String idImagen = UUID.randomUUID().toString() + ".jpg";

                try {
                    File dest = new File("recursos/imagenes/" + idImagen);
                    Files.copy(archivoImagen[0].toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException ex) {
                    // TODO: show alert
                    return;
                }

                TiqueteServicio tiqueteServicio = new TiqueteServicio();
                int id = tiqueteServicio.getSerial();
                TiqueteDto tiqueteDto = new TiqueteDto(id, pasajeroSeleccionado.getCedulaPasajero(), idViaje, asientoSeleccionado, idImagen);
                tiqueteServicio.inserInto(tiqueteDto, null);

                cbPasajero.getSelectionModel().clearSelection();
                cbViaje.getSelectionModel().clearSelection();
                cbAsiento.getSelectionModel().clearSelection();
                iwImagen.setImage(null);
                archivoImagen[0] = null;
            }
        });

        root.add(lblPasajero, 0, 0);
        root.add(cbPasajero, 1, 0);
        root.add(lblViaje, 0, 1);
        root.add(cbViaje, 1, 1);
        root.add(lblAsiento, 0, 2);
        root.add(cbAsiento, 1, 2);
        root.add(btnSeleccionarImagen, 0, 3);
        root.add(iwImagen, 1, 3);
        root.add(btnGuardar, 1, 4);

        Scene scene = new Scene(root, 400, 350);
        stage.setScene(scene);
        stage.show();
    }
}
