import greenfoot.Actor;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import greenfoot.GreenfootSound;
import greenfoot.Color;
import greenfoot.Greenfoot;
/**
 * Esta es una subclase de la superclase Nave que tiene como función manejar las naves que controlará el jugador.
 *
 * @author (Team Naves)
 * @version (Lunes, 22 de junio de 2020)
 */
public class NaveAliada extends Nave
{
    //Manejar separados de NaveEnemiga, si no se combinarán sus puntos de salud en todas las instancias.
    /*El número de vidas será estático para que no se reinicie sino que se quede su número cada que se reinicie el mundo.*/
    private static boolean diseñoOriginalActivo; //Estático para revisar al tomar el escudo no destruya a los enemigos.
    private static int anchoImagen;
    private static int altoImagen;
    private static int vidas;// Inicializar vidas en 3 como estáticas para que al instanciar no se reinicien. Aunque esto aún no funciona.
    private static int x, y; //Para obtener las coordenadas desde las naves enemigas.
    private static int puntosIniciales; //Puntos con los que comenzamos los niveles. Se manejarán con getters y setters.
    private static int puntos;//La puntuación del jugador que se reiniciará al morir
    private static int diseñoNaveAliada;  //Para la creación del nivel será necesario.
    private static int tipoDisparoAliada;
    private Jugador jugador;
    private GreenfootSound sonidoItem;
    private int puntosSaludIniciales;
    private int puntosMenosAlMorir = 0;
    private int tipoDisparoInicial;
    private long inicioDisparoMilis = 0;
    
    /** Constructor de la nave del jugador.*/
    public NaveAliada(){
        sonidoItem = new GreenfootSound("tocarItems.mp3"); //El sonido que se reproducirá al tocar un item.
        puntosSalud += 200; //Tendremos 100 + 200 PS.
        //public MostrarInfo(int puntosSalud, int tipoInfo, int tamañoFuente, Color colorFuente, Color colorFondo, Color bordeFuente)
        infoPS = new MostrarInfo(puntosSalud, 0, 15, Color.RED, Color.WHITE, null);
        diseñoOriginalActivo = true; //El diseño original es el que no ha sido afectado por los items.
        setImage("Naves/Aliadas/NaveA"+ diseñoNaveAliada + ".png"); //De esta forma pondremos la imagen dependiendo del diseño para no repetirlo en cada diseño.
        //imagen = espacio.modificarEscalaImagen(getImage(), 2, 1); //Enviar la imagen con sus modificadores y establecerla reescalada.
        setImage(Imagen.modificarEscalaImagen(getImage(), 2, 1));//Acomodar la imagen modificada. La recibimos del método directamente. No necesitamos ninguna variable.
        /*Hay que tener una condición para el diseño de la nave. Este se estableció con el super constructor.*/
        anchoImagen = getImage().getWidth();
        altoImagen = getImage().getHeight();
        puntosSaludIniciales = puntosSalud;
        /*public Pantalla(World mundoActual, Actor objeto)
            -> Instanciamos después de establecer la imagen. La necesitamos.*/
        pantalla = new Pantalla(this);
    }
    public void act(){
        //Método para mostrar los PS de cada nave y que se muevan con ellos. Implementado en clase Nave como PROTECTED.
        mundo = getWorld();
        //Tomar las coordenadas para dárselas a las naves enemigas.
        x = getX();
        y = getY();
        existeMostrarInfo = muestraPuntosSalud(infoPS, existeMostrarInfo, "", puntosSalud, getX(), getY()-getImage().getHeight()/2);
        /*public static long disparar(World mundoActual, GreenfootImage imagenNave,
                        long inicioDisparoMilis, int tipoDisparo, int direccion, int x, int y)*/
        inicioDisparoMilis = Disparo.disparar(mundo, getImage(), inicioDisparoMilis, tipoDisparoAliada, direccion, getX(), getY());
         movimientoLimites(mundo, getX(), getY());
        efectosItem();
        morirChoque(puntosSalud);//Método que reinicia el juego si perdiste una vida
        perder();
    }
    /** Método que controlará el movimiento de nuestra nave.*/
    protected void movimiento(){
        if(Greenfoot.isKeyDown("up") || Greenfoot.isKeyDown("w"))//ARRIBA
           setDireccion(Direccion.ARRIBA); //super.act(direccion); //Se podría hacer así si recibiera esos parámetros el método act() de la superclse
        if(Greenfoot.isKeyDown("down") || Greenfoot.isKeyDown("s"))
            setDireccion(Direccion.ABAJO);//ABAJO
        if(Greenfoot.isKeyDown("left") || Greenfoot.isKeyDown("a"))
            setDireccion(Direccion.IZQUIERDA);//IZQUIERDA
        if(Greenfoot.isKeyDown("right") || Greenfoot.isKeyDown("d"))
            setDireccion(Direccion.DERECHA);//DERECHA
        /*Los métodos siguientes están así porque se revisan todas las posibilidades. Es decir, todas las combinaciones
            de teclas que podrían causar un tipo de movimiento en diagonal. Porque esto se toma en cuenta para
            modificar la posición de los sprites.*/
        if((Greenfoot.isKeyDown("right")&& Greenfoot.isKeyDown("up") )|| (Greenfoot.isKeyDown("d")&&Greenfoot.isKeyDown("w"))
                || (Greenfoot.isKeyDown("right")&&Greenfoot.isKeyDown("w")) || (Greenfoot.isKeyDown("d")&&Greenfoot.isKeyDown("up")))
            setDireccion(Direccion.ARRIBA_DERECHA); //ARRIBA_DERECHA
        if((Greenfoot.isKeyDown("left")&&Greenfoot.isKeyDown("up")) || (Greenfoot.isKeyDown("a")&&Greenfoot.isKeyDown("w"))
                || (Greenfoot.isKeyDown("left")&&Greenfoot.isKeyDown("w")) || (Greenfoot.isKeyDown("a")&&Greenfoot.isKeyDown("up")))
            setDireccion(Direccion.ARRIBA_IZQUIERDA);//ARRIBA_IZQUIERDA
        if((Greenfoot.isKeyDown("right")&&Greenfoot.isKeyDown("down") )|| (Greenfoot.isKeyDown("d")&&Greenfoot.isKeyDown("s"))
                || (Greenfoot.isKeyDown("right")&&Greenfoot.isKeyDown("s")) || (Greenfoot.isKeyDown("d")&&Greenfoot.isKeyDown("down")))
            setDireccion(Direccion.ABAJO_DERECHA);//ABAJO_DERECHA
        if((Greenfoot.isKeyDown("left")&&Greenfoot.isKeyDown("down") )|| (Greenfoot.isKeyDown("a")&&Greenfoot.isKeyDown("s"))
                || (Greenfoot.isKeyDown("left")&&Greenfoot.isKeyDown("s")) || (Greenfoot.isKeyDown("a")&&Greenfoot.isKeyDown("down")))
            setDireccion(Direccion.ABAJO_IZQUIERDA);//ABAJO_IZQUIERDA
    }
    /** Método que establece la dirección en la que el jugador se mueve. Además, mueve a la nave en pantalla.*/
    protected void setDireccion(int direccion){
        this.direccion = direccion; //Establecer nuestra direccion
        int aumentaX = 0, aumentaY = 0; //Variables para ver cuánto se mueve dependiendo de la condición.
        int[] aumenta = {aumentaX, aumentaY};//Arreglo que guardará los valores a aumentar. Sirve para el presionaShift.
        switch(direccion){
            case Direccion.ARRIBA:
                anguloGiro = Direccion.ANGULO_ARRIBA;
                aumentaY = -4;
                break;
            case Direccion.ABAJO:
                anguloGiro = Direccion.ANGULO_ABAJO;
                aumentaY = 4;
                break;
            case Direccion.IZQUIERDA:
                anguloGiro = Direccion.ANGULO_IZQUIERDA;
                aumentaX = -4;
                break;
            case Direccion.DERECHA:
                anguloGiro = Direccion.ANGULO_DERECHA;
                aumentaX = 4;
                break;
            /*A las diagonales no aumentarles valor, porque como ya actúan con la velocidad
               de la combinación de los botones, al aumentar valor irá más rápida.
               - Solo darles la dirección del ángulo para el disparo.*/
            case Direccion.ARRIBA_DERECHA:
                anguloGiro = Direccion.ANGULO_ARRIBA_DERECHA;
                break;
            case Direccion.ARRIBA_IZQUIERDA:
                anguloGiro = Direccion.ANGULO_ARRIBA_IZQUIERDA;
                break;
            case Direccion.ABAJO_IZQUIERDA:
                anguloGiro = Direccion.ANGULO_ABAJO_IZQUIERDA;
                break;
            case Direccion.ABAJO_DERECHA:
                anguloGiro = Direccion.ANGULO_ABAJO_DERECHA;
            break;
        }
        setRotation(anguloGiro);
        //Asignar los valores al arreglo
        aumenta[0] = aumentaX;
        aumenta[1] = aumentaY;
        presionoShift(aumenta); //private void presionoShift(int[] aumenta)
        //Verificar en dónde se modificaron los valores para aumentarlos
        setLocation(getX() + aumenta[0], getY() + aumenta[1]);
    }
    /** Método que va a verificar si se presionó shift y además cambiará los valores
      para que se vea con más velcidad en pantalla*/
    private void presionoShift(int[] aumenta){ //Como es un arreglo, los valores se modifican
          //aumenta[0] <- aumentaX; aumenta[1] <- aumentaY;
          /* - NO AUMENTAR EL VALOR A LAS DIAGONALES PORQUE IRÁN MÁS RÁPIDO, YA QUE DE BASE ACTÚAN CON LAS
                VELOCIDADES DE LAS TECLAS COMBINADAS.*/
        if(Greenfoot.isKeyDown("shift"))
            switch(direccion){
                case Direccion.ARRIBA:
                    aumenta[1] -= 2;
                    break;
                case Direccion.ABAJO:
                    aumenta[1] += 2;
                    break;
                case Direccion.IZQUIERDA:
                    aumenta[0] -= 2;
                    break;
                case Direccion.DERECHA:
                    aumenta[0] += 2;
                    break;
            }
    }
    /** Método que determinará si chocamos con un item y le dará su habilidad o efecto a la nave.
        Regresará el tipo del item que servirá para condicionar los métodos que se ejectutan en act().
            Por ejemplo con el escudo, no se bajará la salud de la nave.*/
    private int choqueItem(){
    //eliminarObjetoChoque(Actor objetoChoque, Actor objetoRaiz, World mundoActual, int puntosSalud, int daño, int puntosNave)
        if(Choques.eliminarObjetoChoque(getOneObjectAtOffset(0, 0, Items.class), this, getWorld(), puntosSalud, 1, 0)
            == puntosSalud-1){ //Le quita 1 de vida solo para indicar que tocó el item y luego se lo volverá a aumentar.
                //Switch case para ver el tipo de item y actuar.
                // Items.mostrarInfoItem(getWorld());
                sonidoItem.play(); //Reproducir el efecto al tocar el item.
                switch(Items.getTipoItem()){
                    case 1: //Aumentar el número de vidas. Es lo único que hacemos, por lo que terminamos de inmediato.
                        Items.setTiempoFinalItem(System.currentTimeMillis());
                        Items.setItemActivoFalso();
                        vidas++;
                        break;
                    case 2: //ESCUDO. Poner el sprite del escudo y al terminar el tiempo cambiarlo.
                        //Mandar el tiempo en el que se tocó el item
                        Items.setTiempoFinalItem(System.currentTimeMillis());
                        setImage("Naves/Aliadas/NaveA"+ diseñoNaveAliada +"Escudo.png");
                        setImage(Imagen.modificarEscalaImagen(getImage(), 2, 1)); //Reescalarla, ya que volverá a tomar el tamaño original.
                        diseñoOriginalActivo = false;//Ya que aquí el diseño cambia por el escudo.
                        break;
                    case 3: //Aumenta los PS. Sólo aparece si están más bajos de los iniciales-
                        Items.setTiempoFinalItem(System.currentTimeMillis());
                        if(puntosSalud < puntosSaludIniciales)
                            puntosSalud += 25;
                        if(puntosSalud > puntosSaludIniciales)//Si al sumar superaron el límite
                            puntosSalud = puntosSaludIniciales; //Poner los máximos
                        break;
                    case 4: //Cambiar el tipo de disparo.
                        int auxiliarTipo;
                        Items.setTiempoFinalItem(System.currentTimeMillis());
                        tipoDisparoInicial = tipoDisparoAliada;
                        while((auxiliarTipo = Aleatorio.getNumeroAleatorio(1, 3)) == tipoDisparoInicial){}//Que el tipo sea diferente.
                        tipoDisparoAliada = auxiliarTipo;
                        break;
                    case 5: //Puntos dobles.
                        Items.setTiempoFinalItem(System.currentTimeMillis()); //Que dure 3.5 segundos.
                        break;
                    case 6: //NUCLEAR. No la incluiremos en el juego. Podría mostrar un texto de "has destruido a todo ser viviente".
                        Items.setTiempoFinalItem(System.currentTimeMillis());
                        mundo.removeObjects(getWorld().getObjects(Roca.class));
                        mundo.removeObjects(getWorld().getObjects(NaveEnemiga.class));
                        mundo.removeObjects(getWorld().getObjects(MostrarInfo.class)); //Se borraría el nuestro también.
                        break;
                }
        }
        return Items.getTipoItem(); //No hay item si regresa 0.
    }
    /** Clase que controlará los efectos de los items.*/
    private void efectosItem(){
        if(choqueItem() == 2 && System.currentTimeMillis() < Items.getTiempoFinalItem()){ //El item actual es el escudo.
            /*Pasamos que el daño sea 0 para que no nos afecte a nosotros pero sí dañe lo demás.
             *  O tal vez que simplemente no pase nada al chocar con algo más.*/
            morirChoque(0); //Mejor que no le pase nada al chocar con los objetos. Aunque necesito checarlo.
        }
        else
            if(!diseñoOriginalActivo){ //El item no es el 2 o excedió el tiempo
                setImage("Naves/Aliadas/NaveA"+ diseñoNaveAliada +".png"); //Reestablecer el diseño original al terminar el efecto.
                setImage(Imagen.modificarEscalaImagen(getImage(), 2, 1)); //Reescalarla, ya que volverá a tomar el tamaño original.
                diseñoOriginalActivo = true;
                Items.setItemActivoFalso(); //Hacemos al item falso luego de terminar su periodo.
            }
        if(choqueItem() == 4 && System.currentTimeMillis() >= Items.getTiempoFinalItem()){ //Se acabó el efecto del disparo.
            tipoDisparoAliada = tipoDisparoInicial;
            Items.setItemActivoFalso();
        }
        if(choqueItem() == 5 && System.currentTimeMillis() >= Items.getTiempoFinalItem()) //Se acabaron los puntos dobles.
            Items.setItemActivoFalso();
    }
    /** Método para ver si la nave choca con algo y elimina los objetos que chocaron, además baja el número de vidas.*/
    private void morirChoque(int daño){ //Aquí tomamos en cuenta que se hayan perdido todos los PS
        /* -> Para eliminar el cuadro de texto al morir.
         * protected int eliminaCuadroPS(MostrarInfo infoPS, Actor objetoChoque,
                                        Actor objetoRaiz, World mundoActual, int puntosSalud, int daño, int puntosNave){*/
        if(eliminaCuadroPS(infoPS, getOneObjectAtOffset(0, 0, Roca.class), this, getWorld(), puntosSalud, daño, puntosMenosAlMorir) == 0
            || eliminaCuadroPS(infoPS, getOneObjectAtOffset(0, 0, NaveEnemiga.class), this, getWorld(), puntosSalud, daño, puntosMenosAlMorir) == 0){//&& Items.getTipoItem() != 2){//Que el item no sea el escudo.
                Items.setItemActivoFalso(); //
                vidas --;
                try{ /*Sería bueno detener el juego unos milisegundos después de morir.*/
                    Thread.sleep(500);//Parar el sistema un momento.
                }catch(InterruptedException ie){
                    System.out.println("Interrupción sleep.");
                }
                if(perder()) //Si perdimos, pedir información para marcadores.
                    Greenfoot.setWorld(new GameOver(this)); //Poner la pantalla de Game Over. Desde ahí se pedirá la información. Se manda la nave para tener las stats.
                else{ //Si aún no perdemos, seguir reiniciando el nivel.
                    Items.setItemActivoFalso(); //Hacemos al item falso luego de terminar su periodo.
                    tipoDisparoAliada = 1; //Reiniciar el tipo de disparo por si morimos con otro tipo.
                    Items.setTiempoFinalItem(System.currentTimeMillis()); //Reiniciar el tiempo de los items.
                    Greenfoot.setWorld(new Niveles(Niveles.getNivelActual()));//Este método crea el mundo de nuevo después de morir.
                }
        }
    }
    /** Método que verifica si perdimos.*/
    private boolean perder(){
        return vidas == 0; //True si tenemos 0 vidas, false si no.
    }
    /** Getters estáticos de coordenadas para NaveEnemiga.*/
    public static int getCordX(){
        return x;
    }
    public static int getCordY(){
        return y;
    }
    /** Getter y setter para el diseño de la nave. El setter para la selección de nave.*/
    public static int getDiseñoNaveAliada(){
        return diseñoNaveAliada;
    }
    public static void setDiseñoNaveAliada(int diseño){
        diseñoNaveAliada = diseño;
    }
    public static void setTipoDisparo(int tipoDisparo){
        tipoDisparoAliada = tipoDisparo;
    }
    public static int getTipoDisparo(){
        return tipoDisparoAliada;
    }
    /** Método para obtener las vidas actuales del jugador.*/
    public static int getVidasJugador(){
        return vidas;
    }
    /** Método para obtener la puntuación actual.*/
    public static int getPuntos(){
        return puntos;
    }
    /** Setter para las vidas para cuando se reinicie el nivel*/
    public static void setVidas(int vidasDadas){
        vidas = vidasDadas;
    }
    /** Método que establecerá los puntos iniciales del jugador. Estos serán 0
       en el primer nivel. Después serán los puntos con los que se terminó en
       el nivel anterior, por lo que puntosIniciales = puntosUltimoNivel.*/
    public static void setPuntosIniciales(int pts){
        puntosIniciales = pts;
    }
    /** Creo que este getter no lo necesitaremos.*/
    public static int getPuntosIniciales(){
        return puntosIniciales;
    }
    /** Método para establecer la puntuación actual. Esto sumará el parámetr recibido, así que si se pierden puntos,
        se mandará un número negativo.*/
    public static void sumaPuntos(int puntosRecibidos){
        puntos += puntosRecibidos;
    }
    public static void setPuntos(int puntosRecibidos){
        puntos = puntosRecibidos;
    }
    /** Regresa los PS máximos (con lo que inciamos).*/
    public int getPuntosSaludIniciales(){
        return puntosSaludIniciales;
    }
    /**Getter booleano para ver si el escudo está activo en métodos generales.*/
    public static boolean isDiseñoOriginalActivo(){
        return diseñoOriginalActivo;
    }
    /** Getters de dimensiones de la imagen de la nave.*/
    public static int getAnchoImagen(){
        return anchoImagen;
    }
    public static int getAltoImagen(){
        return altoImagen;
    }
    /** Método para obtener la Coordenada en X del disparo. Pero para esto hay que verificar que el disparo exista.
        Si el disparo no existe, entonces devolver -1 en x porque no se puede acceder a esa coordenada.*/
    public int getCordXDisparo(){
        if(disparo.getWorld() != null)
            return disparo.getCordX();
        else
            return -1;
    }
    /** Método para obtener la Coordenada en Y del disparo. Verificamos que el disparo exista y si no, deolver un valor no alcanzable.*/
    public int getCordYDisparo(){
        if(disparo.getWorld() != null)
            return disparo.getCordY();
        else
            return mundo.getHeight()+10;//Si no existe, regresar un valor inexistente en el rango del área de juego.
    }
}
