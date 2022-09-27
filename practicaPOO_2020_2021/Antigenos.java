import java.time.LocalDate;

/**
 * Write a description of class Antigenos here.
 * 
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Antigenos extends PruebaClasica{
    private static final int DIAS_ESPERA = 0;
     
    public Antigenos(){
        super.setFecha(LocalDate.now());
        super.diasEspera = DIAS_ESPERA;
    }

    @Override
    public String toString(){
        return "\n\n(4)-Antígenos" + 
        super.toString();
    }
}
