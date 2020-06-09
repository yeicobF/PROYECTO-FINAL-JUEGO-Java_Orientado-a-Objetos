import greenfoot.*;
/**
 * Clase para crear mensajes de texto con  los parámetros que reciba.  Regresa el cuadro de texto como imagen.
 *
 * @author (Team Naves)
 * @version (Viernes, 29 de mayo de 2020)
 */

public class Etiqueta// extends Actor
{   /* Quité la herencia de actor, ya que entonces no tendría sentido crear
        una clase de Botón, porque este también podría ser interactuable
        y se podría hacer de manera directa, entonces creamos la imagen
        y la regresamos en el método creaCuadroTexto(texto)*/
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
        texto = ""; //Inicializar el texto vacío por si no se ha enviado texto que se regrese esto con el getter.
    }
    //Crear un cuadro de texto con la misma etiqueta ya inicializada.
    /*Aquí se crea la imagen con el texto deseado y se regresa esta, de esta manera el objeto la recibe y al
       agregar el objeto en el escenario, se pondrá la imagen con las coordenadas deseadas.*/
    public GreenfootImage crearCuadroTexto(String texto){//, int x, int y){
        this.texto = texto;
        mensaje = new GreenfootImage(texto, tamañoFuente, colorFuente, colorFondo, bordeFuente);
        //setImage(mensaje);
        //w.addObject(etiqueta, x, y);
        //Regresamos el mensaje directamente, ya que como esto no es un actor, no necesita ni se puede usar el setImage();.
        return mensaje;
    }
    public String getTexto(){
        // System.out.println(texto);
        // System.out.println(texto.length());
        return texto;
    }
    public int getLargoTexto(){
      return texto.length();
    }
    /*Método que devuelve el equivalente del largo del texto
        al tamaño en que mide la fuente como imagen en el juego.
        Esto porque teniendo los caracteres miden pixeles,
          pero multiplicando por el tamaño de la fuente se
          convierte en "tamaño real."*/
    public int getProporcionTextoImagen(){
      //System.out.println(texto + texto.length() * 8);
      return texto.length() * 8; //1 pixel -> 0.125 char
    }
}
