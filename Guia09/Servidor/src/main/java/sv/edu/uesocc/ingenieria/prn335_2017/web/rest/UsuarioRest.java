/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.uesocc.ingenieria.prn335_2017.web.rest;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import sv.edu.uesocc.ingenieria.prn335_2017.datos.acceso.UsuarioFacadeLocal;
import sv.edu.uesocc.ingenieria.prn335_2017.datos.definiciones.Usuario;

/**
 *
 * @author bryan
 */
@Path("Usuario")
public class UsuarioRest implements Serializable {

    @EJB
    UsuarioFacadeLocal usuarioFacade;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Usuario> findAll() {
        try {
            if (usuarioFacade != null) {
                return usuarioFacade.findAll();
            }
        } catch (Exception e) {
            System.out.println("ex: " + e);
        }

        return Collections.EMPTY_LIST;
    }

    @GET
    @Path("{inicio}/{tamanio}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Usuario> findRange(
            @PathParam("inicio") @DefaultValue("0") int inicio,
            @PathParam("tamanio") @DefaultValue("10") int tamanio) {
        try {
            if (usuarioFacade != null) {
                return usuarioFacade.findRange(inicio, tamanio);
            }
        } catch (Exception e) {
            System.out.println("ex: " + e);
        }
        return Collections.EMPTY_LIST;
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Usuario findById(
            @PathParam("id") int id) {
        try {
            if (usuarioFacade != null) {
                return usuarioFacade.find(id);
            }
        } catch (Exception e) {
            System.out.println("ex: " + e);
        }
        return new Usuario();
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Usuario crear(Usuario reg){
        if(reg != null && reg.getIdUsuario()== null){
            try {
                if (usuarioFacade != null) {
                    Usuario u = usuarioFacade.crear(reg);
                    if(u!=null){
                        return u;
                    }
                }
            } catch (Exception e) {
                System.out.println("ex: "+e);
            }
        }
        return new Usuario();
    }
    
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Usuario editar(Usuario reg){
        if(reg != null){
            try {
                if (usuarioFacade != null) {
                    Usuario u = usuarioFacade.editar(reg);
                    if(u!=null){
                        return u;
                    }
                }
            } catch (Exception e) {
                System.out.println("ex: "+e);
            }
        }
        return new Usuario();
    }
    
    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Usuario eliminar(@PathParam("id") int id){
        if(id > 0){
            try {
                if (usuarioFacade != null) {
                    Usuario u = usuarioFacade.remover(usuarioFacade.find(id));
                    if(u!=null){
                        return u;
                    }
                }
            } catch (Exception e) {
                System.out.println("ex: "+e);
            }
        }
        return new Usuario();
    }

}

