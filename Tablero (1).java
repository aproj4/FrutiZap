import java.util.ArrayList;
import java.util.Collections;

/**
 * Representa el tablero del juego de memoria.
 * Contiene todas las tarjetas, las mezcla aleatoriamente
 * y verifica si dos tarjetas seleccionadas forman una pareja.
 */
public class Tablero {

    /** Lista de tarjetas en el tablero */
    private ArrayList<Tarjeta> tarjetas;

    /**
     * Constructor del tablero.
     * Crea 4 pares de tarjetas (8 en total) y las mezcla aleatoriamente.
     */
    public Tablero() {
        tarjetas = new ArrayList<>();

        // 4 frutas distintas, cada una aparece dos veces (par)
        String[] frutas = {"🍎", "🍊", "🍇", "🍓"};

        for (String fruta : frutas) {
            tarjetas.add(new Tarjeta(fruta));
            tarjetas.add(new Tarjeta(fruta));
        }

        // Mezclar aleatoriamente
        Collections.shuffle(tarjetas);
    }

    /**
     * Retorna la tarjeta en la posición indicada.
     * @param indice posición de la tarjeta (0 a 15)
     * @return la tarjeta en esa posición
     */
    public Tarjeta getTarjeta(int indice) {
        return tarjetas.get(indice);
    }

    /**
     * Retorna el número total de tarjetas en el tablero.
     * @return cantidad de tarjetas
     */
    public int getTotalTarjetas() {
        return tarjetas.size();
    }

    /**
     * Verifica si dos tarjetas (por índice) forman una pareja.
     * Si son pareja, las marca como encontradas.
     * Si no, las vuelve a tapar.
     * @param indice1 posición de la primera tarjeta
     * @param indice2 posición de la segunda tarjeta
     * @return true si forman pareja, false si no
     */
    public boolean verificarPareja(int indice1, int indice2) {
        Tarjeta t1 = tarjetas.get(indice1);
        Tarjeta t2 = tarjetas.get(indice2);

        if (t1.getFruta().equals(t2.getFruta())) {
            t1.marcarComoEncontrada();
            t2.marcarComoEncontrada();
            return true;
        } else {
            t1.voltear(); // la tapa de nuevo
            t2.voltear(); // la tapa de nuevo
            return false;
        }
    }

    /**
     * Verifica si todas las tarjetas del tablero ya fueron encontradas.
     * @return true si el juego terminó, false si quedan parejas por encontrar
     */
    public boolean juegoTerminado() {
        for (Tarjeta t : tarjetas) {
            if (!t.isFueEncontrada()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Muestra el tablero completo en consola, en filas de 4 tarjetas.
     * Cada tarjeta muestra su fruta si está visible, o "?" si está oculta.
     */
    public void mostrarTablero() {
        System.out.println("\n--- TABLERO ---");
        for (int i = 0; i < tarjetas.size(); i++) {
            System.out.print((i + 1) + ":" + tarjetas.get(i) + "  ");
            if ((i + 1) % 4 == 0) {
                System.out.println();
            }
        }
        System.out.println("---------------");
    }
}
