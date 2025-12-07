package org.ejemplo.model.Niño;

import org.ejemplo.model.Persona;

public class Niño extends Persona {
    private int edad;
    private String peso;     
    private String talla;    

    public Niño() { super(); } 

    public Niño(String nombre, String apellidoPaterno, String apellidoMaterno, int ci,
                int edad, String peso, String talla) {
        super(nombre, apellidoPaterno, apellidoMaterno, ci);
        this.edad = edad;
        this.peso = peso;
        this.talla = talla;
    }

    public int getEdad() { return edad; }
    public void setEdad(int edad) { this.edad = edad; }

    public String getPeso() { return peso; }
    public void setPeso(String peso) { this.peso = peso; }

    public String getTalla() { return talla; }
    public void setTalla(String talla) { this.talla = talla; }

    public double getPesoKg() {
        try {
            return Double.parseDouble(peso.replaceAll("[^0-9.]", ""));
        } catch (Exception e) { return 0; }
    }
    

    public double getTallaCm() {
        try {
            return Double.parseDouble(talla.replaceAll("[^0-9.]", ""));
        } catch (Exception e) { return 0; }
    }

    @Override
    public String toString() {
        return String.format("%-30s Edad: %2d | Peso: %-6s | Talla: %-6s",
                super.toString(), edad, peso, talla);
    }
}