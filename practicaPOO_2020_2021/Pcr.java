import java.time.LocalDate;

/**
 * Write a description of class Pcr here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Pcr extends PruebaClasica{
    private static final int DIAS_ESPERA = 15;
    
    public Pcr(){
        super.setFecha(LocalDate.now());
        super.diasEspera = DIAS_ESPERA;
    }

    @Override
    public String toString(){
        return "\n\n(5)-PCR" + 
        super.toString();
    }
}
