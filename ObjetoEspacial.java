    import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
    
    /**
     * Write a description of class ObjetoEspacial here.
     * 
     * @author (your name) 
     * @version (a version number or a date)
     */
    public class ObjetoEspacial extends Actor
    {
        public static final int UP=0;
        public static final int DOWN=1;
        public static final int LEFT=2;
        public static final int RIGHT=3;
        public static final int UP_RIGHT=4;
        public static final int UP_LEFT=5;
        public static final int DOWN_LEFT=6;
        public static final int DOWN_RIGHT=7;    
        protected int TipoGiro;//Gira izquierda o derecha.
        protected int VelocidadGiro;//Velocidad con la girara.
        protected int AnguloDeGiro;//Variable c on la que se designa el angulo.
        protected int VelocidadAvance;//Velocidad con la que avanzara el objeto.
        protected int Direccion;//A que direccion se movera el objeto.
        protected int cordY;
        protected int cordX;
        public void ObjetoEspacial()//Constructor
        {
            TipoGiro = (int) Math.random()*2; //Izquierda o derecha.
            VelocidadGiro = (int) Math.random()*10; //Constante aleatoria que determina la sumatoria del angulo.
            if(TipoGiro==0)
            {
             VelocidadGiro= VelocidadGiro*(-1);//Aqui si sale 0 se determina que el angulo sera negativo = que gire a la izquierda
            }
            VelocidadAvance = (int) Math.random()*5;//Velocidad de distancia que tendra el objeto.
            Direccion = (int) Math.random()*8;//Determina una direccion de las 8 posibles.
            Posicion();//Funcion que determina las coordenadas iniciales del objeto.

        }
        public void Posicion() 
        {
            int Temporal=0;
            Temporal = (int) Math.random()*4;//A esta variable se le designara temporalmente un valor el cual definira en que lado de la pantalla saldra arriba o abajo
            switch(Temporal)
            {
            case UP:
            cordX= (int) Math.random ()*600; //Un numero aleatorio para que pueda salir en un rango de 0-599 respecto a X
            cordY= 0; //La parte de arriba en coordenadas para Y siempre debe ser 0
            break;
            case DOWN:
            cordX= (int) Math.random ()*600;//Un numero aleatorio para que pueda salir en un rango de 0-599 respecto a X
            cordY=599; //La parte de abajo en coordenadas para Y siempre debe ser 600
            break;
            case RIGHT:
            cordX=600;
            cordY= (int) Math.random ()*600;//Un numero aleatorio para que pueda salir en un rango de 0-599 respecto a Y
            break;
            case LEFT:
            cordX=0;
            cordY=  (int) Math.random ()*600;//Un numero aleatorio para que pueda salir en un rango de 0-599 respecto a Y
            break;
            }
        }    
            public void setDireccion(int direccion){
        switch(direccion){
            case UP:
                AnguloDeGiro+=VelocidadGiro;//Aqui el angulo aumentara o disminuira.
                setRotation(AnguloDeGiro);
                    setLocation(getX(),getY()-VelocidadAvance);
                    cordY-=VelocidadAvance;

                break;
            case DOWN:
                AnguloDeGiro+=VelocidadGiro;//Aqui el angulo aumentara o disminuira.
                setRotation(AnguloDeGiro);
                    setLocation(getX(),getY()+VelocidadAvance);
                    cordY+=VelocidadAvance;
                break;
            case LEFT:
                AnguloDeGiro+=VelocidadGiro;//Aqui el angulo aumentara o disminuira.
                setRotation(AnguloDeGiro);
                    setLocation(getX()-VelocidadAvance,getY());
                    cordX-=VelocidadAvance;
                break;
            case RIGHT:
                AnguloDeGiro+=VelocidadGiro;//Aqui el angulo aumentara o disminuira.
                setRotation(AnguloDeGiro);
                    setLocation(getX()+VelocidadAvance,getY());
                    cordX+=VelocidadAvance;
                break;
                
            case UP_RIGHT:
                AnguloDeGiro+=VelocidadGiro;//Aqui el angulo aumentara o disminuira.
                setRotation(AnguloDeGiro);

                    setLocation(getX()+VelocidadAvance,getY()-VelocidadAvance);
                    cordX+=VelocidadAvance;
                    cordY-=VelocidadAvance;

                break;
            case UP_LEFT:
                AnguloDeGiro+=VelocidadGiro;//Aqui el angulo aumentara o disminuira.
                setRotation(AnguloDeGiro);

                    setLocation(getX()-VelocidadAvance,getY()-VelocidadAvance);
                    cordX-=VelocidadAvance;
                    cordY-=VelocidadAvance;

                break;
            case DOWN_LEFT:
                AnguloDeGiro+=VelocidadGiro;//Aqui el angulo aumentara o disminuira.
                setRotation(AnguloDeGiro);


                    setLocation(getX()-VelocidadAvance,getY()+VelocidadAvance);
                    cordX-=VelocidadAvance;
                    cordY+=VelocidadAvance;

                break;
            case DOWN_RIGHT:
                AnguloDeGiro+=VelocidadGiro;//Aqui el angulo aumentara o disminuira.
                setRotation(AnguloDeGiro);

                    setLocation(getX()+VelocidadAvance,getY()+VelocidadAvance);
                    cordX+=VelocidadAvance;
                    cordY+=VelocidadAvance;

                break;
        }       
    }
}
