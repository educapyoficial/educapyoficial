package com.educapyoficial.educapy.models;

import java.io.Serializable;
import java.util.Objects;

public class Opcion implements Comparable<Opcion> {

    private String id;
    private String valor;
    public int orden;

    public Opcion() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return valor.trim();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Opcion opcion = (Opcion) o;
        return valor.equals(opcion.valor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(valor);
    }

    @Override
    public int compareTo(Opcion e) {
        if (e.orden < orden) {
            return -1;
        } else if (e.orden != orden) {
            return 0;
        } else {
            return 1;
        }
    }
}
