package com.educapyoficial.educapy.models;

public class EducapyCalificaciones {

    //datos que voy a recojer para actualizar o borrar los nodos
    private String uid;
    private String nombre1R; //nombre
    private String gkeR;
    private String calificacion;

    public EducapyCalificaciones() {
    }

    public EducapyCalificaciones(String uid, String nombre1R, String gkeR, String calificacion) {
        this.uid = uid;
        this.nombre1R = nombre1R;
        this.gkeR = gkeR;
        this.calificacion = calificacion;
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

    public String getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(String calificacion) {
        this.calificacion = calificacion;
    }

    @Override
    public String toString() {
        return "Nombre " + nombre1R ;
    }
}
