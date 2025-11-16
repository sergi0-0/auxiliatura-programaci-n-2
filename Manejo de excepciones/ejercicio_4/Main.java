
package ejercicio_4;
public class Main {
    public static void main(String[] args) {
        Inventario inventario = new Inventario();
        System.out.println("=== SISTEMA DE INVENTARIO - PRUEBAS ===\n");
        try {
            Producto p1 = new Producto("P001", "Laptop HP", 1200.50, 5);
            Producto p2 = new Producto("P002", "Mouse Logitech", 25.0, 50);
            Producto p3 = new Producto("P003", "Teclado Mecánico", 85.0, 30);
            inventario.agregarProducto(p1);
            inventario.agregarProducto(p2);
            inventario.agregarProducto(p3);
        } catch (IllegalArgumentException e) {
            System.out.println("Error al crear producto: " + e.getMessage());
        }
        inventario.mostrarInventario();
        System.out.println("--- Intentando producto duplicado ---");
        try {
            Producto duplicado = new Producto("P001", "Otra Laptop", 1000, 3);
            inventario.agregarProducto(duplicado);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println("\n--- Venta exitosa ---");
        try {
            inventario.venderProducto("P002", 10);
        } catch (ProductoNoEncontradoException | StockInsuficienteException e) {
            System.out.println("Error en venta: " + e.getMessage());
        }
        System.out.println("\n--- Venta con stock insuficiente ---");
        try {
            inventario.venderProducto("P001", 10); // Solo hay 5
        } catch (ProductoNoEncontradoException | StockInsuficienteException e) {
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println("\n--- Búsqueda de producto inexistente ---");
        try {
            inventario.buscarProducto("P999");
        } catch (ProductoNoEncontradoException e) {
            System.out.println("Error: " + e.getMessage());
        }
        inventario.mostrarInventario();
        System.out.println("=== FIN DE PRUEBAS ===");
    }
}