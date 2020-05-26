import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Clase que maneja todo lo relacionado a los meteoros.
 *
 * @author (Team Naves)
 * @version (Lunes, 25 de mayo de 2020)
 */
public class Roca extends Actor
{
    //Roca[] r; <- Era para hacer un arreglo de rocas
    World w;
    MetodosGenerales m = new MetodosGenerales(); //Instanciar clase en donde se encuentra el método de eliminación al chocar objetos.
    private int x, y, i;
    private static int numRocasMax; //Número máximo de rocas. Estático para no necesitar instanciarlo y que sean generales los valores en todas las rocas.
    private static int numRocasActual; //Para saber si las rocas actuales son menos que las máximas.
   public Roca(){}//Constructor sólo para cuando queremos los métodos.
    public Roca(int tipoMeteoro){//CONSTRUCTOR
       /*El código de abajo servirá para reescalar la imagen, ya que estaba muy grande*/
       setImage("Objetos/Meteoro"+ tipoMeteoro+ ".png");
       //Aquí modificamos el tamaño de los meteoros a una escala random para que tengan tamaños diferentes.
       m.modificarEscalaImagen(getImage(), m.getRandomNumber(2,4), 1);
   }
   public void act(){
        // Add your action code here.
        w = getWorld();
        move(3);//Método que mueve a cierta velocidad el objeto
        if(getX()>=w.getWidth()-5||getX()<=5 || getY()>=w.getHeight()-5||getY()<=5){
            turn(180);
            if(Greenfoot.getRandomNumber(100)<90){
                turn(Greenfoot.getRandomNumber(90-45));
            }
        }
        /*Método que elimina la roca y el disparo al chocar.
            public boolean eliminarObjetoChoque(Actor objetoChoque, Actor objetoRaiz, World mundoActual)
                Lo implementé en la clase Disparo para que quede ahí todo organizado.*/
        /*public int eliminarObjetoChoque(Actor objetoChoque, Actor objetoRaiz, World mundoActual, int puntosSalud, int daño)
          No tiene puntos de salud por lo que mandamos un 0 y daño 0 para que cumpla la condición de daño.*/
        m.eliminarObjetoChoque(getOneObjectAtOffset(0, 0, Disparo.class), this, (Espacio)getWorld(),0, 0);
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
        w = getWorld();
        int i, x, y, random;
        for(i = 0; i < numRocas; i++){
            random = m.getRandomNumber(1, 4);//Random para el sprite aleatorio del meteoro. Los sprites van del 1 al 4.
            Roca r = new Roca(random);//Este parámetro indicará qué tipo de meteoro se generará
            x = Greenfoot.getRandomNumber(w.getWidth());//Ancho
            y = Greenfoot.getRandomNumber(w.getHeight());//Alto
            getWorld().addObject(r, x, y);
        }
    }
    //Getters para obtener las coordenadas de las rocas.
    public int getCoordX(){
        return getX();
    }
    public int getCoordY(){
        return getY();
    }
    //- Métodos Estáticos para no necesitar instanciarlo y que sean generales los valores en todas las rocas.
    /*Método para obtener el número actual de rocas. Es estático porque su implementación también lo es.*/
    public static int getNumRocasActual(){
        return numRocasActual;
    }
    /*Setter estático para ser accedido sin instanciar y cambiar el número actual de rocas.*/
    public static void setNumRocasActual(int numRocasAct){
        numRocasActual = numRocasAct; //No se puede utilizar this. porque no se instancia el objeto por ser estático.
    }
    public static int getNumRocasMax(){
      return numRocasMax;
    }
    public static void setNumRocasMax(int rocasMax){
        numRocasMax = rocasMax;
    }

}
