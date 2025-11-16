
package Ejercicio6;
class Medicamento {
    private String nombre;
    private double precio;
    private int stock;
    private String laboratorio;

    public Medicamento(String nombre, double precio, int stock, String laboratorio) {
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.laboratorio = laboratorio;
    }

    public void reducirStock(int cant) {
        if (cant <= stock) stock -= cant;
    }

    @Override
    public String toString() {
        return nombre + " ($" + precio + ") - Stock: " + stock;
    }

    public String getNombre() { return nombre; }
    public double getPrecio() { return precio; }
}