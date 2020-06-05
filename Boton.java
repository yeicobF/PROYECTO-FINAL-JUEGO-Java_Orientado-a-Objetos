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
    World w;
    String texto; //El texto del botón
    //public Etiqueta(int tamañoFuente, Color colorFuente, Color colorFondo, Color bordeFuente)
    public Boton(String texto, int tamañoFuente, Color colorFuente, Color colorFondo, Color bordeFuente){
        this.texto = texto;
        Etiqueta e = new Etiqueta(tamañoFuente, colorFuente, colorFondo, bordeFuente);
        setImage(e.crearCuadroTexto(texto)); //Crear el cuadro de texto y hacerlo como la imagen de aquí.
        //No necesitamos el método crearBoton. Aquí ya se crea con la imagen del texto necesario.
    }
    public static Actor creaBoton(World mundoActual, String texto, int x, int y, 
                    Color colorFuente, Color colorFondo, Color bordeFuente, int tamañoFuente)
    {
        Actor boton = new Boton(texto, tamañoFuente, colorFuente, colorFondo, bordeFuente);
        mundoActual.addObject(boton, x, y);
        return boton;
    }

    // //Crear un botón con los paarámetros que recibe. Regresa el botón para poder interactuar con él.
    // public Actor creaBoton(String s, int tamaño, int x, int y)
    // {
        // getWorld().addObject();
        // return boton;
    // }
}
