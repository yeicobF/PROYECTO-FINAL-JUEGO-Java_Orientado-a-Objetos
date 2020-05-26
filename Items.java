import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 *  -> Esta descripción es hasta la fecha de creación. Puede ser modificada en un futuro. *
 * En esta clase se manejarán los items disponibles. Cada item tendrá una función que dará una mejora a nuestra nave.
 * Estas mejoras irán apareciendo en el mapa dependiendo del tiempo. También se tomará en cuenta el nivel y si agregamos dificultad,
 *  también habrá que tomarla en cuenta para el ratio de aparición de los items. Hasta ahora se piensa generarlos con un número random
 *  y dependiendo del número saldrá el item que nada más cambiará la imagen. La clase NaveAliada (nosotros) es la que tocará los items
 *  y dependiendo del random que haya generado el item (este número se mandará a NaveAliada) dará estos cambios.
 *  Lista de items (hasta ahora):
 *      - 1. Subir vidas o salud (habrá que clarificar si aumentar el número de vidas actual o los HP (Health Points o Puntos de Salud)
 *              de la nave).
 *      - 2. Escudo o blindaje.
 *      - 3. Destrucción de un enemigo al tocarlo. Este como desventaja para que no sea muy desbalanceado o fácil,
 *              disminuirá la velocidad del jugador.
 *      - 4. Cambiar el tipo de disparo. De este podrían haber varios, pero aún no sabemos.
 *      - 5. Aparecer una nave aliada.
 *      - 6. Obtener más puntos o dinero. Esta aún no estamos seguros, pero sería buena por si implementamos un sistema
 *              de mejora de nuestra nave que se manejara con dinero o puntos. También podría ser por si agregamos un modo
 *              infinito en el que los puntos sean los que se tomen en consideración principalmente. Así implementaríamos
 *              los records.
 *      - 7. Realentizar a los enemigos.
 *      - 8. Nuclear o algo así. Como en Call Of Duty (COD) Zombies con el Kaboom (la bomba atómica) la cual destruiría a
 *              todas las naves del escenario.
 *      - 9. Insta Kill. Igual como en COD, esta podría destruir a los enemigos de un disparo en lugar de varios, ya que
 *              los disparos quitarán puntos de salud (HP o PS).
 * @author (Team Naves)
 * @version (Lunes, 25 de mayo de 2020)
 */
public class Items extends Actor
{
    /**
     * Act - do whatever the Items wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    World w; //Para métodos getHeight, ...
    private MetodosGenerales m = new MetodosGenerales(); //Instanciar para acceder a los métodos generales.
    private static long tiempoActividad = 0; /*Variable que definirá el tiempo de vida del item y cuánto lleva activo.
        Tuve que hacerla estática para poder utilizarla con el setTiempoFinalItem.*/
    private static boolean itemActivo = false;//Variable que verá si ya existe un item o no. Será estático para que no se necesite instanciar.
    /*Medirá el tiempo en el que el item terminó su efecto. Inicializar con una cantidad para que no se genere un item luego luego.
            El item se inicializa tomando en cuenta el tiempo del sistema. Si no se hace así, sería impreciso con el tiempo.*/
    private static long tiempoFinalItem = System.currentTimeMillis() + 1000;//Variable para comprobar hace cuánto se creó el último item.
    private static int tipoItemStatic = 0;//Para acceder al tipo sin instanciar.
    /*Variables para tener en cuenta el ancho y alto del item para que no se creen al tope de la pantalla y se corten.*/
    private static int anchoItem = 0;
    private static int altoItem = 0;
    private int velocidadItem = 0; //Variable que determinará la velocidad de caida del item.
    //private int tipoItem;//Esta variable definirá el tipo del item y su sprite.
    public Items(){} //Constructor para utilizar sus métodos.
    public Items(int tipoItem, int velocidadItem){
        this.velocidadItem = velocidadItem;
        tipoItemStatic = tipoItem;
        itemActivo = true;
        switch(tipoItem){ //Switch case para
            case 1: // VIDA. Esta no requiere tiempoActivo, ya que sube un corazón.
                setImage("Items/Item1Vida.png");
                tiempoActividad = 0;
                //tiempoFinalItem = System.currentTimeMillis(); //Ya que inmediatamente que tomas el corazón, hace efecto.
                break;
            case 2: //ESCUDO. Este sí requiere tiempo de actividad, ya que no es ilimitado.
                tiempoActividad = 10000;//10k milisegundos, es decir, 10 segundos.
                //De esta manera, sabemos cuál será el tiempo en que finalizará el efecto del item.
                /*Esto se necesita establecer al tocar el item. Esto porque con esta implementación,
                   * el tiempo irá contando desde que aparece el item.*/
                //tiempoFinalItem = System.currentTimeMillis() + tiempoActividad;
                setImage("Items/Item2Escudo.png");
                break;
        }
        //public GreenfootImage modificarEscalaImagen(GreenfootImage imagen, int divisor, int multiplicacion)
        //Reescalar los items a la mitad del tamaño del sprite.
        m.modificarEscalaImagen(getImage(), 2, 1);
        //Obtener ancho y alto del item después de modificar su escala.
        //Como volteé la imagen y quedó horizontal, entonces usaremos el getWidth (ancho, pero está volteada), que ahora es el alto.
        anchoItem = getImage().getHeight(); //Obtener ancho de imagen.
        altoItem = getImage().getWidth(); //Obtener alto, que como la imagen se volteó, es su ancho.
    }
    public void act()
    {
        //Hay que implementar que se mueva para abajo el item y cuando toque el piso desaparezca.
        w = getWorld();
        movimientoItem(velocidadItem);
        limitesItem();
    }
    /*Método que mueve al item hacia abajo*/
    private void movimientoItem(int velocidadItem){
        // setLocation(getX(), getY()+1); //Bajar su posición en 1 en y, aunque esto no podrá modificar velocidad.
        //Rotaré la imagen original para que con el setRotation funcione bien.
        setRotation(90); //Esto moverá hacia abajo pero volteará el objeto. Debemos buscar una solución.
        move(velocidadItem);
    }
    /*Método que verá los límites de -y para desaparecer el item.*/
    private void limitesItem(){
        w = getWorld();
        //No está sirviendo para limitar lo bajo en y
        if(getY() >= w.getHeight()-altoItem/2+1){//Si se mueve más abajo de los límites en lo mínimo en y, desaparecer.
            itemActivo = false; //Hacer el item inactivo si llega a los límites
            tiempoFinalItem = System.currentTimeMillis();//Guardar el tiempo final si no se tocó para que no se genere rápidamente.
            getWorld().removeObject(this);
        }
    }
    //TODO: Pasar este método de crearItemTiempo a su clase para mantener la encapsulación. (Ya lo hice)
    //Este método se llamará en los escenarios
    /*Método en el que se creará un item cada cierto tiempo dependiendo de la última vez que se creó y que
     *  un tiempo generado aleatoriamente supere al tiempo mínimo en la coindición.*/
    public void crearItemTiempo(int velocidadItem, long tiempoInicialMilis, long tiempoAumentoVelocidad){ //El item se creará mínimo cada 30 segundos después de haber hecho efecto por última vez
        //long tiempoAumentoVelocidad <- Esto será lo que tardará en aumentar la velocidad el ietm.
        int x, y, tipoItem;
        /*int velocidadItem = 1;//Cuando inicia el nivel iniciarla así y cuandi avance el tiempo cambiarla
              preferí que se mandara como parámetro para que al pasar de nivel sea más rápido.*/
        //No es necesario que se cumpla cuando no exista el objeto, sólo necesita tomar en cuenta el tiempo que ha pasado.
        if(System.currentTimeMillis() - tiempoInicialMilis >= tiempoAumentoVelocidad){//Cada que pase 1 minuto, aumentará la velocidad del item.
            velocidadItem++;
            tiempoInicialMilis = System.currentTimeMillis();//Reiniciar el contador para que ocurra cada minuto.
        }
        //Revisará que no exista el item para generar uno nuevo y que se respete el mínimo de tiempo. Se creará en un rango de 10 a 20 segundos.
        if(!itemActivo && ((System.currentTimeMillis() - tiempoFinalItem) >= m.getRandomNumber(1000, 2000))){
            //Generar ancho y alto restándole su mitad a los límites para que no aparezca cortado en pantalla.
            x = m.getRandomNumber(anchoItem/2, w.getWidth()-anchoItem/2);
            //Recordar que y = 0 está en el centro de la pantalla
            /*No están funcionando los límites. (CREO QUE YA, estaba considerando que y iba de -MinY hasta MaxY, pero
             *      "y" es 0 hasta arriba del escenario y getHeight hasta abajo.)*/
            y = m.getRandomNumber(altoItem, w.getHeight()-altoItem/2);
            //System.out.println("x: "+ x+ "y: "+ y);
            if(NaveAliada.getVidasJugador() == 5)//Generará todos los items menos el corazón, ya que tiene el máximo de vidas.
                tipoItem = m.getRandomNumber(2, 2);
            else
                tipoItem = m.getRandomNumber(1, 2);//Elegirá de manera random el item que se creará dentro de un rango.
            w.addObject(new Items(tipoItem, velocidadItem), x, y);
        }
    }
    /*Getter booleano estático siguiendo la convención: public boolean isAtributo(){ return atributo;}*/
    public static boolean isItemActivo(){
        return itemActivo;
    }
    /*Método estático que cambiará ek estado del item. Esto pasará cuando termine su efecto. Se cambiará desde la clase NaveAliada.
        No es necesario que se cambie a activo porque esto ya pasa al instanciar. Sólo será necesario hacerlo falso.*/
    public static void setItemActivoFalso(){
        itemActivo = false;
        tipoItemStatic = 0;
    }
    /*Getters y setters del último tiempo registrado del efecto del item. Es decir, que el item dejó de hacer efecto.*/
    public static long getTiempoFinalItem(){
        return tiempoFinalItem;
    }
    /*setter que toma en cuenta el tiempo en que se tomó el item para sumarle a su tiempo de duración.*/
    public static void setTiempoFinalItem(long tiempoTocado){
        tiempoFinalItem = tiempoTocado + tiempoActividad;
    }
    /*Getter del tipo de item para definir la habilidad o efecto.*/
    public static int getTipoItem(){
        return tipoItemStatic;
    }
    /*Getters del ancho y alto del item.*/
    public static int getAnchoItem(){
        return anchoItem;
    }
    public static int getAltoItem(){
        return altoItem;
    }
}
