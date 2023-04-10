package br.edu.ifg.luziania;

import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("") //@Path -> Avisar o servidor de aplicações que essa classe java pode receber requisicções
//e define o caminho base para essa class.
public class Login {

    private final Template login;

    public Login(Template login){
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
    public Response autenticar(Autenticacao autenticacao){
        RetornoAutenticacao retorno = new RetornoAutenticacao();
        if (autenticacao.getEmail().equals("aninha@gmail.com") && autenticacao.getSenha().equals("123")) {
            retorno.setMensagem("Usuário autenticado!");
            return Response.ok(retorno, MediaType.APPLICATION_JSON).build();
        }
        else {
            retorno.setMensagem("Usuário não autenticado!");
            return Response.ok(retorno, MediaType.APPLICATION_JSON).build();
        }
    }




}
