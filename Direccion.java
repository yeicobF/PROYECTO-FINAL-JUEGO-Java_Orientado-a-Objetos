/**
 * Clase en donde se manejarán las constantes de las direcciones: Arriba, abajo, ...
 * También se manejarán los ángulos de giro.
 * @author (Team Naves)
 * @version (Jueves, 4 de junio de 2020)
 */
public abstract class Direccion
{
    /* - Acomodé los valores conforme a la rotación del círculo en los ángulos.
       Los ángulos recorren el círculo por debajo primero.*/
    //Constantes de las direcciones.
    public static final int DERECHA = 0;
    public static final int ABAJO_DERECHA = 1;
    public static final int ABAJO = 2;
    public static final int ABAJO_IZQUIERDA = 3;
    public static final int IZQUIERDA = 4;
    public static final int ARRIBA_IZQUIERDA = 5;
    public static final int ARRIBA = 6;
    public static final int ARRIBA_DERECHA =7;
    //ÁNGULOS
    public static final int ANGULO_DERECHA = 0;
    public static final int ANGULO_ABAJO_DERECHA = 45;
    public static final int ANGULO_ABAJO = 90;
    public static final int ANGULO_ABAJO_IZQUIERDA = 135;
    public static final int ANGULO_IZQUIERDA = 180;
    public static final int ANGULO_ARRIBA_IZQUIERDA = 225;
    public static final int ANGULO_ARRIBA = 270;
    public static final int ANGULO_ARRIBA_DERECHA = 315;
    //Para devolver en el método
    private static int angulo;
    /** Método para obtener el ángulo dependiendo de la dirección dada.*/
    public static int getAnguloDireccion(int direccion){
      switch(direccion){
        case ARRIBA: angulo = ANGULO_ARRIBA; break;
        case ABAJO: angulo = ANGULO_ABAJO; break;
        case IZQUIERDA: angulo = ANGULO_IZQUIERDA; break;
        case DERECHA: angulo = ANGULO_DERECHA; break;
        case ARRIBA_IZQUIERDA: angulo = ANGULO_ARRIBA_IZQUIERDA; break;
        case ARRIBA_DERECHA: angulo = ANGULO_ARRIBA_DERECHA; break;
        case ABAJO_IZQUIERDA: angulo = ANGULO_ABAJO_IZQUIERDA; break;
        case ABAJO_DERECHA: angulo = ANGULO_ABAJO_DERECHA; break;
      }
      return angulo;
    }
}
