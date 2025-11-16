/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicio8;
class Facultad {
    private String nombre;
    private String sigla;

    public Facultad(String nombre, String sigla) {
        this.nombre = nombre;
        this.sigla = sigla;
    }

    @Override
    public String toString() {
        return nombre + " (" + sigla + ")";
    }

    public String getNombre() { return nombre; }
}