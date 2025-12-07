package org.ejemplo.model;

public class Cliente {
    private String codCliente;
    private String ci;
    private String nombre;
    private String apellido;

    public Cliente() {}

    public Cliente(String codCliente, String ci, String nombre, String apellido) {
        this.codCliente = codCliente;
        this.ci = ci;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public String getCodCliente() { return codCliente; }
    public String getCi() { return ci; }
    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }

    public void setCodCliente(String codCliente) { this.codCliente = codCliente; }
    public void setCi(String ci) { this.ci = ci; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    @Override
    public String toString() {
        return String.format("%-10s %-12s %-15s %s", codCliente, ci, nombre, apellido);
    }
}