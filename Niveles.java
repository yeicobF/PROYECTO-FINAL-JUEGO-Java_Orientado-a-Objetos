import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Superclase que tendrá como subclases a cada uno de los niveles.
 *  La clase será abstracta porque no  necesitará instanciarse.
 *
 * @author (Jacob)
 * @version (Jueves, 4 de junio de 2020)
 */
public abstract class Niveles extends World
{

    /**
     * Constructor for objects of class Niveles.
     *
     */
    protected Roca roca; //Todos los nieles tendrán rocas (meteoros).
    //protected NaveAliada nave; //Porque en todos los niveles estará la nave.
    private static int nivelActual = 0;//Indica el nivel en que nos encontramos
    private static long tiempoDuracionJuego; //Definirá cuánto durará el nivel.
    protected long tiempoInicialMilis; //Tomar el tiempo en que el juego inició.
    protected long tiempoFinalJuego;
    // private boolean nivelCreado;

    //Constructor para crear el mundo del juego.
    // public Niveles(){
        // super(1000, 600, 1);
    // }
    //Constructor que, dependiendo del nivel asignará los valores necesarios.
    public Niveles()
    {
        //Ancho, alto, tamaño de pixel.
        super(1000, 600, 1); //Todos los escenarios tendrán estas dimensiones.
        //Establecer esto para que cada que se reinicie el nivel, se establezca de nuevo el tiempo en que la partida terminará
        tiempoFinalJuego = System.currentTimeMillis() + tiempoDuracionJuego;
        roca = new Roca(); //Inicializamos el número de rocas actual como el máximo
       /*Agregar la información desde este constructor, ya que serán igual para todos los niveles.*/
        //public MostrarInfo(int tipoInfo, int tamañoFuente, Color colorFuente, Color colorFondo, Color bordeFuente)
       addObject(new MostrarInfo(1, 30, Color.WHITE, Color.BLACK, null), 500, 50);//Vidas
       addObject(new MostrarInfo(2, 30, Color.WHITE, Color.BLACK, Color.RED), 500, 50+30);//Sumo 30 en y por el tamaño de la fuente anterior
       addObject(new MostrarInfo(3, 20, Color.WHITE, Color.BLACK, null), 50, getHeight()-20);
        //System.out.println(" - NIVELES -");
        // nivelCreado = false;

    }
    public static void crearNivel(int numNivel, NaveAliada nave){//, NaveAliada nave){
        //int numRocasMax;
        //nave = new NaveAliada();//Inicializar la nave después de haberle dado los valores en la selección
        switch(numNivel){
            case 1: //Nivel 1
                nivelActual = numNivel; //Indicar el nivel actual.
                tiempoDuracionJuego = 40000;
                //public Espacio(int tiempoFinalJuego, int tipoNaveAliada, int numRocasMax)
                //Instanciar roca con las rocas máximas y su ratio de aparición.
                //public Roca(int numRocasMax, int tiempoRegeneracion)
                //Establecer el máximo de rocas
                Roca.setNumRocasMax(15);
                Roca.setTiempoRegeneracion(10000);
                // System.out.println(" - NIVELES 1-");
                //
                // System.out.println(" - NIVELES 2-");
                Greenfoot.setWorld(new Espacio(nave));
                break;
        }
        // roca = new Roca(numRocasMax, 10000); //Esto no se puede porque después de instanciar el mundo no sale hasta moror.
    }

    // public void act(){
        // if(!nivelCreado){
            // nivelCreado = true;
            // Greenfoot.setWorld(new Espacio(System.currentTimeMillis() + 30000, 1, 10));
        // }
    // }
    //Método estático que devuelve el nivel actual
    public static int getNivelActual(){
        return nivelActual;
    }
    public static long getTiempoDuracionJuego(){
        return tiempoDuracionJuego;
    }
    /* - MÉTODOS QUE PODRÁN UTILIZAR TODOS LOS NIVELES*/

    /*Método en el que se creará un item cada cierto tiempo dependiendo de la última vez que se creó y que
     *  un tiempo generado aleatoriamente supere al tiempo mínimo en la coindición.*/
    protected void crearItemTiempo(){ //El item se creará mínimo cada 30 segundos después de haber hecho efecto por última vez
        int x, y, tipoItem;
        int velocidadItem = 1;//Cuando inicia el nivel iniciarla así y cuandi avance el tiempo cambiarla
        //Revisará que no exista el item para generar uno nuevo y que se respete el mínimo de tiempo. Se creará en un rango de 10 a 20 segundos.
        if(!Items.isItemActivo() && (System.currentTimeMillis() - Items.getTiempoFinalItem()) >= Aleatorio.getNumeroAleatorio(1000, 2000)){
            //Generar ancho y alto restándole su mitad a los límites para que no aparezca cortado en pantalla.
            x = Aleatorio.getNumeroAleatorio(Items.getAnchoItem()/2, super.getWidth()-Items.getAnchoItem()/2);
            //Recordar que y = 0 está en el centro de la pantalla
            /*No están funcionando los límites. (CREO QUE YA, estaba considerando que y iba de -MinY hasta MaxY, pero
             *      "y" es 0 hasta arriba del escenario y getHeight hasta abajo.)*/
            y = Aleatorio.getNumeroAleatorio(Items.getAltoItem(), super.getHeight()-Items.getAltoItem()/2);
            //System.out.println("x: "+ x+ "y: "+ y);
            if(NaveAliada.getVidasJugador() == 5)//Generará todos los items menos el corazón, ya que tiene el máximo de vidas.
                tipoItem = Aleatorio.getNumeroAleatorio(2, 2);
            else
                tipoItem = Aleatorio.getNumeroAleatorio(1, 2);//Elegirá de manera random el item que se creará.
            if(System.currentTimeMillis() - tiempoInicialMilis >= 5000){//Cada que pase 1 minuto, aumentará la velocidad del item.
                velocidadItem++;
                tiempoInicialMilis = System.currentTimeMillis();//Reiniciar el contador para que ocurra cada minuto.
            }
            addObject(new Items(tipoItem, velocidadItem), x, y);
        }
    }



}
