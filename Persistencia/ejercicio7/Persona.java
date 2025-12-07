package org.ejemplo.model;

public class Persona {
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private int ci;

    public Persona() {} 

    public Persona(String nombre, String apellidoPaterno, String apellidoMaterno, int ci) {
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.ci = ci;
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellidoPaterno() { return apellidoPaterno; }
    public void setApellidoPaterno(String apellidoPaterno) { this.apellidoPaterno = apellidoPaterno; }

    public String getApellidoMaterno() { return apellidoMaterno; }
    public void setApellidoMaterno(String apellidoMaterno) { this.apellidoMaterno = apellidoMaterno; }

    public int getCi() { return ci; }
    public void setCi(int ci) { this.ci = ci; }

    @Override
    public String toString() {
        return String.format("%-15s %-15s %-15s CI: %d", nombre, apellidoPaterno, apellidoMaterno, ci);
    }
}