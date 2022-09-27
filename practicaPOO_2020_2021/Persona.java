import java.io.Serializable;

/**
 * Referencias: 
 * https://es.wikipedia.org/wiki/HashCode()_(Java)
 * https://www.arquitecturajava.com/comparando-java-vs-equals/
 *
 * @author (your name here)
 * @version (version number or date here)
 */
public abstract class Persona implements Serializable{
    private String nombre;
    private String apellido;
    private int edad;
    private int idPersona;
    
    public Persona(String nombre, String apellido, int edad){
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        idPersona = hashCode();
    }
    
    public int getIdPersona(){
        return idPersona;
    }
    
    public String getNombre(){
        return nombre;
    }
    
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    
    public String getApellido(){
        return apellido;
    }
    
    public void setApellido(String apellido){
        this.apellido = apellido;
    }
    
    public int getEdad(){
        return edad;
    }
    
    public void setEdad(int edad){
        this.edad = edad;
    }
    
    @Override
    public boolean equals(Object p){
        Persona persona = (Persona)p;
        if(persona.getIdPersona() == idPersona){
            return true;
        }
        return false;
    }
    
    @Override
    public int hashCode(){
        int hash = 1;
        hash = (hash * 17) + nombre.hashCode();
        hash = (hash * 31) + apellido.hashCode();
        return Math.abs(hash);
    }
    
    @Override
    public String toString(){
        return "\nID: "+idPersona+"\nNombre: "+nombre+
        "\nApellido: "+apellido +
        "\nEdad: "+edad;
    }
}
