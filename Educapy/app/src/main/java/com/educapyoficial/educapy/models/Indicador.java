package com.educapyoficial.educapy.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Indicador implements Serializable {

    private String recomendacion;
    private String valor;
    private ArrayList<Opcion> opcionList = new ArrayList<>();

    public Indicador() {

    }

    public Indicador(String recomendacion, String valor) {
        this.recomendacion = recomendacion;
        this.valor = valor;
    }


    private String opcion1;
    private String opcion2;
    private String opcion3;
    private String opcion4;
    private String opcion5;


    public String getRecomendacion() {
        return recomendacion;
    }

    public void setRecomendacion(String recomendacion) {
        this.recomendacion = recomendacion;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getOpcion1() {
        return opcion1;
    }

    public void setOpcion1(String opcion1) {
        this.opcion1 = opcion1;
    }

    public String getOpcion2() {
        return opcion2;
    }

    public void setOpcion2(String opcion2) {
        this.opcion2 = opcion2;
    }

    public String getOpcion3() {
        return opcion3;
    }

    public void setOpcion3(String opcion3) {
        this.opcion3 = opcion3;
    }

    public String getOpcion4() {
        return opcion4;
    }

    public void setOpcion4(String opcion4) {
        this.opcion4 = opcion4;
    }

    public String getOpcion5() {
        return opcion5;
    }

    public void setOpcion5(String opcion5) {
        this.opcion5 = opcion5;
    }


    public ArrayList<Opcion> getOpcionList() {
        return opcionList;
    }

    public void setOpcionList(ArrayList<Opcion> opcionList) {
        this.opcionList = opcionList;
    }

}
