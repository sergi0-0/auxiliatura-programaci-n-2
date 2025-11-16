
package ejercicio_6;
public class Main {
    public static void main(String[] args) {
        System.out.println("=== SISTEMA BANCARIO - PRUEBAS ===\n");
        CuentaBancaria cuenta = null;
        try {
            cuenta = new CuentaBancaria("12345", "Juan Pérez", 1000.0);
            System.out.println("Cuenta creada exitosamente.\n");
        } catch (IllegalArgumentException e) {
            System.out.println("Error al crear cuenta: " + e.getMessage());
            return;
        }
        cuenta.mostrarInfo();
        System.out.println("--- Depósito válido (+500) ---");
        try {
            cuenta.depositar(500.0);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println("\n--- Depósito inválido (-200) ---");
        try {
            cuenta.depositar(-200.0);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println("\n--- Retiro válido ($300) ---");
        try {
            cuenta.retirar(300.0);
        } catch (FondosInsuficientesException | IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println("\n--- Retiro inválido (supera saldo: $2000) ---");
        try {
            cuenta.retirar(2000.0);
        } catch (FondosInsuficientesException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
        cuenta.mostrarInfo();
        System.out.println("=== FIN DE PRUEBAS ===");
    }
}