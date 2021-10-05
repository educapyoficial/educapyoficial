package com.educapyoficial.educapy.models;

public class CursosModel {

    //datos que voy a recojer para actualizar o borrar los nodos
    private String uid;
    private String cursos; //nombre
    private String estado;
    private boolean select;

    public boolean isPrimeraVez() {
        return primeraVez;
    }

    public void setPrimeraVez(boolean primeraVez) {
        this.primeraVez = primeraVez;
    }

    private boolean primeraVez = false;

    public CursosModel() {
    }

    public CursosModel(String uid, String cursos, String estado) {
        this.uid = uid;
        this.cursos = cursos;
        this.estado = estado;
    }

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getCursos() {
        return cursos;
    }

    public void setCursos(String cursos) {
        this.cursos = cursos;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }


}
