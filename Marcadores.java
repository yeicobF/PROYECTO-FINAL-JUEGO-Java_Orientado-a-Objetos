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
    private String nombreJugador; //Este se ingresará.
    private String fechaActual;
    private int puntuacion;
    public Marcadores(int puntuacion, int numNivel){
        fecha = new Fecha();
        fechaActual = fecha.getFecha();
        pedirNombreUsuario();
    }
    public static void mostrarMarcadores(){
        //Abrir el archivo .txt con los marcadores y mostrar cierto número de puntuaciones.
    }
    //Método que pedirá el nombre de usuario del jugador que será añadido a los marcadores.
        /* Fuentes: 
         - https://www.greenfoot.org/topics/3583
         - https://mkyong.com/swing/java-swing-how-to-make-a-confirmation-dialog/
         */
    private void pedirNombreUsuario(){  
        int confirmacion = 1, maxCaracteres = 10;
        while(confirmacion == 1){// 0 = Sí está seguro del nombre, 1 = No está seguro del nombre.
            try{/*public static String showInputDialog​(Component parentComponent, Object message,
                                                        String title, int messageType) throws HeadlessException*/
                nombreJugador = JOptionPane.showInputDialog(null, "Ingresa tu nombre (Máximo de caracteres = "+ maxCaracteres +"):",
                                                        "NOMBRE", JOptionPane.PLAIN_MESSAGE);
                }catch(HeadlessException he){
                    //https://docs.oracle.com/en/java/javase/11/docs/api/java.desktop/java/awt/GraphicsEnvironment.html#isHeadless()
                    //true if this environment cannot support a display, keyboard, and mouse
                    System.out.println("El entorno no soporta un monitor, teclado, y mouse.");
                }
            if(nombreJugador.length() > maxCaracteres){ //Máximo de caracteres = 10.
                //Volver a llamar al método, pero primero mostrar otro cuadro de texto diciendo que pasó el límite.
                confirmacion = JOptionPane.showConfirmDialog(null, 
                                                "Superaste el número de caracteres ("+ maxCaracteres +").\nVuelve a ingresar tu nombre.", 
                                                         "ERROR EN NOMBRE JUGADOR", 
                                                         JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);                                    
            }
            else //No se superó el número de caracteres.
                //Mostrar cuadro de confirmación con SÍ y NO como opciones.
                confirmacion = JOptionPane.showConfirmDialog(null, "¿Estás seguro del nombre?", 
                                                             "CONFIRMACIÓN NOMBRE JUGADOR", 
                                                             JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE); // 0=yes, 1=no
        }
        // //static void showInternalMessageDialog​(Component parentComponent, Object message, String title, int messageType)
        // JOptionPane.showConfirmDialog(null, "Nombre guardado con éxito.", "ÉXITO", JOptionPane.INFORMATION_MESSAGE);
        
        try{/*//public static void showMessageDialog​(Component parentComponent, Object message,
                                                      String title, int messageType) throws HeadlessException*/
            JOptionPane.showMessageDialog(null, "Nombre guardado con éxito.", "ÉXITO", JOptionPane.INFORMATION_MESSAGE);
            }catch(HeadlessException he){
                //https://docs.oracle.com/en/java/javase/11/docs/api/java.desktop/java/awt/GraphicsEnvironment.html#isHeadless()
                //true if this environment cannot support a display, keyboard, and mouse
                System.out.println("El entorno no soporta un monitor, teclado, y mouse.");
            }
             // if(confirmacion == 1)
            // pedirNombreUsuario(); //Volver a pedir el nombre de usuario.
        //Establecer un máximo de caracteres para que no se ingresen demasiados y saturen la pantalla.
        
    }
    
    private class arrayListMarcadores{
        private ArrayList<Marcadores> listaMarcadores; //ArrayList de los marcadores.
        public void addMarcador(){ //Método en donde agregaremos las puntuaciones.
            
        }
    }
}
