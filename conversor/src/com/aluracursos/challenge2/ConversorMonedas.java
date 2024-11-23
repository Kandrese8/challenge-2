package com.aluracursos.challenge2;

public class ConversorMonedas {
    private String monedaOrigen;
    private String monedaDestino;
    private double cantidad;

    public ConversorMonedas(String monedaOrigen, String monedaDestino, double cantidad) {
        this.monedaOrigen = monedaOrigen;
        this.monedaDestino = monedaDestino;
        this.cantidad = cantidad;
    }

    public double realizarConversion(double tasaCambio) {
        return cantidad * tasaCambio;
    }

}
