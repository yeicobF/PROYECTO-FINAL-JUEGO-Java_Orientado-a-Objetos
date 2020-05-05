import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class roca here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Roca extends Actor
{
    Roca[] r;
    World w;
    int x, y, i;
    public void Roca(){}//CONSTRUCTOR
   public Roca[] crearRocas(int numRocas){//Esto para manejar a las rocas y poder controlarlas en conjunto
       w = getWorld();
       r = new Roca[numRocas];
        for(i=0;i<numRocas;i++){
            //r[i] = new Roca();
            r[i].x = Greenfoot.getRandomNumber(w.getHeight());//Para guardar el x y y de cada roca.
            r[i].y = Greenfoot.getRandomNumber(w.getWidth());
            //w.addObject(r[i],x,y);
        }
        return r;//Regresa todo el arreglo de rocas
    }
   public void act(){
        // Add your action code here.
        w = getWorld();
        move(2);//Método que mueve a cierta velocidad el objeto
        if(getX()>=w.getWidth()-5||getX()<=5){
            turn(180);
            if(Greenfoot.getRandomNumber(100)<90){
                turn(Greenfoot.getRandomNumber(90-45));
            }
        }
        if(getY()>=w.getHeight()-5||getY()<=5){
            turn(180);
            if(Greenfoot.getRandomNumber(100)<90){
                turn(Greenfoot.getRandomNumber(90-45));
            }
        }
    }    
    public boolean chocanRocas(int x, int y){//Método que evaluará si dos rocas están chocando para eliminarlas
        if(getX()>=x-5 && getX()<=x+5 && getY()>=y-5 && getY()<=y+5)//Comparar posición de roca parámetro con la actual
            return true;
        else
            return false;//No necesita else porque si no entra en el if, llegará aquí directamente
    }
    public int getCoordX(){
        return getX();
    }
    public int getCoordY(){
        return getY();
    }
}