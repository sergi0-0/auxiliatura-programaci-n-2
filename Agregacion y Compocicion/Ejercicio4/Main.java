package Ejercicio4;
public class Main {
    public static void main(String[] args) {
        Ropero ropero = new Ropero("Madera");
        ropero.agregar(new Ropa("Camisa", "Algodón"));
        ropero.agregar(new Ropa("Pantalón", "Jeans"));
        ropero.agregar(new Ropa("Camisa", "Lino"));
        ropero.agregar(new Ropa("Chaqueta", "Cuero"));
        ropero.agregar(new Ropa("Camisa", "Algodón"));
        ropero.mostrarTodo();
        ropero.mostrarPorMaterial("Algodón");
        ropero.mostrarPorTipo("Camisa");
        System.out.println("--- Eliminando prendas de material 'Algodón' ---");
        ropero.eliminarPorMaterial("Algodón");
        ropero.mostrarTodo();
        System.out.println("--- Eliminando prendas de tipo 'Pantalón' ---");
        ropero.eliminarPorTipo("Pantalón");
        ropero.mostrarTodo();
    }
}