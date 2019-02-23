package com.example.guga_.calculoimc;

public class Historico
{
    private double novoPeso;
    private double novoImc;
    private double novoRestante;
    private String novaData;



    public Historico(double novoPeso, double novoImc, double novoRestante, String novaData) {
        this.novoPeso = novoPeso;
        this.novoImc = novoImc;
        this.novoRestante = novoRestante;
        this.novaData = novaData;
    }

    public double getNovoPeso() {
        return novoPeso;
    }

    public double getNovoRestante() {
        return novoRestante;
    }

    public double getNovoImc() {
        return novoImc;
    }

    public String getNovaData() {
        return novaData;
    }


}
