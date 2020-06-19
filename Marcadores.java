import java.util.ArrayList;

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
    
    
    
    private class arrayListMarcadores{
        private ArrayList<Marcadores> listaMarcadores; //ArrayList de los marcadores.
        public void addMarcador(){ //Método en donde agregaremos las puntuaciones.
            
        }
    }
}
