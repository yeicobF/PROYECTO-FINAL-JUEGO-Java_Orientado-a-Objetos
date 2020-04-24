        import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
        
        /**
         * Write a description of class roca here.
         * 
         * @author (your name) 
         * @version (a version number or a date)
         */
        public class Roca extends Actor
        {
           public void Roca(){//CONSTRUCTOR
               int x, y, i;
                for(i=0;i<numeroRocas;i++){
                    r[i] = new Roca();
                    x = Greenfoot.getRandomNumber(getHeight());
                    y = Greenfoot.getRandomNumber(getWidth());
                    addObject(r[i],x,y);
                }
            }
           public void act() 
            {
                // Add your action code here.
                move(2);//Método que mueve a cierta velocidad el objeto
                World m=getWorld();
                if(getX()>=m.getWidth()-5||getX()<=5){
                    turn(180);
                    if(Greenfoot.getRandomNumber(100)<90){
                        turn(Greenfoot.getRandomNumber(90-45));
                    }
                }
                if(getY()>=m.getHeight()-5||getY()<=5){
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
