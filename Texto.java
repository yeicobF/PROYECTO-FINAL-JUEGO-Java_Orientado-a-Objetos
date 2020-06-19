import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Texto here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Texto extends Actor
{
    /**
     * Act - do whatever the Texto wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private int contador;//Una variable local que servira para cambiar de imagenes
    private int tipo;//Esta variable determinara el tipo de texto que se seleccionara
    private int nivel;//Bandera de control para saber en que nivel se encuentra.
    public Texto(int tipo)
    {
        this.tipo=tipo;
        contador=1;
        setImage("Intro"+ tipo +".png");
    }
    public void act()
    {
        if(tipo!=0)
        {
          switch(this.tipo)
          {
           case 0: break; //No hace nada ya que el tipo 0 es solo una instruccion para el usuario.
           case 1: actualizarIntro(); break; //Aqui se llama la introduccion del objeto.
           default: break;//Un default para que no haga nada.
          }
        } 
    }
    public void actualizarIntro() 
    {
        do
        {
            if(Greenfoot.isKeyDown("enter"))//Checa si se presiono alguna tecla
            {
              setImage("Intro"+ contador +".png");
              contador++;
              Greenfoot.delay(10);
            }
       }while(contador<=7);
    }    
}
