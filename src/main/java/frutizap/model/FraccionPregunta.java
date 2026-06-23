package frutizap.model;

/**
 * Representa una pregunta de fracciones basada en una fruta.
 * Ejemplo: "¿Qué fracción representa 1/2 de una manzana?".
 */
/**
 * Representa una pregunta de fracciones basada en una fruta.
 * Se utiliza para la fase de actividades de fracciones del juego.
 */
public class FraccionPregunta {

    private final String fruta;
    private final int numerador;
    private final int denominador;
    private final String descripcion;

    /**
     * Construye una pregunta de fracciones.
     *
     * @param fruta      nombre de la fruta asociada a la pregunta
     * @param numerador numerador de la fracción correcta
     * @param denominador denominador de la fracción correcta
     * @param descripcion descripción de la pregunta
     */
    public FraccionPregunta(String fruta, int numerador, int denominador, String descripcion) {
        this.fruta = fruta;
        this.numerador = numerador;
        this.denominador = denominador;
        this.descripcion = descripcion;
    }

    /**
     * Obtiene el nombre de la fruta asociada a la pregunta.
     *
     * @return nombre de la fruta
     */
    public String getFruta() {
        return fruta;
    }

    /**
     * Obtiene el numerador de la fracción correcta.
     *
     * @return numerador correcto
     */
    public int getNumerador() {
        return numerador;
    }

    /**
     * Obtiene el denominador de la fracción correcta.
     *
     * @return denominador correcto
     */
    public int getDenominador() {
        return denominador;
    }

    /**
     * Obtiene la descripción de la pregunta.
     *
     * @return texto descriptivo de la pregunta
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Obtiene la fracción correcta como texto.
     *
     * @return fracción en formato "numerador/denominador"
     */
    public String getFraccionTexto() {
        return numerador + "/" + denominador;
    }

    /**
     * Valida una respuesta numérica contra esta pregunta.
     *
     * @param respuestaNumerador numerador de la respuesta
     * @param respuestaDenominador denominador de la respuesta
     * @return true si la respuesta coincide con la fracción esperada
     */
    public boolean esRespuestaCorrecta(int respuestaNumerador, int respuestaDenominador) {
        if (respuestaDenominador == 0) {
            return false;
        }
        return numerador * respuestaDenominador == respuestaNumerador * denominador;
    }

    /**
     * Representación de la pregunta como texto.
     *
     * @return descripción compacta de la pregunta y la fracción correcta
     */
    @Override
    public String toString() {
        return "Fracción de " + fruta + ": " + getFraccionTexto() + " - " + descripcion;
    }
}
