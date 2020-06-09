import greenfoot.*; 

public class Credits extends World
{
    private Actor a1,a2,a3,a4,a5,a6,a7,a8;
    private Actor exit;
    public Credits()
    {    
        super(800, 600, 1); 
        setBackground("images/espacio5.jpg");    
        a1 = Boton.creaBoton(this, "VIDEOJUEGO REALIZADO PARA LA EVALUACIÓN DE LA APLICACIÓN ", getWidth()/2, getHeight() * 1/4+20,Color.WHITE, null, null, 30);
        a2 = Boton.creaBoton(this, "DE LOS TEMAS VISTOS EN EL CURSO DE S2020-TOO.", getWidth()/2, getHeight() * 1/4 + 50,Color.WHITE, null, null, 30);
        a3 = Boton.creaBoton(this, "REALIZADO POR:", getWidth()/2, getHeight() * 1/4 + 110,Color.WHITE, null, null, 30);
        a4 = Boton.creaBoton(this, "- ESTEFANIA CAZARES ROBLEDO", getWidth()/2 - 50, getHeight() * 1/4 + 150,Color.WHITE, null, null, 30);        
        a5 = Boton.creaBoton(this, "- FRANCISCO JACOB FLORES RODRIGUEZ", getWidth()/2, getHeight() * 1/4 + 190,Color.WHITE, null, null, 30);
        a6 = Boton.creaBoton(this, "- LIZARAHI PADRON SANTOYO", getWidth()/2 - 70, getHeight() * 1/4 + 230,Color.WHITE, null, null, 30);
        a7 = Boton.creaBoton(this, "- DANIEL LÓPEZ PADILLA", getWidth()/2 - 140, getHeight() * 1/4 + 270,Color.WHITE, null, null, 30);
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
