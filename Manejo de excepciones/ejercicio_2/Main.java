
package ejercicio_2;
public class Main {
    public static void main(String[] args) {
        System.out.println("=== PRUEBAS DE LA CALCULADORA ===\n");

        // === PRUEBAS BÁSICAS ===
        System.out.println("1. Operaciones básicas:");
        System.out.println("   10 + 5 = " + Calculadora.sumar(10, 5));
        System.out.println("   10 - 5 = " + Calculadora.restar(10, 5));
        System.out.println("   10 * 5 = " + Calculadora.multiplicar(10, 5));

        // === DIVISIÓN CON ERROR ===
        System.out.println("\n2. División por cero (debe lanzar excepción):");
        try {
            System.out.println("   10 / 0 = " + Calculadora.dividir(10, 0));
        } catch (ArithmeticException e) {
            System.out.println("   " + e.getMessage());
        }

        // === CONVERSIÓN VÁLIDA ===
        System.out.println("\n3. Conversión de String a entero:");
        try {
            int num1 = Calculadora.convertirAEntero("456");
            System.out.println("   '456' → " + num1);
            System.out.println("   Operación: 456 + 44 = " + Calculadora.sumar(num1, 44));
        } catch (NumeroInvalidoException e) {
            System.out.println("   " + e.getMessage());
        }

        // === CONVERSIÓN INVÁLIDA ===
        System.out.println("\n4. Conversión inválida (debe lanzar excepción personalizada):");
        try {
            Calculadora.convertirAEntero("abc123");
        } catch (NumeroInvalidoException e) {
            System.out.println("   " + e.getMessage());
        }

        // === OTRA CONVERSIÓN CON ESPACIOS ===
        System.out.println("\n5. Conversión con espacios:");
        try {
            int num2 = Calculadora.convertirAEntero("  789  ");
            System.out.println("   ' 789  ' → " + num2);
        } catch (NumeroInvalidoException e) {
            System.out.println("   " + e.getMessage());
        }

        // === PRUEBA FINAL CON DIVISIÓN DE NÚMEROS CONVERTIDOS ===
        System.out.println("\n6. Uso combinado (convertir + dividir):");
        try {
            int a = Calculadora.convertirAEntero("100");
            int b = Calculadora.convertirAEntero("4");
            System.out.println("   100 / 4 = " + Calculadora.dividir(a, b));
        } catch (NumeroInvalidoException | ArithmeticException e) {
            System.out.println("   Error: " + e.getMessage());
        }

        System.out.println("\n=== FIN DE PRUEBAS ===");
    }
}