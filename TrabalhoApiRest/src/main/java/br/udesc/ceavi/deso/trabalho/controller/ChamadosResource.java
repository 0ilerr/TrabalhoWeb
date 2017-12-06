/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.ceavi.deso.trabalho.controller;

import br.udesc.ceavi.deso.trabalho.model.Chamado;
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
@Path("chamados")
public class ChamadosResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ChamadosResource
     */
    public ChamadosResource() {
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Chamado putJson(Chamado chamado) throws Exception {
        ChamadoJpaController c = new ChamadoJpaController();
        c.create(chamado);
        return chamado;
    }
}
