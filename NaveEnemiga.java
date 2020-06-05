import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
* Clase que maneja a las naves enemigas y su comportamiento.
*
* @author (Team Naves)
* @version (Viernes, 29 de mayo de 2020)
*/

/* Character.compare(char 'A', char 'B'); Esto devuelve 0 si al comparar los caracteres son iguales,
                                 >0 si el 1er valor es mayor que el segundo
                                 <0 si el 1er valor es menor que el segundo*/
public class NaveEnemiga extends Nave
{
    /**
    * Act - do whatever the NaveEnemiga wants to do. This method is called whenever
    * the 'Act' or 'Run' button gets pressed in the environment.
    */
    //La vida siempre será 100 por el constructor de la superclase
    /*Este constructor dará vida y tipo de disparo (aún no implementado) dependiendo del tipo de enemigo sea*/
    private int random;//Quisiera que las naves enemigas apuntaran hacia nosotros y si no al menos que apuntaran hacia la izquierda.
    private int puntosPorDisparo; //Variable que define cuántos puntos obtendremos cuando se dispare a la nave.
    // private static int puntosSalud;
    //Ya existe un atributo protegido de Nave "existeMostrarInfo"
    //private boolean muestraPS; //Para ver que se haya creado MostrarInfo, ya que no se puede crear al instanciar.
    public NaveEnemiga(int tipoEnemigo, int tipoDisparo){//Tal vez haga falta un MINIBOSS
        super(tipoDisparo, tipoEnemigo);//public Nave(int tipoDisparo, int diseño)
        System.out.println("Se creo un enemigo");
        infoPS = new MostrarInfo(puntosSalud, 0, 20, Color.BLACK, Color.YELLOW, null);
        // muestraPS = false; //Ya existe un atributo protegido de Nave "existeMostrarInfo"
        //Instanciar mostrarInfo para mostrar los PS de la nave enemiga encima de estas.
        //public MostrarInfo(int tipoInfo, int tamañoFuente, Color colorFuente, Color colorFondo, Color bordeFuente)
        //getY()-getImage().getHeight()/2 para poner el texto arriba de la nave
        //NO SE PUEDE AGREGAR OBJETO EN EL CONSTRUCTOR
        // getWorld().addObject(infoPS, getX(), getY()-getImage().getHeight()/2);
        setImage("Naves/Enemigas/NaveE"+ tipoEnemigo+ ".png");
        setImage(Imagen.modificarEscalaImagen(getImage(), 2, 1));//Acomodar la imagen modificada. La recibimos del método directamente. No necesitamos ninguna variable.
        if(tipoEnemigo == 1){//BOSS == "0"
            puntosSalud+= 100;//Que las naves de BOSSES tengan más vida
            puntosPorDisparo = 50;
            //tipoDisparo=1;//Disparo más potente pero más lento
        }
        else{//ENEMIGOS PEQUEÑOS
            puntosSalud-= 50;//Que las naves de enemigos pequeños tengan menos vida (vida=100-50)
            puntosPorDisparo = 25;
            //tipoDisparo=2;//Disparo menos potente pero más rápido
        }
        setRotation(270);
        pantalla = new Pantalla(this);
    }
    public void act() //Este código lo reutilicé de la clase Roca, por lo que lo podría poner en una clase más general
       {   /*Aquí con un random se verá la dirección en la que se moverá la nave.
        Dependiendo del random (que será el ángulo de dirección), se moverá a una dirección.*/
        // Add your action code here.
        mundo = getWorld();
        //Método para mostrar los PS de cada nave y que se muevan con ellos. Implementado en clase Nave como PROTECTED.
        existeMostrarInfo = muestraPuntosSalud(infoPS, existeMostrarInfo, "PS: ", puntosSalud, getX(), getY()-getImage().getHeight()/2);
        movimientoLimites(mundo, getX(), getY());
        //protected boolean muestraPuntosSalud(MostrarInfo infoPS, boolean yaExistente, String texto, int x, int y)
        // /* Si los PS no se han mostrado, se agrega el texto.*/
        // if(!muestraPS){//getY()-getImage().getHeight()/2 <- Para posicionar encima de las naves.
        // getWorld().addObject(infoPS, getX(), getY()-getImage().getHeight()/2);
        // muestraPS = true;
        // }
        // /*En este método se mandan las coordenadas en donde se quieren agregar los PS. Estos se actualizarán cuando cambien.*/
        // infoPS.mostrarPS("PS: ", puntosSalud, getX(), getY()-getImage().getHeight()/2);
        
        /*MÉTODO QUE VERIFICARÁ SI UNA ROCA CHOCÓ CON ALGO. Necesitamos ver si podemos implemementar esto de manera
        más general porque también será necesario en la clase NaveEnemiga*/
        puntosSalud = eliminaCuadroPS(infoPS, getOneObjectAtOffset(0, 0, Disparo.class),
                    this, (Espacio)getWorld(), puntosSalud, Disparo.getDaño(), puntosPorDisparo);
                    //NaveAliada.setPuntos(puntosPorDisparo*2);//Los puntos obtenidos se multiplicarán por 2 al destruir la nave
                    //Ya está condición dentro del método
    }
    protected void movimiento(){
        //Si el objeto alcanza los límites en x o y, se dará la vuelta. Las limitaremos a la mitad de la pantalla.
        move(3);//Método que mueve a cierta velocidad el objeto
        if(getX()>=mundo.getWidth()-5 || getX()<=mundo.getWidth()/2 || getY()>=mundo.getHeight()-5||getY()<=5){
            turn(180);
            if(getX()<=mundo.getWidth()/2)
                getImage().mirrorHorizontally();
            random = Greenfoot.getRandomNumber(100);
            if(random < 90){//Cambia el ángulo del cómo se ven los sprites.
                random = Greenfoot.getRandomNumber(90-45);
                turn(random);
            }
            //setRotation(270);//Quiero que se mueva para todos lados pero siempre volteando a ver nuestra nave.
        }
    }
    //Método estático para obtener los Puntos de Salud de la NaveAliada específicamente.
    // public static int getPuntosSalud(){
        // return puntosSalud;
    // }
    public int getCordX(){
        return getX();
    }
    public int getCordY(){
        return getY();
    }
}
