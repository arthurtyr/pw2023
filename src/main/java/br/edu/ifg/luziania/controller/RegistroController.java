package br.edu.ifg.luziania.controller;

import br.edu.ifg.luziania.model.bo.PerfilBO;
import br.edu.ifg.luziania.model.dto.PerfilRetornoDTO;
import br.edu.ifg.luziania.model.util.ErroTemplates;
import br.edu.ifg.luziania.model.util.Sessao;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("")
public class RegistroController {

    @Inject
    Sessao sessao;
    @Inject
    PerfilBO perfilBO;
    private final Template registro;

    public RegistroController(Template registro) {
        this.registro = registro;
    }
    @GET
    @Produces(MediaType.TEXT_HTML)
    @Path("/registro")
    public TemplateInstance registro(){
        if(sessao.getPermissoes().isEmpty()){
            return ErroTemplates.proibido();
        }else if(sessao.getPermissoes().get(1).equals("nao"))
            return ErroTemplates.proibido();
        return registro.instance();
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/listar")
    public Response tabela(){
        PerfilRetornoDTO retornoDTO = perfilBO.listar();

        return Response
                .status(retornoDTO.getStatus())
                .entity(retornoDTO)
                .build();
    }

}