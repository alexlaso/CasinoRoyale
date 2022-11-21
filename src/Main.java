import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Casino casino = new Casino();
        System.out.println("¿Cuantos jugadores hay en la mesa?");
        int numJugadores = sc.nextInt();

        casino.desplumar(numJugadores);
    }
}