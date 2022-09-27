import java.util.HashMap;
import java.util.HashSet;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Write a description of class Gestor here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Gestor {
    protected static HashSet<Persona> listaDePersonas;
    protected static ArrayList<Paciente> pacientesPrimerGrupo;
    protected static ArrayList<Paciente> pacientesSegundoGrupo;
    protected static ArrayList<FechasPruebaVacuna> listaDePruebasVacunas;
    protected static HashMap<Paciente, Enfermero> pacientesVacunados;
    protected static HashMap<Paciente, Enfermero> asignacionDePruebas;
    protected static HashMap<Paciente, Tecnico> asignacionDeDiagnosticos;
    protected BufferedReader buffer;
    protected static final int EDAD_PRIORITARIA = 65;

    public Gestor() {
        listaDePersonas = new HashSet<Persona>();
        pacientesPrimerGrupo = new ArrayList<Paciente>();
        pacientesSegundoGrupo = new ArrayList<Paciente>();
        listaDePruebasVacunas = new ArrayList<FechasPruebaVacuna>();
        pacientesVacunados = new HashMap<Paciente, Enfermero>();
        asignacionDePruebas = new HashMap<Paciente, Enfermero>();
        asignacionDeDiagnosticos = new HashMap<Paciente, Tecnico>();
        buffer = new BufferedReader(new InputStreamReader(System.in));
        
        VacunaCovid19 v1 = new Moderna();
        VacunaCovid19 v2 = new Pfizer();
        VacunaCovid19 v3 = new Johnson();
        PruebaCovid19 pr1 = new Antigenos();
        PruebaCovid19 pr2 = new Pcr();
        PruebaCovid19 pr3 = new Serologica();
        
        v1.setStock(1);
        v2.setStock(0);
        v3.setStock(2);
        pr1.setStock(5);
        pr2.setStock(2);
        pr3.setStock(0);

        listaDePruebasVacunas.add(v1);
        listaDePruebasVacunas.add(v2);
        listaDePruebasVacunas.add(v3);
        listaDePruebasVacunas.add(pr1);
        listaDePruebasVacunas.add(pr2);
        listaDePruebasVacunas.add(pr3);

        Administrador ad = new Administrador("J", "M", 32);
        Paciente pe1 = new Paciente("D", "Y", 70);
        Paciente pe2 = new Paciente("R", "T", 35);
        Paciente pe3 = new Paciente("G", "K", 67);
        Paciente pe4 = new Paciente("P", "O", 60);
        Enfermero e1 = new Enfermero("R", "Z", 24);
        Enfermero e2 = new Enfermero("T", "I", 30);
        Enfermero e3 = new Enfermero("B", "F", 40);
        Tecnico t1 = new Tecnico("Z", "U", 39);
        Tecnico t2 = new Tecnico("D", "L", 55);

        listaDePersonas.add(ad);
        listaDePersonas.add(pe1);
        listaDePersonas.add(pe2);
        listaDePersonas.add(pe3);
        listaDePersonas.add(pe4);
        listaDePersonas.add(e1);
        listaDePersonas.add(e2);
        listaDePersonas.add(e3);
        listaDePersonas.add(t1);
        listaDePersonas.add(t2);

        v1.setFecha(LocalDate.of(2021, 7, 21));
        v2.setFecha(LocalDate.of(2021, 8, 27));
        pr2.setFecha(LocalDate.of(2021, 8, 18));
        
        pe2.setVacuna(v1); //Moderna a paciente de 35 años, superado el periodo de espera
        pe3.setVacuna(v2); //Pfizer a paciente de 67 años, tiene que esperar

        pe4.setPrueba(pr2); //Pcr a paciente de 60 años, tiene que esperar

        pacientesVacunados.put(pe2, e1);
        pacientesVacunados.put(pe3, e2);

        asignacionDePruebas.put(pe2, e1);

        e2.setContadorDePruebas(e2.maxPruebasSemana); //Enfermero de 30 años no puede realizar más pruebas
        t2.setContadorDePruebas(t2.maxPruebasSemana); // Técnico de de 55 años no puede realizar más diagnósticos
    }

    public FechasPruebaVacuna elegirPruebaVacuna(int op) {
        switch (op) {
            case 1:
                return new Moderna();
            case 2:
                return new Pfizer();
            case 3:
                return new Johnson();
            case 4:
                return new Antigenos();
            case 5:
                return new Pcr();
            case 6:
                return new Serologica();
            default:
                System.out.println("Elección incorrecta");
                return null;
        }
    }

    public boolean hayStock(FechasPruebaVacuna pruebaVacuna) {
        for(FechasPruebaVacuna pV : listaDePruebasVacunas){
            if(pV.getClass() == pruebaVacuna.getClass()){
                if (pV.getStock() > 0) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }

    public int sumarPrueba(Empleado persona) {
        if (persona instanceof Enfermero) {
            persona = (Enfermero) persona;
        } else if (persona instanceof Tecnico) {
            persona = (Tecnico) persona;
        } else {
            return 0;
        }
        int cantidadDePruebas = persona.getContadorDePruebas();
        cantidadDePruebas = cantidadDePruebas + 1;
        return cantidadDePruebas;
    }

    public Persona getPersona(int id) {
        for (Persona p : listaDePersonas) {
            if (p.getIdPersona() == id) {
                return p;
            }
        }
        return null;
    }

    public Persona elegirPersona() {
        try {
            int id = Integer.parseInt(buffer.readLine());
            Persona persona = getPersona(id);
            return persona;
        } catch (IOException e) {
            System.out.println("Error en la E/S de los datos");
        }
        return null;
    }

    public HashSet<Persona> getListaDePersonas() {
        return listaDePersonas;
    }

    public void setListaDePersonas(HashSet<Persona> personas) {
        listaDePersonas.addAll(personas);
    }

    public ArrayList<FechasPruebaVacuna> getListaDePruebasVacunas() {
        return listaDePruebasVacunas;
    }

    public ArrayList<FechasPruebaVacuna> getListaDePruebas() {
        ArrayList<FechasPruebaVacuna> listaDePruebas = new ArrayList<FechasPruebaVacuna>();
        for (FechasPruebaVacuna prueba : listaDePruebasVacunas) {
            if (prueba instanceof PruebaCovid19) {
                listaDePruebas.add(prueba);
            }
        }
        return listaDePruebas;
    }

    public void setListaDePruebasVacunas(ArrayList<FechasPruebaVacuna> lista) {
        listaDePruebasVacunas.addAll(lista);
    }

    public void actualizarStock(FechasPruebaVacuna pruebaVacuna, Integer numero) {
        for (FechasPruebaVacuna pV : listaDePruebasVacunas) {
            if (pV.getClass() == pruebaVacuna.getClass()) {
                pV.setStock(numero);
            }
        }
    }
    
    public void listarPacientes(){
        for (Persona persona : listaDePersonas){
            if(persona instanceof Paciente){
                System.out.println(persona.toString());
            }
        }
    }

    public ArrayList<Paciente> getPacientesPrimerGrupo() {
        return pacientesPrimerGrupo;
    }

    public void setPacientesPrimerGrupo(ArrayList<Paciente> pacientes) {
        pacientesPrimerGrupo.addAll(pacientes);
    }

    public void setPacientesVacunados(HashMap<Paciente, Enfermero> pacientes) {
        pacientesVacunados = pacientes;
    }

    public HashMap<Paciente, Enfermero> getPacientesVacunados() {
        return pacientesVacunados;
    }

    public void setPacientesConPrueba(HashMap<Paciente, Enfermero> pacientes) {
        asignacionDePruebas = pacientes;
    }

    public HashMap<Paciente, Enfermero> getPacientesConPrueba() {
        return asignacionDePruebas;
    }

    public void setDiagnosticosPrueba(HashMap<Paciente, Tecnico> pacientes) {
        asignacionDeDiagnosticos = pacientes;
    }

    public HashMap<Paciente, Tecnico> getDiagnosticosPrueba() {
        return asignacionDeDiagnosticos;
    }

    public ArrayList<Paciente> getPacientesSegundoGrupo() {
        return pacientesSegundoGrupo;
    }

    public void setPacientesSegundoGrupo(ArrayList<Paciente> pacientes) {
        pacientesSegundoGrupo.addAll(pacientes);
    }

    public Enfermero getEnfermeroAsignadoAVacuna(Paciente paciente) {
        return pacientesVacunados.get(paciente);
    }
}