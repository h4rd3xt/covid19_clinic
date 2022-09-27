import java.io.Serializable;

/**
 * Enumeration class Prueba - write a description of the enum class here
 *
 * @author (your name here)
 * @version (version number or date here)
 */
public abstract class VacunaCovid19 extends FechasPruebaVacuna implements Serializable{
    protected int numero_dosis;

    public VacunaCovid19(){
        super();
    }

    public int getNumeroDeDosis(){
        return numero_dosis;
    }
    
    public static int generarVacuna(){
        return (int)(Math.random()*(3-1+1)+1);
    }
}
