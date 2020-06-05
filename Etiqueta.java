import greenfoot.*;
/**
 * Clase para crear mensajes de texto con  los parámetros que reciba.  Regresa el cuadro de texto como imagen.
 * 
 * @author (Team Naves) 
 * @version (Viernes, 29 de mayo de 2020)
 */

public class Etiqueta extends Actor
{
    GreenfootImage mensaje;//Variable para mostrar el mensaje luego en el boton
    int tamañoFuente;
    String texto;
    Color colorFuente, colorFondo, bordeFuente;
    //Constructor de etiqueta que no toma en cuenta la cadena a mostrar. De esta manera no se instancia muchas veces.
    public Etiqueta(int tamañoFuente, Color colorFuente, Color colorFondo, Color bordeFuente){   //GreenfootImage(java.lang.String string, int size, java.awt.Color foreground, java.awt.Color background) 
        this.tamañoFuente = tamañoFuente;
        this.colorFuente = colorFuente;
        this.colorFondo = colorFondo;
        this.bordeFuente = bordeFuente;
    }
    //Crear un cuadro de texto con la misma etiqueta ya inicializada.
    /*Aquí se crea la imagen con el texto deseado y se regresa esta, de esta manera el objeto la recibe y al 
       agregar el objeto en el escenario, se pondrá la imagen con las coordenadas deseadas.*/
    public GreenfootImage crearCuadroTexto(String s){//, int x, int y){
        texto = s;
        mensaje = new GreenfootImage(s, tamañoFuente, colorFuente, colorFondo, bordeFuente);
        setImage(mensaje);
        //w.addObject(etiqueta, x, y);
        return getImage();
    }
    public String getTexto(){
        return texto;
    }
}
