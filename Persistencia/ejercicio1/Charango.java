package org.ejemplo.model;
import java.util.Arrays;
public class Charango {
    private String material;
    private int nroCuerdas;
    private boolean[] cuerdas;
    public Charango() { } 
    public Charango(String material, int nroCuerdas, boolean[] cuerdas) {
        this.material = material;
        this.nroCuerdas = nroCuerdas;
        this.cuerdas = Arrays.copyOf(cuerdas, 10);
    }
    public String getMaterial() { return material; }
    public void setMaterial(String material) { this.material = material; }

    public int getNroCuerdas() { return nroCuerdas; }
    public void setNroCuerdas(int nroCuerdas) { this.nroCuerdas = nroCuerdas; }

    public boolean[] getCuerdas() { return cuerdas; }
    public void setCuerdas(boolean[] cuerdas) { this.cuerdas = Arrays.copyOf(cuerdas, 10); }
    public int getCuerdasRotas() {
        int rotas = 0;
        for (boolean b : cuerdas) if (!b) rotas++;
        return rotas;
    }
    public boolean tieneTodasLasCuerdasBuenas() {
        for (boolean b : cuerdas) if (!b) return false;
        return true;
    }
    @Override
    public String toString() {
        return String.format("Charango{material='%s', cuerdas=%d, rotas=%d}",
                material, nroCuerdas, getCuerdasRotas());
    }
}