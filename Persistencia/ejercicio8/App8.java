package org.ejemplo;

import org.ejemplo.dao.ArchRefri;
import org.ejemplo.model.Alimento;

import java.util.Scanner;

public class App8 {
    private static final ArchRefri refri = new ArchRefri("refri.json");
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        cargarDatosPruebaSiVacio();

        int op;
        do {
            menu();
            op = sc.nextInt();
            sc.nextLine(); 

            switch (op) {
                case 1 -> listarTodos();
                case 2 -> crearAlimento();
                case 3 -> modificarAlimento();
                case 4 -> eliminarAlimento();
                case 5 -> caducadosAntesDe();
                case 6 -> sinStock();
                case 7 -> buscarPorNombre();
                case 8 -> alimentoConMasCantidad();
                case 0 -> System.out.println("¡Chau! Todo quedó guardado en refri.json");
                default -> System.out.println("Opción inválida");
            }
        } while (op != 0);
    }

    private static void menu() {
        System.out.println("\n=== NEVERA / REFRIGERADOR ===");
        System.out.println("1. Listar todo");
        System.out.println("2. Crear / Agregar alimento");
        System.out.println("3. Modificar alimento por nombre");
        System.out.println("4. Eliminar alimento por nombre");
        System.out.println("5. Caducados antes de fecha X");
        System.out.println("6. Alimentos sin stock (cantidad 0)");
        System.out.println("7. Buscar alimento por nombre");
        System.out.println("8. Alimento con mayor cantidad");
        System.out.println("0. Salir");
        System.out.print("→ ");
    }

    private static void listarTodos() {
        System.out.println("\nCONTENIDO DEL REFRIGERADOR:");
        if (refri.obtenerTodos().isEmpty()) {
            System.out.println("   Está vacío");
        } else {
            refri.obtenerTodos().forEach(System.out::println);
        }
    }

    private static void crearAlimento() {
        System.out.print("Nombre: ");
        String nom = sc.nextLine();
        System.out.print("Fecha vencimiento (YYYY-MM-DD): ");
        String fecha = sc.nextLine();
        System.out.print("Cantidad: ");
        int cant = sc.nextInt();
        sc.nextLine();

        refri.crear(new Alimento(nom, fecha, cant));
        System.out.println("Alimento agregado");
    }

    private static void modificarAlimento() {
        System.out.print("Nombre del alimento a modificar: ");
        String viejo = sc.nextLine();
        System.out.print("Nuevo nombre: ");
        String nuevoNom = sc.nextLine();
        System.out.print("Nueva fecha vencimiento (YYYY-MM-DD): ");
        String nuevaFecha = sc.nextLine();
        System.out.print("Nueva cantidad: ");
        int nuevaCant = sc.nextInt();
        sc.nextLine();

        boolean ok = refri.modificar(viejo, new Alimento(nuevoNom, nuevaFecha, nuevaCant));
        System.out.println(ok ? "Modificado correctamente" : "No encontrado");
    }

    private static void eliminarAlimento() {
        System.out.print("Nombre del alimento a eliminar: ");
        String nom = sc.nextLine();
        boolean ok = refri.eliminar(nom);
        System.out.println(ok ? "Eliminado" : "No encontrado");
    }

    private static void caducadosAntesDe() {
        System.out.print("Fecha límite (YYYY-MM-DD): ");
        String fecha = sc.nextLine();
        var lista = refri.caducadosAntesDe(fecha);
        System.out.println("\nCaducados antes de " + fecha + ":");
        lista.forEach(System.out::println);
    }

    private static void sinStock() {
        var lista = refri.sinStock();
        System.out.println("\nAlimentos sin stock:");
        if (lista.isEmpty()) System.out.println("   Todos tienen stock");
        else lista.forEach(System.out::println);
    }

    private static void buscarPorNombre() {
        System.out.print("Nombre a buscar: ");
        String nom = sc.nextLine();
        Alimento a = refri.buscar(nom);
        if (a == null) System.out.println("No encontrado");
        else System.out.println("Encontrado: " + a);
    }

    private static void alimentoConMasCantidad() {
        Alimento a = refri.mayorCantidad();
        if (a == null) System.out.println("No hay alimentos");
        else System.out.println("\nAlimento con mayor cantidad:\n" + a);
    }

    private static void cargarDatosPruebaSiVacio() {
        if (!refri.obtenerTodos().isEmpty()) return;

        refri.crear(new Alimento("Leche", "2025-12-10", 3));
        refri.crear(new Alimento("Yogurt", "2025-12-05", 0));
        refri.crear(new Alimento("Queso", "2025-12-20", 8));
        refri.crear(new Alimento("Mantequilla", "2025-11-30", 2));
        refri.crear(new Alimento("Jamón", "2025-12-15", 12));

        System.out.println("Datos de prueba cargados (5 alimentos)");
    }
}