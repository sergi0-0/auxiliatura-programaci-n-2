package org.ejemplo.model;

public class Libro {
    private String codLibro;
    private String titulo;
    private double precio;

    public Libro() {} 

    public Libro(String codLibro, String titulo, double precio) {
        this.codLibro = codLibro;
        this.titulo = titulo;
        this.precio = precio;
    }

    public String getCodLibro() { return codLibro; }
    public void setCodLibro(String codLibro) { this.codLibro = codLibro; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }

    @Override
    public String toString() {
        return String.format("%-10s %-35s %.2f", codLibro, titulo, precio);
    }
}