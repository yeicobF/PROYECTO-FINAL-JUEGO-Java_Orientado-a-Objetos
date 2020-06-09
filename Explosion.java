import greenfoot.GreenfootImage;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import greenfoot.Actor;

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
    Imagen imagen;
    private int divisorImagen; //Para cambiar el tamaño de la imagen.
    private final long tiempoCambioTamaño = 1000;//El tiempo con el que cambie de tamaño la explosión.
    private long tiempoDiferencia; //Es el tiempo que tendrá que pasar para que cambie de tamaño la explosión.
    public Explosion(){
        setImage("Explosion.png");
        imagen = new Imagen(getImage());
        divisorImagen = imagen.getDivisorImagenMaximo(); //Hacer que la imagen aparezca pequeña para que vaya creciendo.
        tiempoDiferencia = System.currentTimeMillis();
        //System.out.println(" - EXPLOSIÓN - ");
        /*Aquí se hará la imagen lo más pequeña posible.*/
        Imagen.modificarEscalaImagen(getImage(), divisorImagen, 1);
    }

    public void act(){
        
           
        // else //if( ! Imagen.isEscalaModificable(getImage(), divisorImagen, 1))
            // getWorld().removeObject(this);
    }
    /*Método que hará que la explosión empiece siendo pequeña, crezca y luego se vuelva a hacer pequeña.*/
    private void animarExplosion(){
        //public static boolean isEscalaModificable(GreenfootImage imagen, int divisor, int multiplicacion)
            /* Si la imagen se puede modificar (sus dimensiones != 0), entonces modficarla.*/
        if(Imagen.isEscalaModificable(getImage(), divisorImagen, 1)) //Cuando el modificador de imagen sea 0, destruir.
            /*Pongo el primer if por separado para que sólo se destruya cuando no cumpla con la escala y no cuando
                el tiempo no cumpla su condición también.*/  
            if(System.currentTimeMillis() - tiempoDiferencia >= tiempoCambioTamaño){
                //Ir haciendo crecer la imagen poco a poco.
              tiempoDiferencia = System.currentTimeMillis();
              divisorImagen ++; //Aumentar el tamaño de la escala.
              Imagen.modificarEscalaImagen(getImage(), divisorImagen, 1);
            }
    }
}
