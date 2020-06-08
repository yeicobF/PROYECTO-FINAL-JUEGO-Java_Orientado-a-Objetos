import greenfoot.*;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
/**
 * Esta clase manejará los archivos necesarios:
 *  Leer archivos (Créditos, instruciones, etcétera)
 *  Crear archivos (puntuaciones, etcétera)
 * 
 * @author (Team Naves) 
 * @version (Viernes, 29 de mayo de 2020)
 */
public class Archivo extends Actor
{
    private Scanner archivo;
    Boton b; //Para crear botones.
    World w;
    String nombre;//Nombre del archivo.
    private boolean textoLeido;
    /**
     * Constructor for objects of class Archivo
     */
    public Archivo(String nombre)
    {
        this.nombre = nombre; //Nombre edel archivo.
        textoLeido = false;
    }

    public void act(){
        abrirMostrarCerrarArchivo();
    }
    
    /* Método que hará los 3 métodos mencionados sin tener que llamarlos manualmente cada uno.*/
    public void abrirMostrarCerrarArchivo(){
        abrirArchivo();
        mostrarArchivo();
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
    public void mostrarArchivo(){
        w = getWorld();
        //numLineas cuenta las filas del archivo para escalarlo.
        boolean primerLinea = true; //En la primer linea del texto indicaré el tamaño del archivo, es decir, sus filas para así centrarlo.
        /*numLineas indicará el total para reescalar y se irá restando para ir bajando en la pantalla.
           altura indica la altura actual del texto. Esta irá bajando dependiendo del número de línea actual.*/
        int numLineas = 0, altura = 0; 
        Etiqueta e = new Etiqueta(30, Color.WHITE, null, null);
        //public Etiqueta(int tamañoFuente, Color colorFuente, Color colorFondo, Color bordeFuente)
        while(archivo.hasNextLine() && !textoLeido){
            if(primerLinea){//La primer línea del .txt dirá el número de filas. Esto para centrar el texto.
                primerLinea = false;
                try {
                       numLineas = Integer.parseInt(archivo.nextLine().toString());
                    }catch (NumberFormatException nfe){
                       System.out.println("No se indicó el número de líneas en el archivo.");
                    }
            }
            //Crea un botón de acuerdo al tamaño del texto
            if(numLineas >= numLineas/2) //Si se encuentra arriba de la mitad, empezar desde arriba
                altura = w.getHeight()/2-40 + numLineas * 30; //La altura de cada línea de texto será de 30
            else//Si se encuentra debajo de la mitad, ir disminuyendo.
                altura = w.getHeight()/2+40 - numLineas * 30;
            
            numLineas --;//Disminuir el número de línea actual.
            /*Encontré una mejor manera para crear los cuadros de texto sin tener que convertirlos en botones.
               Esto será para lo que no necesite botones. Para esto al final del ciclo, se crearán los botones.*/
            
            //Etiqueta e = new Etiqueta(archivo.nextLine(), 30, Color.WHITE, null);
            
            //public GreenfootImage crearCuadroTexto(String s)
            //Se crea el cuadro de texto en el objeto Etiqueta. Se hace su setImage.
            e.crearCuadroTexto(archivo.nextLine());
            //Se agrega el objeto con el nuevo texto.
            w.addObject(e, w.getWidth()/2-archivo.nextLine().toString().length()/2, altura);
            //crearCuadroTexto(Etiqueta etiqueta, int x, int y)
            System.out.println(e.getTexto());
        }
        textoLeido = true; //Se recorrió todo el archivo. Esto por si se utiliza en un método act()
    }
    public void cerrarArchivo(){
        archivo.close();
        getWorld().removeObject(this);
    }
}
