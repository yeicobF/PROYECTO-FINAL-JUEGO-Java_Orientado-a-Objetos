import greenfoot.World;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import greenfoot.Greenfoot;
/**
 * Clase que mostrará un texto de Game Over, seguido del ingreso de tu
 *  nombre a los marcadores.
 * 
 * @author (Daniel, Jacob (agregué unas cosas)) 
 * @version (Sábado, 20 de junio de 2020)
 */
public class GameOver extends World
{
    private Jugador jugador;
    private NaveAliada nave;
    /**
     * Constructor for objects of class GameOver.
     * 
     */
    public GameOver(NaveAliada nave)
    {    /* Se recibe la nave que controlamos para recibir sus estadísticas. De esta manera se podrá llamar a jugador desde aquí.*/
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1000, 600, 1); //Para que no se cree botón de "siguiente". Solo el de regresar al menú. 
        setBackground("escenarios/gameOver.jpg");
        this.nave = nave;
    }
    public void act(){
        Greenfoot.delay(70);
        jugador = new Jugador(nave.getPuntos());
    }
    // public void act(){
        // if(Greenfoot.isKeyDown("enter"))
            // Greenfoot.setWorld(new Menu());  
    // }
}
