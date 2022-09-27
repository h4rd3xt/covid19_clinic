
/**
 * Write a description of class Enfermero here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Enfermero extends Empleado{
    private static final int MAX_PRUEBAS_SEMANA = 5;
    
    /**
     * Constructor for objects of class Enfermero
     */
    public Enfermero(String nombre, String apellidos, int edad){
        super(nombre, apellidos, edad);
        super.maxPruebasSemana = MAX_PRUEBAS_SEMANA;
    }

    public VacunaCovid19 elegirVacuna(){
        int eleccion = VacunaCovid19.generarVacuna();
        switch(eleccion){
            case 1:
            return new Moderna();
            case 2:
            return new Pfizer();
            case 3:
            return new Johnson();
        }
        return null;
    }
    
    @Override
    public String toString(){
        return "\nRol: Enfermero/a"+
        super.toString();
    }
}
