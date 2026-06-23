package frutizap;

import frutizap.logic.Tablero;
import frutizap.model.Tarjeta;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

public class TableroExtraTest {

    private Tablero tablero;

    @BeforeEach
    public void setUp() {
        tablero = new Tablero();
    }

    @Test
    public void esSeleccionValidaFueraDeRango() {
        assertFalse(tablero.esSeleccionValida(-1));
        assertFalse(tablero.esSeleccionValida(tablero.getTotalTarjetas()));
    }

    @Test
    public void esSeleccionValidaNoDespuesDeEncontrada() {
        Tarjeta t = tablero.getTarjeta(0);
        t.marcarComoEncontrada();
        assertFalse(tablero.esSeleccionValida(0));
    }

    @Test
    public void verificarParejaMismoIndiceDevuelveFalse() {
        assertFalse(tablero.verificarPareja(0, 0));
    }
}
