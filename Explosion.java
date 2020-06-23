import greenfoot.GreenfootImage;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import greenfoot.Actor;
import greenfoot.GreenfootSound; //Para el sonido de las explosiones.
/**
 * Write a description of class Explosion here.
 *
 * @author (Jacob)
 * @version (Lunes, 22 de junio de 2020)
 */
public class Explosion extends Actor
{
    /**
     * Act - do whatever the Explosion wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private GreenfootSound sonidoExplosion; //Los sonidos de explosión.
    private Imagen imagen; //Para modificar el ancho y el alto.
    private int modificadorImagen; //Para cambiar el tamaño de la imagen.
    private final long tiempoCambioTamaño = 15;//El tiempo con el que cambie de tamaño la explosión.
    private long tiempoDiferencia; //Es el tiempo que tendrá que pasar para que cambie de tamaño la explosión.
    boolean crecio, decrecio; //Booleano que indicará si ya creció y decreció a explosión.
    /*Recibir la imagen del objeto que explotó para que la explosión mida el mismo tamaño.*/
    public Explosion(GreenfootImage imagenObjeto){
        setImage("Explosion.png");
        sonidoExplosion = new GreenfootSound("explosion"+ Aleatorio.getNumeroAleatorio(1, 7) +".mp3");
        sonidoExplosion.play();
        /*Reescalar la imagen al tamaño del objeto que explotó.
         * El tamaño de la explosión será equivalente al tamaño del objeto que explotó, pero medirá
            1/2 tamaño más que la original para que quede un poco más grande la explosión.*/
        getImage().scale(imagenObjeto.getWidth()*3/2, imagenObjeto.getHeight()*3/2);
        imagen = new Imagen(getImage());
        modificadorImagen = 0; //Hacer que la imagen aparezca pequeña para que vaya creciendo.
        tiempoDiferencia = System.currentTimeMillis();
        //System.out.println(" - EXPLOSIÓN - ");
        /*Aquí se hará la imagen lo más pequeña posible.*/
        setImage(imagen.modificaImagenAnchoAltoMin(getImage(), 1, 1)); //Se pondrá la imagen con el tamaño mínimo.
        crecio = decrecio = false; //Inicializar para las condiciones de imagen.
    }

    public void act(){
        if(animarExplosion())
            getWorld().removeObject(this);
    }
    /*Método que hará que la explosión empiece siendo pequeña, crezca y luego se vuelva a hacer pequeña.*/
    private boolean animarExplosion(){
        //public static boolean isEscalaModificable(GreenfootImage imagen, int divisor, int multiplicacion)
            /* Si la imagen se puede modificar (sus dimensiones != 0), entonces modficarla.*/
        turn(Aleatorio.getNumeroAleatorio(1, 360));//Un sutil giro.
        if(System.currentTimeMillis() - tiempoDiferencia >= tiempoCambioTamaño && (!crecio || !decrecio)){
            /*(!crecio || !decrecio) <- Dejará de hacerlo cuando ya haya crecido y decrecido*/
          //Ir haciendo crecer la imagen poco a poco.
          tiempoDiferencia = System.currentTimeMillis();
          /*Si la imagen aún no crece.*/
          if(modificadorImagen < imagen.getDivisorImagenMaximo() && !crecio){//Esto es para cuando la imagen va de lo más grande a lo más pequeña.    
            modificadorImagen ++; //Al disminuir el multiplicador, se regresa a su tamaño original.
            if(modificadorImagen == imagen.getDivisorImagenMaximo())
                crecio = true; //Indicar que ya creció el sprite.
          }
          /*Mientras la imagen ya haya crecido y no decrecido.
                Mayor a 2, ya que al disminuir por última vez valdrá 1 y será divisible.*/
          if(modificadorImagen > 1 && crecio && !decrecio){ //Para voler a hacerse pequeña
                modificadorImagen --; //Al aumentar el divisor, se regresa al tamaño más pequeño
                if(modificadorImagen == 1) //Cuando el multiplicador sea = 1, entonces decir que ya decreció. 
                    decrecio = true; //Indicar que ya decreció la imagen.
          }
          /*Es necesario el setImage antes de reescalar la imagen porque leí que no es recomendable
                reescalar imagenes ya reescaladas. Esto porque había un problema que mostraba un cuadro
                blanco en lugar de la imagen.*/
          setImage("Explosion.png");
          setImage(imagen.modificaImagenAnchoAltoMin(getImage(), 1, modificadorImagen));
        }
        //System.out.println("Crecio :"+ crecio +", Decrecio: "+ decrecio);
        return crecio && decrecio; //Devuelve true si terminó su proceso.
    }
}
