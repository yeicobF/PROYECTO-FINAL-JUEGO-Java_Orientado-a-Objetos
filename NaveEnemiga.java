import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.lang.Math; //La utilizaremos para sacar el ángulo con la nave aliada.

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
    //private int anguloApuntado;
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
        anguloGiro = 0;
        //setRotation(270);
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
        move(1);//Método que mueve a cierta velocidad el objeto
        setDireccion(1);
        // if(getX()>=mundo.getWidth()-5 || getX()<=mundo.getWidth()/2 || getY()>=mundo.getHeight()-5||getY()<=5){
            // turn(180);
            // if(getX()<=mundo.getWidth()/2)
                // getImage().mirrorHorizontally();
            // random = Greenfoot.getRandomNumber(100);
            // if(random < 90){//Cambia el ángulo del cómo se ven los sprites.
                // random = Greenfoot.getRandomNumber(90-45);
                // turn(random);
            // }
            // //setRotation(270);//Quiero que se mueva para todos lados pero siempre volteando a ver nuestra nave.
        // }
    }
    //protected void setDireccion(int direccion)
    /* Método que apuntará las naves enemigas hacia donde estamos nosotros.
        Se definió abstracto en la clase Nave*/
    protected void setDireccion(int direccion){
        this.direccion = direccion; //Se generará random.
        int catetoAdyacente = NaveAliada.getCordX() - getX();
        int catetoOpuesto = NaveAliada.getCordY() - getY();
        double anguloAuxiliar;
        int anguloAnterior = 0; //Ya que el turn se va acumulando todo el valor dado. Esto para restarlo y volver a la posición original.
        /*Para sacar el ángulo entre las naves enemigas y la aliada hay que utilizar funciones trigonométricas.
           Recordemos que: tan(angulo) = co/ca
            -> Entonces: angulo = tanInversa(co/ca)
            Para esto tomaremos de referencia {x, y} de la nave enemiga y la aliada.
            La nave enemiga será el centro de la circunferencia, entonces habrá que tomar en cuenta el cuadrante
                para determinar el ángulo:
                    - ABAJO_DERECHA (0-90 grados): angulo = angulo
                    - ABAJO_IZQUIERDA (90-180 grados): 180 - angulo
                    - ARRIBA_IZQUIERDA (180-270 grados): 180 + angulo
                    - ARRIBA_DERECHA (270-360): 360-angulo
                Esto tomando siempre x como el cateto adyacente.
            Fuente del método: https://stackoverflow.com/questions/3449826/how-do-i-find-the-inverse-tangent-of-a-line*/
        //Devuelve ángulo negativo si yAliada < yEnemiga y positiva y yAliada >= yEnemiga    
        anguloAuxiliar = Math.toDegrees(Math.atan2(catetoOpuesto, catetoAdyacente));
        System.out.println("- ANGULO DOUBLE: "+ anguloAuxiliar);
        anguloGiro = (int)anguloAuxiliar;
        System.out.println("- ANGULO INT: "+ anguloGiro);
        if(NaveAliada.getCordY() > getY())
            anguloGiro += 360;
        turn(anguloAnterior-anguloGiro);
        
        //turn(0); //El turn es acumulativo.
        anguloAnterior = anguloGiro;
    }
    public int getCordX(){
        return getX();
    }
    public int getCordY(){
        return getY();
    }
}
