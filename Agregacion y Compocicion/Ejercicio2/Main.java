package Ejercicio2;
public class Main {
    public static void main(String[] args) {
        Estudiante estudiante = new Estudiante("Juan Pérez", 1001, "Matemáticas", 85.5);
        Empleado empleado = new Empleado("Ana Gómez", 2001, "Secretaria");
        Administrativo administrativo = new Administrativo("Carlos López", 3001, "Contador", 2500.0);
        Docente docente = new Docente("María Rodríguez", 4001, "Matemáticas", 3500.0);
        System.out.println("=== DATOS PERSONALES ===");
        estudiante.mostrarDatosPersonales();
        empleado.mostrarDatosPersonales();
        administrativo.mostrarDatosPersonales();
        docente.mostrarDatosPersonales();
        System.out.println("\n=== RELACIÓN ESTUDIANTE - DOCENTE ===");
        if (estudiante.getMateria().equalsIgnoreCase(docente.getMateria())) {
            System.out.println("SÍ: El estudiante " + estudiante.getNombre() +
                    " pasa clases de " + estudiante.getMateria() +
                    " con el docente " + docente.getNombre());
        } else {
            System.out.println("NO: Materias diferentes.");
        }
    }
}