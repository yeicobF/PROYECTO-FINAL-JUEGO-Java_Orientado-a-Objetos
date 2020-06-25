import greenfoot.GreenfootImage;
import greenfoot.Color;
/**
 * Clase para crear mensajes de texto con  los parámetros que reciba.  Regresa el cuadro de texto como imagen.
 *
 * @author (Team Naves)
 * @version (Viernes, 29 de mayo de 2020)
 */

public class Etiqueta
{   /* Quité la herencia de actor, ya que entonces no tendría sentido crear
        una clase de Botón, porque este también podría ser interactuable
        y se podría hacer de manera directa, entonces creamos la imagen
        y la regresamos en el método creaCuadroTexto(texto)*/
    GreenfootImage mensaje;//Variable para mostrar el mensaje luego en el boton
    String texto;
    Color colorFuente, colorFondo, bordeFuente;
    int tamañoFuente;
    /** Constructor de etiqueta que no toma en cuenta la cadena a mostrar. De esta manera no se instancia muchas veces.*/
    public Etiqueta(int tamañoFuente, Color colorFuente, Color colorFondo, Color bordeFuente){
           //GreenfootImage(java.lang.String string, int size, java.awt.Color foreground, java.awt.Color background)
        this.tamañoFuente = tamañoFuente;
        this.colorFuente = colorFuente;
        this.colorFondo = colorFondo;
        this.bordeFuente = bordeFuente;
        texto = ""; //Inicializar el texto vacío por si no se ha enviado texto que se regrese esto con el getter.
    }
    /** Método en donde se crea la imagen con el texto deseado y se regresa esta, de esta manera el objeto la recibe y al
       agregar el objeto en el escenario, se pondrá la imagen con las coordenadas deseadas.*/
    public GreenfootImage crearCuadroTexto(String texto){//, int x, int y){
        this.texto = texto;
        mensaje = new GreenfootImage(texto, tamañoFuente, colorFuente, colorFondo, bordeFuente);
        //Regresamos el mensaje directamente, ya que como esto no es un actor, no necesita ni se puede usar el setImage();.
        return mensaje;
    }
    /** Getter del texto de la etiqueta.*/
    public String getTexto(){
        return texto;
    }
    /** Método para obtener el largo del texto.*/
    public int getLargoTexto(){
      return texto.length();
    }
    /** Getter de la imagen creada con el texto.*/
    public GreenfootImage getImagen(){
        return mensaje;
    }
    public void setImagen(GreenfootImage imagen){
        mensaje = imagen;
    }
    /** Método que regresa la x en donde el texto estará centrado
        en el método drawImage(), ya que en las coordenadas hay
        que tomar el tamaño de la letra para centrarlo.*/
    public int getXCentrada(){
        if(texto.isEmpty()) //Si no hay nada en la línea de texto regresar 0 como coordenada, si no, dará problemas.
            return 0;
        else
            if(texto.length()%2 == 1) //El texto tiene letras impares, centrar más a la izquierda.
                return (mensaje.getWidth()/texto.length())*(texto.length()/2 + 1);
            else //El número de letras es par, centrar normal.
                return (mensaje.getWidth()/texto.length())*(texto.length()/2);
    }
    /** Método que devolverá x para la sombra de un texto. La sombra está a la izquierda.*/
    public int getXSombra(int divisorLargo){  /*El divisorLargo es para la sombra en X se acomode. Depende del tamaño de la fuente.*/
        /*Para esto hay que restarle media letra a la coordenada para que se vea la sombra.*/
        //Se suma a la coordenada centrada porque es lo que se va a restar desde el medio de la imagen.
        return getXCentrada() + mensaje.getWidth()/texto.length()/divisorLargo;
    }
}
