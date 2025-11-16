
package Ejercicio8;
import java.util.*;
class SistemaFraternidades {
    private static Map<Integer, Fraternidad> bailarinesPorCarnet = new HashMap<>();

    public static boolean estaEnOtraFraternidad(Bailarin b) {
        return bailarinesPorCarnet.containsKey(b.getCarnet());
    }

    public static void registrarBailarinEnFraternidad(Bailarin b, Fraternidad f) {
        if (estaEnOtraFraternidad(b)) {
            System.out.println("Advertencia: " + b.getNombre() + " ya estaba en otra fraternidad.");
        }
        bailarinesPorCarnet.put(b.getCarnet(), f);
        f.agregarBailarin(b);
    }

    public static void mostrarTodo(List<Fraternidad> fraternidades) {
        System.out.println("=== SISTEMA DE FRATERNIDADES - REPORTE COMPLETO ===\n");
        for (Fraternidad f : fraternidades) {
            System.out.println(f);
            System.out.println("   Encargado: " + f.getEncargado());
            System.out.println("   Bailarines:");
            if (f.getBailarines().isEmpty()) {
                System.out.println("     (Ninguno)");
            } else {
                for (Bailarin b : f.getBailarines()) {
                    System.out.println("     → " + b);
                }
            }
            System.out.println();
        }
    }
}