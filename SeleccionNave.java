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
    /* Crear botones de flecha izquierda y derecha.*/
    private GreenfootImage imagen; //Para sacar las medidas de las flechas.
    private Boton flechaDerecha;
    private Boton flechaIzquierda;
    int numNave; //Número de la nave mostrada.
    /**
     * Constructor for objects of class SeleccionNave.
     * 
     */
    public SeleccionNave()
    {
        super(false); //Para no crear botón de "siguiente".
        setBackground("escenarios/espacio1.jpeg");
        numNave = 1; //Empezar con la primer nave.
        /*Crear botones de flechas:
            public static Boton creaBotonImagen(World mundoActual, GreenfootImage imagen, String nombreImagen, int x, int y)*/
        imagen = new GreenfootImage("Elementos/flechaRojaDerecha.png");
        flechaDerecha = Boton.creaBotonImagen(this, imagen, "Elementos/flechaRojaDerecha.png", getWidth() - imagen.getWidth()/2, getHeight()/2);
        imagen = new GreenfootImage("Elementos/flechaRojaIzquierda.png"); //Volver a crear, que si no se queda como referencia y se modifican las dos.
        flechaIzquierda = Boton.creaBotonImagen(this, imagen, "Elementos/flechaRojaIzquierda.png", imagen.getWidth()/2, getHeight()/2);
        mostrarNave();
    }
    
    public void act(){
        volverMenu(); //Volver al menú si se presiona el respectivo botón.
    }
    /** Método que mostrará la nave actual.*/
    private void mostrarNave(){
        addObject(new ActorAuxiliar("Naves/Aliadas/NaveA"+ numNave + "Grande.png"), getWidth()/2, getHeight()/2);
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
