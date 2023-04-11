package br.edu.ifg.luziania.controller;

import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("")
public class PrincipalController {

    private final Template principal;

    public PrincipalController(Template principal) {
        this.principal = principal;
    }
    @GET
    @Produces(MediaType.TEXT_HTML)
    @Path("/principal")
    public TemplateInstance principal(){
        return principal.instance();
    }
}
