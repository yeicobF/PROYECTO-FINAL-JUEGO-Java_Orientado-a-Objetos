import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;  
import java.time.LocalDate;
/**
 * Clase que manejará la fecha actual. Esto funcionará para guardar los marcadores.
 * Obtuve la forma de obtener la fecha con nuestro formato indicado en: 
 *           https://www.javatpoint.com/java-get-current-date
 * 
 * @author (Team Naves) 
 * @version (Miércoles, 17 de junio de 2020)
 */
public class Fecha{
    //Aquí le daremos el formato a la fecha. Día/Mes/Año Hora:Minutos:segundos.
    private DateTimeFormatter formatoFecha  = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    //Aquí se obtendrá la fecha local actual.
    private LocalDateTime fechaActual;
    public Fecha(){
        //System.out.println(getFecha());
    }
    public String getFecha(){
       // LocalDateTime fecha = LocalDateTime.now();
       // DateTimeFormatter formatoTiempo = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
       // String texto = fecha.format(formatoTiempo);
       // LocalDate fechaOrdenada = LocalDate.parse(texto, formatoTiempo);
       // //System.out.println(dtf.format(now));
       // now= LocalDateTime.now();  
       // System.out.println(dtf.format(now));now = LocalDateTime.now();  
       fechaActual = LocalDateTime.now(); //Obtener la fecha actual.
       //System.out.println(formatoFecha.format(fechaActual));  
       return formatoFecha.format(fechaActual); //Regresar la fecha actual con el formato que le dimos.
    }
   
}