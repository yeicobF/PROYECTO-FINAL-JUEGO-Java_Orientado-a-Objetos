import greenfoot.Actor;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import greenfoot.World;
import greenfoot.GreenfootImage;
import greenfoot.GreenfootSound;
import greenfoot.Greenfoot;
/**
 * Esta clase maneja los disparos y les da propiedades dependiendo del tipo del disparo.
 *
 * @author (Team Naves)
 * @version (Lunes, 22 de junio de 2020)
 */
public class Disparo extends Actor
{
    /**
     * La clase disparo hereda de la superclase Nave porque las naves son las únicas que utilizan los disparos. Aunque
     *  ahora que lo pienso puedo acceder al disparo desde Nave___ sin necesidad de que estén vinculadas
     *  heredando de la misma superclase. Lo había pensado porque no sabía cómo acceder a los atributos
     *  si se instancia nada más en las naves, pero no es necesario que herede de Nave.
     */
    private static int daño; //Estática para que puedan acceder desde fuera y restar el daño hecho.
    private static long tiempoEntreDisparos; //Variable que determinará el tiempo que tardarás en poder disparar de nuevo.
    private GreenfootSound sonidoDisparo; //Para el efecto de disparo.
    private World mundo;
    private Pantalla pantalla;
    private boolean seAsignoDireccion;
    private int direccion; //Creo que no son necesarias las coordenadas por el getX() y getY()
    private int tipoDisparo;
    private int velocidadDisparo;
    private int numAnimaciones; //Las animaciones del disparo.
    private int animacionActual;
    
    /** Constructor para aparecer al disparo.
     *  En cuanto a la direccion del disparo, no pude implementarlo porque no salía como debería salir, así que hay que revisarlo
            porque hay algo que falla. Por ahora solo comentaré las líneas para después implementar dicha función.*/
    public Disparo(int tipoDisparo, int direccion){ //Recibirá las coordenadas de la nave para aparecer.this.direccion = direccion;//Para guardar la direccion del disparo. - Por ahora no se pudo hacer, pero lo comentaré.
        this.tipoDisparo = tipoDisparo;
        sonidoDisparo = new GreenfootSound("disparo.mp3");
        sonidoDisparo.play();
        animacionActual = 1;
        setImage("Disparos/"+ tipoDisparo +"_"+ 1 +".png");
        seAsignoDireccion = false;
        switch(tipoDisparo){//El disparo normal
            case 1:
                daño = 25; //daño inicial (que es bajo).
                velocidadDisparo = 5;
                numAnimaciones = 6;
                tiempoEntreDisparos = 1000; //1.5 segundos
                break;
            case 2: //Menos daño pero más velocidad
                daño = 15;
                velocidadDisparo = 6;
                numAnimaciones = 6;
                tiempoEntreDisparos = 850;
                break;
            case 3: //Más velocidad y daño
                daño = 35;
                velocidadDisparo = 7;
                numAnimaciones = 6;
                tiempoEntreDisparos = 500;
                break;
        }
        //public static GreenfootImage modificarEscalaImagen(GreenfootImage imagen, int divisor, int multiplicacion)
        setImage(Imagen.modificarEscalaImagen(getImage(), 1, 2));
        //Método que devuelve el ángulo dependiendo de nuestra direccion.
        turn(Direccion.getAnguloDireccion(direccion));
        pantalla = new Pantalla(this);
    }
    public void act(){
        mundo = getWorld();
        if(!pantalla.isObjetoLimite(mundo, getX(), getY())) //Si el disparo no está dentro de los límites.
            mundo.removeObject(this);
        animaDisparo();
    }
    /** Método que "anima" el disparo.*/
    private void animaDisparo(){
        if(animacionActual < numAnimaciones)
            animacionActual++;
        else
            animacionActual=1;
        setImage("Disparos/"+ tipoDisparo +"_"+ animacionActual +".png");
        setImage(Imagen.modificarEscalaImagen(getImage(), 1, 3));
        move(velocidadDisparo);
    }
    /** Método que tendrá el control de los disparos del jugador.*/
    public static long disparar(World mundoActual, GreenfootImage imagenNave,
                        long inicioDisparoMilis, int tipoDisparo, int direccion, int x, int y){
        /*Condición que revisará que se pulsó la tecla de disparo, pero habrá un delay de n milisegundos
            para no estar disparando todo el tiempo. Esto midiendo la hora del disparo y restando a la hora actual
            en milisegundos.*/
            /*Ahora guardaremos nuestra dirección para que sea la que siga el disparo*/
        if(Greenfoot.isKeyDown("space") && (System.currentTimeMillis()-inicioDisparoMilis) >= tiempoEntreDisparos){ //Aún falta implementar los tipos de disparo y todo lo relacionado
            inicioDisparoMilis = System.currentTimeMillis();
            //Tomar la imagen de la nave para tomarla en cuenta en la salida del disparo
            //Aparecer el objeto en las coordenadas actuales, pero un poco a la derecha para no empalmar la nave.
            //private void agregarDisparo(World mundoActual, GreenfootImage imagenNave, int tipoDisparo, int direccion, int x, int y)
            agregarDisparo(mundoActual, imagenNave, tipoDisparo, direccion, x, y);
        }
        return inicioDisparoMilis;
    }
    /** Método que agregará el disparo en el frente de la nave, para esto deberá tomar la dirección.*/
    private static void agregarDisparo(World mundoActual, GreenfootImage imagenNave, int tipoDisparo, int direccion, int x, int y){
        switch(direccion){
            case Direccion.DERECHA:
                x += imagenNave.getWidth()/2;  //Mitad de la nave más la otra mitad.
                break;
            case Direccion.ABAJO:
                y += imagenNave.getHeight()/2;
                break;
            case Direccion.IZQUIERDA:
                x -= imagenNave.getWidth()/2;  //Mitad de la nave más la otra mitad.
                break;
            case Direccion.ARRIBA:
                y -= imagenNave.getHeight()/2;
                break;
            default: break; //Que los disparos en diagonal sagan del centro de la nave mejor.
        }
        mundoActual.addObject(new Disparo(tipoDisparo, direccion), x, y);
    }
    public int getCordX(){
        return getX();
    }
    public int getCordY(){
        return getY();
    }
    /** Método que devuelve el daño actual del disparo.*/
    public static int getDaño(){
        return daño;
    }
    /** Setter del daño para cuando cambiemos de disparo.*/
    public void setDaño(int daño){
        daño = daño;
    }
}
