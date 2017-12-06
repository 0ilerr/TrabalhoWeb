/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.ceavi.deso.trabalho.controller;

import br.udesc.ceavi.deso.trabalho.model.Produto;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author euler
 */
@Path("produtos")
public class ProdutosResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ProdutosResource
     */
    public ProdutosResource() {
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Produto posttJson(Produto produto) throws Exception {
        ProdutoJpaController p = new ProdutoJpaController();
        p.create(produto);
        return produto;
    }
}
