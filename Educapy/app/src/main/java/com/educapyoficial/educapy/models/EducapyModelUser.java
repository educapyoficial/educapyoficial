package com.educapyoficial.educapy.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class EducapyModelUser implements Serializable {

    //datos que voy a recojer para actualizar o borrar los nodos
    String id;
    String nombre1R;
    String emailR;
    String foto;
    String idgruR;
    String apellidos1R;
    String lollaman1R;
    String lugarnacimiento1R;
    String nacimientomama2R;
    String edad1R;
    String peso1R;
    String estatura1R;
    String domicilio1R; //aqui va bien
    String telefono1R;
    String almacenasexo1R;
    String calificacionR;
    String gkeR;
    String institucioneducativaR;
    String espacioeducativoOR;
    String finalizarprescolarR;
    String escribirsunombreR;
    String leerpalabraR;
    String contarR; //aqui va bien
    String paisRegister2R;
    String paisRegister3R;
    String paisRegister4R;
    String telefono2R;
    String nombremama2R;
    String estudioscursamamaR2;
    String ocupacionmama2R;
    String telefonomama2R;
    String telefonomovilmama2R; //va bien
    String horariomama2R;
    String nombrepapa2R;
    String estudiospapa2R;
    String ocupacionpapa2R;
    String lugartrabajopapa2R;
    String telefonocasapapa2R;
    String telefonocelularpapa2R; //va bien
    String horariotrabajopapa2R;
    String numeropersonashogar2R;
    String cuantoshermanos2R;
    String lugarocupa2R;
    String vivecon2R; //va bien
    String relacionmama2R;
    String relacionpapa2R;
    String relacionhermano2R;
    String comofueembarazo3R;
    String comofueparto3R;
    String destete3R;
    String lechematerna3R; //va bien
    String primeraspalabras3R;
    String legustacomer3R;
    String familiarcomejun3R;
    String duermebien3R;
    String cuandolesalieron3R;
    String sanosusdientes3R;
    String controlesfinteres3R; //va bien
    String pronunciabien3R;
    String comprendeloquedice3R;
    String comosecomporta3R;
    String comosecomportacalle3R;
    String actividadescotidianas3R;
    String sevistesolo3R; //va bien
    String legustaquehacer3R;
    private String uid;
    private String nombreUsuario;
    private String apellidoUsuario;
    private String nombre;
    private String uidfirebase;
    String relacionotros2T;

    private String calificacion1R;
    String uidProfesor;

    String estado;
    private String tokenFirebase;
    private String uidCurso;

    private EvaluacionIndicadores evaluacionIndicadores;

    private List<EvaluacionIndicadores> evaluacionIndicadoresList = new ArrayList<>();


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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getApellidoUsuario() {
        return apellidoUsuario;
    }

    public void setApellidoUsuario(String apellidoUsuario) {
        this.apellidoUsuario = apellidoUsuario;
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

    public String getNombremama2R() {
        return nombremama2R;
    }

    public void setNombremama2R(String nombremama2R) {
        this.nombremama2R = nombremama2R;
    }

    public String getNombrepapa2R() {
        return nombrepapa2R;
    }

    public void setNombrepapa2R(String nombrepapa2R) {
        this.nombrepapa2R = nombrepapa2R;
    }

    public String getGkeR() {
        return gkeR;
    }

    public void setGkeR(String gkeR) {
        this.gkeR = gkeR;
    }

    public String getApellidos1R() {
        return apellidos1R;
    }

    public void setApellidos1R(String apellidos1R) {
        this.apellidos1R = apellidos1R;
    }

    public String getEmailR() {
        return emailR;
    }

    public void setEmailR(String emailR) {
        this.emailR = emailR;
    }

    public String getCalificacion1R() {
        return calificacion1R;
    }

    public void setCalificacion1R(String calificacion1R) {
        this.calificacion1R = calificacion1R;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getIdgruR() {
        return idgruR;
    }

    public void setIdgruR(String idgruR) {
        this.idgruR = idgruR;
    }

    public String getLollaman1R() {
        return lollaman1R;
    }

    public void setLollaman1R(String lollaman1R) {
        this.lollaman1R = lollaman1R;
    }

    public String getLugarnacimiento1R() {
        return lugarnacimiento1R;
    }

    public void setLugarnacimiento1R(String lugarnacimiento1R) {
        this.lugarnacimiento1R = lugarnacimiento1R;
    }

    public String getEdad1R() {
        return edad1R;
    }

    public void setEdad1R(String edad1R) {
        this.edad1R = edad1R;
    }

    public String getPeso1R() {
        return peso1R;
    }

    public void setPeso1R(String peso1R) {
        this.peso1R = peso1R;
    }

    public String getEstatura1R() {
        return estatura1R;
    }

    public void setEstatura1R(String estatura1R) {
        this.estatura1R = estatura1R;
    }

    public String getDomicilio1R() {
        return domicilio1R;
    }

    public void setDomicilio1R(String domicilio1R) {
        this.domicilio1R = domicilio1R;
    }

    public String getTelefono1R() {
        return telefono1R;
    }

    public void setTelefono1R(String telefono1R) {
        this.telefono1R = telefono1R;
    }

    public String getAlmacenasexo1R() {
        return almacenasexo1R;
    }

    public void setAlmacenasexo1R(String almacenasexo1R) {
        this.almacenasexo1R = almacenasexo1R;
    }

    public String getCalificacionR() {
        return calificacionR;
    }

    public void setCalificacionR(String calificacionR) {
        this.calificacionR = calificacionR;
    }

    public String getInstitucioneducativaR() {
        return institucioneducativaR;
    }

    public void setInstitucioneducativaR(String institucioneducativaR) {
        this.institucioneducativaR = institucioneducativaR;
    }

    public String getEspacioeducativoOR() {
        return espacioeducativoOR;
    }

    public void setEspacioeducativoOR(String espacioeducativoOR) {
        this.espacioeducativoOR = espacioeducativoOR;
    }

    public String getFinalizarprescolarR() {
        return finalizarprescolarR;
    }

    public void setFinalizarprescolarR(String finalizarprescolarR) {
        this.finalizarprescolarR = finalizarprescolarR;
    }

    public String getEscribirsunombreR() {
        return escribirsunombreR;
    }

    public void setEscribirsunombreR(String escribirsunombreR) {
        this.escribirsunombreR = escribirsunombreR;
    }

    public String getLeerpalabraR() {
        return leerpalabraR;
    }

    public void setLeerpalabraR(String leerpalabraR) {
        this.leerpalabraR = leerpalabraR;
    }

    public String getContarR() {
        return contarR;
    }

    public void setContarR(String contarR) {
        this.contarR = contarR;
    }

    public String getPaisRegister2R() {
        return paisRegister2R;
    }

    public void setPaisRegister2R(String paisRegister2R) {
        this.paisRegister2R = paisRegister2R;
    }

    public String getPaisRegister3R() {
        return paisRegister3R;
    }

    public void setPaisRegister3R(String paisRegister3R) {
        this.paisRegister3R = paisRegister3R;
    }

    public String getPaisRegister4R() {
        return paisRegister4R;
    }

    public void setPaisRegister4R(String paisRegister4R) {
        this.paisRegister4R = paisRegister4R;
    }

    public String getTelefono2R() {
        return telefono2R;
    }

    public void setTelefono2R(String telefono2R) {
        this.telefono2R = telefono2R;
    }

    public String getEstudioscursamamaR2() {
        return estudioscursamamaR2;
    }

    public void setEstudioscursamamaR2(String estudioscursamamaR2) {
        this.estudioscursamamaR2 = estudioscursamamaR2;
    }

    public String getOcupacionmama2R() {
        return ocupacionmama2R;
    }

    public void setOcupacionmama2R(String ocupacionmama2R) {
        this.ocupacionmama2R = ocupacionmama2R;
    }

    public String getNacimientomama2R() {
        return nacimientomama2R;
    }

    public void setNacimientomama2R(String nacimientomama2R) {
        this.nacimientomama2R = nacimientomama2R;
    }

    public String getTelefonomama2R() {
        return telefonomama2R;
    }

    public void setTelefonomama2R(String telefonomama2R) {
        this.telefonomama2R = telefonomama2R;
    }

    public String getTelefonomovilmama2R() {
        return telefonomovilmama2R;
    }

    public void setTelefonomovilmama2R(String telefonomovilmama2R) {
        this.telefonomovilmama2R = telefonomovilmama2R;
    }

    public String getHorariomama2R() {
        return horariomama2R;
    }

    public void setHorariomama2R(String horariomama2R) {
        this.horariomama2R = horariomama2R;
    }

    public String getEstudiospapa2R() {
        return estudiospapa2R;
    }

    public void setEstudiospapa2R(String estudiospapa2R) {
        this.estudiospapa2R = estudiospapa2R;
    }

    public String getOcupacionpapa2R() {
        return ocupacionpapa2R;
    }

    public void setOcupacionpapa2R(String ocupacionpapa2R) {
        this.ocupacionpapa2R = ocupacionpapa2R;
    }

    public String getLugartrabajopapa2R() {
        return lugartrabajopapa2R;
    }

    public void setLugartrabajopapa2R(String lugartrabajopapa2R) {
        this.lugartrabajopapa2R = lugartrabajopapa2R;
    }

    public String getTelefonocasapapa2R() {
        return telefonocasapapa2R;
    }

    public void setTelefonocasapapa2R(String telefonocasapapa2R) {
        this.telefonocasapapa2R = telefonocasapapa2R;
    }

    public String getTelefonocelularpapa2R() {
        return telefonocelularpapa2R;
    }

    public void setTelefonocelularpapa2R(String telefonocelularpapa2R) {
        this.telefonocelularpapa2R = telefonocelularpapa2R;
    }

    public String getHorariotrabajopapa2R() {
        return horariotrabajopapa2R;
    }

    public void setHorariotrabajopapa2R(String horariotrabajopapa2R) {
        this.horariotrabajopapa2R = horariotrabajopapa2R;
    }

    public String getNumeropersonashogar2R() {
        return numeropersonashogar2R;
    }

    public void setNumeropersonashogar2R(String numeropersonashogar2R) {
        this.numeropersonashogar2R = numeropersonashogar2R;
    }

    public String getCuantoshermanos2R() {
        return cuantoshermanos2R;
    }

    public void setCuantoshermanos2R(String cuantoshermanos2R) {
        this.cuantoshermanos2R = cuantoshermanos2R;
    }

    public String getLugarocupa2R() {
        return lugarocupa2R;
    }

    public void setLugarocupa2R(String lugarocupa2R) {
        this.lugarocupa2R = lugarocupa2R;
    }

    public String getVivecon2R() {
        return vivecon2R;
    }

    public void setVivecon2R(String vivecon2R) {
        this.vivecon2R = vivecon2R;
    }

    public String getRelacionmama2R() {
        return relacionmama2R;
    }

    public void setRelacionmama2R(String relacionmama2R) {
        this.relacionmama2R = relacionmama2R;
    }

    public String getRelacionpapa2R() {
        return relacionpapa2R;
    }

    public void setRelacionpapa2R(String relacionpapa2R) {
        this.relacionpapa2R = relacionpapa2R;
    }

    public String getRelacionhermano2R() {
        return relacionhermano2R;
    }

    public void setRelacionhermano2R(String relacionhermano2R) {
        this.relacionhermano2R = relacionhermano2R;
    }

    public String getComofueembarazo3R() {
        return comofueembarazo3R;
    }

    public void setComofueembarazo3R(String comofueembarazo3R) {
        this.comofueembarazo3R = comofueembarazo3R;
    }

    public String getComofueparto3R() {
        return comofueparto3R;
    }

    public void setComofueparto3R(String comofueparto3R) {
        this.comofueparto3R = comofueparto3R;
    }

    public String getDestete3R() {
        return destete3R;
    }

    public void setDestete3R(String destete3R) {
        this.destete3R = destete3R;
    }

    public String getLechematerna3R() {
        return lechematerna3R;
    }

    public void setLechematerna3R(String lechematerna3R) {
        this.lechematerna3R = lechematerna3R;
    }

    public String getPrimeraspalabras3R() {
        return primeraspalabras3R;
    }

    public void setPrimeraspalabras3R(String primeraspalabras3R) {
        this.primeraspalabras3R = primeraspalabras3R;
    }

    public String getLegustacomer3R() {
        return legustacomer3R;
    }

    public void setLegustacomer3R(String legustacomer3R) {
        this.legustacomer3R = legustacomer3R;
    }

    public String getFamiliarcomejun3R() {
        return familiarcomejun3R;
    }

    public void setFamiliarcomejun3R(String familiarcomejun3R) {
        this.familiarcomejun3R = familiarcomejun3R;
    }

    public String getDuermebien3R() {
        return duermebien3R;
    }

    public void setDuermebien3R(String duermebien3R) {
        this.duermebien3R = duermebien3R;
    }

    public String getCuandolesalieron3R() {
        return cuandolesalieron3R;
    }

    public void setCuandolesalieron3R(String cuandolesalieron3R) {
        this.cuandolesalieron3R = cuandolesalieron3R;
    }

    public String getSanosusdientes3R() {
        return sanosusdientes3R;
    }

    public void setSanosusdientes3R(String sanosusdientes3R) {
        this.sanosusdientes3R = sanosusdientes3R;
    }

    public String getControlesfinteres3R() {
        return controlesfinteres3R;
    }

    public void setControlesfinteres3R(String controlesfinteres3R) {
        this.controlesfinteres3R = controlesfinteres3R;
    }

    public String getPronunciabien3R() {
        return pronunciabien3R;
    }

    public void setPronunciabien3R(String pronunciabien3R) {
        this.pronunciabien3R = pronunciabien3R;
    }

    public String getComprendeloquedice3R() {
        return comprendeloquedice3R;
    }

    public void setComprendeloquedice3R(String comprendeloquedice3R) {
        this.comprendeloquedice3R = comprendeloquedice3R;
    }

    public String getComosecomporta3R() {
        return comosecomporta3R;
    }

    public void setComosecomporta3R(String comosecomporta3R) {
        this.comosecomporta3R = comosecomporta3R;
    }

    public String getComosecomportacalle3R() {
        return comosecomportacalle3R;
    }

    public void setComosecomportacalle3R(String comosecomportacalle3R) {
        this.comosecomportacalle3R = comosecomportacalle3R;
    }

    public String getActividadescotidianas3R() {
        return actividadescotidianas3R;
    }

    public void setActividadescotidianas3R(String actividadescotidianas3R) {
        this.actividadescotidianas3R = actividadescotidianas3R;
    }

    public String getSevistesolo3R() {
        return sevistesolo3R;
    }

    public void setSevistesolo3R(String sevistesolo3R) {
        this.sevistesolo3R = sevistesolo3R;
    }

    public String getLegustaquehacer3R() {
        return legustaquehacer3R;
    }

    public void setLegustaquehacer3R(String legustaquehacer3R) {
        this.legustaquehacer3R = legustaquehacer3R;
    }

    public String getUidProfesor() {
        return uidProfesor;
    }

    public void setUidProfesor(String uidProfesor) {
        this.uidProfesor = uidProfesor;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }


    public String getRelacionotros2T() {
        return relacionotros2T;
    }

    public void setRelacionotros2T(String relacionotros2T) {
        this.relacionotros2T = relacionotros2T;
    }

    public String getTokenFirebase() {
        return tokenFirebase;
    }

    public void setTokenFirebase(String tokenFirebase) {
        this.tokenFirebase = tokenFirebase;
    }

    public EvaluacionIndicadores getEvaluacionIndicadores() {
        return evaluacionIndicadores;
    }

    public void setEvaluacionIndicadores(EvaluacionIndicadores evaluacionIndicadores) {
        this.evaluacionIndicadores = evaluacionIndicadores;
    }

    public List<EvaluacionIndicadores> getEvaluacionIndicadoresList() {
        return evaluacionIndicadoresList;
    }

    public void setEvaluacionIndicadoresList(List<EvaluacionIndicadores> evaluacionIndicadoresList) {
        this.evaluacionIndicadoresList = evaluacionIndicadoresList;
    }

    @Override
    public String toString() {
        return "Nombre Padre Responsable " + (nombreUsuario != null && !nombreUsuario.equals("") ? "\n" + nombreUsuario : "\n" + nombre)
                + "\nNombre Hijo/a " + "\n" + nombre1R
                + "\nEmail " + "\n" + emailR;
    }
}
