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
        imagen.modificaImagenAnchoAltoMin(1, 1); //Se pondrá la imagen con el tamaño mínimo.
    }

    public void act(){
        // if(animarExplosion())
            // getWorld().removeObject(this);
           
        // else //if( ! Imagen.isEscalaModificable(getImage(), divisorImagen, 1))
            // getWorld().removeObject(this);
    }
    /*Método que hará que la explosión empiece siendo pequeña, crezca y luego se vuelva a hacer pequeña.*/
    private boolean animarExplosion(){
        boolean fin = false;
        //public static boolean isEscalaModificable(GreenfootImage imagen, int divisor, int multiplicacion)
            /* Si la imagen se puede modificar (sus dimensiones != 0), entonces modficarla.*/
        if(System.currentTimeMillis() - tiempoDiferencia >= tiempoCambioTamaño){
          //Ir haciendo crecer la imagen poco a poco.
          tiempoDiferencia = System.currentTimeMillis();
          if(divisorImagen > 1){//Esto es para cuando la imagen va de lo más grande a lo más pequeña.    
            divisorImagen --; //Al disminuir el multiplicador, se regresa a su tamaño original.
            Imagen.modificarEscalaImagen(getImage(), 1, divisorImagen); //Aquí se multiplica hasta llegar al tamaño original.
          }
          if(divisorImagen < imagen.getDivisorImagenMaximo()){ //Para voler a hacerse pequeña
              divisorImagen ++; //Al aumentar el divisor, se regresa al tamaño más pequeño
              Imagen.modificarEscalaImagen(getImage(), divisorImagen, 1); //Aquí se multiplica hasta llegar al tamaño original.
          }
          fin = true;; //Terminar la animación después de cumplir con lo anterior para luego destruir la explosión.
        }
        return fin;
    }
}
