import java.util.Scanner;

/**
 * Clase principal del juego de memoria por consola.
 * El jugador selecciona dos tarjetas por número y el juego
 * verifica si forman una pareja. El juego termina cuando
 * se encuentran todas las parejas.
 */
public class Main {

    /**
     * Punto de entrada del programa.
     * @param args argumentos de línea de comandos (no se usan)
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Tablero tablero = new Tablero();
        int intentos = 0;

        System.out.println("=== JUEGO DE MEMORIA ===");
        System.out.println("Encuentra las 4 parejas de frutas!");

        while (!tablero.juegoTerminado()) {

            tablero.mostrarTablero();

            // Pedir primera tarjeta
            int indice1 = pedirTarjeta(scanner, tablero, "Escoge la primera tarjeta (1-8): ");
            tablero.getTarjeta(indice1).voltear();
            tablero.mostrarTablero();

            // Pedir segunda tarjeta
            int indice2 = pedirTarjeta(scanner, tablero, "Escoge la segunda tarjeta (1-8): ");
            tablero.getTarjeta(indice2).voltear();
            tablero.mostrarTablero();

            // Verificar pareja
            intentos++;
            if (tablero.verificarPareja(indice1, indice2)) {
                System.out.println("✅ ¡Pareja encontrada! " + tablero.getTarjeta(indice1).getFruta());
            } else {
                System.out.println("❌ No son pareja. Las tarjetas se tapan de nuevo.");
            }
            System.out.println("Intentos: " + intentos);
        }

        System.out.println("\n🎉 ¡Felicitaciones! Completaste el juego en " + intentos + " intentos.");
        scanner.close();
    }

    /**
     * Pide al jugador que ingrese un número de tarjeta válido.
     * Valida que la tarjeta exista y no haya sido encontrada ya.
     * @param scanner lector de consola
     * @param tablero el tablero actual
     * @param mensaje texto que se muestra al jugador
     * @return índice válido (0 a 15) de la tarjeta elegida
     */
    private static int pedirTarjeta(Scanner scanner, Tablero tablero, String mensaje) {
        int indice = -1;
        while (indice < 0) {
            System.out.print(mensaje);
            try {
                int numero = Integer.parseInt(scanner.nextLine().trim());
                if (numero < 1 || numero > tablero.getTotalTarjetas()) {
                    System.out.println("Número inválido. Ingresa entre 1 y 8");
                } else if (tablero.getTarjeta(numero - 1).isFueEncontrada()) {
                    System.out.println("Esa tarjeta ya fue encontrada. Elige otra.");
                } else {
                    indice = numero - 1;
                }
            } catch (NumberFormatException e) {
                System.out.println("Ingresa solo un número.");
            }
        }
        return indice;
    }
}
