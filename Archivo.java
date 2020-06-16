import greenfoot.World;
import greenfoot.Color;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
/**
 * Clase de prueba para ver que la lectura de archivos funciona.
 * Jueves, 11/06 1:01 PM funcionó para mostrar el archivo en terminal.
 * -> Esta clase era diferente, pero la sustituí por la clase de ArchivoPrueba.
 * 
 * @author (Jacob) 
 * @version (Domingo, 14 de junio de 2020)
 */
public class Archivo
{
    private Scanner archivo;
    private Etiqueta texto;
    private String nombre; //Nombre del archivo.
    private int tamañoFuente; //Tamaño de la fuente del texto del archivo.
    private int numLineasArchivo; //Número de líneas totales del archivo.
    // private int numLineaActual; //Para indicar en el número de línea que nos encontramos en el archivo y calcular altura del texto.
    /**
       * Constructor for objects of class ArchivoPrueba */
    public Archivo(World mundoActual, String nombreArchivo, int tamañoFuente, Color colorFuente)
    {
        this.nombre = nombreArchivo;
        this.tamañoFuente = tamañoFuente;
        numLineasArchivo =  0; //Inicializar el número de lineas del archivo.
        // numLineaActual = 1;
        texto = new Etiqueta(tamañoFuente, colorFuente, null, null);
        abrirArchivo();
        mostrarArchivo(mundoActual);
        cerrarArchivo();
    }
    public void abrirArchivo(){
        try{
            archivo = new Scanner(new File(nombre)); //Tratar de abrir el archivo.
        }catch(FileNotFoundException e){
            System.out.println("El archivo no se encontró.");
        }
    }
    //Mostrar el archivo utilizando el addButton.
    public void mostrarArchivo(World mundo){
        //w = getWorld();
        //numLineas cuenta las filas del archivo para escalarlo.
        boolean primerLinea = true; //En la primer linea del texto indicaré el tamaño del archivo, es decir, sus filas para así centrarlo.
        /*numLineas indicará el total para reescalar y se irá restando para ir bajando en la pantalla.
           altura indica la altura actual del texto. Esta irá bajando dependiendo del número de línea actual.*/
        
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
                    //La primer línea de texto irá en lo más alto.
                    //Se divide el alto entre 2 y se le resta la mitad de las palabras dependiendo de su tamaño.
                        //Se resta una palabra más por si hay un botón debajo, no lo sobreponga.
                altura = mundo.getHeight()/2 - tamañoFuente*(numLineasArchivo/2) - tamañoFuente;
            }
            else{
                // //Crea un botón de acuerdo al tamaño del texto
                // if(numLineaActual <= numLineasArchivo/2) //Si se encuentra arriba de la mitad, empezar desde arriba
                    // altura = mundo.getHeight()/numLineasArchivo - tamañoFuente - 10 + numLineaActual*tamañoFuente;
                    // //altura = mundo.getHeight()/2+tamañoFuente+10 - numLineaActual * tamañoFuente;
                // else//Si se encuentra debajo de la mitad, ir disminuyendo.
                    // altura = mundo.getHeight()/numLineasArchivo + tamañoFuente + 10 + numLineaActual*tamañoFuente;
                    // // altura = mundo.getHeight()/2-(tamañoFuente+10) + numLineaActual * tamañoFuente;
                // System.out.println("Altura actual: "+ altura);
                // System.out.println("NÚMERO DE LÍNEAS ACTUAL: "+ numLineaActual + archivo.nextLine());
                //mundo.showText(archivo.nextLine(), mundo.getWidth()/2, altura);
                mundo.getBackground().drawImage(texto.crearCuadroTexto(archivo.nextLine()), mundo.getWidth()/2 - texto.getXCentrada(), altura);
                altura += tamañoFuente; //Ir bajando conforme el tamaño de la fuente;
            }
        }
    }
    public void cerrarArchivo(){
        archivo.close();
    }
}