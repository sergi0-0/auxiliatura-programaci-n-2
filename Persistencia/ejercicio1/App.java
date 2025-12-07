package org.ejemplo;

import org.ejemplo.dao.CharangoDAO;
import org.ejemplo.model.Charango;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.Arrays;
public class App {
    private static final CharangoDAO dao = new CharangoDAO();
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        cargarDatosDePruebaSiVacio();

        int op;
        do {
            menu();
            op = sc.nextInt();
            sc.nextLine(); 

            switch (op) {
                case 1 -> listarTodos();
                case 2 -> eliminarConMasDe6Rotas();
                case 3 -> buscarPorMaterial();
                case 4 -> charangosCon10CuerdasBuenas();
                case 5 -> ordenarPorMaterial();
                case 0 -> System.out.println("¡Chau! Datos guardados automáticamente.");
                default -> System.out.println("Opción inválida");
            }
        } while (op != 0);
    }

    private static void menu() {
        System.out.println("\n=== GESTIÓN DE CHARANGOS ===");
        System.out.println("1. Listar todos");
        System.out.println("2. Eliminar con más de 6 cuerdas rotas");
        System.out.println("3. Buscar por material");
        System.out.println("4. Charangos con 10 cuerdas buenas");
        System.out.println("5. Ordenar por material");
        System.out.println("0. Salir");
        System.out.print("→ Elija opción: ");
    }

    private static void listarTodos() {
        System.out.println("\n=== LISTA COMPLETA ===");
        dao.obtenerTodos().forEach(System.out::println);
    }

    private static void eliminarConMasDe6Rotas() {
        List<Charango> lista = dao.obtenerTodos();
        long eliminados = lista.stream()
                .filter(c -> c.getCuerdasRotas() > 6)
                .count();

        lista.removeIf(c -> c.getCuerdasRotas() > 6);
        dao.guardarTodos(lista);

        System.out.println("Se eliminaron " + eliminados + " charango/s con más de 6 cuerdas rotas");
    }

    private static void buscarPorMaterial() {
        System.out.print("Material a buscar: ");
        String mat = sc.nextLine();

        List<Charango> encontrados = dao.obtenerTodos().stream()
                .filter(c -> c.getMaterial().equalsIgnoreCase(mat))
                .collect(Collectors.toList());

        if (encontrados.isEmpty()) {
            System.out.println("No hay charangos de " + mat);
        } else {
            System.out.println("Encontrados:");
            encontrados.forEach(System.out::println);
        }
    }

    private static void charangosCon10CuerdasBuenas() {
        System.out.println("\n=== 10 CUERDAS PERFECTAS ===");
        dao.obtenerTodos().stream()
                .filter(Charango::tieneTodasLasCuerdasBuenas)
                .forEach(System.out::println);
    }

    private static void ordenarPorMaterial() {
        System.out.println("\n=== ORDENADOS POR MATERIAL ===");
        dao.obtenerTodos().stream()
                .sorted(Comparator.comparing(Charango::getMaterial, String.CASE_INSENSITIVE_ORDER))
                .forEach(System.out::println);
    }
   private static void cargarDatosDePruebaSiVacio() {
    if (!dao.obtenerTodos().isEmpty()) return;
    boolean[] c1 = new boolean[10];
    Arrays.fill(c1, true);
    for (int i = 5; i < 10; i++) c1[i] = false; 
    boolean[] c2 = new boolean[10];
    Arrays.fill(c2, true);
    boolean[] c3 = new boolean[10];
    Arrays.fill(c3, false);       
    c3[0] = true; c3[1] = true;      
    boolean[] c4 = new boolean[10];
    Arrays.fill(c4, false);
    c4[0] = true; c4[1] = true; c4[2] = true;
    dao.agregar(new Charango("Quinuala", 10, c1));
    dao.agregar(new Charango("PaloSanto", 10, c2));
    dao.agregar(new Charango("Naranjillo", 10, c3)); 
    dao.agregar(new Charango("Jacarandá", 10, c4));
    System.out.println("Datos de prueba cargados (incluye 2 charangos con más de 6 cuerdas rotas)");
}
}