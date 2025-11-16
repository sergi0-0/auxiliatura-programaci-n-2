package Ejercicio8;
import java.util.*;
class Persona {
    protected String nombre;
    protected int carnet;
    protected int edad;

    public Persona(String nombre, int carnet, int edad) {
        this.nombre = nombre;
        this.carnet = carnet;
        this.edad = edad;
    }

    @Override
    public String toString() {
        return nombre + " (CI: " + carnet + ", Edad: " + edad + ")";
    }

    public String getNombre() { return nombre; }
    public int getCarnet() { return carnet; }
    public int getEdad() { return edad; }
}

