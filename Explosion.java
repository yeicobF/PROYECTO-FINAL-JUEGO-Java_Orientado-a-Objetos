import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Explosion here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Explosion extends Actor
{
    /**
     * Act - do whatever the Explosion wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
     int i;
    public Explosion(){
        setImage("Explosion.png");
        i = 1; //Hacer que la imagen aparezca pequeña para que vaya creciendo.
        Imagen.modificarEscalaImagen(getImage(), i, 1);
    }

    public void act(){
        if(i == 10) //Cuando el modificador de imagen sea 0, destruir.
          getWorld().removeObject(this);
        //Ir haciendo crecer la imagen poco a poco.
        Imagen.modificarEscalaImagen(getImage(), i, 1);
        i++;
    }
}
