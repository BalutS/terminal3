package org.unimag.vista.bus;

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
import org.unimag.dto.EmpresaDto;
import org.unimag.servicio.BusServicio;
import org.unimag.servicio.EmpresaServicio;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class VistaBusCrear {

    public static void mostrar() {
        Stage stage = new Stage();
        stage.setTitle("Crear Bus");

        GridPane root = new GridPane();
        root.setPadding(new Insets(10));
        root.setHgap(10);
        root.setVgap(10);

        Label lblModelo = new Label("Modelo del bus:");
        TextField tfModelo = new TextField();

        Label lblEmpresa = new Label("Empresa:");
        ComboBox<String> cbEmpresa = new ComboBox<>();
        EmpresaServicio empresaServicio = new EmpresaServicio();
        List<EmpresaDto> empresas = empresaServicio.selectFrom();
        cbEmpresa.setItems(FXCollections.observableArrayList(
                empresas.stream().map(EmpresaDto::getNombreEmpresa).collect(Collectors.toList())
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
            String modelo = tfModelo.getText();
            String nombreEmpresa = cbEmpresa.getValue();
            if (modelo.isEmpty() || nombreEmpresa == null || archivoImagen[0] == null) {
                // TODO: Show alert
                return;
            }

            BusServicio busServicio = new BusServicio();
            int id = busServicio.getSerial();
            String idImagen = UUID.randomUUID().toString() + ".jpg";

            try {
                File dest = new File("recursos/imagenes/" + idImagen);
                Files.copy(archivoImagen[0].toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException ex) {
                // TODO: show alert
                return;
            }

            EmpresaDto empresaSeleccionada = empresas.stream()
                    .filter(emp -> emp.getNombreEmpresa().equals(nombreEmpresa))
                    .findFirst()
                    .orElse(null);

            if (empresaSeleccionada != null) {
                BusDto busDto = new BusDto(id, modelo, empresaSeleccionada.getIdEmpresa(), idImagen);
                busServicio.inserInto(busDto, null);

                tfModelo.clear();
                cbEmpresa.getSelectionModel().clearSelection();
                iwImagen.setImage(null);
                archivoImagen[0] = null;
            }
        });

        root.add(lblModelo, 0, 0);
        root.add(tfModelo, 1, 0);
        root.add(lblEmpresa, 0, 1);
        root.add(cbEmpresa, 1, 1);
        root.add(btnSeleccionarImagen, 0, 2);
        root.add(iwImagen, 1, 2);
        root.add(btnGuardar, 1, 3);

        Scene scene = new Scene(root, 400, 300);
        stage.setScene(scene);
        stage.show();
    }
}
