package com;

public class JuegoVida {

    private static final boolean MUERTA = false;

    private static final boolean VIVA = true;

    public boolean[][] siguienteGeneracion(final boolean[][] viejaGeneracion) {
        boolean nuevaGeneracion[][] = new boolean[viejaGeneracion.length][viejaGeneracion[0].length];
        llenarMatriz(viejaGeneracion, nuevaGeneracion);
        return nuevaGeneracion;
    }

    private void llenarMatriz(final boolean[][] viejaGeneracion, final boolean[][] nuevaGeneracion) {
        for (int renglon = 0; renglon < viejaGeneracion.length; renglon++) {
            llenarRenglones(viejaGeneracion, nuevaGeneracion, renglon);
        }
    }

    private void llenarRenglones(final boolean[][] viejaGeneracion, final boolean[][] nuevaGeneracion,
            final int renglon) {
        for (int columna = 0; columna < viejaGeneracion[0].length; columna++) {
            nuevaGeneracion[renglon][columna] = getEstatusByColumna(viejaGeneracion, renglon, columna);
        }
    }

    private boolean getEstatusByColumna(final boolean[][] viejaGenracion, final int columna, final int renglon) {
        Celula celula = new Celula(columna, renglon, viejaGenracion[columna][renglon]);
        return getEstatusByCelula(numCelulaVivasVecinas(celula, viejaGenracion), viejaGenracion[columna][renglon]);
    }

    private boolean getEstatusByCelula(final int cantidadCelulasVivas, final boolean estatusCelula) {
        if (estatusCelula) {
            return continuaViva(cantidadCelulasVivas);
        } else {
            return nace(cantidadCelulasVivas);
        }
    }

    private boolean nace(final int cantidadCelulasVivas) {
        if (cantidadCelulasVivas == 3) {
            return true;
        }
        return false;
    }

    private boolean continuaViva(final int cantidadCelulasVivas) {
        if (cantidadCelulasVivas < 2 || cantidadCelulasVivas > 4) {
            return MUERTA;
        } else {
            return VIVA;
        }
    }

    private int numCelulaVivasVecinas(final Celula celula, final boolean[][] viejaGeneracion) {
        int vivas = 0;
        for (int r = getInicio(celula.getRenglon()); r <= getFinal(celula.getRenglon(), viejaGeneracion.length); r++) {
            vivas = numVivasPorColumna(celula, viejaGeneracion, r) + vivas;
        }
        return vivas;
    }

    private int numVivasPorColumna(final Celula celula, final boolean[][] viejaGeneracion, final int r) {
        int vivas = 0;
        for (int c = getInicio(celula.getColumna()); c <= getFinal(celula.getColumna(), viejaGeneracion[0].length); c++) {
            vivas = contarCelulasVivas(celula, viejaGeneracion, r, c) + vivas;
        }
        return vivas;
    }

    private int contarCelulasVivas(final Celula celula, final boolean[][] viejaGeneracion, final int r, final int c) {
        int vivas = 0;
        if (esCelulaVivaValida(celula, new Celula(r, c, viejaGeneracion[r][c]))) {
            vivas++;
        }
        return vivas;
    }

    private boolean esCelulaVivaValida(final Celula celula, final Celula celulaVecina) {
        return celulaVecina.isLife() && !isPosicionDeCelulaActual(celula, celulaVecina);
    }

    private boolean isPosicionDeCelulaActual(final Celula celula, final Celula celulaVecina) {
        if (celula.getRenglon() == celulaVecina.getRenglon() && celula.getColumna() == celulaVecina.getColumna()) {
            return true;
        }
        return false;
    }

    private int getFinal(final int posicion, final int length) {
        if (posicion != length - 1) {
            return (posicion + 1);
        }
        return length - 1;
    }

    private int getInicio(final int posicion) {
        if (posicion != 0) {
            return (posicion - 1);
        }
        return posicion;
    }
}
