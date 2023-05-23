package br.edu.ifg.luziania.controller;

import br.edu.ifg.luziania.model.bo.UsuarioBO;
import br.edu.ifg.luziania.model.dto.AutenticacaoDTO;
import br.edu.ifg.luziania.model.dto.RetornoDTO;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("") //@Path -> Avisar o servidor de aplicações que essa classe java pode receber requisicções
//e define o caminho base para essa class.
public class LoginController {

    @Inject
    UsuarioBO usuarioBO;
    private final Template login;
    public LoginController(Template login){
        this.login = login;
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    @Path("/login")
    public TemplateInstance login(){
        return login.instance();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/autenticar")
    public Response autenticar(AutenticacaoDTO autenticacao){
        return Response.ok(usuarioBO.autenticar(autenticacao.getEmail(), autenticacao.getSenha()), MediaType.APPLICATION_JSON).build();
    }
}
