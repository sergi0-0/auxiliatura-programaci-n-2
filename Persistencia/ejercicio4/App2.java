package org.ejemplo;

import org.ejemplo.dao.ArchNota;
import org.ejemplo.model.Estudiante;

import java.util.Scanner;

public class App2 {
    private static final ArchNota dao = new ArchNota("notas.json");
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        if (dao.obtenerTodos().isEmpty()) {
            dao.agregar(new Estudiante(111, "Juan", "Pérez", "Gómez", "Programación 2", 85.5));
            dao.agregar(new Estudiante(112, "María", "Laura", "Torrico", "Programación 2", 92.0));
            dao.agregar(new Estudiante(113, "Carlos", "Ramírez", "Flores", "Base de Datos", 78.5));

            System.out.println("Datos de prueba cargados");
        }

        int op;
        do {
            menu();
            op = sc.nextInt();
            sc.nextLine(); 

            switch (op) {
                case 1 -> listarTodos();
                case 2 -> agregarEstudiante();
                case 3 -> promedioGeneral();
                case 4 -> buscarPorMateria();
                case 5 -> eliminarPorMateria();
                case 0 -> System.out.println("¡Adiós! Todo quedó guardado en notas.json");
                default -> System.out.println("Opción inválida");
            }
        } while (op != 0);
    }

    private static void menu() {
        System.out.println("\n=== SISTEMA DE NOTAS ===");
        System.out.println("1. Listar todos");
        System.out.println("2. Agregar estudiante");
        System.out.println("3. Promedio general");
        System.out.println("4. buscar por materia");
        System.out.println("5. Eliminar todos de una materia");
        System.out.println("0. Salir");
        System.out.print("→ Opción: ");
    }

    private static void listarTodos() {
        System.out.println("\n=== TODOS LOS ESTUDIANTES ===");
        dao.obtenerTodos().forEach(System.out::println);
    }

    private static void agregarEstudiante() {
        System.out.print("Carnet: ");
        int carnet = sc.nextInt();
        sc.nextLine();
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Paterno: ");
        String paterno = sc.nextLine();
        System.out.print("Materno: ");
        String materno = sc.nextLine();
        sc.nextLine();
        System.out.print("Materia: ");
        String materia = sc.nextLine();
        System.out.print("Nota final: ");
        double nota = sc.nextDouble();

        Estudiante e = new Estudiante(carnet, nombre, paterno, materno, materia, nota);
        dao.agregar(e);
        System.out.println("Estudiante agregado y guardado");
    }

    private static void promedioGeneral() {
        double prom = dao.obtenerPromedioGeneral();
        System.out.printf("\nPROMEDIO GENERAL DE NOTAS: %.2f\n", prom);
    }

    private static void buscarPorMateria() {
        System.out.print("Materia a buscar: ");
        String mat = sc.nextLine();

        var encontrados = dao.buscarPorMateria(mat);
        if (encontrados.isEmpty()) {
            System.out.println("No hay estudiantes en " + mat);
        } else {
            System.out.println("\nEstudiantes en " + mat + ":");
            encontrados.forEach(System.out::println);
        }
    }

    private static void eliminarPorMateria() {
        System.out.print("Materia a eliminar TODOS sus estudiantes: ");
        String mat = sc.nextLine();

        int cant = dao.eliminarPorMateria(mat);
        System.out.println("Se eliminaron " + cant + " estudiante/s de " + mat);
    }
}