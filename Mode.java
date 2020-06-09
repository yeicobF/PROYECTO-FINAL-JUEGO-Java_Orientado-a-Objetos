import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Mode here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class Mode extends World
{
   private Actor Easy;
   private Actor Normal;
   private Actor Hard;
   private Actor exit;
   private Actor a1;
    public Mode()
    { 
        super(800, 600, 1);
        setBackground("images/espacio5.jpg");
       //eleccion nave
       a1 = Boton.creaBoton(this, "Regresar al menu", (getWidth()/2) -202, (getHeight()*2/3)+103, Color.GRAY, null, null, 30);
       exit = Boton.creaBoton(this, "Regresar al menu", (getWidth()/2) -200, (getHeight()*2/3)+100, Color.WHITE, null, null, 30);    
    }
    public void act()
    {
        if(Greenfoot.mouseClicked(exit))
        {
             Portada world = new Portada();
             Greenfoot.setWorld(world);
        }
    }
}

