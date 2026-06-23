package frutizap.app;

import java.util.Scanner;
import frutizap.logic.Tablero;
import frutizap.logic.JuegoFracciones;
import frutizap.model.FraccionPregunta;
import frutizap.model.Jugador;

/**
 * Clase principal del juego de memoria por consola.
 * El jugador selecciona dos tarjetas por número y el juego
 * verifica si forman una pareja. El juego termina cuando
 * se encuentran todas las parejas.
 */
/**
 * Clase principal del juego de memoria por consola.
 * Proporciona la experiencia de la Fase 1 de memoria y la
 * transición a la Fase 2 de preguntas de fracciones.
 */
public class Main {

    /**
     * Punto de entrada del programa.
     * Inicia el juego, controla el flujo del tablero y luego
     * ejecuta la fase de fracciones.
     *
     * @param args argumentos de línea de comandos (no se usan)
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Tablero tablero = new Tablero();
        Jugador jugador = new Jugador("Jugador 1");
        int intentos = 0;

        System.out.println("=== JUEGO DE MEMORIA ===");
        System.out.println("Encuentra las 4 parejas de frutas!");

        while (!tablero.juegoTerminado()) {
            tablero.mostrarTablero();

            int indice1 = pedirTarjeta(scanner, tablero, "Escoge la primera tarjeta (1-8): ", -1);
            tablero.getTarjeta(indice1).voltear();
            tablero.mostrarTablero();

            int indice2 = pedirTarjeta(scanner, tablero, "Escoge la segunda tarjeta (1-8): ", indice1);
            tablero.getTarjeta(indice2).voltear();
            tablero.mostrarTablero();

            intentos++;
            if (tablero.verificarPareja(indice1, indice2)) {
                jugador.sumarPuntos(10);
                System.out.println("✅ ¡Pareja encontrada! " + tablero.getTarjeta(indice1).getFruta());
                System.out.println("Puntos: " + jugador.getPuntaje());
            } else {
                System.out.println("❌ No son pareja. Las tarjetas se tapan de nuevo.");
            }
            System.out.println("Intentos: " + intentos);
        }

        System.out.println("\n🎉 ¡Felicitaciones, " + jugador.getNombre() + "! Completaste el juego en " + intentos + " intentos.");
        System.out.println("Puntaje final: " + jugador.getPuntaje());

        ejecutarFaseFracciones(scanner);
        scanner.close();
    }

    /**
     * Solicita al jugador el índice de una tarjeta válida.
     * Valida que la selección esté dentro del rango, no haya sido encontrada
     * y no sea el mismo índice anterior.
     *
     * @param scanner     lector de entrada estándar
     * @param tablero     tablero actual del juego
     * @param mensaje     mensaje que se muestra al jugador
     * @param indiceAnterior índice previamente elegido para evitar duplicados
     * @return el índice válido de la tarjeta seleccionada (0 basado)
     */
    private static int pedirTarjeta(Scanner scanner, Tablero tablero, String mensaje, int indiceAnterior) {
        int indice = -1;
        while (indice < 0) {
            System.out.print(mensaje);
            try {
                int numero = Integer.parseInt(scanner.nextLine().trim());
                if (numero < 1 || numero > tablero.getTotalTarjetas()) {
                    System.out.println("Número inválido. Ingresa entre 1 y 8");
                } else if (tablero.getTarjeta(numero - 1).isFueEncontrada()) {
                    System.out.println("Esa tarjeta ya fue encontrada. Elige otra.");
                } else if (numero - 1 == indiceAnterior) {
                    System.out.println("No puedes seleccionar la misma tarjeta dos veces. Elige otra.");
                } else {
                    indice = numero - 1;
                }
            } catch (NumberFormatException e) {
                System.out.println("Ingresa solo un número.");
            }
        }
        return indice;
    }

    /**
     * Ejecuta la fase de preguntas de fracciones por consola.
     * Crea preguntas, las mezcla, solicita respuestas al jugador
     * y muestra el resumen final.
     *
     * @param scanner lector de entrada estándar
     */
    private static void ejecutarFaseFracciones(Scanner scanner) {
        JuegoFracciones juegoFracciones = new JuegoFracciones();
        juegoFracciones.agregarPregunta(new FraccionPregunta("Manzana", 1, 2, "¿Qué fracción es la mitad de una manzana?"));
        juegoFracciones.agregarPregunta(new FraccionPregunta("Naranja", 1, 4, "¿Qué fracción es un cuarto de una naranja?"));
        juegoFracciones.agregarPregunta(new FraccionPregunta("Uva", 3, 4, "¿Qué fracción representa tres cuartos de un racimo de uvas?"));

        juegoFracciones.mezclarPreguntas();

        System.out.println("\n=== FASE 2: ACTIVIDADES DE FRACCIONES ===");

        while (juegoFracciones.tieneSiguientePregunta()) {
            FraccionPregunta pregunta = juegoFracciones.obtenerSiguientePregunta();
            System.out.println(pregunta.getDescripcion());
            System.out.println("Fruta: " + pregunta.getFruta());
            System.out.println("Ingresa la fracción correcta como numerador y denominador separados.");

            int numerador = pedirNumero(scanner, "Numerador: ");
            int denominador = pedirNumero(scanner, "Denominador: ");

            if (juegoFracciones.validarRespuestaActual(numerador, denominador)) {
                System.out.println("✅ Respuesta correcta!");
            } else {
                System.out.println("❌ Respuesta incorrecta. La respuesta correcta es " + pregunta.getFraccionTexto());
            }
        }

        System.out.println("\nHas completado la fase de fracciones.");
        System.out.println("Respuestas correctas: " + juegoFracciones.getRespuestasCorrectas() + "/" + juegoFracciones.getTotalPreguntas());
    }

    /**
     * Solicita un número entero no negativo al jugador.
     * Repite la petición hasta que el valor ingresado sea válido.
     *
     * @param scanner lector de entrada estándar
     * @param mensaje mensaje que se muestra al jugador
     * @return número entero no negativo ingresado por el usuario
     */
    private static int pedirNumero(Scanner scanner, String mensaje) {
        int valor = -1;
        while (valor < 0) {
            System.out.print(mensaje);
            try {
                valor = Integer.parseInt(scanner.nextLine().trim());
                if (valor < 0) {
                    System.out.println("Ingresa un número no negativo.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Ingresa solo un número válido.");
            }
        }
        return valor;
    }
}
