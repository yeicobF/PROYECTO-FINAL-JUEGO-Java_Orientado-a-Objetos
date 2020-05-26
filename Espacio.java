import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
* Write a description of class Espacio here.
*
* @author (Team Naves)
* @version (Lunes, 25 de mayo de 2020)
*/

//TODO: Implementar bien lo de las rocas e items, ya que se pasaron a sus respectivas clases los métodos antes utilizados aquí, así que hay que instanciar las clases si no se trata de métodos estáticos.
//TODO: Ver qué es mejor: Implementar métodos estáticos o no estáticos (esto para cuando se maneja información general, tal como el número de rocas, vidas, item activo o no, etcétera).
//TODO: Mezclar los cambios con los de Daniel y Fanny. Agregar el menú, inteligencia a las naves y lo demás.
//TODO: Ver si se puede hacer una mayor generalización de las clases con una general de Objetos y luego hereden la clase ObjetoEspacial (está en lo que hizo Daniel), Roca e items.
//TODO: Inicializar los tiempos de los items en esta clase para que cada que se instancien sigan apareciendo y no pase que al morir los items ya no vuelven a aparecer.
//TODO: Resolver la problemática de las vidas, ya que no se descuentan al morir. Esto podría ser un problema al instanciar el mundo después de morir, porque se ha de perder los puntos ganados, vidas perdidas, etcétera (o eso me imagino).

public class Espacio extends World
{
/**
 * Constructor for objects of class Espacio.
 *
 */
private Roca r;// = new Roca(); //Instanciar la clase Roca para poder acceder a sus métodos.
private Items item = new Items();
private MetodosGenerales m = new MetodosGenerales(); //Inicializar para tener los métodos no específicos.
private long tiempoMilis; // Para hacer comparaciones de tiempo para creación de objetos.
private long tiempoInicialMilis;//Tomar el tiempo en que el juego inició

public Espacio(){
    super(1000, 600, 1);//CREAR EL ESCENARIO CON LAS MEDIDAS INDICADAS (ancho, alto, tamaño de pixel)
    r = new Roca();
    tiempoInicialMilis = System.currentTimeMillis(); //Inicializar el tiempo en el que inicia el juego.
    tiempoMilis = 0;
    Roca.setNumRocasMax(5); //Inicializamos el número de rocas actual como el máximo de rocas que será el parámetro.
    Roca.setNumRocasActual(Roca.getNumRocasMax());
    //Nave nave = new Nave(50, 300);//El constructor creará las n rocas y no el método, para así manejar las rocas y compararlas.
    //Modificamos la forma de instanciar a la nave aliada. Ahora le daremos el tipo de nave y su disparo.
    NaveAliada nave = new NaveAliada(1, 3);//Para empezar a utilizar la nave aliada con su propia clase, el tipo '9' lo puse por poner, pero representa el tipo de la mave
    NaveEnemiga boss = new NaveEnemiga(2, 1);//eL SPRITE 1 SERÁ EL BOSS
    NaveEnemiga enemigo = new NaveEnemiga(2, 2);
    addObject(nave,300,300);//Aparecer a la nave en el centro
    addObject(boss, super.getWidth()/2+40, super.getHeight()/2+40);//Utilizo el super, ya que esta clase hereda de World y ahí se encuentran esos
    addObject(enemigo, super.getWidth()/2+40, super.getHeight()/2-40);
    r.crearRocas(5); //Crear las rocas primero. Luego que se vayan eliminando se crearán con el tiempo.
   // MostrarVidas v = new MostrarVidas(nave.getVidasJugador());
    //addObject(enemigo, super.getWidth()/2+200, super.getHeight()/2-200);
}

/** Método que siempre se estará ejecutando en espacio. Aquí irán los métodos de creación de objetos, por ejemplo.*/
public void act(){
    r.crearRocasTiempo(tiempoMilis);
    // public void crearItemTiempo(int velocidadItem, long tiempoInicialMilis, long tiempoAumentoVelocidad);
    // item.crearItemTiempo(1, tiempoInicialMilis, 20000);
    }
}
