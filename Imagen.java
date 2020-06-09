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
    private int anchoMin, altoMin; //ancho y alto mínimos.
    //Constructor para utilizar los métodos de modiicación de imagen dinámica.
    public Imagen(GreenfootImage imagen){
        this.imagen = imagen;
        determinaDivisorImagenMaximo(); //Determinar el máximo divisor de imagen.
        determinaAnchoAltoMin(); //Determina los valores mínimos del ancho y alto de la imagen.
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
    /*Método que modificará la imagen a partir de los ancho y alto mínimos con los modificadores que le demos.*/
    public GreenfootImage modificaImagenAnchoAltoMin(GreenfootImage imagen, int divisor, int multiplicacion){
        //System.out.println("Modifica imagen: Ancho: "+ anchoMin/divisor*multiplicacion +", Alto"+ altoMin/divisor*multiplicacion);
        imagen.scale(anchoMin/divisor*multiplicacion, altoMin/divisor*multiplicacion);
        return imagen;
    }
    /*Método que determinará el ancho y alto mínimo de la imagen.*/
    private void determinaAnchoAltoMin(){
        //System.out.println("Determina ancho, alto min: Ancho: "+ anchoImagen / divisorImagenMax +", Alto"+  altoImagen / divisorImagenMax);
        anchoMin = imagen.getWidth() / divisorImagenMax;
        altoMin = imagen.getHeight() / divisorImagenMax;
    }
    
    /*Método que verifica si las dimensiones de la imagen son diferentes a 0 al modificar.*/
    public boolean isEscalaModificable(int divisor, int multiplicacion){
        if(imagen.getWidth()/divisor*multiplicacion > 0 && imagen.getHeight()/divisor*multiplicacion > 0){
            //System.out.println("Ancho: "+ anchoImagen/divisor*multiplicacion +", Alto"+ altoImagen/divisor*multiplicacion);
            return true;
        }
        return false; //Si las dimensiones modificadas dan 0, regresar false.
    }
    /*Método que determina el divisor máximo de una imagen. No se le puede reducir a un número mayor que el determinado. 7
     * El multiplicador será 1.*/
     private void determinaDivisorImagenMaximo(){
        divisorImagenMax = 1;
        while(isEscalaModificable(divisorImagenMax, 1))
            divisorImagenMax ++; //Aumentar el divisor.
        divisorImagenMax --;//Disminuir el valor en 1 porque como inicializamos en 1 porque no se puede dividir entre 0, el valor será mayor.
    }
    /*Método que devuelve el divisor máximo del tamaño de la imagen.*/
    public int getDivisorImagenMaximo(){
        return divisorImagenMax;
    }
}
