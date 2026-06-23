package frutizap;

import frutizap.model.Fruta;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas unitarias para la clase Fruta.
 * Verifica que el objeto mantenga el nombre y las partes correctamente.
 */
public class FrutaTest {

    /**
     * Verifica que una fruta creada con nombre y partes devuelva los valores correctos.
     */
    @Test
    public void crearFrutaCorrectamente() {
        Fruta fruta = new Fruta("Manzana", 4);

        assertEquals("Manzana", fruta.getNombre());
        assertEquals(4, fruta.getPartes());
    }
}
