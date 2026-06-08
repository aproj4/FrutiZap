import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FrutaTest {

    @Test
    public void crearFrutaCorrectamente() {

        Fruta fruta = new Fruta("Manzana", 4);

        assertEquals("Manzana", fruta.getNombre());
        assertEquals(4, fruta.getPartes());
    }
}
