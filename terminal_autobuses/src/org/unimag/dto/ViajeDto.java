/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.unimag.dto;

/**
 *
 * @author Juan
 */
public class ViajeDto {
    
    private int idViaje;
    private RutaDto rutaViaje;
    private ConductorDto conductorViaje;
    private BusDto busViaje;
    private String fechaViaje;
    private int horaViaje;
    private String nombreImagenPublicoViaje;
    private String nombreImagenPrivadoViaje;

    public void setIdViaje(int idViaje) {
        this.idViaje = idViaje;
    }

    public void setRutaViaje(RutaDto rutaViaje) {
        this.rutaViaje = rutaViaje;
    }

    public void setConductorViaje(ConductorDto conductorViaje) {
        this.conductorViaje = conductorViaje;
    }

    public void setBusViaje(BusDto busViaje) {
        this.busViaje = busViaje;
    }

    public void setFechaViaje(String fechaViaje) {
        this.fechaViaje = fechaViaje;
    }

    public void setHoraViaje(int horaViaje) {
        this.horaViaje = horaViaje;
    }

    public void setNombreImagenPublicoViaje(String nombreImagenPublicoViaje) {
        this.nombreImagenPublicoViaje = nombreImagenPublicoViaje;
    }

    public void setNombreImagenPrivadoViaje(String nombreImagenPrivadoViaje) {
        this.nombreImagenPrivadoViaje = nombreImagenPrivadoViaje;
    }

    public int getIdViaje() {
        return idViaje;
    }

    public RutaDto getRutaViaje() {
        return rutaViaje;
    }

    public ConductorDto getConductorViaje() {
        return conductorViaje;
    }

    public BusDto getBusViaje() {
        return busViaje;
    }

    public String getFechaViaje() {
        return fechaViaje;
    }

    public int getHoraViaje() {
        return horaViaje;
    }

    public String getNombreImagenPublicoViaje() {
        return nombreImagenPublicoViaje;
    }

    public String getNombreImagenPrivadoViaje() {
        return nombreImagenPrivadoViaje;
    }

    public ViajeDto() {
    }

    public ViajeDto(int idViaje, RutaDto rutaViaje, ConductorDto conductorViaje, BusDto busViaje, String fechaViaje, int horaViaje, String nombreImagenPublicoViaje, String nombreImagenPrivadoViaje) {
        this.idViaje = idViaje;
        this.rutaViaje = rutaViaje;
        this.conductorViaje = conductorViaje;
        this.busViaje = busViaje;
        this.fechaViaje = fechaViaje;
        this.horaViaje = horaViaje;
        this.nombreImagenPublicoViaje = nombreImagenPublicoViaje;
        this.nombreImagenPrivadoViaje = nombreImagenPrivadoViaje;
    }

    @Override
    public String toString() {
        return "Viaje:" + "ID=" + idViaje;
    }
    
    
}
