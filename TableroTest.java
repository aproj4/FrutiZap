import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas unitarias para la clase Tablero.
 * Verifica la creación del tablero, la verificación de parejas
 * y la detección del fin del juego.
 */
public class TableroTest {

    /** Tablero usado en cada prueba */
    private Tablero tablero;

    /**
     * Se ejecuta antes de cada prueba.
     * Crea un tablero fresco.
     */
    @BeforeEach
    public void setUp() {
        tablero = new Tablero();
    }

    /**
     * El tablero debe tener exactamente 8 tarjetas.
     */
    @Test
    public void tableroTieneOchoTarjetas() {
        assertEquals(8, tablero.getTotalTarjetas());
    }

    /**
     * Al inicio el juego no debe estar terminado.
     */
    @Test
    public void juegoNoTerminaAlInicio() {
        assertFalse(tablero.juegoTerminado());
    }

    /**
     * Dos tarjetas con la misma fruta deben formar pareja.
     * Buscamos en el tablero dos posiciones con la misma fruta.
     */
    @Test
    public void verificarParejaRetornaTrueConFrutasIguales() {
        // Buscamos dos tarjetas con la misma fruta
        int indice1 = -1, indice2 = -1;
        for (int i = 0; i < tablero.getTotalTarjetas(); i++) {
            for (int j = i + 1; j < tablero.getTotalTarjetas(); j++) {
                if (tablero.getTarjeta(i).getFruta().equals(tablero.getTarjeta(j).getFruta())) {
                    indice1 = i;
                    indice2 = j;
                    break;
                }
            }
            if (indice1 >= 0) break;
        }

        // Las volteamos antes de verificar
        tablero.getTarjeta(indice1).voltear();
        tablero.getTarjeta(indice2).voltear();

        assertTrue(tablero.verificarPareja(indice1, indice2));
    }

    /**
     * Dos tarjetas con frutas distintas no deben formar pareja.
     */
    @Test
    public void verificarParejaRetornaFalseConFrutasDistintas() {
        // Buscamos dos tarjetas con frutas distintas
        int indice1 = 0, indice2 = -1;
        for (int i = 1; i < tablero.getTotalTarjetas(); i++) {
            if (!tablero.getTarjeta(0).getFruta().equals(tablero.getTarjeta(i).getFruta())) {
                indice2 = i;
                break;
            }
        }

        tablero.getTarjeta(indice1).voltear();
        tablero.getTarjeta(indice2).voltear();

        assertFalse(tablero.verificarPareja(indice1, indice2));
    }

    /**
     * Cuando todas las tarjetas están encontradas el juego debe terminar.
     */
    @Test
    public void juegoTerminaCuandoTodasEncontradas() {
        // Marcamos todas las tarjetas como encontradas
        for (int i = 0; i < tablero.getTotalTarjetas(); i++) {
            tablero.getTarjeta(i).marcarComoEncontrada();
        }
        assertTrue(tablero.juegoTerminado());
    }
}
