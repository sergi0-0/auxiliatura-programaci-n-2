
package Ejercicio6;
class Registro {
    private String fecha, hora, tipoOperacion, detalle;

    public Registro(String tipo, String detalle) {
        this.fecha = java.time.LocalDate.now().toString();
        this.hora = java.time.LocalTime.now().toString().substring(0,8);
        this.tipoOperacion = tipo;
        this.detalle = detalle;
    }

    @Override
    public String toString() {
        return "[" + fecha + " " + hora + "] " + tipoOperacion + ": " + detalle;
    }
}