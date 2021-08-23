package com.educapyoficial.educapy.models;

public class constructorCalendario {

    //datos que voy a recojer para actualizar o borrar los nodos
    private String uid;
    private String nombreEvento; //nombre
    private String fechaEvento; //fecha del evento
    private String tipodeEvento; //tipo del evento
    private String fotourl; //fotografia de la pregunta

    public constructorCalendario() {
    }

    public constructorCalendario(String uid, String nombreEvento, String fechaEvento, String tipodeEvento, String fotourl) {
        this.uid = uid;
        this.nombreEvento = nombreEvento;
        this.fechaEvento = fechaEvento;
        this.tipodeEvento = tipodeEvento;
        this.fotourl = fotourl;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getNombreEvento() {
        return nombreEvento;
    }

    public void setNombreEvento(String nombreEvento) {
        this.nombreEvento = nombreEvento;
    }

    public String getFechaEvento() {
        return fechaEvento;
    }

    public void setFechaEvento(String fechaEvento) {
        this.fechaEvento = fechaEvento;
    }

    public String getTipodeEvento() {
        return tipodeEvento;
    }

    public void setTipodeEvento(String tipodeEvento) {
        this.tipodeEvento = tipodeEvento;
    }

    public String getFotourl() {
        return fotourl;
    }

    public void setFotourl(String fotourl) {
        this.fotourl = fotourl;
    }

    @Override
    public String toString() {
        return nombreEvento;
    }
}
