import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Clase que maneja todo lo relacionado a los meteoros.
 *
 * @author (Team Naves)
 * @version (Jueves, 4 de junio de 2020)
 */
public class Roca extends Actor
{
    private static long tiempoRegeneracion; //Indicará el tiempo en que las rocas se podrán regenerar.
    private static int numRocasMax; //Número máximo de rocas.
    private static int numRocasActual; //Para saber si las rocas actuales son menos que las máximas.
    private World mundo;
    private Pantalla pantalla;
    private int velocidad;//Atributo que guarda la velocidad del meteoro.
    private int angulo;//Funcion que determinada el movimiento de cada meteoro.
    //Estáticas para que todas las rocas tengan los mismos datos del número de estas.
    private long tiempoCreacion; //Verá si ya pasó el tiempo para que se cree una nueva roca.
    /** Constructor inicial para establecer el máximo de rocas a crear.
    No son necesarios los parámetros, ya que al ser los valores estáticos, los puedo establecer
    aunque no se instancie la clase.*/
    public Roca(){
        tiempoCreacion = System.currentTimeMillis();//Inicializar el tiempo de la condición de creación de rocas
    }

    /** Constructor para crear las rocas como tal para el mundo*/
    public Roca(int tipoMeteoro){
        //Establecer la imagen con el tipo de meteoro recibido.
        setImage("Objetos/Meteoro"+ tipoMeteoro +".png");
        //Aquí modificamos el tamaño de los meteoros a una escala random para que tengan tamaños diferentes.
        Imagen.modificarEscalaImagen(getImage(), Aleatorio.getNumeroAleatorio(2,4), 1);
        velocidad = Aleatorio.getNumeroAleatorio(2, 4);//Determina una velocidad.
        angulo = Aleatorio.getNumeroAleatorio(0, 4);//Se crea un aleatorio de 5 opciones posibles.
        switch(angulo) //En esta parte del codigo entra el numero aleatorio y se escoje para determinar un eje rotacion.
        {
            case 0: /*No se genera rotacion.*/ break;
            case 1: /*Rota derecha.*/ break;
            case 2: break;
            case 3: /*Rota izquierda.*/ angulo = -1; break;
            case 4: angulo = -2; break;
        }
        pantalla = new Pantalla(this);
    }

    public void act(){
        mundo = getWorld();
        move(velocidad);//Método que mueve a cierta velocidad el objeto
        if(!pantalla.isObjetoLimite(mundo, getX(), getY())){
            turn(180);
            if(Greenfoot.getRandomNumber(100)<90)
                turn(Greenfoot.getRandomNumber(90-45));
        }
        turn(angulo);//Aqui se ejecuta la rotacion de cada meteoro.

        /*No tiene puntos de salud por lo que mandamos un 1 y el daño del disparo para que cumpla la condición de daño,
        así que cualquier disparo destruirá el meteorito, pero esto sirve para aumentar los puntos.*/
        //public int eliminarObjetoChoque(Actor objetoChoque, Actor objetoRaiz, World mundoActual, int puntosSalud, int daño, int puntosNave)
        if(Choques.eliminarObjetoChoque(getOneObjectAtOffset(0, 0, Disparo.class), this, getWorld(), 1, Disparo.getDaño(), 5) == 0)
            numRocasActual--; //Como se destruyó la roca, restarla al total de estas.
    }

    /** Método para crear los meteoros en una posición random con un sprite random.*/
    public void crearRocas(int numRocas, World mundoActual){
        Roca r;
        int x, y, random;
        for(int i=0; i<numRocas; i++){
            random = Aleatorio.getNumeroAleatorio(1, 4);//Random para el sprite aleatorio del meteoro. Los sprites van del 1 al 4.
            r = new Roca(random);//Este parámetro indicará qué tipo de meteoro se generará
            x = Greenfoot.getRandomNumber(mundoActual.getWidth());//Ancho
            y = Greenfoot.getRandomNumber(mundoActual.getHeight());//Alto
            mundoActual.addObject(r, x, y);
        }
    }

    /** Método que creará nuevas rocas si se han destruido y si ha pasado cierto tiempo. 
     * Regresa el tiempo por si este cambia para verificar si pasó el límite de tiempo*/
    public void crearRocasTiempo(World mundoActual){ //Tiempo Milis es el último tiempo que se verificó desde la última roca creada.
        //El tiempoMilis ya lo inicializamos al instanciar y depende del tiempoRegeneracion que igual lo recibimos al inicio
        //La primera vez funcionará del tirón  porque el tiempo será el definido de duración, es decir, no será lo mismo al actual en milisegundos
        //Si apenas se van a crear las primeras rocas
        if((System.currentTimeMillis() - tiempoCreacion) >= tiempoRegeneracion && numRocasActual < numRocasMax){
            crearRocas(1, mundoActual); //Se crean las rocas indicadas.
            numRocasActual ++; //Como se creó una roca más, se aumenta al número de rocas actual.
            /*Se almacena el tiempo de la última roca creada. 
             * Se le suma el tiempo de generación para ver hasta cuándo es el límite de este.*/
            tiempoCreacion = System.currentTimeMillis();
        }
    }

    /** Método para obtener el número actual de rocas. Es estático porque su implementación también lo es.*/
    public int getNumRocasActual(){
        return numRocasActual;
    }

    /** Setter estático para ser accedido sin instanciar y cambiar el número actual de rocas.*/
    public void setNumRocasActual(int numRocasActual){
        this.numRocasActual = numRocasActual; //No se puede utilizar this. porque no se instancia el objeto por ser estático.
    }

    public static void setNumRocasMax(int nRocasMax){
        //Como sólo le daremos las máximas una vez, serán las iniciales de las actuales.
        numRocasMax = numRocasActual = nRocasMax;
    }

    public static long getTiempoRegeneracion(){
        return tiempoRegeneracion;
    }

    public static void setTiempoRegeneracion(long tRegeneracion){
        tiempoRegeneracion = tRegeneracion;
    }

    public static int getNumRocasMax(){
        return numRocasMax;
    }

    public int getCoordX(){
        return getX();
    }

    public int getCoordY(){
        return getY();
    }
}
