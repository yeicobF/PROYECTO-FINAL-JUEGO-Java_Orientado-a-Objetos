import greenfoot.GreenfootImage;
import greenfoot.Actor;
import greenfoot.Color;
import greenfoot.Greenfoot;
import greenfoot.World;
/**
 * Clase que funciona para crear botones en el escenario. Estos serán interactivos porque son actores.
 *
 * @author (Team Naves)
 * @version (Miércoles, 10 de junio de 2020)
 */

public class Boton extends Actor
{
    private GreenfootImage imagenChica; //Guardará la imagen original para no reescalarla y que no sepierda la calidad.
    private GreenfootImage imagenGrande; //Guardará la imagen reescalada para cuando pase el mouse por encima.
    private GreenfootImage imagenAuxiliar; //Cuando se use imagenGrande y se modifique a partir de la original, se necesita auxiliar para que la original no cambie.
    private String texto; //El texto del botón
    private Etiqueta e;
    /** Constructor para cuando el botón creado es una imagen.*/
    private Boton(GreenfootImage imagen, String nombreImagen){
        /*Aquí el proceso lo hice diferente. En lugar de que la imagen original sea la que recibimos
           y la grande crezca en proporcion de esta, la imagen original será la que recibimos
           pero más chica, y la grande será la que recibimos. Así no se verá tanto la pérdida de
           calidad al hacer la imagen más grande.*/
        imagenGrande = imagen;
        //Como se asignan por separado, no estarán conectadas.
        imagenChica = new GreenfootImage(nombreImagen);
        // //Se modifica la imagen grande. No requiere auxiliar.
        Imagen.modificarEscalaImagen(imagenChica, 4, 3);
    }
    /** Constructor para crear un botón con texto.*/
    private Boton(String texto, int tamañoFuente, Color colorFuente, Color colorFondo, Color bordeFuente){
        this.texto = texto;
        //public Etiqueta(int tamañoFuente, Color colorFuente, Color colorFondo, Color bordeFuente)
        e = new Etiqueta(tamañoFuente, colorFuente, colorFondo, bordeFuente);
        imagenChica = e.crearCuadroTexto(texto);
        setImage(imagenChica); //Crear el cuadro de texto y hacerlo como la imagen de aquí.
        //No necesitamos el método crearBoton. Aquí ya se crea con la imagen del texto necesario.
        //Cambiar el tamaño del fondo de la imagen que cambiará al pasa el mouse por encima. Esto si tenía fondo anteriormente.
        if(colorFondo != null) //GreenfootImage(texto, tamañoFuente, colorFuente, colorFondo, bordeFuente)
            imagenAuxiliar = imagenGrande =  new GreenfootImage(texto, tamañoFuente, Color.BLACK, Color.WHITE, bordeFuente);
        else
            imagenAuxiliar = e.crearCuadroTexto(texto);
            /* Tuve que hacer esto de asignar la imagenAuxiliar a partir de crear tel texto, ya que si no
            la imagenAuxiliar tiene una especie de conexión como con los apuntadores y cada que se modifica
            la imagen grande o auxiliar también se modifica la imagenOriginal.
            De esta forma ya trabajan de forma independiente.*/
        imagenGrande = Imagen.modificarEscalaImagen(imagenAuxiliar, 10, 10);
    }
    
    public void act(){
        //Si el mouse se pasa por encima se pone la imagen grande.
        if (Greenfoot.mouseMoved(this)) 
            setImage(imagenGrande);
        //Si el mouse no se pasa por encima se pone la imagen chica.
        if (Greenfoot.mouseMoved(null) && !Greenfoot.mouseMoved(this))
            setImage(imagenChica);
    }
    /** Método que crea un botón interacruable.*/
    public static Boton creaBoton(World mundoActual, String texto, int x, int y,
                    Color colorFuente, Color colorFondo, Color bordeFuente, int tamañoFuente)
    {
        Boton boton = new Boton(texto, tamañoFuente, colorFuente, colorFondo, bordeFuente);
        /*El botón se creará centrado tomando en cuenta las coordenadas proporcionadas,
            es decir, x y y serán el centro del botón, no el inicio.*/
        mundoActual.addObject(boton, x - boton.e.getLargoTexto()/2, y);
        return boton;
    }
    /** Método que crea un botón con un texto de sombra debajo.*/
    public static Boton creaBotonSombra(World mundoActual, String textoBoton, Etiqueta texto, int x, int y,
                    Color colorFuente, Color colorFondo, Color bordeFuente, int tamañoFuente, int divisorLargo){
        Boton boton = new Boton(textoBoton, tamañoFuente, colorFuente, colorFondo, bordeFuente);
        mundoActual.addObject(boton, x - boton.e.getLargoTexto()/2, y);
        //Sombra del botón
        mundoActual.getBackground().drawImage(texto.crearCuadroTexto(textoBoton), 
                        x - texto.getXSombra(divisorLargo), y - tamañoFuente/2 + tamañoFuente/25);
        return boton;
    }
    /** Método que crea un botón a partir de una imagen dada, sus dimensiones y sus coordenadas.*/
    public static Boton creaBotonImagen(World mundoActual, GreenfootImage imagen, String nombreImagen, int x, int y){
        Boton boton = new Boton(imagen, nombreImagen); //Recibe el botón con su imagen.
        mundoActual.addObject(boton, x, y); //Agrega el botón.
        return boton;
    }
}
