package org.finalcarrera;

import org.finalcarrera.dao.ArchivoEmpresa;
import org.finalcarrera.model.Empresa;

import java.util.Scanner;

public class App10 {
    private static final ArchivoEmpresa archivo = new ArchivoEmpresa();
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        cargarDatosPruebaSiVacio();

        int op;
        do {
            menu();
            op = sc.nextInt();
            sc.nextLine(); 

            switch (op) {
                case 1 -> registrarEmpresa();
                case 2 -> archivo.mostrarTodas();
                case 3 -> buscarEmpresa();
                case 0 -> {
                    System.out.println("\n¡Guardado Chaoooo");
                }
                default -> System.out.println("Opción no válida");
            }
        } while (op != 0);
    }

    private static void menu() {
        System.out.println("\n=== GESTIÓN DE EMPRESAS ===");
        System.out.println("1. Registrar nueva empresa");
        System.out.println("2. Mostrar todas las empresas");
        System.out.println("3. Buscar empresa por nombre");
        System.out.println("0. Salir");
        System.out.print("→ Elija una opción: ");
    }

    private static void registrarEmpresa() {
        System.out.print("Nombre de la empresa: ");
        String nombre = sc.nextLine();
        System.out.print("Rubro (ej: Tecnología, Salud, Educación): ");
        String rubro = sc.nextLine();
        System.out.print("Número de empleados: ");
        int empleados = sc.nextInt();
        sc.nextLine();

        Empresa nueva = new Empresa(nombre, rubro, empleados);
        archivo.guardar(nueva);
    }

    private static void buscarEmpresa() {
        System.out.print("Nombre (o parte del nombre) a buscar: ");
        String busqueda = sc.nextLine();
        Empresa encontrada = archivo.buscarPorNombre(busqueda);

        if (encontrada == null) {
            System.out.println("No se encontró ninguna empresa con ese nombre.");
        } else {
            System.out.println("\nEmpresa encontrada:");
            System.out.println("-".repeat(50));
            System.out.println(encontrada.toStringBonito());
            System.out.println("-".repeat(50));
        }
    }

    private static void cargarDatosPruebaSiVacio() {
        if (!archivo.obtenerTodas().isEmpty()) return;

        archivo.guardar(new Empresa("TecnoBolivia SRL", "Tecnología", 450));
        archivo.guardar(new Empresa("SaludTotal", "Salud", 1200));
        archivo.guardar(new Empresa("EducaFuturo", "Educación", 80));
        archivo.guardar(new Empresa("AgroExport", "Agricultura", 320));
        archivo.guardar(new Empresa("Banco Unión", "Finanzas", 5600));

        System.out.println("5 empresas de ejemplo cargadas en empresas.txt");
    }
}