import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Write a description of class Espacio here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Espacio extends World
{

    /**
     * Constructor for objects of class Espacio.
     * 
     */
    public Espacio()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1000, 600, 1);
        int numRocas=15, cont1=0, cont2;
        //Nave nave = new Nave(50, 300);//El constructor creará las n rocas y no el método, para así manejar las rocas y compararlas.
        NaveAliada nave = new NaveAliada('0', 50, 300);//Para empezar a utilizar la nave aliada con su propia clase, el tipo '9' lo puse por poner, pero representa el tipo de la mave
        NaveEnemiga boss= new NaveEnemiga('0', 100, 0);
        NaveEnemiga enemigo = new NaveEnemiga('1', 50, 0);
        //Roca[] r = new Roca[numRocas];//Se hará un arreglo de rocas para luego eliminarlas si chocan
        addObject(nave,50,300);
        addObject(boss, super.getWidth()/2, super.getHeight()/2);//Utilizo el super, ya que esta clase hereda de World y ahí se encuentran esos métodos
        addObject(enemigo, super.getWidth()/2+100, super.getHeight()/2);
        //crearRocas(15);
        
        //crearRocas(numRocas, r);
        //while(numRocas>0){ Esto deberá ir en la misma clase de Rocas, porque aquí sólo se crea el escenario y ya.
            //for(cont1=0; cont1<15; cont1++)
                ////for(cont2=0; cont2<15; cont2++)
                   // if(r[cont1]!=null && r[cont2]!=null && !r[cont1].chocanRocas(r[cont2].getCoordX(), r[cont2].getCoordY())){
                       // r[cont1]=r[cont2]=null;//Eliminar los objetos que chocaron
                       // numRocas-=2;//Descontar las rocas que han sido eliminadas
                    //}
       // }
    }
    
    public void crearRocas(int numRocas){
        Roca r = new Roca();
        Roca[] rocas;
        rocas = r.crearRocas(numRocas);
        for(int i=0; i<numRocas; i++)
            addObject(rocas[i], rocas[i].x, rocas[i].y);
    }
}
