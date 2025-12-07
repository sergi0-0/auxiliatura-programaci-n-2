package org.ejemplo.dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.ejemplo.model.Producto;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ArchivoProducto {
    private String nomA;                  
    private List<Producto> productos;
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public ArchivoProducto(String nomA) {
        this.nomA = nomA;
        this.productos = cargarDesdeArchivo();
        if (this.productos == null) {
            this.productos = new ArrayList<>();
        }
    }

    public void crearArchivo() {
        guardarEnArchivo();
        System.out.println("Archivo '" + nomA + "' creado o ya existe.");
    }

    public void guardarProducto(Producto p) {
        
        productos.removeIf(prod -> prod.getCodigo() == p.getCodigo());
        productos.add(p);
        guardarEnArchivo();
        System.out.println("Producto guardado: " + p.getNombre());
    }

    public Producto buscaProducto(int c) {
        return productos.stream()
                .filter(p -> p.getCodigo() == c)
                .findFirst()
                .orElse(null);
    }

    public double calcularPromedioPrecios() {
        if (productos.isEmpty()) return 0.0;
        return productos.stream()
                .mapToDouble(Producto::getPrecio)
                .average()
                .orElse(0.0);
    }

    public Producto productoMasCaro() {
        if (productos.isEmpty()) return null;
        return productos.stream()
                .max((p1, p2) -> Float.compare(p1.getPrecio(), p2.getPrecio()))
                .orElse(null);
    }

    public List<Producto> obtenerTodos() {
        return new ArrayList<>(productos);
    }

    private void guardarEnArchivo() {
        try (Writer writer = new FileWriter(nomA)) {
            gson.toJson(productos, writer);
        } catch (IOException e) {
            System.err.println("Error al guardar en " + nomA + ": " + e.getMessage());
        }
    }

    private List<Producto> cargarDesdeArchivo() {
        File file = new File(nomA);
        if (!file.exists()) {
            return new ArrayList<>();
        }

        try (Reader reader = new FileReader(nomA)) {
            Type listType = new TypeToken<ArrayList<Producto>>(){}.getType();
            List<Producto> lista = gson.fromJson(reader, listType);
            return lista != null ? lista : new ArrayList<>();
        } catch (IOException e) {
            System.err.println("Error al cargar " + nomA + ", se inicia vacío: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}