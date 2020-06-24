import greenfoot.World;
import greenfoot.Greenfoot;
import greenfoot.GreenfootSound;
/**
 * Clase que mostrará un texto de Game Over, seguido del ingreso de tu
   nombre a los marcadores.
 * 
 * @author (Team Naves) 
 * @version (Sábado, 20 de junio de 2020)
 */
public class GameOver extends World
{
    private Jugador jugador;
    private NaveAliada nave;
    //Se inicializa la canción desde el inicio para que se pueda comprobar desde que el menú comience. Si no, dará problemas.
    private static GreenfootSound musica = new GreenfootSound("GameOver.mp3"); //Necesiita ser estática, porque se reproducirá hasta regresar al menú.
    /** Constructor que recibe la nave que controlamos para recibir sus estadísticas. 
     *      De esta manera se podrá llamar a jugador desde aquí.*/
    public GameOver(NaveAliada nave){
        super(1000, 600, 1); //Para que no se cree botón de "siguiente". Solo el de regresar al menú. 
        setBackground("escenarios/gameOver.jpg");
        this.nave = nave;
        if(Niveles.isMusicaReproduciendose()) //Si aún hay música reproduciendose de los niveles.
            Niveles.pararMusica();
        musica.playLoop(); //Reproducir en loop hasta salir de los marcadores al menú.
    }
    public void act(){
        Greenfoot.delay(60);
        jugador = new Jugador(nave.getPuntos());
    }
    /** Método que devolverá true si la música sigue reproduciendose.*/
    public static boolean isMusicaReproduciendose(){
        return musica.isPlaying(); //Regresará true si la canción se sigue reproduciendo.
    }
    /** Método para detener la canción de GameOver al regresar al menú.*/ 
    public static void pararMusica(){
        musica.stop();
    }
}
