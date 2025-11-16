
package Ejercicio12;
import java.util.*;
public class Hospital {
    private String nombre;
    private List<Doctor> doctores;

    public Hospital(String nombre) {
        this.nombre = nombre;
        this.doctores = new ArrayList<>();
    }

    // c) Asignar doctor al hospital
    public void asignarDoctor(Doctor d) {
        doctores.add(d);
        System.out.println(d.getNombre() + " asignado al hospital " + nombre);
    }

    // c) Mostrar doctores del hospital
    public void mostrarDoctores() {
        System.out.println("\n=== DOCTORES EN " + nombre.toUpperCase() + " ===");
        if (doctores.isEmpty()) {
            System.out.println("  (Ningún doctor asignado)");
        } else {
            for (Doctor d : doctores) {
                System.out.println("  • " + d);
            }
        }
        System.out.println("Total: " + doctores.size() + " doctores\n");
    }

    public String getNombre() { return nombre; }
}