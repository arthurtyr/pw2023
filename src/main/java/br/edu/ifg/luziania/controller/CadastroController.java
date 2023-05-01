package br.edu.ifg.luziania.controller;

import br.edu.ifg.luziania.model.dto.CadastrarDTO;
import br.edu.ifg.luziania.model.dto.RetornoDTO;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("")
public class CadastroController {

    private final Template cadastro;
    private CadastrarDTO cadastrarob;
    private RetornoDTO mensagem;
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
        RetornoDTO retorno = new RetornoDTO();
        if(cadastrarob.getEmail().equals("aninha@gmail.com")){
            retorno.setMensagem("utilizado");
        }else{
            retorno.setMensagem("cadastrado");
            this.cadastrarob = cadastrarob;
        }
        this.mensagem = retorno;
        return Response.status(Response.Status.CREATED).entity(retorno).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/cadastrar")
    public Response cadastros(){
        if(mensagem.getMensagem().equals("cadastrado")){
            return Response.ok().entity(cadastrarob).build();
        }else{
            return Response.ok(mensagem, MediaType.APPLICATION_JSON).build();
        }
    }
}
