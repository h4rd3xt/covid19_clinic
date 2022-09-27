import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Referencias:
 * https://stackoverflow.com/questions/19814637/how-to-clear-netbeans-output-with-code
 * https://stackoverflow.com/questions/19870467/how-do-i-get-press-any-key-to-continue-to-work-in-my-java-code/25095049
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Clinica {
    private GestionDeUsuarios gestionDeUsuarios;
    private GestionDePruebas gestionDePruebas;
    private GestionDeVacunacion gestionDeVacunacion;
    private BufferedReader buffer;

    public Clinica() {
        gestionDeUsuarios = new GestionDeUsuarios();
        gestionDePruebas = new GestionDePruebas();
        gestionDeVacunacion = new GestionDeVacunacion();
        buffer = new BufferedReader(new InputStreamReader(System.in));
    }

    private void refrescar() {
        System.out.println('\u000C');
    }

    private void mensajeContinuar() {
        System.out.println("Pulse ENTER para continuar...");
        try {
            System.in.read();
        } catch (IOException e) {
            mensajeError();
            mensajeContinuar();
        }
    }

    private void mensajeError() {
        refrescar();
        System.out.println("{!} Ha introducido un caracter inválido o los datos son ilógicos");
        mensajeContinuar();
    }

    private int getMenuPersona(Persona persona) {
        if (persona instanceof Administrador) {
            return 1;
        } else if (persona instanceof Enfermero) {
            return 2;
        } else if (persona instanceof Tecnico) {
            return 3;
        } else {
            return 0;
        }
    }

    private Persona introduceID(String nombreMenu) {
        System.out.println("------------");
        System.out.println(nombreMenu);
        System.out.println("------------");
        try {
            Persona persona = gestionDeUsuarios.elegirPersona();
            return persona;
        } catch (NullPointerException e) {
            System.out.println("No existe una persona con el ID proporcionado");
            mensajeContinuar();
            menuPrincipal();
        }
        return null;
    }

    public void menuPrincipal() {
        refrescar();
        System.out.println("CLINICA DE COVID19");
        System.out.println("------------------");
        System.out.println("(1)-Loguearse si ya está registrado");
        System.out.println("(2)-Acceder como nuevo usuario");
        System.out.println("(3)-Salir");
        System.out.println("\nElija una opción y pulse Enter");
        try {
            switch (Integer.parseInt(buffer.readLine())) {
                case 1:
                    refrescar();
                    gestionDeUsuarios.listarPersonas();
                    Persona persona = introduceID("Introduzca su ID");
                    int menu = getMenuPersona(persona);
                    loginPersona(menu, persona);
                    break;
                case 2:
                    refrescar();
                    gestionDeUsuarios.altaPersona();
                    menuPrincipal();
                    break;
                case 3:
                    System.exit(1);
            }
        } catch (IOException | NumberFormatException e) {
            mensajeError();
            menuPrincipal();
        }
    }

    private void loginPersona(int menu, Persona persona) {
        refrescar();
        switch (menu) {
            case 1:
                menuAdministrador();
                break;
            case 2:
                Enfermero enfermero = (Enfermero) persona;
                menuEnfermero(enfermero);
                break;
            case 3:
                Tecnico tecnico = (Tecnico) persona;
                menuTenico(tecnico);
            default:
                System.out.println("No hay registros de ese tipo de datos");
                mensajeContinuar();
                menuPrincipal();
        }
    }

    public void menuAdministrador() {
        refrescar();
        System.out.println("Acceso Administrador");
        System.out.println("------------------");
        System.out.println("(1)-Gestión de usuarios");
        System.out.println("(2)-Asignar realización de pruebas y vacunas a un enfermero");
        System.out.println("(3)-Visualizar personal registrado");
        System.out.println("(4)-Visualizar pacientes y sus asignaciones");
        System.out.println("(5)-Visualizar pacientes confinados (En construcción)");
        System.out.println("(6)-Gestión de pruebas Serológicas tras confinamiento (En construcción)");
        System.out.println("(7)-Actualizar el stock de las vacunas");
        System.out.println("(8)-Volver");
        System.out.println("Elija una opción y pulse Enter");
        try {
            switch (Integer.parseInt(buffer.readLine())) {
                case 1:
                    menuGestionUsuarios();
                    break;
                case 2:
                    asignarPruebaVacuna();  
                    break;
                case 3:
                    refrescar();
                    gestionDeUsuarios.listarPersonal();
                    mensajeContinuar();
                    menuAdministrador();
                    break;
                case 4:
                    refrescar();
                    gestionDeVacunacion.mostrarAsignacionesVacunas();
                    mensajeContinuar();
                    menuAdministrador();
                    break;
                case 5:

                    break;
                case 6:

                    break;
                case 7:
                    actualizarStock();
                    break;
                case 8:
                    menuPrincipal();
                default:
                    System.out.println("Ha seleccionado una opción inválida");
                    mensajeContinuar();
                    menuAdministrador();
            }
        } catch (IOException | NumberFormatException e) {
            mensajeError();
            menuAdministrador();
        }
    }

    public void menuGestionUsuarios() {
        refrescar();
        System.out.println("Gestión de usuarios");
        System.out.println("------------------");
        System.out.println("(1)-Alta de nueva persona");
        System.out.println("(2)-Baja de una persona ya existente");
        System.out.println("(3)-Modificación de una persona ya existente");
        System.out.println("(4)-Volver");
        System.out.println("Elija una opción y pulse Enter");
        try {
            switch (Integer.parseInt(buffer.readLine())) {
                case 1:
                    refrescar();
                    gestionDeUsuarios.altaPersona();
                    break;
                case 2:
                    refrescar();
                    gestionDeUsuarios.listarPersonas();
                    Persona baja = introduceID("Introduzca el ID de la persona a borrar");
                    gestionDeUsuarios.bajaPersona(baja);
                    break;
                case 3:
                    refrescar();
                    gestionDeUsuarios.listarPersonas();
                    Persona modificada = introduceID("Introduzca el ID de la persona a modificar");
                    gestionDeUsuarios.modificarPersona(modificada);
                    break;
                case 4:
                    menuAdministrador();
            }
        } catch (IOException | NumberFormatException e) {
            mensajeError();
            menuGestionUsuarios();
        }
        menuPrincipal();
    }

    public void asignarPruebaVacuna() {
        refrescar();
        System.out.println("Asignar pruebas/vacunas");
        System.out.println("------------------");
        System.out.println("(1)-Asignar una prueba");
        System.out.println("(2)-Asignar una vacuna");
        System.out.println("(3)-Volver");
        System.out.println("Elija una opción y pulse Enter");
        try {
            switch (Integer.parseInt(buffer.readLine())) {
                case 1:
                    menuPrueba();
                    break;
                case 2:
                    menuVacuna();
                    break;
                case 3:
                    menuAdministrador();
                    break;
            }
        } catch (NumberFormatException | IOException e) {
            mensajeError();
            asignarPruebaVacuna();
        }
    }

    public void menuVacuna() {
        refrescar();
        gestionDeVacunacion.listarPacientesPendientesVacunar();
        try {
            Paciente paciente = (Paciente) introduceID("Escriba el ID del paciente: ");
            if (paciente instanceof Paciente) {
                if (paciente.seHaVacunado()) {
                    System.out.println("El paciente " + paciente.getNombre() + " ya ha sido vacunado con "
                            + paciente.getVacuna().getClass().getName());
                    Enfermero enfermeroAsignado = gestionDeVacunacion.getEnfermeroAsignadoAVacuna(paciente);
                    System.out.println("El enfermero que va a suministrársela es " + enfermeroAsignado.getNombre() + " "
                            + enfermeroAsignado.getApellido());
                    VacunaCovid19 vacunaAsignada = paciente.getVacuna();
                    gestionDeVacunacion.realizarVacunacion(paciente, enfermeroAsignado, vacunaAsignada);
                } else {
                    System.out.println("Enfermeros disponibles");
                    System.out.println("-----------------------");
                    gestionDeUsuarios.listarEnfermeros();
                    Enfermero enfermero = (Enfermero) introduceID("Indique el ID del enfermero a asignar");
                    VacunaCovid19 vacuna = enfermero.elegirVacuna();
                    gestionDeVacunacion.realizarVacunacion(paciente, enfermero, vacuna);
                }
            } else {
                System.out.println("No existe un paciente con el ID indicado");
                mensajeContinuar();
                menuVacuna();
            }
        } catch (ClassCastException | NullPointerException e) {
            mensajeError();
            menuAdministrador();
        }
        mensajeContinuar();
        asignarPruebaVacuna();
    }

    public void menuPrueba() {
        refrescar();
        gestionDePruebas.listarPacientes();
        try {
            Paciente paciente = (Paciente) introduceID("Escriba el ID del paciente: ");
            if (paciente instanceof Paciente) {
                gestionDePruebas.listarEnfermerosQuePuedenTrabajar();
                Enfermero enfermero = (Enfermero) introduceID("Indique el ID del enfermero a asignar");
                if (enfermero instanceof Enfermero) {
                    gestionDePruebas.realizarPrueba(paciente, enfermero);
                } else {
                    System.out.println("No existe un enfermero con el ID indicado");
                    mensajeContinuar();
                    asignarPruebaVacuna();
                }
            } else {
                System.out.println("No existe un paciente con el ID indicado");
                mensajeContinuar();
                asignarPruebaVacuna();
            }
        } catch (ClassCastException | NullPointerException e) {
            mensajeError();;
            asignarPruebaVacuna();
        }
        mensajeContinuar();
        asignarPruebaVacuna();
    }

    public void asignarDiagnostico() {
        refrescar();
        gestionDePruebas.listarPacientesConPruebas();
        try {
            Paciente paciente = (Paciente) introduceID("Escriba el ID del paciente: ");
            if (paciente instanceof Paciente) {
                Tecnico tecnico = (Tecnico) introduceID(
                        "Indique el ID del técnico que quiere que realice el diagnóstico");
                if (tecnico instanceof Tecnico) {
                    if (paciente.seHaRealizadoUnaPrueba()) {
                        gestionDePruebas.realizarDiagnostico(paciente, tecnico);
                    } else {
                        System.out.println("El paciente indicado no se ha realizado aún ninguna prueba");
                        mensajeContinuar();
                        menuAdministrador();
                    }
                } else {
                    System.out.println("El ID proporcionado no pertenece a un técnico");
                    mensajeContinuar();
                    menuAdministrador();
                }
            } else {
                System.out.println("No existe un paciente con el ID indicado");
                mensajeContinuar();
                asignarPruebaVacuna();
            }
        } catch (ClassCastException | NullPointerException e) {
            mensajeError();
            menuAdministrador();
        }
    }

    public void listarPacientesConfinados() {

    }

    public void menuPruebaSerologica() {

    }

    public void actualizarStock() {
        refrescar();
        System.out.println(gestionDeVacunacion.getListaDePruebasVacunas().toString());
        System.out.println("\nElija la vacuna/prueba que desea actualizar su stock");
        try {
            int op = Integer.parseInt(buffer.readLine());
            System.out.println("Escriba el nuevo stock constará en el sistema");
            Integer numero = Integer.parseInt(buffer.readLine());
            if (op >= 1 && op <= 6) {
                FechasPruebaVacuna pruebaVacuna = gestionDeVacunacion.elegirPruebaVacuna(op);
                gestionDeVacunacion.actualizarStock(pruebaVacuna, numero);
                System.out.println("Stock actualizado");
                mensajeContinuar();
                menuAdministrador();
            } else {
                System.out.println("Ha elegido una vacuna/prueba que no existe");
                mensajeContinuar();
                menuAdministrador();
            }
        } catch (IOException | NumberFormatException e) {
            mensajeError();
            menuAdministrador();
        }
    }

    public void menuEnfermero(Enfermero enfermero) {
        refrescar();
        System.out.println("Acceso Enfermero");
        System.out.println("------------------");
        System.out.println("(1)-Viasulizar datos de los pacientes asignados");
        System.out.println("(2)-Registro y actualización de los resultados de las pruebas y vacunas");
        System.out.println("(3)-Volver");
        System.out.println("Elija una opción y pulse Enter");
        try {
            switch (Integer.parseInt(buffer.readLine())) {
                case 1:
                    gestionDeVacunacion.listarPacientesAsignados(enfermero);
                    break;
                case 2:
                    asignarPruebaVacuna();
                    break;
                case 3:
                    menuPrincipal();
            }
        } catch (IOException | NumberFormatException e) {
            mensajeError();
            menuEnfermero(enfermero);
        }
        menuPrincipal();
    }

    public void menuTenico(Tecnico tecnico) {
        refrescar();
        System.out.println("Acceso Ténico");
        System.out.println("------------------");
        System.out.println("(1)-Viasulizar datos de los pacientes asignados");
        System.out.println("(2)-Registro y actualización de los resultados de las pruebas");
        System.out.println("(3)-Volver");
        System.out.println("Elija una opción y pulse Enter");
        try {
            switch (Integer.parseInt(buffer.readLine())) {
                case 1:
                    refrescar();
                    gestionDePruebas.listarPacientesAsignados(tecnico);
                    break;
                case 2:
                    asignarDiagnostico();
                    break;
                case 3:
                    menuPrincipal();
            }
        } catch (IOException | NumberFormatException e) {
            mensajeError();
            menuTenico(tecnico);
        }
        menuPrincipal();
    }

    public static void main(String args[]) {
        Clinica clinicaCovid19 = new Clinica();
        new Gestor();
        clinicaCovid19.menuPrincipal();
    }
}