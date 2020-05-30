import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Superclase que tendrá como subclases a cada uno de los niveles.
 * 
 * @author (Jacob) 
 * @version (Viernes, 29 de mayo de 2020)
 */
public class Niveles extends World
{

    /**
     * Constructor for objects of class Niveles.
     * 
     */
    private static int nivelActual = 0;//Indica el nivel en que nos encontramos
    //Aquí utilizaremos el mostrarInfo(tipo = niveles) y los demás también podrían ser.
    public Niveles()
    {    
        
        super(600, 400, 1); 
    }
    //Método estático que devuelve el nivel actual
    public static int getNivelActual(){
        return nivelActual;
    }
}
