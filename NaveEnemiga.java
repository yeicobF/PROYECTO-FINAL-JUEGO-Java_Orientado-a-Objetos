import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
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
