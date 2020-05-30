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
    int tipoInfo; //Lo que se mostrará: 1. Vidas y puntos; 2. Puntos de Salud; 3. Nivel
    int vidas, puntos, puntosSalud;
    int valorAntes;//, valorNuevo;//Variables que verán si los valores han cambiado.
    public MostrarInfo(int tipoInfo, Actor objeto){
        //Etiqueta(int tamañoFuente, Color colorFuente, Color colorFondo, Color bordeFuente);
        e = new Etiqueta(30, Color.WHITE, Color.BLACK, Color.ORANGE); //Inicializar la etiqueta a mostrar con las vidas
        /*Esto se podría mejorar recibiendo como parámetros la cadena y el valor, pero aún así habría que verificar
            cuando los valores cambien para que no se esté haciendo el proceso infinitas veces.*/
        NaveAliada valorNuevo;
                valorNuevo = (NaveAliada)objeto;
                valorAntes =  NaveAliada.getVidasJugador();
            switch(tipoInfo){    
            case 1:
                
                setImage(e.crearCuadroTexto("Vidas: "+ NaveAliada.getVidasJugador()));
                break;
            // case 2:
                // valorAntes = valorNuevo = NaveAliada.getPuntos();
                // setImage(e.crearCuadroTexto("Puntos: "+ NaveAliada.getPuntos()));
                // break;
            // case 3:
                // valorAntes = valorNuevo = NaveAliada.getPuntosSalud(); //Para cambiar el texto si estos cambian.
                // setImage(e.crearCuadroTexto("PS: "+ NaveAliada.getPuntosSalud()));
                // break;
            // case 4:
                // valorAntes = valorNuevo = Niveles.getNivelActual();
                // setImage(e.crearCuadroTexto("Nivel "+ Niveles.getNivelActual()));
                // break;
        }
    }
    public void act() 
    {
        /*Si la información cambia, entonces volver a establecer la imagen, esto para que el proceso no se haga
            infinitas veces.*/
            
        if(mostrarInformacion("Vidas: ", valorAntes, valorNuevo.getVidasJugador()))
            valorAntes = valorNuevo.getVidasJugador();
    }   
    public boolean mostrarInformacion(String textoInfo, int valorAntes, int valorNuevo){
        if(valorAntes != valorNuevo){
            setImage(e.crearCuadroTexto(textoInfo + valorNuevo));
            return true; //Regresa true si los valores cambiaron.
        }
        return false;
    }
}
