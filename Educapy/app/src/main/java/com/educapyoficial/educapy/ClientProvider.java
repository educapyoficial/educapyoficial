package com.educapyoficial.educapy;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class ClientProvider {

    DatabaseReference mDatabase;

    public ClientProvider() {
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child("Clients"); //entra al nodo padre Users y posterior al nodo hijo Clients
    }

    public Task<Void> create(ClientM client) //envio estos parametros para que lo reciba el constructor name y email
    {
        Map<String, Object> map = new HashMap<>();
        map.put("visualizoinstieducativa4R", client.getInstitucioneducativaR());
        map.put("email4R", client.getEmailR());
        map.put("gkeR", client.getGkeR());
        map.put("espacioeduca4R", client.getEspacioeducativoOR());
        map.put("reconozcaletras4R", client.getFinalizarprescolarR());
        map.put("escribirpalasencillas4R", client.getEscribirsunombreR());
        map.put("leerpalabra4R", client.getLeerpalabraR());
        map.put("capazdecontar4R", client.getContarR());
        map.put("haselegidoinstitucion4R", client.getPaisRegister2R());
        map.put("esperasinstitucion4R", client.getPaisRegister3R());
        map.put("enquepuedelaborar4R", client.getPaisRegister4R());

        map.put("nombre1R", client.getNombre1R());
        map.put("apellidos1R", client.getApellidos1R());
        map.put("lollaman1R", client.getLollaman1R());
        map.put("lugarnacimiento1R", client.getLugarnacimiento1R());
        map.put("edad1R", client.getEdad1R());
        map.put("peso1R", client.getPeso1R());
        map.put("estatura1R", client.getEstatura1R());
        map.put("domicilio1R", client.getDomicilio1R());
        map.put("telefono1R", client.getTelefono1R());
        map.put("telefono2_1R", client.getTelefono2R());
        map.put("almacenasexo1R", client.getAlmacenasexo1R());


        map.put("nombremama2R", client.getNombremama2R());
        map.put("estudioscursamama2R", client.getEstudioscursamamaR2());
        map.put("ocupacionmama2R", client.getOcupacionmama2R());
        map.put("nacimientomama2R", client.getNacimientomama2R());
        map.put("telefonomama2R", client.getTelefonomama2R());
        map.put("telefonomovil2R", client.getTelefonomovilmama2R());
        map.put("horariomama2R", client.getHorariomama2R());
        map.put("nombrepapa2R", client.getNombrepapa2R());
        map.put("telefono2R", client.getTelefono1R());
        map.put("estudiospapa2R", client.getEstudiospapa2R());
        map.put("ocupacion2R", client.getOcupacionpapa2R());
        map.put("lugartrabajopapa2R", client.getLugartrabajopapa2R());
        map.put("telefonocasapapa2R", client.getTelefonocasapapa2R());
        map.put("telefonocelularpapa2R", client.getTelefonocelularpapa2R());
        map.put("horariotrabajopapa2R", client.getHorariotrabajopapa2R());
        map.put("numeropersonashogar2R", client.getNumeropersonashogar2R());
        map.put("cuantoshermanos2R", client.getCuantoshermanos2R());
        map.put("lugarocupa2R", client.getLugarocupa2R());
        map.put("vivecon2R", client.getVivecon2R());
        map.put("relacionmama2R", client.getRelacionmama2R());
        map.put("relacionpapa2R", client.getRelacionpapa2R());
        map.put("relacionhermano2R", client.getRelacionhermano2R());

        map.put("comofueembarazo3R", client.getComofueembarazo3R());
        map.put("comofueparto3R", client.getComofueparto3R());
        map.put("destete3R", client.getDestete3R());
        map.put("lechematerna3R", client.getLechematerna3R());
        map.put("primeraspalabras3R", client.getPrimeraspalabras3R());
        map.put("legustacomer3R", client.getLegustacomer3R());
        map.put("familiarcomejun3R", client.getFamiliarcomejun3R());
        map.put("duermebien3R", client.getDuermebien3R());
        map.put("cuandolesalieron3R", client.getCuandolesalieron3R());
        map.put("sanosusdientes3R", client.getSanosusdientes3R());
        map.put("controlesfinteres3R", client.getControlesfinteres3R());
        map.put("pronunciabien3R", client.getPronunciabien3R());
        map.put("comprendeloquedice3R", client.getComprendeloquedice3R());
        map.put("comosecomporta3R", client.getComosecomporta3R());
        map.put("comosecomportacalle3R", client.getComosecomportacalle3R());
        map.put("actividadescotidianas3R", client.getActividadescotidianas3R());
        map.put("sevistesolo3R", client.getSevistesolo3R());
        map.put("legustaquehacer3R", client.getLegustaquehacer3R());
        map.put("idgruR", client.getIdgruR());
        map.put("calificacion1R", client.getCalificacionR());


        return mDatabase.child(client.getId()).setValue(map);
    }
}
