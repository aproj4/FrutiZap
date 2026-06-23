package frutizap;

import frutizap.model.FraccionPregunta;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FraccionPreguntaTest {

    @Test
    public void equivalenciaFracciones() {
        FraccionPregunta p = new FraccionPregunta("Manzana", 1, 2, "Mitad");
        assertTrue(p.esRespuestaCorrecta(2, 4)); // 2/4 es equivalente a 1/2
        assertFalse(p.esRespuestaCorrecta(3, 4));
    }

    @Test
    public void denominadorCeroEsFalso() {
        FraccionPregunta p = new FraccionPregunta("Naranja", 1, 3, "");
        assertFalse(p.esRespuestaCorrecta(1, 0));
    }

    @Test
    public void toStringContieneInformacion() {
        FraccionPregunta p = new FraccionPregunta("Uva", 3, 4, "Tres cuartos");
        String s = p.toString();
        assertTrue(s.contains("Uva"));
        assertTrue(s.contains("3/4"));
    }
}
