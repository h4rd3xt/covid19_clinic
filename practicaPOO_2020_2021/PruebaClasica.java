import java.util.Random;

/**
 * Indican un resultado positivo o negativo en la enfermedad
 * 
 * Referencias:
 * https://www.tutorialspoint.com/generate-random-boolean-in-java
 *
 * @author (your name here)
 * @version (version number or date here)
 */
public abstract class PruebaClasica extends PruebaCovid19{
    private Random resultado;
    
    @Override
    public boolean resultadoDeLaPrueba(){
        resultado = new Random();
        return resultado.nextBoolean();
    } 
}
