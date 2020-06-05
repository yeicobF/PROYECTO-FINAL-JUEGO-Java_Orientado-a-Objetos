import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Esta es la superclase que manejará a las clases relacionadas con naves.
 *
 * @author (Team Naves)
 * @version (Jueves, 4 de junio de 2020)
 */
public abstract class Nave extends Actor
{
    //Nave n; //Inicializar la nave para después instanciarla, aunque no es necesario porque esta clase no se utilizará
    protected World mundo;
    protected Disparo disparo; //Porque las naves aliadas y enemigas lo necesitan
    protected MostrarInfo infoPS; //Inicializar la clase MostrarInfo para el cuadro de texto de los PS
    protected Pantalla pantalla; //Las naves deberán mantenerse dentro de loslímites.
    /*Los PS eran estáticos pero así valían lo mismo y cambiaban con todas las estancias.
     *  Cuando bajaba la salud de una nave enemiga, también bajaba la nuestra.*/
    protected int puntosSalud;//Puntos de salud actuales. Al perder todos los puntos de salud se pierde una vida.
    protected int tipoDisparo; //Dependiendo del tipo del disparo cambiará su sprite. Estos serán como las mejoras.
    protected int direccion;
    protected int anguloGiro; //Indicará a dónde apuntamos. Esto servirá para el disparo.
    protected int diseñoNave;//El diseño de la nave
    protected int tipoHabilidad;//Esto serán los PowerUps.
    protected boolean existeMostrarInfo;
    /*NO NECESITAMOS coordX ni coordY porque ya están getX() y getY()*/
    public Nave(){//Para clase nave que no necesitará especificar el tipo del disparo ni su diseño.
      puntosSalud = 100;//Puntos de salud estándar = 100. Cambiarán dependiendo del tipo de nave y su poder.
      existeMostrarInfo = false;
    }//Constructor vacío para no tener problemas en Disparo
    public Nave(int tipoDisparo, int diseñoNave){//char diseño para cuando tengamos más diseños
        //Los puntos de salud ahora serán implementados en cada clase por separado.

        this(); //Llamar al otro constructor para recibir los atributos.
        this.tipoDisparo = tipoDisparo;//Aquí condicionaremos para el diseño y eso pero en la clase Disparo.
        this.diseñoNave = diseñoNave; //De esto dependerá el diseño que tendrá la nave.
    }//CONSTRUCTOR en el que se definirá si la nave es 0.- enemigo o 1.-Nosotros
    //Método abstracto de movimiento. Todas las naves lo tendrán pero su implementación será diferente.
    //protected abstract void movimiento();
    /*Clase que cambia la dirección dependiendo del parámetro que reciba. Además aquí mismo se ejecuta el movimiento.*/
    protected void setDireccion(int direccion){
        this.direccion = direccion; //Establecer nuestra direccion
        int aumentaX = 0, aumentaY = 0; //Variables para ver cuánto se mueve dependiendo de la condición.
        int[] aumenta = {aumentaX, aumentaY};//Arreglo que guardará los valores a aumentar. Sirve para el presionaShift.
        switch(direccion){
            case Direccion.ARRIBA:
                anguloGiro = Direccion.ANGULO_ARRIBA;
                aumentaY = -4;
                break;
            case Direccion.ABAJO:
                anguloGiro = Direccion.ANGULO_ABAJO;
                aumentaY = 4;
                break;
            case Direccion.IZQUIERDA:
                anguloGiro = Direccion.ANGULO_IZQUIERDA;
                aumentaX = -4;
                break;
            case Direccion.DERECHA:
                anguloGiro = Direccion.ANGULO_DERECHA;
                aumentaX = 4;
                break;
            /*A las diagonales no aumentarles valor, porque como ya actúan con la velocidad
               de la combinación de los botones, al aumentar valor irá más rápida.
               - Solo darles la dirección del ángulo para el disparo.*/
            case Direccion.ARRIBA_DERECHA:
                anguloGiro = Direccion.ANGULO_ARRIBA_DERECHA;
                break;
            case Direccion.ARRIBA_IZQUIERDA:
                anguloGiro = Direccion.ANGULO_ARRIBA_IZQUIERDA;
                break;
            case Direccion.ABAJO_IZQUIERDA:
                anguloGiro = Direccion.ANGULO_ABAJO_IZQUIERDA;
                break;
            case Direccion.ABAJO_DERECHA:
                anguloGiro = Direccion.ANGULO_ABAJO_DERECHA;
            break;
        }
        setRotation(anguloGiro);
        //Asignar los valores al arreglo
        aumenta[0] = aumentaX;
        aumenta[1] = aumentaY;
        //private void presionoShift(int[] aumenta){
        presionoShift(aumenta);

        //Verificar en dónde se modificaron los valores para aumentarlos
        setLocation(getX() + aumenta[0], getY() + aumenta[1]);
    }
    /*Método que va a verificar si se presionó shift y además cambiará los valores
      para que se vea con más velcidad en pantalla*/
    private void presionoShift(int[] aumenta){ //Como es un arreglo, los valores se modifican
      //aumenta[0] <- aumentaX; aumenta[1] = aumentaY;
      // + 1 si es en diagonal, +2 si es recto
      /* - NO AUMENTAR EL VALOR A LAS DIAGONALES PORQUE IRÁN MÁS RÁPIDO, YA QUE DE BASE ACTÚAN CON LAS
            VELOCIDADES DE LAS TECLAS COMBINADAS.*/
      if(Greenfoot.isKeyDown("shift"))
        //En esta condición no puede haber un valor con 0
          //LÍNEAS RECTAS (ARRIBA, ABAJO, IZQUIERDA, DERECHA) Aumentan de 2 en 2
        //X y Y no pueden ser diferentes a 0 al mismo tiempo
        /*En las diagonales va más rápido, habría que asignarles 1 en lugar de 2, pero por ahora así está bien.*/
        switch(direccion){
            case Direccion.ARRIBA:
                aumenta[1] -= 2;
                break;
            case Direccion.ABAJO:
                aumenta[1] += 2;
                break;
            case Direccion.IZQUIERDA:
                aumenta[0] -= 2;
                break;
            case Direccion.DERECHA:
                aumenta[0] += 2;
                break;
        }
    }

    /*Método protegido para obtener los puntos de salud actuales de las naves. Protegido para que se pueda utilizar
        desde las subclases y static para que no se necesite una instancia del objeto.
        Sólo se necesitaba para MostrarInfo, pero como es subclase de NaveAliada, no se necesita
            un getter para acceder, ya que puntosSalud es un atributo protegido.*/
    // protected static int getPuntosSalud(){
        // return puntosSalud;
    // }
    /*Método abstracto que será definido en las subclases regresando sus propios puntos de salud.
       Estos tendrán que ser distintos porque los necesitamos manejar como estáticos,
        y si son estáticos generales, estos valdrán lo mismo en todas las instancias.
        - Necesitaba que fuera estático, pero eso no se puede, por lo que lo implementaré a cada
            clase por separado aunque sea un mal diseño.*/
    //public abstract int getPuntosSalud();
    /*Método para mostrar los PS de las naves. Protegido para que todas las naves lo puedan usar.*/
    protected boolean muestraPuntosSalud(MostrarInfo infoPS, boolean yaExistente, String texto, int puntosSalud, int x, int y){
        /* Si los PS no se han mostrado, se agrega el texto.*/
        if(!yaExistente)//getY()-getImage().getHeight()/2 <- Para posicionar encima de las naves.
            getWorld().addObject(infoPS, x, y);
        else
            /*En este método se mandan las coordenadas en donde se quieren agregar los PS. Estos se actualizarán cuando cambien.*/
            infoPS.mostrarPS(texto, puntosSalud, x, y);
        return true; //hacer yaExistente = true, es decir, ya se creó
    }
    /*Método que elimina el cuadro de texto donde se mostrarán los PS cuando estos sean igual a 0. Este método es necesario
        porque cuando los PS == 0, el método eliminarObjetoChoque elimina el objeto directamente.
        En este método, seguirá eliminando el objeto pero también el cuadro de texto.*/
    protected int eliminaCuadroPS(MostrarInfo infoPS, Actor objetoChoque, Actor objetoRaiz,
                                World mundoActual, int puntosSalud, int daño, int puntosNave){
        //Actor objetoChoque, Actor objetoRaiz, World mundoActual, int puntosSalud, int daño, int puntosNave
        if((puntosSalud = Choques.eliminarObjetoChoque(objetoChoque, objetoRaiz, mundoActual, puntosSalud, daño, puntosNave)) == 0)
            mundoActual.removeObject(infoPS);
        return puntosSalud;
    }
    /*Método que eliminará el cuadro de texto sin ninguna condición. Esto para al chocar una nave enemiga se elimine su cuadro de texto.*/
    public void eliminaCuadroPS(MostrarInfo infoPS, World mundoActual){
        mundoActual.removeObject(infoPS);
    }
    public MostrarInfo getMostrarInfo(){
        return infoPS;
    }
    /*Podría hacer un método igual al de eliminaCuadroPS, pero que mostrara el recuadro con los PS = 0 antes de destruirse,
        aunque creo que no tendría mucho sentido.
    protected int eliminaCuadroPS(MostrarInfo infoPS, String texto, Actor objetoChoque, Actor objetoRaiz,
                                World mundoActual, int puntosSalud, int daño, int puntosNave)*/
    public int getCordX(){
        return getX();
    }
    public int getCordY(){
        return getY();
    }
    // NO NECESITO LOS SETTERS O GETTERS PORQUE LOS DECLARO PROTECTED PARA QUE SÓLO LAS SUBCLASES ACCEDAN
    //Getters Y setters protected para que sólo las subclases puedan acceder
    // protected int getPS(){//Puntos de salud actuales. Al perder todos los puntos de salud se pierde una vida.
        // return puntosSalud;
    // }
    // protected void setPS(int puntosSalud){//Puntos de salud actuales. Al perder todos los puntos de salud se pierde una vida.
        // this.puntosSalud = puntosSalud;
    // }

    // protected int getTipoDisparo(){//Dependiendo del tipo del disparo cambiará su sprite. Estos serán como las mejoras.
        // return tipoDisparo;
    // }
    // protected void setTipoDisparo(int tipoDisparo){//Dependiendo del tipo del disparo cambiará su sprite. Estos serán como las mejoras.
        // this.tipoDisparo = tipoDisparo;
    // }

    // protected int getDiseñoNave(){//El diseño de la nave
        // return diseñoNave;
    // }
    // protected void setDiseñoNave(int diseño){//El diseño de la nave
        // this.diseñoNave = diseñoNave;
    // }

    // protected int getTipoHabilidad(){//Esto serán los PowerUps.
        // return tipoHabilidad;
    // }
    // protected void setTipoHabilidad(int tipoHabilidad){//Esto serán los PowerUps.
        // this.tipoHabilidad = tipoHabilidad;
    // }
}
