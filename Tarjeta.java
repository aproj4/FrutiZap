/**
 * Representa una tarjeta del juego de memoria.
 * Cada tarjeta tiene una fruta asignada, un estado de visibilidad
 * y un contador de veces que ha sido volteada.
 */
public class Tarjeta {

    /** Fruta que representa esta tarjeta (por ejemplo "🍎", "🍊", "🍇") */
    private String fruta;

    /** Indica si la tarjeta está actualmente volteada (visible) o no */
    private boolean estaVolteada;

    /** Indica si la tarjeta ya fue encontrada como pareja correcta */
    private boolean fueEncontrada;

    /** Cantidad de veces que esta tarjeta ha sido volteada */
    private int vecesVolteada;

    /**
     * Constructor de la tarjeta.
     * @param fruta la fruta que representa esta tarjeta
     */
    public Tarjeta(String fruta) {
        this.fruta = fruta;
        this.estaVolteada = false;
        this.fueEncontrada = false;
        this.vecesVolteada = 0;
    }

    /**
     * Voltea la tarjeta: si estaba boca abajo la pone visible, y viceversa.
     * Incrementa el contador de veces volteada cada vez que se llama.
     */
    public void voltear() {
        this.estaVolteada = !this.estaVolteada;
        this.vecesVolteada++;
    }

    /**
     * Retorna la fruta de la tarjeta.
     * @return fruta asignada
     */
    public String getFruta() {
        return fruta;
    }

    /**
     * Indica si la tarjeta está actualmente volteada.
     * @return true si está visible, false si está boca abajo
     */
    public boolean isEstaVolteada() {
        return estaVolteada;
    }

    /**
     * Indica si esta tarjeta ya fue encontrada como pareja.
     * @return true si ya fue encontrada
     */
    public boolean isFueEncontrada() {
        return fueEncontrada;
    }

    /**
     * Marca esta tarjeta como encontrada (pareja correcta).
     */
    public void marcarComoEncontrada() {
        this.fueEncontrada = true;
        this.estaVolteada = true;
    }

    /**
     * Retorna cuántas veces ha sido volteada esta tarjeta.
     * @return número de veces volteada
     */
    public int getVecesVolteada() {
        return vecesVolteada;
    }

    /**
     * Representación visual de la tarjeta en consola.
     * Muestra la fruta si está volteada o encontrada, o "?" si está oculta.
     * @return texto que representa el estado de la tarjeta
     */
    @Override
    public String toString() {
        if (fueEncontrada || estaVolteada) {
            return "[ " + fruta + " ]";
        } else {
            return "[  ?  ]";
        }
    }
}
