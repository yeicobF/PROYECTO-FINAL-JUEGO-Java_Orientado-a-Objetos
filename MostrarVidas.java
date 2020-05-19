import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Clase de prueba para ver que las vidas no se reinicien al perder. 
 * No funcionó, pero quería que se mostraran en pantalla para ver cómo estaban funcionando.
 * -> Hay que hacerla funcionar.
 * 
 * @author (Team Naves) 
 * @version (Domingo, 17 de mayo - Lunes, 18 de mayo de 2020)
 */
public class MostrarVidas extends Actor
{
    /**
     * Act - do whatever the MostrarVidas wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    int vidas;
    public MostrarVidas(int vidas){
        this.vidas=vidas;
    }
    public void act() 
    {
        setImage(new GreenfootImage(""+ vidas, 35, null, null));
    }
}
