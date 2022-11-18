public class Apostacion {
    public int total, contadorJugadores, numeroGanador;
    public int[] numerosApostados;
    public int[] dineroApostado;
    public boolean[] quienHaApostado;
    public boolean [] quienHaGanado;

    public boolean apuestaAbierta = true;

    public Apostacion(int numJugadores) {
        contadorJugadores = numJugadores;
        quienHaApostado = new boolean[numJugadores];
        numerosApostados = new int[numJugadores];
        quienHaGanado = new boolean[numJugadores];
        dineroApostado = new int[numJugadores];
        for (int i = 0; i < numJugadores; i++){
            numerosApostados[i] = 0;
            dineroApostado[i]=0;
        }
    }


    public synchronized void addApuesta(int apuesta, int i) throws InterruptedException {
        while (!apuestaAbierta && quienHaApostado[i]) {
            wait();
        }
        //
        try {
            total += apuesta;
            isAllTrue(quienHaApostado);
            quienHaApostado[i]=true;
        }
        finally{
            if (isAllTrue(quienHaApostado)) {
                apuestaAbierta=false;
            }
            notifyAll();
        }
    }

    public synchronized int repartirApuesta() throws InterruptedException {
        while (apuestaAbierta) {
            System.out.println("\nSe aceptan apuestas\n");
            if (contadorJugadores<1){
                finalizarPerdida();
            }
            wait();}
        if (!apuestaAbierta&&contadorJugadores>0){
        try{
            /*for (int i=0;i<contadorJugadores;i++){
                System.out.println("El jugador " + i+" ha apostado: "+ quienHaApostado[i]);
            }*/
            numeroGanador = (int) (Math.random()*36);
            System.out.println("El numero ganador es: "+ numeroGanador);
        for (int i=0;i< quienHaApostado.length;i++){
            quienHaApostado[i]=false;
            if (numeroGanador == 0){
                quienHaGanado[i]=false;
            } else if(numerosApostados[i]==numeroGanador){
                quienHaGanado[i]=true;
            } else {
                quienHaGanado[i]=false;
            }
        }
            System.out.println("Las apuestas se han repartido");
        }catch(Exception ignored){}
        finally {
            apuestaAbierta = true;
            notifyAll();
        }}
        return total;
        }


    public static boolean isAllTrue(boolean[] quienHaApostado)
    {
        for (boolean b : quienHaApostado) {
            if (!b) return false;
        }
        return true;
    }

    public static void finalizarPerdida(){
        System.out.println("\nGANA LA BANCA\n");
        System.exit(0);
    }

    public static void estoNuncaVaAOcurrir(){
        System.out.println("GANA UN JUGADOR");
        System.exit(0);

    }
    }

