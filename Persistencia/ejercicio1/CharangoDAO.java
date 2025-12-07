package org.ejemplo.dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.ejemplo.model.Charango;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CharangoDAO {
    private static final String ARCHIVO = "charangos.json";
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private List<Charango> charangos;

    public CharangoDAO() {
        this.charangos = cargar();
    }

    public List<Charango> obtenerTodos() {
        return new ArrayList<>(charangos); 
    }

    public void guardarTodos(List<Charango> lista) {
        this.charangos = new ArrayList<>(lista);
        guardarEnArchivo();
    }

    public void agregar(Charango c) {
        charangos.add(c);
        guardarEnArchivo();
    }

    public void eliminar(Charango c) {
        charangos.remove(c);
        guardarEnArchivo();
    }

    //PERSISTENCIA
    private void guardarEnArchivo() {
        try (Writer writer = new FileWriter(ARCHIVO)) {
            gson.toJson(charangos, writer);
        } catch (IOException e) {
            System.err.println("Error al guardar charangos: " + e.getMessage());
        }
    }

    private List<Charango> cargar() {
        File file = new File(ARCHIVO);
        if (!file.exists()) {
            return new ArrayList<>();
        }

        try (Reader reader = new FileReader(ARCHIVO)) {
            Type listType = new TypeToken<ArrayList<Charango>>(){}.getType();
            List<Charango> lista = gson.fromJson(reader, listType);
            return lista != null ? lista : new ArrayList<>();
        } catch (IOException e) {
            System.err.println("Error al cargar charangos, se crea lista vacía: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}