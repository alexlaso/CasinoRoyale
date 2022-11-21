public class Banca implements Runnable {
    int dineroBanca;
    Apostacion apostacion;

    public Banca(Apostacion apostacion, int dineroBanca){
        this.apostacion=apostacion;
        this.dineroBanca=dineroBanca;
    }

    @Override
    public void run() {
        if (apostacion.contadorJugadores<2){
            Apostacion.finalizarPerdida();
        }
        while(apostacion.contadorJugadores>0){
            espera();
        try {
            apostacion.repartirApuesta();
            dineroBanca+=apostacion.total;
            regalarDinero();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        if (dineroBanca<1){
            Apostacion.estoNuncaVaAOcurrir();
        }
        }
        }

        private void regalarDinero(){
        for (int i=0; i<apostacion.quienHaGanado.length; i++){
            if (apostacion.quienHaGanado[i]){
                dineroBanca-=apostacion.dineroApostado[i];
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
