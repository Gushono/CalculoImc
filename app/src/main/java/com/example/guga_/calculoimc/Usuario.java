package com.example.guga_.calculoimc;

public class Usuario {

    private double pesoInicial;
    private double pesoAtual;
    private double pesoIdeal;
    private double pesoRestante;
    private double altura;
    private double imc;
    private String data;


    public Usuario(double pesoInicial, double pesoAtual, double pesoIdeal, double pesoRestante, double altura, double imc, String data){

        this.pesoInicial = pesoInicial;
        this.pesoAtual = pesoAtual;
        this.pesoIdeal = pesoIdeal;
        this.pesoRestante = pesoRestante;
        this.altura = altura;
        this.imc = imc;
        this.data = data;

    }


    public double getPesoInicial() {
        return pesoInicial;
    }

    public double getPesoAtual() {
        return pesoAtual;
    }

    public double getPesoIdeal() {
        return pesoIdeal;
    }

    public double getPesoRestante() {
        return pesoRestante;
    }

    public double getAltura() {
        return altura;
    }

    public double getImc() {
        return imc;
    }

    public String getData() {
        return data;
    }




}
