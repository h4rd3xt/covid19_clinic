import java.time.LocalDate;

/**
 * Referencias:
 * http://puntocomnoesunlenguaje.blogspot.com/2012/07/generacion-de-numeros-aleatorios-en-java.html
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Serologica extends PruebaCovid19{
    private static final int DIAS_ESPERA = 183;
    
    public Serologica(){
        super.setFecha(LocalDate.now());
        super.diasEspera = DIAS_ESPERA;
    }
    
    @Override
    public boolean resultadoDeLaPrueba(){
        if(valorDeAnticuerpos() > 2){
            return true;
        }else{
            return false;
        }
    }
    
    public int valorDeAnticuerpos(){
        return (int)(Math.random()*(10-0+1)+0);
    }

    @Override
    public String toString(){
        return "\n\n(6)-Serológica" + 
        super.toString();
    }
}
