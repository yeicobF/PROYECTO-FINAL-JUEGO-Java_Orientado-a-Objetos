import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class nave here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class nave extends Actor
{
    public static final int UP=0;
    public static final int DOWN=1;
    public static final int LEFT=2;
    public static final int RIGHT=3;
    public void nave(){
        
    }
    public void act() 
    {   
        if(Greenfoot.isKeyDown("up")){
            setDireccion(UP);
        }
        if(Greenfoot.isKeyDown("down")){
            setDireccion(DOWN);
        }
        if(Greenfoot.isKeyDown("left")){
            setDireccion(LEFT);
        }
        if(Greenfoot.isKeyDown("right")){
            setDireccion(RIGHT);
        }
    }
    public void setDireccion(int direccion){
        switch(direccion){
            case UP:
                setRotation(0);
                if(Greenfoot.isKeyDown("space")){
                    setLocation(getX(),getY()-4);
                }
                else{
                    setLocation(getX(),getY()-1);
                }
                break;
            case DOWN:
                setRotation(180);
                if(Greenfoot.isKeyDown("space")){
                    setLocation(getX(),getY()+4);
                }
                else{
                    setLocation(getX(),getY()+1);
                }
                break;
            case LEFT:
                setRotation(270);
                if(Greenfoot.isKeyDown("space")){
                    setLocation(getX()-4,getY());
                }
                else{
                    setLocation(getX()-1,getY());
                }
                break;
            case RIGHT:
                setRotation(90);
                if(Greenfoot.isKeyDown("space")){
                    setLocation(getX()+4,getY());
                }
                else{
                    setLocation(getX()+1,getY());
                }
                break;
        }       
    }
}
