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
    public Espacio(int tipoNaveAliada){
        //super();
        //public Roca(int numRocasMax, int tiempoRegeneracion)
        // roca = new Roca(numRocasMax, 10000); //Inicializamos el número de rocas actual como el máximo
        //Nave nave = new Nave(50, 300);//El constructor creará las n rocas y no el método, para así manejar las rocas y compararlas.
        //Modificamos la forma de instanciar a la nave aliada. Ahora le daremos el tipo de nave y su disparo.
        NaveAliada nave = new NaveAliada(1, 3);//Para empezar a utilizar la nave aliada con su propia clase, el tipo '9' lo puse por poner, pero representa el tipo de la mave
        //Como NaveEnemiga.puntosSalud es estático, entonces el último que se instancie dará el valor de los PS. Quitarles lo estático.
        NaveEnemiga enemigo = new NaveEnemiga(2, 2);
        NaveEnemiga boss = new NaveEnemiga(1, 2);//eL SPRITE 1 SERÁ EL BOSS

        addObject(nave,300,300);//Aparecer a la nave en el centro
        addObject(boss, super.getWidth()/2+40, super.getHeight()/2+40);//Utilizo el super, ya que esta clase hereda de World y ahí se encuentran esos métodos
        addObject(enemigo, super.getWidth()/2+40, super.getHeight()/2-40);
        roca.crearRocas(Roca.getNumRocasMax(), this); //Crear las rocas primero. Luego que se vayan eliminando se crearán con el tiempo.
        //MostrarVidas cuadroVidas = new MostrarVidas();
        /*Agrega el objeto que muestra las vidas. Como la imagen de este serán las vidas que se actualizan constantemente,
           se agrega como objeto con las coordenadas deseadas.
           Hacer lo mismo para mostrar los puntos, incluso podría ser en la misma clase.
            - MostrarEstado tal vez.*/
        //public MostrarInfo(int tipoInfo, int tamañoFuente, Color colorFuente, Color colorFondo, Color bordeFuente)
       addObject(new MostrarInfo(1, 30, Color.WHITE, Color.BLACK, null), 500, 50);//Vidas
       addObject(new MostrarInfo(2, 30, Color.WHITE, Color.BLACK, Color.RED), 500, 50+30);//Sumo 30 en y por el tamaño de la fuente anterior
       // addObject(new MostrarInfo(3, 20, Color.WHITE, Color.BLACK, null), 50, getHeight()-20);
       // addObject(new MostrarInfo(4, 20, Color.RED, Color.BLACK, Color.WHITE), getWidth()-200, 20);
       // MostrarVidas v = new MostrarVidas(nave.getVidasJugador());
        //addObject(enemigo, super.getWidth()/2+200, super.getHeight()/2-200);
    }

    /** Método que siempre se estará ejecutando en espacio. Aquí irán los métodos de creación de objetos, por ejemplo.*/
    public void act(){
      //Condición para saber cuándo se pasó el tiempo del juego
      /*Aquí en lugar de que pare podríamos sacar al boss y al matarlo ahora sí pasar de nivel.*/  
      if(System.currentTimeMillis() - tiempoFinalJuego >= 0)
          Greenfoot.stop();
          //public void crearRocasTiempo(World mundoActual)
        roca.crearRocasTiempo(this);
        crearItemTiempo();
        //System.out.println("Salud aliado: "+ NaveAliada.getPuntosSalud() +"Salud enemigo: "+ NaveEnemiga.getPuntosSalud()) ;
    }

}
