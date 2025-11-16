package Ejercicio6;
import java.util.*;

abstract class Farmacia {
    protected String nombre;
    protected String direccion;
    protected List<Medicamento> medicamentos;
    protected List<Venta> ventas;

    public Farmacia(String nombre, String direccion) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.medicamentos = new ArrayList<>();
        this.ventas = new ArrayList<>();
    }

    public void agregarMedicamento(Medicamento m) {
        medicamentos.add(m);
    }

    public abstract void generarReporte();
}

class Sucursal extends Farmacia {
    public Sucursal(String nombre, String direccion) {
        super(nombre, direccion);
    }

    @Override
    public void generarReporte() {
        System.out.println("Reporte de sucursal: " + nombre);
    }
}

class FarmaciaCentral extends Farmacia {
    public FarmaciaCentral(String nombre, String direccion) {
        super(nombre, direccion);
    }

    @Override
    public void generarReporte() {
        System.out.println("Reporte central: " + nombre);
    }
}