import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * - El nombre o la implementación podría cambiar en un futuro. -
 * En esta clase estarán métodos más generales para que los pueda implementar cualquier clase.
 *  Por ejemplo: Reescalar imagen o la destruccion de objetos al chocar.
 * 
 * @author (your name) 
 * @version (Domingo, 10 de mayo - Lunes, 11 de mayo de 2020)
 */
public class MetodosGenerales  
{
    // instance variables - replace the example below with your own
    private int x;

    /**
     * Constructor for objects of class MetodosGenerales
     */
    public MetodosGenerales()
    {
    }

    /*Método para reescalar una imagen de acuerdo a los parámetros y regresarla modificada.
     * - Recibe la imagen del tipo GreenfootImage, que es como Greenfoot las maneja.
        Lo que hace es dividir el ancho de la imagen recibida entre "division" y luego multiplicarlo por "multiplicacion",
            así obtendremos una imagen de esa proporcion.
        Ancho Imagen final = Ancho/divisor * multiplicación
        Alto Imagen final = Alto/divisor * multiplicación*/
    public GreenfootImage modificarEscalaImagen(GreenfootImage imagen, int divisor, int multiplicacion){
        imagen.scale(imagen.getWidth()/divisor*multiplicacion, imagen.getHeight()/divisor*multiplicacion);
        return imagen;
    }
    /*Método que revisa si ocurrió un choque de un objeto con el que se manda de parámetro.
        Simplemente regresa verdadero o falso si choca o no.*/
    public boolean choqueActores(Actor objeto){
        return objeto != null;  //Aquí revisa que el objeto exista. Si el objeto existe entonces sí ocurrió un choque.
    }
    /*Método que elimina un objeto si chocó con otro. Hay que mandarle los objetos que chocaron y su mundo.
        El mundo actual es de tipo "World" porque es la mayor generalización. De ahí siguen los niveles que son class diferentes.*/
    public boolean eliminarObjetoChoque(Actor objetoChoque, Actor objetoRaiz, World mundoActual){
        if(choqueActores(objetoChoque)){
            //Espacio e = (Espacio)getWorld();//Se toma el mundo actual. - Ya se recibe como parámetro.
            mundoActual.removeObject(objetoChoque);//Se elimina el objeto con el que se chocó.
            mundoActual.removeObject(objetoRaiz);//Se elimina el objeto que chocó. El objeto que llama el método.
            return true;//Regresar verdadero si se hizo el procedimiento anterior. Es decir, se eliminó el objeto.
        }
        return false;//Regresa falso si no se hizo el procedimiento anteior.
    }//En el método de destruir nave habrá que bajar vidas si se eliminó.
    
   /*MÉTODO PARA CALCULAR UN RANDOM EN UN RANGO DE NÚMEROS*/
    public int getRandomNumber(int start,int end){
       int normal = Greenfoot.getRandomNumber(end-start+1);
       return normal+start;
    }
}
