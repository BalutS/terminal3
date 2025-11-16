package org.unimag.vista.gestor;

import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.SubScene;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.event.ActionEvent;
import javafx.scene.layout.Region;
import javafx.scene.control.Button;
import javafx.scene.layout.Priority;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.MenuButton;
import org.unimag.recurso.utilidad.Icono;
import org.unimag.controlador.varios.ControladorSalida;
import org.unimag.recurso.constante.Configuracion;

public class VistaCabecera extends SubScene {

    private final int menuAncho = 160;
    private final int menuAlto = 35;
    private final int espacioX = 15;

    private Pane miPanelCuerpo;
    private final Stage miEscenario;
    private final HBox miPanelCabecera;
    private final BorderPane miPanelPrincipal;

    public VistaCabecera(
            Stage esce,
            BorderPane prin,
            Pane pane,
            double anchoPanel,
            double altoCabecera) {
        super(new HBox(), anchoPanel, altoCabecera);
        miPanelCabecera = (HBox) this.getRoot();
        miPanelCabecera.setAlignment(Pos.CENTER_LEFT);

        miPanelPrincipal = prin;
        miPanelCuerpo = pane;
        miEscenario = esce;

        miPanelCabecera.setSpacing(espacioX);
        miPanelCabecera.setPadding(new Insets(0, 30, 0, 30));
        miPanelCabecera.setStyle(Configuracion.CABECERA_ESTILO_FONDO);//se empezo a utilizar la configuracion

        crearOpciones();
    }

    public HBox getMiPanelCabecera() {
        return miPanelCabecera;
    }

    private void crearOpciones() {
        menuEmpresas();
        menuBuses();
        menuAsientos();
        menuRutas();
        menuConductores();
        menuPasajeros();
        menuEquipajes();
        menuViajes();
        menuTiquetes();
        btnSalir();
        btnAcerca(400, 450);
    }

    private void agregarMenu(MenuButton menu) {
        menu.setCursor(Cursor.HAND);
        menu.setPrefWidth(menuAncho);
        miPanelCabecera.getChildren().add(menu);
    }

    private void menuAsientos() {
        MenuButton menuButton = new MenuButton("Asientos");
        menuButton.setPrefWidth(menuAncho);
        menuButton.setPrefHeight(menuAlto);
        menuButton.setGraphic(Icono.obtenerIcono("IconoAsiento.png", 25));
        MenuItem crearAsiento = new MenuItem("Crear Asientos");
        crearAsiento.setOnAction(e -> org.unimag.vista.asiento.VistaAsientoCrear.mostrar());
        menuButton.getItems().addAll(crearAsiento, new MenuItem("Listar Asientos"), new MenuItem("Administrar Asientos"), new MenuItem("Carrusel Asientos"));
        agregarMenu(menuButton);
    }

    private void menuBuses() {
        MenuButton menuButton = new MenuButton("Buses");
        menuButton.setPrefWidth(menuAncho);
        menuButton.setPrefHeight(menuAlto);
        menuButton.setGraphic(Icono.obtenerIcono("IconoBus.png", 25));
        MenuItem crearBus = new MenuItem("Crear Buses");
        crearBus.setOnAction(e -> org.unimag.vista.bus.VistaBusCrear.mostrar());
        menuButton.getItems().addAll(crearBus, new MenuItem("Listar Buses"), new MenuItem("Administrar Buses"), new MenuItem("Carrusel Buses"));
        agregarMenu(menuButton);
    }

    private void menuConductores() {
        MenuButton menuButton = new MenuButton("Conductores");
        menuButton.setPrefWidth(menuAncho);
        menuButton.setPrefHeight(menuAlto);
        menuButton.setGraphic(Icono.obtenerIcono("IconoConductor.png", 25));
        menuButton.getItems().addAll(new MenuItem("Crear Conductores"), new MenuItem("Listar Conductores"), new MenuItem("Administrar Conductores"), new MenuItem("Carrusel Conductores"));
        agregarMenu(menuButton);
    }

    private void menuEmpresas() {
        MenuButton menuButton = new MenuButton("Empresas");
        menuButton.setPrefWidth(menuAncho);
        menuButton.setPrefHeight(menuAlto);
        menuButton.setGraphic(Icono.obtenerIcono("IconoEmpresa.png", 25));
        MenuItem crearEmpresa = new MenuItem("Crear Empresas");
        crearEmpresa.setOnAction(e -> org.unimag.vista.empresa.VistaEmpresaCrear.mostrar());
        menuButton.getItems().addAll(crearEmpresa, new MenuItem("Listar Empresas"), new MenuItem("Administrar Empresas"), new MenuItem("Carrusel Empresas"));
        agregarMenu(menuButton);
    }

    private void menuEquipajes() {
        MenuButton menuButton = new MenuButton("Equipajes");
        menuButton.setPrefWidth(menuAncho);
        menuButton.setPrefHeight(menuAlto);
        menuButton.setGraphic(Icono.obtenerIcono("IconoEquipaje.png", 25));
        MenuItem crearEquipaje = new MenuItem("Crear Equipajes");
        crearEquipaje.setOnAction(e -> org.unimag.vista.equipaje.VistaEquipajeCrear.mostrar());
        menuButton.getItems().addAll(crearEquipaje, new MenuItem("Listar Equipajes"), new MenuItem("Administrar Equipajes"), new MenuItem("Carrusel Equipajes"));
        agregarMenu(menuButton);
    }

    private void menuPasajeros() {
        MenuButton menuButton = new MenuButton("Pasajeros");
        menuButton.setPrefWidth(menuAncho);
        menuButton.setPrefHeight(menuAlto);
        menuButton.setGraphic(Icono.obtenerIcono("IconoPasajero.png", 25));
        MenuItem crearPasajero = new MenuItem("Crear Pasajeros");
        crearPasajero.setOnAction(e -> org.unimag.vista.pasajero.VistaPasajeroCrear.mostrar());
        menuButton.getItems().addAll(crearPasajero, new MenuItem("Listar Pasajeros"), new MenuItem("Administrar Pasajeros"), new MenuItem("Carrusel Pasajeros"));
        agregarMenu(menuButton);
    }

    private void menuRutas() {
        MenuButton menuButton = new MenuButton("Rutas");
        menuButton.setPrefWidth(menuAncho);
        menuButton.setPrefHeight(menuAlto);
        menuButton.setGraphic(Icono.obtenerIcono("IconoRuta.png", 25));
        MenuItem crearRuta = new MenuItem("Crear Rutas");
        crearRuta.setOnAction(e -> org.unimag.vista.ruta.VistaRutaCrear.mostrar());
        menuButton.getItems().addAll(crearRuta, new MenuItem("Listar Rutas"), new MenuItem("Administrar Rutas"), new MenuItem("Carrusel Rutas"));
        agregarMenu(menuButton);
    }

    private void menuTiquetes() {
        MenuButton menuButton = new MenuButton("Tiquetes");
        menuButton.setPrefWidth(menuAncho);
        menuButton.setPrefHeight(menuAlto);
        menuButton.setGraphic(Icono.obtenerIcono("IconoTiquete.png", 25));
        MenuItem crearTiquete = new MenuItem("Crear Tiquetes");
        crearTiquete.setOnAction(e -> org.unimag.vista.tiquete.VistaTiqueteCrear.mostrar());
        menuButton.getItems().addAll(crearTiquete, new MenuItem("Listar Tiquetes"), new MenuItem("Administrar Tiquetes"), new MenuItem("Carrusel Tiquetes"));
        agregarMenu(menuButton);
    }

    private void menuViajes() {
        MenuButton menuButton = new MenuButton("Viajes");
        menuButton.setPrefWidth(menuAncho);
        menuButton.setPrefHeight(menuAlto);
        menuButton.setGraphic(Icono.obtenerIcono("IconoViaje.png", 25));
        MenuItem crearViaje = new MenuItem("Crear Viajes");
        crearViaje.setOnAction(e -> org.unimag.vista.viaje.VistaViajeCrear.mostrar());
        menuButton.getItems().addAll(crearViaje, new MenuItem("Listar Viajes"), new MenuItem("Administrar Viajes"), new MenuItem("Carrusel Viajes"));
        agregarMenu(menuButton);
    }

    private void btnSalir() {
        Button btnSalir = new Button("Salir");
        btnSalir.setCursor(Cursor.HAND);
        btnSalir.setPrefWidth(menuAncho);
        btnSalir.setPrefHeight(menuAlto);
        btnSalir.setOnAction((ActionEvent event) -> {
            event.consume();
            ControladorSalida.verificar(miEscenario);
        });
        miPanelCabecera.getChildren().add(btnSalir);
    }

    private void btnAcerca(double anchoFlotante, double altoFlotante) {
        Button botonAyuda = new Button("?");
        botonAyuda.setOnAction((ActionEvent event) -> {
            VistaAcerca.mostrar(miEscenario, anchoFlotante, altoFlotante);
        });
        botonAyuda.setPrefWidth(30);
        botonAyuda.setId("btn-ayuda");
        botonAyuda.setCursor(Cursor.HAND);
        botonAyuda.getStylesheets().add(getClass().getResource("/org/unimag/recurso/estilo/BtnAcerca.css").toExternalForm());
        Region espacio = new Region();
        HBox.setHgrow(espacio, Priority.ALWAYS);
        miPanelCabecera.getChildren().add(espacio);
        miPanelCabecera.getChildren().add(botonAyuda);
    }
}