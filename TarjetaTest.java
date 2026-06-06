import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas unitarias para la clase Tarjeta.
 * Verifica el comportamiento del volteo, el contador de clics,
 * y el marcado de tarjetas como encontradas.
 */
public class TarjetaTest {

    /** Tarjeta usada en cada prueba */
    private Tarjeta tarjeta;

    /**
     * Se ejecuta antes de cada prueba.
     * Crea una tarjeta fresca con manzana.
     */
    @BeforeEach
    public void setUp() {
        tarjeta = new Tarjeta("🍎");
    }

    /**
     * Una tarjeta recién creada debe estar boca abajo.
     */
    @Test
    public void tarjetaIniciaOculta() {
        assertFalse(tarjeta.isEstaVolteada());
    }

    /**
     * Al voltear una tarjeta debe quedar visible.
     */
    @Test
    public void voltearDejaVisible() {
        tarjeta.voltear();
        assertTrue(tarjeta.isEstaVolteada());
    }

    /**
     * Al voltear dos veces debe volver a estar oculta.
     */
    @Test
    public void voltearDosVecesVuelveAOcultar() {
        tarjeta.voltear();
        tarjeta.voltear();
        assertFalse(tarjeta.isEstaVolteada());
    }

    /**
     * El contador debe incrementarse con cada volteo.
     */
    @Test
    public void contadorIncrementaConCadaVolteo() {
        tarjeta.voltear();
        tarjeta.voltear();
        tarjeta.voltear();
        assertEquals(3, tarjeta.getVecesVolteada());
    }

    /**
     * Una tarjeta recién creada no debe estar encontrada.
     */
    @Test
    public void tarjetaIniciaNoEncontrada() {
        assertFalse(tarjeta.isFueEncontrada());
    }

    /**
     * Al marcarla como encontrada debe quedar en ese estado.
     */
    @Test
    public void marcarComoEncontradaFunciona() {
        tarjeta.marcarComoEncontrada();
        assertTrue(tarjeta.isFueEncontrada());
    }

    /**
     * Al marcarla como encontrada también debe quedar visible.
     */
    @Test
    public void marcarComoEncontradaDejaVisible() {
        tarjeta.marcarComoEncontrada();
        assertTrue(tarjeta.isEstaVolteada());
    }

    /**
     * La fruta asignada debe ser la misma que se pasa al constructor.
     */
    @Test
    public void getFrutaRetornaFrutaCorrecta() {
        assertEquals("🍎", tarjeta.getFruta());
    }
}
