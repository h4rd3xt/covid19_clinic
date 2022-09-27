import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Referencias:
 * http://lineadecodigo.com/java/usando-las-clases-hashset-y-hashmap/
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class GestionDeUsuarios extends Gestor {
    private BufferedReader buffer;

    public GestionDeUsuarios() {
        super();
        buffer = new BufferedReader(new InputStreamReader(System.in));
    }

    public void altaPersona() {
        System.out.println("Alta de una nueva persona");
        System.out.println("------------------");
        try {
            System.out.println("Nombre: ");
            String nombre = buffer.readLine();
            System.out.println("Apellidos: ");
            String apellidos = buffer.readLine();
            System.out.println("Edad: ");
            int edad = Integer.parseInt(buffer.readLine());
            System.out.println("Función de esta persona: ");
            System.out.println("(1)-Administrador");
            System.out.println("(2)-Enfermero");
            System.out.println("(3)-Ténico");
            System.out.println("(4)-Paciente");
            switch (Integer.parseInt(buffer.readLine())) {
                case 1:
                    listaDePersonas.add(new Administrador(nombre, apellidos, edad));
                case 2:
                    listaDePersonas.add(new Enfermero(nombre, apellidos, edad));
                case 3:
                    listaDePersonas.add(new Tecnico(nombre, apellidos, edad));
                case 4:
                    listaDePersonas.add(new Paciente(nombre, apellidos, edad));
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error en el proceso de alta de la persona");
        }
    }

    public void bajaPersona(Persona persona) {
        listaDePersonas.remove(persona);
    }

    public void modificarPersona(Persona persona) {
        System.out.println("\n¿Qué desea modificar de esta persona?");
        System.out.println("(1)-Nombre");
        System.out.println("(2)-Apellido");
        System.out.println("(3)-Edad");
        try {
            switch (Integer.parseInt(buffer.readLine())) {
                case 1:
                    System.out.println("\nNuevo nombre: ");
                    persona.setNombre(buffer.readLine());
                    break;
                case 2:
                    System.out.println("\nNuevo apellido: ");
                    persona.setApellido(buffer.readLine());
                    break;
                case 3:
                    System.out.println("\nNueva edad: ");
                    persona.setEdad(Integer.parseInt(buffer.readLine()));
                    break;
            }
        } catch (NumberFormatException | IOException e) {
            System.out.println("Error al introducir los datos");
        }
    }

    public void listarPersonas(){
        for(Persona persona : listaDePersonas){
            System.out.println(persona.toString());
        }
    }

    public void listarPacientes(){
        for(Persona persona : listaDePersonas){
            if(persona instanceof Paciente){
                System.out.println(persona.toString());
            }
        }
    }
    
    public void listarPersonal(){
        for (Persona persona : listaDePersonas) {
            if (persona instanceof Empleado) {
                System.out.println(persona.toString());
            }
        }
    }

    public void listarEnfermeros(){
        for(Persona persona : listaDePersonas){
            if(persona instanceof Enfermero){
                System.out.println(persona.toString());
            }
        }
    }
}
