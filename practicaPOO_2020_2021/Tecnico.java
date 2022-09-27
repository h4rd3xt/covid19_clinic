
/**
 * Write a description of class Tecnico here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Tecnico extends Empleado{
    private static final int MAX_ANALISIS_SEMANA = 4;
    
    /**
     * Constructor for objects of class Tecnico
     */
    public Tecnico(String nombre, String apellidos, int edad){
        super(nombre, apellidos, edad);
        super.maxPruebasSemana = MAX_ANALISIS_SEMANA;
    }
    
    @Override
    public String toString(){
        return "\nRol: Técnico"+
        super.toString();
    }
}
