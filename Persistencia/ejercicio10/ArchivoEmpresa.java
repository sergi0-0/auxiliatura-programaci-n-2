package org.finalcarrera.dao;

import org.finalcarrera.model.Empresa;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ArchivoEmpresa {
    private static final String ARCHIVO = "empresas.txt";
    private List<Empresa> empresas;

    public ArchivoEmpresa() {
        this.empresas = cargarDesdeArchivo();
    }

    public void guardar(Empresa e) {
        empresas.removeIf(emp -> emp.getNombre().equalsIgnoreCase(e.getNombre()));
        empresas.add(e);
        guardarEnArchivo();
        System.out.println("Empresa '" + e.getNombre() + "' guardada correctamente.");
    }

    public void mostrarTodas() {
        if (empresas.isEmpty()) {
            System.out.println("\nNo hay empresas registradas aún.");
            return;
        }
        System.out.println("\n=== LISTA DE EMPRESAS ===");
        System.out.println("-".repeat(70));
        empresas.forEach(e -> System.out.println(e.toStringBonito()));
        System.out.println("-".repeat(70));
    }

    public Empresa buscarPorNombre(String nombreBuscado) {
        return empresas.stream()
                .filter(e -> e.getNombre().toLowerCase().contains(nombreBuscado.toLowerCase()))
                .findFirst()
                .orElse(null);
    }

    public List<Empresa> obtenerTodas() {
        return new ArrayList<>(empresas);
    }

    private void guardarEnArchivo() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(ARCHIVO))) {
            for (Empresa e : empresas) {
                pw.println(e.toString()); 
            }
        } catch (IOException e) {
            System.err.println("Error al guardar empresas: " + e.getMessage());
        }
    }

    private List<Empresa> cargarDesdeArchivo() {
        File file = new File(ARCHIVO);
        if (!file.exists()) {
            return new ArrayList<>();
        }

        List<Empresa> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) continue;
                String[] partes = linea.split("\\|");
                if (partes.length == 3) {
                    String nombre = partes[0].trim();
                    String rubro = partes[1].trim();
                    int empleados = Integer.parseInt(partes[2].trim());
                    lista.add(new Empresa(nombre, rubro, empleados));
                }
            }
        } catch (Exception e) {
            System.err.println("Error al cargar empresas: " + e.getMessage());
        }
        return lista;
    }
}