/**
 * Write a description of class GestionVacunacion here.
 * 
 * Referencias:
 * 
 * https://www.discoduroderoer.es/formas-de-recorrer-un-hashmap-en-java/
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class GestionDeVacunacion extends Gestor {

    public GestionDeVacunacion() {
        super();
    }

    public boolean esPrioritario(Paciente paciente) {
        if (paciente.getEdad() > EDAD_PRIORITARIA) {
            return true;
        } else {
            return false;
        }
    }

    public void asignarGrupoDePrioridad(){
        for (Persona persona : listaDePersonas) {
            if(persona instanceof Paciente){
                Paciente paciente = (Paciente) persona;
                if(esPrioritario(paciente)){
                    pacientesPrimerGrupo.add(paciente);
                }else{
                    pacientesSegundoGrupo.add(paciente);
                }
            }
        }
    }

    public void comprobarPautaCompleta(Paciente paciente) {
        if (!paciente.faltaSegundaDosis()) {
            if (esPrioritario(paciente)) {
                pacientesPrimerGrupo.remove(paciente);
            } else {
                pacientesSegundoGrupo.remove(paciente);
            }
            System.out.println("El paciente acaba de tener la pauta completa de la vacuna");
        }
    }

    public void vacunar(Paciente paciente, Enfermero enfermero, VacunaCovid19 vacuna) {
        paciente.setVacuna(vacuna);
        pacientesVacunados.put(paciente, enfermero);
        System.out.println("Se ha vacunado al paciente con " + vacuna.getClass().getName());
        comprobarPautaCompleta(paciente);
    }

    public void realizarVacunacion(Paciente paciente, Enfermero enfermero, VacunaCovid19 vacuna) {
        asignarGrupoDePrioridad();
        if (hayStock(vacuna)) {
            if (restriccionSegundaDosis(vacuna) && paciente.seHaVacunado()) {
                System.out.println("No ha cumplido el periodo de espera para la segunda dosis");
                System.out.println("Fecha de vacunación: " + paciente.getVacuna().getFecha());
            } else {
                if (esPrioritario(paciente)) {
                    vacunar(paciente, enfermero, vacuna);
                } else {
                    if (pacientesPrimerGrupo.isEmpty()) {
                        vacunar(paciente,enfermero,vacuna);
                    }else{
                        System.out.println("Aún hay pacientes prioritarios en lista de espera");
                    }
                }
            }
        } else {
            System.out.println("No hay unidades para la vacuna " + vacuna.getClass().getName());
        }
    }

    public boolean restriccionSegundaDosis(VacunaCovid19 vacuna) {
        int diasDeEsperaVacuna = vacuna.getDiasEspera();
        if (vacuna.restriccionTemporal(diasDeEsperaVacuna)) {
            return true;
        } else {
            return false;
        }
    }

    public void listarPacientesPendientesVacunar(){
        for(Persona persona : listaDePersonas){
            if(persona instanceof Paciente){
                Paciente paciente = (Paciente)persona;
                if(!paciente.seHaVacunado() || paciente.faltaSegundaDosis()){
                    System.out.println(paciente.toString());
                    System.out.println("Número de vacunas aplicadas: " + paciente.getNumeroDeVacunas());
                }
            }
        }
    }

    public void listarPacientesAsignados(Enfermero enfermero) {
        for(Paciente paciente : pacientesVacunados.keySet()){
            if(pacientesVacunados.get(paciente).equals(enfermero)){
                System.out.println(paciente.toString());
            }
        }
    }

    public void mostrarAsignacionesVacunas() {
        for (Paciente paciente : pacientesVacunados.keySet()) {
            System.out.println("===============================");
            System.out.println(paciente.toString());
            System.out.println("Vacunado con: " + paciente.getVacuna().getClass().getName());
            System.out.println("Fecha de vacunación: " + paciente.getVacuna().getFecha());
            System.out.println("Número de dosis proporcionadas: " + paciente.getNumeroDeVacunas());
            if (paciente.pautaCompleta()) {
                System.out.println("Paciente con pauta completa");
            }
            System.out.println("\n       Asignado a       ");
            System.out.println(pacientesVacunados.get(paciente).toString());
        }
    }
}
