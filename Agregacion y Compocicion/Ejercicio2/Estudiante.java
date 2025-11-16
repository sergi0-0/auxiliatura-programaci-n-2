package Ejercicio2;
class Estudiante {
    private String nombre;
    private int carnet;
    private String materia;
    private double nota;

    public Estudiante(String nombre, int carnet, String materia, double nota) {
        this.nombre = nombre;
        this.carnet = carnet;
        this.materia = materia;
        this.nota = nota;
    }

    public void mostrarDatosPersonales() {
        System.out.println("Estudiante: " + nombre + ", Carnet: " + carnet);
    }

    public String getMateria() { return materia; }
    public String getNombre() { return nombre; }
    public int getCarnet() { return carnet; }
}