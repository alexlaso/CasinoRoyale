public class Casino {
    public void desplumar(int numJugadores) {
        Apostacion apostacion = new Apostacion(numJugadores);
        for (int i = 0; i < numJugadores; i++) {
            int nuevojugador = (int) ((Math.random() *3)+1);
            switch (nuevojugador) {
                case 1 -> {
                    new Thread(new VictimaConcreta(apostacion, 1000, i)).start();
                    System.out.println("Nuevo jugador Concreto");
                }
                case 2 -> {
                    new Thread(new VictimaFibonacci(apostacion, 1000, i)).start();
                    System.out.println("Nuevo jugador Fibonacci");
                }
                case 3 -> {
                    new Thread(new VictimaMartingala(apostacion, 1000, i)).start();
                    System.out.println("Nuevo jugador Martingala");
                }
            }
        }
        new Thread(new Banca(apostacion, 500000)).start();
    }
}
