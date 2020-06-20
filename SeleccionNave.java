import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Clase que manejará la selección de naves. Mostrará las naves y dependiendo de a cuál
 *  se le haga click, se le establecerán sus propiedades.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SeleccionNave extends Menu
{
    // NaveAliada nave; //Para mostrar las naves.
    int numNave; //Número de la nave mostrada.
    /**
     * Constructor for objects of class SeleccionNave.
     * 
     */
    public SeleccionNave()
    {
        super(false); //Para no crear botón de "siguiente".
        setBackground("escenarios/espacio1.jpeg");
        numNave = 2; //Empezar con la primer nave.
        mostrarNave();
    }
    
    public void act(){
        
    }
    /** Método que mostrará la nave actual.*/
    private void mostrarNave(){
        addObject(new NaveAliada(numNave), getWidth()/2, getHeight()/2);
    }
    /** Método que irá cambiando la nave que se muestra.*/
    private void cambiarNave(){
        /*if(boton = derecha) numNave ++;
           if(boton == izquierda) numNave --;
           if(numNave > navesMax) numNave = 1; //Se reinicia el conteo.
           if(numNave < 1) numNave = navesMax; //Pasar a la máxima.*/
    }
    /** Método que verá que nave elegiste.*/
    private void seleccionNave(){
        NaveAliada.setDiseñoNaveAliada(numNave);
        NaveAliada.setTipoDisparo(1); //El tipo de disparo base.
    }
}
