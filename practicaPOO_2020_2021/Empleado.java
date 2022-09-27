import java.time.DayOfWeek;
import java.time.LocalDate;

/**
 * Abstract class Empleado - write a description of the class here
 * 
 * Referencias:
 * https://stackoverflow.com/questions/28450720/get-date-of-first-day-of-week-based-on-localdate-now-in-java-8
 *
 * @author (your name here)
 * @version (version number or date here)
 */
public abstract class Empleado extends Persona{
    protected int maxPruebasSemana;
    protected int contadorDePruebas;
    
    public Empleado(String nombre, String apellidos, int edad){
        super(nombre, apellidos, edad);
    }
    
    public void setContadorDePruebas(int contadorDePruebas){
        this.contadorDePruebas = contadorDePruebas;
    }

    public int getContadorDePruebas(){
        return contadorDePruebas;
    }
    
    public int mumeroDePruebasRealizadaEstaSemana(){
        return contadorDePruebas;
    }
    
    public boolean puedeTrabajar(){
        LocalDate hoy = LocalDate.now();
        LocalDate lunes = hoy.with(DayOfWeek.MONDAY);
        if(hoy == lunes){
            contadorDePruebas = 0;
        }
        if(contadorDePruebas < maxPruebasSemana){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString(){
        return "\nRol: Empleado"+
        super.toString();
    }
}