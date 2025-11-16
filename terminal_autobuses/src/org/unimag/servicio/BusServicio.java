package org.unimag.servicio;

import com.poo.persistence.NioFile;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.unimag.api.ApiOperacionBD;
import org.unimag.dto.BusDto;
import org.unimag.recurso.constante.Persistencia;

public class BusServicio implements ApiOperacionBD<BusDto, Integer>{

    private NioFile miArchivo;
    private String nombrePersistencia;

    public BusServicio() {

        nombrePersistencia = Persistencia.NOMBRE_BUS;

        try {
            miArchivo = new NioFile(nombrePersistencia);
        } catch (IOException ex) {
            Logger.getLogger(BusServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public int getSerial() {
        int id = 0;

        try {
            id = miArchivo.ultimoCodigo() + 1;
        } catch (IOException ex) {
            Logger.getLogger(BusServicio.class.getName()).log(Level.SEVERE, null, ex);
        }

        return id;
    }

    @Override
    public BusDto inserInto(BusDto objeto, String ruta) {
        try {
            miArchivo.crearFila(objeto.getIdBus() + ";" + objeto.getModeloBus() + ";" + objeto.getIdEmpresa() + ";" + objeto.getNombreImagenPublicoBus());
        } catch (IOException ex) {
            Logger.getLogger(BusServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return objeto;
    }

    @Override
    public List<BusDto> selectFrom() {
        List<BusDto> buses = new java.util.ArrayList<>();
        try {
            List<String> filas = miArchivo.obtenerFilas();
            for (String fila : filas) {
                String[] partes = fila.split(";");
                if (partes.length == 4) {
                    buses.add(new BusDto(Integer.parseInt(partes[0]), partes[1], Integer.parseInt(partes[2]), partes[3]));
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(BusServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return buses;
    }

    @Override
    public int numRows() {
        int cantidad = 0;
        try {
            cantidad = miArchivo.cantidadFilas();

        } catch (IOException ex) {
            Logger.getLogger(BusServicio.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(BusServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return correcto;
    }

    @Override
    public BusDto updateSet(Integer codigo, BusDto objeto, String ruta) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public BusDto getOne(Integer codigo) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
