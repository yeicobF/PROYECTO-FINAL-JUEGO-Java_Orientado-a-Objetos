import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Clase que muestra las vidas actuales del jugador. Por fin funcionó. Establece la imagen de las vidas (que se van actualizando)
 *  y al agregar el objeto en el escenario, estas se ven.
 * 
 * @author (Team Naves) 
 * @version (Viernes, 29 de mayo de 2020)
 */
public class MostrarVidas extends NaveAliada
{
    /**
     * Act - do whatever the MostrarVidas wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    Etiqueta e;//Instanciar clase etiqueta para agregar el cuadro de texto.
    int vidas;
    public MostrarVidas(){
        //public Etiqueta(String s, int tamaño, Color primerPlano, Color fondo)
        //Etiqueta(int tamaño, Color colorPrimerPlano, Color colorFondo);
        e = new Etiqueta(30, Color.WHITE, Color.BLACK); //Inicializar la etiqueta a mostrar con las vidas
        vidas = NaveAliada.getVidasJugador();
        setImage(e.crearCuadroTexto("Vidas: "+ NaveAliada.getVidasJugador()));
    }
    public void act() 
    {
        // if(vidas != NaveAliada.getVidasJugador()){//Si cambian las vidas, volver a instanciar con nuevas vidas.
            // vidas = NaveAliada.getVidasJugador();//Esto necesita ser optimizado, ya que instanciar a cada rato no es óptimo.
            
        // }
        System.out.println("MostrarVidas: "+ NaveAliada.getVidasJugador());
        //public GreenfootImage crearCuadroTexto(String s, int x, int y)
        //w = getWorld();
        //Recibe la imagen de crearTexto y se actualiza constantemente, así al hacer el addObject en el espacio, lo hace con el texto.
        //Condición que comprueba si las 
        if(vidas != NaveAliada.getVidasJugador())
            setImage(e.crearCuadroTexto("Vidas: "+ NaveAliada.getVidasJugador()));//, 500, 200);
        //e.crearCuadroTexto(e, w.getWidth()/2, w.getHeight()-20);
    }
}
