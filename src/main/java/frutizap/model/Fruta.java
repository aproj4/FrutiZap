package frutizap.model;

/**
 * Representa una fruta utilizada en el juego FrutiZap.
 *
 * Cada fruta puede dividirse en varias partes para
 * actividades relacionadas con fracciones.
 */
public class Fruta {

    private String nombre;
    private int partes;

    /**
     * Constructor de la fruta.
     *
     * @param nombre Nombre de la fruta.
     * @param partes Cantidad de partes en las que puede dividirse.
     */
    public Fruta(String nombre, int partes) {
        this.nombre = nombre;
        this.partes = partes;
    }

    /**
     * Obtiene el nombre de la fruta.
     *
     * @return nombre de la fruta.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene la cantidad de partes en las que se puede dividir la fruta.
     *
     * @return cantidad de partes
     */
    public int getPartes() {
        return partes;
    }
}
