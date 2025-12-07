package org.ejemplo;

import org.ejemplo.dao.*;
import org.ejemplo.model.*;

import java.util.Scanner;

public class App6 {
    private static final ArchLibro archLibro = new ArchLibro("libros.json");
    private static final ArchCliente archCliente = new ArchCliente("clientes.json");
    private static final ArchPrestamo archPrestamo = new ArchPrestamo("prestamos.json");
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        cargarDatosPruebaSiVacio();

        int op;
        do {
            menu();
            op = sc.nextInt();
            sc.nextLine();

            switch (op) {
                case 1 -> puntoA();
                case 2 -> puntoB();
                case 3 -> puntoC();
                case 4 -> puntoD();
                case 5 -> puntoE();
                case 6 -> puntoF();
                case 0 -> System.out.println("¡Chau! Todo guardado.");
                default -> System.out.println("Opción inválida");
            }
        } while (op != 0);
    }

    private static void menu() {
        System.out.println("\n=== BIBLIOTECA SYSTEM ===");
        System.out.println("1. a) Libros entre dos precios");
        System.out.println("2. b) Ingreso total por libro");
        System.out.println("3. c) Libros nunca prestados");
        System.out.println("4. d) Clientes que nunca prestaron un libro");
        System.out.println("5. e) Libro más prestado");
        System.out.println("6. f) Cliente con más préstamos");
        System.out.println("0. Salir");
        System.out.print("→ ");
    }

    private static void puntoA() {
        System.out.print("Precio mínimo: "); double min = sc.nextDouble();
        System.out.print("Precio máximo: "); double max = sc.nextDouble();
        System.out.println("\nLibros entre " + min + " y " + max + ":");
        archLibro.librosEntrePrecios(min, max).forEach(System.out::println);
    }

    private static void puntoB() {
        System.out.print("Código del libro: "); String cod = sc.nextLine();
        double ingreso = archPrestamo.ingresoPorLibro(cod, archLibro.getLibros());
        System.out.printf("Ingreso total por %s: %.2f\n", cod, ingreso);
    }

    private static void puntoC() {
        var prestados = archPrestamo.getPrestamos().stream().map(Prestamo::getCodLibro).toList();
        System.out.println("\nLibros NUNCA prestados:");
        archLibro.getLibros().stream()
                .filter(l -> !prestados.contains(l.getCodLibro()))
                .forEach(System.out::println);
    }

    private static void puntoD() {
        System.out.print("Código del libro: "); String cod = sc.nextLine();
        var clientesNunca = archCliente.clientesNuncaPrestaronLibro(cod, archPrestamo.getPrestamos());
        System.out.println("\nClientes que nunca prestaron " + cod + ":");
        clientesNunca.forEach(System.out::println);
    }

    private static void puntoE() {
        String libro = archPrestamo.libroMasPrestado(archLibro.getLibros());
        System.out.println("\nLibro más prestado: " + libro);
    }

    private static void puntoF() {
        String cliente = archPrestamo.clienteConMasPrestamos();
        System.out.println("\nCliente con más préstamos: " + cliente);
    }

    private static void cargarDatosPruebaSiVacio() {
        if (!archLibro.getLibros().isEmpty()) return;

        archLibro.guardarLibro(new Libro("L001", "Cien años de soledad", 85.0));
        archLibro.guardarLibro(new Libro("L002", "El principito", 45.0));
        archLibro.guardarLibro(new Libro("L003", "1984", 120.0));
        archLibro.guardarLibro(new Libro("L004", "Don Quijote", 150.0));

        archCliente.guardar(new Cliente("C001", "111111", "Juan", "Pérez"));
        archCliente.guardar(new Cliente("C002", "222222", "María", "Gómez"));
        archCliente.guardar(new Cliente("C003", "333333", "Carlos", "López"));

        archPrestamo.guardar(new Prestamo("C001", "L001", "2025-10-01", 2));
        archPrestamo.guardar(new Prestamo("C002", "L001", "2025-10-05", 3));
        archPrestamo.guardar(new Prestamo("C001", "L003", "2025-11-01", 1));
        archPrestamo.guardar(new Prestamo("C003", "L002", "2025-11-10", 4));

        System.out.println("Datos de prueba cargados");
    }
}