import greenfoot.Actor;
import greenfoot.GreenfootImage;
import greenfoot.Greenfoot;
import greenfoot.Color;
import greenfoot.GreenfootSound;
/**
 * Clase que manejará la selección de naves. Mostrará las naves y dependiendo de a cuál
 *  se le haga click, se le establecerán sus propiedades.
 * 
 * @author (Jacob) 
 * @version (Sábado, 20 de junio de 2020)
 */
public class SeleccionNave extends Menu
{
    private static GreenfootSound musicaSeleccion = new GreenfootSound("seleccionNave.mp3");; //Estática y diferente para poder evaluar desde main.
    private final int numNaveInicial = 1, numNaveFinal = 4;
    private GreenfootImage imagen; //Para sacar las medidas de las flechas.
    private Boton naveActual, naveNueva; //Será de tipo Botón para que al seleccionarla cambie su tamaño.
    private Boton flechaDerecha;
    private Boton flechaIzquierda;
    private int numNaveActual, numNaveNueva, xActual, xNueva; //Número de la nave mostrada actualmente y la que se mostrará.
    private boolean cambioCompleto; //Verificará que el cambio de naves se ha terminado y se puede hacer otro.
    private boolean mostrarLado; //Para ver si la nave se muestra a la izquierda o derecha.
    /**
     * Constructor for objects of class SeleccionNave.
     * 
     */
    public SeleccionNave()
    {
        super(false); //Para no crear botón de "siguiente".
        setBackground("escenarios/espacio1.jpeg");
        /* Crear un texto que indique que es la selección de nave.*/
            //public Etiqueta(int tamañoFuente, Color colorFuente, Color colorFondo, Color bordeFuente)
        texto = new Etiqueta(35, Color.WHITE, null, null);
        getBackground().drawImage(texto.crearCuadroTexto("SELECCIONE SU NAVE"), getWidth()/2 - texto.getXCentrada(), 40);
        numNaveActual = numNaveNueva = 1; //Empezar con la primer nave.
        /*Crear botones de flechas:
            public static Boton creaBotonImagen(World mundoActual, GreenfootImage imagen, String nombreImagen, int x, int y)*/
        imagen = new GreenfootImage("Elementos/flechaRojaDerecha.png");
        flechaDerecha = Boton.creaBotonImagen(this, imagen, "Elementos/flechaRojaDerecha.png", getWidth() - imagen.getWidth()/2, getHeight()/2);
        
        imagen = new GreenfootImage("Elementos/flechaRojaIzquierda.png"); //Volver a crear, que si no se queda como referencia y se modifican las dos.
        flechaIzquierda = Boton.creaBotonImagen(this, imagen, "Elementos/flechaRojaIzquierda.png", imagen.getWidth()/2, getHeight()/2);
        
        imagen = new GreenfootImage("Naves/Aliadas/NaveA"+ numNaveActual + "Grande.png");
        naveActual = Boton.creaBotonImagen(this, imagen, "Naves/Aliadas/NaveA"+ numNaveActual + "Grande.png", getWidth()/2, getHeight()/2);
        cambioCompleto = true; //Inicializar verdadero, ya que se permite el cambio de la nave actual.
    }
    
    public void act(){
        musicaSeleccion.playLoop();
        seleccionNave();
        cambiarNave(); //Verificará si se cambió la nave.
        volverMenu(); //Volver al menú si se presiona el respectivo botón.
    }
    /** Método que mostrará la nave actual.
       boolean cambio indicará si se cambió de nave.
     * boolean ladoCambio verificará hacia dónde se hizo el cambio de la nave.*/
    private void mostrarNave(boolean ladoCambio){ //boolean ladoCambio verificará hacia dónde se hizo el cambio de la nave.
        int cantidadMovimiento = 5; //Número de pixeles que se desplazarán las naves al moverse.
        if(cambioCompleto){ //Si se cambió de nave lo hace. Para que no lo haga todo el tiempo.
            cambioCompleto = false; //Aquí empieza el cambio
            xActual = getWidth()/2; //La nave actualmente mostrada está localizada en la mitad de la pantalla.
            imagen = new GreenfootImage("Naves/Aliadas/NaveA"+ numNaveNueva+ "Grande.png");
            if(ladoCambio) //true -> Izquierda. false -> derecha.
                xNueva = imagen.getWidth()/2; //Empezar de la izquierda, ya que se moverá a la derecha.
            else
                xNueva = getWidth() - imagen.getWidth()/2; //Empezar de la derecha, ya que se moverá a la izquierda.
            naveNueva = Boton.creaBotonImagen(this, imagen, "Naves/Aliadas/NaveA"+ numNaveNueva + "Grande.png", xNueva, getHeight()/2);
        }
        if(!cambioCompleto){
            if(xActual < getWidth() - naveActual.getImage().getWidth()/2 && ladoCambio){ //Saldrá hacia la derecha.
                xActual += cantidadMovimiento;
                naveActual.setLocation(xActual, getHeight()/2);
            }
            if(xActual > naveActual.getImage().getWidth()/2 && !ladoCambio){ //Saldrá hacia la izquierda.
                xActual -= cantidadMovimiento;
                naveActual.setLocation(xActual, getHeight()/2);
            }
            if(xNueva != getWidth()/2){
                if(xNueva < getWidth()/2)
                    xNueva += cantidadMovimiento;
                else
                    xNueva -= cantidadMovimiento;
                naveNueva.setLocation(xNueva, getHeight()/2);
            }
            if(xActual >= getWidth() - naveActual.getImage().getWidth()/2 || xActual <= naveActual.getImage().getWidth()/2
                && xNueva >= getWidth()/2 - 5 && xNueva <= getWidth()/2 + 5){
                    /* Si la nave actual sobrepasa los límites y la nueva está dentro de los límites
                       establecidos que están conforme al centro de la pantalla. Esto porque dependerá de la cantidad de movimiento,
                       así que si aumenta más, podría fallar.*/
                removeObject(naveActual); //Eliminar la nave actual (la que se estaba yendo).
                naveActual = naveNueva; //Ahora la nave actual es la nueva.
                naveNueva = null;
                numNaveActual = numNaveNueva;
                cambioCompleto = true; //Se terminaron los cambios y muestra de naves.
            }
        }
    }
    /** Método que irá cambiando la nave que se muestra.*/
    private void cambiarNave(){
        /*if(boton = derecha) numNave ++;
           if(boton == izquierda) numNave --;
           if(numNave > navesMax) numNave = 1; //Se reinicia el conteo.
           if(numNave < 1) numNave = navesMax; //Pasar a la máxima.*/
        //cambioCompleto se verifica para ver si ya se puede cambiar de nave.
        if(isFlechaDerecha() && cambioCompleto){
            numNaveNueva = numNaveActual + 1;
            if(numNaveNueva > numNaveFinal) //Se pasó de la nave máxima.
                numNaveNueva = numNaveInicial;
            mostrarLado = true; //La nave sale desde la izquierda y avanza a la derecha.
        }
        if(isFlechaIzquierda() && cambioCompleto){
            numNaveNueva = numNaveActual - 1;
            if(numNaveNueva < numNaveInicial) //Se pasó de la nave máxima.
                numNaveNueva = numNaveFinal; //Mostrar la máxima.
            mostrarLado = false; //La nave sale desde la derecha.
        }
        if(numNaveActual != numNaveNueva)
            mostrarNave(mostrarLado); //Si se puso una nueva nave, mostrarla y borrar la otra.
            //true -> Izquierda. false -> derecha.
    }
    /** Método que verifica si se presionó la flecha derecha con el mouse o teclado.*/
    private boolean isFlechaDerecha(){
        if(Greenfoot.mouseClicked(flechaDerecha) || Greenfoot.isKeyDown("right"))
            return true;
        return false;
    }
    /** Método que verifica si se presionó la flecha izquierda.*/
    private boolean isFlechaIzquierda(){
        if(Greenfoot.mouseClicked(flechaIzquierda) || Greenfoot.isKeyDown("left"))
            return true;
        return false;
    }
    /** Método que verá que nave elegiste. Solo se podrá elegir la nave si está en medio de la pantalla.
        No exactamente en medio porque esto va a variar dependiendo de la cantidad de movimiento,
            pero dentro de un rango dado.*/
    private void seleccionNave(){
        /* Si se seleccionó la nave y está en medio de la pantalla.*/
        if((Greenfoot.mouseClicked(naveActual) || Greenfoot.isKeyDown("enter"))
                && naveActual.getX() >= getWidth()/2 - 5 && naveActual.getX() <= getWidth()/2 + 5){
            NaveAliada.setDiseñoNaveAliada(numNaveActual);
            NaveAliada.setTipoDisparo(1); //El tipo de disparo base.
            musicaSeleccion.stop(); //Detener la música , al seleccionar la nave.
            Greenfoot.setWorld(new Intro(1)); //Mostrar la instroducción del primer nivel.
        }
    }
    /** Método que devuelve la canción que se reproduce para detenerla.*/
    public static GreenfootSound getMusica(){
        return musicaSeleccion; 
    }
}
