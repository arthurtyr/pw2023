package br.edu.ifg.luziania.controller;

import br.edu.ifg.luziania.model.dto.PerfilDTO;
import br.edu.ifg.luziania.model.dto.RetornoCadastroDTO;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
@Path("")
public class PerfilController {

    private final Template perfil;

    private PerfilDTO perfilob;

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
        return Response.ok().build();
    }
}
