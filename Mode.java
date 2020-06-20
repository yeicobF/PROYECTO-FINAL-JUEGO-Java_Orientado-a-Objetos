import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Mode here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class Mode extends Menu
{
   private Boton Facil;
   private Boton Normal;
   private Boton Difícil;
   
   public Mode(){ 
       super(false);
       //eleccion nave  
   }
   public void act(){
       volverMenu();
   }
}

