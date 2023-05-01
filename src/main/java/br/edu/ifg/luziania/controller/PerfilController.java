package br.edu.ifg.luziania.controller;

import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
@Path("")
public class PerfilController {

    private final Template perfil;

    public PerfilController(Template perfil){
        this.perfil = perfil;
    }

    @GET
    @Produces({MediaType.TEXT_HTML})
    @Path("/perfil")
    public TemplateInstance perfil(){
        return perfil.instance();
    }
}
