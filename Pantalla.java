import greenfoot.World;
import greenfoot.Actor;
import greenfoot.GreenfootImage;
/**
 * Clase abstracta que manejará métodos como los límites de pantalla, por ejemplo
 * 
 * @author (Jacob) 
 * @version (Jueves, 4 - Viernes, 5 de junio de 2020)
 */
public class Pantalla  
{
    Actor objeto;
    private int tamañoImagenMax; //Tomará el tamaño mayor de la imagen como referencia para que siempre cumpla la condición.
    /*Método que regresa true si el objeto está dentro del límite del mundo.
        Nos tendrá que regresar luego dentro de los límites para poder seguir actuando,
            si no la nave se quedará trabada.*/
    public Pantalla(Actor objeto){
        this.objeto = objeto;
        if(objeto.getImage().getWidth() > objeto.getImage().getHeight())
            tamañoImagenMax = objeto.getImage().getWidth();
        else
            tamañoImagenMax = objeto.getImage().getHeight();
        //System.out.println("- Pantalla: "+ tamañoImagenMax);
    }
    public boolean isObjetoLimite(World mundoActual, int x, int y){
      //Comparar límite de la pantalla con el objeto dentro, es decir, se toma en cuenta la mitad de su imagen.
        if(x <= tamañoImagenMax/2 || (x >= mundoActual.getWidth() - tamañoImagenMax/2)
            || y <= tamañoImagenMax/2 || (y >= mundoActual.getHeight() - tamañoImagenMax/2)){
            return false;
        }
        return true; //Si está dentro de los límites, regresar false
    }
    /*Método que regresará al objeto dentro de los límites si se sale de estos.*/
    public void regresarObjetoLimite(World mundoActual, int x, int y){
        //Si le pongo ++ o -- no hace nada
        if(x <= tamañoImagenMax/2)//Izquierda
            objeto.setLocation(x+1, y);
        if(x >= mundoActual.getWidth() - tamañoImagenMax/2) //No necesitamos especificar la condición porque ya sabemos que está fuera de los límites.
            objeto.setLocation(x-1, y);//Derecha
        if(y <= tamañoImagenMax/2)//Límite superior
            objeto.setLocation(x, y+1);
        if(y >= mundoActual.getHeight() - tamañoImagenMax/2)
            objeto.setLocation(x, y-1); //Abajo
    }
}
