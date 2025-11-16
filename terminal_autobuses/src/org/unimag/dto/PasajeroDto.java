package org.unimag.dto;

public class PasajeroDto {
    
    private int cedulaPasajero;
    private String nombrePasajero;
    private Short edadPasajero;
    private Boolean generoPasajero;
    private String nombreImagenPublicoPasajero;
    private String nombreImagenPrivadoPasajero;

    public void setCedulaPasajero(int cedulaPasajero) {
        this.cedulaPasajero = cedulaPasajero;
    }

    public void setNombrePasajero(String nombrePasajero) {
        this.nombrePasajero = nombrePasajero;
    }

    public void setEdadPasajero(Short edadPasajero) {
        this.edadPasajero = edadPasajero;
    }

    public void setGeneroPasajero(Boolean generoPasajero) {
        this.generoPasajero = generoPasajero;
    }

    public void setNombreImagenPublicoPasajero(String nombreImagenPublicoPasajero) {
        this.nombreImagenPublicoPasajero = nombreImagenPublicoPasajero;
    }

    public void setNombreImagenPrivadoPasajero(String nombreImagenPrivadoPasajero) {
        this.nombreImagenPrivadoPasajero = nombreImagenPrivadoPasajero;
    }

    public int getCedulaPasajero() {
        return cedulaPasajero;
    }

    public String getNombrePasajero() {
        return nombrePasajero;
    }

    public Short getEdadPasajero() {
        return edadPasajero;
    }

    public Boolean getGeneroPasajero() {
        return generoPasajero;
    }

    public String getNombreImagenPublicoPasajero() {
        return nombreImagenPublicoPasajero;
    }

    public String getNombreImagenPrivadoPasajero() {
        return nombreImagenPrivadoPasajero;
    }

    public PasajeroDto() {
    }

    public PasajeroDto(int cedulaPasajero, String nombrePasajero, Short edadPasajero, Boolean generoPasajero, String nombreImagenPublicoPasajero, String nombreImagenPrivadoPasajero) {
        this.cedulaPasajero = cedulaPasajero;
        this.nombrePasajero = nombrePasajero;
        this.edadPasajero = edadPasajero;
        this.generoPasajero = generoPasajero;
        this.nombreImagenPublicoPasajero = nombreImagenPublicoPasajero;
        this.nombreImagenPrivadoPasajero = nombreImagenPrivadoPasajero;
    }

    @Override
    public String toString() {
        return "Pasajero:" + "\nNombre=" + nombrePasajero + "\nCedula=" +cedulaPasajero;
    }

}
