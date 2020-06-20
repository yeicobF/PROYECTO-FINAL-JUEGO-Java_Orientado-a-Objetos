import greenfoot.World;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import greenfoot.Actor;
import greenfoot.Color;
/**
 * Clase que mostrará los créditos del juego.
 *  Esta clase antes se llamaba Credits.
 * 
 * @author (Team Naves) 
 * @version (Viernes, 19 de junio de 2020)
 */
public class Creditos extends Menu
{
    private Actor a1,a2,a3,a4,a5,a6,a7,a8;
    public Creditos()
    {    
        super(false);
        //public Etiqueta(int tamañoFuente, Color colorFuente, Color colorFondo, Color bordeFuente);
        // texto = new Etiqueta(30, Color.WHITE, null, null);
        /*public Archivo(String nombreArchivo, int tamañoFuente, Color colorFuente)*/
        archivo = new Archivo("archivos/creditos.txt", 30, Color.WHITE);
        archivo.mostrarArchivo(this);
    }
    
    public void act(){
        volverMenu();
    }
}
