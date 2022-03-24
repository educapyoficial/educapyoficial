package com.educapyoficial.educapy.models;

import java.io.Serializable;
import java.util.Date;

public class RegistroAnecdotario implements Serializable {

    private String uidAlumno;
    private String lugar;
    private Date fecha;
    private String actividadRealizada;
    private String conducta;
    private String comentario;
    private String recomendacion;
    private String nombreAlumno;
    private String apellidoAlumno;
    private String observador;
    private String periodo;
    private String tiempo;
    private String tiempoDesde;
    private String tiempoHasta;

    public String getUidAlumno() {
        return uidAlumno;
    }

    public void setUidAlumno(String uidAlumno) {
        this.uidAlumno = uidAlumno;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getActividadRealizada() {
        return actividadRealizada;
    }

    public void setActividadRealizada(String actividadRealizada) {
        this.actividadRealizada = actividadRealizada;
    }

    public String getConducta() {
        return conducta;
    }

    public void setConducta(String conducta) {
        this.conducta = conducta;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getRecomendacion() {
        return recomendacion;
    }

    public void setRecomendacion(String recomendacion) {
        this.recomendacion = recomendacion;
    }

    public String getNombreAlumno() {
        return nombreAlumno;
    }

    public void setNombreAlumno(String nombreAlumno) {
        this.nombreAlumno = nombreAlumno;
    }

    public String getApellidoAlumno() {
        return apellidoAlumno;
    }

    public void setApellidoAlumno(String apellidoAlumno) {
        this.apellidoAlumno = apellidoAlumno;
    }


    public String getObservador() {
        return observador;
    }

    public void setObservador(String observador) {
        this.observador = observador;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }


    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }

    public String getTiempoDesde() {
        return tiempoDesde;
    }

    public void setTiempoDesde(String tiempoDesde) {
        this.tiempoDesde = tiempoDesde;
    }

    public String getTiempoHasta() {
        return tiempoHasta;
    }

    public void setTiempoHasta(String tiempoHasta) {
        this.tiempoHasta = tiempoHasta;
    }
}
