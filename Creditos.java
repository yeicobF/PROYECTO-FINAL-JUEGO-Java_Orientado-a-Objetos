import greenfoot.World;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import greenfoot.Actor;
import greenfoot.Color;
/**
 * Clase que mostrará los créditos del juego.
 *  Esta clase antes se llamaba Credits.
 * 
 * @author (Team Naves) 
 * @version (Viernes, 19 de junio de 2020)
 */
public class Creditos extends Menu
{
    private Actor a1,a2,a3,a4,a5,a6,a7,a8;
    public Creditos()
    {    
        super(false);
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
        }
    
    public void act(){
        volverMenu();
    }
}
