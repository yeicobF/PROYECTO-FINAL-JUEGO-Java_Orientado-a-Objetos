import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Clase que mostrará la información en pantalla. Es decir, mostrará las vidas, puntos y más información de ser necesaria.
 *  Esta clase la hice a partir de la anterior clase MostrarVidas. Mejor lo convertiré en método.
 * @author (Jacob) 
 * @version (Martes, 2 de junio - Miércoles 3 de junio de 2020)
 */
public class MostrarInfo extends NaveAliada
{
    Etiqueta e;//Instanciar clase etiqueta para agregar el cuadro de texto.
    int tipoInfo; //Lo que se mostrará: 1. Vidas y puntos; 2. Puntos de Salud; 3. Nivel
    /*puntosSalud proviene de la clase Nave de la cual hereda NaveAliada y esta hereda de NaveAliada,
        el atributo es protegido, por eso podemos acceder.*/
    int vidas, puntos, puntosSalud, nivelActual;
    /* Constructor para NaveAliada y NaveEnemiga en donde pasarán la salud inicial de estas.
        Esto para luego mostrar y actualizar la salud, ya que quitaré los atributos de ser estáticos,
            esto porque causa problemas con las Naves enemigas, porque todas tendrán el mismo valor
            por lo mismo de ser estáticos, entonces lo dejaré como antes: Protegido en la clase Nave (superclase)
            y en el método act pasaré los puntos de salud actuales y así se actualizarán sin necesidad de ser estáticos.*/
    public MostrarInfo(int puntosSalud, int tipoInfo, int tamañoFuente, Color colorFuente, Color colorFondo, Color bordeFuente){
        this(tipoInfo, tamañoFuente, colorFuente, colorFondo, bordeFuente); //Llam
        this.puntosSalud = puntosSalud;
        setImage(e.crearCuadroTexto("PS: "+ puntosSalud));
    }
    public MostrarInfo(int tipoInfo, int tamañoFuente, Color colorFuente, Color colorFondo, Color bordeFuente){
        //Etiqueta(int tamañoFuente, Color colorFuente, Color colorFondo, Color bordeFuente);
        e = new Etiqueta(tamañoFuente, colorFuente, colorFondo, bordeFuente); //Inicializar la etiqueta a mostrar con las vidas
        this.tipoInfo = tipoInfo;
        /*Esto se podría mejorar recibiendo como parámetros la cadena y el valor, pero aún así habría que verificar
            cuando los valores cambien para que no se esté haciendo el proceso infinitas veces.*/
        /*No habrá tipoInfo para los PS, así que le daré el 0 pero haré un break. Podría crear aquí la imagen,
           pero no se podría porque en este punto aún no se asignan los puntos de salud, así que la imagen la crearé en el otro constructor.*/
        switch(tipoInfo){ 
        case 0: break; //Si es 0 = PS, no hacen nada aquí
        case 1:
            vidas = NaveAliada.getVidasJugador();
            setImage(e.crearCuadroTexto("Vidas: "+ NaveAliada.getVidasJugador()));
            break;
        case 2:
            puntos = NaveAliada.getPuntos();
            setImage(e.crearCuadroTexto("Puntos: "+ NaveAliada.getPuntos()));
            break;
        // case 3:
            // /*Los puntos de salud era un atributo estático proveniente de la clase Nave,
                // por lo que cada que el valor cambiara para NaveAliada cambiaría también para NaveEnemiga
                // y viceversa. Mejor sólo lo hice protegido sin ser estático.
                // - El problema es que si no es estático, aquí tendremos la variable pero no el valor
                    // de la nave aliada. Así no se modificará constantemente, así que lo necesitamos
                    // estático pero de la propia NaveAliada.*/
            // puntosSalud = NaveAliada.getPuntosSalud(); //Para cambiar el texto si estos cambian.
            // System.out.println();
            // setImage(e.crearCuadroTexto("PS: "+ NaveAliada.getPuntosSalud()));
            // break;
        case 3:
            nivelActual = Niveles.getNivelActual(); //Ya que aún no se implementa la clase
            setImage(e.crearCuadroTexto("Nivel "+ Niveles.getNivelActual()));
            break;
        // case 5: //PS de nave enemiga
            // puntosSalud = NaveEnemiga.getPuntosSalud();
            // setImage(e.crearCuadroTexto("PS: "+ NaveEnemiga.getPuntosSalud()));//Sólo mostrar los PS encima.
            // break;
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
    //Método para hacer lo mismo que en act() pero con los PS de las naves. Se irá moviendo el texto de acuerdo a las coord de la nave.
    public void mostrarPS(String texto, int puntosSalud, int x, int y){ //Los puntos de salud se irán actualizando.
        this.puntosSalud = mostrarInformacion(texto, this.puntosSalud, puntosSalud);
        setLocation(x, y-getImage().getHeight()/2); //Mover los PS a la localización dada. Esto para seguir a las naves.
    }
    public void act() 
    {
        /*Si la información cambia, entonces volver a establecer la imagen, esto para que el proceso no se haga
            infinitas veces.*/
        switch(tipoInfo){
            case 0: 
                // if(NaveAliada.class = null)
                    
                // if(isTouching(NaveAliada.class))
                    // getWorld().removeObject(this);
                break; //Puntos de salud, llamaré al otro método.
            case 1:
                vidas = mostrarInformacion("Vidas: ", vidas, NaveAliada.getVidasJugador());
                break;
            case 2:
                puntos = mostrarInformacion("Puntos: ", puntos, NaveAliada.getPuntos());
                break;
            case 3:
                nivelActual = mostrarInformacion("Nivel: ", nivelActual, Niveles.getNivelActual());
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
