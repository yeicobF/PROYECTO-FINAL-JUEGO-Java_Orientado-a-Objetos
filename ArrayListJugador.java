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
    String textoArchivo; //El texto que se guardará en el archivo.
    public ArrayListJugador(){
        arrayListJugador = new ArrayList<Jugador>(); //Inicializar el arrayList.
        textoArchivo = "";
    }
    public void addJugador(String nombre, int puntos, int nivel, String fecha){
        arrayListJugador.add(new Jugador(nombre, puntos, nivel, fecha));
    }
    public void ordenarArrayList(){
         Collections.sort(arrayListJugador); //Los metodos compareTo y toString fueron sobrecargados (Override) en jugador.
    }
    /** Método que guardará el arrayList en una cadena para luego escribirla en el archivo.*/
    public String guardarEnCadena(){
        for(Jugador j : arrayListJugador)/*Escribir cada línea separando los valores con espacios y al final un salto de línea.*/
            //textoArchivo = textoArchivo.concat(j.getNombreJugador() +" "+ j.getPuntos() +" "+ j.getNivel() +" "+ j.getFecha() +"\n");
            textoArchivo = textoArchivo.concat(j.getInformacion());
        return textoArchivo; //Regresa el texto ordenado.
    }
    public void imprimirArrayList(){
        for(Jugador j : arrayListJugador)
            System.out.println(j); //Se imprimirá mediante el toString.
    }
}
