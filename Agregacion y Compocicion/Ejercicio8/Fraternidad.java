
package Ejercicio8;
import java.util.*;
class Fraternidad {
    private String nombre;
    private Encargado encargado;
    private List<Bailarin> bailarines;

    public Fraternidad(String nombre, Encargado encargado) {
        this.nombre = nombre;
        this.encargado = encargado;
        this.bailarines = new ArrayList<>();
    }

    public boolean agregarBailarin(Bailarin b) {
        // Validar que no esté en otra fraternidad
        for (Bailarin existente : bailarines) {
            if (existente.getCarnet() == b.getCarnet()) {
                System.out.println("Error: " + b.getNombre() + " ya está en esta fraternidad.");
                return false;
            }
        }
        // Validar globalmente (opcional: usar un mapa global de carnet -> fraternidad)
        if (SistemaFraternidades.estaEnOtraFraternidad(b)) {
            System.out.println("Error: " + b.getNombre() + " ya pertenece a otra fraternidad.");
            return false;
        }
        bailarines.add(b);
        return true;
    }

    public Encargado getEncargado() { return encargado; }
    public List<Bailarin> getBailarines() { return bailarines; }
    public String getNombre() { return nombre; }

    @Override
    public String toString() {
        return nombre + " [Encargado: " + encargado.getNombre() + "]";
    }
}