package org.ejemplo.dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.ejemplo.model.Farmacia;
import org.ejemplo.model.Medicamento;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ArchFarmacia {
    private String na; 
    public List<Farmacia> farmacias;
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public ArchFarmacia(String na) {
        this.na = na;
        this.farmacias = cargar();
    }

    public void crearArchivo() {
        guardar();
    }

    public void mostrarMedicamentosSucursal(String sucursal) {
        farmacias.stream()
                .filter(f -> f.getSucursal().equalsIgnoreCase(sucursal))
                .forEach(f -> {
                    System.out.println("\n" + f);
                    for (int i = 0; i < f.getNroMedicamentos(); i++) {
                        System.out.println("   " + f.getMedicamentos()[i]);
                    }
                });
    }

    public void buscarTapsin() {
        System.out.println("\nFarmacias que tienen TAPSIN:");
        farmacias.forEach(f -> {
            boolean tiene = false;
            for (int i = 0; i < f.getNroMedicamentos(); i++) {
                if (f.getMedicamentos()[i].getNombre().equalsIgnoreCase("Tapsin")) {
                    tiene = true;
                    break;
                }
            }
            if (tiene) {
                System.out.printf("Sucursal: %s | Dirección: %s%n", f.getSucursal(), f.getDireccion());
            }
        });
    }

    public void mostrarMedicamentosPorTipo(String tipo) {
        System.out.println("\nMedicamentos de tipo: " + tipo);
        farmacias.forEach(f -> {
            Arrays.stream(f.getMedicamentos())
                    .limit(f.getNroMedicamentos())
                    .filter(m -> m.getTipo().equalsIgnoreCase(tipo))
                    .forEach(m -> System.out.println(f.getSucursal() + " → " + m));
        });
    }

    public void mostrarFarmaciasOrdenadasPorDireccion() {
        System.out.println("\nFARMACIAS ORDENADAS POR DIRECCIÓN:");
        farmacias.stream()
                .sorted(Comparator.comparing(Farmacia::getDireccion, String.CASE_INSENSITIVE_ORDER))
                .forEach(f -> System.out.println(f.getDireccion() + " - " + f.getNombreFarmacia() + " (" + f.getSucursal() + ")"));
    }

    public void moverMedicamentosTipo(String tipo, String sucursalOrigen, String sucursalDestino) {
        Farmacia origen = buscarFarmacia(sucursalOrigen);
        Farmacia destino = buscarFarmacia(sucursalDestino);
        if (origen == null || destino == null) {
            System.out.println("Una de las sucursales no existe");
            return;
        }

        int movidos = 0;
        for (int i = 0; i < origen.getNroMedicamentos(); i++) {
            Medicamento m = origen.getMedicamentos()[i];
            if (m != null && m.getTipo().equalsIgnoreCase(tipo)) {
                destino.agregarMedicamento(m);
                origen.getMedicamentos()[i] = null; 
                movidos++;
            }
        }
        compactarArray(origen);
        guardar();
        System.out.println("Se movieron " + movidos + " medicamento(s) de tipo " + tipo);
    }

    private Farmacia buscarFarmacia(String sucursal) {
        return farmacias.stream()
                .filter(f -> f.getSucursal().equalsIgnoreCase(sucursal))
                .findFirst()
                .orElse(null);
    }

    private void compactarArray(Farmacia f) {
        Medicamento[] temp = new Medicamento[100];
        int idx = 0;
        for (int i = 0; i < f.getNroMedicamentos(); i++) {
            if (f.getMedicamentos()[i] != null) {
                temp[idx++] = f.getMedicamentos()[i];
            }
        }
        f.getMedicamentos()[0] = temp[0]; 
        java.lang.reflect.Field field;
        try {
            field = Farmacia.class.getDeclaredField("nroMedicamentos");
            field.setAccessible(true);
            field.setInt(f, idx);
        } catch (Exception ignored) {}
    }

    public void listar() {
        farmacias.forEach(f -> {
            System.out.println(f);
            for (int i = 0; i < f.getNroMedicamentos(); i++) {
                System.out.println("   " + f.getMedicamentos()[i]);
            }
        });
    }

    private void guardar() {
        try (Writer w = new FileWriter(na)) {
            gson.toJson(farmacias, w);
        } catch (IOException e) { e.printStackTrace(); }
    }

    private List<Farmacia> cargar() {
        File f = new File(na);
        if (!f.exists()) return new ArrayList<>();
        try (Reader r = new FileReader(na)) {
            Type type = new TypeToken<ArrayList<Farmacia>>(){}.getType();
            List<Farmacia> lista = gson.fromJson(r, type);
            return lista != null ? lista : new ArrayList<>();
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    public Object obtenerTodos() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    public void agregarFarmacia(Farmacia f) {
        farmacias.add(f);
        guardar();
    }
    public List<Farmacia> getFarmacias() {
        return new ArrayList<>(farmacias);
    }
}