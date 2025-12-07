package org.ejemplo.model;

public class Prestamo {
    private String codCliente;
    private String codLibro;
    private String fechaPrestamo;
    private int cantidad;

    public Prestamo() {}

    public Prestamo(String codCliente, String codLibro, String fechaPrestamo, int cantidad) {
        this.codCliente = codCliente;
        this.codLibro = codLibro;
        this.fechaPrestamo = fechaPrestamo;
        this.cantidad = cantidad;
    }

    public String getCodCliente() { return codCliente; }
    public void setCodCliente(String codCliente) { this.codCliente = codCliente; }

    public String getCodLibro() { return codLibro; }
    public void setCodLibro(String codLibro) { this.codLibro = codLibro; }

    public String getFechaPrestamo() { return fechaPrestamo; }
    public void setFechaPrestamo(String fechaPrestamo) { this.fechaPrestamo = fechaPrestamo; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    @Override
    public String toString() {
        return String.format("Cliente:%s  Libro:%s  Fecha:%s  Cant:%d",
                codCliente, codLibro, fechaPrestamo, cantidad);
    }
}