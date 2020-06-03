import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * - El nombre o la implementación podría cambiar en un futuro. -
 * En esta clase estarán métodos más generales para que los pueda implementar cualquier clase.
 *  Por ejemplo: Reescalar imagen o la destruccion de objetos al chocar.
 *  -> La clase es estática para que no haya necesidad de instanciarla en orden de trabajar con sus métodos.
 *      Aunque de lo largo que es el nombre de la clase, tal vez sí conviene más instanciarlo para ahorrar caracteres.
 *      - Así que al final mejor no se hizo estática XD.
 * 
 * @author (Team Naves) 
 * @version (Martes, 2 de junio - Miércoles 3 de junio de 2020)
 */
public class MetodosGenerales  
{
    // instance variables - replace the example below with your own
    private int x;
    /**
     * Constructor for objects of class MetodosGenerales
     */
    public MetodosGenerales()
    {
    }

    /*Método para reescalar una imagen de acuerdo a los parámetros y regresarla modificada.
     * - Recibe la imagen del tipo GreenfootImage, que es como Greenfoot las maneja.
        Lo que hace es dividir el ancho de la imagen recibida entre "division" y luego multiplicarlo por "multiplicacion",
            así obtendremos una imagen de esa proporcion.
        Ancho Imagen final = Ancho/divisor * multiplicación
        Alto Imagen final = Alto/divisor * multiplicación*/
    public GreenfootImage modificarEscalaImagen(GreenfootImage imagen, int divisor, int multiplicacion){
        imagen.scale(imagen.getWidth()/divisor*multiplicacion, imagen.getHeight()/divisor*multiplicacion);
        return imagen;
    }
    /*Método que revisa si ocurrió un choque de un objeto con el que se manda de parámetro.
        Simplemente regresa verdadero o falso si choca o no.*/
    public boolean choqueActores(Actor objeto){
        return objeto != null;  //Aquí revisa que el objeto exista. Si el objeto existe entonces sí ocurrió un choque.
    }
    /*Método que elimina un objeto si chocó con otro. Hay que mandarle los objetos que chocaron y su mundo.
     *  No necesita ser booleano porque no importa que sea true o false, sino, que se elimine o no dependiendo de la salud.
        El mundo actual es de tipo "World" porque es la mayor generalización. De ahí siguen los niveles que son class diferentes.*/
    public int eliminarObjetoChoque(Actor objetoChoque, Actor objetoRaiz, World mundoActual, 
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
        if(choqueActores(objetoChoque) && Items.getTipoItem() != 2 || objetoChoque instanceof Disparo){ //Para destruir, la salud tiene que ser menor o igual a 0. No destruir en el choque inmediat0.
            // if(objetoChoque instanceof NaveEnemiga)
                // objetoChoque.eliminaCuadroPS(NaveEnemiga.getMostrarInfo(), mundoActual); 
            mundoActual.removeObject(objetoChoque);//Se elimina el objeto con el que se chocó. Este siempre se elimina. Por ejemplo el disparo.
            // Items.setImagenItemFalso();//Después de que entra cuando la imagen del item está activa, se indica que se desactivó.
            puntosSalud -= daño; //Restar el daño al objeto
            NaveAliada.setPuntos(puntosNave);//Se le suman los puntos al jugador
            if(puntosSalud <=0){ //Si la salud del objeto actual es 0 o menos, entonces ahora sí eliminar el objeto actual
                NaveAliada.setPuntos(puntosNave);//Como se destruyo el objeto se nos volverán a sumar los puntos.
                mundoActual.removeObject(objetoRaiz);//Se elimina el objeto que chocó. El objeto que llama el método.
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
    
   /*MÉTODO PARA CALCULAR UN RANDOM EN UN RANGO DE NÚMEROS*/
    public int getRandomNumber(int start,int end){
       int normal = Greenfoot.getRandomNumber(end-start+1);
       return normal+start;
    }
}
