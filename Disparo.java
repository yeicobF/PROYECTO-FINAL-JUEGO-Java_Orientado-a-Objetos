import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Disparo here.
 * 
 * @author (Team Naves) 
 * @version (Domingo, 17 de mayo - Lunes, 18 de mayo de 2020)
 */
public class Disparo extends Actor
{
    /**
     * La clase disparo hereda de la superclase Nave porque las naves son las únicas que utilizan los disparos. Aunque
     *  ahora que lo pienso puedo acceder al disparo desde Nave___ sin necesidad de que estén vinculadas
     *  heredando de la misma superclase. Lo había pensado porque no sabía cómo acceder a los atributos
     *  si se instancia nada más en las naves, pero no es necesario que herede de Nave.
     */
    World w;
    MetodosGenerales m = new MetodosGenerales();//Instanciar la clase con los métodos generales.
    private int direccion; //Creo que no son necesarias las coordenadas por el getX() y getY()
    /* - EQUIVALENTES DE LAS DIRECCIONES - Sacadas de la clase Nave, por lo que se podrá hacer una generalización,
                                                pero hay que ver cómo.*/
    public static int daño; //Estática para que puedan acceder desde fuera y restar el daño hecho.
    private int velocidadDisparo;
    
    public static final int UP=0;
    public static final int DOWN=1;
    public static final int LEFT=2;
    public static final int RIGHT=3;
    public static final int UP_RIGHT=4;
    public static final int UP_LEFT=5;
    public static final int DOWN_LEFT=6;
    public static final int DOWN_RIGHT=7;
    /*Constructor para aparecer al disparo*/
    /*En cuanto a la direccion del disparo, no pude implementarlo porque no salía como debería salir, así que hay que revisarlo
        porque hay algo que falla. Por ahora solo comentaré las líneas para después implementar dicha función.*/
    public Disparo(int tipoDisparo, int cordX, int cordY){//, int direccion){//Recibirá las coordenadas de la nave para aparecer.
        //super(cordX, cordY);//[LO TENÍA ASÍ] Ya que hereda de Nave y lo requiere, pero le dará 100 de vida al disparo,cosa que es innecesaria, pero ya veremos qué podemos cambiar.
        // this.cordX=cordX; //Antes Comentados por la herencia que intenté con nave
        // this.cordY=cordY;
        //this.direccion=direccion;//Para guardar la direccion del disparo. - Por ahora no se pudo hacer, pero lo comentaré.
        if(tipoDisparo == 0){//El disparo normal
            daño = 25; //daño inicial (que es bajo).
            velocidadDisparo = 5;
            setImage("pruebaLaserShot.png");
            GreenfootImage image = getImage(); //Tomar la imagen que modificaremos
            image.scale(image.getWidth()/4, image.getHeight()/4);//Reescalar imagen a 1/5 de las medidas originales.
            setImage(image);//Acomodar ahora sí la imagen modificada
        }
    }
    public void act(){
        w = getWorld();
        move(velocidadDisparo);
        /*CUANDO EL OBJETO TOQUE EL LÍMITE DE PANTALLA (O a un enemigo en un futuro porque no lo hemos implementado)*
          eliminaremos el objeto.*/
        // if(getX() >= w.getWidth()-1)
            // getWorld().removeObject(this); //Así se elimina el disparo de pantalla
        limitePantalla();    
        
        //eliminarObjetosImpacto();//Método que eliminará al disparo y los objetos con los que impacta.
        /*ESTE MÉTODO LO TOMÉ DE setDireccion de la clase Nave, pero le quité las condiciones de más velocidad,
            para que así se dispare para la dirección a la que estemos apuntando.
            Por ahora no se pudo, así que se dejará como estaba pero con estas líneas comentadas.*/
        // switch(direccion){
        // case UP:
            // setRotation(0);
            // // setLocation(getX(),getY()-6);
            // // cordY-=4;
            // break;
        // case DOWN:
            // setRotation(180);
            // // setLocation(getX(),getY()+4);
            // // cordY+=2;
            // break;
        // case LEFT:
            // setRotation(270);
            // setLocation(getX()-4,getY());
            // cordX-=4;
            // break;
        // case RIGHT:
            // setRotation(90);
            // setLocation(getX()+4,getY());
            // cordX+=4;
            // break;
            
        // case UP_RIGHT:
            // setRotation(45);
            // setLocation(getX()+1,getY()-1);
            // cordX+=1;
            // cordY-=1;
            // break;
            
            // case UP_LEFT:
            // setRotation(315);
            // setLocation(getX()-1,getY()-1);
            // cordX-=1;
            // cordY-=1;
            // break;
            
            // case DOWN_LEFT:
            // setRotation(225);
            // setLocation(getX()-1,getY()+1);
            // cordX-=1;
            // cordY+=1;
            // break;
            
            // case DOWN_RIGHT:
            // setRotation(135);
            // setLocation(getX()+1,getY()+1);
            // cordX+=1;
            // cordY+=1;
            
            // break;
        // }
    }
    /*Método que desaparece a los objetos con los que impacta el disparo y a sí mismo.*/
    private void eliminarObjetosImpacto(){ //Por ahora Lunes, 11 de mayo me crasheaba, así que mejor lo implementaré en las otras clases.
        //public boolean eliminarObjetoChoque(Actor objetoChoque, Actor objetoRaiz, World mundoActual
        //public int eliminarObjetoChoque(Actor objetoChoque, Actor objetoRaiz, World mundoActual, int puntosSalud, int daño)
        // m.eliminarObjetoChoque(getOneObjectAtOffset(0, 0, Roca.class), this, (Espacio)getWorld());
        // m.eliminarObjetoChoque(getOneObjectAtOffset(0, 0, NaveEnemiga.class), this, (Espacio)getWorld());
    }
    /*Método que revisa si el disparo está dentro de los límites de la pantalla.*/
    private void limitePantalla(){
        if(getX() >= w.getWidth()-1 || getX() <= 0 //Límites en X
            || getY() <= (-w.getHeight())+1 || getY() >= w.getHeight()-1)//Límites en Y. Cuando y = 0 se encuentra en el centro de la pantalla.
            getWorld().removeObject(this); //Así se elimina el disparo de pantalla
    }
    public int getCordX(){
        return getX();
    }
    public int getCordY(){
        return getY();
    }
    // No es necesario el Getter porque es público el daño
    // public int getDaño(){
        // return daño;
    // }
}
