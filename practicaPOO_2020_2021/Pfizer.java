import java.time.LocalDate;

/**
 * Write a description of class Pfizer here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Pfizer extends VacunaCovid19{
    private static final int DIAS_ESPERA = 21;
    private static final int NUMERO_DOSIS = 2;
    
    public Pfizer(){
        super.setFecha(LocalDate.now());
        super.diasEspera = DIAS_ESPERA;
        super.numero_dosis = NUMERO_DOSIS;
    }
    
    @Override
    public String toString(){
        return "\n\n(2)-Pfizer" + 
        super.toString();
    }
}
