import greenfoot.*;
/**
 * Clase para crear el mensaje que se mostrará en el botón.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class Etiqueta extends Actor
{
    GreenfootImage mensaje;//Variable para mostrar el mensaje luego en el boton
    World w;
    
    public Etiqueta(String s, int tamaño, Color primerPlano, Color fondo){   //GreenfootImage(java.lang.String string, int size, java.awt.Color foreground, java.awt.Color background) 
        mensaje = new GreenfootImage(s, tamaño, primerPlano, fondo);
        setImage(mensaje);
    }
    //Crear un cuadro de texto con la misma etiqueta ya inicialozada.
    public void crearCuadroTexto(Etiqueta etiqueta, int x, int y){
        w = getWorld();
        w.addObject(etiqueta, x, y);
    }
}
