
package Ejercicio6;
import java.util.*;
class Venta {
    private int idVenta;
    private double total;
    private List<Registro> registros;
    private Receta receta;

    public Venta(int id) {
        this.idVenta = id;
        this.total = 0;
        this.registros = new ArrayList<>();
    }

    public void agregarMedicamento(Medicamento m, int cant) {
        m.reducirStock(cant);
        total += m.getPrecio() * cant;
        registros.add(new Registro("Venta", cant + " x " + m.getNombre()));
    }

    public void setReceta(Receta r) { this.receta = r; }

    @Override
    public String toString() {
        return "Venta #" + idVenta + " - Total: $" + total;
    }
}