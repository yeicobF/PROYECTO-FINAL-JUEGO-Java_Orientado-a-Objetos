import greenfoot.World;
import greenfoot.Color;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter; //Para escribir en los archivos.
/*public FileWriter(String fileName,
          boolean append)
           throws IOException*/
/**
 * Clase de prueba para ver que la lectura de archivos funciona.
 * Jueves, 11/06 1:01 PM funcionó para mostrar el archivo en terminal.
 * -> Esta clase era diferente, pero la sustituí por la clase de ArchivoPrueba.
 * 
 * @author (Jacob) 
 * @version (Viernes, 19 de junio de 2020)
 */
public class Archivo
{
    private Scanner archivo;
    private FileWriter escritor; //Se usará para escribir en el archivo.
    private Etiqueta texto;
    private String nombreArchivo; //Nombre del archivo.
    private int tamañoFuente; //Tamaño de la fuente del texto del archivo.
    private int numLineasArchivo; //Número de líneas totales del archivo.
    // private int numLineaActual; //Para indicar en el número de línea que nos encontramos en el archivo y calcular altura del texto.
    /** Constructor para cuando se quiere mostrar el archivo centrado en x y y directamente.*/
    public Archivo(String nombreArchivo, int tamañoFuente, Color colorFuente){
        this.nombreArchivo = nombreArchivo;
        this.tamañoFuente = tamañoFuente;
        numLineasArchivo =  0; //Inicializar el número de lineas del archivo.
        texto = new Etiqueta(tamañoFuente, colorFuente, null, null);
        /* Hacer todo el procedimiento de lectura y muestra de archivo.*/
        abrirArchivo();//Se abre el archivo y luego se llamarán los métodos que se vayan a utilizar.
    }
    /** Constructor para cuando se quiere escribir en el archivo.*/
    public Archivo(String nombreArchivo){
        this.nombreArchivo = nombreArchivo;
    }
    public void abrirArchivo(){
        try{
            archivo = new Scanner(new File(nombreArchivo)); //Tratar de abrir el archivo.
        }catch(FileNotFoundException e){
            System.out.println("El archivo no se encontró.");
        }
    }
    /*Método estático para escribir en un archivo. Es estático ya que no se requiere
       instanciar la clase.
        boolean escribirAlFinal = true si quieres escribir al final del archivo. False si al principio.
        - Esto solo lo necesitará usar la clase de marcadores.
        - Insertaremos de mayor puntuación a menor. Así que si el archivo ya tiene información,
            habrá que ir comparando y cambiando los valores.*/
    public void escribirEnArchivo(boolean escribirAlFinal, Jugador jugador){ 
        /*public FileWriter(String fileName,
          boolean append)
           throws IOException*/
        try{
           escritor = new FileWriter(nombreArchivo, escribirAlFinal);
        }catch(IOException e){
            /*  if the named file exists but is a directory rather than a regular file, 
             *      does not exist but cannot be created, or cannot be opened for any other reason*/
            System.out.println("Ocurrió un error con el archivo.");
        }
    }
    
    //Mostrar el archivo de texto abierto.
    public void mostrarArchivo(World mundo){
        boolean primerLinea = true; //En la primer linea del texto indicaré el tamaño del archivo, es decir, sus filas para así centrarlo.
        int altura = 0;
        //public Etiqueta(int tamañoFuente, Color colorFuente, Color colorFondo, Color bordeFuente)
        while(archivo.hasNextLine()){
            if(primerLinea){//La primer línea del .txt dirá el número de filas. Esto para centrar el texto.
                primerLinea = false;
                try {
                       numLineasArchivo = Integer.parseInt(archivo.nextLine());
                       //System.out.println("Entró a número de líneas: "+ numLineasArchivo);
                    }catch (NumberFormatException nfe){
                       System.out.println("No se indicó el número de líneas en el archivo.");
                    }
                /*La primer línea de texto irá en lo más alto.
                  - Se divide el alto entre 2 y se le resta la mitad de las palabras dependiendo de su tamaño.
                  - Se resta una palabra más por si hay un botón debajo, no lo sobreponga.*/
                altura = mundo.getHeight()/2 - tamañoFuente*(numLineasArchivo/2) - tamañoFuente;
            }
            else{
                //Dibuja la línea de texto centrada en "x" y "y".
                mundo.getBackground().drawImage(texto.crearCuadroTexto(archivo.nextLine()), mundo.getWidth()/2 - texto.getXCentrada(), altura);
                altura += tamañoFuente; //Ir bajando conforme el tamaño de la fuente;
            }
        }
        //Después de haber mostrado el archivo, cerrarlo.
        cerrarArchivo();
    }
    public void cerrarArchivo(){
        archivo.close();
    }
}