package org.ejemplo;

import org.ejemplo.dao.ArchZoo;
import org.ejemplo.model.Animal;
import org.ejemplo.model.Zoologico;

import java.util.Scanner;

public class App9 {
    private static final ArchZoo arch = new ArchZoo("zoo.json");
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        cargarDatosPruebaSiVacio();

        int op;
        do {
            menu();
            op = sc.nextInt();
            sc.nextLine();

            switch (op) {
                case 1 -> arch.listarTodo();
                case 2 -> crearZoologico();
                case 3 -> modificarZoologico();
                case 4 -> eliminarZoologico();
                case 5 -> mayorVariedad();
                case 6 -> zoologicosVacios();
                case 7 -> buscarPorEspecie();
                case 8 -> moverAnimales();
                case 0 -> System.out.println("¡Chau! Todo guardado en zoo.json");
                default -> System.out.println("Opción inválida");
            }
        } while (op != 0);
    }

    private static void menu() {
        System.out.println("\n=== SISTEMA DE ZOOLÓGICOS ===");
        System.out.println("1. Listar todo");
        System.out.println("2. Crear zoológico + animales");
        System.out.println("3. Modificar zoológico");
        System.out.println("4. Eliminar zoológico");
        System.out.println("5. Zoológicos con mayor variedad");
        System.out.println("6. Zoológicos vacíos");
        System.out.println("7. Mostrar animales de una especie");
        System.out.println("8. Mover animales entre zoológicos");
        System.out.println("0. Salir");
        System.out.print("→ ");
    }

    private static void crearZoologico() {
        System.out.print("ID del zoológico: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Nombre: ");
        String nom = sc.nextLine();

        Zoologico z = new Zoologico(id, nom);

        System.out.println("Cuantos animales quiere agregar?");
        int n = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < n; i++) {
            System.out.println("Animal " + (i+1) + ":");
            System.out.print("  Especie: ");
            String esp = sc.nextLine();
            System.out.print("  Nombre: ");
            String name = sc.nextLine();
            System.out.print("  Cantidad: ");
            int cant = sc.nextInt();
            sc.nextLine();
            z.agregarAnimal(new Animal(esp, name, cant));
        }

        arch.crear(z);
        System.out.println("Zoológico creado con éxito");
    }

    private static void modificarZoologico() {
        System.out.print("ID del zoológico a modificar: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Nuevo nombre: ");
        String nuevoNom = sc.nextLine();
        Zoologico nuevo = new Zoologico(id, nuevoNom);
        boolean ok = arch.modificar(id, nuevo);
        System.out.println(ok ? "Modificado" : "No encontrado");
    }

    private static void eliminarZoologico() {
        System.out.print("ID del zoológico a eliminar: ");
        int id = sc.nextInt();
        boolean ok = arch.eliminar(id);
        System.out.println(ok ? "Eliminado" : "No encontrado");
    }

    private static void mayorVariedad() {
        var lista = arch.zoologicosMayorVariedad();
        System.out.println("\nZoológico(s) con mayor variedad de especies:");
        lista.forEach(z -> System.out.println(z.getNombre() + " (" + z.contarEspeciesDiferentes() + " especies)"));
    }

    private static void zoologicosVacios() {
        var lista = arch.zoologicosVacios();
        System.out.println("\nZoológicos vacíos:");
        if (lista.isEmpty()) System.out.println("   Todos tienen animales");
        else lista.forEach(z -> System.out.println("   " + z.getNombre()));
    }

    private static void buscarPorEspecie() {
        System.out.print("Especie a buscar: ");
        String esp = sc.nextLine();
        arch.mostrarAnimalesDeEspecie(esp);
    }

    private static void moverAnimales() {
        System.out.print("ID zoológico origen: ");
        int origen = sc.nextInt();
        System.out.print("ID zoológico destino: ");
        int destino = sc.nextInt();
        arch.moverAnimales(origen, destino);
    }

    private static void cargarDatosPruebaSiVacio() {
        if (!arch.obtenerTodos().isEmpty()) return;

        Zoologico z1 = new Zoologico(1, "Zoo La Paz");
        z1.agregarAnimal(new Animal("Felino", "León", 4));
        z1.agregarAnimal(new Animal("Ave", "Cóndor", 6));
        z1.agregarAnimal(new Animal("Reptil", "Cocodrilo", 2));

        Zoologico z2 = new Zoologico(2, "Zoo Santa Cruz");
        z2.agregarAnimal(new Animal("Felino", "Tigre", 3));
        z2.agregarAnimal(new Animal("Ave", "Loro", 15));
        z2.agregarAnimal(new Animal("Mamífero", "Oso", 2));
        z2.agregarAnimal(new Animal("Reptil", "Serpiente", 8));

        Zoologico z3 = new Zoologico(3, "Zoo Cochabamba"); 

        arch.crear(z1);
        arch.crear(z2);
        arch.crear(z3);

        System.out.println("Datos de prueba cargados (3 zoológicos)");
    }
}