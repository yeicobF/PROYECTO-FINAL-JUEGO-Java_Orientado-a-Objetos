import greenfoot.Actor;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import greenfoot.World;
import greenfoot.Color;
import greenfoot.Greenfoot; //Para crear el mundo de la pantalla siguiente.
/**
 * Clase en donde se mostrarán las instrucciones de cómo jugar.
 *  Antes llamada How.
 * 
 * @author (Team Naves) 
 * @version (Viernes, 19 de junio de 2020)
 */
public class ComoJugar extends Menu
{
    /** Constructor de a clase ComoJugar.*/
    public ComoJugar(boolean siguiente){ //siguiente revisa si se avanzó a la siguiente página o no.
        super(!siguiente); //Si se avanzó a la página siguiente, siguiente = true, entonces enviar false para no crear el botón.
        /*public Archivo(String nombreArchivo, int tamañoFuente, Color colorFuente)*/
        if(!siguiente) //No se ha avanzado de página
            archivo = new Archivo("archivos/comoJugar1.txt", 30, Color.WHITE);
        else{
            /* Crea el botń "anterior" aquí directamente, ya que si lo pongo en el
               superconstructor, las demás clases crearán el botón innecesariamente.
               Habría que pensar en una mejor implementación para que no pase esto.*/
            crearAnterior(); //Crear botón para volver.
            botonSiguiente = null; //Inicializar el botón siguiente como null para que no lo detecte.
            archivo = new Archivo("archivos/comoJugar2.txt", 30, Color.WHITE);
        }
        archivo.mostrarArchivo(this);
    }

    public void act(){
        if(isSiguiente()){
            mundoAnterior = this;
            Greenfoot.setWorld(new ComoJugar(true)); //true porque se avanzó al siguiente.
        }
        if(isAnterior())//Se presionó el botón de anterior. Restablecer el mundo pasado.
            Greenfoot.setWorld(mundoAnterior);
        volverMenu();
    }
}
