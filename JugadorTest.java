import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class JugadorTest {

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
