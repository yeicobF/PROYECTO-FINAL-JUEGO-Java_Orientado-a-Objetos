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
    private Boton naveActual, naveNueva; //Será de tipo Botón para que al seleccionarla cambie su tamaño.
    private Boton flechaDerecha;
    private Boton flechaIzquierda;
    int numNaveActual, numNaveNueva, xActual, xNueva; //Número de la nave mostrada actualmente y la que se mostrará.
    /**
     * Constructor for objects of class SeleccionNave.
     * 
     */
    public SeleccionNave()
    {
        super(false); //Para no crear botón de "siguiente".
        setBackground("escenarios/espacio1.jpeg");
        numNaveActual = numNaveNueva = 1; //Empezar con la primer nave.
        /*Crear botones de flechas:
            public static Boton creaBotonImagen(World mundoActual, GreenfootImage imagen, String nombreImagen, int x, int y)*/
        imagen = new GreenfootImage("Elementos/flechaRojaDerecha.png");
        flechaDerecha = Boton.creaBotonImagen(this, imagen, "Elementos/flechaRojaDerecha.png", getWidth() - imagen.getWidth()/2, getHeight()/2);
        
        imagen = new GreenfootImage("Elementos/flechaRojaIzquierda.png"); //Volver a crear, que si no se queda como referencia y se modifican las dos.
        flechaIzquierda = Boton.creaBotonImagen(this, imagen, "Elementos/flechaRojaIzquierda.png", imagen.getWidth()/2, getHeight()/2);
        
        imagen = new GreenfootImage("Naves/Aliadas/NaveA"+ numNaveActual + "Grande.png");
        naveActual = Boton.creaBotonImagen(this, imagen, "Naves/Aliadas/NaveA"+ numNaveActual + "Grande.png", getWidth()/2, getHeight()/2);
        // mostrarNave();
    }
    
    public void act(){
        //nave.move(1);
        
        volverMenu(); //Volver al menú si se presiona el respectivo botón.
    }
    /** Método que mostrará la nave actual.
       boolean cambio indicará si se cambió de nave.
     * boolean ladoCambio verificará hacia dónde se hizo el cambio de la nave.*/
    private void mostrarNave(boolean cambio, boolean ladoCambio){ //boolean ladoCambio verificará hacia dónde se hizo el cambio de la nave.
        int x; //Coordenada de donde saldrá la nueva nave.
        if(numNaveActual != numNaveNueva){ //Si se cambió de nave lo hace. Para que no lo haga todo el tiempo.
            numNaveActual = numNaveNueva;
            xActual = getWidth()/2; //La nave actualmente mostrada está localizada en la mitad de la pantalla.
            imagen = new GreenfootImage("Naves/Aliadas/NaveA"+ numNaveActual + "Grande.png");
            if(ladoCambio) //true -> Izquierda. false -> derecha.
                x = xNueva = imagen.getWidth()/2; //Empezar de la izquierda.
            else
                x = xNueva = getWidth() - imagen.getWidth()/2;
            naveNueva = Boton.creaBotonImagen(this, imagen, "Naves/Aliadas/NaveA"+ numNaveActual + "Grande.png", x, getHeight()/2);
        }
        //addObject(new ActorAuxiliar("Naves/Aliadas/NaveA"+ numNave + "Grande.png"), getWidth()/2, getHeight()/2);
        if(xActual < getWidth() - naveActual.getImage().getWidth()/2 && !ladoCambio){ //Saldrá hacia la derecha.
            xActual ++;
            naveActual.setLocation(xActual, getHeight()/2);
        }
        if(xActual > naveActual.getImage().getWidth()/2 && ladoCambio){ //Saldrá hacia la izquierda.
            xActual --;
            naveActual.setLocation(xActual, getHeight()/2);
        }
        if(xNueva != getWidth()/2){
            if(xNueva < getWidth()/2)
                xNueva ++;
            else
                xNueva --;
            naveNueva.setLocation(xNueva, getHeight()/2);
            // naveNueva.move(1);
        }
        if(xActual == getWidth() - naveActual.getImage().getWidth()/2 || xActual == naveActual.getImage().getWidth()/2
            && xNueva == getWidth()/2){
            removeObject(naveActual); //Eliminar la nave actual (la que se estaba yendo).
            naveActual = naveNueva; //Ahora la nave actual es la nueva.
        }
    }
    /** Método que mueve las naves cuando hay un cambio de estas.*/
    // private void moverNaves(int xActual, int xNueva){
        // if(xActual < getWidth() - naveActual.getImage().getWidth()/2 && !ladoCambio){ //Saldrá hacia la derecha.
            // xActual ++;
            // naveActual.move(1);
        // }
        // if(xActual > naveActual.getImage().getWidth()/2 && ladoCambio){ //Saldrá hacia la izquierda.
            // xActual --;
            // naveActual.move(1);
        // }
    // }
    /** Método que irá cambiando la nave que se muestra.*/
    private void cambiarNave(){
        /*if(boton = derecha) numNave ++;
           if(boton == izquierda) numNave --;
           if(numNave > navesMax) numNave = 1; //Se reinicia el conteo.
           if(numNave < 1) numNave = navesMax; //Pasar a la máxima.*/
    }
    /** Método que verá que nave elegiste.*/
    private void seleccionNave(){
        NaveAliada.setDiseñoNaveAliada(numNaveActual);
        NaveAliada.setTipoDisparo(1); //El tipo de disparo base.
    }
}
