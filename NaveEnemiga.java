import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.lang.Math; //La utilizaremos para sacar el ángulo con la nave aliada.
import java.util.ArrayList;

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
    /*ArrayList estático con las naves enemigas para ver que no choquen y así.*/
    private static ArrayList<NaveEnemiga> navesAL; //Estático para que sea igual para todas las instancias.
    private boolean arrayListCreado = false;
    private int random;//Quisiera que las naves enemigas apuntaran hacia nosotros y si no al menos que apuntaran hacia la izquierda.
    private int puntosPorDisparo; //Variable que define cuántos puntos obtendremos cuando se dispare a la nave.
    private int anchoImagen;
    private int altoImagen;
    //private int anguloApuntado;
    // private static int puntosSalud;
    //Ya existe un atributo protegido de Nave "existeMostrarInfo"
    //private boolean muestraPS; //Para ver que se haya creado MostrarInfo, ya que no se puede crear al instanciar.
    public NaveEnemiga(int tipoEnemigo, int tipoDisparo){//Tal vez haga falta un MINIBOSS
        super(tipoDisparo, tipoEnemigo);//public Nave(int tipoDisparo, int diseño)
        if(!arrayListCreado){ //Crear ArrayList si no se ha creado
             navesAL = new ArrayList<NaveEnemiga>();
             arrayListCreado = true;
        }
        //System.out.println("Se creo un enemigo");
        infoPS = new MostrarInfo(puntosSalud, 0, 20, Color.BLACK, Color.YELLOW, null);
        // muestraPS = false; //Ya existe un atributo protegido de Nave "existeMostrarInfo"
        //Instanciar mostrarInfo para mostrar los PS de la nave enemiga encima de estas.
        //public MostrarInfo(int tipoInfo, int tamañoFuente, Color colorFuente, Color colorFondo, Color bordeFuente)
        //getY()-getImage().getHeight()/2 para poner el texto arriba de la nave
        //NO SE PUEDE AGREGAR OBJETO EN EL CONSTRUCTOR
        // getWorld().addObject(infoPS, getX(), getY()-getImage().getHeight()/2);
        setImage("Naves/Enemigas/NaveE"+ tipoEnemigo+ ".png");
        setImage(Imagen.modificarEscalaImagen(getImage(), 2, 1));//Acomodar la imagen modificada. La recibimos del método directamente. No necesitamos ninguna variable.
        anchoImagen = getImage().getWidth();
        altoImagen = getImage().getHeight();
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
        navesAL.add(this);//Agregar el objeto al arrayList una vez creado.
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
                    this, getWorld(), puntosSalud, Disparo.getDaño(), puntosPorDisparo);

                    //NaveAliada.setPuntos(puntosPorDisparo*2);//Los puntos obtenidos se multiplicarán por 2 al destruir la nave
                    //Ya está condición dentro del método
    }
    protected void movimiento(){
        //Si el objeto alcanza los límites en x o y, se dará la vuelta. Las limitaremos a la mitad de la pantalla.
        //limiteChoqueNavesEnemigas();
        //limiteChoqueNaveAliada(200);
        move(1);//Método que mueve a cierta velocidad el objeto
        turnTowards(NaveAliada.getCordX(), NaveAliada.getCordY());

        //setLocation(getX()+1, getY()+1);
    }
    /*Método que marcará un límite entre las mismas naves enemigas y
       nuestra nave para que no choquen.*/
    private void limiteChoqueNavesEnemigas(){
        //Recorrer las demás naves para ver que no choque
        for(NaveEnemiga nave : navesAL){
            //(System.out.println("Entro al ciclo");
            if((getX() + anchoImagen/2) >= (nave.getX() - nave.getAnchoImagen()/2)) //Ver que no sobrepase por la izquierda.
                //System.out.println("(getX() + anchoImagen/2) >= (nave.getX() - nave.getAnchoImagen()/2)");
                setLocation(getX()-1, getY());
            if((getX() - anchoImagen/2) <= (nave.getX() + nave.getAnchoImagen()/2)) //Ver que no sobrepase por la derecha.
                setLocation(getX()+1, getY());
            if((getY() + altoImagen/2) >= (nave.getY() - nave.getAltoImagen()/2))//Ver que no sobrepase por encima.
                setLocation(getX(), getY()-1);
            if((getY() - altoImagen/2) <= (nave.getY() + nave.getAltoImagen()/2))//Ver que no sobrepase por debajo.
                setLocation(getX(), getY()+1);
        }
    }
    /*Método que pondrá un límite para no chocar con la nave aliada (nosotros).*/
    private void limiteChoqueNaveAliada(int distanciaAlejamiento){
        if(getX() + anchoImagen >= NaveAliada.getCordX() - NaveAliada.getAnchoImagen()/2 - distanciaAlejamiento) //Ver que no sobrepase por la izquierda.
            setLocation(getX()-200, getY());
        if(getX() - anchoImagen <= NaveAliada.getCordX() + NaveAliada.getAnchoImagen()/2 + distanciaAlejamiento) //Ver que no sobrepase por la derecha.
            setLocation(getX()+200, getY());
        if(getY() + altoImagen >= NaveAliada.getCordY() - NaveAliada.getAltoImagen()/2 - distanciaAlejamiento)//Ver que no sobrepase por encima.
            setLocation(getX(), getY()-200);
        if(getY() - altoImagen <= NaveAliada.getCordY() + NaveAliada.getAltoImagen()/2 + distanciaAlejamiento)//Ver que no sobrepase por debajo.
            setLocation(getX(), getY()+200);
    }
    //Métodos que regresan el tamaño de la imagen de la nave.
    private int getAnchoImagen(){
        return getImage().getWidth();
    }
    private int getAltoImagen(){
        return getImage().getWidth();
    }
    public int getCordX(){
        return getX();
    }
    public int getCordY(){
        return getY();
    }
}
