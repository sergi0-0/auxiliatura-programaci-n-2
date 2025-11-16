
package Ejercicio10;
class Evento {
    private String nombre;
    private int nc; // número de charlas
    private Charla[] charlas;

    public Evento(String nombre) {
        this.nombre = nombre;
        this.charlas = new Charla[50];
        this.nc = 0;
    }

    public boolean agregarCharla(Charla c) {
        if (nc < 50) {
            charlas[nc++] = c;
            return true;
        }
        return false;
    }

    // === a) Edad promedio de participantes ===
    public double edadPromedioParticipantes() {
        int sumaEdades = 0;
        int totalParticipantes = 0;
        for (int i = 0; i < nc; i++) {
            Charla charla = charlas[i];
            for (int j = 0; j < charla.getNp(); j++) {
                sumaEdades += charla.participantes[j].getEdad();
                totalParticipantes++;
            }
        }
        return totalParticipantes > 0 ? (double) sumaEdades / totalParticipantes : 0;
    }

    // === b) Buscar persona por nombre y apellido ===
    public boolean existePersona(String nombre, String apellido) {
        // Buscar en participantes
        for (int i = 0; i < nc; i++) {
            Charla charla = charlas[i];
            for (int j = 0; j < charla.getNp(); j++) {
                Participante p = charla.participantes[j];
                if (p.getNombre().equalsIgnoreCase(nombre) && p.getApellido().equalsIgnoreCase(apellido)) {
                    return true;
                }
            }
        }
        // Buscar en speakers
        for (int i = 0; i < nc; i++) {
            Speaker s = charlas[i].getSpeaker();
            if (s.getNombre().equalsIgnoreCase(nombre) && s.getApellido().equalsIgnoreCase(apellido)) {
                return true;
            }
        }
        return false;
    }

    // === c) Eliminar charlas si speaker con CI X no asistió ===
    public void eliminarCharlasDeSpeakerNoAsistio(int ciSpeaker) {
        for (int i = 0; i < nc; i++) {
            if (charlas[i].getSpeaker().getCi() == ciSpeaker && !charlas[i].isSpeakerAsistio()) {
                System.out.println("Eliminando charla: " + charlas[i].getNombreCharla() + " (Speaker no asistió)");
                // Mover charlas hacia atrás
                for (int j = i; j < nc - 1; j++) {
                    charlas[j] = charlas[j + 1];
                }
                charlas[--nc] = null;
                i--; // revisar el nuevo en esta posición
            }
        }
    }

    // === d) Ordenar charlas por número de participantes (descendente) ===
    public void ordenarCharlasPorParticipantes() {
        for (int i = 0; i < nc - 1; i++) {
            for (int j = 0; j < nc - i - 1; j++) {
                if (charlas[j].getNp() < charlas[j + 1].getNp()) {
                    Charla temp = charlas[j];
                    charlas[j] = charlas[j + 1];
                    charlas[j + 1] = temp;
                }
            }
        }
    }

    public void mostrarCharlas() {
        System.out.println("\n=== CHARLAS DEL EVENTO '" + nombre + "' ===");
        for (int i = 0; i < nc; i++) {
            System.out.println((i + 1) + ". " + charlas[i]);
        }
    }
}