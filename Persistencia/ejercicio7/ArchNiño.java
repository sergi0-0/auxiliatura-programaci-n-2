package org.ejemplo.dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.ejemplo.model.Niño.Niño;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;

public class ArchNiño {
    private String na; 
    private List<Niño> niños;

    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public ArchNiño(String na) {
        this.na = na;
        this.niños = cargar();
    }

    public void guardar(Niño n) {
        niños.removeIf(nino -> nino.getCi() == n.getCi());
        niños.add(n);
        guardarTodo();
    }

    public List<Niño> obtenerTodos() {
        return new ArrayList<>(niños);
    }

    public long cantidadConPesoAdecuado() {
        return niños.stream()
                .filter(this::pesoAdecuado)
                .count();
    }

    public List<Niño> niñosConPesoOTallaInadecuada() {
        return niños.stream()
                .filter(n -> !pesoAdecuado(n))
                .toList();
    }

    public double promedioEdad() {
        if (niños.isEmpty()) return 0.0;
        return niños.stream()
                .mapToInt(Niño::getEdad)
                .average()
                .orElse(0.0);
    }

    public Niño buscarPorCi(int ci) {
        return niños.stream()
                .filter(n -> n.getCi() == ci)
                .findFirst()
                .orElse(null);
    }

    public List<Niño> niñosConMayorTalla() {
        if (niños.isEmpty()) return List.of();
        double maxTalla = niños.stream()
                .mapToDouble(Niño::getTallaCm)
                .max().orElse(0);
        return niños.stream()
                .filter(n -> n.getTallaCm() == maxTalla)
                .toList();
    }

    private boolean pesoAdecuado(Niño n) {
        double peso = n.getPesoKg();
        int edad = n.getEdad();

        if (edad < 2) return peso >= 8 && peso <= 14;
        if (edad <= 5) return peso >= 14 && peso <= 22;
        if (edad <= 8) return peso >= 20 && peso <= 32;
        if (edad <= 12) return peso >= 28 && peso <= 50;
        return true;
    }

    private void guardarTodo() {
        try (Writer w = new FileWriter(na)) {
            gson.toJson(niños, w);
        } catch (IOException e) { e.printStackTrace(); }
    }

    private List<Niño> cargar() {
        File f = new File(na);
        if (!f.exists()) return new ArrayList<>();
        try (Reader r = new FileReader(na)) {
            Type type = new TypeToken<ArrayList<Niño>>(){}.getType();
            List<Niño> lista = gson.fromJson(r, type);
            return lista != null ? lista : new ArrayList<>();
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }
}