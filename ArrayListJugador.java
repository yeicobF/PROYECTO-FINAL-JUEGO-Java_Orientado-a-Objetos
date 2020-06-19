import java.util.ArrayList;
import java.util.Collections;
/**
 * Clase que manejará un arrayList de Jugador y lo podrá ordenar dependiendo de los puntos.
 * - https://beginnersbook.com/2013/12/java-arraylist-of-object-sort-example-comparable-and-comparator/
 * 
 * @author (Jacob) 
 * @version (Viernes, 19 de junio de 2020)
 */
public class ArrayListJugador extends Jugador 
{
    // ArrayList<Student> arraylist = new ArrayList<Student>();
    ArrayList<Jugador> arrayListJugador;
    public ArrayListJugador(){
        arrayListJugador = new ArrayList<Jugador>(); //Inicializar el arrayList.
    }
    public void addJugador(String nombre, int puntos, int nivel, String fecha){
        arrayListJugador.add(new Jugador(nombre, puntos, nivel, fecha));
    }
    public void ordenarArrayList(){
         Collections.sort(arrayListJugador); //Los metodos compareTo y toString fueron sobrecargados (Override) en jugador.
    }
    public void imprimirArrayList(){
        for(Jugador j : arrayListJugador)
            System.out.println(j); //Se imprimirá mediante el toString.
    }
}
