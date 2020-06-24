import greenfoot.Actor;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import greenfoot.World;
/**
 * Esta es la superclase que manejará a las clases relacionadas con naves.
 *
 * @author (Team Naves)
 * @version (Jueves, 4 de junio de 2020)
 */
public abstract class Nave extends Actor
{
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
    /** Constructor para clase nave que no necesitará especificar el tipo del disparo ni su diseño.*/
    public Nave(){
      puntosSalud = 100;//Puntos de salud estándar = 100. Cambiarán dependiendo del tipo de nave y su poder.
      existeMostrarInfo = false;
    }
    /** Constructor de Nave para las naves enemigas.*/
    public Nave(int tipoDisparo, int diseñoNave){
        this(); //Llamar al otro constructor para recibir los atributos.
        this.tipoDisparo = tipoDisparo;//Aquí condicionaremos para el diseño y eso pero en la clase Disparo.
        this.diseñoNave = diseñoNave; //De esto dependerá el diseño que tendrá la nave.
    }
    protected abstract void movimiento();
    /** Método que mantendrá a la nave dentro de los límites*/
    protected void movimientoLimites(World mundoActual, int x, int y){
        if(pantalla.isObjetoLimite(mundoActual, getX(), getY()))    
            movimiento(); //Si estamos dentro de los límites, movernos
        else //Si nos salimos de los límites, regresar a estos.
            pantalla.regresarObjetoLimite(mundoActual, getX(), getY());
    }
    /** Método para mostrar los PS de las naves. Protegido para que todas las naves lo puedan usar.*/
    protected boolean muestraPuntosSalud(MostrarInfo infoPS, boolean yaExistente, String texto, int puntosSalud, int x, int y){
        /* Si los PS no se han mostrado, se agrega el texto.*/
        if(!yaExistente)//getY()-getImage().getHeight()/2 <- Para posicionar encima de las naves.
            getWorld().addObject(infoPS, x, y);
        else
            /*En este método se mandan las coordenadas en donde se quieren agregar los PS. Estos se actualizarán cuando cambien.*/
            infoPS.mostrarPS(texto, puntosSalud, x, y);
        return true; //hacer yaExistente = true, es decir, ya se creó
    }
    /** Método que elimina el cuadro de texto donde se mostrarán los PS cuando estos sean igual a 0. Este método es necesario
        porque cuando los PS == 0, el método eliminarObjetoChoque elimina el objeto directamente.
        En este método, seguirá eliminando el objeto pero también el cuadro de texto.*/
    protected int eliminaCuadroPS(MostrarInfo infoPS, Actor objetoChoque, Actor objetoRaiz,
                                World mundoActual, int puntosSalud, int daño, int puntosNave){
        //Actor objetoChoque, Actor objetoRaiz, World mundoActual, int puntosSalud, int daño, int puntosNave
        if((puntosSalud = Choques.eliminarObjetoChoque(objetoChoque, objetoRaiz, mundoActual, puntosSalud, daño, puntosNave)) <= 0)
            mundoActual.removeObject(infoPS);
        return puntosSalud;
    }
    /** Método que eliminará el cuadro de texto sin ninguna condición. Esto para al chocar una nave enemiga se elimine su cuadro de texto.*/
    public void eliminaCuadroPS(MostrarInfo infoPS, World mundoActual){
        mundoActual.removeObject(infoPS);
    }
    public MostrarInfo getMostrarInfo(){
        return infoPS;
    }
    /** Getters Y setters protected para que sólo las subclases puedan acceder.*/
    protected int getPuntosSalud(){//Puntos de salud actuales. Al perder todos los puntos de salud se pierde una vida.
        return puntosSalud;
    }
}
