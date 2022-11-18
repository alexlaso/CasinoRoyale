import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int numJugadores, tipoApuesta;

        Casino casino = new Casino();
        System.out.println("¿Cuantos jugadores hay en la mesa?");
        numJugadores = sc.nextInt();/*
        System.out.println("¿Qué tipo de apuesta desea realizar?");
        System.out.println("1 \uF0E0 Apuesta fija a número concreto");
        System.out.println("2 \uF0E0 Apuesta al estilo Fibonacci");
        System.out.println("3 \uF0E0 Apuesta al estilo Martingala");
        tipoApuesta = sc.nextInt();*/

        casino.desplumar(numJugadores);
    }
}