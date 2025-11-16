package org.unimag.servicio;

import com.poo.persistence.NioFile;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.unimag.api.ApiOperacionBD;
import org.unimag.dto.EmpresaDto;
import org.unimag.recurso.constante.Persistencia;

public class EmpresaServicio implements ApiOperacionBD<EmpresaDto, Integer>{

    private NioFile miArchivo;
    private String nombrePersistencia;

    public EmpresaServicio() {

        nombrePersistencia = Persistencia.NOMBRE_EMPRESA;

        try {
            miArchivo = new NioFile(nombrePersistencia);
        } catch (IOException ex) {
            Logger.getLogger(EmpresaServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public int getSerial() {
        int id = 0;

        try {
            id = miArchivo.ultimoCodigo() + 1;
        } catch (IOException ex) {
            Logger.getLogger(EmpresaServicio.class.getName()).log(Level.SEVERE, null, ex);
        }

        return id;
    }

    @Override
    public EmpresaDto inserInto(EmpresaDto objeto, String ruta) {
        try {
            miArchivo.crearFila(objeto.getIdEmpresa() + ";" + objeto.getNombreEmpresa() + ";" + objeto.getNombreImagenPublicoEmpresa());
        } catch (IOException ex) {
            Logger.getLogger(EmpresaServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return objeto;
    }

    @Override
    public List<EmpresaDto> selectFrom() {
        List<EmpresaDto> empresas = new java.util.ArrayList<>();
        try {
            List<String> filas = miArchivo.obtenerFilas();
            for (String fila : filas) {
                String[] partes = fila.split(";");
                if (partes.length == 3) {
                    empresas.add(new EmpresaDto(Integer.parseInt(partes[0]), partes[1], partes[2], ""));
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(EmpresaServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return empresas;
    }

    @Override
    public int numRows() {
        int cantidad = 0;
        try {
            cantidad = miArchivo.cantidadFilas();

        } catch (IOException ex) {
            Logger.getLogger(EmpresaServicio.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(EmpresaServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return correcto;
    }

    @Override
    public EmpresaDto updateSet(Integer codigo, EmpresaDto objeto, String ruta) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public EmpresaDto getOne(Integer codigo) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
