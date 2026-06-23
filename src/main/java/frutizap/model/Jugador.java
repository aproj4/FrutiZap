package frutizap.model;

/**
 * Representa un jugador del juego.
 *
 * @author Ana
 */
/**
 * Representa un jugador del juego.
 * Mantiene el nombre y el puntaje acumulado.
 */
public class Jugador {

    private String nombre;
    private int puntaje;

    /**
     * Construye un jugador con nombre y puntaje inicial cero.
     *
     * @param nombre nombre del jugador
     */
    public Jugador(String nombre) {
        this.nombre = nombre;
        this.puntaje = 0;
    }

    /**
     * Obtiene el nombre del jugador.
     *
     * @return nombre del jugador
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene el puntaje acumulado del jugador.
     *
     * @return puntaje actual
     */
    public int getPuntaje() {
        return puntaje;
    }

    /**
     * Suma puntos al puntaje acumulado del jugador.
     *
     * @param puntos cantidad de puntos a sumar
     */
    public void sumarPuntos(int puntos) {
        puntaje += puntos;
    }
}
