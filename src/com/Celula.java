package com;


public class Celula {

    private int columna;

    private int renglon;

    private boolean estatus;

    public Celula(final int columna, final int renglon, final boolean estatus) {
        this.columna = columna;
        this.renglon = renglon;
        this.estatus = estatus;
    }

    public int getColumna() {
        return renglon;
    }

    public int getRenglon() {
        return columna;
    }

    public boolean isLife() {
        return estatus;
    }
}
