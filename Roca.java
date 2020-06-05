import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Clase que maneja todo lo relacionado a los meteoros.
 *
 * @author (Team Naves)
 * @version (Jueves, 4 de junio de 2020)
 */
public class Roca extends Actor
{
    private World mundo;
    private Pantalla pantalla;
    //Estáticas para que todas las rocas tengan los mismos datos del número de estas.
    private static int numRocasMax; //Número máximo de rocas.
    private static int numRocasActual; //Para saber si las rocas actuales son menos que las máximas.
    private long tiempoCreacion; //Verá si ya pasó el tiempo para que se cree una nueva roca.
    private static long tiempoRegeneracion; //Indicará el tiempo en que las rocas se podrán regenerar.
    //private long tiempoMilis;//Verificará si pasó el tiempo para que se regeneren las rocas.
    //private boolean crearRocasIniciales;//Indicará si ya se crearon las rocas al inicio.
    //Aquí utilizaremos el mostrarInfo(tipo = niveles) y los demás también podrían ser.
    /*Constructor inicial para establecer el máximo de rocas a crear.*/
    public Roca(){//int numRocasMax, int tiempoRegeneracion){
        /*No son necesarios los parámetros, ya que al ser los valores estáticos, los puedo establecer
           aunque no se instancie la clase.*/
      // this.numRocasActual = numRocasMax = numRocasMax; //Inicializar las rocas
      // this.tiempoRegeneracion  = tiempoRegeneracion;
      // //crearRocasIniciales = false; //Aún no se crean las rocas iniciales.
      tiempoCreacion = System.currentTimeMillis();//Inicializar el tiempo de la condición de creación de rocas
    }
    /*Constructor para crear las rocas como tal para el mundo*/
   public Roca(int tipoMeteoro){
       //Establecer la imagen con el tipo de meteoro recibido.
       setImage("Objetos/Meteoro"+ tipoMeteoro +".png");
       //Aquí modificamos el tamaño de los meteoros a una escala random para que tengan tamaños diferentes.
       Imagen.modificarEscalaImagen(getImage(), Aleatorio.getNumeroAleatorio(2,4), 1);
       // if(tipoMeteoro==0){
            // setImage("pruebaMeteoro1.png");
            // GreenfootImage image = getImage(); //Tomar la imagen que modificaremos
            // //El tamaño de la imagen será random
            // random = getRandomNumber(11,17);//Esta función está implementada más abajo.
            // image.scale(image.getWidth()/random, image.getHeight()/random);//Reescalar imagen a 2/8 de las medidas originales.
            // setImage(image);//Acomodar ahora sí la imagen modificada
        // }
        // else{//Se maneja una diferente escala dependiendo del tamaño de la imagen, pero pensamos en ponerlas a proporción del área de juego.
            // GreenfootImage image = getImage(); //Tomar la imagen que modificaremos
            // random = getRandomNumber(2, 3);
            // image.scale(image.getWidth()/random, image.getHeight()/random);//Reescalar imagen a 2/8 de las medidas originales.
            // setImage(image);//Acomodar ahora sí la imagen modificada
        // }
       pantalla = new Pantalla(this);
   }
   public void act(){
        // Add your action code here.
        mundo = getWorld();
        move(3);//Método que mueve a cierta velocidad el objeto
        if(!pantalla.isObjetoLimite(mundo, getX(), getY())){
            turn(180);
            if(Greenfoot.getRandomNumber(100)<90){
                turn(Greenfoot.getRandomNumber(90-45));
            }
        }
        // if(getY()>=w.getHeight()-5||getY()<=5){ //Estas líneas de más se pueden evitar.
            // turn(180);
            // if(Greenfoot.getRandomNumber(100)<90){
                // turn(Greenfoot.getRandomNumber(90-45));
            // }
        // }
        /*MÉTODO QUE VERIFICARÁ SI UNA ROCA CHOCÓ CON ALGO. Necesitamos ver si podemos implemementar esto de manera
            más general porque también será necesario en la clase NaveEnemiga.
                Implementé este método más general en la clase "MétodosGenerales"*/
        /*Se heredan todas las características, y se ponen como parámetros 0, 0
           porque de esta manera vamos a saber que los objetos chocaron.*/
        // Actor naveAliada = getOneObjectAtOffset(0, 0, NaveAliada.class);
        // if(naveAliada != null){ //Aquí se va a revisar si chocó con nuestra nave
            // Espacio e = (Espacio)getWorld(); //Se toma el mundo actual
            // getWorld().removeObject(naveAliada); //Se elimina la nave
            // getWorld().removeObject(this); //Se elimina la roca
            // //AQUÍ TENGO QUE TOMAR EN CUENTA LAS VIDAS QUE TENGO PARA IR BAJÁNDOLAS
            // //naveAliada.vidas++; //Esto solo es conceptual.
        // }
        /*Método que elimina la roca y la nave si chocan
           public boolean eliminarObjetoChoque(Actor objetoChoque, Actor objetoRaiz, World mundoActual)
            El "this" es el objeto actual.
         -> No se implementa aquí porque ya está en la clase "NaveAliada".*/
        //m.eliminarObjetoChoque(getOneObjectAtOffset(0, 0, NaveAliada.class), this, (Espacio)getWorld());

        // Actor disparoNave = getOneObjectAtOffset(0, 0, Disparo.class);
        // if(disparoNave != null){ //Aquí sevisamos el el disparo chocó con la roca
            // /*El único problema que veo es que cuando se elimina ni siquiera chocan los objetos. Hay que arreglar esto.*/
            // Espacio e = (Espacio)getWorld(); //Se toma el mundo actual
            // getWorld().removeObject(disparoNave); //Se elimina el disparo
            // getWorld().removeObject(this); //Se elimina la roca
        // }
        /*Método que elimina la roca y el disparo al chocar.
            public boolean eliminarObjetoChoque(Actor objetoChoque, Actor objetoRaiz, World mundoActual)
                Lo implementé en la clase Disparo para que quede ahí todo organizado.*/
            /*No tiene puntos de salud por lo que mandamos un 1 y el daño del disparo para que cumpla la condición de daño,
               así que cualquier disparo destruirá el meteorito, pero esto sirve para aumentar los puntos.*/
    //public int eliminarObjetoChoque(Actor objetoChoque, Actor objetoRaiz, World mundoActual, int puntosSalud, int daño, int puntosNave)
        if(Choques.eliminarObjetoChoque(getOneObjectAtOffset(0, 0, Disparo.class), this,
                (Espacio)getWorld(), 1, Disparo.getDaño(), 5) <= 0){
            numRocasActual--; //Como se destruyó la roca, restarla al total de estas.
            System.out.println("Rocas ahora: "+ numRocasActual);
        }
            //NaveAliada.setPuntos(10);//Aumentar 10 puntos al jugador por destruir una roca.
            //Espacio.numRocasActual-= 1;
    }


    // public boolean chocanRocas(int x, int y){//Método que evaluará si dos rocas están chocando para eliminarlas
        // if(getX()>=x-5 && getX()<=x+5 && getY()>=y-5 && getY()<=y+5)//Comparar posición de roca parámetro con la actual
            // return true;
        // else
            // return false;//No necesita else porque si no entra en el if, llegará aquí directamente
    // }

    /* -> Estos métodos me los traje de la clase Niveles.*/

    /*Método para crear los meteoros en una posición random con un sprite random*/
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
    /*Método que creará nuevas rocas si se han destruido y si ha pasado cierto tiempo. Regresa el tiempo por si este cambia para verificar si pasó el límite de tiempo*/
    //El tiempoMilis ya lo inicializamos al instanciar y depende del tiempoRegeneracion que igual lo recibimos al inicio
    public void crearRocasTiempo(World mundoActual){ //Tiempo Milis es el último tiempo que se verificó desde la última roca creada.
      //La primera vez funcionará del tirón  porque el tiempo será el definido de duración, es decir, no será lo mismo al actual en milisegundos
        //Si apenas se van a crear las primeras rocas
        if((System.currentTimeMillis() - tiempoCreacion) >= tiempoRegeneracion && numRocasActual < numRocasMax){
            crearRocas(1, mundoActual); //Se crean las rocas indicadas.
            numRocasActual ++; //Como se creó una roca más, se aumenta al número de rocas actual.
            System.out.println("-> Numero Rocas Actual: "+ numRocasActual);
            /*Se almacena el tiempo de la última roca creada. Se le suma el tiempo de generación para ver hasta cuándo es el límite de este.*/
            tiempoCreacion = System.currentTimeMillis();
        }
    }
    /*Método para obtener el número actual de rocas. Es estático porque su implementación también lo es.*/
    public int getNumRocasActual(){
        return numRocasActual;
    }
    /*Setter estático para ser accedido sin instanciar y cambiar el número actual de rocas.*/
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
