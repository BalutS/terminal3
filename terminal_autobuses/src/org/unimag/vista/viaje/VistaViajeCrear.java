package org.unimag.vista.viaje;

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
import org.unimag.dto.BusDto;
import org.unimag.dto.ConductorDto;
import org.unimag.dto.RutaDto;
import org.unimag.dto.ViajeDto;
import org.unimag.servicio.BusServicio;
import org.unimag.servicio.ConductorServicio;
import org.unimag.servicio.RutaServicio;
import org.unimag.servicio.ViajeServicio;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class VistaViajeCrear {

    public static void mostrar() {
        Stage stage = new Stage();
        stage.setTitle("Crear Viaje");

        GridPane root = new GridPane();
        root.setPadding(new Insets(10));
        root.setHgap(10);
        root.setVgap(10);

        Label lblRuta = new Label("Ruta:");
        ComboBox<String> cbRuta = new ComboBox<>();
        RutaServicio rutaServicio = new RutaServicio();
        List<RutaDto> rutas = rutaServicio.selectFrom();
        cbRuta.setItems(FXCollections.observableArrayList(
                rutas.stream().map(ruta -> ruta.getCiudadOrigenRuta() + "-" + ruta.getCiudadDestinoRuta()).collect(Collectors.toList())
        ));

        Label lblConductor = new Label("Conductor:");
        ComboBox<String> cbConductor = new ComboBox<>();
        ConductorServicio conductorServicio = new ConductorServicio();
        List<ConductorDto> conductores = conductorServicio.selectFrom();
        cbConductor.setItems(FXCollections.observableArrayList(
                conductores.stream().map(ConductorDto::getNombreConductor).collect(Collectors.toList())
        ));

        Label lblBus = new Label("Bus:");
        ComboBox<String> cbBus = new ComboBox<>();
        BusServicio busServicio = new BusServicio();
        List<BusDto> buses = busServicio.selectFrom();
        cbBus.setItems(FXCollections.observableArrayList(
                buses.stream().map(bus -> bus.getIdBus() + ", " + bus.getModeloBus()).collect(Collectors.toList())
        ));

        Label lblFecha = new Label("Fecha:");
        TextField tfFecha = new TextField();

        Label lblHora = new Label("Hora:");
        TextField tfHora = new TextField();

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
            String rutaSeleccionadaStr = cbRuta.getValue();
            String conductorSeleccionadoStr = cbConductor.getValue();
            String busSeleccionadoStr = cbBus.getValue();
            String fecha = tfFecha.getText();
            String hora = tfHora.getText();

            if (rutaSeleccionadaStr == null || conductorSeleccionadoStr == null || busSeleccionadoStr == null || fecha.isEmpty() || hora.isEmpty() || archivoImagen[0] == null) {
                // TODO: Show alert
                return;
            }

            RutaDto rutaSeleccionada = rutas.stream()
                    .filter(ruta -> (ruta.getCiudadOrigenRuta() + "-" + ruta.getCiudadDestinoRuta()).equals(rutaSeleccionadaStr))
                    .findFirst()
                    .orElse(null);

            ConductorDto conductorSeleccionado = conductores.stream()
                    .filter(conductor -> conductor.getNombreConductor().equals(conductorSeleccionadoStr))
                    .findFirst()
                    .orElse(null);

            int idBus = Integer.parseInt(busSeleccionadoStr.split(",")[0].trim());
            String idImagen = UUID.randomUUID().toString() + ".jpg";

            try {
                File dest = new File("recursos/imagenes/" + idImagen);
                Files.copy(archivoImagen[0].toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException ex) {
                // TODO: show alert
                return;
            }

            if (rutaSeleccionada != null && conductorSeleccionado != null) {
                ViajeServicio viajeServicio = new ViajeServicio();
                int id = viajeServicio.getSerial();
                ViajeDto viajeDto = new ViajeDto(id, rutaSeleccionada.getIdRuta(), conductorSeleccionado.getCedulaConductor(), idBus, fecha, hora, idImagen);
                viajeServicio.inserInto(viajeDto, null);

                cbRuta.getSelectionModel().clearSelection();
                cbConductor.getSelectionModel().clearSelection();
                cbBus.getSelectionModel().clearSelection();
                tfFecha.clear();
                tfHora.clear();
                iwImagen.setImage(null);
                archivoImagen[0] = null;
            }
        });

        root.add(lblRuta, 0, 0);
        root.add(cbRuta, 1, 0);
        root.add(lblConductor, 0, 1);
        root.add(cbConductor, 1, 1);
        root.add(lblBus, 0, 2);
        root.add(cbBus, 1, 2);
        root.add(lblFecha, 0, 3);
        root.add(tfFecha, 1, 3);
        root.add(lblHora, 0, 4);
        root.add(tfHora, 1, 4);
        root.add(btnSeleccionarImagen, 0, 5);
        root.add(iwImagen, 1, 5);
        root.add(btnGuardar, 1, 6);

        Scene scene = new Scene(root, 400, 450);
        stage.setScene(scene);
        stage.show();
    }
}
