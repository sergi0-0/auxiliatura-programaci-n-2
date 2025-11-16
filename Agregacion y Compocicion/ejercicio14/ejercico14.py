class Empleado:
    def __init__(self, nombre, puesto, salario):
        self.nombre = nombre
        self.puesto = puesto
        self.salario = salario

    def __str__(self):
        return f"{self.nombre} - {self.puesto} (${self.salario:,.2f})"

class Empresa:
    def __init__(self, nombre):
        self.nombre = nombre
        self.empleados = []  
    def agregar_empleado(self, empleado):
        self.empleados.append(empleado)
        print(f"Empleado {empleado.nombre} agregado a {self.nombre}")
    def mostrar_informacion(self):
        print(f"\n=== EMPRESA: {self.nombre.upper()} ===")
        if not self.empleados:
            print("  (No hay empleados registrados)")
        else:
            for emp in self.empleados:
                print(f"  • {emp}")
        print(f"Total empleados: {len(self.empleados)}\n")
    def buscar_empleado(self, nombre):
        for emp in self.empleados:
            if emp.nombre.lower() == nombre.lower():
                return emp
        return None
    def eliminar_empleado(self, nombre):
        empleado = self.buscar_empleado(nombre)
        if empleado:
            self.empleados.remove(empleado)
            print(f"Empleado {nombre} eliminado de {self.nombre}")
            return True
        else:
            print(f"Empleado {nombre} no encontrado")
            return False
    def promedio_salarial(self):
        if not self.empleados:
            return 0.0
        total = sum(emp.salario for emp in self.empleados)
        return total / len(self.empleados)
    def empleados_mayor_salario(self, valor):
        return [emp for emp in self.empleados if emp.salario > valor]


if __name__ == "__main__":
    empresa = Empresa("TechBolivia S.A.")

    e1 = Empleado("Juan Pérez", "Desarrollador", 2500.0)
    e2 = Empleado("María Gómez", "Diseñadora UX", 2200.0)
    e3 = Empleado("Carlos López", "Gerente", 4500.0)
    e4 = Empleado("Ana Torres", "Contadora", 3000.0)
    e5 = Empleado("Luis Mamani", "Soporte Técnico", 1800.0)

    empresa.agregar_empleado(e1)
    empresa.agregar_empleado(e2)
    empresa.agregar_empleado(e3)
    empresa.agregar_empleado(e4)
    empresa.agregar_empleado(e5)

    empresa.mostrar_informacion()

    print("=== BÚSQUEDA ===")
    emp_buscado = empresa.buscar_empleado("Carlos López")
    if emp_buscado:
        print(f"Encontrado: {emp_buscado}")
    else:
        print("Empleado no encontrado")

    print("\n=== ELIMINACIÓN ===")
    empresa.eliminar_empleado("Ana Torres")
    empresa.mostrar_informacion()

    print("=== ESTADÍSTICAS ===")
    promedio = empresa.promedio_salarial()
    print(f"Promedio salarial: ${promedio:,.2f}")

    print(f"\nEmpleados con salario > $2500:")
    altos = empresa.empleados_mayor_salario(2500)
    if altos:
        for emp in altos:
            print(f"  • {emp}")
    else:
        print("  (Ninguno)")

    print("\n--- Demostración de Agregación ---")
    del empresa  
    print(f"Empleado sigue existiendo: {e1}")  