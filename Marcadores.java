import java.util.ArrayList;
import javax.swing.JOptionPane; //Para el pop-up que pedirá el nombre del jugador. Que tenga un límite de caracteres.
import java.awt.HeadlessException; //Excepción de input de JOption.
/**
 * Clase en la que se manejarán los marcadores. Se agregarán las nuevas puntuaciones
 *  y se mostrarán las que ya hay. Se podrían mostrar hasta un límite de n puntuaciones.
 *  Para esto se utilizará el manejo de archivos de dicha clase.
 * 
 *      Se mostrarán los marcadores como:
            Nombre  Puntuación   Nivel   Fecha
            ------ ------------  -----  -------
 * 
 * @author (Team Naves) 
 * @version (Jueves, 18 de junio de 2020)
 */
public class Marcadores  
{
    private Archivo archivo; //El archivo en donde estarán los marcadores.
    private Fecha fecha; //Para obtener la fecha del marcador.
    protected String nombreJugador; //Este se ingresará.
    private String fechaActual;
    private int puntuacion;
    public Marcadores(int puntuacion, int numNivel){
        fecha = new Fecha();
        fechaActual = fecha.getFecha();
        //pedirNombreUsuario();
    }
    public static void mostrarMarcadores(){
        //Abrir el archivo .txt con los marcadores y mostrar cierto número de puntuaciones.
    }
    //Método que pedirá el nombre de usuario del jugador que será añadido a los marcadores.
        /* Fuentes: 
         - https://www.greenfoot.org/topics/3583
         - https://mkyong.com/swing/java-swing-how-to-make-a-confirmation-dialog/
         */
    
    /*Clase que maneja métodos relacionados con el nombre del jugador.*/
    private abstract class NombreJugador{
        private void pedirNombreUsuario(){  
            int confirmacion = 1, maxCaracteres = 10;
            while(confirmacion == 1){// 0 = Sí está seguro del nombre, 1 = No está seguro del nombre.
                try{/*public static String showInputDialog​(Component parentComponent, Object message,
                                                            String title, int messageType) throws HeadlessException*/
                    nombreJugador = JOptionPane.showInputDialog(null, "Ingresa tu nombre (Máximo de caracteres = "+ maxCaracteres +"):",
                                                            "NOMBRE", JOptionPane.PLAIN_MESSAGE);
                    if(nombreJugador == null)
                        continue;
                    }catch(HeadlessException he){
                        //https://docs.oracle.com/en/java/javase/11/docs/api/java.desktop/java/awt/GraphicsEnvironment.html#isHeadless()
                        //true if this environment cannot support a display, keyboard, and mouse
                        System.out.println("El entorno no soporta un monitor, teclado, y mouse.");
                    }
                if(nombreJugador.length() > maxCaracteres || nombreJugador.isEmpty() 
                        || isCadenaEspacios(nombreJugador) || hayEspaciosEnBlanco(nombreJugador)) //Máximo de caracteres = 10.
                    
                    //Se pasó el máximo de caracteres o no ingresó nada o ingresó solo espacios. Ingresará de nuevo su nombre.
                    // confirmacion = JOptionPane.showConfirmDialog(null, 
                                                    // "Superaste el número de caracteres ("+ maxCaracteres +").\nVuelve a ingresar tu nombre.", 
                                                             // "ERROR EN NOMBRE JUGADOR", 
                                                             // JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
                    try{/*//public static void showMessageDialog​(Component parentComponent, Object message,
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
                        // JOptionPane.showMessageDialog(null, "Error:\n"
                                    // +"Superaste el número de caracteres ("+ maxCaracteres +"), o"
                                    // +"\nno ingresaste nada, o"
                                    // +"\ningresaste solo espacios en blanco.\n\nVuelve a ingresar tu nombre.", 
                                                             // "ERROR EN NOMBRE JUGADOR", JOptionPane.ERROR_MESSAGE);
                        }catch(HeadlessException he){
                            //https://docs.oracle.com/en/java/javase/11/docs/api/java.desktop/java/awt/GraphicsEnvironment.html#isHeadless()
                            //true if this environment cannot support a display, keyboard, and mouse
                            System.out.println("El entorno no soporta un monitor, teclado, y mouse.");
                        }
                else //No se superó el número de caracteres.
                    //Mostrar cuadro de confirmación con SÍ y NO como opciones.
                    confirmacion = JOptionPane.showConfirmDialog(null, "¿Estás seguro del nombre?", 
                                                                 "CONFIRMACIÓN NOMBRE JUGADOR", 
                                                                 JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE); // 0=yes, 1=no
            }
            try{/*//public static void showMessageDialog​(Component parentComponent, Object message,
                                                          String title, int messageType) throws HeadlessException*/
                JOptionPane.showMessageDialog(null, "Nombre guardado con éxito.", "ÉXITO", JOptionPane.INFORMATION_MESSAGE);
                }catch(HeadlessException he){
                    //https://docs.oracle.com/en/java/javase/11/docs/api/java.desktop/java/awt/GraphicsEnvironment.html#isHeadless()
                    //true if this environment cannot support a display, keyboard, and mouse
                    System.out.println("El entorno no soporta un monitor, teclado, y mouse.");
                }
        }
        /*Método que revisa si lo que tiene la cadena son solo espacios en blanco.*/
        private boolean isCadenaEspacios(String cadena){
            int contadorEspacios = 0;
            /* Character.compare(char 'A', char 'B'); Esto devuelve 0 si al comparar los caracteres son iguales, 
                                                 >0 si el 1er valor es mayor que el segundo
                                                 <0 si el 1er valor es menor que el segundo
                -> Also, by using double quotes you create String constant (" "), while with single quotes it's a char constant (' ').
                    https://stackoverflow.com/questions/4510136/how-to-check-if-a-char-is-equal-to-an-empty-space*/
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
    }
    private class arrayListMarcadores{
        private ArrayList<Marcadores> listaMarcadores; //ArrayList de los marcadores.
        public void addMarcador(){ //Método en donde agregaremos las puntuaciones.
            
        }
    }
}
