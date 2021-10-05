package com.educapyoficial.educapy.models;

import java.util.List;

public class EducapyModelUserProfesor {

    //datos que voy a recojer para actualizar o borrar los nodos
    private String uid;
    private String correo; //nombre
    private String es_profesor; //nombre
    private String nombre; //fotografia de la pregunta
    private String gkeR;
    private String nombreUsuario;
    private String uidfirebase;
    private String estado;
    private String uidCurso;
    private List<String> uidCursosList;

    public EducapyModelUserProfesor() {
    }

    public EducapyModelUserProfesor(String uid, String correo, String es_profesor, String nombre, String gkeR) {
        this.uid = uid;
        this.correo = correo;
        this.es_profesor = es_profesor;
        this.nombre = nombre;
        this.gkeR = gkeR;
    }


    public String getUidCurso() {
        return uidCurso;
    }

    public void setUidCurso(String uidCurso) {
        this.uidCurso = uidCurso;
    }

    public String getUidfirebase() {
        return uidfirebase;
    }

    public void setUidfirebase(String uidfirebase) {
        this.uidfirebase = uidfirebase;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getEs_profesor() {
        return es_profesor;
    }

    public void setEs_profesor(String es_profesor) {
        this.es_profesor = es_profesor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGkeR() {
        return gkeR;
    }

    public void setGkeR(String gkeR) {
        this.gkeR = gkeR;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }


    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<String> getUidCursosList() {
        return uidCursosList;
    }

    public void setUidCursosList(List<String> uidCursosList) {
        this.uidCursosList = uidCursosList;
    }


    @Override
    public String toString() {
        return "Nombre " + nombre;
    }
}
