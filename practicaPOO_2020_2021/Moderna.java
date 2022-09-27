import java.time.LocalDate;

/**
 * Write a description of class Moderna here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Moderna extends VacunaCovid19{
    private static final int DIAS_ESPERA = 21;
    private static final int NUMERO_DOSIS = 2;
    
    public Moderna(){
        super.setFecha(LocalDate.now());
        super.diasEspera = DIAS_ESPERA;
        super.numero_dosis = NUMERO_DOSIS;
    }

    @Override
    public String toString(){
        return "(1)-Moderna" + 
        "\nNúmero de dosis: " + numero_dosis +
        super.toString();
    }
}
