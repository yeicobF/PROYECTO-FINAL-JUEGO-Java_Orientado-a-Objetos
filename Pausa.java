import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Clase que manejará el menú de pausa y cuando se le da click.
 *  Cuado se pause el juego se mostrarán las opciones:
 *  1 - Reanudar.
 *  2 - Salir.
 *      2.1 - Guardar cambios
 *          2.1.1 - Sí
 *              2.1.1.1 - Ingresa tu nombre
 *          2.1.2 - No
 *              2.1.2.1 - ¿Estás seguro?
 *                  2.1.2.1.1 - Sí
 *                  2.1.2.1.2 - No
 *                      - Ir al punto 2.1.1.1, ya que sí se guardarán cambios.
 *
 * @author (Team Naves)
 * @version (Martes, 9 de junio de 2020)
 */

 public class Pausa extends World {
     private World mundoAntesDePausa;
     private static Boton pausa; //Se creará y si lo tocamos se para el juego.
     private Boton reanudar; //Botón que reanudará la partida.
     private Boton salir; //Botón que saldrá del juego.
     private Boton reiniciar;
     private Etiqueta texto; //Para crear cuadro de texto.
     /*Crear menú de pausa con los botones de reanudar y salir.*/
     public Pausa(World mundoAntesDePausa, GreenfootImage fondoPausa){
         super(1000, 600, 1); //Dimensiones del cuadro de pausa. Aquí se mostrarán las opciones.
         setBackground(fondoPausa); //Que el fondo sea el fondo del nivel actual.
         this.mundoAntesDePausa = mundoAntesDePausa;
         //public Etiqueta(int tamañoFuente, Color colorFuente, Color colorFondo, Color bordeFuente)
         texto = new Etiqueta(50, Color.WHITE, Color.BLACK, Color.YELLOW);
         //Crear texto que indique la Pausa
         getBackground().drawImage(texto.crearCuadroTexto("Pausa"), getWidth()/2 - texto.getXCentrada(), 10);
         System.out.println(" - MITAD FONDO: 500, X CALCULADA DEBERÍA SER: 497, ES: "+ (getWidth()/2 - texto.getLargoTexto()/2*30));
         //public static Boton creaBotonImagen(World mundoActual, GreenfootImage imagen, int x, int y)
         //Se restan 30 por el tamaño de la fuente
         reanudar = Boton.creaBoton(this, "Reanudar", getWidth()/2, getHeight()/2 - 50, Color.WHITE, Color.GRAY, null, 50);
         reiniciar = Boton.creaBoton(this, "Reiniciar nivel", getWidth()/2, getHeight()/2 + 50, Color.WHITE, Color.GRAY, null, 50);
         salir = Boton.creaBoton(this, "Salir", getWidth()/2, getHeight()/2 + 150, Color.WHITE, Color.GRAY, null, 50);
     }
     
     
     public void act(){
         if(isReanudar()){
            //texto.getImagen().clear(); //Borrar cuadro de texto. No funciona porque el que lo dibuja es el drawImage().
            Greenfoot.setWorld(mundoAntesDePausa);
        }
        if(isSalir())//Aquí se preguntará si se quieren guardar cambios antes de salir o no. Por ahora sólo saldremos a portada.
            Greenfoot.setWorld(new Portada());
        if(isReiniciar())
            Greenfoot.setWorld(new Niveles(Niveles.getNivelActual()));
     }
     
     /*Método que regresa true si el botón de pausa ha sido tocado con el mouse
        o activado con las teclas "p" o "esc".*/
     public static boolean isPausa(){
         if(Greenfoot.mouseClicked(pausa) || Greenfoot.isKeyDown("p") || Greenfoot.isKeyDown("escape"))
            return true;
         return false;
     }
     private boolean isReanudar(){
         if(Greenfoot.mouseClicked(reanudar) || Greenfoot.isKeyDown("r"))// || si presionamos con las flechitas.
            return true;
         return false;
     }
     private boolean isSalir(){
         if(Greenfoot.mouseClicked(salir))// || si presionamos con las flechitas.
            return true;
         return false;
     }
     private boolean isReiniciar(){
         if(Greenfoot.mouseClicked(reiniciar))// || si presionamos con las flechitas.
            return true;
         return false;
     }
     /* Crear botón de pausa*/
     public static void creaBotonPausa(World mundoActual, int x, int y){
         GreenfootImage imagen = new GreenfootImage("Elementos/Pausa.png");
         pausa = Boton.creaBotonImagen(mundoActual, imagen, "Elementos/Pausa.png", 
                                        x - imagen.getWidth()/2, y + imagen.getHeight()/2); //Establecer el botón de pausa.
     }
     
 }
