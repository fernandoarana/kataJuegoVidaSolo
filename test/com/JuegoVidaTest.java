package com;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;


public class JuegoVidaTest {

    private static final boolean VIVA = true;

    private static final boolean MUERTA = false;

    private JuegoVida juegoVida = new JuegoVida();
    
    private int numCiclos = 0;

    @Before
    public void setUp() throws Exception {
        numCiclos = 0;
    }

    @Test
    public void todoEstaMuerto() {
        boolean viejaGeneracion[][] = new boolean[][] { { MUERTA, MUERTA, MUERTA }, { MUERTA, MUERTA, MUERTA } };
        boolean nuevaGeneracion[][] = juegoVida.siguienteGeneracion(viejaGeneracion);
        evaluarGeneracionRespuesta2x2(nuevaGeneracion, viejaGeneracion);
    }

    @Test
    public void todoEstanVivos_entocesSoloEsquinasViven() {
        boolean viejaGeneracion[][] = new boolean[][] { { VIVA, VIVA, VIVA }, { VIVA, VIVA, VIVA } };
        boolean nuevaGeneracion[][] = juegoVida.siguienteGeneracion(viejaGeneracion);
        boolean generacionEsperada[][] = new boolean[][] { { VIVA, MUERTA, VIVA }, { VIVA, MUERTA, VIVA } };
        evaluarGeneracionRespuesta2x2(nuevaGeneracion, generacionEsperada);
    }

    @Test
    public void todoEstanMuertosMenosDosJuntos_entocesMueren() {
        boolean viejaGeneracion[][] = new boolean[][] { { VIVA, VIVA, MUERTA }, { MUERTA, MUERTA, MUERTA } };
        boolean nuevaGeneracion[][] = juegoVida.siguienteGeneracion(viejaGeneracion);
        boolean generacionEsperada[][] = new boolean[][] { { MUERTA, MUERTA, MUERTA }, { MUERTA, MUERTA, MUERTA } };
        evaluarGeneracionRespuesta2x2(nuevaGeneracion, generacionEsperada);
    }

    @Test
    public void todoEstanMuertosMenosUno_entocesMuere() {
        boolean viejaGeneracion[][] = new boolean[][] { { VIVA, MUERTA, MUERTA }, { MUERTA, MUERTA, MUERTA } };
        boolean nuevaGeneracion[][] = juegoVida.siguienteGeneracion(viejaGeneracion);
        boolean generacionEsperada[][] = new boolean[][] { { MUERTA, MUERTA, MUERTA }, { MUERTA, MUERTA, MUERTA } };
        evaluarGeneracionRespuesta2x2(nuevaGeneracion, generacionEsperada);
    }

    @Test
    public void tresVivas_entocesNaceUnaYMuerenOtras() {
        boolean viejaGeneracion[][] = new boolean[][] { { VIVA, VIVA, VIVA }, { MUERTA, MUERTA, MUERTA } };
        boolean nuevaGeneracion[][] = juegoVida.siguienteGeneracion(viejaGeneracion);
        boolean generacionEsperada[][] = new boolean[][] { { MUERTA, VIVA, MUERTA }, { MUERTA, VIVA, MUERTA } };
        evaluarGeneracionRespuesta2x2(nuevaGeneracion, generacionEsperada);
    }

    private void evaluarGeneracionRespuesta2x2(final boolean[][] nuevaGeneracion, final boolean[][] generacionEsperada) {
        assertTrue(Arrays.equals(nuevaGeneracion[0], generacionEsperada[0]));
        assertTrue(Arrays.equals(nuevaGeneracion[1], generacionEsperada[1]));
    }

    @Test
    public void patronCruzInfinita() {
        boolean generacion1[][] = new boolean[][] { { MUERTA, VIVA, MUERTA }, { MUERTA, VIVA, MUERTA },
                { MUERTA, VIVA, MUERTA } };
        boolean generacion2[][] = new boolean[][] { { MUERTA, MUERTA, MUERTA }, { VIVA, VIVA, VIVA },
                { MUERTA, MUERTA, MUERTA } };
        evaluarG(generacion1, generacion2);
    }

    @Test
    public void patronCuboInfinito() {
        boolean generacion1[][] = new boolean[][] { { MUERTA, MUERTA, MUERTA, MUERTA },
                { MUERTA, VIVA, VIVA, MUERTA }, { MUERTA, VIVA, VIVA, MUERTA }, { MUERTA, MUERTA, MUERTA, MUERTA } };
        evaluarG(generacion1, generacion1);
    }

    private void evaluarG(final boolean[][] generacionInicial, final boolean[][] generacionEsperada) {
        boolean[][] generacionResultante = juegoVida.siguienteGeneracion(generacionInicial);
        evaluarGeneracionRespuesta3x3(generacionResultante, generacionEsperada);
        if (numCiclos < 10) {
            numCiclos++;
            evaluarG(generacionInicial, generacionResultante);
        }
    }

    private void evaluarGeneracionRespuesta3x3(final boolean[][] nuevaGeneracion, final boolean[][] generacion1) {
        assertTrue(Arrays.equals(nuevaGeneracion[0], generacion1[0]));
        assertTrue(Arrays.equals(nuevaGeneracion[1], generacion1[1]));
        assertTrue(Arrays.equals(nuevaGeneracion[2], generacion1[2]));
    }
}
