import greenfoot.*;

public class How extends World
{
    private Actor a1,a2,a3,a4,a5,a6,a7,a8,a9,a10,a11,a12,a13,a14,a15;
    private Actor exit;
    public How()
    {
        super(800, 600, 1);
        setBackground("images/espacio5.jpg");
        a1 = Boton.creaBoton(this, "Para moverte necesitaras las teclas WASD: W (Arriba), A (Izquierda),", getWidth()/2, getHeight() * 1/4-50,Color.WHITE, null, null, 30);
        a2 = Boton.creaBoton(this, "S (Abajo) y D (Derecha). Usaras la barra de espacio para disparar.", getWidth()/2 -16, getHeight() * 1/4-20,Color.WHITE, null, null, 30);
        a3 = Boton.creaBoton(this, "Tienes 3 vidas, cada vez que un meteorito o el enemigo te dañe", getWidth()/2 -30, getHeight() * 1/4 + 10,Color.WHITE, null, null, 30);
        a4 = Boton.creaBoton(this, "tu contador bajará; perderás si te quedas sin vidas.", getWidth()/2 - 92, getHeight() * 1/4 + 40,Color.WHITE, null, null, 30);
        a5 = Boton.creaBoton(this, "Debes pasar 3 niveles para ganar, cada nivel es diferente. Para pasar", getWidth()/2+4, getHeight() * 1/4 + 70,Color.WHITE, null, null, 30);
        a6 = Boton.creaBoton(this, "de nivel debes estar cierto tiempo jugando sin morir.", getWidth()/2 - 87, getHeight() * 1/4 + 100,Color.WHITE, null, null, 30);
        a7 = Boton.creaBoton(this, "Aleatoriamente aparecerán items, cada uno proporciona diferentes", getWidth()/2-8, getHeight() * 1/4 + 130,Color.WHITE, null, null, 30);
        a7 = Boton.creaBoton(this, "habilidades:", getWidth()/2-307, getHeight() * 1/4 + 160,Color.WHITE, null, null, 30);
        a7 = Boton.creaBoton(this, "- Vida: Te aumenta un punto de vida.", getWidth()/2-175, getHeight() * 1/4 + 190,Color.WHITE, null, null, 30);
        a7 = Boton.creaBoton(this, "- Escudo: Te vulves inmune por unos segundos.", getWidth()/2-110, getHeight() * 1/4 + 220,Color.WHITE, null, null, 30);
        a7 = Boton.creaBoton(this, "- Aliado: Una nave aliada te ayuda por un momento.", getWidth()/2-92, getHeight() * 1/4 + 250,Color.WHITE, null, null, 30);
        a7 = Boton.creaBoton(this, "- Daño: Tendrás un mejor daño de ataque temporalmente.", getWidth()/2-60, getHeight() * 1/4 + 280,Color.WHITE, null, null, 30);
        a8 = Boton.creaBoton(this, "Regresar al menu", (getWidth()/2) -202, (getHeight()*2/3)+103, Color.GRAY, null, null, 30);
        exit = Boton.creaBoton(this, "Regresar al menu", (getWidth()/2) -200, (getHeight()*2/3)+100, Color.WHITE, null, null, 30);
        prepare();
    }

    public void act()
    {
        if(Greenfoot.mouseClicked(exit) )
        {
            Portada world = new Portada();
            Greenfoot.setWorld(world);
        }
    }

    public void prepare()
    {
    }
}

