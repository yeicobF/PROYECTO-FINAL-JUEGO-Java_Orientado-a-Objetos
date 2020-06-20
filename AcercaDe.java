import greenfoot.Actor;
import greenfoot.World; 
import greenfoot.Color;
import greenfoot.Greenfoot;
/**
 * Clase en donde se dirá información acerca del juego.
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
        super(true);
        //public ArchivoPrueba(World mundoActual, String nombreArchivo, int tamañoFuente, Color colorFuente)
        archivo = new Archivo("prueba.txt", 30, Color.WHITE);
        archivo.mostrarArchivo(this);
        /*public static Actor creaBoton(World mundoActual, String texto, int x, int y, 
                    Color colorFuente, Color colorFondo, Color bordeFuente, int tamañoFuente)*/
        // a1 = Boton.creaBoton(this, "Siguiente", (getWidth()/2) -2, (getHeight()*2/3)+143, Color.GRAY, null, null, 30);
        // next = Boton.creaBoton(this, "Siguiente", getWidth()/2, (getHeight()*2/3)+140,Color.WHITE, null, null, 30);
    }
    public void act(){
        // if(Greenfoot.mouseClicked(next))
        // {
            // removeObject(a1);
            // removeObject(next);
            // setBackground("escenarios/espacio1.jpeg");
            // a2 = Boton.creaBoton(this, "Regresar al menu", (getWidth()/2) -202, (getHeight()*2/3)+103, Color.GRAY, null, null, 30);
            // exit = Boton.creaBoton(this, "Regresar al menu", (getWidth()/2) -200, (getHeight()*2/3)+100, Color.WHITE, null, null, 30);    
        // }
        volverMenu();
    }
}
