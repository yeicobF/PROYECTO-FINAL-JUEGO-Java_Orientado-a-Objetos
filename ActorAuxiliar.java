import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Clase que será un auxiliar para poder manejar cosas que no tienen un uso general o específico.
 *  Por ejemplo para guardar cuadros de texto y obtener sus medidas.
 * 
 * @author (Jacob) 
 * @version (Viernes, 19 de junio de 2020)
 */
public class ActorAuxiliar extends Actor
{
    public ActorAuxiliar(){} 
    /** Constructor para establecer la imagen con la recibida en el parámetro.*/
    public ActorAuxiliar(String nombreImagen){
        setImage(nombreImagen);
    }
}
