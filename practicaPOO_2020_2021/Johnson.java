import java.time.LocalDate;

/**
 * Write a description of class Johnson here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Johnson extends VacunaCovid19{
    private static final int DIAS_ESPERA = 0;
    private static final int NUMERO_DOSIS = 1;
    
    public Johnson(){
        super.setFecha(LocalDate.now());
        super.diasEspera = DIAS_ESPERA;
        super.numero_dosis = NUMERO_DOSIS;
    }

    @Override
    public String toString(){
        return "\n\n(3)-Johnson" + 
        super.toString();
    }
}
