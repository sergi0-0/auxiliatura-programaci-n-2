package org.ejemplo.dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.ejemplo.model.Prestamo;
import org.ejemplo.model.Libro;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ArchPrestamo {
    private String nomArch;
    private List<Prestamo> prestamos;

    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public ArchPrestamo(String nomArch) {
        this.nomArch = nomArch;
        this.prestamos = cargar();
    }

    public List<Prestamo> getPrestamos() { return new ArrayList<>(prestamos); }

    public void guardar(Prestamo p) {
        prestamos.add(p);
        guardarTodo();
    }
    public double ingresoPorLibro(String codLibro, List<Libro> libros) {
        Libro libro = libros.stream()
                .filter(l -> l.getCodLibro().equals(codLibro))
                .findFirst().orElse(null);
        if (libro == null) return 0.0;

        int totalCant = prestamos.stream()
                .filter(p -> p.getCodLibro().equals(codLibro))
                .mapToInt(p -> p.getCantidad())
                .sum();

        return totalCant * libro.getPrecio();
    }

    public String libroMasPrestado(List<Libro> libros) {
        if (prestamos.isEmpty()) return "Ninguno";

        Map<String, Integer> conteo = prestamos.stream()
                .collect(Collectors.groupingBy(Prestamo::getCodLibro,
                        Collectors.summingInt(Prestamo::getCantidad)));

        return conteo.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(e -> {
                    String cod = e.getKey();
                    Libro l = libros.stream().filter(lib -> lib.getCodLibro().equals(cod)).findFirst().orElse(null);
                    return l != null ? l.getTitulo() + " (" + e.getValue() + " veces)" : cod;
                })
                .orElse("Ninguno");
    }

    public String clienteConMasPrestamos() {
        if (prestamos.isEmpty()) return "Ninguno";

        Map<String, Integer> conteo = prestamos.stream()
                .collect(Collectors.groupingBy(Prestamo::getCodCliente,
                        Collectors.summingInt(Prestamo::getCantidad)));

        return conteo.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(e -> e.getKey() + " (" + e.getValue() + " libros)")
                .orElse("Ninguno");
    }

    private void guardarTodo() {
        try (Writer w = new FileWriter(nomArch)) {
            gson.toJson(prestamos, w);
        } catch (IOException e) {}
    }

    private List<Prestamo> cargar() {
        File f = new File(nomArch);
        if (!f.exists()) return new ArrayList<>();
        try (Reader r = new FileReader(nomArch)) {
            Type type = new TypeToken<ArrayList<Prestamo>>(){}.getType();
            List<Prestamo> lista = gson.fromJson(r, type);
            return lista != null ? lista : new ArrayList<>();
        } catch (IOException e) { return new ArrayList<>(); }
    }
}