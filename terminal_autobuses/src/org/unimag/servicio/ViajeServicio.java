package org.unimag.servicio;

import com.poo.persistence.NioFile;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.unimag.api.ApiOperacionBD;
import org.unimag.dto.ViajeDto;
import org.unimag.recurso.constante.Persistencia;

public class ViajeServicio implements ApiOperacionBD<ViajeDto, Integer>{

    private NioFile miArchivo;
    private String nombrePersistencia;

    public ViajeServicio() {

        nombrePersistencia = Persistencia.NOMBRE_VIAJE;

        try {
            miArchivo = new NioFile(nombrePersistencia);
        } catch (IOException ex) {
            Logger.getLogger(ViajeServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public int getSerial() {
        int id = 0;

        try {
            id = miArchivo.ultimoCodigo() + 1;
        } catch (IOException ex) {
            Logger.getLogger(ViajeServicio.class.getName()).log(Level.SEVERE, null, ex);
        }

        return id;
    }

    @Override
    public ViajeDto inserInto(ViajeDto objeto, String ruta) {
        try {
            miArchivo.crearFila(objeto.getIdViaje() + ";" + objeto.getIdRuta() + ";" + objeto.getIdConductor() + ";" + objeto.getIdBus() + ";" + objeto.getFechaViaje() + ";" + objeto.getHoraViaje() + ";" + objeto.getNombreImagenPublicoViaje());
        } catch (IOException ex) {
            Logger.getLogger(ViajeServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return objeto;
    }

    @Override
    public List<ViajeDto> selectFrom() {
        List<ViajeDto> viajes = new java.util.ArrayList<>();
        try {
            List<String> filas = miArchivo.obtenerFilas();
            for (String fila : filas) {
                String[] partes = fila.split(";");
                if (partes.length == 7) {
                    viajes.add(new ViajeDto(Integer.parseInt(partes[0]), Integer.parseInt(partes[1]), partes[2], Integer.parseInt(partes[3]), partes[4], partes[5], partes[6]));
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(ViajeServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return viajes;
    }

    @Override
    public int numRows() {
        int cantidad = 0;
        try {
            cantidad = miArchivo.cantidadFilas();

        } catch (IOException ex) {
            Logger.getLogger(ViajeServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cantidad;
    }

    @Override
    public Boolean deleteFrom(Integer codigo) {
        Boolean correcto = false;
        try {
            List<String> arreglo;

            arreglo = miArchivo.borrarFilaPosicion(codigo);
            if (!arreglo.isEmpty()) {
                correcto = true;
            }
        } catch (IOException ex) {
            Logger.getLogger(ViajeServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return correcto;
}

    @Override
    public ViajeDto updateSet(Integer codigo, ViajeDto objeto, String ruta) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ViajeDto getOne(Integer codigo) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
