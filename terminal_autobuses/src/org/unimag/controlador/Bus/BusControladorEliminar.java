package org.unimag.controlador.Bus;

import org.unimag.servicio.BusServicio;

public class BusControladorEliminar {
    
    public static Boolean borrar(int codigo) {
        Boolean correcto;
        BusServicio busServicio = new BusServicio();
        correcto = busServicio.deleteFrom(codigo);
        return correcto;
    }
    
}
