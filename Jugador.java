import javax.swing.JOptionPane; //Para el pop-up que pedirá el nombre del jugador. Que tenga un límite de caracteres.
import java.awt.HeadlessException; //Excepción de input de JOption.
import greenfoot.Greenfoot; //Para detener el juego si ocurre una excepción. Aunque podríamos guardar en el archivo de recuperación y no terminar la ejecución.
/**
 * En esta clase se manejará todo lo que respecta al jugador:
 *  - Nombre
 *  - Puntos
 *  - Nivel
 *  - Fecha
 * Luego se llamará a Marcadores para agregar estos datos al archivo de los marcadores.
 * 
 * @author (Jacob) 
 * @version (Jueves, 18 de junio de 2020)
 */
/*Clase que maneja métodos relacionados con el nombre del jugador.*/
public class Jugador implements Comparable{ //Para comparar el arrayList.
    //private Marcadores marcadores;
    private Archivo archivo; //Para revisar que el nombre ingresado no esté repetido.
    private ArrayListJugador arrListJugador; //ArrayList para ver que el nombre ingresado no exista en los marcadores.
    protected String nombreJugador; //Este se ingresará.
    private String fechaActual;
    private int puntos;
    private int nivel; //El nivel en que se quedó el jugador.
    /*Constructor para la clase del arrayList.*/
    protected Jugador(){}
    public Jugador(int puntos){
        this.puntos = puntos; //Antes de morir, se mandarán los puntos para no recibirlos reiniciados.
        fechaActual = Fecha.getFecha(); //Obtener la hora actual.
        nivel = Niveles.getNivelActual(); //Obtener el nivel en el que nos quedamos.
        pedirNombreJugador();
        Greenfoot.setWorld(new Marcadores(this)); //Ahora llamar a los marcadores con este objeto.
    }
    /*Método para guardar los valores desde el arrayList. Protected porque solo lo usará el arrayList.*/
    protected Jugador(String nombreJugador, int puntos, int nivel, String fechaActual){
        this.nombreJugador = nombreJugador;
        this.puntos = puntos;
        this.nivel = nivel;
        this.fechaActual = fechaActual;
    }
    //Método que pedirá el nombre de usuario del jugador que será añadido a los marcadores.
    /* Fuentes: 
     - https://www.greenfoot.org/topics/3583
     - https://mkyong.com/swing/java-swing-how-to-make-a-confirmation-dialog/
     */
    private void pedirNombreJugador(){  
        int confirmacion = 1, maxCaracteres = 10;
        archivo = new Archivo("archivos/marcadores.txt");
        archivo.abrirArchivo();
        arrListJugador = archivo.guardarArchivoArrayList();
        
        try{ //Ya que puede haber una HeadlessException
            while(confirmacion == 1){// 0 = Sí está seguro del nombre, 1 = No está seguro del nombre.
                /*public static String showInputDialog​(Component parentComponent, Object message,
                                                            String title, int messageType) throws HeadlessException*/
                nombreJugador = JOptionPane.showInputDialog(null, "Ingresa tu nombre (Máximo de caracteres = "+ maxCaracteres +"):",
                                                        "NOMBRE", JOptionPane.PLAIN_MESSAGE);
                if(nombreJugador == null) //Si se presionó el botón cancelar, volver a pedir el nombre.
                    continue;
                if(nombreJugador.length() > maxCaracteres || nombreJugador.isEmpty() 
                        || isCadenaEspacios(nombreJugador) || hayEspaciosEnBlanco(nombreJugador))/*//public static void showMessageDialog​(Component parentComponent, Object message,
                                                          String title, int messageType) throws HeadlessException*/
                        if(nombreJugador.length() > maxCaracteres)
                            JOptionPane.showMessageDialog(null, "Error:\n"
                                    +"Superaste el número de caracteres ("+ maxCaracteres +").\n\nVuelve a ingresar tu nombre.", 
                                                             "ERROR EN NOMBRE DE JUGADOR", JOptionPane.ERROR_MESSAGE);
                        else{ //Si se superaron los caracteres, no mostrar otro error.
                            if(nombreJugador.isEmpty())
                                /*   StringUtils.isEmpty(null)      = true
                                     StringUtils.isEmpty("")        = true  
                                     StringUtils.isEmpty(" ")       = false  
                                     StringUtils.isEmpty("bob")     = false  
                                     StringUtils.isEmpty("  bob  ") = false */
                                JOptionPane.showMessageDialog(null, "Error:\n"
                                        +"No ingresaste nada.\n\nVuelve a ingresar tu nombre.", 
                                                                 "ERROR EN NOMBRE DE JUGADOR", JOptionPane.ERROR_MESSAGE);
                            if(isCadenaEspacios(nombreJugador)) //Método implementado en esta clase. Verifica que la cadena sea solo espacios en blanco.
                                JOptionPane.showMessageDialog(null, "Error:\n"
                                        +"Ingresaste solo espacios en blanco.\n\nVuelve a ingresar tu nombre.", 
                                                                 "ERROR EN NOMBRE JUGADOR", JOptionPane.ERROR_MESSAGE);
                            else //La cadena no está solo compuesta de espacios en blanco. Puede tener espacios entre caracteres.
                                if(hayEspaciosEnBlanco(nombreJugador))
                                    JOptionPane.showMessageDialog(null, "Error:\n"
                                        +"Hay espacios en blanco en la cadena (Entre letras, al inicio o al final).\n\nVuelve a ingresar tu nombre.", 
                                                                 "ERROR EN NOMBRE JUGADOR", JOptionPane.ERROR_MESSAGE);
                        }
                else //No hubo ningún error al ingresar el nombre.
                    //Mostrar cuadro de confirmación con SÍ y NO como opciones.
                    if(arrListJugador.isNombreMarcadores(nombreJugador)) //El nombre está repetido.
                        JOptionPane.showMessageDialog(null, "Error:\n"
                                        +"El nombre que ingresaste ya se encuentra en los marcadores.\n\nVuelve a ingresar tu nombre.", 
                                                                 "ERROR EN NOMBRE JUGADOR", JOptionPane.ERROR_MESSAGE);
                    else //El nombre no está repetido, se puede confirmar.
                        confirmacion = JOptionPane.showConfirmDialog(null, "¿Estás seguro del nombre?", 
                                                                     "CONFIRMACIÓN NOMBRE JUGADOR", 
                                                                     JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE); // 0=yes, 1=no
            }
            /*//public static void showMessageDialog​(Component parentComponent, Object message,
                                                      String title, int messageType) throws HeadlessException*/
            JOptionPane.showMessageDialog(null, "Nombre guardado con éxito.", "ÉXITO", JOptionPane.INFORMATION_MESSAGE);
        }catch(HeadlessException he){
            //https://docs.oracle.com/en/java/javase/11/docs/api/java.desktop/java/awt/GraphicsEnvironment.html#isHeadless()
            //true if this environment cannot support a display, keyboard, and mouse
            System.out.println("El entorno no soporta un monitor, teclado, y mouse.");
            /*Aquí habrá que guardar la información en un archivo de texto de emergencia por si ocurre
             *  algún error, que la información no se pierda.*/
             
            //Greenfoot.stop(); //Se detendrá el juego por la excepción. Aunque podríamos no hacer esto.
        }
        // return nombreJugador; //Regresa el nombre del jugador.
    }
    /*Método que revisa si lo que tiene la cadena son solo espacios en blanco.*/
    private boolean isCadenaEspacios(String cadena){
        int contadorEspacios = 0;
        /* Character.compare(char 'A', char 'B'); Esto devuelve 0 si al comparar los caracteres son iguales, 
                                             >0 si el 1er valor es mayor que el segundo
                                             <0 si el 1er valor es menor que el segundo
            -> Also, by using double quotes you create String constant (" "), while with single quotes it's a char constant (' ').
                https://stackoverflow.com/questions/4510136/how-to-check-if-a-char-is-equal-to-an-empty-space
                https://stackoverflow.com/questions/11229986/get-string-character-by-index-java
                https://stackoverflow.com/questions/3247067/how-do-i-check-that-a-java-string-is-not-all-whitespaces*/
        for(int i = 0; i < cadena.length(); i++)
            if(Character.compare(cadena.charAt(i),' ') == 0)
                contadorEspacios ++;
        /* Si todos los caracteres son espacios en blanco y la cadena tiene al menos 1 caracter..*/
        if(contadorEspacios == cadena.length() && contadorEspacios > 0)
            return true;
        return false;
    }
    /*Método que verifica si hay espacios en blanco en el nombre del jugador.*/
    private boolean hayEspaciosEnBlanco(String cadena){
        //Si la cadena no está compuesta de solo espacios en blanco. MEJOR USARÉ UN else con isCadenaEspacios en el programa.
        //if(isCadenaEspacios(cadena))
        for(int i = 0; i < cadena.length(); i++) //Regresa true tan pronto como encuentre un espacio.
            if(Character.compare(cadena.charAt(i),' ') == 0)
                return true; //Encontró un espacio en la cadena antes de revisarla completa.
        return false; //Terminó el ciclo sin encontrar ningún espacio en blanco.
    }
    public String getNombreJugador(){
        return nombreJugador;
    }
    public int getPuntos(){
        return puntos;
    }
    public int getNivel(){
        return nivel;
    }
    public String getFecha(){
        return fechaActual;
    }
    /** Método que regresa una cadena con toda la información del jugador.*/
    public String getInformacion(){
        return nombreJugador +" "+ puntos +" "+ nivel +" "+ fechaActual +"\n";
    }
    /*https://beginnersbook.com/2013/12/java-arraylist-of-object-sort-example-comparable-and-comparator/*/
    @Override
    public int compareTo(Object comparaJugador){
        int comparaPuntos = ((Jugador)comparaJugador).getPuntos();
        /* Para orden descendiente*/
        return comparaPuntos - this.puntos;

        /* int compareage=((Student)comparestu).getStudentage();
        /* For Ascending order*/
        //return this.studentage-compareage;

        /* For Descending order do like this */
        //return compareage-this.studentage;*/
    }

    @Override
    public String toString() {
        return "Nombre: "+ nombreJugador + ", Puntos: "+ puntos +", Nivel: "+ nivel +", Fecha: "+ fechaActual;
    }
}

