import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Write a description of class Espacio here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Espacio extends Niveles
{

    /**
     * Constructor for objects of class Espacio.
     * 
     */
<<<<<<< Updated upstream
    public Espacio()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 600, 1);
        int numRocas=15, cont1=0, cont2;
        Nave nave = new Nave();//El constructor creará las n rocas y no el método, para así manejar las rocas y compararlas.
        NaveEnemiga malo = new NaveEnemiga();
        //Roca[] r = new Roca[numRocas];//Se hará un arreglo de rocas para luego eliminarlas si chocan
        addObject(nave,300,300);
        addObject(malo,100,100);
        //crearRocas(numRocas, r);
        //while(numRocas>0){ Esto deberá ir en la misma clase de Rocas, porque aquí sólo se crea el escenario y ya.
            //for(cont1=0; cont1<15; cont1++)
                ////for(cont2=0; cont2<15; cont2++)
                   // if(r[cont1]!=null && r[cont2]!=null && !r[cont1].chocanRocas(r[cont2].getCoordX(), r[cont2].getCoordY())){
                       // r[cont1]=r[cont2]=null;//Eliminar los objetos que chocaron
                       // numRocas-=2;//Descontar las rocas que han sido eliminadas
                    //}
       // }
    }
    

=======
    
    
    public Espacio(){
        super();
        numRocasMax = numRocasActual = 5; //Inicializamos el número de rocas actual como el máximo
        //Nave nave = new Nave(50, 300);//El constructor creará las n rocas y no el método, para así manejar las rocas y compararlas.
        //Modificamos la forma de instanciar a la nave aliada. Ahora le daremos el tipo de nave y su disparo.
        NaveAliada nave = new NaveAliada(1, 3);//Para empezar a utilizar la nave aliada con su propia clase, el tipo '9' lo puse por poner, pero representa el tipo de la mave
        //Como NaveEnemiga.puntosSalud es estático, entonces el último que se instancie dará el valor de los PS. Quitarles lo estático.
        NaveEnemiga enemigo = new NaveEnemiga(2, 2);
        NaveEnemiga boss = new NaveEnemiga(1, 2);//eL SPRITE 1 SERÁ EL BOSS
        
        addObject(nave,300,300);//Aparecer a la nave en el centro
        addObject(boss, super.getWidth()/2+40, super.getHeight()/2+40);//Utilizo el super, ya que esta clase hereda de World y ahí se encuentran esos métodos
        addObject(enemigo, super.getWidth()/2+40, super.getHeight()/2-40);
        crearRocas(numRocasMax); //Crear las rocas primero. Luego que se vayan eliminando se crearán con el tiempo.
        //MostrarVidas cuadroVidas = new MostrarVidas();
        /*Agrega el objeto que muestra las vidas. Como la imagen de este serán las vidas que se actualizan constantemente,
           se agrega como objeto con las coordenadas deseadas.
           Hacer lo mismo para mostrar los puntos, incluso podría ser en la misma clase.
            - MostrarEstado tal vez.*/
        //public MostrarInfo(int tipoInfo, int tamañoFuente, Color colorFuente, Color colorFondo, Color bordeFuente)
       addObject(new MostrarInfo(1, 30, Color.WHITE, Color.BLACK, null), 500, 50);//Vidas
       addObject(new MostrarInfo(2, 30, Color.WHITE, Color.BLACK, Color.RED), 500, 50+30);//Sumo 30 en y por el tamaño de la fuente anterior
       // addObject(new MostrarInfo(3, 20, Color.WHITE, Color.BLACK, null), 50, getHeight()-20);
       // addObject(new MostrarInfo(4, 20, Color.RED, Color.BLACK, Color.WHITE), getWidth()-200, 20);
       // MostrarVidas v = new MostrarVidas(nave.getVidasJugador());
        //addObject(enemigo, super.getWidth()/2+200, super.getHeight()/2-200);
    }
    
    /** Método que siempre se estará ejecutando en espacio. Aquí irán los métodos de creación de objetos, por ejemplo.*/
    public void act(){
        crearRocasTiempo(tiempoMilis);
        crearItemTiempo();
        //System.out.println("Salud aliado: "+ NaveAliada.getPuntosSalud() +"Salud enemigo: "+ NaveEnemiga.getPuntosSalud()) ;
    }
    
>>>>>>> Stashed changes
}
