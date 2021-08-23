package com.educapyoficial.educapy.models;

import java.util.UUID;

public class constructoAsistenciaRevision {

    //datos que voy a recojer para actualizar o borrar los nodos
    private String uid;
    private String nombre1R; //nombre
    private String gkeR;
    private String parametroAsistencia; //tipo del evento
    private String fechaId;

    public constructoAsistenciaRevision() {
    }

    public constructoAsistenciaRevision(String uid, String nombre1R, String gkeR, String parametroAsistencia, String fechaId) {
        this.uid = uid;
        this.nombre1R = nombre1R;
        this.gkeR = gkeR;
        this.parametroAsistencia = parametroAsistencia;
        this.fechaId = fechaId;
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

    public String getFechaId() {
        return fechaId;
    }

    public void setFechaId(String fechaId) {
        this.fechaId = fechaId;
    }

    @Override
    public String toString() {
        return "Fecha " + fechaId;
    }
}
