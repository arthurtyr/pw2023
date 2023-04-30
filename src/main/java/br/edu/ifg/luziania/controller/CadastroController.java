package br.edu.ifg.luziania.controller;

import br.edu.ifg.luziania.model.dto.CadastrarDTO;
import br.edu.ifg.luziania.model.dto.RetornoCadastroDTO;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("")
public class CadastroController {

    private final Template cadastro;
    private CadastrarDTO cadastrarob;

    public CadastroController(Template cadastro){
        this.cadastro = cadastro;
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    @Path("/cadastro")
    public TemplateInstance cadastro(){
        return cadastro.instance();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/cadastrar")
    public Response cadastrar(CadastrarDTO cadastrarob){
            this.cadastrarob = cadastrarob;
            return Response.status(Response.Status.CREATED).entity(cadastrarob).build();

    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/cadastrar")
    public Response cadastros(){
        return Response.ok().entity(cadastrarob).build();

    }
}
