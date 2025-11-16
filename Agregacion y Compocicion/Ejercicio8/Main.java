
package Ejercicio8;
import java.util.*;
public class Main {
    public static void main(String[] args) {
        // === FACULTADES ===
        Facultad f1 = new Facultad("Ingeniería en Sistemas", "SIS");
        Facultad f2 = new Facultad("Medicina", "MED");

        // === ENCARGADOS ===
        Encargado e1 = new Encargado("Luis Mamani", 8001, 25);
        Encargado e2 = new Encargado("Ana Choque", 8002, 27);

        // === FRATERNIDADES ===
        Fraternidad frat1 = new Fraternidad("Los Tigres", e1);
        Fraternidad frat2 = new Fraternidad("Águilas Negras", e2);

        // === BAILARINES (5 instancias) ===
        Bailarin b1 = new Bailarin("Pedro Gonzales", 1001, 20, f1);
        Bailarin b2 = new Bailarin("María Laura", 1002, 19, f1);
        Bailarin b3 = new Bailarin("Carlos Quispe", 1003, 21, f2);
        Bailarin b4 = new Bailarin("Sofia Rojas", 1004, 18, f2);
        Bailarin b5 = new Bailarin("Juan Perez", 1005, 22, f1);

        // === REGISTRO EN FRATERNIDADES ===
        b1.registrarEnFraternidad(frat1);
        b2.registrarEnFraternidad(frat1);
        b3.registrarEnFraternidad(frat2);
        b4.registrarEnFraternidad(frat2);
        b5.registrarEnFraternidad(frat1);

        // Intento de duplicado (debe fallar)
        System.out.println("--- Intentando duplicado ---");
        Bailarin duplicado = new Bailarin("Pedro Gonzales", 1001, 20, f1);
        duplicado.registrarEnFraternidad(frat2); // Debe rechazar

        // === MOSTRAR TODO ===
        List<Fraternidad> fraternidades = Arrays.asList(frat1, frat2);
        SistemaFraternidades.mostrarTodo(fraternidades);
    }
}
