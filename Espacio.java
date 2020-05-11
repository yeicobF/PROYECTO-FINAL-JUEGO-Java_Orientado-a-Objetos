import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Write a description of class Espacio here.
 * 
 * @author (your name) 
 * @version (Domingo, 10 de mayo - Lunes, 11 de mayo de 2020)
 */
public class Espacio extends World
{

    /**
     * Constructor for objects of class Espacio.
     * 
     */
    MetodosGenerales m = new MetodosGenerales();
    public Espacio(){
        super(1000, 600, 1);//CREAR EL ESCENARIO CON LAS MEDIDAS INDICADAS (ancho, alto, tamaño de pixel)
        int numRocas=10, cont1=0, cont2;
        //Nave nave = new Nave(50, 300);//El constructor creará las n rocas y no el método, para así manejar las rocas y compararlas.
        //Modificamos la forma de instanciar a la nave aliada. Ahora le daremos el tipo de nave y su disparo.
        NaveAliada nave = new NaveAliada(1, 3, 3);//Para empezar a utilizar la nave aliada con su propia clase, el tipo '9' lo puse por poner, pero representa el tipo de la mave
        NaveEnemiga boss = new NaveEnemiga(2, 1);//eL SPRITE 1 SERÁ EL BOSS
        NaveEnemiga enemigo = new NaveEnemiga(2, 2);
        addObject(nave,50,300);//Aparecer a la nave en el centro
        addObject(boss, super.getWidth()/2+40, super.getHeight()/2+40);//Utilizo el super, ya que esta clase hereda de World y ahí se encuentran esos métodos
        addObject(enemigo, super.getWidth()/2+40, super.getHeight()/2-40);
        crearRocas(numRocas);
        MostrarVidas v = new MostrarVidas(nave.getVidasJugador());
        addObject(enemigo, super.getWidth()/2+200, super.getHeight()/2-200);
    }
    /*Método para crear los meteoros en una posición random con un sprite random*/
    public void crearRocas(int numRocas){
        Roca r;
        int x, y, random;
        for(int i=0; i<numRocas; i++){
            random = m.getRandomNumber(1, 4);//Random para el sprite aleatorio del meteoro. Los sprites van del 1 al 4.
            r = new Roca(random);//Este parámetro indicará qué tipo de meteoro se generará
            x = Greenfoot.getRandomNumber(getWidth());//Ancho
            y = Greenfoot.getRandomNumber(getHeight());//Alto
            addObject(r, x, y);
        }
    }
}
