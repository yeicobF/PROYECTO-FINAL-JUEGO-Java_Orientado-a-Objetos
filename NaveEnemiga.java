import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.lang.Math; //La utilizaremos para sacar el ángulo con la nave aliada.
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
    /*Este constructor dará vida y tipo de disparo (aún no implementado) dependiendo del tipo de enemigo sea*/
    /*ArrayList estático con las naves enemigas para ver que no choquen y así.*/
    private static ArrayList<NaveEnemiga> navesAL; //Estático para que sea igual para todas las instancias.
    NaveEnemiga naveLimites; //Así se revisarán los límites de las naves..
    private boolean arrayListCreado = false;
    private int random;//Quisiera que las naves enemigas apuntaran hacia nosotros y si no al menos que apuntaran hacia la izquierda.
    private int puntosPorDisparo; //Variable que define cuántos puntos obtendremos cuando se dispare a la nave.
    private int anchoImagen;
    private int altoImagen;
    private int radio; //El radio de la imagen (tomando en cuenta la medida más grande).
    //private int anguloApuntado;
    // private static int puntosSalud;
    //Ya existe un atributo protegido de Nave "existeMostrarInfo"
    //private boolean muestraPS; //Para ver que se haya creado MostrarInfo, ya que no se puede crear al instanciar.
    public NaveEnemiga(int tipoEnemigo, int tipoDisparo){//Tal vez haga falta un MINIBOSS
        super(tipoDisparo, tipoEnemigo);//public Nave(int tipoDisparo, int diseño)
        if(!arrayListCreado){ //Crear ArrayList si no se ha creado
             navesAL = new ArrayList<NaveEnemiga>();
             arrayListCreado = true;
        }
        //System.out.println("Se creo un enemigo");
        infoPS = new MostrarInfo(puntosSalud, 0, 20, Color.BLACK, Color.YELLOW, null);
        // muestraPS = false; //Ya existe un atributo protegido de Nave "existeMostrarInfo"
        //Instanciar mostrarInfo para mostrar los PS de la nave enemiga encima de estas.
        //public MostrarInfo(int tipoInfo, int tamañoFuente, Color colorFuente, Color colorFondo, Color bordeFuente)
        //getY()-getImage().getHeight()/2 para poner el texto arriba de la nave
        //NO SE PUEDE AGREGAR OBJETO EN EL CONSTRUCTOR
        // getWorld().addObject(infoPS, getX(), getY()-getImage().getHeight()/2);
        setImage("Naves/Enemigas/NaveE"+ tipoEnemigo+ ".png");
        setImage(Imagen.modificarEscalaImagen(getImage(), 2, 1));//Acomodar la imagen modificada. La recibimos del método directamente. No necesitamos ninguna variable.
        anchoImagen = getImage().getWidth();
        altoImagen = getImage().getHeight();
        //Revisa cuál medida es mayor para tomar el radio.
        radio = (getImage().getWidth() >= getImage().getHeight()) ? getImage().getWidth() : getImage().getHeight();
        // System.out.println("Ancho total: "+ anchoImagen +"Alto total: "+ altoImagen +"Radio: "+ radio);
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
        // if(tipoEnemigo == 1){//BOSS == "0"
            // puntosSalud+= 100;//Que las naves de BOSSES tengan más vida
            // puntosPorDisparo = 50;
            // //tipoDisparo=1;//Disparo más potente pero más lento
        // }
        // else{//ENEMIGOS PEQUEÑOS
            // puntosSalud-= 50;//Que las naves de enemigos pequeños tengan menos vida (vida=100-50)
            // puntosPorDisparo = 25;
            // //tipoDisparo=2;//Disparo menos potente pero más rápido
        // }
        anguloGiro = 0;
        //setRotation(270);
        pantalla = new Pantalla(this);
        navesAL.add(this);//Agregar el objeto al arrayList una vez creado.
    }
    public void act() //Este código lo reutilicé de la clase Roca, por lo que lo podría poner en una clase más general
       {   /*Aquí con un random se verá la dirección en la que se moverá la nave.
        Dependiendo del random (que será el ángulo de dirección), se moverá a una dirección.*/
        // Add your action code here.
        mundo = getWorld();
        //Método para mostrar los PS de cada nave y que se muevan con ellos. Implementado en clase Nave como PROTECTED.
        existeMostrarInfo = muestraPuntosSalud(infoPS, existeMostrarInfo, "PS: ", puntosSalud, getX(), getY()-getImage().getHeight()/2);
        movimientoLimites(mundo, getX(), getY());
        //protected boolean muestraPuntosSalud(MostrarInfo infoPS, boolean yaExistente, String texto, int x, int y)
        // /* Si los PS no se han mostrado, se agrega el texto.*/
        // if(!muestraPS){//getY()-getImage().getHeight()/2 <- Para posicionar encima de las naves.
        // getWorld().addObject(infoPS, getX(), getY()-getImage().getHeight()/2);
        // muestraPS = true;
        // }
        // /*En este método se mandan las coordenadas en donde se quieren agregar los PS. Estos se actualizarán cuando cambien.*/
        // infoPS.mostrarPS("PS: ", puntosSalud, getX(), getY()-getImage().getHeight()/2);

        /*MÉTODO QUE VERIFICARÁ SI UNA ROCA CHOCÓ CON ALGO. Necesitamos ver si podemos implemementar esto de manera
        más general porque también será necesario en la clase NaveEnemiga*/
        puntosSalud = eliminaCuadroPS(infoPS, getOneObjectAtOffset(0, 0, Disparo.class),
                    this, getWorld(), puntosSalud, Disparo.getDaño(), puntosPorDisparo);

                    //NaveAliada.setPuntos(puntosPorDisparo*2);//Los puntos obtenidos se multiplicarán por 2 al destruir la nave
                    //Ya está condición dentro del método
    }
    protected void movimiento(){
        //Si el objeto alcanza los límites en x o y, se dará la vuelta. Las limitaremos a la mitad de la pantalla.
        //limiteChoqueNavesEnemigas();
        //limiteChoqueNaveAliada(200);
        move(1);//Método que mueve a cierta velocidad el objeto
        turnTowards(NaveAliada.getCordX(), NaveAliada.getCordY());
        puedeMoverse();
        // limiteChoqueNavesEnemigas();
        //setLocation(getX()+1, getY()+1);
    }
    /**Métdodo que verifica si la nave puede moverse.*/
    private void puedeMoverse(){
        //Revisar si alguna nave chocó con nosotros.
        /*Martes, 16 de junio de 2020. Intentaré hacer lo de los límite con getNeighbours o creo que sería mejor
           getObjectsInRange.*/
        int izqNave, derNave, arrNave, abNave; //Para guardar las coordenadas de las naves.
        izqNave = derNave = arrNave = abNave = 0;
        //Establecer el radio de la medida más grande.
        //Hacer una lista de naves enemigas con las que se choca.
        List<NaveEnemiga> navesEnemigas = getObjectsInRange(radio, NaveEnemiga.class);
        //Si el tamaño es mayor a 0, automáticamente se obtendrá la nave con la que se choca.
        if(navesEnemigas.size() > 0){
            for(int i = 0; i < navesEnemigas.size(); i++)
                naveLimites = navesEnemigas.get(i); //Asignar la nave que chocó a una variable auxiliar para revisar los límites.
                //System.out.println("Entró al ciclo por choques enemigos:"+ navesEnemigas.size());
                //Establecer las coordenadas de las orillas de la imagen de las naves con las que chocamos.
                arrNave = naveLimites.getY() - naveLimites.getAltoImagen();
                abNave = naveLimites.getY() + naveLimites.getAltoImagen();
                izqNave = naveLimites.getX() - naveLimites.getAnchoImagen();
                derNave = naveLimites.getX() + naveLimites.getAnchoImagen();
                //Ahora revisar los líites para salir de estos.
                
                
                
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
        // if(getOneObjectAtOffset(getX() + anchoImagen/2, getY(), _cls_))
    }
    /*Método que marcará un límite entre las mismas naves enemigas y
       nuestra nave para que no choquen.*/
    // private void limiteChoqueNavesEnemigas(){
        // // //Recorrer las demás naves para ver que no choque
        // // for(NaveEnemiga nave : navesAL){
            // // //(System.out.println("Entro al ciclo");
            // // if((getX() + anchoImagen/2) >= (nave.getX() - nave.getAnchoImagen()/2)) //Ver que no sobrepase por la izquierda.
                // // //System.out.println("(getX() + anchoImagen/2) >= (nave.getX() - nave.getAnchoImagen()/2)");
                // // setLocation(getX()-1, getY());
            // // if((getX() - anchoImagen/2) <= (nave.getX() + nave.getAnchoImagen()/2)) //Ver que no sobrepase por la derecha.
                // // setLocation(getX()+1, getY());
            // // if((getY() + altoImagen/2) >= (nave.getY() - nave.getAltoImagen()/2))//Ver que no sobrepase por encima.
                // // setLocation(getX(), getY()-1);
            // // if((getY() - altoImagen/2) <= (nave.getY() + nave.getAltoImagen()/2))//Ver que no sobrepase por debajo.
                // // setLocation(getX(), getY()+1);
        // // }
        // /*Martes, 16 de junio de 2020. Intentaré hacer lo de los límite con getNeighbours o creo que sería mejor
           // getObjectsInRange.*/
        // int radio, izqNave, derNave, arrNave, abNave; //Para guardar las coordenadas de las naves.
        // radio = izqNave = derNave = arrNave = abNave = 0;
        // //Establecer el radio de la medida más grande.
        // radio = (getImage().getWidth()/2 >= getImage().getHeight()/2) ? getImage().getWidth()/2 : getImage().getHeight()/2;
        // //Hacer una lista de naves enemigas con las que se choca.
        // List<NaveEnemiga> navesEnemigas = getObjectsInRange(radio, NaveEnemiga.class);
        // //Si el tamaño es mayor a 0, automáticamente se obtendrá la nave con la que se choca.
        // if(navesEnemigas.size() > 0){
            // for(int i = 0; i < navesEnemigas.size(); i++)
                // naveLimites = navesEnemigas.get(i); //Asignar la nave que chocó a una variable auxiliar para revisar los límites.
                // //Establecer las coordenadas de las orillas de la imagen de las naves con las que chocamos.
                // arrNave = naveLimites.getY() - naveLimites.getAltoImagen();
                // abNave = naveLimites.getY() + naveLimites.getAltoImagen();
                // izqNave = naveLimites.getX() - naveLimites.getAnchoImagen();
                // derNave = naveLimites.getX() + naveLimites.getAnchoImagen();
                // //Ahora revisar los líites para salir de estos.
                // if(izqNave < getX() + radio){ //Se pasó por la derecha a la izquierda.
                    // naveLimites.setLocation(naveLimites.getX() + 1, naveLimites.getY());
                    // setLocation(getX() - 1, getY()); //Acomodar también la nave origen.
                // }
                // if(derNave >= getX() - radio && abNave >= getY() - radio){ //Se pasó por la izquierda a la derecha.
                    // naveLimites.setLocation(naveLimites.getX() - 1, naveLimites.getY());
                    // setLocation(getX() + 1, getY());
                // }
                // if(abNave > getY() - radio){ //Se pasó por arriba hacia abajo.
                    // naveLimites.setLocation(naveLimites.getX(), naveLimites.getY() - 1);
                    // setLocation(getX(), getY() + 1);
                // }
                // if(arrNave < getY() + radio){ //Se pasó por abajo hacia arriba.
                    // naveLimites.setLocation(naveLimites.getX(), naveLimites.getY() + 1);
                    // setLocation(getX(), getY() - 1);
                // }
        // }
            // // getWorld().removeObjects(navesEnemigas); //Elimina las naves con las que choca.
    // }
    /*Método que pondrá un límite para no chocar con la nave aliada (nosotros).*/
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
