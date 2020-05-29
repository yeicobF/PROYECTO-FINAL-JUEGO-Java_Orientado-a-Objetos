import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Clase que mostrará la información en pantalla. Es decir, mostrará las vidas, puntos y más información de ser necesaria.
 *  Esta clase la hice a partir de la anterior clase MostrarVidas. Mejor lo convertiré en método.
 * @author (Jacob) 
 * @version (Viernes, 29 de mayo de 2020)
 */
public class MostrarInfo extends NaveAliada
{
    Etiqueta e;//Instanciar clase etiqueta para agregar el cuadro de texto.
    int vidas;
    int puntos;
    public MostrarInfo(){
        //Etiqueta(int tamaño, Color colorPrimerPlano, Color colorFondo);
        e = new Etiqueta(30, Color.WHITE, Color.BLACK); //Inicializar la etiqueta a mostrar con las vidas
        vidas = NaveAliada.getVidasJugador();
        puntos = NaveAliada.getPuntos();
        setImage(e.crearCuadroTexto("Vidas: "+ NaveAliada.getVidasJugador() 
                                    +"\nPuntos: "+ NaveAliada.getPuntos()));
    }
    public void act() 
    {
        /*Si la información cambia, entonces volver a establecer la imagen, esto para que el proceso no se haga
            infinitas veces.*/
        if(vidas != NaveAliada.getVidasJugador() || puntos != NaveAliada.getPuntos())
            setImage(e.crearCuadroTexto("Vidas: "+ NaveAliada.getVidasJugador() 
                                        +"\nPuntos: "+ NaveAliada.getPuntos()));
    }    
}
