/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicio10;
class Charla {
    private String lugar;
    private String nombreCharla;
    private Speaker speaker;
    private int np; // número de participantes
    public Participante[] participantes;
    private boolean speakerAsistio = true; // por defecto asiste

    public Charla(String lugar, String nombreCharla, Speaker speaker) {
        this.lugar = lugar;
        this.nombreCharla = nombreCharla;
        this.speaker = speaker;
        this.participantes = new Participante[50];
        this.np = 0;
    }

    public boolean agregarParticipante(Participante p) {
        if (np < 50) {
            participantes[np++] = p;
            return true;
        }
        return false;
    }

    public void setSpeakerNoAsistio() {
        this.speakerAsistio = false;
    }

    public boolean isSpeakerAsistio() { return speakerAsistio; }
    public Speaker getSpeaker() { return speaker; }
    public int getNp() { return np; }
    public String getNombreCharla() { return nombreCharla; }
    public String getLugar() { return lugar; }

    @Override
    public String toString() {
        return "Charla: '" + nombreCharla + "' en " + lugar +
               " | Speaker: " + speaker.getNombre() + " " + speaker.getApellido() +
               " | Participantes: " + np + "/50";
    }
}