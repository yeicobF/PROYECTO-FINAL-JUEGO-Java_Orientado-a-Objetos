import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class nave here.
 * Write a description of class Nave here.
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
    public static final int UP_RIGHT=4;
    public static final int UP_LEFT=5;
    public static final int DOWN_LEFT=6;
    public static final int DOWN_RIGHT=7;
    public int vida = 100;
    protected int tipoDisparo; //Dependiendo del tipo del disparo cambiará su sprite. Estos serán como las mejoras.
    protected char diseño;//El diseño de la nave
    protected char tipoHabilidad;//Esto serán los PowerUps.
    protected int cordX=0;//Nos ayudarán a cuando choquen con las piedras, otras naves, los disparos, etcétera.
    protected int cordY=0;//CREO QUE NO SE NECESITAN PORQUE YA HAY UN getX y getY 
    public void Nave(){}//CONSTRUCTOR
    /**
     * Act - do whatever the Nave wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
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
        if((Greenfoot.isKeyDown("right")&&Greenfoot.isKeyDown("up") )|| (Greenfoot.isKeyDown("d")&&(Greenfoot.isKeyDown("w")))){
            setDireccion(UP_RIGHT);
        }
        if((Greenfoot.isKeyDown("left")&&Greenfoot.isKeyDown("up") )|| (Greenfoot.isKeyDown("a")&&(Greenfoot.isKeyDown("w")))){
            setDireccion(UP_LEFT);
        }
        if((Greenfoot.isKeyDown("right")&&Greenfoot.isKeyDown("down") )|| (Greenfoot.isKeyDown("d")&&(Greenfoot.isKeyDown("s")))){
            setDireccion(DOWN_RIGHT);
        }
        if((Greenfoot.isKeyDown("left")&&Greenfoot.isKeyDown("down") )|| (Greenfoot.isKeyDown("a")&&(Greenfoot.isKeyDown("s")))){
            setDireccion(DOWN_LEFT);
        }
    }
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
    //Método para verificar mediante un parámetro de coordenadas X y Y si el objeto ya chocó con algún otro
    //public boolean choqueObjeto
    public int getCordX(){
        return cordX;
    }
    public int getCordY(){
        return cordY;
    }
    {
        // Add your action code here.
    }    
}