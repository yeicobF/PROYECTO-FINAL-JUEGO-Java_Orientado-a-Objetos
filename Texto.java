import greenfoot.Actor;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import greenfoot.Greenfoot;
/**
 * Clase que mostrará las imágenes de cada introducción de nivel.
 * La imagen se mostrará dependiendo del nivel y el número de imagen.
 * 
 * @author (Daniel, Jacob (optimización)) 
 * @version (Sábado, 20 de junio de 2020)
 */
public class Texto extends Actor
{
    private int limiteImagen; //El límite de las imágenes para mostrar.
    private int numImagen; //El número de la imagen actual.
    private int nivel;//Bandera de control para saber en que nivel se encuentra.
    public Texto(int nivel){
        setImage("historia/presionaEnter.png"); //Imagen que indicará que deberás presionar enter para cambiar de texto.
        this.nivel = nivel;
        switch(nivel){ //Se establecerá el máximo de imágenes para la intro.
            case 1: limiteImagen = 7; break;
            case 2: limiteImagen = 6; break;
            case 3: limiteImagen = 5; break;
        }
        numImagen = 1; //Empezar desde la primer imagen.
    }
    public void act(){
        actualizarIntro();
    }
    /** Método que veriica si se presionó enter para avanzar la introducción.*/
    private void actualizarIntro(){
        if(numImagen <= limiteImagen && isEnter()){//Si se presiona enter, cambiar de imagen.
               setImage("historia/intro"+ nivel +"_"+ numImagen +".png");
               numImagen ++;
               Greenfoot.delay(15);
           }
    }    
    /** Veriica si se presoonó enter para cambiar de imagen.*/
    private boolean isEnter(){
        // if(Greenfoot.isKeyDown("enter"))
            // return true;
        return Greenfoot.isKeyDown("enter"); //Devuele lo que salga dr aquí.
    }
    /** Método que verifica si se terminó la intro, para así proceder a avanzar de nivel.*/
    public boolean isIntroFinal(){
        return numImagen >= limiteImagen + 1; //Si se superó el número de imagen, se devolverá true.
    }
}
