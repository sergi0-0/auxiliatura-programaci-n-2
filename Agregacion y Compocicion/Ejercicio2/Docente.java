
package Ejercicio2;

class Docente {
    private String nombre;
    private int carnet;
    private String materia;
    private double salario;

    public Docente(String nombre, int carnet, String materia, double salario) {
        this.nombre = nombre;
        this.carnet = carnet;
        this.materia = materia;
        this.salario = salario;
    }

    public void mostrarDatosPersonales() {
        System.out.println("Docente: " + nombre + ", Carnet: " + carnet);
    }

    public String getMateria() { return materia; }
    public String getNombre() { return nombre; }
    public int getCarnet() { return carnet; }
}