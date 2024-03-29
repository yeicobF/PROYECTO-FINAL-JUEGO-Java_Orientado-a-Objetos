import greenfoot.World;
import greenfoot.GreenfootSound;
import greenfoot.GreenfootImage;
import greenfoot.Color;
import greenfoot.Greenfoot;
/**
 * Superclase que tendrá como subclases a cada uno de los niveles.
 *  La clase será abstracta porque no  necesitará instanciarse.
 *
 * @author (Jacob)
 * @version (Lunes, 22 de junio de 2020)
 */
public class Niveles extends World
{
    protected Roca roca; //Todos los nieles tendrán rocas (meteoros).
    protected NaveAliada nave;
    protected long tiempoInicialMilis; //Tomar el tiempo en que el juego inició.
    protected long tiempoFinalJuego;
    //Inicializar con la canción para que se pueda verificar desde el menú si hay música reproduciéndose.
    private static GreenfootSound musicaNivel = new GreenfootSound("Nivel1.mp3"); /* Para reproducir la música de cada nivel. Estática para detener la del nivel anterior y así.*/
    private static boolean pasarNivel = false; //Booleano que indica si se pasó de nivel. Esto servirá para establecer los puntos iniciales solo al pasar
    private static int nivelActual = 0;//Indica el nivel en que nos encontramos
    private static long tiempoDuracionJuego; //Definirá cuánto durará el nivel.
    private static long tiempoRestante; //El tiempo restante del nivel.
    private GreenfootImage fondo; //Para el fondo de pantalla de cada nivel.
    private int tipoItem;
    /** Constructor de Niveles que, dependiendo del nivel asignará los valores necesarios.*/
    public Niveles(int numNivel){
        //Ancho, alto, tamaño de pixel.
        super(1000, 600, 1); //Todos los escenarios tendrán estas dimensiones.
        NaveAliada.setVidas(NaveAliada.getVidasJugador());
        nave = new NaveAliada();
        crearNivel(numNivel);
        tiempoInicialMilis = System.currentTimeMillis();
        Items.setItemActivoFalso();
        Pausa.creaBotonPausa(this, getWidth(), 0); //Crear el botón de pausa en el escenario.
        //Establecer esto para que cada que se reinicie el nivel, se establezca de nuevo el tiempo en que la partida terminará
        tiempoFinalJuego = System.currentTimeMillis() + tiempoDuracionJuego;
        roca = new Roca(); //Inicializamos el número de rocas actual como el máximo
       /*Agregar la información desde este constructor, ya que serán igual para todos los niveles.*/
        //public MostrarInfo(int tipoInfo, int tamañoFuente, Color colorFuente, Color colorFondo, Color bordeFuente)
       addObject(new MostrarInfo(1, 30, Color.WHITE, Color.BLACK, null), 500, 50);//Vidas
       addObject(new MostrarInfo(2, 30, Color.WHITE, Color.BLACK, Color.RED), 500, 50+30);//Sumo 30 en y por el tamaño de la fuente anterior
       addObject(new MostrarInfo(3, 20, Color.WHITE, Color.BLACK, null), 50, getHeight()-20);
       addObject(new MostrarInfo(4, 20, Color.WHITE, Color.RED, null), getWidth() - 150, getHeight()-20);
       //Crear las rocas que tendrá el nivel.
       roca.crearRocas(Roca.getNumRocasMax(), this); //Crear las rocas primero. Luego que se vayan eliminando se crearán con el tiempo.
    }
    public void act(){
        tiempoRestante = tiempoFinalJuego - System.currentTimeMillis(); //El tiempo que le esta a la partida.
        musicaNivel.playLoop(); //Reproducir la música en Loop.
        /*Pausa: Este método revisa si se activó la pausa.*/
        if(Pausa.isPausa()) //Si se pausa, mostrar el menú.
            Greenfoot.setWorld(new Pausa(this, getBackground()));
        /* Se terminó el tiempo.*/
        if(isTiempoFin()){
            musicaNivel.stop(); //Parar la música de nivel al terminarlo.
            nivelActual ++; //Avanzar de nivel.
            if(nivelActual <= 3){ //Si el nivel a crear es menor al 3, que es el máximo.
                pasarNivel = true; //Indicar que se pasó de nivel para establecer los puntos iniciales.
                Greenfoot.setWorld(new Intro(nivelActual)); //Avanzar de nivel creando primero la intro.
            }
            else //Si númeroActual == 4 significa que el juego terminó.
                Greenfoot.setWorld(new GameOver(nave)); //Poner la pantalla de Game Over. Se manda la nave para tener las stats.
        }
          //public void crearRocasTiempo(World mundoActual)
        roca.crearRocasTiempo(this); /* No funciona. Creo que debería ser estático. (Miércoles, 23 de junio de 2020).*/
        crearItemTiempo(nave);
    }
    /** Método que crea el nivel que recibió por parámetro.*/
    public void crearNivel(int numNivel){
        if(musicaNivel.isPlaying()) //Si hay música del nivel reproduciéndose, pararla.
            musicaNivel.stop(); 
        if(pasarNivel)
            NaveAliada.setVidas(3); //Al iniciar cada nivel, dar 3 vidas.
        nivelActual = numNivel; //Indicar el nivel actual.
        addObject(nave, getWidth()/2-NaveAliada.getAnchoImagen()/2, getHeight()/2+NaveAliada.getAltoImagen()/2);//Aparecer a la nave en el centro
        musicaNivel = new GreenfootSound("Nivel"+ numNivel +".mp3"); //Se pondrá la canción dependiendo del número del nivel.
        if(pasarNivel && numNivel > 1){
            NaveAliada.setPuntosIniciales(NaveAliada.getPuntos());
            pasarNivel = false;
        }
        if(Pausa.isJuegoReinicio()){
            Pausa.setReinicioFalse();
            nave.setVidas(3);
        }
        /* public NaveEnemiga(int tipoEnemigo, int tipoDisparo)*/
        switch(numNivel){
            case 1: //Nivel 1
                setBackground("escenarios/espacio1.jpeg");
                NaveAliada.setPuntosIniciales(0); //En el primer nivel siempre apareceremos con 0 puntos.
                NaveAliada.setPuntos(NaveAliada.getPuntosIniciales());
                tiempoDuracionJuego = 30000;
                //Establecer el máximo de rocas
                Roca.setNumRocasMax(5);
                Roca.setTiempoRegeneracion(700); //Instanciar roca con las rocas máximas y su ratio de aparición.
                //public NaveEnemiga(int tipoEnemigo, int tipoDisparo)
                addObject(new NaveEnemiga(2, 2), getWidth()/2+getWidth()/4, getHeight()/2-getHeight()/4);//Utilizo el super, ya que esta clase hereda de World y ahí se encuentran esos métodos
                addObject(new NaveEnemiga(1, 2), getWidth()/2+getWidth()/4, getHeight()/2+getHeight()/4);
                addObject(new NaveEnemiga(1, 2), getWidth()/2, 0);
                break;
            case 2:
                tiempoDuracionJuego = 60000;
                NaveAliada.setPuntos(NaveAliada.getPuntosIniciales());
                fondo = new GreenfootImage("escenarios/espacio9.png");
                fondo.scale(1000, 600);
                setBackground(fondo);
                Roca.setNumRocasMax(10);
                Roca.setTiempoRegeneracion(600);
                addObject(new NaveEnemiga(1, 2), getWidth()/2+getWidth()/4, getHeight()/2+getHeight()/4);
                addObject(new NaveEnemiga(1, 2), getWidth()/2-getWidth()/4, getHeight()/2-getHeight()/4);
                addObject(new NaveEnemiga(2, 2), getWidth()/2, 0);
                addObject(new NaveEnemiga(3, 2), getWidth()/2, getHeight());
                break;
            case 3:
                tiempoDuracionJuego = 60000;
                NaveAliada.setPuntos(NaveAliada.getPuntosIniciales());
                fondo = new GreenfootImage("escenarios/espacio11.png");
                fondo.scale(1000, 600);
                setBackground(fondo);
                Roca.setNumRocasMax(12);
                Roca.setTiempoRegeneracion(450);
                addObject(new NaveEnemiga(3, 2), getWidth()/4, getHeight()/2+getHeight()/4);
                addObject(new NaveEnemiga(3, 2), getWidth()/4, getHeight()/2-getHeight()/4);
                addObject(new NaveEnemiga(3, 2), getWidth()/4, getHeight()/2+getHeight()/4);
                addObject(new NaveEnemiga(3, 2), getWidth()/4, getHeight()/2-getHeight()/4);
                addObject(new NaveEnemiga(2, 2), 0, 0);
                addObject(new NaveEnemiga(2, 2), getWidth(), 0);
                addObject(new NaveEnemiga(1, 2), getWidth(), getHeight());
                addObject(new NaveEnemiga(1, 2), 0, getHeight());
                break;
        }
        tiempoRestante = tiempoDuracionJuego;
    }
    /** Método que verifica si la música del nivel se está reproduciendo.*/
    public static boolean isMusicaReproduciendose(){
        return musicaNivel.isPlaying();
    }
    /** Método que detiene la canción que se esté reproduciendo del nivel.*/
    public static void pararMusica(){
        musicaNivel.stop();
    }
    /** Métdo que verifica si se termino el tiempo establecido en el nivel*/
    private boolean isTiempoFin(){
        return System.currentTimeMillis() - tiempoFinalJuego >= 0;
    }
    /** Método estático que devuelve el nivel actual.*/
    public static int getNivelActual(){
        return nivelActual;
    }
    public static long getTiempoDuracionJuego(){
        return tiempoDuracionJuego;
    }
    public static long getTiempoRestanteNivel(){
        return tiempoRestante;
    }
    /** Método en el que se creará un item cada cierto tiempo dependiendo de la última vez que se creó y que
          un tiempo generado aleatoriamente supere al tiempo mínimo en la coindición.*/
    private void crearItemTiempo(NaveAliada nave){ //El item se creará mínimo cada 30 segundos después de haber hecho efecto por última vez
        int x, y, aleatorio;
        int velocidadItem = 1;//Cuando inicia el nivel iniciarla así y cuando avance el tiempo cambiarla
        tipoItem = 0;
        //Revisará que no exista el item para generar uno nuevo y que se respete el mínimo de tiempo. Se creará en un rango de 1 a 5 segundos.
        if(!Items.isItemActivo() && (System.currentTimeMillis() - Items.getTiempoFinalItem()) >= Aleatorio.getNumeroAleatorio(1000, 5000)){
            //Generar ancho y alto restándole su mitad a los límites para que no aparezca cortado en pantalla.
            x = Aleatorio.getNumeroAleatorio(Items.getAnchoItem()/2, super.getWidth()-Items.getAnchoItem()/2);
            /*No están funcionando los límites. (CREO QUE YA, estaba considerando que y iba de -MinY hasta MaxY, pero
             *      "y" es 0 hasta arriba del escenario y getHeight hasta abajo.)*/
            y = Aleatorio.getNumeroAleatorio(Items.getAltoItem(), super.getHeight()/2-Items.getAltoItem()/2);
            if(NaveAliada.getVidasJugador() == 5)//Generará todos los items menos el corazón, ya que tiene el máximo de vidas.
                tipoItem = Aleatorio.getNumeroAleatorio(2, 5);
            else
                if(nave.getPuntosSalud() >= nave.getPuntosSaludIniciales()){ //Se tienen todos los PS.
                    while((aleatorio = Aleatorio.getNumeroAleatorio(1, 5)) == 3){}//Mientras se cree el de PS seguir ciclando
                    tipoItem = aleatorio; //Salió del ciclo. Ya no salió el 3.
                }
                else
                    tipoItem = Aleatorio.getNumeroAleatorio(1, 5);//Elegirá de manera random el item que se creará.
            if(System.currentTimeMillis() - tiempoInicialMilis >= 5000){//Cada que pase 1 minuto, aumentará la velocidad del item.
                velocidadItem++;
                tiempoInicialMilis = System.currentTimeMillis();//Reiniciar el contador para que ocurra cada minuto.
            }
            addObject(new Items(tipoItem, velocidadItem), x, y);
        }
    }
}
