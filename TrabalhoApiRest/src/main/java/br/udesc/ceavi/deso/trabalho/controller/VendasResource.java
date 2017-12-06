/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.ceavi.deso.trabalho.controller;

import br.udesc.ceavi.deso.trabalho.model.Venda;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author euler
 */
@Path("vendas")
public class VendasResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of VendasResource
     */
    public VendasResource() {
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Venda postJson(Venda venda) throws Exception {
        VendaJpaController v = new VendaJpaController();
        v.create(venda);
        return venda;
    }
}
