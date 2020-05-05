import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Esta es una subclase de la superclase Nave que tiene como función manejar las naves que controlará el jugador.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class NaveAliada extends Nave
{
    /**
     * Act - do whatever the NaveAliada wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    //CONSTRUCTOR que tomará en cuenta el diseño de la nave, por ejemplo, para cuandola modificamos
    public NaveAliada(char diseñoNave, int cordX, int cordY){
        super(cordX, cordY);//La vida es de 100, ya que el super constructor (de la clase Nave) lo establece.
        if(Character.compare(diseñoNave, '0')==0){ //La nave potente por ser la más poderosa que se podrá obtener
            setImage("NavePotente.png");
            vida+=100;//Como es una nave más poderosa, se aumentará su vida
            //tipoDisparo= Algún disparo poderoso que determinaremos más adelante.
            /*El código de abajo servirá para reescalar la imagen, ya que estaba muy grande*/
            GreenfootImage image = getImage(); //Tomar la imagen que modificaremos
            image.scale(image.getWidth()/5*1, image.getHeight()/5*1);//Reescalar imagen a 1/5 de las medidas originales.
            setImage(image);//Acomodar ahora sí la imagen modificada
        }//Si no, que se utilice la nave predeterminada que es la que establecimos con los diseños de Greenfoot
        // else
            // if(Character.compare(diseñoNave, '1')==0)//Algún otro diseño y así sucesivamente
            // //vida+=50;//Cada que se modifique la nave aumentará la vida y cosas así
    }
    /*Clase para el movimiento manual de la nave. La diferimos del Movimiento de la nave enemiga porque 
     * esa se moverá con numeros generados de manera aleatoria. Si la podemos hacer más general, lo haremos.*/
    public void act() 
    {
        if(Greenfoot.isKeyDown("up") || Greenfoot.isKeyDown("w")){//ARRIBA
           super.setDireccion(UP); //super.act(direccion); //Se podría hacer así si recibiera esos parámetros el método act() de la superclse
        }
        if(Greenfoot.isKeyDown("down") || Greenfoot.isKeyDown("s")){
            super.setDireccion(DOWN);//ABAJO
        }
        if(Greenfoot.isKeyDown("left") || Greenfoot.isKeyDown("a")){
            super.setDireccion(LEFT);//IZQUIERDA
        }
        if(Greenfoot.isKeyDown("right") || Greenfoot.isKeyDown("d")){
            super.setDireccion(RIGHT);//DERECHA
        }
        /*Los métodos siguientes están así porque se revisan todas las posibilidades. Es decir, todas las combinaciones
            de teclas que podrían causar un tipo de movimiento en diagonal. Porque esto se toma en cuenta para
            modificar la posición de los sprites.*/
        if((Greenfoot.isKeyDown("right")&&Greenfoot.isKeyDown("up") )|| (Greenfoot.isKeyDown("d")&&Greenfoot.isKeyDown("w")) 
                || (Greenfoot.isKeyDown("right")&&Greenfoot.isKeyDown("w")) || (Greenfoot.isKeyDown("d")&&Greenfoot.isKeyDown("up"))){
            super.setDireccion(UP_RIGHT); //ARRIBA_DERECHA
        }
        if((Greenfoot.isKeyDown("left")&&Greenfoot.isKeyDown("up")) || (Greenfoot.isKeyDown("a")&&Greenfoot.isKeyDown("w"))
                || (Greenfoot.isKeyDown("left")&&Greenfoot.isKeyDown("w")) || (Greenfoot.isKeyDown("a")&&Greenfoot.isKeyDown("up"))){
            super.setDireccion(UP_LEFT);//ARRIBA_IZQUIERDA
        }
        if((Greenfoot.isKeyDown("right")&&Greenfoot.isKeyDown("down") )|| (Greenfoot.isKeyDown("d")&&Greenfoot.isKeyDown("s"))
                || (Greenfoot.isKeyDown("right")&&Greenfoot.isKeyDown("s")) || (Greenfoot.isKeyDown("d")&&Greenfoot.isKeyDown("down"))){
            super.setDireccion(DOWN_RIGHT);//ABAJO_DERECHA
        }
        if((Greenfoot.isKeyDown("left")&&Greenfoot.isKeyDown("down") )|| (Greenfoot.isKeyDown("a")&&Greenfoot.isKeyDown("s"))
                || (Greenfoot.isKeyDown("left")&&Greenfoot.isKeyDown("s")) || (Greenfoot.isKeyDown("a")&&Greenfoot.isKeyDown("down"))){
            super.setDireccion(DOWN_LEFT);//ABAJO_IZQUIERDA
        }
    }    
}
