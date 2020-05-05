import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class NaveEnemiga here.
 * 
 * @author (your name) 
 * @version (Martes, 5 de mayo de 2020)
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
    World w;
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
            setImage("pruebaSpaceship1.png");
            //REESCALAR IMAGEN
            GreenfootImage image = getImage(); //Tomar la imagen que modificaremos
            image.scale(image.getWidth()/10, image.getHeight()/10);//Reescalar imagen
            setImage(image);
        }
        setRotation(270);
    }
    public void act() //Este código lo reutilicé de la clase Roca, por lo que lo podría poner en una clase más general
    {   /*Aquí con un random se verá la dirección en la que se moverá la nave. 
            Dependiendo del random (que será el ángulo de dirección), se moverá a una dirección.*/
        // Add your action code here.
        w = getWorld();
        int random;//Quisiera que las naves enemigas apuntaran hacia nosotros y si no al menos que apuntaran hacia la izquierda.
        move(3);//Método que mueve a cierta velocidad el objeto
        //Si el objeto alcanza los límites en x o y, se dará la vuelta. Las limitaremos a la mitad de la pantalla.
        if(getX()>=w.getWidth()-5 || getX()<=w.getWidth()/2 || getY()>=w.getHeight()-5||getY()<=5){ 
            turn(180);
            if(getX()<=w.getWidth()/2)
                getImage().mirrorHorizontally();
            random = Greenfoot.getRandomNumber(100);
            if(random < 90){//Cambia el ángulo del cómo se ven los sprites.
                random = Greenfoot.getRandomNumber(90-45);
                turn(random);
            }
            //setRotation(270);//Quiero que se mueva para todos lados pero siempre volteando a ver nuestra nave.
        }
        /*MÉTODO QUE VERIFICARÁ SI UNA ROCA CHOCÓ CON ALGO. Necesitamos ver si podemos implemementar esto de manera
            más general porque también será necesario en la clase NaveEnemiga*/
            /*Copié este método de la clase Roca, así que sí debería poder implementarse de manera más general, pero
               por ahora lo copio aquí también.*/
        /*Se heredan todas las características, y se ponen como parámetros 0, 0 
           porque de esta manera vamos a saber que los objetos chocaron.*/
        Actor naveAliada = getOneObjectAtOffset(0, 0, NaveAliada.class);
        Actor disparoNave = getOneObjectAtOffset(0, 0, Disparo.class);
        if(naveAliada != null){ //Aquí se va a revisar si chocó con nuestra nave
            Espacio e = (Espacio)getWorld(); //Se toma el mundo actual
            getWorld().removeObject(naveAliada); //Se elimina la nave
            getWorld().removeObject(this); //Se elimina la roca
            //AQUÍ TENGO QUE TOMAR EN CUENTA LAS VIDAS QUE TENGO PARA IR BAJÁNDOLAS
            //naveAliada.vidas++; //Esto solo es conceptual.
        }
        if(disparoNave != null){ //Aquí sevisamos el el disparo chocó con la roca
            /*El único problema que veo es que cuando se elimina ni siquiera chocan los objetos. Hay que arreglar esto.*/
            Espacio e = (Espacio)getWorld(); //Se toma el mundo actual
            getWorld().removeObject(disparoNave); //Se elimina el disparo
            getWorld().removeObject(this); //Se elimina la roca
        }
    }
    public int getCordX(){
        return getX();
    }
    public int getCordY(){
        return getY();
    }
}
