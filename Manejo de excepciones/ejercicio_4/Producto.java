
package ejercicio_4;
public class Producto {
    private String codigo;
    private String nombre;
    private double precio;
    private int stock;
    public Producto(String codigo, String nombre, double precio, int stock) 
            throws IllegalArgumentException {
        if (codigo == null || codigo.trim().isEmpty()) {
            throw new IllegalArgumentException("El código no puede estar vacío.");
        }
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
        if (precio < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo.");
        }
        if (stock < 0) {
            throw new IllegalArgumentException("El stock no puede ser negativo.");
        }
        this.codigo = codigo.trim();
        this.nombre = nombre.trim();
        this.precio = precio;
        this.stock = stock;
    }
    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public double getPrecio() { return precio; }
    public int getStock() { return stock; }
    public void reducirStock(int cantidad) throws StockInsuficienteException {
        if (cantidad > stock) {
            throw new StockInsuficienteException(
                "Stock insuficiente para '" + nombre + "'. Disponible: " + stock + ", Solicitado: " + cantidad
            );
        }
        this.stock -= cantidad;
    }
    @Override
    public String toString() {
        return String.format("%s | %s | $%.2f | Stock: %d", codigo, nombre, precio, stock);
    }
}