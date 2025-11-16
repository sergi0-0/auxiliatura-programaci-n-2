/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejercicio_6;
public class CuentaBancaria {
    private String numeroCuenta;
    private String titular;
    private double saldo;

    public CuentaBancaria(String numeroCuenta, String titular, double saldoInicial) 
            throws IllegalArgumentException {
        if (numeroCuenta == null || numeroCuenta.trim().isEmpty()) {
            throw new IllegalArgumentException("El número de cuenta no puede estar vacío.");
        }
        if (titular == null || titular.trim().isEmpty()) {
            throw new IllegalArgumentException("El titular no puede estar vacío.");
        }
        if (saldoInicial < 0) {
            throw new IllegalArgumentException("El saldo inicial no puede ser negativo.");
        }

        this.numeroCuenta = numeroCuenta.trim();
        this.titular = titular.trim();
        this.saldo = saldoInicial;
    }

    // b) Depositar
    public void depositar(double monto) throws IllegalArgumentException {
        if (monto <= 0) {
            throw new IllegalArgumentException("El monto a depositar debe ser positivo.");
        }
        this.saldo += monto;
        System.out.printf("Depósito exitoso: +$%.2f | Saldo actual: $%.2f\n", monto, saldo);
    }

    // b) Retirar
    public void retirar(double monto) throws FondosInsuficientesException, IllegalArgumentException {
        if (monto <= 0) {
            throw new IllegalArgumentException("El monto a retirar debe ser positivo.");
        }
        if (monto > saldo) {
            throw new FondosInsuficientesException(
                String.format("Fondos insuficientes. Saldo: $%.2f, Intentó retirar: $%.2f", saldo, monto)
            );
        }
        this.saldo -= monto;
        System.out.printf("Retiro exitoso: -$%.2f | Saldo actual: $%.2f\n", monto, saldo);
    }

    // b) Mostrar información
    public void mostrarInfo() {
        System.out.println("\n=== INFORMACIÓN DE LA CUENTA ===");
        System.out.println("Número de cuenta: " + numeroCuenta);
        System.out.println("Titular: " + titular);
        System.out.printf("Saldo: $%.2f\n", saldo);
        System.out.println("================================\n");
    }

    public double getSaldo() { return saldo; }
}