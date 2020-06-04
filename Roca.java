import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Clase que maneja todo lo relacionado a los meteoros.
 *
 * @author (Team Naves)
 * @version (Viernes, 29 de mayo de 2020)
 */
public class Roca extends Actor
{
    //Roca[] r; <- Era para hacer un arreglo de rocas
    World w;
    MetodosGenerales m = new MetodosGenerales(); //Instanciar clase en donde se encuentra el método de eliminación al chocar objetos.
    int x, y, i;
   public Roca(int tipoMeteoro){//CONSTRUCTOR
       int random;
       /*El código de abajo servirá para reescalar la imagen, ya que estaba muy grande*/
       setImage("Objetos/Meteoro"+ tipoMeteoro+ ".png");
       //Aquí modificamos el tamaño de los meteoros a una escala random para que tengan tamaños diferentes.
       Imagen.modificarEscalaImagen(getImage(), Aleatorio.getNumeroAleatorio(2,4), 1);
       // if(tipoMeteoro==0){
            // setImage("pruebaMeteoro1.png");
            // GreenfootImage image = getImage(); //Tomar la imagen que modificaremos
            // //El tamaño de la imagen será random
            // random = getRandomNumber(11,17);//Esta función está implementada más abajo.
            // image.scale(image.getWidth()/random, image.getHeight()/random);//Reescalar imagen a 2/8 de las medidas originales.
            // setImage(image);//Acomodar ahora sí la imagen modificada
        // }
        // else{//Se maneja una diferente escala dependiendo del tamaño de la imagen, pero pensamos en ponerlas a proporción del área de juego.
            // GreenfootImage image = getImage(); //Tomar la imagen que modificaremos
            // random = getRandomNumber(2, 3);
            // image.scale(image.getWidth()/random, image.getHeight()/random);//Reescalar imagen a 2/8 de las medidas originales.
            // setImage(image);//Acomodar ahora sí la imagen modificada
        // }
   }
   public void act(){
        // Add your action code here.
        w = getWorld();
        move(3);//Método que mueve a cierta velocidad el objeto
        if(getX()>=w.getWidth()-5||getX()<=5 || getY()>=w.getHeight()-5||getY()<=5){
            turn(180);
            if(Greenfoot.getRandomNumber(100)<90){
                turn(Greenfoot.getRandomNumber(90-45));
            }
        }
        // if(getY()>=w.getHeight()-5||getY()<=5){ //Estas líneas de más se pueden evitar.
            // turn(180);
            // if(Greenfoot.getRandomNumber(100)<90){
                // turn(Greenfoot.getRandomNumber(90-45));
            // }
        // }
        /*MÉTODO QUE VERIFICARÁ SI UNA ROCA CHOCÓ CON ALGO. Necesitamos ver si podemos implemementar esto de manera
            más general porque también será necesario en la clase NaveEnemiga.
                Implementé este método más general en la clase "MétodosGenerales"*/
        /*Se heredan todas las características, y se ponen como parámetros 0, 0
           porque de esta manera vamos a saber que los objetos chocaron.*/
        // Actor naveAliada = getOneObjectAtOffset(0, 0, NaveAliada.class);
        // if(naveAliada != null){ //Aquí se va a revisar si chocó con nuestra nave
            // Espacio e = (Espacio)getWorld(); //Se toma el mundo actual
            // getWorld().removeObject(naveAliada); //Se elimina la nave
            // getWorld().removeObject(this); //Se elimina la roca
            // //AQUÍ TENGO QUE TOMAR EN CUENTA LAS VIDAS QUE TENGO PARA IR BAJÁNDOLAS
            // //naveAliada.vidas++; //Esto solo es conceptual.
        // }
        /*Método que elimina la roca y la nave si chocan
           public boolean eliminarObjetoChoque(Actor objetoChoque, Actor objetoRaiz, World mundoActual)
            El "this" es el objeto actual.
         -> No se implementa aquí porque ya está en la clase "NaveAliada".*/
        //m.eliminarObjetoChoque(getOneObjectAtOffset(0, 0, NaveAliada.class), this, (Espacio)getWorld());

        // Actor disparoNave = getOneObjectAtOffset(0, 0, Disparo.class);
        // if(disparoNave != null){ //Aquí sevisamos el el disparo chocó con la roca
            // /*El único problema que veo es que cuando se elimina ni siquiera chocan los objetos. Hay que arreglar esto.*/
            // Espacio e = (Espacio)getWorld(); //Se toma el mundo actual
            // getWorld().removeObject(disparoNave); //Se elimina el disparo
            // getWorld().removeObject(this); //Se elimina la roca
        // }
        /*Método que elimina la roca y el disparo al chocar.
            public boolean eliminarObjetoChoque(Actor objetoChoque, Actor objetoRaiz, World mundoActual)
                Lo implementé en la clase Disparo para que quede ahí todo organizado.*/
            /*No tiene puntos de salud por lo que mandamos un 1 y el daño del disparo para que cumpla la condición de daño,
               así que cualquier disparo destruirá el meteorito, pero esto sirve para aumentar los puntos.*/
    //public int eliminarObjetoChoque(Actor objetoChoque, Actor objetoRaiz, World mundoActual, int puntosSalud, int daño, int puntosNave)
        if(Choques.eliminarObjetoChoque(getOneObjectAtOffset(0, 0, Disparo.class), this,
                (Espacio)getWorld(), 1, Disparo.getDaño(), 5) <= 0)
            Espacio.setNumRocasActual(Espacio.getNumRocasActual() - 1); //Como se destruyó la roca, restarla al total de estas.
            //NaveAliada.setPuntos(10);//Aumentar 10 puntos al jugador por destruir una roca.
            //Espacio.numRocasActual-= 1;
    }


    // public boolean chocanRocas(int x, int y){//Método que evaluará si dos rocas están chocando para eliminarlas
        // if(getX()>=x-5 && getX()<=x+5 && getY()>=y-5 && getY()<=y+5)//Comparar posición de roca parámetro con la actual
            // return true;
        // else
            // return false;//No necesita else porque si no entra en el if, llegará aquí directamente
    // }
    public int getCoordX(){
        return getX();
    }
    public int getCoordY(){
        return getY();
    }
}
