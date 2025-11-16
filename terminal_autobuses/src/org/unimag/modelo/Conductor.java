
package org.unimag.modelo;

public class Conductor {
    private int cedulaConductor;
    private String nombreConductor;
    private Short edadConductor;
    private Boolean generoConductor;
    private String nombreImagenPublicoConductor;
    private String nombreImagenPrivadoConductor;

    /**
     * @return the cedulaConductor
     */
    public int getCedulaConductor() {
        return cedulaConductor;
    }

    /**
     * @param cedulaConductor the cedulaConductor to set
     */
    public void setCedulaConductor(int cedulaConductor) {
        this.cedulaConductor = cedulaConductor;
    }

    /**
     * @return the nombreConductor
     */
    public String getNombreConductor() {
        return nombreConductor;
    }

    /**
     * @param nombreConductor the nombreConductor to set
     */
    public void setNombreConductor(String nombreConductor) {
        this.nombreConductor = nombreConductor;
    }

    /**
     * @return the edadConductor
     */
    public Short getEdadConductor() {
        return edadConductor;
    }

    /**
     * @param edadConductor the edadConductor to set
     */
    public void setEdadConductor(Short edadConductor) {
        this.edadConductor = edadConductor;
    }

    /**
     * @return the generoConductor
     */
    public Boolean getGeneroConductor() {
        return generoConductor;
    }

    /**
     * @param generoConductor the generoConductor to set
     */
    public void setGeneroConductor(Boolean generoConductor) {
        this.generoConductor = generoConductor;
    }

    /**
     * @return the nombreImagenPublicoConductor
     */
    public String getNombreImagenPublicoConductor() {
        return nombreImagenPublicoConductor;
    }

    /**
     * @param nombreImagenPublicoConductor the nombreImagenPublicoConductor to set
     */
    public void setNombreImagenPublicoConductor(String nombreImagenPublicoConductor) {
        this.nombreImagenPublicoConductor = nombreImagenPublicoConductor;
    }

    /**
     * @return the nombreImagenPrivadoConductor
     */
    public String getNombreImagenPrivadoConductor() {
        return nombreImagenPrivadoConductor;
    }

    /**
     * @param nombreImagenPrivadoConductor the nombreImagenPrivadoConductor to set
     */
    public void setNombreImagenPrivadoConductor(String nombreImagenPrivadoConductor) {
        this.nombreImagenPrivadoConductor = nombreImagenPrivadoConductor;
    }

    public Conductor(int cedulaConductor, String nombreConductor, Short edadConductor, Boolean generoConductor, String nombreImagenPublicoConductor, String nombreImagenPrivadoConductor) {
        this.cedulaConductor = cedulaConductor;
        this.nombreConductor = nombreConductor;
        this.edadConductor = edadConductor;
        this.generoConductor = generoConductor;
        this.nombreImagenPublicoConductor = nombreImagenPublicoConductor;
        this.nombreImagenPrivadoConductor = nombreImagenPrivadoConductor;
    }

    public Conductor() {
    }

    @Override
    public String toString() {
        return "Conductor{" + "cedulaConductor=" + cedulaConductor + ", nombreConductor=" + nombreConductor + ", edadConductor=" + edadConductor + ", generoConductor=" + generoConductor + ", nombreImagenPublicoConductor=" + nombreImagenPublicoConductor + ", nombreImagenPrivadoConductor=" + nombreImagenPrivadoConductor + '}';
    }
    
}
