public class VictimaConcreta implements Runnable {
    int saldo, apuesta, i, numeroApostacionado;
    Apostacion apostacion;

    public VictimaConcreta(Apostacion apostacion, int saldo, int i){
        this.apostacion = apostacion;
        this.saldo = saldo;
        this.i = i;
        apostacion.quienHaApostado[i] = false;
    }

    @Override
    public void run() {
        while (saldo>0){espera();
            try{
                if (apostacion.quienHaGanado[i]){
                    System.out.println("El jugador " + i + " HA GANADO \n\n\n");
                    saldo+=apuesta;
                }
                if (saldo>0 && saldo<100){
                    apuesta = saldo;
                }else{
                    apuesta = (int) (Math.random()*150);
                    if (apuesta>saldo){
                        apuesta = saldo;
                    }
                }
                numeroApostacionado = (int) (Math.random()*36);
                apostacion.numerosApostados[i] = numeroApostacionado;
                apostacion.dineroApostado[i]=apuesta;
                saldo-=apuesta;
                this.apostacion.addApuesta(apuesta, i);
                System.out.println("El jugador "+i+" ha apostado al numero: "+apostacion.numerosApostados[i]+", la maravillosa cantidad de: "+apuesta);
                System.out.println("Al jugador "+i+" le queda "+saldo);
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
        if (saldo<=0){
            System.out.println("El jugador "+i+" se ha ido a sacar más dinero");
            apostacion.contadorJugadores--;
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
