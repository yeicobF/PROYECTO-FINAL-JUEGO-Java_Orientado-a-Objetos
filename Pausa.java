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
     public Pausa(World mundoAntesDePausa){
         super(300, 200, 1); //Dimensiones del cuadro de pausa. Aquí se mostrarán las opciones.
         this.mundoAntesDePausa = mundoAntesDePausa;
     }
     /*Método que regresa true si el botón de pausa ha sido tocado con el mouse
        o activado con las teclas "p" o "esc".*/
     public static boolean isPausa(){
         if(Greenfoot.mouseClicked(pausa) || Greenfoot.isKeyDown("p") || Greenfoot.isKeyDown("escape"))
            return true;
         return false;
     }
     /* Crear botón de pausa*/
     public static void creaBotonPausa(World mundoActual, int x, int y){
         GreenfootImage imagen = new GreenfootImage("Elementos/Pausa.png");
         imagen.scale(imagen.getWidth()/20, imagen.getHeight()/20);
         pausa = Boton.creaBotonImagen(mundoActual, imagen, x - imagen.getWidth()/2, y + imagen.getHeight()/2); //Establecer el botón de pausa.
     }
 }
