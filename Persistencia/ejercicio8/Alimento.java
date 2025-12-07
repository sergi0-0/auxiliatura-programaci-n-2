package org.ejemplo.model;

public class Alimento {
    private String nombre;
    private String fechaVencimiento;
    private int cantidad;

    public Alimento() {} 

    public Alimento(String nombre, String fechaVencimiento, int cantidad) {
        this.nombre = nombre;
        this.fechaVencimiento = fechaVencimiento;
        this.cantidad = cantidad;
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getFechaVencimiento() { return fechaVencimiento; }
    public void setFechaVencimiento(String fechaVencimiento) { this.fechaVencimiento = fechaVencimiento; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    @Override
    public String toString() {
        return String.format("%-20s Vence: %s  Cantidad: %d", nombre, fechaVencimiento, cantidad);
    }
}