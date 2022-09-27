import java.io.IOException;

/**
 *
 * 
 * @author (your name)
 * @version (a version number or a date)
 */
public class GestionDePruebas extends Gestor {

    public GestionDePruebas() {
        super();
    }

    public void realizarPrueba(Paciente paciente, Enfermero enfermero) {
        if (enfermero.puedeTrabajar()) {
            listarPruebas();
            System.out.println("\nElija la prueba que va a utilizar");
            try {
                PruebaCovid19 prueba = (PruebaCovid19) elegirPruebaVacuna(Integer.parseInt(buffer.readLine()));
                if(prueba != null) {
                    if (hayStock(prueba)) {
                        paciente.setPrueba(prueba);
                        int contadorDePrueba = sumarPrueba(enfermero);
                        enfermero.setContadorDePruebas(contadorDePrueba);
                        System.out.println("Prueba asignada");
                    }else{
                        System.out.println("No hay stock de la prueba seleccionada");
                    }
                }
            } catch (NumberFormatException | IOException e) {
                System.out.println("Ha elegido una opción incorrecta");
            }
        } else {
            System.out.println("El enfermero asignado a la prueba ha realizado "
                + enfermero.mumeroDePruebasRealizadaEstaSemana() + " esta semana");
            System.out.println("No puede realizar más");
        }
    }

    public void realizarDiagnostico(Paciente paciente, Tecnico tecnico) {
        if (tecnico.puedeTrabajar()) {
            int cantidadDeDiagnosticos = sumarPrueba(tecnico);
            tecnico.setContadorDePruebas(cantidadDeDiagnosticos);
            asignacionDeDiagnosticos.put(paciente, tecnico);
            System.out.println("Prueba realizada: " + paciente.getPrueba().getClass().getName());
            System.out.println("Resultado del diagnostico: " + paciente.getPrueba().resultadoDeLaPrueba());
            asignacionDePruebas.remove(paciente);
        } else {
            System.out.println("El técnico asignado a la prueba ha realizado "
                + tecnico.mumeroDePruebasRealizadaEstaSemana() + " esta semana");
            System.out.println("No puede realizar más");
        }
    }

    public void listarPruebas() {
        for (FechasPruebaVacuna pruebaVacuna : listaDePruebasVacunas) {
            if (pruebaVacuna instanceof PruebaCovid19) {
                PruebaCovid19 prueba = (PruebaCovid19) pruebaVacuna;
                System.out.println(prueba.toString());
            }
        }
    }

    public void listarEnfermerosQuePuedenTrabajar() {
        for (Persona persona : listaDePersonas) {
            if (persona instanceof Enfermero) {
                Enfermero enfermero = (Enfermero) persona;
                if (enfermero.puedeTrabajar()) {
                    System.out.println(enfermero.toString());
                }
            }
        }
    }

    public void listarPacientesConPruebas() {
        for (Persona persona : listaDePersonas) {
            if (persona instanceof Paciente) {
                Paciente paciente = (Paciente) persona;
                if (paciente.seHaRealizadoUnaPrueba()) {
                    System.out.println(paciente.toString());
                    System.out.println("Prueba asignada: " + paciente.getPrueba().getClass().getName());
                }
            }
        }
    }

    public void listarPacientesAsignados(Tecnico tecnico) {
        for (Paciente paciente : asignacionDeDiagnosticos.keySet()) {
            if (asignacionDeDiagnosticos.get(paciente).equals(tecnico)) {
                System.out.println(paciente.toString());
            }
        }
    }
}
