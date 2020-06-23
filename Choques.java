//Del paquete de grenfoot sólo necesito Actor y World
import greenfoot.Actor;
import greenfoot.World;

/**
 * Clase que manejará los métodos que revisan si objetos chocan.
 *  Todos estos métodos serán estáticos, por lo que no será necesario instanciar la clase.
 *
 * @author (Jacob)
 * @version (Jueves, 4 de junio de 2020)
 */
public abstract class Choques
{
    // instance variables - replace the example below with your own
    private int x;

    /**
     * Constructor for objects of class Choques
     */
    public Choques(){}

    /*Método que revisa si ocurrió un choque de un objeto con el que se manda de parámetro.
        Simplemente regresa verdadero o falso si choca o no.*/
    //Método que verifica si el objeto que llama al método y el parámetro  chocaron.
    private static boolean choqueActores(Actor objeto){
        return objeto != null;  //Aquí revisa que el objeto exista. Si el objeto existe entonces sí ocurrió un choque.
    }
    /*Método que elimina un objeto si chocó con otro. Hay que mandarle los objetos que chocaron y su mundo.
     *  No necesita ser booleano porque no importa que sea true o false, sino, que se elimine o no dependiendo de la salud.
        El mundo actual es de tipo "World" porque es la mayor generalización. De ahí siguen los niveles que son class diferentes.*/
    public static int eliminarObjetoChoque(Actor objetoChoque, Actor objetoRaiz, World mundoActual,
                                                    int puntosSalud, int daño, int puntosNave){
        /*if(choqueActores(objetoChoque) && (objetoChoque instanceof Items || (Items.getTipoItem() != 2 && Items.getTipoItem() != 0)))
           -> Esta condición quiere decir que para que se elimine el objeto deberá haber un choque de objetos,
                además, este objeto deberá ser un item o en el momento que se toque no esté el efecto del escudo (tipo 2). Así cuando toque un item se eliminará y los objetos también mientras no
                tengamos el escudo. También el que puede tener efectos es el disparo, así que hay que considerarlo.*/
        /* - La condición cambió a: if(choqueActores(objetoChoque)  && !Items.isTocoItem())
                Esto significa que para eliminar a un objeto, no habrá que haber tocado un item.
                Por ahora esto funciona para el escudo, pero cuando se implementen otros items que no
                implementen la inmunidad podría traer problemas.
            Al tener el escudo tendremos inmunidad. Los disparos siempre pueden destruir.*/
        if(Items.getTipoItem() == 5) //Más puntos
            puntosNave *= 2; //Duplicar los puntos que obtendremos.
        if(choqueActores(objetoChoque) && Items.getTipoItem() != 2 || objetoChoque instanceof Disparo){ //Para destruir, la salud tiene que ser menor o igual a 0. No destruir en el choque inmediat0.
            // if(objetoChoque instanceof NaveEnemiga)
                // objetoChoque.eliminaCuadroPS(NaveEnemiga.getMostrarInfo(), mundoActual);
            mundoActual.removeObject(objetoChoque);//Se elimina el objeto con el que se chocó. Este siempre se elimina. Por ejemplo el disparo.
            // Items.setImagenItemFalso();//Después de que entra cuando la imagen del item está activa, se indica que se desactivó.
            puntosSalud -= daño; //Restar el daño al objeto
            //Sumarle los puntos que indicamos. Si nosotros chocamos, se nos sumará un número negativo.
            NaveAliada.sumaPuntos(puntosNave);//Se le suman los puntos al jugador
            /*Si los puntos de salud de lo impactado <= 0, se nos dará el doble de puntos,
                pero hay que revisar que los que perdieron toda la salud no
                somos nosotros, porque si no nos quitará el doble de puntos
                de los que indicamos que nos debería de quitar al morir.*/
            if(puntosSalud <=0){ //Si la salud del objeto actual es 0 o menos, entonces ahora sí eliminar el objeto actual
                //Antes de destruir el objeto, tomar sus coordenadas y aparecer la explosión.
                mundoActual.addObject(new Explosion(objetoRaiz.getImage()), objetoRaiz.getX(), objetoRaiz.getY()); 
                mundoActual.removeObject(objetoRaiz);//Se elimina el objeto que chocó. El objeto que llama el método.
                if(!(objetoRaiz instanceof NaveAliada)) //Si el objeto no somos nosotros, sumarnos los puntos.
                    NaveAliada.sumaPuntos(puntosNave);//Como se destruyo el objeto se nos volverán a sumar los puntos.
            }
                //return true;//Regresar verdadero si se hizo el procedimiento anterior. Es decir, se eliminó el objeto.
            // if(objetoRaiz instanceof Roca) //Tal vez podría ponerlo en la misma roca, pero como ya no existiría después del return, no haría efecto.
                // Espacio.setNumRocasActual(Espacio.getNumRocasActual() - 1);//Llamamos al setter estático (no necesita instanciarse).
                    //Esto mejor lo punto en su propia clase
            if(objetoChoque instanceof Items && !Items.isTocoItem()) //Si no ha tocado el item, ahora sí y habrá que indicarlo
                Items.setTocoItemTrue();//Se tocó el item.
        }
        if(NaveAliada.getPuntos() < 0) //No queremos que salgan puntos negativos.
            NaveAliada.setPuntos(0);//Sumar los números que están por debajo para dejarlos en 0.
        return puntosSalud;//Regresa los puntos de salud para que se siga evaluando el método.
    }//En el método de destruir nave habrá que bajar vidas si se eliminó.

}
