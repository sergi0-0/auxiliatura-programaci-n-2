package org.ejemplo.model;

public class Estudiante {
    private int carnet;
    private String nombre;
    private String paterno;
    private String materno;
    private String materia;
    private double notaFinal;

    public Estudiante() {}

    public Estudiante(int carnet, String nombre, String paterno, String materno, String materia, double notaFinal) {
        this.carnet = carnet;
        this.nombre = nombre;
        this.paterno = paterno;
        this.materno = materno;
        this.materia = materia;
        this.notaFinal = notaFinal;
    }

    public int getCarnet() { return carnet; }
    public void setCarnet(int carnet) { this.carnet = carnet; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getPaterno() { return paterno; }
    public void setPaterno(String paterno) { this.paterno = paterno; }

    public String getMaterno() { return materno; }
    public void setMaterno(String materno) { this.materno = materno; }

    public String getMateria() { return materia; }
    public void setMateria(String materia) { this.materia = materia; }

    public double getNotaFinal() { return notaFinal; }
    public void setNotaFinal(double notaFinal) { this.notaFinal = notaFinal; }

    @Override
    public String toString() {
        return String.format("%-8d %-12s %-10s %-10s %-20s Nota: %.2f",
                carnet, nombre, paterno, materno, materia, notaFinal);
    }
}