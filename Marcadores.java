import greenfoot.World;
import greenfoot.Color;
import greenfoot.GreenfootImage;
import java.util.Scanner;
/**
 * Clase en la que se manejarán los marcadores. Se agregarán las nuevas puntuaciones
 *  y se mostrarán las que ya hay. Se podrían mostrar hasta un límite de n puntuaciones.
 *  Para esto se utilizará el manejo de archivos de dicha clase.
 * 
 *      Se mostrarán los marcadores como:
            Nombre  Puntuación   Nivel   Fecha
            ------ ------------  -----  -------
 * 
 * @author (Jacob) 
 * @version (Viernes, 19 de junio de 2020)
 */
public class Marcadores extends Menu
{
    private GreenfootImage fondo; //Para mostrar los marcadores.
    private Archivo marcadores; //Aquí se hará todo el proceso de ordenar los marcadores.
    private Jugador jugador;
    private Scanner archivo; //Para recorrer el archivo y mostrarlo.
    private ActorAuxiliar[] textos; //Cuadros de texto como actor para colocarlos con facilidad.
    private int[] xTextos; //Guardará las coordenadas de los textos.
    /* 0 - Nombre
       1 - Puntos
       2 - Nivel
       3 - Fecha*/
    private int espacioEntreTextos; //El espacio que habrá entre texto y texto.
    /** Constructor de Marcadores que recibe un booleano que verifica
       si se quieren mostrar los marcadores en este constructor o no.
       Esto  servirá para que no se muestren los marcadores al ser llamado
       por el otro constructor que necesita guardar el nuevo marcador
       y ponerlo en el archivo. Luego se muestran los marcadores.
       Si se mostraran en este constructor, luego se empalmarían.*/
    public Marcadores(boolean mostrarMarcadores){ //Constructor en donde se crea el área de marcadores.
        super(false); //Construir la pantalla de marcadores.
        //public Etiqueta(int tamañoFuente, Color colorFuente, Color colorFondo, Color bordeFuente)
        texto = new Etiqueta(40, Color.WHITE, null, null);
        xTextos = new int[4];//Fuente: https://stackoverflow.com/questions/5364278/creating-an-array-of-objects-in-java
        textos = new ActorAuxiliar[] {new ActorAuxiliar(), new ActorAuxiliar(), new ActorAuxiliar(), new ActorAuxiliar()}; //Crear el arreglo de los textos.
        //Darles sus respectivas imágenes.
        textos[0].setImage(texto.crearCuadroTexto("NOMBRE"));
        textos[1].setImage(texto.crearCuadroTexto("PUNTOS"));
        textos[2].setImage(texto.crearCuadroTexto("NIVEL"));
        textos[3].setImage(texto.crearCuadroTexto("FECHA"));
        calculaEspacioEntreTextos();
        agregaCuadrosTexto();
        if(mostrarMarcadores)
            mostrarMarcadores();
    }
    public Marcadores(Jugador jugador){
        this(false); //Llamar al otro constructor de marcadores.
        this.jugador = jugador; //Se recibe el objeto con su información para agregarla a los marcadores.
        agregaJugadorMarcadores();
        /*public Archivo(String nombreArchivo, String nombreArchivoEscribir)
            -> Se ordena el archivo de marcadores con el nuevo que ingresamos.*/
        marcadores = new Archivo("archivos/marcadoresSinOrdenar.txt", "archivos/marcadores.txt");
        mostrarMarcadores();
    }
    
    public void act(){
        volverMenu();
    }
    /** Método que agregará al jugador al archivo de los marcadores.*/
    private void agregaJugadorMarcadores(){
        Archivo a = new Archivo("archivos/marcadoresSinOrdenar.txt");
        /* Escribir al final del archivo de los marcadores el jugador que se ha recibido.*/
        a.escribirAlFinalArchivo(jugador.getInformacion());
    }
    /** Método para mostrar los marcadores en orden descendiente.*/
    public void mostrarMarcadores(){
        //Abrir el archivo .txt con los marcadores y mostrar cierto número de puntuaciones.
        //public Archivo(String nombreArchivo, int tamañoFuente, Color colorFuente)
        //public Etiqueta(int tamañoFuente, Color colorFuente, Color colorFondo, Color bordeFuente)
        int altura = 50, numeroMarcador = 1, maxMarcadores = 15; //Altura de los textos.
        texto = new Etiqueta(30, Color.YELLOW, null, null);
        fondo = getBackground();
        marcadores = new Archivo("archivos/marcadores.txt", 30, Color.WHITE);
        archivo = marcadores.getArchivo();
        while(archivo.hasNext() && numeroMarcador <= maxMarcadores){ //Mientras el archivo tenga más texto.
            fondo.drawImage(texto.crearCuadroTexto(archivo.next()), xTextos[0] - texto.getXCentrada(), altura); //Nombre
            fondo.drawImage(texto.crearCuadroTexto(archivo.next()), xTextos[1] - texto.getXCentrada(), altura); //Puntos
            fondo.drawImage(texto.crearCuadroTexto(archivo.next()), xTextos[2] - texto.getXCentrada(), altura); //Nivel
            fondo.drawImage(texto.crearCuadroTexto(archivo.next() +" "+ archivo.next()), xTextos[3] - texto.getXCentrada(), altura); //Fecha
            altura += texto.getImagen().getHeight();
            numeroMarcador ++;
        }
        archivo.close(); //Cerrar archivo.
        marcadores.cerrarArchivo(); //Para asegurar.
    }
    /** Método que calcula el espacio entre cuadro de texto y cuadro de texto para que queden bien repartidos.*/
    private void calculaEspacioEntreTextos(){
        int sumaTamTextos; //Es auxiliar nada más.
        sumaTamTextos = espacioEntreTextos = 0;
        for(int i = 0; i < 4; i++)
            sumaTamTextos += textos[i].getImage().getWidth(); //El getWidth devuelve el ancho desde la mitad de la imagen.
        espacioEntreTextos = (getWidth() - sumaTamTextos)/5; //Calcular el espacio entre cada texto dividiendo lo sobrante de la pantalla entre 5.
    }
    /** Método que agregará los cuadros de texto de acuerdo al espacio entre estos.*/
    private void agregaCuadrosTexto(){
        int xActual = espacioEntreTextos; //Auxiliar para ver en dónde va la x para colocar los cuadros.
        for(int i = 0; i < 4; i++){
            xTextos[i] = xActual; //Dar la posición en X del texto para centrar los marcadores.
            addObject(textos[i], xActual, 20);
            //Esto se hace así porque se sumará la orilla derecha del cuadro de texto + el espacio entre los textos.
            xActual += espacioEntreTextos + textos[i].getImage().getWidth();
        }
    }
}
