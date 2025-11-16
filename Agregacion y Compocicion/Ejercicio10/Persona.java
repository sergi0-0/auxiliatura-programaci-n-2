/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicio10;
class Persona {
    protected String nombre;
    protected String apellido;
    protected int edad;
    protected int ci;

    public Persona(String nombre, String apellido, int edad, int ci) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.ci = ci;
    }

    @Override
    public String toString() {
        return nombre + " " + apellido + " (CI: " + ci + ", Edad: " + edad + ")";
    }

    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public int getEdad() { return edad; }
    public int getCi() { return ci; }
}