package org.ejemplo;

import org.ejemplo.dao.ArchivoTrabajador;
import org.ejemplo.model.Trabajador;

import java.util.Scanner;

public class App {
    private static final ArchivoTrabajador dao = new ArchivoTrabajador();
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        dao.crearArchivo(); 
        if (dao.obtenerTodos().isEmpty()) {
            dao.guardarTrabajador(new Trabajador("Juan Pérez", 1001, 800.0));
            dao.guardarTrabajador(new Trabajador("María Gómez", 1002, 1200.0));
            dao.guardarTrabajador(new Trabajador("Carlos López", 1003, 950.50));
            dao.guardarTrabajador(new Trabajador("Ana Rodríguez", 1004, 1500.0));
            System.out.println("Datos de prueba cargados");
        }

        int op;
        do {
            menu();
            op = sc.nextInt();
            sc.nextLine();

            switch (op) {
                case 1 -> listarTodos();
                case 2 -> guardarNuevoTrabajador();
                case 3 -> aumentarSalario();
                case 4 -> mostrarMejorPagado();
                case 5 -> mostrarOrdenadosPorSalario();
                case 0 -> System.out.println("¡Datos guardados! Chau");
                default -> System.out.println("Opción no válida");
            }
        } while (op != 0);
    }

    private static void menu() {
        System.out.println("\n=== SISTEMA DE TRABAJADORES ===");
        System.out.println("1. Listar todos");
        System.out.println("2. Agregar nuevo trabajador");
        System.out.println("3. Aumentar salario (por carnet)");
        System.out.println("4. Mostrar trabajador mejor pagado");
        System.out.println("5. Mostrar ordenados por salario (mayor a menor)");
        System.out.println("0. Salir");
        System.out.print("→ Opción: ");
    }

    private static void listarTodos() {
        System.out.println("\n=== LISTA DE TRABAJADORES ===");
        dao.obtenerTodos().forEach(System.out::println);
    }

    private static void guardarNuevoTrabajador() {
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Carnet: ");
        int carnet = sc.nextInt();
        System.out.print("Salario: ");
        double salario = sc.nextDouble();

        Trabajador t = new Trabajador(nombre, carnet, salario);
        dao.guardarTrabajador(t);
        System.out.println("Trabajador guardado correctamente");
    }

    private static void aumentarSalario() {
        System.out.print("Carnet del trabajador: ");
        int carnet = sc.nextInt();
        System.out.print("Porcentaje de aumento: ");
        int porc = sc.nextInt();

        if (dao.aumentarSalario(carnet, porc)) {
            System.out.println("Salario actualizado");
        } else {
            System.out.println("No se encontró trabajador con carnet " + carnet);
        }
    }

    private static void mostrarMejorPagado() {
        Trabajador mejor = dao.buscarMejorPagado();
        if (mejor == null) {
            System.out.println("No hay trabajadores registrados");
        } else {
            System.out.println("\nMEJOR PAGADO:");
            System.out.println(mejor);
        }
    }

    private static void mostrarOrdenadosPorSalario() {
        System.out.println("\n=== ORDENADOS POR SALARIO (descendente) ===");
        dao.obtenerOrdenadosPorSalario().forEach(System.out::println);
    }
}