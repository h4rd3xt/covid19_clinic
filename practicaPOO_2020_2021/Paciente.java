
/**
 * Write a description of class Paciente here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Paciente extends Persona implements Comparable<Paciente>{
    private PruebaCovid19 prueba;
    private VacunaCovid19 vacuna;
    private int numeroDeVacunas;
    private int numeroDePruebas;
    private boolean confinado;

    public Paciente(String nombre, String apellidos, int edad){
        super(nombre, apellidos, edad);
    }

    public void setPrueba(PruebaCovid19 prueba){
        this.prueba = prueba;
        numeroDePruebas = numeroDePruebas + 1;
    }

    public PruebaCovid19 getPrueba(){
        return prueba;
    }

    public void setVacuna(VacunaCovid19 vacuna){
        this.vacuna = vacuna;
        numeroDeVacunas = numeroDeVacunas + 1;
    }

    public VacunaCovid19 getVacuna(){
        return vacuna;
    }

    public int getNumeroDeVacunas(){
        return numeroDeVacunas;
    }
    
    public int getNumeroDePruebas(){
        return numeroDePruebas;
    }

    public void setConfinado(boolean confinado){
        this.confinado = confinado;
    }

    public boolean getConfinado(){
        return confinado;
    }
    
    public boolean seHaVacunado(){
        if(numeroDeVacunas > 0){
            return true;
        }else{
            return false;
        }
    }

    public boolean faltaSegundaDosis(){
        int numeroDeDosis = vacuna.getNumeroDeDosis();
        if(numeroDeVacunas < numeroDeDosis){
            return  true;
        }else {
            return false;
        }
    }

    public boolean pautaCompleta(){
        int numeroDeDosis = vacuna.getNumeroDeDosis();
        if(numeroDeVacunas == numeroDeDosis){
            return  true;
        }else {
            return false;
        }
    }
    
    public boolean seHaRealizadoUnaPrueba(){
        if(numeroDePruebas > 0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public int compareTo(Paciente paciente){
        if(paciente.getEdad() > getEdad()){
            return 1;
        }else if(paciente.getEdad() < getEdad()){
            return -1;
        }else{
            return 0;
        }
    }

    @Override
    public String toString(){
        return "\nRol: Paciente"+
        super.toString();
    }
}
