/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.ceavi.deso.trabalho.controller;

import br.udesc.ceavi.deso.trabalho.model.Cliente;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author euler
 */
@Path("clientes")
public class ClientesResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ClientesResource
     */
    public ClientesResource() {
    }

    /**
     * PUT method for updating or creating an instance of ClientesResource
     *
     * @param content representation for the resource
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Cliente putJson(Cliente cliente) throws Exception {
        ClienteJpaController c = new ClienteJpaController();
        c.create(cliente);
        return cliente;
    }
}
