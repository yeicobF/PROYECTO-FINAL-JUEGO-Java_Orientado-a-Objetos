import greenfoot.Actor;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import greenfoot.Color;
import java.util.ArrayList;
import java.util.List;

/**
* Clase que maneja a las naves enemigas y su comportamiento.
*
* @author (Team Naves)
* @version (Viernes, 29 de mayo de 2020)
*/

/* Character.compare(char 'A', char 'B'); Esto devuelve 0 si al comparar los caracteres son iguales,
                                 >0 si el 1er valor es mayor que el segundo
                                 <0 si el 1er valor es menor que el segundo*/
public class NaveEnemiga extends Nave
{
    /**
    * Act - do whatever the NaveEnemiga wants to do. This method is called whenever
    * the 'Act' or 'Run' button gets pressed in the environment.
    */
    //La vida siempre será 100 por el constructor de la superclase
    
    /*ArrayList estático con las naves enemigas para ver que no choquen y así.*/
    private static ArrayList<NaveEnemiga> navesAL; //Estático para que sea igual para todas las instancias.
    NaveEnemiga naveLimites; //Así se revisarán los límites de las naves..
    private boolean arrayListCreado = false;
    private int random;//Quisiera que las naves enemigas apuntaran hacia nosotros y si no al menos que apuntaran hacia la izquierda.
    private int puntosPorDisparo; //Variable que define cuántos puntos obtendremos cuando se dispare a la nave.
    private int anchoImagen;
    private int altoImagen;
    private int radio; //El radio de la imagen (tomando en cuenta la medida más grande).
    /** Este constructor dará vida y tipo de disparo (aún no implementado) dependiendo del tipo de enemigo sea.*/
    public NaveEnemiga(int tipoEnemigo, int tipoDisparo){//Tal vez haga falta un MINIBOSS
        super(tipoDisparo, tipoEnemigo);//public Nave(int tipoDisparo, int diseño)
        if(!arrayListCreado){ //Crear ArrayList si no se ha creado
             navesAL = new ArrayList<NaveEnemiga>();
             arrayListCreado = true;
        }
        infoPS = new MostrarInfo(puntosSalud, 0, 20, Color.BLACK, Color.YELLOW, null);
        setImage("Naves/Enemigas/NaveE"+ tipoEnemigo+ ".png");
        setImage(Imagen.modificarEscalaImagen(getImage(), 2, 1));//Acomodar la imagen modificada. La recibimos del método directamente. No necesitamos ninguna variable.
        anchoImagen = getImage().getWidth();
        altoImagen = getImage().getHeight();
        //Revisa cuál medida es mayor para tomar el radio con operadores ternarios.
        radio = (getImage().getWidth() >= getImage().getHeight()) ? getImage().getWidth() : getImage().getHeight();
        switch(tipoEnemigo){
            case 1:
                puntosSalud += 100;//Que las naves de BOSSES tengan más vida
                puntosPorDisparo = 50;
                break;
            case 2:
                puntosSalud += 150;//Que las naves de BOSSES tengan más vida
                puntosPorDisparo = 75;
                break;
            case 3:
                puntosSalud += 300;//Que las naves de BOSSES tengan más vida
                puntosPorDisparo = 100;
                break;
        }
        anguloGiro = 0;
        pantalla = new Pantalla(this);
        navesAL.add(this);//Agregar el objeto al arrayList una vez creado.
    }
    public void act() //Este código lo reutilicé de la clase Roca, por lo que lo podría poner en una clase más general
       {   
            mundo = getWorld();
            //Método para mostrar los PS de cada nave y que se muevan con ellos. Implementado en clase Nave como PROTECTED.
            existeMostrarInfo = muestraPuntosSalud(infoPS, existeMostrarInfo, "PS: ", puntosSalud, getX(), getY()-getImage().getHeight()/2);
            movimientoLimites(mundo, getX(), getY());
            puntosSalud = eliminaCuadroPS(infoPS, getOneObjectAtOffset(0, 0, Disparo.class),
                        this, getWorld(), puntosSalud, Disparo.getDaño(), puntosPorDisparo);
    }
    /** Método que hará mover a las naves enemigas. Abstracto en clase Nave.*/
    protected void movimiento(){
        move(1);//Método que mueve a cierta velocidad el objeto
        turnTowards(NaveAliada.getCordX(), NaveAliada.getCordY());
        puedeMoverse(); //Si el objeto alcanza los límites en x o y, se dará la vuelta. Las limitaremos a la mitad de la pantalla.
    }
    /** Método que verifica si la nave puede moverse.*/
    private void puedeMoverse(){ /*Este método no funciona como debería hasta ahora (Miércoles, 23 de junio de 2020).*/
        //Revisar si alguna nave chocó con nosotros.
        int izqNave, derNave, arrNave, abNave; //Para guardar las coordenadas de las naves.
        izqNave = derNave = arrNave = abNave = 0;
        //Establecer el radio de la medida más grande.
        //Hacer una lista de naves enemigas con las que se choca.
        List<NaveEnemiga> navesEnemigas = getObjectsInRange(radio, NaveEnemiga.class);
        //Si el tamaño es mayor a 0, automáticamente se obtendrá la nave con la que se choca.
        if(navesEnemigas.size() > 0){
            for(int i = 0; i < navesEnemigas.size(); i++)
                naveLimites = navesEnemigas.get(i); //Asignar la nave que chocó a una variable auxiliar para revisar los límites.
                //Establecer las coordenadas de las orillas de la imagen de las naves con las que chocamos.
                arrNave = naveLimites.getY() - naveLimites.getAltoImagen();
                abNave = naveLimites.getY() + naveLimites.getAltoImagen();
                izqNave = naveLimites.getX() - naveLimites.getAnchoImagen();
                derNave = naveLimites.getX() + naveLimites.getAnchoImagen();
                //Ahora revisar los líites para salir de estos
                //Pasó por límite derecho.
                if(getX() - radio <= derNave && getX() - radio > naveLimites.getX() && getY() >= arrNave && getY() <= abNave){
                // if(izqNave <= getX() + radio && naveLimites.getY() >= getY() - radio && naveLimites.getY() <= getY() + radio){ //Se pasó por la derecha a la izquierda.
                    System.out.println("1. getX() - radio <= derNave && getY() >= arrNave && getY() <= abNave\n\t"+
                                    (getX() - radio) +" <= "+ derNave +" && "+ getY() +" >= "+ arrNave +" && "+ getY() + "<="+ abNave);
                    naveLimites.setLocation(naveLimites.getX() - 1, naveLimites.getY());
                    setLocation(getX() + 1, getY()); //Acomodar también la nave origen.
                }
                if(getX() + radio >= izqNave && getX() + radio < naveLimites.getX() && getY() >= arrNave && getY() <= abNave){
                // if(derNave >= getX() - radio && naveLimites.getY() >= getY() - radio && naveLimites.getY() <= getY() + radio){ //Se pasó por la izquierda a la derecha.
                    System.out.println("2. getX() + radio >= izqNave && getY() >= arrNave && getY() <= abNave\n\t"+
                                    (getX() + radio) +" >= "+ izqNave +" && "+ getY() +" >= "+ arrNave +" && "+ getY() + "<="+ abNave);
                    naveLimites.setLocation(naveLimites.getX() + 1, naveLimites.getY());
                    setLocation(getX() - 1, getY());
                }
                if(getY() - radio <= abNave && getY() - radio > naveLimites.getY() && getX() >= izqNave && getX() <= derNave){
                // if(abNave >= getY() - radio && naveLimites.getX() >= getX() - radio && naveLimites.getX() <= getX() + radio){
                    System.out.println("3. getY() - radio <= abNave && getX() >= izqNave && getX() <= derNave\n\t"+
                                    (getY() - radio) +" <= "+ abNave +" && "+ getX() +" >= "+ izqNave +" && "+ getX() + "<="+ derNave);
                    naveLimites.setLocation(naveLimites.getX(), naveLimites.getY() - 1);
                    setLocation(getX(), getY() + 1);
                }
                if(getY() + radio >= arrNave && getY() + radio < naveLimites.getY() && getX() >= izqNave && getX() <= derNave){
                // if(arrNave <= getY() + radio && naveLimites.getX() >= getX() - radio && naveLimites.getX() <= getX() + radio){ //Se pasó por abajo hacia arriba.
                    System.out.println("4. getY() + radio >= arrNave && getX() >= izqNave && getX() <= derNave\n\t"+
                                    (getY() + radio) +" >= "+ arrNave +" && "+ getX() +" >= "+ izqNave +" && "+ getX() + "<="+ derNave);
                    naveLimites.setLocation(naveLimites.getX(), naveLimites.getY() + 1);
                    setLocation(getX(), getY() - 1);
                }
        }
    }
    /** Método que pondrá un límite para no chocar con la nave aliada (nosotros).*/
    private void limiteChoqueNaveAliada(int distanciaAlejamiento){
        if(getX() + anchoImagen >= NaveAliada.getCordX() - NaveAliada.getAnchoImagen()/2 - distanciaAlejamiento) //Ver que no sobrepase por la izquierda.
            setLocation(getX()-200, getY());
        if(getX() - anchoImagen <= NaveAliada.getCordX() + NaveAliada.getAnchoImagen()/2 + distanciaAlejamiento) //Ver que no sobrepase por la derecha.
            setLocation(getX()+200, getY());
        if(getY() + altoImagen >= NaveAliada.getCordY() - NaveAliada.getAltoImagen()/2 - distanciaAlejamiento)//Ver que no sobrepase por encima.
            setLocation(getX(), getY()-200);
        if(getY() - altoImagen <= NaveAliada.getCordY() + NaveAliada.getAltoImagen()/2 + distanciaAlejamiento)//Ver que no sobrepase por debajo.
            setLocation(getX(), getY()+200);
    }
    //Métodos que regresan el tamaño de la imagen de la nave.
    private int getAnchoImagen(){
        return getImage().getWidth();
    }
    private int getAltoImagen(){
        return getImage().getWidth();
    }
    public int getCordX(){
        return getX();
    }
    public int getCordY(){
        return getY();
    }
}
