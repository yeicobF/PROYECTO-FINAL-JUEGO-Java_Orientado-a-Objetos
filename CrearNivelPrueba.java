import greenfoot.*;
/**
 * Write a description of class CrearNivelPrueba here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CrearNivelPrueba  
{
    // instance variables - replace the example below with your own
    /**
     * Constructor for objects of class CrearNivelPrueba
     */
    public CrearNivelPrueba()
    {
        //Niveles nivel = new Niveles();
        NaveAliada.setDiseñoNaveAliada(3);
        NaveAliada.setTipoDisparo(3);
        Greenfoot.setWorld(new Niveles(1));
    }

}
