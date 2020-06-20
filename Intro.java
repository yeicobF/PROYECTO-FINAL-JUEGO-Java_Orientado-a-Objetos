import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Intro here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Intro extends World
{
    private int Bandera;//Una bandera para saber que le programa ya termino de ejecutar el objeto
    private int nivel;//Contador para saber que nivel es el que se debe llamar de forma automatica.
    private int contador;//Contador para saber en que punto va
    private Texto Cuadro;//Esta variable sera el objeto donde se almacena la imagen con el texto
    private Texto Instruccion;//Esta variabla sera el objeto que indica al jugador que tecla presionar.
    /**
     * Constructor for objects of class Intro.
     * 
     */
    public Intro(int tipo)
    {    
        // Create a new world with 1000x600 cells with a cell size of 1x1 pixels.
        super(1000, 600, 1);
        actualizarvariables(tipo);

    }
    public void act(){
        switch(this.contador)
        {
           case 1: //Aqui se ejecuta la introduccion.
            if(Greenfoot.isKeyDown("enter"))//Checa si se presiono alguna tecla
             {
                    this.contador=2;//Contador con el que se sabe cuando se dio un click y modifica el objeto de texto
             }
             break;
           case 2: 
           if(Greenfoot.isKeyDown("enter"))//Checa si se presiono alguna tecla
             {
                    this.contador=9;//Contador con el que se sabe cuando se dio un click y modifica el objeto de texto
             }
           break;
           case 3:
           if(Greenfoot.isKeyDown("enter"))//Checa si se presiono alguna tecla
             {
                    this.contador=14;//Contador con el que se sabe cuando se dio un click y modifica el objeto de texto
             }
           default: break;  
        }
            
        //}while(this.contador<=7);
        if(contador!=Bandera)
         {
          Bandera++;
          Greenfoot.setWorld(new Niveles(nivel));//Al terminar ya llama al juego.
         }
    }
    
    public void actualizarvariables(int tipo)
    {
       switch(tipo)
       {
         case 1: //Aqui se toma como el inicio del juego
          this.contador=1;
          this.nivel=1;
          this.Bandera=1;
          Texto Introduccion = new Texto(0); // Objeto permanente para que le jugador sepa que debe presionar Enter para avanzar dialogos
          Texto Cuadro = new Texto(1); //Objeto reutilizable para los dialogos
          addObject(Introduccion,500,560);//Coloca la instruccion
          addObject(Cuadro,500,200);//Coloca el cuadro de texto
          break;
         case 2://Aqui se toma como el 
          this.contador=8;
          this.nivel=2;
          this.Bandera=8;
          Texto Introduccion2 = new Texto(0); // Objeto permanente para que le jugador sepa que debe presionar Enter para avanzar dialogos
          Texto Cuadro2 = new Texto(8); //Objeto reutilizable para los dialogos
          addObject(Introduccion2,500,560);//Coloca la instruccion
          addObject(Cuadro2,500,200);//Coloca el cuadro de texto
          break;
          case 3:
          this.contador=8;
          this.nivel=3;
          this.Bandera=8;
          Texto Introduccion3 = new Texto(0); // Objeto permanente para que le jugador sepa que debe presionar Enter para avanzar dialogos
          Texto Cuadro3 = new Texto(14); //Objeto reutilizable para los dialogos
          addObject(Introduccion3,500,560);//Coloca la instruccion
          addObject(Cuadro3,500,200);//Coloca el cuadro de texto
         default: break;
       }
    }
}
