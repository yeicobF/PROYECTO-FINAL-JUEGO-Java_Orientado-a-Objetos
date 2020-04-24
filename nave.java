import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class nave here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Nave extends Actor
{
    public static final int UP=0;
    public static final int DOWN=1;
    public static final int LEFT=2;
    public static final int RIGHT=3;
    public int vida = 100;
    protected int tipoDisparo; //Dependiendo del tipo del disparo cambiará su sprite. Estos serán como las mejoras.
    protected char diseño;//El diseño de la nave
    protected char tipoHabilidad;//Esto serán los PowerUps.
    private int cordX=0;//Nos ayudarán a cuando choquen con las piedras, otras naves, los disparos, etcétera.
    private int cordY=0;//CREO QUE NO SE NECESITAN PORQUE YA HAY UN getX y getY 
    public void Nave(){}//CONSTRUCTOR
    public void act() 
    {   
        if(Greenfoot.isKeyDown("up") || Greenfoot.isKeyDown("w")){
            setDireccion(UP);
        }
        if(Greenfoot.isKeyDown("down") || Greenfoot.isKeyDown("s")){
            setDireccion(DOWN);
        }
        if(Greenfoot.isKeyDown("left") || Greenfoot.isKeyDown("a")){
            setDireccion(LEFT);
        }
        if(Greenfoot.isKeyDown("right") || Greenfoot.isKeyDown("d")){
            setDireccion(RIGHT);
        }
    }
    public void setDireccion(int direccion){
        switch(direccion){
            case UP:
                setRotation(0);
                if(Greenfoot.isKeyDown("space")){
                    setLocation(getX(),getY()-4);
                    cordY-=4;
                }
                else{
                    setLocation(getX(),getY()-2);
                    cordY-=2;
                }
                break;
            case DOWN:
                setRotation(180);
                if(Greenfoot.isKeyDown("space")){
                    setLocation(getX(),getY()+4);
                    cordY+=4;
                }
                else{
                    setLocation(getX(),getY()+2);
                    cordY+=2;
                }
                break;
            case LEFT:
                setRotation(270);
                if(Greenfoot.isKeyDown("space")){
                    setLocation(getX()-4,getY());
                    cordX-=4;
                }
                else{
                    setLocation(getX()-2,getY());
                    cordX-=2;
                }
                break;
            case RIGHT:
                setRotation(90);
                if(Greenfoot.isKeyDown("space")){
                    setLocation(getX()+4,getY());
                    cordY+=4;
                }
                else{
                    setLocation(getX()+2,getY());
                    cordY+=2;
                }
                break;
        }       
    }
    //Método para verificar mediante un parámetro de coordenadas X y Y si el objeto ya chocó con algún otro
    //public boolean choqueObjeto
    public int getCordX(){
        return cordX;
    }
    public int getCordY(){
        return cordY;
    }
}
