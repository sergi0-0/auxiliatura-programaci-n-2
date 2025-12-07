package org.ejemplo.model;

public class Medicamento {
    private String nombre;
    private String codMedicamento;
    private String tipo;
    private double precio;

    public Medicamento() {}

    public Medicamento(String nombre, String codMedicamento, String tipo, double precio) {
        this.nombre = nombre;
        this.codMedicamento = codMedicamento;
        this.tipo = tipo;
        this.precio = precio;
    }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCodMedicamento() { return codMedicamento; }
    public void setCodMedicamento(String codMedicamento) { this.codMedicamento = codMedicamento; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }

    @Override
    public String toString() {
        return String.format("%-20s %-12s %-10s %.2f", nombre, codMedicamento, tipo, precio);
    }
}