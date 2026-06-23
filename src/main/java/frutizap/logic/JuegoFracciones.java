package frutizap.logic;

import frutizap.model.FraccionPregunta;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Gestiona un conjunto de preguntas de fracciones para la fase educativa.
 */
/**
 * Gestiona un conjunto de preguntas de fracciones para la fase educativa.
 * Permite agregar preguntas, barajarlas y validar respuestas secuenciales.
 */
public class JuegoFracciones {

    private final List<FraccionPregunta> preguntas;
    private int indiceActual;
    private int respuestasCorrectas;

    /**
     * Construye una instancia vacía de preguntas.
     */
    public JuegoFracciones() {
        this.preguntas = new ArrayList<>();
        this.indiceActual = 0;
        this.respuestasCorrectas = 0;
    }

    /**
     * Construye una instancia con una lista inicial de preguntas.
     *
     * @param preguntas lista de preguntas de fracciones
     */
    public JuegoFracciones(List<FraccionPregunta> preguntas) {
        this.preguntas = new ArrayList<>(preguntas);
        this.indiceActual = 0;
        this.respuestasCorrectas = 0;
    }

    /**
     * Agrega una pregunta al conjunto.
     *
     * @param pregunta pregunta de fracciones a agregar
     */
    public void agregarPregunta(FraccionPregunta pregunta) {
        preguntas.add(pregunta);
    }

    /**
     * Mezcla aleatoriamente las preguntas y reinicia el progreso.
     */
    public void mezclarPreguntas() {
        Collections.shuffle(preguntas);
        indiceActual = 0;
        respuestasCorrectas = 0;
    }

    /**
     * Indica si aún hay preguntas sin responder.
     *
     * @return true si queda una pregunta disponible
     */
    public boolean tieneSiguientePregunta() {
        return indiceActual < preguntas.size();
    }

    /**
     * Obtiene la siguiente pregunta sin avanzar el índice.
     *
     * @return pregunta actual pendiente de responder
     * @throws IllegalStateException si no hay más preguntas disponibles
     */
    public FraccionPregunta obtenerSiguientePregunta() {
        if (!tieneSiguientePregunta()) {
            throw new IllegalStateException("No hay más preguntas disponibles.");
        }
        return preguntas.get(indiceActual);
    }

    /**
     * Valida la respuesta de la pregunta actual y avanza al siguiente ítem.
     *
     * @param numerador numerador ingresado por el jugador
     * @param denominador denominador ingresado por el jugador
     * @return true si la respuesta es correcta
     */
    public boolean validarRespuestaActual(int numerador, int denominador) {
        FraccionPregunta pregunta = obtenerSiguientePregunta();
        boolean correcta = pregunta.esRespuestaCorrecta(numerador, denominador);
        if (correcta) {
            respuestasCorrectas++;
        }
        indiceActual++;
        return correcta;
    }

    /**
     * Obtiene la cantidad de respuestas correctas acumuladas.
     *
     * @return número de respuestas correctas
     */
    public int getRespuestasCorrectas() {
        return respuestasCorrectas;
    }

    /**
     * Obtiene el número total de preguntas disponibles.
     *
     * @return cantidad de preguntas
     */
    public int getTotalPreguntas() {
        return preguntas.size();
    }

    /**
     * Reinicia el progreso del juego de preguntas de fracciones.
     */
    public void reiniciar() {
        indiceActual = 0;
        respuestasCorrectas = 0;
    }
}
