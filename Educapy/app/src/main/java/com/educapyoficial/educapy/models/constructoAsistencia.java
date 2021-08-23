package com.educapyoficial.educapy.models;

public class constructoAsistencia {

    //datos que voy a recojer para actualizar o borrar los nodos
    private String uid;
    private String nombre1R; //nombre
    private String gkeR;
    private String parametroAsistencia; //tipo del evento
    private String fecha;

    public constructoAsistencia() {
    }


    public constructoAsistencia(String uid, String nombre1R, String gkeR, String parametroAsistencia, String fecha) {
        this.uid = uid;
        this.nombre1R = nombre1R;
        this.gkeR = gkeR;
        this.parametroAsistencia = parametroAsistencia;
        this.fecha = fecha;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getNombre1R() {
        return nombre1R;
    }

    public void setNombre1R(String nombre1R) {
        this.nombre1R = nombre1R;
    }

    public String getGkeR() {
        return gkeR;
    }

    public void setGkeR(String gkeR) {
        this.gkeR = gkeR;
    }

    public String getParametroAsistencia() {
        return parametroAsistencia;
    }

    public void setParametroAsistencia(String parametroAsistencia) {
        this.parametroAsistencia = parametroAsistencia;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Nombre " + nombre1R ;
    }
}
