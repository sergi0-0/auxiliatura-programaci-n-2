package ejercicio_4;
import java.util.*;
public class Inventario {
    private List<Producto> productos;
    public Inventario() {
        this.productos = new ArrayList<>();
    }
    public void agregarProducto(Producto p) throws IllegalArgumentException {
        if (p == null) {
            throw new IllegalArgumentException("El producto no puede ser nulo.");
        }
        for (Producto existente : productos) {
            if (existente.getCodigo().equals(p.getCodigo())) {
                throw new IllegalArgumentException(
                    "Ya existe un producto con el código: " + p.getCodigo()
                );
            }
        }
        productos.add(p);
        System.out.println("Producto agregado: " + p.getCodigo());
    }
    public Producto buscarProducto(String codigo) throws ProductoNoEncontradoException {
        if (codigo == null || codigo.trim().isEmpty()) {
            throw new IllegalArgumentException("El código no puede estar vacío.");
        }
        for (Producto p : productos) {
            if (p.getCodigo().equals(codigo.trim())) {
                return p;
            }
        }
        throw new ProductoNoEncontradoException("Producto con código '" + codigo + "' no encontrado.");
    }
    public void venderProducto(String codigo, int cantidad) 
            throws ProductoNoEncontradoException, StockInsuficienteException {
        Producto p = buscarProducto(codigo);
        p.reducirStock(cantidad);
        System.out.printf("Venta exitosa: %d unidad(es) de %s\n", cantidad, p.getNombre());
    }
    public void mostrarInventario() {
        System.out.println("\n=== INVENTARIO ACTUAL ===");
        if (productos.isEmpty()) {
            System.out.println("  (Vacío)");
        } else {
            for (Producto p : productos) {
                System.out.println("  • " + p);
            }
        }
        System.out.println("Total productos: " + productos.size() + "\n");
    }
}