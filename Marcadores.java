import java.util.ArrayList;
import greenfoot.Actor;
import greenfoot.World;
import greenfoot.Color;
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
public class Marcadores extends World
{
    private Jugador jugador;
    private Etiqueta texto;
    private ActorAuxiliar[] textos; //Cuadros de texto como actor para colocarlos con facilidad.
    // private int sumaTamTextos; //La suma del largo de los textos para acomodarlos en pantalla.
    private int espacioEntreTextos; //El espacio que habrá entre texto y texto.
    /* 0 - Nombre
       1 - Puntos
       2 - Nivel
       3 - Fecha*/
    public Marcadores(Jugador jugador){
        super(1000, 600, 1); //Construir la pantalla de marcadores.
        setBackground("images/espacio5.jpg");
        this.jugador = jugador; //Se recibe el objeto con su información para agregarla a los marcadores.
        //public Etiqueta(int tamañoFuente, Color colorFuente, Color colorFondo, Color bordeFuente)
        texto = new Etiqueta(30, Color.WHITE, null, null);
        //https://stackoverflow.com/questions/5364278/creating-an-array-of-objects-in-java
        textos = new ActorAuxiliar[] {new ActorAuxiliar(), new ActorAuxiliar(), new ActorAuxiliar(), new ActorAuxiliar()}; //Crear el arreglo de los textos.
        //Darles sus respectivas imágenes.
        textos[0].setImage(texto.crearCuadroTexto("NOMBRE"));
        textos[1].setImage(texto.crearCuadroTexto("PUNTOS"));
        textos[2].setImage(texto.crearCuadroTexto("NIVEL"));
        textos[3].setImage(texto.crearCuadroTexto("FECHA"));
        calculaEspacioEntreTextos();
        agregaCuadrosTexto();
        mostrarMarcadores();
    }
    public static void mostrarMarcadores(){
        //Abrir el archivo .txt con los marcadores y mostrar cierto número de puntuaciones.
    }
    /*Método que calcula el espacio entre cuadro de texto y cuadro de texto para que queden bien repartidos.*/
    private void calculaEspacioEntreTextos(){
        int sumaTamTextos; //Es auxiliar nada más.
        sumaTamTextos = espacioEntreTextos = 0;
        for(int i = 0; i < 4; i++)
            sumaTamTextos += textos[i].getImage().getWidth(); //El getWidth devuelve el ancho desde la mitad de la imagen.
        espacioEntreTextos = (getWidth() - sumaTamTextos)/5; //Calcular el espacio entre cada texto dividiendo lo sobrante de la pantalla entre 5.
        System.out.println("SumaTamTextos = "+ sumaTamTextos +", Espacio entre textos: "+ espacioEntreTextos +", getWidth(): "+ getWidth());
    }
    /* Método que agregará los cuadros de texto de acuerdo al espacio entre estos.*/
    private void agregaCuadrosTexto(){
        int xActual = espacioEntreTextos; //Auxiliar para ver en dónde va la x para colocar los cuadros.
        for(int i = 0; i < 4; i++){
            addObject(textos[i], xActual, 20);
            //Esto se hace así porque se sumará la orilla derecha del cuadro de texto + el espacio entre los textos.
            xActual += espacioEntreTextos + textos[i].getImage().getWidth();
        }
    }
    
    private class arrayListMarcadores{
        private ArrayList<Marcadores> listaMarcadores; //ArrayList de los marcadores.
        public void addMarcador(){ //Método en donde agregaremos las puntuaciones.
            
        }
    }
}
