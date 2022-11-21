public class VictimaMartingala implements Runnable {
    int saldo, apuesta, i, numeroApostacionado, ultimaApuesta;
    Apostacion apostacion;
    boolean apuestaInicial = true;
    public VictimaMartingala(Apostacion apostacion, int saldo, int i){
        this.apostacion = apostacion;
        this.saldo = saldo;
        this.i = i;
        apostacion.quienHaApostado[i] = false;
    }

    @Override
    public void run() {
        while (saldo>0){espera();
            try {
                if (apostacion.quienHaGanado[i] || apuestaInicial){
                    if (apostacion.quienHaGanado[i]){
                        System.out.println("El jugador " + i + " HA GANADO \n\n\n");
                        saldo+=apuesta;
                    }
                    if (apuestaInicial){
                        apuestaInicial=false;
                    }
                    apuesta = 20;//Hecho así para que la primera apuesta se duplique y apueste 20 de todas formas
                    ultimaApuesta=apuesta;
                }
                if (!apostacion.quienHaGanado[i] && !apuestaInicial){
                    apuesta = ultimaApuesta * 2;
                    if (apuesta > saldo) {
                        apuesta = saldo;
                    }
                    ultimaApuesta = apuesta;
                }
                saldo -= apuesta;
                numeroApostacionado = (int) (Math.random() * 36);
                this.apostacion.addApuesta(apuesta, i);
                System.out.println("El jugador "+i+" ha apostado al numero: "+apostacion.numerosApostados[i]+", la maravillosa cantidad de: "+apuesta);
                System.out.println("Al jugador "+i+" le queda "+saldo);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        if (saldo<=0){
            System.out.println("El jugador "+i+" se ha ido a sacar más dinero");
            apostacion.contadorJugadores--;
            System.out.println("Quedan "+apostacion.contadorJugadores+" jugadores");
            if (apostacion.contadorJugadores<1){
                Apostacion.finalizarPerdida();
            }
        }
    }


    private void espera() {
        try {
            Thread.sleep(1000);
        }
        catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
