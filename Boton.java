import greenfoot.*;
/**
 * Clase que funciona para crear botones en el escenario. Estos serán interactivos porque son actores.
 *
 * @author (Team Naves)
 * @version (Jueves, 4 de junio de 2020)
 */

public class Boton extends Actor
{
    // instance variables - replace the example below with your own
    String texto; //El texto del botón
    private Etiqueta e;
    /*Constructor para cuando el botón creado es una imagen.*/
    private Boton(GreenfootImage imagen){
        setImage(imagen); //Establece la imagen del botón.
    }
    //public Etiqueta(int tamañoFuente, Color colorFuente, Color colorFondo, Color bordeFuente)
    private Boton(String texto, int tamañoFuente, Color colorFuente, Color colorFondo, Color bordeFuente){
        this.texto = texto;
        e = new Etiqueta(tamañoFuente, colorFuente, colorFondo, bordeFuente);
        setImage(e.crearCuadroTexto(texto)); //Crear el cuadro de texto y hacerlo como la imagen de aquí.
        //No necesitamos el método crearBoton. Aquí ya se crea con la imagen del texto necesario.
    }
    public static Boton creaBoton(World mundoActual, String texto, int x, int y,
                    Color colorFuente, Color colorFondo, Color bordeFuente, int tamañoFuente)
    {
        Boton boton = new Boton(texto, tamañoFuente, colorFuente, colorFondo, bordeFuente);
        /*El botón se creará centrado tomando en cuenta las coordenadas proporcionadas,
            es decir, x y y serán el centro del botón, no el inicio.*/
        mundoActual.addObject(boton, x - boton.e.getLargoTexto()/2, y);
        //System.out.println(boton.e.getTexto() + boton.e.getLargoTexto() +"\n");
        //System.out.println(x - boton.e.getLargoTexto()/2 + "\n");
        return boton;
    }
    /*Método que crea un botón a partir de una imagen dada, sus dimensiones y sus coordenadas.*/
    public static Boton creaBotonImagen(World mundoActual, GreenfootImage imagen, int x, int y){
        Boton boton = new Boton(imagen); //Recibe el botón con su imagen.
        mundoActual.addObject(boton, x, y); //Agrega el botón.
        return boton;
    }
    // //Crear un botón con los paarámetros que recibe. Regresa el botón para poder interactuar con él.
    // public Actor creaBoton(String s, int tamaño, int x, int y)
    // {
        // getWorld().addObject();
        // return boton;
    // }
}
