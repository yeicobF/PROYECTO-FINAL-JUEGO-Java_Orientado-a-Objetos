import greenfoot.Actor;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import greenfoot.World;
import greenfoot.Color;
import greenfoot.Greenfoot; //Para crear el mundo de la pantalla siguiente.
/**
 * Clase en donde se mostrarán las instrucciones de cómo jugar.
 *  Antes llamada How
 * 
 * @author (Team Naves) 
 * @version (Viernes, 19 de junio de 2020)
 */
public class ComoJugar extends Menu
{
    
    /**
     * Constructor for objects of class ComoJugar.
     * 
     */
    public ComoJugar(boolean siguiente){ //siguiente revisa si se avanzó a la siguiente página o no.
        super(!siguiente); //Si se avanzó a la página siguiente, siguiente = true, entonces enviar false para no crear el botón.
        // a1 = Boton.creaBoton(this, "Para moverte necesitaras las teclas WASD: W (Arriba), A (Izquierda),", getWidth()/2, getHeight() * 1/4-50,Color.WHITE, null, null, 30);
        // a2 = Boton.creaBoton(this, "S (Abajo) y D (Derecha). Usaras la barra de espacio para disparar.", getWidth()/2 -16, getHeight() * 1/4-20,Color.WHITE, null, null, 30);
        // a3 = Boton.creaBoton(this, "Tienes 3 vidas, cada vez que un meteorito o el enemigo te dañe", getWidth()/2 -30, getHeight() * 1/4 + 10,Color.WHITE, null, null, 30);
        // a4 = Boton.creaBoton(this, "tu contador bajará; perderás si te quedas sin vidas.", getWidth()/2 - 92, getHeight() * 1/4 + 40,Color.WHITE, null, null, 30);
        // a5 = Boton.creaBoton(this, "Debes pasar 3 niveles para ganar, cada nivel es diferente. Para pasar", getWidth()/2+4, getHeight() * 1/4 + 70,Color.WHITE, null, null, 30);
        // a6 = Boton.creaBoton(this, "de nivel debes estar cierto tiempo jugando sin morir.", getWidth()/2 - 87, getHeight() * 1/4 + 100,Color.WHITE, null, null, 30);
        // a7 = Boton.creaBoton(this, "Aleatoriamente aparecerán items, cada uno proporciona diferentes", getWidth()/2-8, getHeight() * 1/4 + 130,Color.WHITE, null, null, 30);
        // a7 = Boton.creaBoton(this, "habilidades:", getWidth()/2-307, getHeight() * 1/4 + 160,Color.WHITE, null, null, 30);
        // a7 = Boton.creaBoton(this, "- Vida: Te aumenta un punto de vida.", getWidth()/2-175, getHeight() * 1/4 + 190,Color.WHITE, null, null, 30);
        // a7 = Boton.creaBoton(this, "- Escudo: Te vulves inmune por unos segundos.", getWidth()/2-110, getHeight() * 1/4 + 220,Color.WHITE, null, null, 30);
        // a7 = Boton.creaBoton(this, "- Aliado: Una nave aliada te ayuda por un momento.", getWidth()/2-92, getHeight() * 1/4 + 250,Color.WHITE, null, null, 30);
        // a7 = Boton.creaBoton(this, "- Daño: Tendrás un mejor daño de ataque temporalmente.", getWidth()/2-60, getHeight() * 1/4 + 280,Color.WHITE, null, null, 30);
        /*public Archivo(String nombreArchivo, int tamañoFuente, Color colorFuente)*/
        if(!siguiente) //No se ha avanzado de página
            archivo = new Archivo("archivos/comoJugar1.txt", 30, Color.WHITE);
        else{
            crearAnterior(); //Crear botón para volver.
            archivo = new Archivo("archivos/comoJugar2.txt", 30, Color.WHITE);
        }
        archivo.mostrarArchivo(this);
    }

    public void act(){
        if(isSiguiente()){
            // //removeObject(botonSiguiente);
            // //super(false); //Crear mundo sin botón de siguiente.
            // archivo.mostrarArchivo(this);
            System.out.println("Presionó siguiente.");
            mundoAnterior = this; //Guardar el mundo anterior. Esto no deja hacer el siguiente paso.
            Greenfoot.setWorld(new Menu()); //true porque se avanzó al siguiente.
        }
        if(isAnterior()) //Se presionó el botón de anterior. Restablecer el mundo pasado.
            Greenfoot.setWorld(mundoAnterior);
        volverMenu();
    }
}