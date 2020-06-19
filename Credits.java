import greenfoot.*; 

public class Credits extends World
{
    private Actor a1,a2,a3,a4,a5,a6,a7,a8;
    private Boton menu;
    private Etiqueta texto;
    public Credits()
    {    
        super(1000, 600, 1); 
        setBackground("images/espacio5.jpg");  
        //public Etiqueta(int tamañoFuente, Color colorFuente, Color colorFondo, Color bordeFuente);
        texto = new Etiqueta(30, Color.WHITE, null, null);
        //a1 = Boton.creaBoton(this, "VIDEOJUEGO REALIZADO PARA LA EVALUACIÓN DE LA APLICACIÓN ", getWidth()/2, getHeight() * 1/4+20,Color.WHITE, null, null, 30);
        getBackground().drawImage(texto.crearCuadroTexto("VIDEOJUEGO REALIZADO PARA LA EVALUACIÓN DE LA APLICACIÓN"), getWidth()/2 - texto.getXCentrada(), getHeight() * 1/4);
        //a2 = Boton.creaBoton(this, "DE LOS TEMAS VISTOS EN EL CURSO DE S2020-TOO.", getWidth()/2, getHeight() * 1/4 + 50,Color.WHITE, null, null, 30);
        getBackground().drawImage(texto.crearCuadroTexto("DE LOS TEMAS VISTOS EN EL CURSO DE S2020-TOO."), getWidth()/2 - texto.getXCentrada(), getHeight() * 1/4 + 25);
        //a3 = Boton.creaBoton(this, "REALIZADO POR:", getWidth()/2, getHeight() * 1/4 + 110,Color.WHITE, null, null, 30);
        getBackground().drawImage(texto.crearCuadroTexto("Realizado por:"), getWidth()/2 - texto.getXCentrada(), getHeight() * 1/4 + 75);
        //texto = new Etiqueta(30, Color.WHITE, null, null);
        //a4 = Boton.creaBoton(this, "- ESTEFANIA CAZARES ROBLEDO", getWidth()/2 - 50, getHeight() * 1/4 + 150,Color.WHITE, null, null, 30);        
        getBackground().drawImage(texto.crearCuadroTexto("- ESTEFANIA CAZARES ROBLEDO"), getWidth()/2 - texto.getXCentrada(), getHeight() * 1/4 + 100);
        //a5 = Boton.creaBoton(this, "- FRANCISCO JACOB FLORES RODRIGUEZ", getWidth()/2, getHeight() * 1/4 + 190,Color.WHITE, null, null, 30);
        getBackground().drawImage(texto.crearCuadroTexto("- FRANCISCO JACOB FLORES RODRIGUEZ"), getWidth()/2 - texto.getXCentrada(), getHeight() * 1/4 + 125);
        //a6 = Boton.creaBoton(this, "- LIZARAHI PADRON SANTOYO", getWidth()/2 - 70, getHeight() * 1/4 + 230,Color.WHITE, null, null, 30);
        getBackground().drawImage(texto.crearCuadroTexto("- LIZARAHI PADRON SANTOYO"), getWidth()/2 - texto.getXCentrada(), getHeight() * 1/4 + 150);
        //a7 = Boton.creaBoton(this, "- DANIEL LÓPEZ PADILLA", getWidth()/2 - 140, getHeight() * 1/4 + 270,Color.WHITE, null, null, 30);
        getBackground().drawImage(texto.crearCuadroTexto("- DANIEL LÓPEZ PADILLA"), getWidth()/2 - texto.getXCentrada(), getHeight() * 1/4 + 175);
        //a8 = Boton.creaBoton(this, "Regresar al menu", (getWidth()/2) -202, (getHeight()*2/3)+103, Color.GRAY, null, null, 30);
        //Boton.creaBotonSombra(_mundoActual_, _textoBoton_, _texto_, _x_, _y_, _colorFuente_, _colorFondo_, _bordeFuente_, _tamañoFuente_, _divisorLargo_)
        texto = new Etiqueta(30, Color.GRAY, null, null);
        menu = Boton.creaBotonSombra(this, "Regresar al menu", texto, getWidth()/2 - 200, (getHeight()*2/3)+100, Color.WHITE, null, null, 30, 2);
    }
    
    public void act()
    {
        if(Greenfoot.mouseClicked(menu))
            Greenfoot.setWorld(new Portada());
    }
}
