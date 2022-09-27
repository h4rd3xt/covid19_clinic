import java.time.LocalDate;

/**
 * Abstract class Fechas - write a description of the class here
 *
 * @author (your name here)
 * @version (version number or date here)
 */
public abstract class FechasPruebaVacuna{
    private LocalDate fecha;
    protected int diasEspera;
    protected int stock;

    public void setFecha(LocalDate fecha){
        this.fecha = fecha;
    }
    
    public LocalDate getFecha(){
        return fecha;
    }
    
    public int getDiasEspera(){
        return diasEspera;
    }

    public void setStock(int stock){
        this.stock = stock;
    }

    public int getStock(){
        return stock;
    }
    
    public boolean restriccionTemporal(int diasEspera){
        if(LocalDate.now().isAfter(fecha.plusDays(diasEspera)) ){
            return false;
        }
        return true;
    }

    @Override
    public String toString(){
        return "\nDías de espera: " + diasEspera +
        "\nStock actual: " + stock;
    }
}
