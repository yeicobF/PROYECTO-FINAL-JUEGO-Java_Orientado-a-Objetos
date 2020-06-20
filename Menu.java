import greenfoot.Greenfoot;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import greenfoot.World;
import greenfoot.GreenfootSound;
import greenfoot.Color;
/**
 * Clase que manejará el acceso a todos los submenús, además de métodos que tendrán en común.
 *  Antes era la clase Portada.
 * - Iniciar
 * - ¿Cómo jugar?
 * - Acerca de
 * - Marcadores
 * - Créditos
 * 
 * @author (Team Nave) 
 * @version (Viernes, 20 de junio de 2020)
 */
public class Menu extends World
{
    private Etiqueta texto; //Para crear los cuadros de texto.
    private Boton botonIniciar;
    private Boton botonCreditos;
    private Boton botonAcercaDe;
    private Boton botonComoJugar;
    private Boton botonMarcadores;
    private GreenfootSound musica; //Para reproducir la música de fondo.
    /**
     * Constructor for objects of class Menu.
     * 
     */
    public Menu()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1000, 600, 1); 
        setBackground("escenarios/espacio2.jpg");
        /* public static Actor creaBoton(World mundoActual, String texto, int x, int y,
                    Color colorFuente, Color colorFondo, Color bordeFuente, int tamañoFuente)*/
        /* Crear cuadros de texto no necesariamente interactuables:
            -> public Etiqueta(int tamañoFuente, Color colorFuente, Color colorFondo, Color bordeFuente)
            -> public GreenfootImage crearCuadroTexto(String s) <- Recibir los cuadros de texto como imagen.*/
        texto = new Etiqueta(50, Color.GRAY, null, null); //Inicializar cuadro de texto que simulará la sombra de los botones.
        /*public static Boton creaBotonSombra(World mundoActual, String textoBoton, Etiqueta texto, int x, int y,
                    Color colorFuente, Color colorFondo, Color bordeFuente, int tamañoFuente, int divisorLargo)*/
        botonIniciar = Boton.creaBotonSombra(this, "Iniciar", texto, getWidth()/2, getHeight()/10, Color.WHITE, null, null, 50, 20);
        botonComoJugar = Boton.creaBotonSombra(this, "¿Cómo Jugar?", texto, getWidth()/2, getHeight()/10*3, Color.WHITE, null, null, 50, 6);
        botonAcercaDe = Boton.creaBotonSombra(this, "Acerca de", texto, getWidth()/2, getHeight()/10*5, Color.WHITE, null, null, 50, 20);
        botonMarcadores = Boton.creaBotonSombra(this, "Marcadores", texto, getWidth()/2, getHeight()/10*7, Color.WHITE, null, null, 50, 20);
        botonCreditos = Boton.creaBotonSombra(this, "Créditos", texto, getWidth()/2, getHeight()/10*9, Color.WHITE, null, null, 50, 5);
        
        // if(musica != null) //Si ya se estaba reproduciendo música.
            // musica.stop();
        musica = new GreenfootSound("sounds/TitleScreen.mp3");
        musica.playLoop(); //Reproducir la canción en un loop.
    }
    public void act()
    {
        if(Greenfoot.mouseClicked(botonIniciar)){
            musica.stop();
            //Mode world = new Mode();
            //Niveles nivel = new Niveles();
            NaveAliada.setDiseñoNaveAliada(1);
            NaveAliada.setTipoDisparo(1);
            // Greenfoot.setWorld(new Intro());
            Greenfoot.setWorld(new Niveles(1));
        }
        if( Greenfoot.mouseClicked(botonCreditos))
            Greenfoot.setWorld(new Credits());
        if(Greenfoot.mouseClicked(botonAcercaDe))
            Greenfoot.setWorld(new About());
        if(Greenfoot.mouseClicked(botonComoJugar))
            Greenfoot.setWorld(new How());
        if(Greenfoot.mouseClicked(botonMarcadores))
            Greenfoot.setWorld(new Marcadores(true)); //Se manda true para que se muestren los marcadores.
    }
    // /** Método que verificará que parte del menú se seleccionó.*/
    // private void seleccionMenu(World seleccion){
        // musica.stop(); //Detener la música. Aunque realmente solo es necesario al iniciar el juego. Así que mejor lo comentaré.
        // Greenfoot.setWorld(seleccion);
    // }
}
