package org.unimag.dto;

public class AsientoDto {
    
    private int idAsiento;
    private BusDto busAsiento;
    private Boolean estadoAsiento;
    private String nombreImagenPublicoAsiento;
    private String nombreImagenPrivadoAsiento;

    @Override
    public String toString() {
        return "Asiento: " + "ID=" + idAsiento;
    }

    public void setIdAsiento(int idAsiento) {
        this.idAsiento = idAsiento;
    }

    public void setBusAsiento(BusDto busAsiento) {
        this.busAsiento = busAsiento;
    }

    public void setEstadoAsiento(Boolean estadoAsiento) {
        this.estadoAsiento = estadoAsiento;
    }

    public void setNombreImagenPublicoAsiento(String nombreImagenPublicoAsiento) {
        this.nombreImagenPublicoAsiento = nombreImagenPublicoAsiento;
    }

    public void setNombreImagenPrivadoAsiento(String nombreImagenPrivadoAsiento) {
        this.nombreImagenPrivadoAsiento = nombreImagenPrivadoAsiento;
    }

    public int getIdAsiento() {
        return idAsiento;
    }

    public BusDto getBusAsiento() {
        return busAsiento;
    }

    public Boolean getEstadoAsiento() {
        return estadoAsiento;
    }

    public String getNombreImagenPublicoAsiento() {
        return nombreImagenPublicoAsiento;
    }

    public String getNombreImagenPrivadoAsiento() {
        return nombreImagenPrivadoAsiento;
    }

    public AsientoDto() {
    }

    public AsientoDto(int idAsiento, BusDto busAsiento, Boolean estadoAsiento, String nombreImagenPublicoAsiento, String nombreImagenPrivadoAsiento) {
        this.idAsiento = idAsiento;
        this.busAsiento = busAsiento;
        this.estadoAsiento = estadoAsiento;
        this.nombreImagenPublicoAsiento = nombreImagenPublicoAsiento;
        this.nombreImagenPrivadoAsiento = nombreImagenPrivadoAsiento;
    }

    
}
