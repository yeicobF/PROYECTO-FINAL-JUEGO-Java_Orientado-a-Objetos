import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Clase que mostrará las imágenes de cada introducción de nivel.
 * La imagen se mostrará dependiendo del nivel y el número de imagen.
 * 
 * @author (Daniel) 
 * @version (Sábado, 20 de junio de 2020)
 */
public class Texto extends Actor
{
    /**
     * Act - do whatever the Texto wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private int limiteImagen; //El límite de las imágenes para mostrar.
    // private int contador;//Una variable local que servira para cambiar de imagenes
    // private int tipo;//Esta variable determinara el tipo de texto que se seleccionara
    private int numImagen; //El número de la imagen actual.
    private int nivel;//Bandera de control para saber en que nivel se encuentra.
    public Texto(int nivel)
    {
        // this.tipo=tipo;
        // switch(tipo)
        // {
            // case 1: contador=1; limite=6; break;
            // case 8: contador=8; limite=12; break;
            // case 14: contador=14; limite=17; break;
        // }
        // contador=1;
        setImage("historia/presionaEnter.png"); //Imagen que indicará que deberás presionar enter para cambiar de texto.
        this.nivel = nivel;
        switch(nivel){ //Se establecerá el máximo de imágenes para la intro.
            case 1: limiteImagen = 7; break;
            case 2: limiteImagen = 6; break;
            case 3: limiteImagen = 5; break;
        }
        numImagen = 1; //Empezar desde la primer imagen.
    }
    public void act()
    {
        // if(tipo!=0)
        // {
          // switch(this.tipo)
          // {
           // case 0: break; //No hace nada ya que el tipo 0 es solo una instruccion para el usuario.
           // case 1: actualizarIntro(); break; //Aqui se llama la introduccion del objeto.
           // case 8: actualizarIntro(); break; //Aqui se llama la introduccion del objeto.
           // case 14: actualizarIntro(); break; //Aqui se llama la introduccion del objeto.
           // default: break;//Un default para que no haga nada.
          // }
        // } 
        actualizarIntro();
    }
    private void actualizarIntro() 
    {
        // while(contador<=limite)
        // {
            // if(Greenfoot.isKeyDown("enter"))//Checa si se presiono alguna tecla
            // {
              // contador++;
              // setImage("Intro"+ contador +".png");
              
              // Greenfoot.delay(15);
            // }
       // }
       if(numImagen <= limiteImagen && isEnter()){//Si se presiona enter, cambiar de imagen.
               setImage("historia/intro"+ nivel +"_"+ numImagen +".png");
               numImagen ++;
               Greenfoot.delay(15);
           }
    }    
    /** Veriica si se presoonó enter para cambiar de imagen.*/
    private boolean isEnter(){
        if(Greenfoot.isKeyDown("enter"))
            return true;
        return false;
    }
}
