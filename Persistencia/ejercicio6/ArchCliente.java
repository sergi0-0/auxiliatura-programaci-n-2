package org.ejemplo.dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.ejemplo.model.Cliente;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import org.ejemplo.model.Prestamo;

public class ArchCliente {
    private String nomArch;
    private List<Cliente> clientes;

    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public ArchCliente(String nomArch) {
        this.nomArch = nomArch;
        this.clientes = cargar();
    }

    public List<Cliente> getClientes() { return new ArrayList<>(clientes); }

    public void guardar(Cliente c) {
        clientes.removeIf(cl -> cl.getCodCliente().equals(c.getCodCliente()));
        clientes.add(c);
        guardarTodo();
    }

public List<Cliente> clientesNuncaPrestaronLibro(String codLibro, List<Prestamo> prestamos) {
    var codigosQuePrestaronEsteLibro = prestamos.stream()
            .filter(p -> p.getCodLibro().equals(codLibro))
            .map(Prestamo::getCodCliente)
            .toList();
    return clientes.stream()
            .filter(c -> !codigosQuePrestaronEsteLibro.contains(c.getCodCliente()))
            .toList();
}

    private void guardarTodo() {
        try (Writer w = new FileWriter(nomArch)) {
            gson.toJson(clientes, w);
        } catch (IOException e) {}
    }

    private List<Cliente> cargar() {
        File f = new File(nomArch);
        if (!f.exists()) return new ArrayList<>();
        try (Reader r = new FileReader(nomArch)) {
            Type type = new TypeToken<ArrayList<Cliente>>(){}.getType();
            List<Cliente> lista = gson.fromJson(r, type);
            return lista != null ? lista : new ArrayList<>();
        } catch (IOException e) { return new ArrayList<>(); }
    }
}