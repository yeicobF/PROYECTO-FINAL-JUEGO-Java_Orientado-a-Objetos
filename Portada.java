import greenfoot.*;
public class Portada extends World
{
    private Etiqueta texto; //Para crear los cuadros de texto.
    private Boton botonIniciar;
    private Boton botonCreditos;
    private Boton botonAcercaDe;
    private Boton botonComoJugar;
    private Boton botonMarcadores;
    private GreenfootSound bkgMusic;

    public Portada()
    {
        super(1000, 600, 1);
        /* public static Actor creaBoton(World mundoActual, String texto, int x, int y,
                    Color colorFuente, Color colorFondo, Color bordeFuente, int tamañoFuente)*/
        setBackground("images/espacio5.jpg");
        /* Crear cuadros de texto no necesariamente interactuables:
            -> public Etiqueta(int tamañoFuente, Color colorFuente, Color colorFondo, Color bordeFuente)
            -> public GreenfootImage crearCuadroTexto(String s) <- Recibir los cuadros de texto como imagen.*/
        texto = new Etiqueta(50, Color.GRAY, null, null); //Inicializar cuadro de texto que simulará la sombra de los botones.
        /*public static Boton creaBotonSombra(World mundoActual, String textoBoton, Etiqueta texto, int x, int y,
                    Color colorFuente, Color colorFondo, Color bordeFuente, int tamañoFuente, int divisorLargo)*/
        botonIniciar = Boton.creaBotonSombra(this, "Iniciar", texto, getWidth()/2, getHeight()/8, Color.WHITE, null, null, 50, 20);
        botonComoJugar = Boton.creaBotonSombra(this, "¿Cómo Jugar?", texto, getWidth()/2, getHeight()/8*3, Color.WHITE, null, null, 50, 6);
        botonAcercaDe = Boton.creaBotonSombra(this, "Acerca de", texto, getWidth()/2, getHeight()/8*5, Color.WHITE, null, null, 50, 20);
        botonCreditos = Boton.creaBotonSombra(this, "Créditos", texto, getWidth()/2, getHeight()/8*7, Color.WHITE, null, null, 50, 5);
        
        if(bkgMusic != null)
            bkgMusic.stop();

        bkgMusic = new GreenfootSound("sounds/TitleScreen.mp3");
    }

    public void act()
    {
        if(Greenfoot.mouseClicked(botonIniciar))
        {
            bkgMusic.stop();
            //Mode world = new Mode();
            //Niveles nivel = new Niveles();
            NaveAliada.setDiseñoNaveAliada(1);
            NaveAliada.setTipoDisparo(1);
            // Greenfoot.setWorld(new Intro());
            Greenfoot.setWorld(new Niveles(1));
        }
        else if( Greenfoot.mouseClicked(botonCreditos))
        {
            Credits world = new Credits();
            Greenfoot.setWorld(world);
        }
        else if(Greenfoot.mouseClicked(botonAcercaDe))
        {
            About world = new About();
            Greenfoot.setWorld(world);
        }
        else if(Greenfoot.mouseClicked(botonComoJugar))
        {
            How world = new How();
            Greenfoot.setWorld(world);
        }

    }

    @Override
    public void started()
    {
        bkgMusic.playLoop();
    }

    @Override
    public void stopped()
    {
        bkgMusic.pause();
    }
}
