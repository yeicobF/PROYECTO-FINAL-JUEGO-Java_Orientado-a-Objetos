import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Esta es la superclase que manejará a las clases relacionadas con naves.
 * 
 * @author (Team Naves) 
 * @version (Domingo, 17 de mayo - Lunes, 18 de mayo de 2020)
 */
public abstract class Nave extends Actor
{
    //Nave n; //Inicializar la nave para después instanciarla, aunque no es necesario porque esta clase no se utilizará
    protected Disparo disparo; //Porque las naves aliadas y enemigas lo necesitan
    protected MetodosGenerales m = new MetodosGenerales();//Variable para usar sus métodos como el de reescalar la imagen.
    protected MostrarInfo infoPS; //Inicializar la clase MostrarInfo para el cuadro de texto de los PS
    public static final int UP=0;
    public static final int DOWN=1;
    public static final int LEFT=2;
    public static final int RIGHT=3;
    public static final int UP_RIGHT=4;
    public static final int UP_LEFT=5;
    public static final int DOWN_LEFT=6;
    public static final int DOWN_RIGHT=7;
    /*Los PS eran estáticos pero así valían lo mismo y cambiaban con todas las estancias. 
     *  Cuando bajaba la salud de una nave enemiga, también bajaba la nuestra.*/
    protected int puntosSalud;//Puntos de salud actuales. Al perder todos los puntos de salud se pierde una vida.
    protected int tipoDisparo; //Dependiendo del tipo del disparo cambiará su sprite. Estos serán como las mejoras.
    protected int diseñoNave;//El diseño de la nave
    protected int tipoHabilidad;//Esto serán los PowerUps.
    protected boolean existeMostrarInfo;
    /*NO NECESITAMOS coordX ni coordY porque ya están getX() y getY()*/
    public Nave(){}//Constructor vacío para no tener problemas en Disparo
    public Nave(int tipoDisparo, int diseñoNave){//char diseño para cuando tengamos más diseños
        //Los puntos de salud ahora serán implementados en cada clase por separado.
        this.tipoDisparo = tipoDisparo;//Aquí condicionaremos para el diseño y eso pero en la clase Disparo.
        this.diseñoNave = diseñoNave; //De esto dependerá el diseño que tendrá la nave.
        puntosSalud=100;//Puntos de salud estándar = 100. Cambiarán dependiendo del tipo de nave y su poder.
        existeMostrarInfo = false;
    }//CONSTRUCTOR en el que se definirá si la nave es 0.- enemigo o 1.-Nosotros
    public void act() //act(int direccion) 
    {   
    }
    /*Clase que cambia la dirección dependiendo del parámetro que reciba. Además aquí mismo se ejecuta el movimiento.*/
    protected void setDireccion(int direccion){
        switch(direccion){
            case UP:
                setRotation(0);
                if(Greenfoot.isKeyDown("space")){
                    setLocation(getX(),getY()-8);
                }
                else{
                    setLocation(getX(),getY()-6);
                }
                break;
            case DOWN:
                setRotation(180);
                if(Greenfoot.isKeyDown("space")){
                    setLocation(getX(),getY()+6);
                }
                else{
                    setLocation(getX(),getY()+4);
                }
                break;
            case LEFT:
                setRotation(270);
                if(Greenfoot.isKeyDown("space")){
                    setLocation(getX()-6,getY());
                }
                else{
                    setLocation(getX()-4,getY());
                }
                break;
            case RIGHT:
                setRotation(90);
                if(Greenfoot.isKeyDown("space")){
                    setLocation(getX()+6,getY());
                }
                else{
                    setLocation(getX()+4,getY());
                }
                break;
                
            case UP_RIGHT:
                setRotation(45);
                if(Greenfoot.isKeyDown("space")){
                    setLocation(getX()+2,getY()-2);
                }
                else{
                    setLocation(getX()+1,getY()-1);
                }
                break;
                
                case UP_LEFT:
                setRotation(315);
                if(Greenfoot.isKeyDown("space")){
                    setLocation(getX()-2,getY()-2);
                }
                else{
                    setLocation(getX()-1,getY()-1);
                }
                break;
                
                case DOWN_LEFT:
                setRotation(225);
                if(Greenfoot.isKeyDown("space")){
                    setLocation(getX()-2,getY()+2);
                }
                else{
                    setLocation(getX()-1,getY()+1);
                }
                break;
                
                case DOWN_RIGHT:
                setRotation(135);
                if(Greenfoot.isKeyDown("space")){
                    setLocation(getX()+2,getY()+2);
                }
                else{
                    setLocation(getX()+1,getY()+1);
                }
                break;
        }       
    }
    /*Método protegido para obtener los puntos de salud actuales de las naves. Protegido para que se pueda utilizar
        desde las subclases y static para que no se necesite una instancia del objeto.
        Sólo se necesitaba para MostrarInfo, pero como es subclase de NaveAliada, no se necesita
            un getter para acceder, ya que puntosSalud es un atributo protegido.*/
    // protected static int getPuntosSalud(){
        // return puntosSalud;
    // }
    /*Método abstracto que será definido en las subclases regresando sus propios puntos de salud.
       Estos tendrán que ser distintos porque los necesitamos manejar como estáticos,
        y si son estáticos generales, estos valdrán lo mismo en todas las instancias.
        - Necesitaba que fuera estático, pero eso no se puede, por lo que lo implementaré a cada
            clase por separado aunque sea un mal diseño.*/
    //public abstract int getPuntosSalud();
    /*Método para mostrar los PS de las naves. Protegido para que todas las naves lo puedan usar.*/
    protected boolean muestraPuntosSalud(MostrarInfo infoPS, boolean yaExistente, String texto, int puntosSalud, int x, int y){
        /* Si los PS no se han mostrado, se agrega el texto.*/
        if(!yaExistente)//getY()-getImage().getHeight()/2 <- Para posicionar encima de las naves.
            getWorld().addObject(infoPS, x, y);
        else
            /*En este método se mandan las coordenadas en donde se quieren agregar los PS. Estos se actualizarán cuando cambien.*/
            infoPS.mostrarPS(texto, puntosSalud, x, y);    
        return true; //hacer yaExistente = true, es decir, ya se creó
    }
    /*Método que elimina el cuadro de texto donde se mostrarán los PS cuando estos sean igual a 0. Este método es necesario
        porque cuando los PS == 0, el método eliminarObjetoChoque elimina el objeto directamente.
        En este método, seguirá eliminando el objeto pero también el cuadro de texto.*/
    protected int eliminaCuadroPS(MostrarInfo infoPS, Actor objetoChoque, Actor objetoRaiz, 
                                World mundoActual, int puntosSalud, int daño, int puntosNave){
        //Actor objetoChoque, Actor objetoRaiz, World mundoActual, int puntosSalud, int daño, int puntosNave
        if((puntosSalud = m.eliminarObjetoChoque(objetoChoque, objetoRaiz, mundoActual, puntosSalud, daño, puntosNave)) == 0)
            mundoActual.removeObject(infoPS);
        return puntosSalud;
    }
    /*Podría hacer un método igual al de eliminaCuadroPS, pero que mostrara el recuadro con los PS = 0 antes de destruirse,
        aunque creo que no tendría mucho sentido.
    protected int eliminaCuadroPS(MostrarInfo infoPS, String texto, Actor objetoChoque, Actor objetoRaiz, 
                                World mundoActual, int puntosSalud, int daño, int puntosNave)*/
    public int getCordX(){
        return getX();
    }
    public int getCordY(){
        return getY();
    }
    // NO NECESITO LOS SETTERS O GETTERS PORQUE LOS DECLARO PROTECTED PARA QUE SÓLO LAS SUBCLASES ACCEDAN
    //Getters Y setters protected para que sólo las subclases puedan acceder
    // protected int getPS(){//Puntos de salud actuales. Al perder todos los puntos de salud se pierde una vida.
        // return puntosSalud;
    // }
    // protected void setPS(int puntosSalud){//Puntos de salud actuales. Al perder todos los puntos de salud se pierde una vida.
        // this.puntosSalud = puntosSalud;
    // }
    
    // protected int getTipoDisparo(){//Dependiendo del tipo del disparo cambiará su sprite. Estos serán como las mejoras.
        // return tipoDisparo;
    // }
    // protected void setTipoDisparo(int tipoDisparo){//Dependiendo del tipo del disparo cambiará su sprite. Estos serán como las mejoras.
        // this.tipoDisparo = tipoDisparo;
    // }
    
    // protected int getDiseñoNave(){//El diseño de la nave
        // return diseñoNave;
    // }
    // protected void setDiseñoNave(int diseño){//El diseño de la nave
        // this.diseñoNave = diseñoNave;
    // }
    
    // protected int getTipoHabilidad(){//Esto serán los PowerUps.
        // return tipoHabilidad;
    // }
    // protected void setTipoHabilidad(int tipoHabilidad){//Esto serán los PowerUps.
        // this.tipoHabilidad = tipoHabilidad;
    // }
}
