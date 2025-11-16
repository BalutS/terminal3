package org.unimag.controlador.Empresa;

import org.unimag.modelo.Empresa;
import org.unimag.servicio.EmpresaServicio;

import java.io.File;
import java.util.UUID;

public class ControladorCrearEmpresa {

    public static void guardar(String nombre, File imagen) {
        String idImagen = UUID.randomUUID().toString() + ".jpg";
        Empresa empresa = new Empresa(
                String.valueOf(System.currentTimeMillis()), // ID
                nombre,
                idImagen
        );

        // TODO: Save image to a specific folder
        EmpresaServicio.guardar(empresa);
    }
}
