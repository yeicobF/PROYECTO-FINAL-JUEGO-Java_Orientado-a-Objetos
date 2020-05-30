import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Esta es una subclase de la superclase Nave que tiene como función manejar las naves que controlará el jugador.
 * 
 * @author (Team Naves) 
 * @version (Viernes, 29 de mayo de 2020)
 */
public class NaveAliada extends Nave
{
    /**
     * Act - do whatever the NaveAliada wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    protected World w;
    /*protected MetodosGenerales m = new MetodosGenerales();//Variable para usar sus métodos como el de reescalar la imagen.
        Se puede usar aquí porque es protected en la superclase Nave.*/
    //Medir el tiempo para no poder disparar de manera tan seguida, tener un delay entre disparo y disparo.
    /*GreenfootImage imagen; //Esta variable guardará la imagen actual para reescalarla con su respectivo método.
        * NO HACE FALTA LA VARIABLE PORQUE LE MÉTODO REGRESA LA IMAGEN YA MODIFICADA * */
    /*Bandera para que no se esté haciendo el setImage() constantemente cuando no se cumplan ciertas condiciones.
       Por ejemplo, que no lo esté haciendo una y otra vez cuando se terminó el escudo, sino solo se haga una vez
        porque la bandera va a cambiar una vez cambie.*/
    private static boolean diseñoOriginalActivo; //Estático para revisar al tomar el escudo no destruya a los enemigos.
    private long inicioDisparoMillis=0;
    private static int vidas = 3;// Inicializar vidas en 3 como estáticas para que al instanciar no se reinicien. Aunque esto aún no funciona.
    private int direccion;
    private int puntosMenosAlMorir = -20;
    /*El número de vidas será estático para que no se reinicie sino que se quede su número cada que se reinicie el mundo.*/
    // private static int vidas = 3;//Número de vidas actuales del jugador. Estas se descuentan al perder todos los puntos de Salud.
    private static int puntos = 0;//La puntuación del jugador que se reiniciará al morir
    //CONSTRUCTOR que tomará en cuenta el diseño de la nave, por ejemplo, para cuandola modificamos
    public NaveAliada(){}//Constructor vacío para MostrarVidas.
    public NaveAliada(int tipoDisparo, int diseñoNave){
        //-> El tipo de disparo lo debería determinar el diseño y no deberíamos mandarlo. Esto es una posibilidad, pero hay que pensarlo.
        super(tipoDisparo, diseñoNave);//Los puntos de salud son de 100 como base, ya que el super constructor (de la clase Nave) lo establece.
        diseñoOriginalActivo = true; //El diseño original es el que no ha sido afectado por los items.
        /* Creo que esto ya no es necesario.
         * vidas-=1;/*Establecer el número de vidas, ya que estas pueden cambiar al morir. Como es estática, siempre valdrá lo mismo.
                    Entonces cuando se vuelva a instanciar el mundo, lo hará con 3 vidas que son las iniciales e irá restando.*/
        setImage("Naves/Aliadas/NaveA"+ diseñoNave+ ".png"); //De esta forma pondremos la imagen dependiendo del diseño para no repetirlo en cada diseño.
        /*El método de abajo (implementado en la clase Espacio) servirá para reescalar la imagen.
            public GreenfootImage modificarEscalaImagen(GreenfootImage imagen, int divsior, int multiplicacion)*/
            //imagen = espacio.modificarEscalaImagen(getImage(), 2, 1); //Enviar la imagen con sus modificadores y establecerla reescalada.
        setImage(m.modificarEscalaImagen(getImage(), 2, 1));//Acomodar la imagen modificada. La recibimos del método directamente. No necesitamos ninguna variable.
        /*Hay que tener una condición para el diseño de la nave. Este se estableció con el super construcor.*/
        if(diseñoNave ==0){ //La nave potente por ser la más poderosa que se podrá obtener
            //setImage("NavePotente.png");
            puntosSalud+= 100;//Como es una nave más poderosa, se aumentará su vida
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
        //Revisamos que el item siga dentro de su tiempo y que haya chocado, que haya un tipo de item.
        if(choqueItem() == 2 && System.currentTimeMillis() < Items.getTiempoFinalItem()){ //El item actual es el escudo.
            /*Pasamos que el daño sea 0 para que no nos afecte a nosotros pero sí dañe lo demás. 
             *  O tal vez que simplemente no pase nada al chocar con algo más.*/
            morirChoque(0); //Mejor que no le pase nada al chocar con los objetos. Aunque necesito checarlo.
        }
        else
            if(!diseñoOriginalActivo){ //El item no es el 2 o excedió el tiempo
                setImage("Naves/Aliadas/NaveA"+ diseñoNave+ ".png"); //Reestablecer el diseño original al terminar el efecto.
                setImage(m.modificarEscalaImagen(getImage(), 2, 1)); //Reescalarla, ya que volverá a tomar el tamaño original.
                diseñoOriginalActivo = true;
                Items.setItemActivoFalso(); //Hacemos al item falso luego de terminar su periodo.
                // Mandar el tiempo en el que se terminó el item
                // Items.setTiempoFinalItem(System.currentTimeMillis());
            }
        // /*Método que baja una vida al jugador si choca con una roca, con una nave enemiga o con un disparo enemigo (aún no implementado).*/ 
        // Ya implementé esto en un método
        morirChoque(puntosSalud);//Método que reinicia el juego si perdiste una vida
        perder(); //Método que detiene el juego
        // if(m.eliminarObjetoChoque(getOneObjectAtOffset(0, 0, Roca.class), this,  (Espacio)getWorld())
            // || m.eliminarObjetoChoque(getOneObjectAtOffset(0, 0, NaveEnemiga.class), this,  (Espacio)getWorld())){
            // vidas--;
            // Greenfoot.setWorld(new Espacio());//Este método crea el mundo de nuevo después de morir.
            // /*Aunque para el reinicio hay que tomar en cuenta el reinicio de vidas y todo eso.*/
        // }
    }  

    /*Método que controlará el movimiento de nuestra nave.*/
    public void movimiento(){
        w = getWorld();
        //Checar que esté dentro de loslímites en x y y para que no se corte la nave. Limitaré con la altura para cuando estamos volteados.
        // if((getX() >= getImage().getHeight()) && (getX() <= (w.getWidth() - getImage().getHeight()))
                // && (getY() >= getImage().getHeight()) && (getY() <= w.getHeight() - getImage().getHeight())){ 
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
        // }
        /*Cuando la nave sale de los límites ya no se puede mover, así que quiero hacer que dependiendo de en qué límite esté,
            la nave se regrese al área hábil. Aún no lo he podido implementar bien.*/
        // else{
            // switch(getX()){
                // case getImage().getHeight(): //Cuando estamos fuera del límite izquierdo
                    // setLocation(getX()+1, getY()); //Mover a la derecha
                    // break;
                    
            // }
            // switch(getY()){
            // }
        // }
    }
    /*Método que tendrá el control de los disparos del jugador.*/
    public void disparar(){
        /*Condición que revisará que se pulsó la tecla de disparo, pero habrá un delay de 1250 milisegundos
            para no estar disparando todo el tiempo. Esto midiendo la hora del disparo y restando a la hora actual
            en milisegundos.*/
            /*Ahora guardaremos nuestra dirección para que sea la que siga el disparo*/
            /*Por ahora, Martes, 5 de mayo de 2020, no se pudo hacer lo de la dirección del disparo,
                pero ahí quedará la variable para cuando lo implementemos nos puede ser útil.*/
        if(Greenfoot.isKeyDown("space") && (System.currentTimeMillis()-inicioDisparoMillis)>=1150.0){ //Aún falta implementar los tipos de disparo y todo lo relacionado
            inicioDisparoMillis = System.currentTimeMillis();
            w = getWorld(); //Para agregar el objeto
            disparo = new Disparo(0, getX(), getY());//, direccion);//Instanciar el disparo en las coordenadas actuales y nuestra direccion.
            //Tomar la imagen de la nave para tomarla en cuenta en la salida del disparo
            GreenfootImage image = getImage();
            //Aparecer el objeto en las coordenadas actuales, pero un poco a la derecha para no empalmar la nave.
            w.addObject(disparo, getX()+getImage().getWidth()/2, getY());
        }
    }
    /*Método que determinará si chocamos con un item y le dará su habilidad o efecto a la nave.
        Regresará el tipo del item que servirá para condicionar los métodos que se ejectutan en act().
            Por ejemplo con el escudo, no se bajará la salud de la nave.*/
    private int choqueItem(){
    //public int eliminarObjetoChoque(Actor objetoChoque, Actor objetoRaiz, World mundoActual, int puntosSalud, int daño, int puntosNave)
        if(m.eliminarObjetoChoque(getOneObjectAtOffset(0, 0, Items.class), this, (Espacio)getWorld(), puntosSalud, 1, 0)
            == puntosSalud-1){ //Le quita 1 de vida solo para indicar que tocó el item y luego se lo volverá a aumentar.
                puntosSalud++;//Subir el punto de salud que le quitó.
                //Switch case para ver el tipo de item y actuar.
                switch(Items.getTipoItem()){
                    case 1: //Aumentar el número de vidas. Es lo único que hacemos, por lo que terminamos de inmediato.
                        Items.setTiempoFinalItem(System.currentTimeMillis());
                        Items.setItemActivoFalso();
                        vidas++;
                        //System.out.println("Vidas: "+ vidas);
                        break;
                    case 2: //ESCUDO. Poner el sprite del escudo y al terminar el tiempo cambiarlo.
                        //Mandar el tiempo en el que se tocó el item
                        //Debería bajar la velocidad. Aunque creo que no se puede porque se utiliza el setDirection.
                        Items.setTiempoFinalItem(System.currentTimeMillis());
                        setImage("Naves/Aliadas/NaveA"+ diseñoNave+ "Escudo.png");
                        setImage(m.modificarEscalaImagen(getImage(), 2, 1)); //Reescalarla, ya que volverá a tomar el tamaño original.
                        diseñoOriginalActivo = false;//Ya que aquí el diseño cambia por el escudo.
                        break;
                }
        }
        return Items.getTipoItem(); //No hay item si regresa 0.  
    }
    
    /*Método para ver si la nave choca con algo y elimina los objetos que chocaron, además baja el número de vidas.*/
    /*Método que baja una vida al jugador si choca con una roca, con una nave enemiga o con un disparo enemigo (aún no implementado).*/ 
    private void morirChoque(int daño){ //Aquí tomamos en cuenta que se hayan perdido todos los PS
        if(m.eliminarObjetoChoque(getOneObjectAtOffset(0, 0, Roca.class), this, (Espacio)getWorld(), puntosSalud, daño, puntosMenosAlMorir) == 0
            || m.eliminarObjetoChoque(getOneObjectAtOffset(0, 0, NaveEnemiga.class), this, (Espacio)getWorld(), puntosSalud, daño, puntosMenosAlMorir) == 0){
                Items.setItemActivoFalso(); //
                vidas --;
                //System.out.println("Vidas: "+ vidas);
                Greenfoot.setWorld(new Espacio());//Este método crea el mundo de nuevo después de morir.
        }
    }
    //Desaparecer si ya perdimos todas las vidas.
    private void perder(){
        if(vidas == 0)
            Greenfoot.stop(); //Si ya perdimos todas las vidas, entonces parar
        //REINICIAR NIVEL PERO CON LAS VIDAS, PUNTUACIÓN, ETCÉTERA INTACTOS DESPUÉS DE SU MODIFICACIÓN
        //Tal vez así las vidas no se reiniciarán
            /*Aunque para el reinicio hay que tomar en cuenta el reinicio de vidas y todo eso.*/
    }
    
    /*Método para obtener las vidas actuales del jugador.*/
    public static int getVidasJugador(){
        return vidas;
    }
    /*Método para recuperar los puntos de salud del jugador.*/
    //NO SE NECESITA PORQUE EN LA SUPERCLASE NAVE ES PROTEGIDO.
    // public abstract static int getPuntosSalud(){
        // return puntosSalud;
    // }
    /*Método para obtener la puntuación actual.*/
    public static int getPuntos(){
        return puntos;
    }
    /*Método para establecer la puntuación actual. Esto sumará el parámetr recibido, así que si se pierden puntos,
        se mandará un número negativo.*/
    public static void setPuntos(int puntosRecibidos){
        puntos += puntosRecibidos;
    }
    /*Getter booleano para ver si el escudo está activo en métodos generales.*/
    public static boolean isDiseñoOriginalActivo(){
        return diseñoOriginalActivo;
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