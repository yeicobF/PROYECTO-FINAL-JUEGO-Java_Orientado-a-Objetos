import greenfoot.GreenfootImage; //Sólo importar el paquete de imágenes.
/**
 * Clase que manejará métodos relacionados con las imágenes.
 * No necesitará instanciarse, por lo que será abstracto.
 * 
 * @author (Jacob) 
 * @version (Jueves, 4 de junio de 2020)
 */
public abstract class Imagen  
{
    //No necesitará instanciarse.
    public Imagen(){}

    /*Método para reescalar una imagen de acuerdo a los parámetros y regresarla modificada.
     * - Recibe la imagen del tipo GreenfootImage, que es como Greenfoot las maneja.
        Lo que hace es dividir el ancho de la imagen recibida entre "division" y luego multiplicarlo por "multiplicacion",
            así obtendremos una imagen de esa proporcion.
        Ancho Imagen final = Ancho/divisor * multiplicación
        Alto Imagen final = Alto/divisor * multiplicación*/
    public static GreenfootImage modificarEscalaImagen(GreenfootImage imagen, int divisor, int multiplicacion){
        imagen.scale(imagen.getWidth()/divisor*multiplicacion, imagen.getHeight()/divisor*multiplicacion);
        return imagen;
    }
}
