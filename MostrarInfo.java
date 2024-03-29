import greenfoot.Actor;
import greenfoot.Color;
import greenfoot.Greenfoot;
/**
 * Clase que mostrará la información en pantalla. Es decir, mostrará las vidas, puntos y más información de ser necesaria.
 *  Esta clase la hice a partir de la anterior clase MostrarVidas. Mejor lo convertiré en método.
 * @author (Jacob) 
 * @version (Martes, 2 de junio - Miércoles 3 de junio de 2020)
 */
public class MostrarInfo extends Actor
{
    private Etiqueta e;//Instanciar clase etiqueta para agregar el cuadro de texto.
    private int tipoInfo, vidas, puntos, puntosSalud, nivelActual;
    private long tiempoRestante; //El tiempo restante del nivel actual.
    private String nombreItem; //El nombre del item actual.
    /** Constructor para NaveAliada y NaveEnemiga en donde pasarán la salud inicial de estas.
        Esto para luego mostrar y actualizar la salud, ya que quitaré los atributos de ser estáticos,
            esto porque causa problemas con las Naves enemigas, porque todas tendrán el mismo valor
            por lo mismo de ser estáticos, entonces lo dejaré como antes: Protegido en la clase Nave (superclase)
            y en el método act pasaré los puntos de salud actuales y así se actualizarán sin necesidad de ser estáticos.*/
    public MostrarInfo(int puntosSalud, int tipoInfo, int tamañoFuente, Color colorFuente, Color colorFondo, Color bordeFuente){
        this(tipoInfo, tamañoFuente, colorFuente, colorFondo, bordeFuente); //Llam
        this.puntosSalud = puntosSalud;
        setImage(e.crearCuadroTexto("PS: "+ puntosSalud));
    }
    /** Constructor de MostrarInfo que mostrará lo que reciba en el tipo.*/
    public MostrarInfo(int tipoInfo, int tamañoFuente, Color colorFuente, Color colorFondo, Color bordeFuente){
        //Etiqueta(int tamañoFuente, Color colorFuente, Color colorFondo, Color bordeFuente);
        e = new Etiqueta(tamañoFuente, colorFuente, colorFondo, bordeFuente); //Inicializar la etiqueta a mostrar con las vidas
        this.tipoInfo = tipoInfo;
        switch(tipoInfo){ 
        case 0: break; //Si es 0 = PS, no hacen nada aquí
        case 1:
            vidas = NaveAliada.getVidasJugador();
            setImage(e.crearCuadroTexto("Vidas: "+ NaveAliada.getVidasJugador()));
            break;
        case 2:
            puntos = NaveAliada.getPuntos();
            setImage(e.crearCuadroTexto("Puntos: "+ NaveAliada.getPuntos()));
            break;
        case 3:
            nivelActual = Niveles.getNivelActual(); //Ya que aún no se implementa la clase
            setImage(e.crearCuadroTexto("Nivel "+ Niveles.getNivelActual()));
            break;
        case 4: //Tiempo Restante.
            tiempoRestante = Niveles.getTiempoRestanteNivel();
            setImage(e.crearCuadroTexto("Tiempo restante: "+ Niveles.getTiempoRestanteNivel()));
            break;
        case 5: //Nombre del item
            nombreItem = Items.getNombreItem();
            setImage(e.crearCuadroTexto("Item: "+ Items.getNombreItem()));
            break;
        case 6: //Tiempo restante del item.
            tiempoRestante = System.currentTimeMillis() - Items.getTiempoFinalItem();
            setImage(e.crearCuadroTexto("Duración: "+ (System.currentTimeMillis() - Items.getTiempoFinalItem())));
            break;
        default: 
            setImage(e.crearCuadroTexto("Ese tipo de información no existe.\n - FIN DEL JUEGO - "));
            try{
                Thread.sleep(3000);//Parar el sistema un momento.
            }catch(InterruptedException ie){
                System.out.println("Interrupción sleep.");
            }
            break;
        }
    }
    public void act() 
    {
        /*Si la información cambia, entonces volver a establecer la imagen, esto para que el proceso no se haga
            infinitas veces.*/
        switch(tipoInfo){
            case 0: break; //Puntos de salud, llamaré al otro método.
            case 1: vidas = mostrarInformacion("Vidas: ", vidas, NaveAliada.getVidasJugador()); break;
            case 2: puntos = mostrarInformacion("Puntos: ", puntos, NaveAliada.getPuntos()); break;
            //Este es un poco innecesario porque sólo cambiará al instanciar nivel, pero no hace tanto daño.
            case 3: nivelActual = mostrarInformacion("Nivel: ", nivelActual, Niveles.getNivelActual()); break;
            case 4: tiempoRestante = mostrarInformacion("Tiempo restante: ", tiempoRestante, Niveles.getTiempoRestanteNivel()); break;
            case 5: nombreItem = mostrarInformacion("Item: ", nombreItem, Items.getNombreItem());
            case 6: tiempoRestante = mostrarInformacion("Duración: ", tiempoRestante, System.currentTimeMillis() - Items.getTiempoFinalItem());
            default: Greenfoot.stop();/*Parar el mundo porque no se ingresó un tipo de Información válido.*/break;
        }
        
    }   
    /** Método para hacer lo mismo que en act() pero con los PS de las naves. Se irá moviendo el texto de acuerdo a las coord de la nave.*/
    public void mostrarPS(String texto, int puntosSalud, int x, int y){ //Los puntos de salud se irán actualizando.
        this.puntosSalud = mostrarInformacion(texto, this.puntosSalud, puntosSalud);
        setLocation(x, y-getImage().getHeight()/2); //Mover los PS a la localización dada. Esto para seguir a las naves.
    }
    /** Método que verificará si los valores han cambiado para actualizar la imagen. Esto para que la imagen no cambie todo el tiempo.*/
    private long mostrarInformacion(String textoInfo, long valorAntes, long valorNuevo){
        if(valorAntes != valorNuevo){
            setImage(e.crearCuadroTexto(textoInfo + valorNuevo));
            return valorNuevo;
        }
        return valorAntes;
    }
    /** Método que verificará si los valores han cambiado para actualizar la imagen. Esto para que la imagen no cambie todo el tiempo.*/
    private int mostrarInformacion(String textoInfo, int valorAntes, int valorNuevo){
        if(valorAntes != valorNuevo){
            setImage(e.crearCuadroTexto(textoInfo + valorNuevo));
            return valorNuevo;
        }
        return valorAntes;
    }
    /** Método que verifica si las cadenas son diferentes.*/
    private String mostrarInformacion(String textoInfo, String valorAntes, String valorNuevo){
        if(!valorAntes.equals(valorNuevo)){ //Si los textos son diferentes.
            setImage(e.crearCuadroTexto(textoInfo + valorNuevo));
            return valorNuevo;
        }
        return valorAntes;
    }
}
