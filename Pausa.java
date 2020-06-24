import greenfoot.World;
import greenfoot.GreenfootImage;
import greenfoot.Actor;
import greenfoot.Color;
import greenfoot.Greenfoot;
/**
 * Clase que manejará el menú de pausa y cuando se le da click.
 *  Cuado se pause el juego se mostrarán las opciones:
 *  1 - Reanudar.
 *  2 - Salir.
 *
 * @author (Team Naves)
 * @version (Miércoles, 10 de junio de 2020)
 */

 public class Pausa extends World
 {
     private static boolean reinicio; //Bandera que indica que se reinició el nivel.
     private static Boton pausa; //Se creará y si lo tocamos se para el juego.
     private World mundoAntesDePausa;
     private GreenfootImage fondoPausa; //Para cuando se reanude el juego se borre el texto de "Pausa".
     private Actor pausaTexto; //Para crear el cuadro de texto y luego borrarlo.
     private Boton reanudar; //Botón que reanudará la partida.
     private Boton salir; //Botón que saldrá del juego.
     private Boton reiniciar;
     private Etiqueta texto; //Para crear cuadro de texto.
     /** Crear menú de pausa con los botones de reanudar y salir.*/
     public Pausa(World mundoAntesDePausa, GreenfootImage fondoPausa){
         super(1000, 600, 1); //Dimensiones del cuadro de pausa. Aquí se mostrarán las opciones.
         setBackground(fondoPausa); //Que el fondo sea el fondo del nivel actual.
         this.mundoAntesDePausa = mundoAntesDePausa;
         this.fondoPausa = fondoPausa;
         reinicio = false;
         //Crear botón con texto que indique la Pausa
         pausaTexto = Boton.creaBoton(this, "Pausa", getWidth()/2, getHeight()/8, Color.WHITE, Color.BLACK, Color.YELLOW, 50);
         reanudar = Boton.creaBoton(this, "Reanudar", getWidth()/2, getHeight()/2 - 50, Color.WHITE, Color.GRAY, null, 50);
         reiniciar = Boton.creaBoton(this, "Reiniciar nivel", getWidth()/2, getHeight()/2 + 50, Color.WHITE, Color.GRAY, null, 50);
         salir = Boton.creaBoton(this, "Salir", getWidth()/2, getHeight()/2 + 150, Color.WHITE, Color.GRAY, null, 50);
     }
     public void act(){
        if(isReanudar())
        Greenfoot.setWorld(mundoAntesDePausa);
        if(isSalir())//Aquí se preguntará si se quieren guardar cambios antes de salir o no. Por ahora sólo saldremos a portada.
            Greenfoot.setWorld(new Menu());
        if(isReiniciar())
            Greenfoot.setWorld(new Niveles(Niveles.getNivelActual()));
     }
     /** Método que regresa true si el botón de pausa ha sido tocado con el mouse
        o activado con las teclas "p" o "esc".*/
     public static boolean isPausa(){
         return Greenfoot.mouseClicked(pausa) || Greenfoot.isKeyDown("p") || Greenfoot.isKeyDown("escape");
     }
     /** Método que verifica si se reanudó el nivel.*/
     private boolean isReanudar(){
         return Greenfoot.mouseClicked(reanudar) || Greenfoot.isKeyDown("r");// || si presionamos con las flechitas.
     }
     /** Método que verifica si se salió del nivel.*/
     private boolean isSalir(){
         return Greenfoot.mouseClicked(salir) || Greenfoot.isKeyDown("s");
     }
     /** Método que verifica si se reinició el nivel.*/
     public boolean isReiniciar(){
         if(Greenfoot.mouseClicked(reiniciar))// || si presionamos con las flechitas.
            reinicio = true;
         return reinicio;
     }
     /** Método que verifica si el juego fue reiniciado para establecer las vidas a las base.*/
     public static boolean isJuegoReinicio(){
         return reinicio;
     }
     /** Método que hace la variable de reinicio falsa.*/
     public static void setReinicioFalse(){
         reinicio = false;
     }
     /** Crear botón de pausa*/
     public static void creaBotonPausa(World mundoActual, int x, int y){
         GreenfootImage imagen = new GreenfootImage("Elementos/Pausa.png");
         pausa = Boton.creaBotonImagen(mundoActual, imagen, "Elementos/Pausa.png", 
                                        x - imagen.getWidth()/2, y + imagen.getHeight()/2); //Establecer el botón de pausa.
     }
     
 }
