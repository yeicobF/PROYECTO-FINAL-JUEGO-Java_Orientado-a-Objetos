import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Intro here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Intro extends World
{
    private int contador;//Contador para saber en que punto va
    private Texto Cuadro;//Esta variable sera el objeto donde se almacena la imagen con el texto
    private Texto Instruccion;//Esta variabla sera el objeto que indica al jugador que tecla presionar.
    /**
     * Constructor for objects of class Intro.
     * 
     */
    public Intro()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1000, 600, 1);
        this.contador=1;
        Texto Introduccion = new Texto(0); // Objeto permanente para que le jugador sepa que debe presionar Enter para avanzar dialogos
        Texto Cuadro = new Texto(1); //Objeto reutilizable para los dialogos
        addObject(Introduccion,500,560);//Coloca la instruccion
        addObject(Cuadro,500,200);//Coloca el cuadro de texto
    }
    public void act() 
    {
        do
        {
            if(Greenfoot.isKeyDown("enter"))//Checa si se presiono alguna tecla
            {
             Cuadro.actualizar(contador);//Actualiza el objeto al siguiente cuadro de texto.
             this.contador++;//Contador con el que se sabe cuando se dio un click y modifica el objeto de texto
            }
        }while(this.contador<=7);
        Greenfoot.setWorld(new Niveles(1));//Al terminar ya llama al juego.
    }
}
