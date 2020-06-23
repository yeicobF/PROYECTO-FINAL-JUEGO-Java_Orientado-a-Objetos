import greenfoot.Actor;
import greenfoot.World; 
import greenfoot.Color;
import greenfoot.Greenfoot;
/**
 * Clase en donde se dirá información acerca del juego. Se mostrará el lore técnicamente.
 *  Antes llamada About.
 * 
 * @author (Team Naves) 
 * @version (Viernes, 19 de junio de 2020)
 */
public class AcercaDe extends Menu
{

    /**
     * Constructor for objects of class AcercaDe.
     * 
     */
    public AcercaDe(){
        super(false);
        //public ArchivoPrueba(World mundoActual, String nombreArchivo, int tamañoFuente, Color colorFuente)
        archivo = new Archivo("archivos/acercaDe.txt", 26, Color.WHITE);
        archivo.mostrarArchivo(this);
    }
    public void act(){
        volverMenu();
    }
}
