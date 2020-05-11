import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Esta es una subclase de la superclase Nave que tiene como función manejar las naves que controlará el jugador.
 * 
 * @author (your name) 
 * @version (Domingo, 10 de mayo - Lunes, 11 de mayo de 2020)
 */
public class NaveAliada extends Nave
{
    /**
     * Act - do whatever the NaveAliada wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    Disparo disparo;
    World w;
    /*protected MetodosGenerales m = new MetodosGenerales();//Variable para usar sus métodos como el de reescalar la imagen.
        Se puede usar aquí porque es protected en la superclase Nave.*/
    //Medir el tiempo para no poder disparar de manera tan seguida, tener un delay entre disparo y disparo.
    /*GreenfootImage imagen; //Esta variable guardará la imagen actual para reescalarla con su respectivo método.
        * NO HACE FALTA LA VARIABLE PORQUE LE MÉTODO REGRESA LA IMAGEN YA MODIFICADA * */
    private long inicioDisparoMillis=0;
    private int direccion;
    /*El número de vidas será estático para que no se reinicie sino que se quede su número cada que se reinicie el mundo.*/
    private static int vidas = 3;//Número de vidas actuales del jugador. Estas se descuentan al perder todos los puntos de Salud.
    private int puntuacion = 0;//La puntuación del jugador que se reiniciará al morir
    //CONSTRUCTOR que tomará en cuenta el diseño de la nave, por ejemplo, para cuandola modificamos
    public NaveAliada(int tipoDisparo, int diseñoNave, int vidas){
        super(tipoDisparo, diseñoNave);//Los puntos de salud son de 100 como base, ya que el super constructor (de la clase Nave) lo establece.
        this.vidas = vidas;//Establecer el número de vidas, ya que estas pueden cambiar al morir.
        setImage("Naves/Aliadas/NaveA"+ diseñoNave+ ".png"); //De esta forma pondremos la imagen dependiendo del diseño para no repetirlo en cada diseño.
        /*El método de abajo (implementado en la clase Espacio) servirá para reescalar la imagen.
            public GreenfootImage modificarEscalaImagen(GreenfootImage imagen, int divsior, int multiplicacion)*/
            //imagen = espacio.modificarEscalaImagen(getImage(), 2, 1); //Enviar la imagen con sus modificadores y establecerla reescalada.
        setImage(m.modificarEscalaImagen(getImage(), 2, 1));//Acomodar la imagen modificada. La recibimos del método directamente. No necesitamos ninguna variable.
        /*Hay que tener una condición para el diseño de la nave. Este se estableció con el super construcor.*/
        if(diseñoNave ==0){ //La nave potente por ser la más poderosa que se podrá obtener
            //setImage("NavePotente.png");
            puntosSalud+=100;//Como es una nave más poderosa, se aumentará su vida
            //tipoDisparo= Algún disparo poderoso que determinaremos más adelante.
            
        }//Si no, que se utilice la nave predeterminada que es la que establecimos con los diseños de Greenfoot
        // else
            // if(Character.compare(diseñoNave, '1')==0)//Algún otro diseño y así sucesivamente
            // //vida+=50;//Cada que se modifique la nave aumentará la vida y cosas así
    }
    /*Clase para el movimiento manual de la nave. La diferimos del Movimiento de la nave enemiga porque 
     * esa se moverá con numeros generados de manera aleatoria. Si la podemos hacer más general, lo haremos.*/
    public void act() 
    {
        disparar();
        movimiento();
        // /*Método que baja una vida al jugador si choca con una roca, con una nave enemiga o con un disparo enemigo (aún no implementado).*/ 
        // Ya implementé esto en un método
        choqueNave();
        morir(); //Esto aún no funciona por lo de las vidas que se reinician al crear el nuevo mundo al morir.
        // if(m.eliminarObjetoChoque(getOneObjectAtOffset(0, 0, Roca.class), this,  (Espacio)getWorld())
            // || m.eliminarObjetoChoque(getOneObjectAtOffset(0, 0, NaveEnemiga.class), this,  (Espacio)getWorld())){
            // vidas--;
            // Greenfoot.setWorld(new Espacio());//Este método crea el mundo de nuevo después de morir.
            // /*Aunque para el reinicio hay que tomar en cuenta el reinicio de vidas y todo eso.*/
        // }
    }  

    /*Método que controlará el movimiento de nuestra nave.*/
    public void movimiento(){
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
    /*Método que tendrá el control de los disparos del jugador.*/
    public void disparar(){
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
            w.addObject(disparo, getX()+getImage().getWidth()/2, getY());
        }
    }
    /*Método para ver si la nave choca con algo y elimina los objetos que chocaron, además baja el número de vidas.*/
    /*Método que baja una vida al jugador si choca con una roca, con una nave enemiga o con un disparo enemigo (aún no implementado).*/ 
    public void choqueNave(){
        if(m.eliminarObjetoChoque(getOneObjectAtOffset(0, 0, Roca.class), this, (Espacio)getWorld())
            || m.eliminarObjetoChoque(getOneObjectAtOffset(0, 0, NaveEnemiga.class), this, (Espacio)getWorld())){
            vidas--;
            //REINICIAR NIVEL PERO CON LAS VIDAS, PUNTUACIÓN, ETCÉTERA INTACTOS DESPUÉS DE SU MODIFICACIÓN
            Greenfoot.setWorld(new Espacio());//Este método crea el mundo de nuevo después de morir.
            //Tal vez así las vidas no se reiniciarán
                /*Aunque para el reinicio hay que tomar en cuenta el reinicio de vidas y todo eso.*/
        }
    }
    //Desaparecer si ya perdimos todas las vidas.
    public void morir(){
        if(vidas==0)
            getWorld().removeObject(this);
    }
    
    /*Método para obtener las vidas actuales del jugador.*/
    public int getVidasJugador(){
        return vidas;
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
