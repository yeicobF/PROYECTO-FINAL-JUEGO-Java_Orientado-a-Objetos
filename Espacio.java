import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Write a description of class Espacio here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Espacio extends World
{

    /**
     * Constructor for objects of class Espacio.
     * 
     */
    public Espacio()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 600, 1); 
        nave n1=new nave();
        addObject(n1,50,300);
        crearRocas(15);
    }
    public void crearRocas(int numero)
    {
        for(int i=0;i<numero;i++)
        {
            roca r= new roca();
            int y = Greenfoot.getRandomNumber(getWidth());
            int x = Greenfoot.getRandomNumber(getHeight());
            addObject(r,x,y);
        }
    }
}
