import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Write a description of class Espacio here.
 * 
 * @author (Team Naves) 
 * @version (Viernes, 29 de mayo de 2020)
 */
public class Espacio extends World
{
    /**
     * Constructor for objects of class Espacio.
     * 
     */
    MetodosGenerales m = new MetodosGenerales(); //Inicializar para tener los métodos no específicos.
    private long tiempoMilis = 0; // Para hacer comparaciones de tiempo para creación de objetos.
    private long tiempoInicialMilis = System.currentTimeMillis(); //Tomar el tiempo en que el juego inició.
    private int numRocasMax; //Número máximo de rocas.
    private static int numRocasActual; //Para saber si las rocas actuales son menos que las máximas.
    public Espacio(){
        super(1000, 600, 1);//CREAR EL ESCENARIO CON LAS MEDIDAS INDICADAS (ancho, alto, tamaño de pixel)
        numRocasMax = numRocasActual = 5; //Inicializamos el número de rocas actual como el máximo
        //Nave nave = new Nave(50, 300);//El constructor creará las n rocas y no el método, para así manejar las rocas y compararlas.
        //Modificamos la forma de instanciar a la nave aliada. Ahora le daremos el tipo de nave y su disparo.
        NaveAliada nave = new NaveAliada(1, 3);//Para empezar a utilizar la nave aliada con su propia clase, el tipo '9' lo puse por poner, pero representa el tipo de la mave
        NaveEnemiga boss = new NaveEnemiga(2, 1);//eL SPRITE 1 SERÁ EL BOSS
        NaveEnemiga enemigo = new NaveEnemiga(2, 2);
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
       addObject(new MostrarInfo(3, 20, Color.WHITE, Color.BLACK, null), 50, getHeight()-20);
       // addObject(new MostrarInfo(4, 20, Color.RED, Color.BLACK, Color.WHITE), getWidth()-200, 20);
       // MostrarVidas v = new MostrarVidas(nave.getVidasJugador());
        //addObject(enemigo, super.getWidth()/2+200, super.getHeight()/2-200);
    }
    
    /** Método que siempre se estará ejecutando en espacio. Aquí irán los métodos de creación de objetos, por ejemplo.*/
    public void act(){
        crearRocasTiempo(tiempoMilis);
        crearItemTiempo();
    }
    /*Método en el que se creará un item cada cierto tiempo dependiendo de la última vez que se creó y que 
     *  un tiempo generado aleatoriamente supere al tiempo mínimo en la coindición.*/
    public void crearItemTiempo(){ //El item se creará mínimo cada 30 segundos después de haber hecho efecto por última vez
        int x, y, tipoItem;
        int velocidadItem = 1;//Cuando inicia el nivel iniciarla así y cuandi avance el tiempo cambiarla
        //Revisará que no exista el item para generar uno nuevo y que se respete el mínimo de tiempo. Se creará en un rango de 10 a 20 segundos.
        if(!Items.isItemActivo() && (System.currentTimeMillis() - Items.getTiempoFinalItem()) >= m.getRandomNumber(1000, 2000)){
            //Generar ancho y alto restándole su mitad a los límites para que no aparezca cortado en pantalla.
            x = m.getRandomNumber(Items.getAnchoItem()/2, super.getWidth()-Items.getAnchoItem()/2);
            //Recordar que y = 0 está en el centro de la pantalla
            /*No están funcionando los límites. (CREO QUE YA, estaba considerando que y iba de -MinY hasta MaxY, pero
             *      "y" es 0 hasta arriba del escenario y getHeight hasta abajo.)*/
            y = m.getRandomNumber(Items.getAltoItem(), super.getHeight()-Items.getAltoItem()/2);
            //System.out.println("x: "+ x+ "y: "+ y);
            if(NaveAliada.getVidasJugador() == 5)//Generará todos los items menos el corazón, ya que tiene el máximo de vidas.
                tipoItem = m.getRandomNumber(2, 2);
            else
                tipoItem = m.getRandomNumber(1, 2);//Elegirá de manera random el item que se creará.
            if(System.currentTimeMillis() - tiempoInicialMilis >= 60000){//Cada que pase 1 minuto, aumentará la velocidad del item.
                velocidadItem++;
                tiempoInicialMilis = System.currentTimeMillis();//Reiniciar el contador para que ocurra cada minuto.
            }
            addObject(new Items(tipoItem, velocidadItem), x, y);
        }
    }
    /*Método que creará nuevas rocas si se han destruido y si ha pasado cierto tiempo.*/
    public void crearRocasTiempo(long tiempoMilis){ //Tiempo Milis es el último tiempo que se verificó desde la última roca creada.
        if((System.currentTimeMillis() - tiempoMilis)>= 10000 && numRocasActual < numRocasMax){
            crearRocas(1); //Se crean las rocas indicadas.
            numRocasActual ++; //Como se creó una roca más, se aumenta al número de rocas actual.
            tiempoMilis = System.currentTimeMillis();//Se almacena el tiempo de la última roca creada.
        }
    }
    /*Método para crear los meteoros en una posición random con un sprite random*/
    public void crearRocas(int numRocas){
        Roca r;
        int x, y, random;
        for(int i=0; i<numRocas; i++){
            random = m.getRandomNumber(1, 4);//Random para el sprite aleatorio del meteoro. Los sprites van del 1 al 4.
            r = new Roca(random);//Este parámetro indicará qué tipo de meteoro se generará
            x = Greenfoot.getRandomNumber(getWidth());//Ancho
            y = Greenfoot.getRandomNumber(getHeight());//Alto
            addObject(r, x, y);
        }
    }
    /*Método para obtener el número actual de rocas. Es estático porque su implementación también lo es.*/
    public static int getNumRocasActual(){
        return numRocasActual;
    }
    /*Setter estático para ser accedido sin instanciar y cambiar el número actual de rocas.*/
    public static void setNumRocasActual(int numRocasAct){
        numRocasActual = numRocasAct; //No se puede utilizar this. porque no se instancia el objeto por ser estático.
    }
}
