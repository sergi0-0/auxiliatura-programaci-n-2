package org.ejemplo.dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.ejemplo.model.Libro;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ArchLibro {
    private String nomArch;
    private List<Libro> libros;

    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public ArchLibro(String nomArch) {
        this.nomArch = nomArch;
        this.libros = cargar();
    }

    public List<Libro> getLibros() { return new ArrayList<>(libros); }

    public void guardarLibro(Libro l) {
        libros.removeIf(lib -> lib.getCodLibro().equals(l.getCodLibro()));
        libros.add(l);
        guardarTodo();
    }

    public List<Libro> librosEntrePrecios(double min, double max) {
        return libros.stream()
                .filter(l -> l.getPrecio() >= min && l.getPrecio() <= max)
                .toList();
    }

    private void guardarTodo() {
        try (Writer w = new FileWriter(nomArch)) {
            gson.toJson(libros, w);
        } catch (IOException e) {}
    }

    private List<Libro> cargar() {
        File f = new File(nomArch);
        if (!f.exists()) return new ArrayList<>();
        try (Reader r = new FileReader(nomArch)) {
            Type type = new TypeToken<ArrayList<Libro>>(){}.getType();
            List<Libro> lista = gson.fromJson(r, type);
            return lista != null ? lista : new ArrayList<>();
        } catch (IOException e) { return new ArrayList<>(); }
    }

    public void guardar(Libro libro) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
}