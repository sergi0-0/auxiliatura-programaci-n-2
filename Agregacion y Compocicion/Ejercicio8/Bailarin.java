
package Ejercicio8;
class Bailarin extends Persona {
    private Fraternidad fraternidad;
    private Facultad facultad;

    public Bailarin(String nombre, int carnet, int edad, Facultad facultad) {
        super(nombre, carnet, edad);
        this.facultad = facultad;
    }

    public void registrarEnFraternidad(Fraternidad f) {
        this.fraternidad = f;
        SistemaFraternidades.registrarBailarinEnFraternidad(this, f);
    }

    public Fraternidad getFraternidad() { return fraternidad; }
    public Facultad getFacultad() { return facultad; }

    @Override
    public String toString() {
        return super.toString() + " | Facultad: " + facultad +
               (fraternidad != null ? " | Fraternidad: " + fraternidad.getNombre() : " | Sin fraternidad");
    }
}