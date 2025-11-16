package Ejercicio12;
public class Main {
    public static void main(String[] args) {
        Doctor d1 = new Doctor("Carlos Méndez", "Cardiología");
        Doctor d2 = new Doctor("Laura Gómez", "Pediatría");
        Doctor d3 = new Doctor("Roberto Salazar", "Neurología");
        Doctor d4 = new Doctor("Ana Torres", "Dermatología");
        Hospital h1 = new Hospital("Hospital Obrero");
        Hospital h2 = new Hospital("Clínica Los Olivos");
        Hospital h3 = new Hospital("Hospital del Sur");
        h1.asignarDoctor(d1);
        h1.asignarDoctor(d2);
        h2.asignarDoctor(d2);  
        h2.asignarDoctor(d3);
        h3.asignarDoctor(d1);
        h3.asignarDoctor(d4);
        h1.mostrarDoctores();
        h2.mostrarDoctores();
        h3.mostrarDoctores();
        System.out.println("--- Eliminando hospital 'Clínica Los Olivos' ---");
        h2 = null; 
        System.gc(); 
        System.out.println("Los doctores aún existen:");
        System.out.println(d2); 
        System.out.println(d3);
    }
}
