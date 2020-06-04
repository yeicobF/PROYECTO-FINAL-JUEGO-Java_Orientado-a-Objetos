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
    protected MetodosGenerales m = new MetodosGenerales(); //Instanciar para tener los métodos no específicos.
    private static int nivelActual = 0;//Indica el nivel en que nos encontramos
    protected long tiempoMilis = 0; // Para hacer comparaciones de tiempo para creación de objetos.
    protected long tiempoInicialMilis = System.currentTimeMillis(); //Tomar el tiempo en que el juego inició.
    protected int numRocasMax; //Número máximo de rocas.
    protected static int numRocasActual; //Para saber si las rocas actuales son menos que las máximas.
    //Aquí utilizaremos el mostrarInfo(tipo = niveles) y los demás también podrían ser.
    public Niveles()
    {
        //Ancho, alto, tamaño de pixel.
        super(1000, 600, 1); //Todos los escenarios tendrán estas dimensiones.
    }
    //Método estático que devuelve el nivel actual
    public static int getNivelActual(){
        return nivelActual;
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
    /*Método que creará nuevas rocas si se han destruido y si ha pasado cierto tiempo.*/
    protected void crearRocasTiempo(long tiempoMilis){ //Tiempo Milis es el último tiempo que se verificó desde la última roca creada.
        if((System.currentTimeMillis() - tiempoMilis) >= 20000 && numRocasActual < numRocasMax){
            crearRocas(1); //Se crean las rocas indicadas.
            numRocasActual ++; //Como se creó una roca más, se aumenta al número de rocas actual.
            tiempoMilis = System.currentTimeMillis();//Se almacena el tiempo de la última roca creada.
        }
    }
    /*Método para crear los meteoros en una posición random con un sprite random*/
    protected void crearRocas(int numRocas){
        Roca r;
        int x, y, random;
        for(int i=0; i<numRocas; i++){
            random = Aleatorio.getNumeroAleatorio(1, 4);//Random para el sprite aleatorio del meteoro. Los sprites van del 1 al 4.
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
