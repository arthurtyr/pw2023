package br.edu.ifg.luziania.controller;

import br.edu.ifg.luziania.model.dto.PerfilDTO;
import br.edu.ifg.luziania.model.dto.RetornoDTO;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
@Path("")
public class PerfilController {

    private final Template perfil;

    private PerfilDTO perfilob;

    private RetornoDTO mensagem;

    public PerfilController(Template perfil){
        this.perfil = perfil;
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    @Path("/perfil")
    public TemplateInstance perfil(){
        return perfil.instance();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/criarperfil")
    public Response criarperfil(PerfilDTO perfil) {
        RetornoDTO retorno = new RetornoDTO();
        if (perfil.getNomeperfil().equals("aninha")){
            retorno.setMensagem("Utilizado");
        }else{
            retorno.setMensagem("Cadastrado");
            this.perfilob = perfil;
        }
        this.mensagem = retorno;
        return Response.status(Response.Status.CREATED).entity(retorno).build();
    }

    @GET
    @Produces
    @Path("/criarperfil")
    public Response perfis(){
        if (mensagem.getMensagem().equals("Cadastrado")){
            return Response.ok().entity(perfilob).build();
        }else {
            return Response.ok(mensagem, MediaType.APPLICATION_JSON).build();
        }
    }
}
