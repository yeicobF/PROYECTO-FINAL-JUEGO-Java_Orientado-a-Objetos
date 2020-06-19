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
    private ArrayListJugador arrListJugador; //ArrayList para guardar los valores, ordenarlos y sobreescribir el archivo.
    private Scanner archivo;
    private FileWriter escritor; //Se usará para escribir en el archivo.
    private Etiqueta texto;
    private String nombreArchivo; //Nombre del archivo.
    private String fecha; //Para concatenar la fecha y hora del jugador.
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
    /** Constructor para cuando se quiere escribir en el archivo. Esto con el arrayList.
        Recibirá el archivo base y el archivo en el que se escribirá.*/
    public Archivo(String nombreArchivo, String nombreArchivoEscribir){
        arrListJugador = new ArrayListJugador();
        this.nombreArchivo = nombreArchivo;
        //nombreArchivo = "archivos/marcadoresSinOrdenar.txt"
        //nombreArchivoEscribir = "archivos/marcadores.txt"
        //guardarArchivoArrayList(); //Para probar que funcione.
        escribirEnArchivo(nombreArchivoEscribir); //Escribirá los valores ordenados en el archivo marcadores.txt.
    }
    /** Constructor para escribir al final del archivo. También para comprobar que el nombre ingresado
        por el jugador no se encuentre en los marcadores.*/
    public Archivo(String nombreArchivo){//, String texto){
        this.nombreArchivo = nombreArchivo;
        arrListJugador = new ArrayListJugador(); //Crear el arrayList de los datos para verificar los nombres.
        //escribirAlFinalArchivo(nombreArchivo, texto);
    }
    public void abrirArchivo(){
        try{
            archivo = new Scanner(new File(nombreArchivo)); //Tratar de abrir el archivo.
        }catch(FileNotFoundException e){
            System.out.println("El archivo no se encontró.");
        }
    }
    /*Método que escribirá el texto ingresado al final de un archivo.*/
    public void escribirAlFinalArchivo(String texto){
        abrirArchivo();
        try{ /*boolean append es para escribir al final  si es true o al inicio con false.*/
            escritor = new FileWriter(nombreArchivo, true);
            escritor.write(texto); //Escribir al final del archivo el texto dado.
            escritor.close();
        }catch(IOException e){
            /*  if the named file exists but is a directory rather than a regular file, 
             *      does not exist but cannot be created, or cannot be opened for any other reason*/
            System.out.println("Ocurrió un error con el archivo.");
        }
        cerrarArchivo();
    }
    /*Método estático para escribir en un archivo. Es estático ya que no se requiere
       instanciar la clase.
        boolean escribirAlFinal = true si quieres escribir al final del archivo. False si al principio.
        - Esto solo lo necesitará usar la clase de marcadores.
        - Insertaremos de mayor puntuación a menor. Así que si el archivo ya tiene información,
            habrá que ir comparando y cambiando los valores.*/
    public void escribirEnArchivo(String nombreArchivoEscribir){ 
        /*public FileWriter(String fileName,
          boolean append)
           throws IOException*/
        /*La idea es ir comparando la puntuación actual con las demás y si encuentra una menor, ir haciendo
           los cambios con bubbleSort (lento pero fácil de implementar) y así sobreescribir el archivo.
           Habría que leer todo el archivo, guardar los valores ordenados en un arrayList y luego
           sobreescribir el archivo.
           -> Hice esto pero no con un bubblesort, sino con Compare.sort() en el arrayList.*/
        guardarArchivoArrayList(); //Aquí se guardarán los valores del archivo en un arrayList.
        arrListJugador.ordenarArrayList(); //Ordenar el arrayList para sobreescribirlo en orden.
        try{ //nombreArchivoEscribir = "archivos/marcadores.txt"
           escritor = new FileWriter(nombreArchivoEscribir);//, false); //boolean append es para escribir al final  si es true o al inicio con false (sobreescribir).
           /*Fuente: https://www.youtube.com/watch?v=lHFlAYaNfdo&t=279s*/
           //arrListJugador.guardarEnCadena();
           //System.out.println(arrListJugador.guardarEnCadena());
           escritor.write(arrListJugador.guardarEnCadena()); //Escribir la cadena con los datos ordenados en el archivo.
           escritor.close();
        }catch(IOException e){
            /*  if the named file exists but is a directory rather than a regular file, 
             *      does not exist but cannot be created, or cannot be opened for any other reason*/
            System.out.println("Ocurrió un error con el archivo.");
        }
    }
    /*Se recorrerá el archivo guardando los valores en el arrayList para luego reordenar el archivo.*/
    public ArrayListJugador guardarArchivoArrayList(){
        //Variables auxiliares para guardar los valores
        String nombreJugador;
        nombreJugador = fecha = "";
        int puntos, nivel;
        abrirArchivo();
        /*Guardar cada valor del archivo en el arrayList de jugador.*/
        while(archivo.hasNext()){ //Recorrerá cada cadena.
            nombreJugador = archivo.next();
            /*nextInt() guarda los valores encontrados como enteros.*/
            puntos = archivo.nextInt();
            nivel = archivo.nextInt();
            /*Concatena la fecha y la hora.*/
            // System.out.println(archivo.next() + archivo.next());
            fecha = ""; //Reiniciar la cadena, ya que si no, se sigue concatenando.
            fecha = fecha.concat(archivo.next() +" "+ archivo.next());
            /*Viernes, 19 de junio de 2020 <- Aquí hay un error.*/
            arrListJugador.addJugador(nombreJugador, puntos, nivel, fecha);
        }
        cerrarArchivo(); //Ya no necesitaremos el archivo abierto.
        // //Variables auxiliares para guardar los valores
        // String nombreJugador, fecha;
        // int puntos, nivel;
        // abrirArchivo();
        // /*Guardar cada valor del archivo en el arrayList de jugador.*/
        // while(archivo.hasNext()){ //Recorrerá cada cadena.
            // nombreJugador = archivo.next();
            // puntos = archivo.nextInt();
            // nivel = archivo.nextInt();
            // fecha = archivo.next();
            // arrListJugador.addJugador(nombreJugador, puntos, nivel, fecha);
        // }
        return arrListJugador; //Regresa el arrayList.
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
    public Scanner getArchivo(){
        return archivo;
    }
}