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
        super(600, 600, 1);
        int numRocas=15, cont1=0, cont2;
        Nave nave = new Nave();//El constructor creará las n rocas y no el método, para así manejar las rocas y compararlas.
        //NaveEnemiga malo = new NaveEnemiga();
        //Roca[] r = new Roca[numRocas];//Se hará un arreglo de rocas para luego eliminarlas si chocan
        addObject(nave,300,300);
        //addObject(malo,100,100);
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
    

}
