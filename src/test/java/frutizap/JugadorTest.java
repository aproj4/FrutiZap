package frutizap;

import frutizap.model.Jugador;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas unitarias para la clase Jugador.
 * Verifica la creación del jugador y la suma de puntos.
 */
public class JugadorTest {

    /**
     * Verifica que un jugador inicie con el nombre correcto y puntaje cero.
     */
    @Test
    public void crearJugadorCorrectamente() {
        Jugador jugador = new Jugador("Ana");

        assertEquals("Ana", jugador.getNombre());
        assertEquals(0, jugador.getPuntaje());
    }

    @Test
    public void sumarPuntosCorrectamente() {
        Jugador jugador = new Jugador("Ana");

        jugador.sumarPuntos(10);

        assertEquals(10, jugador.getPuntaje());
    }

    @Test
    public void sumarVariosPuntosCorrectamente() {
        Jugador jugador = new Jugador("Ana");

        jugador.sumarPuntos(10);
        jugador.sumarPuntos(15);

        assertEquals(25, jugador.getPuntaje());
    }
}
