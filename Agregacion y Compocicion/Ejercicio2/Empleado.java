package Ejercicio2;
class Empleado {
    private String nombre;
    private int carnet;
    private String cargo;

    public Empleado(String nombre, int carnet, String cargo) {
        this.nombre = nombre;
        this.carnet = carnet;
        this.cargo = cargo;
    }

    public void mostrarDatosPersonales() {
        System.out.println("Empleado: " + nombre + ", Carnet: " + carnet);
    }

    public String getNombre() { return nombre; }
    public int getCarnet() { return carnet; }
}