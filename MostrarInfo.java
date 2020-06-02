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
    /*puntosSalud proviene de la clase Nave de la cual hereda NaveAliada y esta hereda de NaveAliada,
        el atributo es protegido, por eso podemos acceder.*/
    int vidas, puntos, puntosSalud, nivelActual;
    public MostrarInfo(int tipoInfo, int tamañoFuente, Color colorFuente, Color colorFondo, Color bordeFuente){
        //Etiqueta(int tamañoFuente, Color colorFuente, Color colorFondo, Color bordeFuente);
        e = new Etiqueta(tamañoFuente, colorFuente, colorFondo, bordeFuente); //Inicializar la etiqueta a mostrar con las vidas
        this.tipoInfo = tipoInfo;
        /*Esto se podría mejorar recibiendo como parámetros la cadena y el valor, pero aún así habría que verificar
            cuando los valores cambien para que no se esté haciendo el proceso infinitas veces.*/
        switch(tipoInfo){    
        case 1:
            vidas = NaveAliada.getVidasJugador();
            setImage(e.crearCuadroTexto("Vidas: "+ NaveAliada.getVidasJugador()));
            break;
        case 2:
            puntos = NaveAliada.getPuntos();
            setImage(e.crearCuadroTexto("Puntos: "+ NaveAliada.getPuntos()));
            break;
        case 3:
            /*Los puntos de salud era un atributo estático proveniente de la clase Nave,
                por lo que cada que el valor cambiara para NaveAliada cambiaría también para NaveEnemiga
                y viceversa. Mejor sólo lo hice protegido sin ser estático.
                - El problema es que si no es estático, aquí tendremos la variable pero no el valor
                    de la nave aliada. Así no se modificará constantemente, así que lo necesitamos
                    estático pero de la propia NaveAliada.*/
            puntosSalud = NaveAliada.getPuntosSalud(); //Para cambiar el texto si estos cambian.
            System.out.println();
            setImage(e.crearCuadroTexto("PS: "+ NaveAliada.getPuntosSalud()));
            break;
        case 4:
            nivelActual = Niveles.getNivelActual(); //Ya que aún no se implementa la clase
            setImage(e.crearCuadroTexto("Nivel "+ Niveles.getNivelActual()));
            break;
        case 5: //PS de nave enemiga
            puntosSalud = NaveEnemiga.getPuntosSalud();
            setImage(e.crearCuadroTexto("PS: "+ NaveEnemiga.getPuntosSalud()));//Sólo mostrar los PS encima.
            break;
        default: 
            setImage(e.crearCuadroTexto("Ese tipo de información no existe.\n - FIN DEL JUEGO - "));
            try{
                Thread.sleep(3000);//Parar el sistema un momento.
            }catch(InterruptedException ie){
                System.out.println("Interrupción sleep.");
            }
            break;
        }
    }
    public void act() 
    {
        /*Si la información cambia, entonces volver a establecer la imagen, esto para que el proceso no se haga
            infinitas veces.*/
        switch(tipoInfo){
            case 1:
                vidas = mostrarInformacion("Vidas: ", vidas, NaveAliada.getVidasJugador());
                break;
            case 2:
                puntos = mostrarInformacion("Puntos: ", puntos, NaveAliada.getPuntos());
                break;
            case 3:
                puntosSalud = mostrarInformacion("PS: ", puntosSalud, NaveAliada.getPuntosSalud());
                break;
            case 4:
                nivelActual = mostrarInformacion("Nivel: ", nivelActual, Niveles.getNivelActual());
            case 5: //Nave enemiga
                puntosSalud = mostrarInformacion("PS: ", puntosSalud,  NaveEnemiga.getPuntosSalud());
            default:
                //getWorld().removeObject(this); //Quitar el cuadro de texto, aunque creo que no será necesario.
                Greenfoot.stop();//Parar el mundo porque no se ingresó un tipo de Información válido.
                break;
        }
        
    }   
    //Método que verificará si los valores han cambiado para actualizar la imagen. Esto para que la imagen no cambie todo el tiempo.
    private int mostrarInformacion(String textoInfo, int valorAntes, int valorNuevo){
        if(valorAntes != valorNuevo){
            setImage(e.crearCuadroTexto(textoInfo + valorNuevo));
            System.out.println(textoInfo+ "-> Antes: "+ valorAntes +" ValorAhora: "+ valorNuevo);
            return valorNuevo;
        }
        return valorAntes;
    }
}
