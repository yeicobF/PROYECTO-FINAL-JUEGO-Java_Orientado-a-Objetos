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
    private final long tiempoCambioTamaño = 0;//El tiempo con el que cambie de tamaño la explosión.
    private long tiempoDiferencia; //Es el tiempo que tendrá que pasar para que cambie de tamaño la explosión.
    boolean crecio, decrecio;
    public Explosion(){
        setImage("Explosion.png");
        imagen = new Imagen(getImage());
        divisorImagen = 0; //Hacer que la imagen aparezca pequeña para que vaya creciendo.
        tiempoDiferencia = System.currentTimeMillis();
        //System.out.println(" - EXPLOSIÓN - ");
        /*Aquí se hará la imagen lo más pequeña posible.*/
        setImage(imagen.modificaImagenAnchoAltoMin(1, 1)); //Se pondrá la imagen con el tamaño mínimo.
        crecio = decrecio = false; //Inicializar para las condiciones de imagen.
    }

    public void act(){
        if(animarExplosion())
            getWorld().removeObject(this);
           
        // else //if( ! Imagen.isEscalaModificable(getImage(), divisorImagen, 1))
            // getWorld().removeObject(this);
    }
    /*Método que hará que la explosión empiece siendo pequeña, crezca y luego se vuelva a hacer pequeña.*/
    private boolean animarExplosion(){
        //public static boolean isEscalaModificable(GreenfootImage imagen, int divisor, int multiplicacion)
            /* Si la imagen se puede modificar (sus dimensiones != 0), entonces modficarla.*/
        if(System.currentTimeMillis() - tiempoDiferencia >= tiempoCambioTamaño && (!crecio || !decrecio)){
            /*(!crecio || !decrecio) <- Dejará de hacerlo cuando ya haya crecido y decrecido*/
          //Ir haciendo crecer la imagen poco a poco.
          tiempoDiferencia = System.currentTimeMillis();
          /*Si la imagen aún no crece.*/
          if(divisorImagen < imagen.getDivisorImagenMaximo() && !crecio){//Esto es para cuando la imagen va de lo más grande a lo más pequeña.    
            divisorImagen ++; //Al disminuir el multiplicador, se regresa a su tamaño original.
            if(divisorImagen == imagen.getDivisorImagenMaximo())
                crecio = true; //Indicar que ya creció el sprite.
          }
          /*Mientras la imagen ya haya crecido y no decrecido.
                Mayor a 2, ya que al disminuir por última vez valdrá 1 y será divisible.*/
          if(divisorImagen > 2 && crecio && !decrecio){ //Para voler a hacerse pequeña
                divisorImagen --; //Al aumentar el divisor, se regresa al tamaño más pequeño
                if(divisorImagen == 1)
                    decrecio = true; //Indicar que ya decreció la imagen.
          }
          setImage(imagen.modificaImagenAnchoAltoMin(1, divisorImagen));
        }
        return crecio && decrecio; //Devuelve true si terminó su proceso.
    }
}
