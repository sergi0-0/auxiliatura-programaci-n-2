/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicio2;
class Administrativo extends Empleado {
    private double salario;

    public Administrativo(String nombre, int carnet, String cargo, double salario) {
        super(nombre, carnet, cargo);
        this.salario = salario;
    }

    @Override
    public void mostrarDatosPersonales() {
        System.out.println("Administrativo: " + getNombre() + ", Carnet: " + getCarnet());
    }
}