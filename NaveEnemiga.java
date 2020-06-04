import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
<<<<<<< Updated upstream
 * Write a description of class NaveEnemiga here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class NaveEnemiga extends Nave
{
    private int Direccion;//La direccion que tendra la nave
    private int TiempoInicial=0;//Tiempo inicial del sistema
    private int TiempoLimite=0;//Tiempo destino para hacer el cambio
    public void act() 
    {
        if(TiempoInicial==TiempoLimite)
        {
            ActualizarTiempo();//Aqui actualiza las dos variables de tiempo
        }else
        {
           Direccion=Aleatorio();//Aqui se actualiza a un numero aleatorio de 0-7, para simular las teclas
           while(TiempoInicial!=TiempoLimite)
           {
               setDireccion(Direccion);//Uso el override para realizar el movimiento pero en un ciclo asi la nave se mueve de forma sucesiva
           }
        }
    }    
    public int Aleatorio()
    {
        return (int) (Math.random()*8); //Esta es la funcion que genera numeros aleatorios, 8 diferentes.
    }
    public void ActualizarTiempo()
    {
         TiempoInicial = (int) System.currentTimeMillis(); //Toma el tiempo del sistema
         TiempoLimite = TiempoInicial+500; //Añade 500 milisegundos y lo designa como tiempo Limite
=======
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
    World w;
    private int random;//Quisiera que las naves enemigas apuntaran hacia nosotros y si no al menos que apuntaran hacia la izquierda.
    private int puntosPorDisparo; //Variable que define cuántos puntos obtendremos cuando se dispare a la nave.
    // private static int puntosSalud;
    //Ya existe un atributo protegido de Nave "existeMostrarInfo"
    //private boolean muestraPS; //Para ver que se haya creado MostrarInfo, ya que no se puede crear al instanciar.
    public NaveEnemiga(int tipoEnemigo, int tipoDisparo){//Tal vez haga falta un MINIBOSS
        super(tipoDisparo, tipoEnemigo);//public Nave(int tipoDisparo, int diseño)
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
    }
    public void act() //Este código lo reutilicé de la clase Roca, por lo que lo podría poner en una clase más general
       {   /*Aquí con un random se verá la dirección en la que se moverá la nave.
        Dependiendo del random (que será el ángulo de dirección), se moverá a una dirección.*/
        // Add your action code here.
        w = getWorld();
        move(3);//Método que mueve a cierta velocidad el objeto
        //protected boolean muestraPuntosSalud(MostrarInfo infoPS, boolean yaExistente, String texto, int x, int y)
        //Método para mostrar los PS de cada nave y que se muevan con ellos. Implementado en clase Nave como PROTECTED.
        existeMostrarInfo = muestraPuntosSalud(infoPS, existeMostrarInfo, "PS: ", puntosSalud, getX(), getY()-getImage().getHeight()/2);
        // /* Si los PS no se han mostrado, se agrega el texto.*/
        // if(!muestraPS){//getY()-getImage().getHeight()/2 <- Para posicionar encima de las naves.
        // getWorld().addObject(infoPS, getX(), getY()-getImage().getHeight()/2);
        // muestraPS = true;
        // }
        // /*En este método se mandan las coordenadas en donde se quieren agregar los PS. Estos se actualizarán cuando cambien.*/
        // infoPS.mostrarPS("PS: ", puntosSalud, getX(), getY()-getImage().getHeight()/2);
        //Si el objeto alcanza los límites en x o y, se dará la vuelta. Las limitaremos a la mitad de la pantalla.
        if(getX()>=w.getWidth()-5 || getX()<=w.getWidth()/2 || getY()>=w.getHeight()-5||getY()<=5){
            turn(180);
            if(getX()<=w.getWidth()/2)
                getImage().mirrorHorizontally();
            random = Greenfoot.getRandomNumber(100);
            if(random < 90){//Cambia el ángulo del cómo se ven los sprites.
                random = Greenfoot.getRandomNumber(90-45);
                turn(random);
            }
            //setRotation(270);//Quiero que se mueva para todos lados pero siempre volteando a ver nuestra nave.
        }
        /*MÉTODO QUE VERIFICARÁ SI UNA ROCA CHOCÓ CON ALGO. Necesitamos ver si podemos implemementar esto de manera
        más general porque también será necesario en la clase NaveEnemiga*/
        /*Copié este método de la clase Roca, así que sí debería poder implementarse de manera más general, pero
           por ahora lo copio aquí también.*/
        /*Se heredan todas las características, y se ponen como parámetros 0, 0
        porque de esta manera vamos a saber que los objetos chocaron.
        -> Lo implementé en la clase Disparo para que quede ahí todo organizado.*/
        // Actor naveAliada = getOneObjectAtOffset(0, 0, NaveAliada.class);
        // Actor disparoNave = getOneObjectAtOffset(0, 0, Disparo.class);
        // if(naveAliada != null){ //Aquí se va a revisar si chocó con nuestra nave
        // Espacio e = (Espacio)getWorld(); //Se toma el mundo actual
        // getWorld().removeObject(naveAliada); //Se elimina la nave
        // getWorld().removeObject(this); //Se elimina la roca
        // //AQUÍ TENGO QUE TOMAR EN CUENTA LAS VIDAS QUE TENGO PARA IR BAJÁNDOLAS
        // //naveAliada.vidas++; //Esto solo es conceptual.
        // }
        // if(disparoNave != null){ //Aquí sevisamos el el disparo chocó con la roca
        // /*El único problema que veo es que cuando se elimina ni siquiera chocan los objetos. Hay que arreglar esto.*/
        // Espacio e = (Espacio)getWorld(); //Se toma el mundo actual
        // getWorld().removeObject(disparoNave); //Se elimina el disparo
        // getWorld().removeObject(this); //Se elimina la roca
        // }
        //public boolean eliminarObjetoChoque(Actor objetoChoque, Actor objetoRaiz, World mundoActual, int puntosSalud, int daño)
        //Guardaremos la salud actual de la nave enemiga, ya que la regresa el método
        /* - Se hace la asignación de la salud y se verifica que sea igual a 0, si es 0
                entonces se dan más puntos que si sólo se le dispara.*/
        /*protected int eliminaCuadroPS(MostrarInfo infoPS, Actor objetoChoque,
                                    Actor objetoRaiz, World mundoActual, int puntosSalud, int daño, int puntosNave){*/
        // if(getOneObjectAtOffset(0, 0, Disparo.class))
            // getWorld().removeObject(infoPS);
        puntosSalud = eliminaCuadroPS(infoPS, getOneObjectAtOffset(0, 0, Disparo.class),
                    this, (Espacio)getWorld(), puntosSalud, Disparo.getDaño(), puntosPorDisparo);
                    //NaveAliada.setPuntos(puntosPorDisparo*2);//Los puntos obtenidos se multiplicarán por 2 al destruir la nave
                    //Ya está condición dentro del método
    }

    //Método estático para obtener los Puntos de Salud de la NaveAliada específicamente.
    // public static int getPuntosSalud(){
        // return puntosSalud;
    // }
    public int getCordX(){
        return getX();
>>>>>>> Stashed changes
    }
    @Override
    public void setDireccion(int direccion){
        switch(direccion){
            case UP:
                setRotation(0);
                if(Greenfoot.isKeyDown("space")){
                    setLocation(getX(),getY()-8);
                    cordY-=6;
                }
                else{
                    setLocation(getX(),getY()-6);
                    cordY-=4;
                }
                break;
            case DOWN:
                setRotation(180);
                if(Greenfoot.isKeyDown("space")){
                    setLocation(getX(),getY()+6);
                    cordY+=6;
                }
                else{
                    setLocation(getX(),getY()+4);
                    cordY+=4;
                }
                break;
            case LEFT:
                setRotation(270);
                if(Greenfoot.isKeyDown("space")){
                    setLocation(getX()-6,getY());
                    cordX-=6;
                }
                else{
                    setLocation(getX()-4,getY());
                    cordX-=4;
                }
                break;
            case RIGHT:
                setRotation(90);
                if(Greenfoot.isKeyDown("space")){
                    setLocation(getX()+6,getY());
                    cordX+=6;
                }
                else{
                    setLocation(getX()+4,getY());
                    cordX+=4;
                }
                break;
                
             case UP_RIGHT:
                setRotation(45);
                if(Greenfoot.isKeyDown("space")){
                    setLocation(getX()+2,getY()-2);
                    cordX+=2;
                    cordY-=2;
                }
                else{
                    setLocation(getX()+1,getY()-1);
                    cordX+=1;
                    cordY-=1;
                }
                break;
                case UP_LEFT:
                setRotation(315);
                if(Greenfoot.isKeyDown("space")){
                    setLocation(getX()-2,getY()-2);
                    cordX-=2;
                    cordY-=2;
                }
                else{
                    setLocation(getX()-1,getY()-1);
                    cordX-=1;
                    cordY-=1;
                }
                break;
                case DOWN_LEFT:
                setRotation(225);
                if(Greenfoot.isKeyDown("space")){
                    setLocation(getX()-2,getY()+2);
                    cordX-=2;
                    cordY+=2;
                }
                else{
                    setLocation(getX()-1,getY()+1);
                    cordX-=1;
                    cordY+=1;
                }
                break;
                case DOWN_RIGHT:
                setRotation(135);
                if(Greenfoot.isKeyDown("space")){
                    setLocation(getX()+2,getY()+2);
                    cordX+=2;
                    cordY+=2;
                }
                else{
                    setLocation(getX()+1,getY()+1);
                    cordX+=1;
                    cordY+=1;
                }
                break;
        }       
    }
}
