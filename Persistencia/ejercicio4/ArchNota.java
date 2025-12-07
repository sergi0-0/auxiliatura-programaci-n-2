package org.ejemplo.dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.ejemplo.model.Estudiante;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ArchNota {
    private String nombreArch;
    private List<Estudiante> estudiantes;

    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public ArchNota(String nombreArch) {
        this.nombreArch = nombreArch;
        this.estudiantes = cargar();
    }

    public void agregar(Estudiante e) {
        estudiantes.removeIf(es -> es.getCarnet() == e.getCarnet());
        estudiantes.add(e);
        guardar();
    }

    public double obtenerPromedioGeneral() {
        if (estudiantes.isEmpty()) return 0.0;
        return estudiantes.stream()
                .mapToDouble(Estudiante::getNotaFinal)
                .average()
                .orElse(0.0);
    }

    public List<Estudiante> buscarPorMateria(String materia) {
        return estudiantes.stream()
                .filter(e -> e.getMateria().equalsIgnoreCase(materia.trim()))
                .collect(Collectors.toList());
    }

    public int eliminarPorMateria(String materia) {
        int antes = estudiantes.size();
        estudiantes.removeIf(e -> e.getMateria().equalsIgnoreCase(materia.trim()));
        guardar(); 
        return antes - estudiantes.size();
    }

    public List<Estudiante> obtenerTodos() {
        return new ArrayList<>(estudiantes);
    }
    private void guardar() {
        try (Writer writer = new FileWriter(nombreArch)) {
            gson.toJson(estudiantes, writer);
        } catch (IOException ex) {
            System.err.println("Error al guardar en " + nombreArch + ": " + ex.getMessage());
        }
    }

    private List<Estudiante> cargar() {
        File file = new File(nombreArch);
        if (!file.exists()) {
            return new ArrayList<>();
        }

        try (Reader reader = new FileReader(nombreArch)) {
            Type listType = new TypeToken<ArrayList<Estudiante>>(){}.getType();
            List<Estudiante> lista = gson.fromJson(reader, listType);
            return lista != null ? lista : new ArrayList<>();
        } catch (IOException e) {
            System.err.println("Error al cargar " + nombreArch + ", se inicia vacío");
            return new ArrayList<>();
        }
    }
}