import greenfoot.World;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;
import greenfoot.GreenfootSound; //Para el sonido de la intro.
/**
 * Clase en la que se manejará la introducción de cada nivel.
 * Para llamar la introduccion 1, 2 o 3 usar: Greenfoot.setWorld(new Intro("inserta aca el numero"));
 * 
 * @author (Team Naves) 
 * @version (Sábado, 20 de junio de 2020)
 */
public class Intro extends World
{
    private GreenfootSound musica;
    private Texto textosIntro;//Esta variable sera el objeto donde se almacena la imagen con el texto
    private int nivel;//Contador para saber que nivel es el que se debe llamar de forma automatica.
    /** Constructor de la clase Intro que recibe el nivel al que se pasó.*/
    public Intro(int nivelIntro){
        super(1000, 600, 1);
        musica = new GreenfootSound("historia.mp3");
        GreenfootImage fondo = new GreenfootImage("escenarios/espacio8Original.png");
        fondo.scale(1000, 600); //Reescalar el fondo para que quepa en pantalla.
        setBackground(fondo);
        nivel = nivelIntro; //El número de la introducción indicará el número del nivel.
        if(nivel == 1)
            NaveAliada.setVidas(3); //Si entramos en el primer nivel, establecer las vidas como 3.
        textosIntro = new Texto(nivelIntro);
        addObject(textosIntro, 500, 200);
    }
    public void act(){
        musica.playLoop();
        if(textosIntro.isIntroFinal() && Greenfoot.isKeyDown("enter")){ //Revisa si se llegó al final de la introducción y se presionó enter.
            musica.stop(); //Parar la música al terminar la intro.
            Greenfoot.setWorld(new Niveles(nivel));//Al terminar la introducción ya llama al juego.
        }
    }
}
