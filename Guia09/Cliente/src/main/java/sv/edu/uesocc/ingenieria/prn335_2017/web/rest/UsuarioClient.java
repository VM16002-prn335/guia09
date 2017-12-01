/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.uesocc.ingenieria.prn335_2017.web.rest;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import org.primefaces.event.SelectEvent;
import sv.edu.uesocc.ingenieria.prn335_2017.definiciones.Usuario;

/**
 *
 * @author bryan
 */
@Named(value = "usuarioBean")
@ViewScoped
public class UsuarioClient implements Serializable {

    private final Client cliente;
    private static final String BASE_URI = "http://localhost:8080/guia09-1.0-SNAPSHOT/webresources/";
    List<Usuario> listaUsaurio;
    Usuario usuarioEntity;
    boolean btnVisible = false;

    public UsuarioClient() {
        cliente = ClientBuilder.newClient();
    }
    
    /**
     * Este metodo sirve sirve para que se inicie todo despues de cargar los form.
     */
    @PostConstruct
    public void init() {
        this.usuarioEntity = new Usuario();
        llenarTabla();

    }

    /**
     * Este metodo sirve para llenar la lista que se ocupara en la vista.
     */
    public void llenarTabla() {
        reset();
        try {
            listaUsaurio = cliente
                    .target(BASE_URI)
                    .path("Usuario")
                    .request(MediaType.APPLICATION_JSON)
                    .get(new GenericType<List<Usuario>>() {
                    });
        } catch (Exception e) {
            System.out.println("ex: " + e);
        }
    }

    /**
     * Este metodo sirve para crear un nuevo registro. 
     * @return
     */
    public Usuario crearReg() {
        System.out.println("ENTRO A CREAR PRRO");
        if (usuarioEntity != null && usuarioEntity.getIdUsuario() == null) {
            try {
                Usuario salida = cliente.target(BASE_URI)
                        .path("Usuario")
                        .request(MediaType.APPLICATION_JSON)
                        .post(Entity.entity(usuarioEntity, MediaType.APPLICATION_JSON), Usuario.class);
                if (salida != null && salida.getIdUsuario() != null) {
                    llenarTabla();
                    return salida;
                }
            } catch (Exception e) {
                System.out.println("ex: " + e);
            }
        }
        return null;
    }

    /**
     * Este metodo sirve para editar un nuevo registro.
     * @return
     */
    public Usuario editarReg() {
        System.out.println("ENTRO A EDITAR PRRO");
        if (usuarioEntity != null) {
            try {
                Usuario salida = cliente.target(BASE_URI)
                        .path("Usuario")
                        .request(MediaType.APPLICATION_JSON)
                        .put(Entity.entity(usuarioEntity, MediaType.APPLICATION_JSON), Usuario.class);
                    llenarTabla();
                    return salida;
            } catch (Exception e) {
                System.out.println("ex: " + e);
            }
        }else{
            System.out.println("ALGO NULO");
        }
        return null;
    }

    /**
     * Este metodo sirve para eliminar un nuevo registro.
     * @return
     */
    public Usuario eliminarReg() {
        System.out.println("ENTRO A ELIMINAR PRRO");
        if (usuarioEntity != null) {
            try {
                Usuario salida = cliente.target(BASE_URI)
                        .path("Usuario")
                        .path(usuarioEntity.getIdUsuario().toString())
                        .request(MediaType.APPLICATION_JSON)
                        .delete(Usuario.class);
                    llenarTabla();
                    return salida;
            } catch (Exception e) {
                System.out.println("ex: " + e);
            }
        }else{
            System.out.println("ALGO NULO");
        }
        return null;
    }
    
    /**
     * Este metodo sirve para reiniciar la entity
     */
    public void reset() {
        System.out.println("ENTRO A RESET PRRO");
        usuarioEntity = new Usuario();
    }

    // <editor-fold defaultstate="collapsed" desc="Getters y Setters">
    public void onRowSelect(SelectEvent event) {
        btnVisible = true;
    }

    public void btnCancelar() {
        usuarioEntity = new Usuario();
        btnVisible = false;
    }

    public List<Usuario> getListaUsaurio() {
        return listaUsaurio;
    }

    public void setListaUsaurio(List<Usuario> listaUsaurio) {
        this.listaUsaurio = listaUsaurio;
    }

    public boolean isBtnVisible() {
        return btnVisible;
    }

    public void setBtnVisible(boolean btnVisible) {
        this.btnVisible = btnVisible;
    }

    public Usuario getUsuarioEntity() {
        return usuarioEntity;
    }

    public void setUsuarioEntity(Usuario usuarioEntity) {
        this.usuarioEntity = usuarioEntity;
    }
    // </editor-fold>

}
