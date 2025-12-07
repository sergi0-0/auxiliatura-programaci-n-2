package org.finalcarrera.model;

public class Empresa {
    private String nombre;
    private String rubro;
    private int numeroEmpleados;

    public Empresa() {}

    public Empresa(String nombre, String rubro, int numeroEmpleados) {
        this.nombre = nombre;
        this.rubro = rubro;
        this.numeroEmpleados = numeroEmpleados;
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getRubro() { return rubro; }
    public void setRubro(String rubro) { this.rubro = rubro; }

    public int getNumeroEmpleados() { return numeroEmpleados; }
    public void setNumeroEmpleados(int numeroEmpleados) { this.numeroEmpleados = numeroEmpleados; }

    @Override
    public String toString() {
        return nombre + "|" + rubro + "|" + numeroEmpleados;
    }

    public String toStringBonito() {
        return String.format("%-30s | %-20s | %,d empleados", 
                             nombre, rubro, numeroEmpleados);
    }
}