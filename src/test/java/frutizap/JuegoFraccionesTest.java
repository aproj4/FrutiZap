package frutizap;

import frutizap.logic.JuegoFracciones;
import frutizap.model.FraccionPregunta;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

public class JuegoFraccionesTest {

    private JuegoFracciones juego;

    @BeforeEach
    public void setUp() {
        juego = new JuegoFracciones();
        juego.agregarPregunta(new FraccionPregunta("Manzana", 1, 2, "Mitad"));
        juego.agregarPregunta(new FraccionPregunta("Naranja", 1, 4, "Cuarto"));
        juego.mezclarPreguntas();
    }

    @Test
    public void tieneSiguientePreguntaYObtencion() {
        assertTrue(juego.tieneSiguientePregunta());
        assertNotNull(juego.obtenerSiguientePregunta());
    }

    @Test
    public void validarRespuestaActualCuentaCorrectas() {
        // tomar la primera pregunta actual
        FraccionPregunta p = juego.obtenerSiguientePregunta();
        boolean correcta = juego.validarRespuestaActual(p.getNumerador(), p.getDenominador());
        assertTrue(correcta);
        assertEquals(1, juego.getRespuestasCorrectas());
    }

    @Test
    public void validarRespuestaIncorrectaNoCuenta() {
        FraccionPregunta p = juego.obtenerSiguientePregunta();
        // responder mal (1/1)
        boolean correcta = juego.validarRespuestaActual(1, 1);
        assertFalse(correcta);
        assertEquals(0, juego.getRespuestasCorrectas());
    }

    @Test
    public void obtenerSiguientePreguntaLanzaSiNoHay() {
        // consumir todas las preguntas
        while (juego.tieneSiguientePregunta()) {
            FraccionPregunta p = juego.obtenerSiguientePregunta();
            juego.validarRespuestaActual(p.getNumerador(), p.getDenominador());
        }
        assertFalse(juego.tieneSiguientePregunta());
        assertThrows(IllegalStateException.class, () -> juego.obtenerSiguientePregunta());
    }
}
