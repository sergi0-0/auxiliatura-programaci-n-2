package org.ejemplo.model;

public class Zoologico {
    private int id;
    private String nombre;
    public int nroAnimales;
    private Animal[] animales = new Animal[30];

    public Zoologico() {} 

    public Zoologico(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        this.nroAnimales = 0;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public int getNroAnimales() { return nroAnimales; }
    public Animal[] getAnimales() { return animales; }

    public void agregarAnimal(Animal a) {
        if (nroAnimales < 30) {
            animales[nroAnimales++] = a;
        }
    }

    public int contarEspeciesDiferentes() {
        if (nroAnimales == 0) return 0;
        return (int) java.util.Arrays.stream(animales, 0, nroAnimales)
                .map(Animal::getEspecie)
                .distinct()
                .count();
    }

    @Override
    public String toString() {
        return "ID: " + id + id + " | " + nombre + " | Animales: " + nroAnimales;
    }
}