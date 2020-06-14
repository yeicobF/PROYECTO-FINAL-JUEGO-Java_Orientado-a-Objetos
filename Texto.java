import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Texto here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Texto extends Actor
{
    /**
     * Act - do whatever the Texto wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Texto(int tipo)
    {
        setImage("Intro"+ tipo +".png");
    }
    public void actualizar(int tipo) 
    {
        setImage("Intro"+ tipo +".png");
    }    
}
