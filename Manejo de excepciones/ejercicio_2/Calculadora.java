
package ejercicio_2;

public class Calculadora {
    public static double sumar(double a, double b) {
        return a + b;
    }
    public static double restar(double a, double b) {
        return a - b;
    }
    public static double multiplicar(double a, double b) {
        return a * b;
    }
    public static double dividir(double a, double b) throws ArithmeticException {
        if (b == 0) {
            throw new ArithmeticException("Error: División por cero no permitida.");
        }
        return a / b;
    }
    public static int convertirAEntero(String valor) throws NumeroInvalidoException {
        try {
            return Integer.parseInt(valor.trim());
        } catch (NumberFormatException e) {
            throw new NumeroInvalidoException("Error: '" + valor + "' no es un número entero válido.");
        }
    }
}