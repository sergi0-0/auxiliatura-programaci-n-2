package org.ejemplo.dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.ejemplo.model.Trabajador;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ArchivoTrabajador {
    private static final String ARCHIVO = "trabajadores.json";
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private List<Trabajador> trabajadores;

    public ArchivoTrabajador() {
        this.trabajadores = cargarDesdeArchivo();
        if (trabajadores == null) trabajadores = new ArrayList<>();
    }
    // a)
    public void crearArchivo() {
        guardarEnArchivo(); 
        System.out.println("Archivo " + ARCHIVO + " creado/existe.");
    }
    // b)
    public void guardarTrabajadores(List<Trabajador> lista) {
        this.trabajadores = new ArrayList<>(lista);
        guardarEnArchivo();
    }
    // b)
    public void guardarTrabajador(Trabajador t) {
        trabajadores.removeIf(tr -> tr.getCarnet() == t.getCarnet());
        trabajadores.add(t);
        guardarEnArchivo();
    }
    // c) 
    public boolean aumentarSalario(int carnet, int porcentaje) {
        for (Trabajador t : trabajadores) {
            if (t.getCarnet() == carnet) {
                t.aumentarSalario(porcentaje);
                guardarEnArchivo();
                return true;
            }
        }
        return false;
    }
    // d) 
    public Trabajador buscarMejorPagado() {
        if (trabajadores.isEmpty()) return null;
        return trabajadores.stream()
                .max((t1, t2) -> Double.compare(t1.getSalario(), t2.getSalario()))
                .orElse(null);
    }
    // e)
    public List<Trabajador> obtenerOrdenadosPorSalario() {
        return trabajadores.stream()
                .sorted((t1, t2) -> Double.compare(t2.getSalario(), t1.getSalario()))
                .toList();
    }
    public List<Trabajador> obtenerTodos() {
        return new ArrayList<>(trabajadores);
    }

    private void guardarEnArchivo() {
        try (Writer writer = new FileWriter(ARCHIVO)) {
            gson.toJson(trabajadores, writer);
        } catch (IOException e) {
            System.err.println("Error al guardar trabajadores: " + e.getMessage());
        }
    }

    private List<Trabajador> cargarDesdeArchivo() {
        File file = new File(ARCHIVO);
        if (!file.exists()) return new ArrayList<>();

        try (Reader reader = new FileReader(ARCHIVO)) {
            Type listType = new TypeToken<ArrayList<Trabajador>>(){}.getType();
            List<Trabajador> lista = gson.fromJson(reader, listType);
            return lista != null ? lista : new ArrayList<>();
        } catch (IOException e) {
            System.err.println("Error al leer archivo, se inicia vacío: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}