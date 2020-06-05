/**
 * Clase en donde se manejaràn las constantes de las direcciones: Arriba, abajo, ...
 * También se manejarán los ángulos de giro.
 * @author (Jacob)
 * @version (Jueves, 4 de junio de 2020)
 */
public abstract class Direccion
{
    //Constantes de las direcciones.
    public static final int ARRIBA = 0;
    public static final int ABAJO = 1;
    public static final int IZQUIERDA = 2;
    public static final int DERECHA = 3;
    public static final int ARRIBA_DERECHA =4;
    public static final int ARRIBA_IZQUIERDA = 5;
    public static final int ABAJO_IZQUIERDA = 6;
    public static final int ABAJO_DERECHA = 7;
    //ÁNGULOS
    public static final int ANGULO_ARRIBA = 0;
    public static final int ANGULO_ABAJO = 180;
    public static final int ANGULO_IZQUIERDA = 270;
    public static final int ANGULO_DERECHA = 90;
    public static final int ANGULO_ARRIBA_DERECHA = 45;
    public static final int ANGULO_ARRIBA_IZQUIERDA = 315;
    public static final int ANGULO_ABAJO_IZQUIERDA = 225;
    public static final int ANGULO_ABAJO_DERECHA = 135;
    //Para devolver en el método
    private static int angulo;

    /*Método para obtener el ángulo dependiendo de la dirección dada*/
    public static int getAnguloDireccion(int direccion){
      switch(direccion){
        case ARRIBA:
          angulo = ANGULO_ARRIBA;
          break;
        case ABAJO:
          angulo = ANGULO_ABAJO;
          break;
        case IZQUIERDA:
          angulo = ANGULO_IZQUIERDA;
          break;
        case DERECHA:
          angulo = ANGULO_DERECHA;
          break;
        case ARRIBA_IZQUIERDA:
          angulo = ANGULO_ARRIBA_IZQUIERDA;
          break;
        case ARRIBA_DERECHA:
          angulo = ANGULO_ARRIBA_DERECHA;
          break;
        case ABAJO_IZQUIERDA:
          angulo = ANGULO_ABAJO_IZQUIERDA;
          break;
        case ABAJO_DERECHA:
          angulo = ANGULO_ABAJO_DERECHA;
          break;
      }
      return angulo;
    }
}
