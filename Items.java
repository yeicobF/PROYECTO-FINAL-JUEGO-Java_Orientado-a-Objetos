import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 *  -> Esta descripción es hasta la fecha de creación. Puede ser modificada en un futuro. *
 * En esta clase se manejarán los items disponibles. Cada item tendrá una función que dará una mejora a nuestra nave.
 * Estas mejoras irán apareciendo en el mapa dependiendo del tiempo. También se tomará en cuenta el nivel y si agregamos dificultad, 
 *  también habrá que tomarla en cuenta para el ratio de aparición de los items. Hasta ahora se piensa generarlos con un número random
 *  y dependiendo del número saldrá el item que nada más cambiará la imagen. La clase NaveAliada (nosotros) es la que tocará los items
 *  y dependiendo del random que haya generado el item (este número se mandará a NaveAliada) dará estos cambios.
 *  Lista de items (hasta ahora):
 *      - 1. Subir vidas o salud (habrá que clarificar si aumentar el número de vidas actual o los HP (Health Points o Puntos de Salud)
 *              de la nave).
 *      - 2. Escudo o blindaje.
 *      - 3. Destrucción de un enemigo al tocarlo. Este como desventaja para que no sea muy desbalanceado o fácil, 
 *              disminuirá la velocidad del jugador.
 *      - 4. Cambiar el tipo de disparo. De este podrían haber varios, pero aún no sabemos.
 *      - 5. Aparecer una nave aliada.
 *      - 6. Obtener más puntos o dinero. Esta aún no estamos seguros, pero sería buena por si implementamos un sistema
 *              de mejora de nuestra nave que se manejara con dinero o puntos. También podría ser por si agregamos un modo
 *              infinito en el que los puntos sean los que se tomen en consideración principalmente. Así implementaríamos
 *              los records.
 *      - 7. Realentizar a los enemigos.
 *      - 8. Nuclear o algo así. Como en Call Of Duty (COD) Zombies con el Kaboom (la bomba atómica) la cual destruiría a 
 *              todas las naves del escenario.
 *      - 9. Insta Kill. Igual como en COD, esta podría destruir a los enemigos de un disparo en lugar de varios, ya que
 *              los disparos quitarán puntos de salud (HP o PS).
 * @author (Team Naves) 
 * @version (Domingo, 10 de mayo - Lunes, 11 de mayo de 2020)
 */
public class Items extends Actor
{
    /**
     * Act - do whatever the Items wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private long inicioDisparoMillis=0;//Variable para comprobar hace cuánto se creó el último item.
    private long tiempoActivo=0; //Variable que definirá el tiempo de vida del item y cuánto lleva activo.
    private boolean itemActivo=false;//Variable que verá si ya existe un item o no.
    int tipoItem;//Esta variable definirá el tipo del item y su sprite.
    public Items(int tipoItem){
        itemActivo = true;
        if(tipoItem == 1){ // VIDA. Esta no requiere tiempoActivo
            setImage("Item1Vida.png");
        }
        
    }
    public void act() 
    {
        
        
    }    
}
