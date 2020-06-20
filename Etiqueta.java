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
    // /*Método que devuelve el equivalente del largo del texto
        // al tamaño en que mide la fuente como imagen en el juego.
        // Esto porque teniendo los caracteres miden pixeles,
          // pero multiplicando por el tamaño de la fuente se
          // convierte en "tamaño real."
          // NO ES NECESARIO*/
    // public int getProporcionTextoImagen(){
      // //System.out.println(texto + texto.length() * 8);
      // return texto.length() * 8; //1 pixel -> 0.125 char
    // }
    /*Getter de la imagen creada con el texto*/
    public GreenfootImage getImagen(){
        return mensaje;
    }
    public void setImagen(GreenfootImage imagen){
        mensaje = imagen;
    }
    /*Método que regresa la x en donde el texto estará centrado
        en el método drawImage(), ya que en las coordenadas hay
        que tomar el tamaño de la letra para centrarlo.*/
    public int getXCentrada(){
        if(texto.isEmpty()) //Si no hay nada en la línea de texto regresar 0 como coordenada, si no, dará problemas.
            return 0;
        else
            if(texto.length()%2 == 1){ //El texto tiene letras impares, centrar más a la izquierda.
                System.out.println("IMPAR: "+ (mensaje.getWidth()/texto.length())*(texto.length()/2 + 1));
                return (mensaje.getWidth()/texto.length())*(texto.length()/2 + 1);
            }
            else{ //El número de letras es par, centrar normal.
                System.out.println("PAR: "+ (mensaje.getWidth()/texto.length())*(texto.length()/2));
                return (mensaje.getWidth()/texto.length())*(texto.length()/2);
            }
    }
    /* Método que devolverá x para la sombra de un texto. La sombra está a la izquierda.*/
    public int getXSombra(int divisorLargo){  /*El divisorLargo es para la sombra en X se acomode. Depende del tamaño de la fuente.*/
        /*Para esto hay que restarle media letra a la coordenada para que se vea la sombra.*/
        //mensaje.getWidth()/texto.length()/3 devuelve poco menos de la mitad de una letra.
        //Se suma a la coordenada centrada porque es lo que se va a restar desde el medio de la imagen.
        return getXCentrada() + mensaje.getWidth()/texto.length()/divisorLargo;
    }
}
