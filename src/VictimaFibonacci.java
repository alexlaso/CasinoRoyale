public class VictimaFibonacci implements Runnable {
    int saldo, apuesta, i, numeroApostacionado, primeraApuesta, segundaApuesta;
    Apostacion apostacion;
    boolean apuestaInicial = true;
    public VictimaFibonacci(Apostacion apostacion, int saldo, int i){
        this.apostacion = apostacion;
        this.saldo = saldo;
        this.i = i;
        apostacion.quienHaApostado[i]=false;
    }

    @Override
    public void run() {
        while (saldo>0){espera();
            try{
                if (apostacion.quienHaGanado[i] || apuestaInicial){
                    if (apostacion.quienHaGanado[i]){
                        System.out.println("El jugador " + i + " HA GANADO \n\n\n");
                        saldo+=apuesta;
                    }
                    if (apuestaInicial){
                        apuestaInicial=false;
                    }
                    apuesta = 20;
                    primeraApuesta = 0;
                    segundaApuesta = apuesta;
                }
                if (!apostacion.quienHaGanado[i]){
                    apuesta = primeraApuesta + segundaApuesta;
                    if (apuesta > saldo){
                        apuesta=saldo;
                    }
                    primeraApuesta = segundaApuesta;
                    segundaApuesta = apuesta;
                }
                numeroApostacionado = (int) (Math.random()*36);
                apostacion.numerosApostados[i] = numeroApostacionado;
                apostacion.dineroApostado[i]=apuesta;
                saldo-=apuesta;
                this.apostacion.addApuesta(apuesta, i);
                System.out.println("El jugador "+i+" ha apostado al numero: "+apostacion.numerosApostados[i]+", la maravillosa cantidad de: "+apuesta);
                System.out.println("Al jugador "+i+" le queda "+saldo);
            } catch (Exception e) {
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
