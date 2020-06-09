import greenfoot.GreenfootImage; //Sólo importar el paquete de imágenes.
/**
 * Clase que manejará métodos relacionados con las imágenes.
 * No necesitará instanciarse, por lo que será abstracto.
 * 
 * @author (Jacob) 
 * @version (Martes, 9 de junio de 2020)
 */
public class Imagen  
{
    private GreenfootImage imagen;
    private int divisorImagenMax; //Es el divisor máximo al que puede disminuirse la imagen. Se definirá en un método. 
    //Constructor para utilizar los métodos de modiicación de imagen dinámica.
    public Imagen(GreenfootImage imagen){
        this.imagen = imagen;
        determinaDivisorImagenMaximo(imagen); //Determinar el máximo divisor de imagen.
    }

    /*Método para reescalar una imagen de acuerdo a los parámetros y regresarla modificada.
     * - Recibe la imagen del tipo GreenfootImage, que es como Greenfoot las maneja.
        Lo que hace es dividir el ancho de la imagen recibida entre "division" y luego multiplicarlo por "multiplicacion",
            así obtendremos una imagen de esa proporcion.
        Ancho Imagen final = Ancho/divisor * multiplicación
        Alto Imagen final = Alto/divisor * multiplicación*/
    public static GreenfootImage modificarEscalaImagen(GreenfootImage imagen, int divisor, int multiplicacion){
        /*Si las dimensiones modificadas miden más que 0, entonces sí hacer los cambios. Si no, no los hace y de igual
            forma devuele la imagen.*/
        imagen.scale(imagen.getWidth()/divisor*multiplicacion, imagen.getHeight()/divisor*multiplicacion);
        return imagen;
    }
    /*Método que verifica si las dimensiones de la imagen son diferentes a 0 al modificar.*/
    public boolean isEscalaModificable(GreenfootImage imagen, int divisor, int multiplicacion){
        if(imagen.getWidth()/divisor*multiplicacion > 0 && imagen.getHeight()/divisor*multiplicacion > 0)
            return true;
        return false; //Si las dimensiones modificadas dan 0, regresar false.
    }
    /*Método que determina el divisor máximo de una imagen. No se le puede reducir a un número mayor que el determinado. 7
     * El multiplicador será 1.*/
     private void determinaDivisorImagenMaximo(GreenfootImage imagen){
        divisorImagenMax = 0;
        while(isEscalaModificable(imagen, divisorImagenMax, 1))
            divisorImagenMax ++; //Aumentar el divisor.
    }
    /*Método que devuelve el divisor máximo del tamaño de la imagen.*/
    public int getDivisorImagenMaximo(){
        return divisorImagenMax;
    }
}
