import greenfoot.*;
public class Portada extends World
{
    private Actor startButton;
    private Actor creditsButton;
    private Actor aboutButton;
    private Actor howButton;
    private Actor a1,a2,a3,a4;
    
    private GreenfootSound bkgMusic;
    
    public Portada()
    { 
        super(1000, 600, 1);
        /* public static Actor creaBoton(World mundoActual, String texto, int x, int y, 
                    Color colorFuente, Color colorFondo, Color bordeFuente, int tamañoFuente)*/
        setBackground("images/espacio5.jpg");
        a1 = Boton.creaBoton(this, "Iniciar", getWidth()/2 - 2, getHeight() * 1/3 + 3,Color.GRAY, null, null, 30);
        startButton = Boton.creaBoton(this, "Iniciar", getWidth()/2, getHeight() * 1/3,Color.WHITE, null, null, 30);
        a2 = Boton.creaBoton(this, "Créditos", getWidth()/2 - 2, getHeight() * 1/3 + 53,Color.GRAY, null, null, 30);
        creditsButton = Boton.creaBoton(this, "Créditos", getWidth()/2, (getHeight() * 1/3) + 50, Color.WHITE, null, null, 30);
        a3 = Boton.creaBoton(this, "¿Cómo jugar?", getWidth()/2 - 2, getHeight() * 1/3 + 103,Color.GRAY, null, null, 30);
        howButton = Boton.creaBoton(this, "¿Cómo jugar?", getWidth()/2, (getHeight() * 1/3) + 100, Color.WHITE, null, null, 30);
        a4 = Boton.creaBoton(this, "Acerca de", getWidth()/2 - 2, getHeight() * 1/3 + 153,Color.GRAY, null, null, 30);
        aboutButton = Boton.creaBoton(this, "Acerca de", getWidth()/2, (getHeight() * 1/3) + 150, Color.WHITE, null, null, 30);

        if(bkgMusic != null)
            bkgMusic.stop();
        
        bkgMusic = new GreenfootSound("sounds/TitleScreen.mp3");
    }

    public void act()
    {
        if(Greenfoot.mouseClicked(startButton))
        {
            bkgMusic.stop();
            //Mode world = new Mode();
            //Niveles nivel = new Niveles();
            NaveAliada.setDiseñoNaveAliada(1);
            NaveAliada.setTipoDisparo(1);
            Niveles.crearNivel(1);
        }
        else if( Greenfoot.mouseClicked(creditsButton))
        {
            Credits world = new Credits();
            Greenfoot.setWorld(world);
        }
        else if(Greenfoot.mouseClicked(aboutButton))
        {
            About world = new About();
            Greenfoot.setWorld(world);
        }
        else if(Greenfoot.mouseClicked(howButton))
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
