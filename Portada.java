import greenfoot.*;
public class Portada extends World
{
    private Etiqueta texto; //Para crear los cuadros de texto.
    private Actor botonIniciar;
    private Actor botonCreditos;
    private Actor botonAcercaDe;
    private Actor botonComoJugar;
    private Actor a1,a2,a3,a4;

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
        texto = new Etiqueta(30, Color.GRAY, null, null); //Inicializar cuadro de texto que simulará la sombra de los botones.

        botonIniciar = Boton.creaBoton(this, "Iniciar", getWidth()/2, getHeight() * 1/3, Color.WHITE, null, null, 30);
        getBackground().drawImage(texto.crearCuadroTexto("Iniciar"), getWidth()/2 - texto.getProporcionTextoImagen()/2, getHeight() * 1/3 + 3); //Sombra de botón Iniciar

        botonComoJugar = Boton.creaBoton(this, "¿Cómo jugar?", getWidth()/2, (getHeight() * 1/3) + 50, Color.WHITE, null, null, 30);
        getBackground().drawImage(texto.crearCuadroTexto("¿Cómo jugar?"), getWidth()/2 - 2, getHeight() * 1/3 + 53); //Sombra de botón Iniciar

        botonAcercaDe = Boton.creaBoton(this, "Acerca de", getWidth()/2, (getHeight() * 1/3) + 100, Color.WHITE, null, null, 30);
        getBackground().drawImage(texto.crearCuadroTexto("Acerca de"), getWidth()/2, (getHeight() * 1/3) + 103); //Sombra de botón Iniciar

        botonCreditos = Boton.creaBoton(this, "Créditos", getWidth()/2 - 2, getHeight() * 1/3 + 150, Color.WHITE, null, null, 30);
        getBackground().drawImage(texto.crearCuadroTexto("Créditos"), getWidth()/2 - 2, getHeight() * 1/3 + 153); //Sombra de botón Iniciar.

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
