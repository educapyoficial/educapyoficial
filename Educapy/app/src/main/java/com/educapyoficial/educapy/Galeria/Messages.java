package com.educapyoficial.educapy.Galeria;

public class Messages {
    String titulo,urlfoto;

    public Messages() {
    }

    public Messages(String titulo, String urlfoto) {
        this.titulo = titulo;
        this.urlfoto = urlfoto;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getUrlfoto() {
        return urlfoto;
    }

    public void setUrlfoto(String urlfoto) {
        this.urlfoto = urlfoto;
    }
}
