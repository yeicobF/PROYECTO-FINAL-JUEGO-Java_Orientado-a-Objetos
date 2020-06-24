import greenfoot.Greenfoot;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import greenfoot.World;
import greenfoot.GreenfootSound;
import greenfoot.Color;
import greenfoot.GreenfootImage; 
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
    protected static World mundoAnterior; //Para guardar el mundo en donde estábamos antes de ir a Siguiente página. Estático para que no se reestablezca.
    protected static long tiempoCambio; //El tiempo que ha transcurrido desde que se avanzó hacia adelante o atrás. Esto para que no detecte un click a los dos botones inmediatamente.
    private GreenfootImage fondo; //Para el fondo del menú.
    protected Boton volverMenu; //Botón para volver al menú del juego.
    protected Boton botonSiguiente; //Botón para avanzar a la siguiente "página".
    protected Boton botonAnterior; //Para volver si presionamos siguiente.
    protected Etiqueta texto; //Para crear los cuadros de texto.
    protected Archivo archivo; //Para mostrar los archivos de texto.
    private Boton botonIniciar;
    private Boton botonCreditos;
    private Boton botonAcercaDe;
    private Boton botonComoJugar;
    private Boton botonMarcadores;
    protected static GreenfootSound musica = new GreenfootSound("sounds/TitleScreen.mp3"); //Para reproducir la música de fondo.
    /**
     * Constructor for objects of class Menu.
     * 
     */
    /** Constructor que solo usarán las subclases y que recibirá true si se quiere crear
            un botón de siguiente para avanzar a la siguiente "pantalla", o false si no se requiere.*/
    protected Menu(boolean siguiente){
        /*siguiente = true -> crea botón de "siguiente".
           siguiente = false -> crea botón de "anterior". Esto se hará por ahora en sus respectivas clases,
                                                           para que no se cree en las demás pantallas sin requerirlo.
                                                           Habría que pensar en una mejor implementación.*/
        super(1000, 600, 1);
        setBackground("escenarios/espacio5.jpg");
        texto = new Etiqueta(30, Color.GRAY, null, null);
        volverMenu = Boton.creaBotonSombra(this, "Regresar al menu", texto, getWidth()/6, getHeight() - 50, Color.WHITE, null, null, 30, 2);
        if(siguiente){
            crearSiguiente();
            botonAnterior = null; //Inicializar el botón anterior como null.
        }
    }
    public Menu(){
        super(1000, 600, 1); 
        fondo = new GreenfootImage("escenarios/espacio2.jpg");
        fondo.scale(1000, 600);
        setBackground(fondo);
        if(GameOver.isMusicaReproduciendose()) //Si se está reproduciendo música de gameOver.
            GameOver.pararMusica(); //Detener la música de GameOver.
        if(Niveles.isMusicaReproduciendose())
            Niveles.pararMusica();
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
    }
    public void act(){
        musica.playLoop(); //Reproducir la canción en un loop.
        seleccionMenu();
    }
    /** Método que verá qué parte del menú se seleccionó.*/
    private void seleccionMenu(){
        if(Greenfoot.mouseClicked(botonIniciar))
            Greenfoot.setWorld(new SeleccionNave());
        if(Greenfoot.mouseClicked(botonCreditos))
            Greenfoot.setWorld(new Creditos());
        if(Greenfoot.mouseClicked(botonAcercaDe))
            Greenfoot.setWorld(new AcercaDe());
        if(Greenfoot.mouseClicked(botonComoJugar))
            Greenfoot.setWorld(new ComoJugar(false)); //false porque no es la pantalla siguiente, sino la inicial.
        if(Greenfoot.mouseClicked(botonMarcadores))
            Greenfoot.setWorld(new Marcadores(true)); //Se manda true para que se muestren los marcadores.
    }
    /** Método para volver al menú en los submenús.*/
    protected void volverMenu(){
        if(Greenfoot.mouseClicked(volverMenu))
             Greenfoot.setWorld(new Menu());
    }
    /** Método para crear un botón "siguiente".*/
    protected void crearSiguiente(){
        botonSiguiente = Boton.creaBotonSombra(this, "Siguiente", texto, getWidth()/6*5, getHeight() - 50, Color.WHITE, null, null, 30, 2);
    }
    /** Método para verificar si se presionó "siguiente para avanzar a la siguiente "página".*/
    protected boolean isSiguiente(){
        return Greenfoot.mouseClicked(botonSiguiente) && botonSiguiente != null; //El botón existe.
        //Si se presionó el botón, regresar true.
        //No se tocó el botón.
    }
    /** Método para crear un botón que regrese a la "página" anterior.*/
    protected void crearAnterior(){
        botonAnterior = Boton.creaBotonSombra(this, "Anterior", texto, getWidth()/6*5, getHeight() - 50, Color.WHITE, null, null, 30, 2);
    }
    /** Método que verifica si se presionó el botón "anterior" para volver.*/
    protected boolean isAnterior(){
        return Greenfoot.mouseClicked(botonAnterior) && botonAnterior != null; //El botón existe.
    }
}
