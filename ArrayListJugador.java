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
    ArrayList<Jugador> arrayListJugador;
    String textoArchivo; //El texto que se guardará en el archivo.
    /** Constructor para la clase de ArrayListJugador.*/
    public ArrayListJugador(){
        arrayListJugador = new ArrayList<Jugador>(); //Inicializar el arrayList.
        textoArchivo = "";
    }
    /** Método para agregar a un jugador al arrayList.*/
    public void addJugador(String nombre, int puntos, int nivel, String fecha){
        arrayListJugador.add(new Jugador(nombre, puntos, nivel, fecha));
    }
    /** Método para ordenar el arrayList dependiendo de los puntos del jugador.*/
    public void ordenarArrayList(){
         Collections.sort(arrayListJugador); //Los metodos compareTo y toString fueron sobrecargados (Override) en jugador.
    }
    /** Método que guardará el arrayList en una cadena para luego escribirla en el archivo.*/
    public String guardarEnCadena(){
        for(Jugador j : arrayListJugador)/*Escribir cada línea separando los valores con espacios y al final un salto de línea.*/
            textoArchivo = textoArchivo.concat(j.getInformacion());
        return textoArchivo; //Regresa el texto ordenado.
    }
    /** Método que verificará si el nombre ingresado ya existe en los marcadores o no.*/
    public boolean isNombreMarcadores(String nombreIngresado){
        for(Jugador j : arrayListJugador){
            if(j.getNombreJugador().equals(nombreIngresado)) //https://www.geeksforgeeks.org/compare-two-strings-in-java/
                return true; //Si regresa true en algún momento, significa que el nombre ya estaba en los marcadores.
        }
        return false; //Si nunca regresó true, llegará hasta aquí y regresará false.
    }
    /** Método para ver que el nombre ingresado por el usuario no esté repetido.*/
    public void imprimirArrayList(){
        for(Jugador j : arrayListJugador)
            System.out.println(j); //Se imprimirá mediante el toString.
    }
}
