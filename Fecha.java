import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;  
import java.time.LocalDate;
/**
 * Clase que manejará la fecha actual. Esto funcionará para guardar los marcadores.
 * Obtuve la forma de obtener la fecha con nuestro formato indicado en: 
 *           https://www.javatpoint.com/java-get-current-date
 * 
 * @author (Jacob) 
 * @version (Miércoles, 17 de junio de 2020)
 */
public abstract class Fecha
{
    //Aquí le daremos el formato a la fecha. Día/Mes/Año Hora:Minutos:segundos.
    private static DateTimeFormatter formatoFecha  = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    //Aquí se obtendrá la fecha local actual.
    private static LocalDateTime fechaActual;
    /** Método para obtener la fecha actual en el formato dado arriba.*/
    public static String getFecha(){
       fechaActual = LocalDateTime.now(); //Obtener la fecha actual.
       return formatoFecha.format(fechaActual); //Regresar la fecha actual con el formato que le dimos.
    }
   
}