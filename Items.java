import greenfoot.World;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import greenfoot.Actor;
import greenfoot.Color;
/**
 *  -> Esta descripción es hasta la fecha de creación. Puede ser modificada en un futuro. *
 * En esta clase se manejarán los items disponibles. Cada item tendrá una función que dará una mejora a nuestra nave.
 * Estas mejoras irán apareciendo en el mapa dependiendo del tiempo. También se tomará en cuenta el nivel y si agregamos dificultad,
 *  también habrá que tomarla en cuenta para el ratio de aparición de los items. Hasta ahora se piensa generarlos con un número random
 *  y dependiendo del número saldrá el item que nada más cambiará la imagen. La clase NaveAliada (nosotros) es la que tocará los items
 *  y dependiendo del random que haya generado el item (este número se mandará a NaveAliada) dará estos cambios.
 *  Lista de items (hasta ahora):
 *      - 1. Aumentar una vida.
 *      - 2. Escudo o blindaje.
 *      - 3. Cambiar el tipo de dispar: Se cambia entre los 3 tipos de disparo.
 *      - 4. Puntos dobles: Se duplican los puntos obtenidos por un periodo de tiempo.
 *      - 5. Subir PS: Se aumentan los puntos de salud si es que bajaron.
 * @author (Team Naves)
 * @version (Lunes, 22 de junio de 2020)
 */
public class Items extends Actor
{
    private static MostrarInfo nombreItem; //Mostrará un cuadro con el nombre del item.
    private static MostrarInfo duracionItem; //Mostrará un cuadro con la suración del item.
    
    /*Medirá el tiempo en el que el item terminó su efecto. Inicializar con una cantidad para que no se genere un item luego luego.
            El item se inicializa tomando en cuenta el tiempo del sistema. Si no se hace así, sería impreciso con el tiempo.*/
    private static long tiempoActividad = 0; /*Variable que definirá el tiempo de vida del item y cuánto lleva activo.
        Tuve que hacerla estática para poder utilizarla con el setTiempoFinalItem.*/
    private static long tiempoFinalItem;
    private static int tipoItemStatic = 0;//Para acceder al tipo sin instanciar.
    /*Variables para tener en cuenta el ancho y alto del item para que no se creen al tope de la pantalla y se corten.*/
    private static int anchoItem = 0;
    private static int altoItem = 0;
    private static boolean tocoItem; //Indica si la imagen del item sigue en pantalla o no.
    private static boolean itemActivo = false;//Variable que verá si ya existe un item o no. Será estático para que no se necesite instanciar.
    private World mundo; //Para métodos getHeight, ...
    private Pantalla pantalla;
    private long inicioDisparoMillis = 0;//Variable para comprobar hace cuánto se creó el último item.
    private int velocidadItem = 0; //Variable que determinará la velocidad de caida del item.
    /** Constructor de la clase Items. Tomará el tipo de item y su velocidad de caida en pantalla.*/
    public Items(int tipoItem, int velocidadItem){
        this.velocidadItem = velocidadItem;
        tipoItemStatic = tipoItem;
        itemActivo = true;
        tocoItem = false;
        tiempoFinalItem = System.currentTimeMillis();
        switch(tipoItem){ //Switch case para
            case 1: // VIDA. Esta no requiere tiempoActivo, ya que sube un corazón.
                setImage("Items/Item1Vida.png");
                tiempoActividad = 0;
                break;
            case 2: //ESCUDO. Este sí requiere tiempo de actividad, ya que no es ilimitado.
                tiempoActividad = 5000;//10k milisegundos, es decir, 10 segundos.
                //De esta manera, sabemos cuál será el tiempo en que finalizará el efecto del item.
                /*Esto se necesita establecer al tocar el item. Esto porque con esta implementación,
                   * el tiempo irá contando desde que aparece el item.*/
                setImage("Items/Item2Escudo.png");
                break;
            case 3: //SUBIR PS
                setImage("Items/Item3SubirPS.png");
                setImage(Imagen.modificarEscalaImagen(getImage(), 2, 1));
                tiempoActividad = 0;
                break;
            case 4: //Cambiar el tipo del disparo. Hará más daño.
                tiempoActividad = 10000;
                setImage("Items/Item4CambiarDisparo.png");
                break;
            case 5:
                tiempoActividad = 3500;
                setImage("Items/Item5PuntosExtra.png");
                break;
            case 6: //NUCLEAR. Destruirá todo lo que hay en el mapa.
                tiempoActividad = 0;
                setImage("Items/Item6Nuclear.png");
        }
        //public GreenfootImage modificarEscalaImagen(GreenfootImage imagen, int divisor, int multiplicacion)
            //Reescalar los items a la mitad del tamaño del sprite.
        Imagen.modificarEscalaImagen(getImage(), 2, 1);
        pantalla = new Pantalla(this);
    }
    public void act()
    {
        //Hay que implementar que se mueva para abajo el item y cuando toque el piso desaparezca.
        if(tipoItemStatic == 4 || tipoItemStatic == 6)
            turn(5); //Si es el de cambiar disparo o nuclear, hacerlos girar
        movimientoItem(velocidadItem);
        limitesItem();
    }
    /*Método que mueve al item hacia abajo*/
    private void movimientoItem(int velocidadItem){
        //Rotaré la imagen original para que con el setRotation funcione bien. Si no, no se mueve en la dirección deseada.
        setRotation(90); //Esto moverá hacia abajo pero volteará el objeto. Debemos buscar una solución.
        move(velocidadItem);
    }
    /** Método que mostrará la info del item actual. No logré que funcionara. Se detenía la ejecución al llamar al método.*/
    public static void mostrarInfoItem(World mundoActual){
        //public MostrarInfo(int tipoInfo, int tamañoFuente, Color colorFuente, Color colorFondo, Color bordeFuente)
        //5.- Nombre del item. 6.- Duración item.
        nombreItem = new MostrarInfo(5, 15, Color.WHITE, Color.YELLOW, null);
        duracionItem = new MostrarInfo(6, 15, Color.WHITE, Color.YELLOW, null);
        mundoActual.addObject(nombreItem, 0, 0); //Agregar a la esquina superior izquierda.
        mundoActual.addObject(nombreItem, 0, 15);
    }
    /** Método que verá los límites de -y para desaparecer el item.*/
    private void limitesItem(){
        mundo = getWorld();
        //public boolean isObjetoLimite(World mundoActual, int x, int y)
        if(!pantalla.isObjetoLimite(mundo, getX(), getY())){//Si se mueve más abajo de los límites en lo mínimo en y, desaparecer.
            itemActivo = false; //Hacer el item inactivo si llega a los límites
            tiempoFinalItem = System.currentTimeMillis();//Guardar el tiempo final si no se tocó para que no se genere rápidamente.
            tipoItemStatic = 0;
            getWorld().removeObject(this);
        }
    }
    /** Getter booleano estático siguiendo la convención: public boolean isAtributo(){ return atributo;}*/
    public static boolean isItemActivo(){
        return itemActivo;
    }
    public static boolean isTocoItem(){
        return tocoItem;
    }
    public static void setTocoItemTrue(){
        tocoItem = true;
    }
    /** Método estático que cambiará ek estado del item. Esto pasará cuando termine su efecto. Se cambiará desde la clase NaveAliada.
        No es necesario que se cambie a activo porque esto ya pasa al instanciar. Sólo será necesario hacerlo falso.*/
    public static void setItemActivoFalso(){
        itemActivo = false;
        tipoItemStatic = 0;
    }
    /** Getters y setters del último tiempo registrado del efecto del item. Es decir, que el item dejó de hacer efecto.*/
    public static long getTiempoFinalItem(){
        return tiempoFinalItem;
    }
    /** Setter que toma en cuenta el tiempo en que se tomó el item para sumarle a su tiempo de duración.*/
    public static void setTiempoFinalItem(long tiempoTocado){
        tiempoFinalItem = tiempoTocado + tiempoActividad;
    }
    /** Getter del tipo de item para definir la habilidad o efecto.*/
    public static int getTipoItem(){
        if(!tocoItem)//Si aún no se ha tocado el item, que el tipo sea 0
            return 0;
        else
            return tipoItemStatic;//Si ya se tocó el item, ahora sí regresar su valor real
    }
    /** Método que devuelve el nombre del item actual.*/
    public static String getNombreItem(){
        /*
           1.- Vida
           2.- Escudo
           3.- Subir PS
           4.- Cambiar disparo
           5.- Puntos extra
           6.- Nuclear*/
        String nombre = "";
        switch(tipoItemStatic){
            case 0: nombre = "Nada"; break;
            case 1: nombre = "Vida"; break;
            case 2: nombre = "Escudo"; break;
            case 3: nombre = "Subir PS"; break;
            case 4: nombre = "Cambiar disparo"; break;
            case 5: nombre = "Puntos extra"; break;
            case 6: nombre = "Nuclear"; break;
            default: nombre = "Item no existente"; break;
        }
        return nombre;
    }
    /** Setter del tipo de item para definir la habilidad o efecto. Aunque no es necesario porque si no existe pues no necesita ser 0.*/
    public static void setTipoItemCero(){
        tipoItemStatic = 0;
    }
    /** Getters del ancho y alto del item.*/
    public static int getAnchoItem(){
        return anchoItem;
    }
    public static int getAltoItem(){
        return altoItem;
    }
}
