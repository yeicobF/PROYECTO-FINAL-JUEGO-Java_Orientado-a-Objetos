import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Esta es la superclase que manejará a las clases relacionadas con naves.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Nave extends Actor
{
    //Nave n; //Inicializar la nave para después instanciarla, aunque no es necesario porque esta clase no se utilizará
    public static final int UP=0;
    public static final int DOWN=1;
    public static final int LEFT=2;
    public static final int RIGHT=3;
    public static final int UP_RIGHT=4;
    public static final int UP_LEFT=5;
    public static final int DOWN_LEFT=6;
    public static final int DOWN_RIGHT=7;
    public int vida;
    protected int tipoDisparo; //Dependiendo del tipo del disparo cambiará su sprite. Estos serán como las mejoras.
    protected char diseño;//El diseño de la nave
    protected char tipoHabilidad;//Esto serán los PowerUps.
    private int cordX;//Nos ayudarán a cuando choquen con las piedras, otras naves, los disparos, etcétera.
    private int cordY;//CREO QUE NO SE NECESITAN PORQUE YA HAY UN getX y getY 
    public Nave(int cordX, int cordY){//char diseño para cuando tengamos más diseños
        vida=100;
        this.cordX=cordX;
        this.cordY=cordY;
    }//CONSTRUCTOR en el que se definirá si la nave es 0.- enemigo o 1.-Nosotros
    public void act() //act(int direccion) 
    {   
        //setDireccion(direccion); //Podría recibir aquí los parámetros y llamar a setDireccion, pero se puede hacer directo
        // if(Greenfoot.isKeyDown("up") || Greenfoot.isKeyDown("w")){
            // setDireccion(UP);
        // }
        // if(Greenfoot.isKeyDown("down") || Greenfoot.isKeyDown("s")){
            // setDireccion(DOWN);
        // }
        // if(Greenfoot.isKeyDown("left") || Greenfoot.isKeyDown("a")){
            // setDireccion(LEFT);
        // }
        // if(Greenfoot.isKeyDown("right") || Greenfoot.isKeyDown("d")){
            // setDireccion(RIGHT);
        // }
        // if((Greenfoot.isKeyDown("right")&&Greenfoot.isKeyDown("up") )|| (Greenfoot.isKeyDown("d")&&(Greenfoot.isKeyDown("w")))){
            // setDireccion(UP_RIGHT);
        // }
        // if((Greenfoot.isKeyDown("left")&&Greenfoot.isKeyDown("up") )|| (Greenfoot.isKeyDown("a")&&(Greenfoot.isKeyDown("w")))){
            // setDireccion(UP_LEFT);
        // }
        // if((Greenfoot.isKeyDown("right")&&Greenfoot.isKeyDown("down") )|| (Greenfoot.isKeyDown("d")&&(Greenfoot.isKeyDown("s")))){
            // setDireccion(DOWN_RIGHT);
        // }
        // if((Greenfoot.isKeyDown("left")&&Greenfoot.isKeyDown("down") )|| (Greenfoot.isKeyDown("a")&&(Greenfoot.isKeyDown("s")))){
            // setDireccion(DOWN_LEFT);
        // }
    }
    /*Clase que cambia la dirección dependiendo del parámetro que reciba. Además aquí mismo se ejecuta el movimiento.*/
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
                    cordY+=2;
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
                    cordY+=6;
                }
                else{
                    setLocation(getX()+4,getY());
                    cordY+=4;
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
}
