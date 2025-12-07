package org.ejemplo;

import org.ejemplo.dao.ArchNiño;
import org.ejemplo.model.Niño.Niño;

import java.util.Scanner;

public class App7 {
    private static final ArchNiño arch = new ArchNiño("niños.json");
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
                case 2 -> cantidadPesoAdecuado();
                case 3 -> niñosConProblemas();
                case 4 -> promedioEdad();
                case 5 -> buscarPorCi();
                case 6 -> niñoMasAlto();
                case 0 -> System.out.println("¡Chau chau! Todo guardado.");
                default -> System.out.println("Opción no válida");
            }
        } while (op != 0);
    }

    private static void menu() {
        System.out.println("\n=== CONTROL DE NIÑOS ===");
        System.out.println("1. Listar todos los niños");
        System.out.println("2. Cantidad con peso adecuado");
        System.out.println("3. Niños con peso/talla inadecuada");
        System.out.println("4. Promedio de edad");
        System.out.println("5. Buscar niño por CI");
        System.out.println("6. Niño(s) más alto(s)");
        System.out.println("0. Salir");
        System.out.print("→ ");
    }

    private static void listarTodos() {
        System.out.println("\nLISTA DE NIÑOS:");
        arch.obtenerTodos().forEach(System.out::println);
    }

    private static void cantidadPesoAdecuado() {
        long cant = arch.cantidadConPesoAdecuado();
        System.out.println("\nNiños con peso adecuado según edad: " + cant);
    }

    private static void niñosConProblemas() {
        var lista = arch.niñosConPesoOTallaInadecuada();
        System.out.println("\nNiños con peso o talla inadecuada:");
        if (lista.isEmpty()) {
            System.out.println("   Todos están perfectos!");
        } else {
            lista.forEach(System.out::println);
        }
    }

    private static void promedioEdad() {
        double prom = arch.promedioEdad();
        System.out.printf("\nPromedio de edad: %.2f años\n", prom);
    }

    private static void buscarPorCi() {
        System.out.print("CI del niño: ");
        int ci = sc.nextInt();
        Niño n = arch.buscarPorCi(ci);
        if (n == null) {
            System.out.println("No existe niño con CI " + ci);
        } else {
            System.out.println("Encontrado:");
            System.out.println(n);
        }
    }

    private static void niñoMasAlto() {
        var lista = arch.niñosConMayorTalla();
        System.out.println("\nNiño(s) con mayor talla:");
        lista.forEach(System.out::println);
    }

    private static void cargarDatosPruebaSiVacio() {
        if (!arch.obtenerTodos().isEmpty()) return;

        arch.guardar(new Niño("Mateo", "García", "López", 9876543, 5, "18 kg", "110 cm"));
        arch.guardar(new Niño("Sofía", "Martínez", "Ruiz", 1234567, 8, "28 kg", "130 cm"));
        arch.guardar(new Niño("Lucas", "Torrez", "Flores", 8888888, 3, "14 kg", "95 cm"));
        arch.guardar(new Niño("Valentina", "Pérez", "Mendoza", 5555555, 10, "45 kg", "142 cm")); // fuera de rango
        arch.guardar(new Niño("Benjamín", "Rojas", "Castro", 9999999, 6, "22 kg", "118 cm"));

        System.out.println("Datos de prueba cargados (5 niños)");
    }
}