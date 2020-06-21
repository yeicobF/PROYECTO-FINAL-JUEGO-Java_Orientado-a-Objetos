import greenfoot.Greenfoot; //Contiene el getRandomNumber.
/**
 * Clase que manejará métodos estáticos que tengan que ver con algo
 * generado de manera aleatoria.
 * No necesita ser instanciada.
 * 
 * @author (Jacob) 
 * @version (Jueves, 4 de junio de 2020)
 */
public abstract class Aleatorio 
{
    /*MÉTODO PARA CALCULAR UN NÚMERO ALEATORIO EN UN RANGO DE NÚMEROS*/
    public static int getNumeroAleatorio(int numeroInicial, int numeroFinal){
       int numeroAleatorio = Greenfoot.getRandomNumber(numeroFinal - numeroInicial +1);
       return numeroAleatorio + numeroInicial; //Regresa el número que se generó sumado con el inicial para mantener el rango.
    }
}
