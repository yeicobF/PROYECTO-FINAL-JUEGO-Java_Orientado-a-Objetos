import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class NaveEnemiga here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

/* Character.compare(char 'A', char 'B'); Esto devuelve 0 si al comparar los caracteres son iguales, 
                                             >0 si el 1er valor es mayor que el segundo
                                             <0 si el 1er valor es menor que el segundo*/
public class NaveEnemiga extends Nave
{
    /**
     * Act - do whatever the NaveEnemiga wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    //La vida siempre será 100 por el constructor de la superclase
    /*Este constructor dará vida y tipo de disparo (aún no implementado) dependiendo del tipo de enemigo sea*/
    public NaveEnemiga(char tipoEnemigo, int cordX, int cordY){//Tal vez haga falta un MINIBOSS
        super(cordX, cordY);//public Nave(int cordX, int cordY)
        if(Character.compare(tipoEnemigo, '0')==0){//BOSS == "0"
            vida+=100;//Que las naves de BOSSES tengan más vida
            //tipoDisparo=1;//Disparo más potente pero más lento
            setImage("nave02.png");
            /*El código de abajo servirá para reescalar la imagen, ya que estaba muy grande*/
            GreenfootImage image = getImage(); //Tomar la imagen que modificaremos
            image.scale(image.getWidth() - 30, image.getHeight() - 30);//Reescalar imagen
            setImage(image);//Acomodar ahora sí la imagen modificada
        }
        else{//ENEMIGOS PEQUEÑOS
            vida-=50;//Que las naves de enemigos pequeños tengan menos vida (vida=100-50)
            //tipoDisparo=2;//Disparo menos potente pero más rápido
            setImage("pruebaSpriteEnemigoUFO.png");
            //REESCALAR IMAGEN
            GreenfootImage image = getImage(); //Tomar la imagen que modificaremos
            image.scale(image.getWidth() - 30, image.getHeight() - 30);//Reescalar imagen
            setImage(image);
        }
    }
    public void act() 
    {
        // Add your action code here.
    }    
}
