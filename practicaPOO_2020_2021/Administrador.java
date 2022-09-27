/**
 * Write a description of class Administrador here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Administrador extends Empleado {

    public Administrador(String nombre, String apellidos, int edad) {
        super(nombre, apellidos, edad);
    }

    public void modificarPersona() {
    }

    @Override
    public String toString() {
        return "\nRol: Administrador" + super.toString();
    }
}
