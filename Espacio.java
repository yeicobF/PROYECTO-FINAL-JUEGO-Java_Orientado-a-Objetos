import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Write a description of class Espacio here.
 *
 * @author (Team Naves)
 * @version (Martes, 2 de junio - Miércoles 3 de junio de 2020)
 */
public class Espacio extends Niveles
{
    /**
     * Constructor for objects of class Espacio.
     *
     */
    public Espacio(){
        addObject(nave, getWidth()/2-NaveAliada.getAnchoImagen()/2, getHeight()/2+NaveAliada.getAltoImagen()/2);//Aparecer a la nave en el centro
        //public NaveEnemiga(int tipoEnemigo, int tipoDisparo)
        addObject(new NaveEnemiga(2, 2), getWidth()/2+getWidth()/4, getHeight()/2-getHeight()/4);//Utilizo el super, ya que esta clase hereda de World y ahí se encuentran esos métodos
        addObject(new NaveEnemiga(1, 2), getWidth()/2+getWidth()/4, getHeight()/2+getHeight()/4);
        addObject(new NaveEnemiga(3, 1), getWidth()/2-getWidth()/4, getHeight()/2+getHeight()/4);
    }

    /** Método que siempre se estará ejecutando en espacio. Aquí irán los métodos de creación de objetos, por ejemplo.*/
    public void act(){
      //Condición para saber cuándo se pasó el tiempo del juego
      /*Aquí en lugar de que pare podríamos sacar al boss y al matarlo ahora sí pasar de nivel.*/
      /*Pausa: Este método revisa si se activó la pausa.*/
        if(Pausa.isPausa()) //Si se pausa, mostrar el menú.
            Greenfoot.stop();
      if(System.currentTimeMillis() - tiempoFinalJuego >= 0)
          Greenfoot.stop();
          //public void crearRocasTiempo(World mundoActual)
        roca.crearRocasTiempo(this);
        crearItemTiempo(nave);
        //System.out.println("Salud aliado: "+ NaveAliada.getPuntosSalud() +"Salud enemigo: "+ NaveEnemiga.getPuntosSalud()) ;
    }

}
