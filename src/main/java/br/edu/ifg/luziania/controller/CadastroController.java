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
    public Response cadastrar(CadastrarDTO cadastrar){
        RetornoCadastroDTO retorno = new RetornoCadastroDTO();
        if(cadastrar.getEmail().equals("aninha") && cadastrar.getNome().equals("tyr") && cadastrar.getSenha().equals("123") && cadastrar.getConfisenha().equals("123")){
            retorno.setMensagem("Usuario autenticado");
            return Response.ok(retorno, MediaType.APPLICATION_JSON).build();
        }else{
            retorno.setMensagem("Usuario nao autenticado");
            return Response.ok(retorno, MediaType.APPLICATION_JSON).build();
        }
    }
}
