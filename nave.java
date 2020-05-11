import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Esta es la superclase que manejará a las clases relacionadas con naves.
 * 
 * @author (your name) 
 * @version (Domingo, 10 de mayo - Lunes, 11 de mayo de 2020)
 */
public class Nave extends Actor
{
    //Nave n; //Inicializar la nave para después instanciarla, aunque no es necesario porque esta clase no se utilizará
    protected MetodosGenerales m = new MetodosGenerales();//Variable para usar sus métodos como el de reescalar la imagen.
    public static final int UP=0;
    public static final int DOWN=1;
    public static final int LEFT=2;
    public static final int RIGHT=3;
    public static final int UP_RIGHT=4;
    public static final int UP_LEFT=5;
    public static final int DOWN_LEFT=6;
    public static final int DOWN_RIGHT=7;
    protected int puntosSalud;//Puntos de salud actuales. Al perder todos los puntos de salud se pierde una vida.
    protected int tipoDisparo; //Dependiendo del tipo del disparo cambiará su sprite. Estos serán como las mejoras.
    protected int diseño;//El diseño de la nave
    protected int tipoHabilidad;//Esto serán los PowerUps.
    /*NO NECESITAMOS coordX ni coordY porque ya están getX() y getY()*/
    public Nave(){}//Constructor vacío para no tener problemas en Disparo
    public Nave(int tipoDisparo, int diseño){//char diseño para cuando tengamos más diseños
        puntosSalud=100;//Puntos de salud estándar = 100. Cambiarán dependiendo del tipo de nave y su poder.
        this.tipoDisparo = tipoDisparo;//Aquí condicionaremos para el diseño y eso pero en la clase Disparo.
        this.diseño = diseño; //De esto dependerá el diseño que tendrá la nave.
    }//CONSTRUCTOR en el que se definirá si la nave es 0.- enemigo o 1.-Nosotros
    public void act() //act(int direccion) 
    {   
    }
    /*Clase que cambia la dirección dependiendo del parámetro que reciba. Además aquí mismo se ejecuta el movimiento.*/
    public void setDireccion(int direccion){
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
    //Método para verificar mediante un parámetro de coordenadas X y Y si el objeto ya chocó con algún otro
    //Aunque creo que ya no es necesario porque ya se implementó la forma de hacerlo
    //public boolean choqueObjeto
    public int getCordX(){
        return getX();
    }
    public int getCordY(){
        return getY();
    }
}
