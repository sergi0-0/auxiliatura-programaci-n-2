/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Ejercicio10;
public class Main {
    public static void main(String[] args) {
        Evento evento = new Evento("Tech Summit 2025");

        // Speakers
        Speaker s1 = new Speaker("Ana", "García", 35, 12345, "IA");
        Speaker s2 = new Speaker("Luis", "Martínez", 40, 67890, "Ciberseguridad");
        Speaker s3 = new Speaker("Carlos", "López", 38, 54321, "Blockchain");

        // Charlas
        Charla c1 = new Charla("Auditorio A", "Introducción a IA", s1);
        Charla c2 = new Charla("Sala B", "Seguridad en la Nube", s2);
        Charla c3 = new Charla("Auditorio C", "Futuro del Blockchain", s3);

        // Participantes
        Participante p1 = new Participante("Juan", "Pérez", 22, 1111, 1001);
        Participante p2 = new Participante("María", "Gómez", 25, 2222, 1002);
        Participante p3 = new Participante("Pedro", "Silva", 20, 3333, 1003);
        Participante p4 = new Participante("Luis", "Martínez", 30, 4444, 1004); // mismo apellido que speaker
        Participante p5 = new Participante("Ana", "García", 28, 5555, 1005);   // mismo nombre que speaker

        // Agregar participantes a charlas
        c1.agregarParticipante(p1);
        c1.agregarParticipante(p2);
        c1.agregarParticipante(p3);

        c2.agregarParticipante(p2);
        c2.agregarParticipante(p4);

        c3.agregarParticipante(p1);
        c3.agregarParticipante(p5);

        // Agregar charlas al evento
        evento.agregarCharla(c1);
        evento.agregarCharla(c2);
        evento.agregarCharla(c3);

        // === a) Edad promedio ===
        System.out.println("a) Edad promedio de participantes: " + String.format("%.2f", evento.edadPromedioParticipantes()));

        // === b) Buscar persona ===
        System.out.println("\nb) ¿Existe 'Ana García'? → " + evento.existePersona("Ana", "García"));
        System.out.println("   ¿Existe 'Pedro Silva'? → " + evento.existePersona("Pedro", "Silva"));
        System.out.println("   ¿Existe 'Ximena Ruiz'? → " + evento.existePersona("Ximena", "Ruiz"));

        // === c) Speaker no asistió (CI: 12345) ===
        System.out.println("\n--- Speaker Ana García (CI:12345) NO asistió ---");
        c1.setSpeakerNoAsistio(); // Simulamos que no vino
        evento.eliminarCharlasDeSpeakerNoAsistio(12345);

        // === d) Ordenar por participantes ===
        evento.ordenarCharlasPorParticipantes();
        evento.mostrarCharlas();
    }
}