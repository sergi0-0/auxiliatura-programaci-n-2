package org.ejemplo;

import org.ejemplo.dao.ArchFarmacia;
import org.ejemplo.model.Farmacia;
import org.ejemplo.model.Medicamento;

import java.util.Scanner;

public class App5 {
    private static final ArchFarmacia dao = new ArchFarmacia("farmacias.json");
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        dao.crearArchivo();

       if (dao.getFarmacias().isEmpty()) {
        cargarDatosPrueba();
       }
        int op;
        do {
            menu();
            op = sc.nextInt();
            sc.nextLine();

            switch (op) {
                case 1 -> dao.listar();
                case 2 -> puntoA();
                case 3 -> puntoB();
                case 4 -> puntoC();
                case 5 -> puntoD();
                case 6 -> puntoE();
                case 0 -> System.out.println("¡Chau!");
                default -> System.out.println("Opción no válida");
            }
        } while (op != 0);
    }

    private static void menu() {
        System.out.println("\n=== FARMACIA SYSTEM ===");
        System.out.println("1. Listar todo");
        System.out.println("2. a) Medicamentos de sucursal X");
        System.out.println("3. b) Farmacias con Tapsin");
        System.out.println("4. c) Medicamentos por tipo");
        System.out.println("5. d) Farmacias ordenadas por dirección");
        System.out.println("6. e) Mover medicamentos de tipo X");
        System.out.println("0. Salir");
        System.out.print("→ ");
    }

    private static void puntoA() {
        System.out.print("Sucursal: ");
        dao.mostrarMedicamentosSucursal(sc.nextLine());
    }

    private static void puntoB() { dao.buscarTapsin(); }

    private static void puntoC() {
        System.out.print("Tipo de medicamento: ");
        dao.mostrarMedicamentosPorTipo(sc.nextLine());
    }

    private static void puntoD() { dao.mostrarFarmaciasOrdenadasPorDireccion(); }

    private static void puntoE() {
        System.out.print("Tipo a mover: ");
        String tipo = sc.nextLine();
        System.out.print("Sucursal origen: ");
        String origen = sc.nextLine();
        System.out.print("Sucursal destino: ");
        String destino = sc.nextLine();
        dao.moverMedicamentosTipo(tipo, origen, destino);
    }

        private static void cargarDatosPrueba() {
        Farmacia f1 = new Farmacia("Farmacia del Pueblo", "Central", "Av. Ayacucho");
        f1.agregarMedicamento(new Medicamento("Paracetamol", "PAR001", "Analgésico", 15.5));
        f1.agregarMedicamento(new Medicamento("Tapsin", "TAP001", "Analgésico", 25.0));

        Farmacia f2 = new Farmacia("Farmacia Los Pinos", "Norte", "Calle Bolívar");
        f2.agregarMedicamento(new Medicamento("Ibuprofeno", "IBU001", "Antiinflamatorio", 18.0));
        f2.agregarMedicamento(new Medicamento("Tapsin", "TAP002", "Analgésico", 24.5));
        f2.agregarMedicamento(new Medicamento("Amoxicilina", "AMO001", "Antibiótico", 45.0));

        dao.agregarFarmacia(f1);
        dao.agregarFarmacia(f2);

        System.out.println("Datos de prueba cargados correctamente");
    }
}