import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Esta es una subclase de la superclase Nave que tiene como función manejar las naves que controlará el jugador.
 * 
 * @author (your name) 
 * @version (Martes, 5 de mayo de 2020)
 */
public class NaveAliada extends Nave
{
    /**
     * Act - do whatever the NaveAliada wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    Disparo disparo;
    World w;
    //Medir el tiempo para no poder disparar de manera tan seguida, tener un delay entre disparo y disparo.
    long inicioDisparoMillis=0;
    int direccion;
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
        /*Condición que revisará que se pulsó la tecla de disparo, pero habrá un delay de 1250 milisegundos
            para no estar disparando todo el tiempo. Esto midiendo la hora del disparo y restando a la hora actual
            en milisegundos.*/
            /*Ahora guardaremos nuestra dirección para que sea la que siga el disparo*/
            /*Por ahora, Martes, 5 de mayo de 2020, no se pudo hacer lo de la dirección del disparo,
                pero ahí quedará la variable para cuando lo implementemos nos puede ser útil.*/
        if(Greenfoot.isKeyDown("space") && (System.currentTimeMillis()-inicioDisparoMillis)>=1250.0){ //Aún falta implementar los tipos de disparo y todo lo relacionado
            inicioDisparoMillis = System.currentTimeMillis();
            w = getWorld(); //Para agregar el objeto
            disparo = new Disparo('0', getX(), getY());//, direccion);//Instanciar el disparo en las coordenadas actuales y nuestra direccion.
            //Tomar la imagen de la nave para tomarla en cuenta en la salida del disparo
            GreenfootImage image = getImage();
            //Aparecer el objeto en las coordenadas actuales, pero un poco a la derecha para no empalmar la nave.
            w.addObject(disparo, getX()+image.getWidth()/2, getY());
        }
        if(Greenfoot.isKeyDown("up") || Greenfoot.isKeyDown("w")){//ARRIBA
           super.setDireccion(UP); //super.act(direccion); //Se podría hacer así si recibiera esos parámetros el método act() de la superclse
           direccion = UP;
        }
        if(Greenfoot.isKeyDown("down") || Greenfoot.isKeyDown("s")){
            super.setDireccion(DOWN);//ABAJO
            direccion = DOWN;
        }
        if(Greenfoot.isKeyDown("left") || Greenfoot.isKeyDown("a")){
            super.setDireccion(LEFT);//IZQUIERDA
            direccion = LEFT;
        }
        if(Greenfoot.isKeyDown("right") || Greenfoot.isKeyDown("d")){
            super.setDireccion(RIGHT);//DERECHA
            direccion = RIGHT;
        }
        /*Los métodos siguientes están así porque se revisan todas las posibilidades. Es decir, todas las combinaciones
            de teclas que podrían causar un tipo de movimiento en diagonal. Porque esto se toma en cuenta para
            modificar la posición de los sprites.*/
        if((Greenfoot.isKeyDown("right")&&Greenfoot.isKeyDown("up") )|| (Greenfoot.isKeyDown("d")&&Greenfoot.isKeyDown("w")) 
                || (Greenfoot.isKeyDown("right")&&Greenfoot.isKeyDown("w")) || (Greenfoot.isKeyDown("d")&&Greenfoot.isKeyDown("up"))){
            super.setDireccion(UP_RIGHT); //ARRIBA_DERECHA
            direccion = UP_RIGHT;
        }
        if((Greenfoot.isKeyDown("left")&&Greenfoot.isKeyDown("up")) || (Greenfoot.isKeyDown("a")&&Greenfoot.isKeyDown("w"))
                || (Greenfoot.isKeyDown("left")&&Greenfoot.isKeyDown("w")) || (Greenfoot.isKeyDown("a")&&Greenfoot.isKeyDown("up"))){
            super.setDireccion(UP_LEFT);//ARRIBA_IZQUIERDA
            direccion = UP_LEFT;
        }
        if((Greenfoot.isKeyDown("right")&&Greenfoot.isKeyDown("down") )|| (Greenfoot.isKeyDown("d")&&Greenfoot.isKeyDown("s"))
                || (Greenfoot.isKeyDown("right")&&Greenfoot.isKeyDown("s")) || (Greenfoot.isKeyDown("d")&&Greenfoot.isKeyDown("down"))){
            super.setDireccion(DOWN_RIGHT);//ABAJO_DERECHA
            direccion = DOWN_RIGHT;
        }
        if((Greenfoot.isKeyDown("left")&&Greenfoot.isKeyDown("down") )|| (Greenfoot.isKeyDown("a")&&Greenfoot.isKeyDown("s"))
                || (Greenfoot.isKeyDown("left")&&Greenfoot.isKeyDown("s")) || (Greenfoot.isKeyDown("a")&&Greenfoot.isKeyDown("down"))){
            super.setDireccion(DOWN_LEFT);//ABAJO_IZQUIERDA
            direccion = DOWN_LEFT;
        }
    }  
    /*Método para obtener la Coordenada en X del disparo. Pero para esto hay que verificar que el disparo exista.
        Si el disparo no existe, entonces devolver -1 en x porque no se puede acceder a esa coordenada.*/
    public int getCordXDisparo(){
        if(disparo.getWorld() != null)
            return disparo.getCordX();
        else
            return -1;
    }
    /*Método para obtener la Coordenada en Y del disparo. Verificamos que el disparo exista y si no, deolver un valor no alcanzable.*/
    public int getCordYDisparo(){
        if(disparo.getWorld() != null)
            return disparo.getCordY();
        else
            return w.getHeight()+10;//Si no existe, regresar un valor inexistente en el rango del área de juego.
    }
}
