
package Ejercicio4;
class Ropero {
    private String material;
    private Ropa[] ropas;
    private int nrRopas;
    public Ropero(String material) {
        this.material = material;
        this.ropas = new Ropa[20];
        this.nrRopas = 0;
    }
    // b) Agregar prenda
    public boolean agregar(Ropa prenda) {
        if (nrRopas < 20) {
            ropas[nrRopas++] = prenda;
            return true;
        }
        return false;
    }
    // c) Eliminar por material
    public void eliminarPorMaterial(String mat) {
        for (int i = 0; i < nrRopas; i++) {
            if (ropas[i] != null && ropas[i].getMaterial().equalsIgnoreCase(mat)) {
                ropas[i] = null;
            }
        }
        compactar();
    }
    // c) Eliminar por tipo
    public void eliminarPorTipo(String tipo) {
        for (int i = 0; i < nrRopas; i++) {
            if (ropas[i] != null && ropas[i].getTipo().equalsIgnoreCase(tipo)) {
                ropas[i] = null;
            }
        }
        compactar();
    }
    // Compactar el arreglo (eliminar nulos)
    private void compactar() {
        int writeIndex = 0;
        for (int readIndex = 0; readIndex < nrRopas; readIndex++) {
            if (ropas[readIndex] != null) {
                ropas[writeIndex++] = ropas[readIndex];
            }
        }
        for (int i = writeIndex; i < nrRopas; i++) {
            ropas[i] = null;
        }
        nrRopas = writeIndex;
    }

    // Mostrar prendas de un material
    public void mostrarPorMaterial(String mat) {
        System.out.println("Prendas de material '" + mat + "':");
        boolean encontrado = false;
        for (int i = 0; i < nrRopas; i++) {
            if (ropas[i] != null && ropas[i].getMaterial().equalsIgnoreCase(mat)) {
                System.out.println("  - " + ropas[i]);
                encontrado = true;
            }
        }
        if (!encontrado) System.out.println("  (Ninguna)");
    }
    // Mostrar prendas de un tipo
    public void mostrarPorTipo(String tipo) {
        System.out.println("Prendas de tipo '" + tipo + "':");
        boolean encontrado = false;
        for (int i = 0; i < nrRopas; i++) {
            if (ropas[i] != null && ropas[i].getTipo().equalsIgnoreCase(tipo)) {
                System.out.println("  - " + ropas[i]);
                encontrado = true;
            }
        }
        if (!encontrado) System.out.println("  (Ninguna)");
    }
    public void mostrarTodo() {
        System.out.println("=== Contenido del Ropero (material: " + material + ") ===");
        if (nrRopas == 0) {
            System.out.println("  (Vacío)");
        } else {
            for (int i = 0; i < nrRopas; i++) {
                System.out.println("  [" + i + "] " + ropas[i]);
            }
        }
        System.out.println("Total prendas: " + nrRopas + "\n");
    }
}