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
    private int limite;
    private int contador;//Una variable local que servira para cambiar de imagenes
    private int tipo;//Esta variable determinara el tipo de texto que se seleccionara
    private int nivel;//Bandera de control para saber en que nivel se encuentra.
    public Texto(int tipo)
    {
        this.tipo=tipo;
        switch(tipo)
        {
            case 1: contador=1; limite=6; break;
            case 8: contador=8; limite=12; break;
            case 14: contador=14; limite=17; break;
        }
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
           case 8: actualizarIntro(); break; //Aqui se llama la introduccion del objeto.
           case 14: actualizarIntro(); break; //Aqui se llama la introduccion del objeto.
           default: break;//Un default para que no haga nada.
          }
        } 
    }
    public void actualizarIntro() 
    {
        while(contador<=limite)
        {
            if(Greenfoot.isKeyDown("enter"))//Checa si se presiono alguna tecla
            {
              contador++;
              setImage("Intro"+ contador +".png");
              
              Greenfoot.delay(15);
            }
       }
    }    
}
