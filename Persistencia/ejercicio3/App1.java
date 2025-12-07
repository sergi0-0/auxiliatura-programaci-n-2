package org.ejemplo;

import org.ejemplo.dao.ArchivoProducto;
import org.ejemplo.model.Producto;

import java.util.Scanner;

public class App1 {
    private static final ArchivoProducto dao = new ArchivoProducto("productos.json");
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        dao.crearArchivo();

        if (dao.obtenerTodos().isEmpty()) {
            dao.guardarProducto(new Producto(101, "Laptop HP", 4500.50f));
            dao.guardarProducto(new Producto(102, "Mouse Logitech", 85.90f));
            dao.guardarProducto(new Producto(103, "Teclado Mecánico", 320.00f));
            dao.guardarProducto(new Producto(104, "Monitor 27\"", 1890.00f));
            dao.guardarProducto(new Producto(105, "Auriculares Sony", 650.75f));
            System.out.println("Datos de prueba cargados");
        }

        int op;
        do {
            menu();
            op = sc.nextInt();

            switch (op) {
                case 1 -> listarTodos();
                case 2 -> agregarProducto();
                case 3 -> buscarPorCodigo();
                case 4 -> mostrarPromedio();
                case 5 -> mostrarProductoMasCaro();
                case 0 -> System.out.println("¡Chau! Todo quedó guardado en productos.json");
                default -> System.out.println("Opción no válida");
            }
        } while (op != 0);
    }

    private static void menu() {
        System.out.println("\n=== SISTEMA DE PRODUCTOS ===");
        System.out.println("1. Listar todos los productos");
        System.out.println("2. Agregar nuevo producto");
        System.out.println("3. Buscar producto por código");
        System.out.println("4. Mostrar promedio de precios");
        System.out.println("5. Mostrar producto más caro");
        System.out.println("0. Salir");
        System.out.print("→ Elija opción: ");
    }

    private static void listarTodos() {
        System.out.println("\n=== LISTA DE PRODUCTOS ===");
        dao.obtenerTodos().forEach(System.out::println);
    }

    private static void agregarProducto() {
        System.out.print("Código: ");
        int cod = sc.nextInt();
        sc.nextLine();
        System.out.print("Nombre: ");
        String nom = sc.nextLine();
        System.out.print("Precio: ");
        float pre = sc.nextFloat();

        Producto p = new Producto(cod, nom, pre);
        dao.guardarProducto(p);
    }

    private static void buscarPorCodigo() {
        System.out.print("Código a buscar: ");
        int cod = sc.nextInt();

        Producto p = dao.buscaProducto(cod);
        if (p == null) {
            System.out.println("No existe producto con código " + cod);
        } else {
            System.out.println("Producto encontrado:");
            System.out.println(p);
        }
    }

    private static void mostrarPromedio() {
        double promedio = dao.calcularPromedioPrecios();
        System.out.printf("\nPromedio de precios: %.2f\n", promedio);
    }

    private static void mostrarProductoMasCaro() {
        Producto caro = dao.productoMasCaro();
        if (caro == null) {
            System.out.println("No hay productos registrados");
        } else {
            System.out.println("\nPRODUCTO MÁS CARO:");
            System.out.println(caro);
        }
    }
}