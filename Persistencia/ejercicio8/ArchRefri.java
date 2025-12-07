package org.ejemplo.dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.ejemplo.model.Alimento;

import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ArchRefri {
    private String nombre;               
    private List<Alimento> alimentos;

    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public ArchRefri(String nombre) {
        this.nombre = nombre;
        this.alimentos = cargar();
    }

    public void crear(Alimento a) {
        alimentos.removeIf(al -> al.getNombre().equalsIgnoreCase(a.getNombre()));
        alimentos.add(a);
        guardar();
    }

    public boolean modificar(String nombreViejo, Alimento nuevo) {
        for (int i = 0; i < alimentos.size(); i++) {
            if (alimentos.get(i).getNombre().equalsIgnoreCase(nombreViejo)) {
                alimentos.set(i, nuevo);
                guardar();
                return true;
            }
        }
        return false;
    }

    public boolean eliminar(String nombre) {
        boolean eliminado = alimentos.removeIf(a -> a.getNombre().equalsIgnoreCase(nombre));
        if (eliminado) guardar();
        return eliminado;
    }
    public List<Alimento> caducadosAntesDe(String fechaX) { 
        LocalDate fecha = LocalDate.parse(fechaX);
        return alimentos.stream()
                .filter(a -> LocalDate.parse(a.getFechaVencimiento()).isBefore(fecha))
                .toList();
    }

    public List<Alimento> sinStock() {
        return alimentos.stream()
                .filter(a -> a.getCantidad() == 0)
                .toList();
    }

    public Alimento buscar(String nombre) {
        return alimentos.stream()
                .filter(a -> a.getNombre().equalsIgnoreCase(nombre))
                .findFirst()
                .orElse(null);
    }

    public Alimento mayorCantidad() {
        return alimentos.stream()
                .max(Comparator.comparingInt(Alimento::getCantidad))
                .orElse(null);
    }

    public List<Alimento> obtenerTodos() {
        return new ArrayList<>(alimentos);
    }
    private void guardar() {
        try (Writer writer = new FileWriter(nombre)) {
            gson.toJson(alimentos, writer);
        } catch (IOException e) {
            System.err.println("Error al guardar: " + e.getMessage());
        }
    }

    private List<Alimento> cargar() {
        File file = new File(nombre);
        if (!file.exists()) return new ArrayList<>();

        try (Reader reader = new FileReader(nombre)) {
            Type type = new TypeToken<ArrayList<Alimento>>(){}.getType();
            List<Alimento> lista = gson.fromJson(reader, type);
            return lista != null ? lista : new ArrayList<>();
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }
}