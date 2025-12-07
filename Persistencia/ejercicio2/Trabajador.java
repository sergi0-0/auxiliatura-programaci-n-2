package org.ejemplo.model;
public class Trabajador {
    private String nombre;
    private int carnet;
    private double salario;
    public Trabajador() {}

    public Trabajador(String nombre, int carnet, double salario) {
        this.nombre = nombre;
        this.carnet = carnet;
        this.salario = salario;
    }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public int getCarnet() { return carnet; }
    public void setCarnet(int carnet) { this.carnet = carnet; }

    public double getSalario() { return salario; }
    public void setSalario(double salario) { this.salario = salario; }

    public void aumentarSalario(int porcentaje) {
        this.salario += this.salario * porcentaje / 100.0;
    }
    @Override
    public String toString() {
        return String.format("Trabajador{nombre='%s', carnet=%d, salario=%.2f}", 
                             nombre, carnet, salario);
    }
}