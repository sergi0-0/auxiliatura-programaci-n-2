package org.ejemplo.dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.ejemplo.model.Animal;
import org.ejemplo.model.Zoologico;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ArchZoo {
    private String nombre; 
    private List<Zoologico> zoologicos;

    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public ArchZoo(String nombre) {
        this.nombre = nombre;
        this.zoologicos = cargar();
    }

    public void crear(Zoologico z) {
        zoologicos.removeIf(zoo -> zoo.getId() == z.getId());
        zoologicos.add(z);
        guardar();
    }

    public boolean modificar(int id, Zoologico nuevo) {
        for (int i = 0; i < zoologicos.size(); i++) {
            if (zoologicos.get(i).getId() == id) {
                zoologicos.set(i, nuevo);
                guardar();
                return true;
            }
        }
        return false;
    }

    public boolean eliminar(int id) {
        boolean eliminado = zoologicos.removeIf(z -> z.getId() == id);
        if (eliminado) guardar();
        return eliminado;
    }

    public List<Zoologico> zoologicosMayorVariedad() {
        if (zoologicos.isEmpty()) return List.of();

        int maxVariedad = zoologicos.stream()
                .mapToInt(Zoologico::contarEspeciesDiferentes)
                .max().orElse(0);

        return zoologicos.stream()
                .filter(z -> z.contarEspeciesDiferentes() == maxVariedad)
                .toList();
    }

    public List<Zoologico> zoologicosVacios() {
        return zoologicos.stream()
                .filter(z -> z.getNroAnimales() == 0)
                .toList();
    }

    public void mostrarAnimalesDeEspecie(String especie) {
        System.out.println("\nAnimales de especie '" + especie + "':");
        boolean encontrado = false;
        for (Zoologico z : zoologicos) {
            for (int i = 0; i < z.getNroAnimales(); i++) {
                Animal a = z.getAnimales()[i];
                if (a.getEspecie().equalsIgnoreCase(especie)) {
                    System.out.println(z.getNombre() + " → " + a);
                    encontrado = true;
                }
            }
        }
        if (!encontrado) System.out.println("   No hay animales de esa especie");
    }

    public void moverAnimales(int idOrigen, int idDestino) {
        Zoologico origen = buscarPorId(idOrigen);
        Zoologico destino = buscarPorId(idDestino);

        if (origen == null || destino == null) {
            System.out.println("Uno de los zoológicos no existe");
            return;
        }
        if (origen.getNroAnimales() == 0) {
            System.out.println("El zoológico origen está vacío");
            return;
        }

        for (int i = 0; i < origen.getNroAnimales(); i++) {
            destino.agregarAnimal(origen.getAnimales()[i]);
        }
        origen.nroAnimales = 0; 
        guardar();
        System.out.println("Todos los animales fueron movidos correctamente");
    }

    private Zoologico buscarPorId(int id) {
        return zoologicos.stream()
                .filter(z -> z.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public List<Zoologico> obtenerTodos() {
        return new ArrayList<>(zoologicos);
    }

    public void listarTodo() {
        for (Zoologico z : zoologicos) {
            System.out.println(z);
            for (int i = 0; i < z.getNroAnimales(); i++) {
                System.out.println("   " + z.getAnimales()[i]);
            }
        }
    }

    private void guardar() {
        try (Writer w = new FileWriter(nombre)) {
            gson.toJson(zoologicos, w);
        } catch (IOException e) { e.printStackTrace(); }
    }

    private List<Zoologico> cargar() {
        File f = new File(nombre);
        if (!f.exists()) return new ArrayList<>();
        try (Reader r = new FileReader(nombre)) {
            Type type = new TypeToken<ArrayList<Zoologico>>(){}.getType();
            List<Zoologico> lista = gson.fromJson(r, type);
            return lista != null ? lista : new ArrayList<>();
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }
}