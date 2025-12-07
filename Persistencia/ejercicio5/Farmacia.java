package org.ejemplo.model;

import java.util.Arrays;

public class Farmacia {
    private String nombreFarmacia;
    private String sucursal;
    private String direccion;
    private int nroMedicamentos;
    private Medicamento[] medicamentos = new Medicamento[100];

    public Farmacia() {} 

    public Farmacia(String nombreFarmacia, String sucursal, String direccion) {
        this.nombreFarmacia = nombreFarmacia;
        this.sucursal = sucursal;
        this.direccion = direccion;
        this.nroMedicamentos = 0;
    }

    public String getNombreFarmacia() { return nombreFarmacia; }
    public void setNombreFarmacia(String nombreFarmacia) { this.nombreFarmacia = nombreFarmacia; }

    public String getSucursal() { return sucursal; }
    public void setSucursal(String sucursal) { this.sucursal = sucursal; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public int getNroMedicamentos() { return nroMedicamentos; }
    public Medicamento[] getMedicamentos() { return medicamentos; }

    public void agregarMedicamento(Medicamento m) {
        if (nroMedicamentos < 100) {
            medicamentos[nroMedicamentos++] = m;
        }
    }

    @Override
    public String toString() {
        return String.format("Farmacia: %s | Sucursal: %s | Dirección: %s | Medicamentos: %d",
                nombreFarmacia, sucursal, direccion, nroMedicamentos);
    }
}