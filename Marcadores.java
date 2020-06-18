import java.util.ArrayList;
import javax.swing.JOptionPane; //Para el pop-up que pedirá el nombre del jugador. Que tenga un límite de caracteres.
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
    //Método que pedirá el nombre de usuario del jugador que será añadido a los marcadores.
        /* Fuentes: 
         - https://www.greenfoot.org/topics/3583
         - https://mkyong.com/swing/java-swing-how-to-make-a-confirmation-dialog/
         */
    private void pedirNombreUsuario(){
        int confirmacion, maxCaracteres = 10;
        nombreJugador = JOptionPane.showInputDialog("Ingresa tu nombre (Máximo de caracteres = "+ maxCaracteres +"):");
        if(nombreJugador.length() > maxCaracteres){ //Máximo de caracteres = 10.
            //Volver a llamar al método, pero primero mostrar otro cuadro de texto diciendo que pasó el límite.
            confirmacion = JOptionPane.showConfirmDialog(null, 
                                            "Superaste el número de caracteres ("+ maxCaracteres +").\nVuelve a ingresar tu nombre.", 
                                                     "ERROR EN NOMBRE JUGADOR", 
                                                     JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
            pedirNombreUsuario(); //Volver a pedir el nombre.                                         
        }
        //Mostrar cuadro de confirmación con SÍ y NO como opciones.
        confirmacion = JOptionPane.showConfirmDialog(null, "¿Estás seguro del nombre?", 
                                                     "CONFIRMACIÓN NOMBRE JUGADOR", 
                                                     JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE); // 0=yes, 1=no
        if(confirmacion == 1)
            pedirNombreUsuario(); //Volver a pedir el nombre de usuario.
        //Establecer un máximo de caracteres para que no se ingresen demasiados y saturen la pantalla.
        
    }
    
    private class arrayListMarcadores{
        private ArrayList<Marcadores> listaMarcadores; //ArrayList de los marcadores.
        public void addMarcador(){ //Método en donde agregaremos las puntuaciones.
            
        }
    }
}
