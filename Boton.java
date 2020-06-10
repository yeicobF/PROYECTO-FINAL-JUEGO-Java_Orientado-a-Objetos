import greenfoot.*;
/**
 * Clase que funciona para crear botones en el escenario. Estos serán interactivos porque son actores.
 *
 * @author (Team Naves)
 * @version (Miércoles, 10 de junio de 2020)
 */

public class Boton extends Actor
{
    // instance variables - replace the example below with your own
    GreenfootImage imagenOriginal; //Guardará la imagen original para no reescalarla y que no sepierda la calidad.
    GreenfootImage imagenGrande; //Guardará la imagen reescalada para cuando pase el mouse por encima.
    GreenfootImage imagenAuxiliar; //Cuando se use imagenGrande y se modifique a partir de la original, se necesita auxiliar para que la original no cambie.
    String texto; //El texto del botón
    private Etiqueta e;
    /*Constructor para cuando el botón creado es una imagen.*/
    private Boton(GreenfootImage imagen, String nombreImagen){
        /*Aquí el proceso lo hice diferente. En lugar de que la imagen original sea la que recibimos
           y la grande crezca en proporcion de esta, la imagen original será la que recibimos
           pero más chica, y la grande será la que recibimos. Así no se verá tanto la pérdida de
           calidad al hacer la imagen más grande.*/
        imagenGrande = imagen;
        //Como se asignan por separado, no estarán conectadas.
        imagenOriginal = new GreenfootImage(nombreImagen);
        // //Se modifica la imagen grande. No requiere auxiliar.
        // /*El proceso de modificación lo hice manual, pero tengo que hacerlo automático. Esto es
            // para el botón de pausa específicamente. Necesito cambiar el tamaño del botón de pausa
            // a 1024/20.*/
        // Imagen.modificarEscalaImagen(imagenGrande, 20, 1);
        Imagen.modificarEscalaImagen(imagenOriginal, 4, 3);
    }
    //public Etiqueta(int tamañoFuente, Color colorFuente, Color colorFondo, Color bordeFuente)
    private Boton(String texto, int tamañoFuente, Color colorFuente, Color colorFondo, Color bordeFuente){
        this.texto = texto;
        e = new Etiqueta(tamañoFuente, colorFuente, colorFondo, bordeFuente);
        imagenOriginal = e.crearCuadroTexto(texto);
        
        setImage(imagenOriginal); //Crear el cuadro de texto y hacerlo como la imagen de aquí.
        //No necesitamos el método crearBoton. Aquí ya se crea con la imagen del texto necesario.
        //imagenAuxiliar = getImage();
        //Cambiar el tamaño del fondo de la imagen que cambiará al pasa el mouse por encima. Esto si tenía fondo anteriormente.
        if(colorFondo != null) //GreenfootImage(texto, tamañoFuente, colorFuente, colorFondo, bordeFuente)
            imagenAuxiliar = imagenGrande =  new GreenfootImage(texto, tamañoFuente, Color.BLACK, Color.WHITE, bordeFuente);
        else
            /* Tuve que hacer esto de asignar la imagenAuxiliar a partir de crear tel texto, ya que si no
            la imagenAuxiliar tiene una especie de conexión como con los apuntadores y cada que se modifica
            la imagen grande o auxiliar también se modifica la imagenOriginal.
            De esta forma ya trabajan de forma independiente.*/
            imagenAuxiliar = e.crearCuadroTexto(texto);
        /*En la instrucción de abajo hay un problema, se modifica la imagen pero se pone esa en lugar de la original.*/
        imagenGrande = Imagen.modificarEscalaImagen(imagenAuxiliar, 4, 5);
        //setImage(imagenOriginal);
    }
    
    public void act(){
        if (Greenfoot.mouseMoved(this)) //Si el mouse se pasa por encima.
            setImage(imagenGrande);    
            // setImage(Imagen.modificarEscalaImagen(imagenAuxiliar, 4, 5));
        if (Greenfoot.mouseMoved(null) && !Greenfoot.mouseMoved(this)) //Si el mouse no se pasa por encima.
            setImage(imagenOriginal);
    }
    
    public static Boton creaBoton(World mundoActual, String texto, int x, int y,
                    Color colorFuente, Color colorFondo, Color bordeFuente, int tamañoFuente)
    {
        Boton boton = new Boton(texto, tamañoFuente, colorFuente, colorFondo, bordeFuente);
        /*El botón se creará centrado tomando en cuenta las coordenadas proporcionadas,
            es decir, x y y serán el centro del botón, no el inicio.*/
        mundoActual.addObject(boton, x - boton.e.getLargoTexto()/2, y);
        //System.out.println(boton.e.getTexto() + boton.e.getLargoTexto() +"\n");
        //System.out.println(x - boton.e.getLargoTexto()/2 + "\n");
        return boton;
    }
    /*Método que crea un botón a partir de una imagen dada, sus dimensiones y sus coordenadas.*/
    public static Boton creaBotonImagen(World mundoActual, GreenfootImage imagen, String nombreImagen, int x, int y){
        Boton boton = new Boton(imagen, nombreImagen); //Recibe el botón con su imagen.
        mundoActual.addObject(boton, x, y); //Agrega el botón.
        return boton;
    }
    // //Crear un botón con los paarámetros que recibe. Regresa el botón para poder interactuar con él.
    // public Actor creaBoton(String s, int tamaño, int x, int y)
    // {
        // getWorld().addObject();
        // return boton;
    // }
}
