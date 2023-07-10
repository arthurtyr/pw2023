package br.edu.ifg.luziania.controller;

import br.edu.ifg.luziania.model.bo.PerfilBO;
import br.edu.ifg.luziania.model.dto.CadastroPerfilDTO;
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
public class PerfilController {

    @Inject
    Sessao sessao;

    @Inject
    PerfilBO perfilBO;
    private final Template perfil;

    public PerfilController(Template perfil){
        this.perfil = perfil;
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    @Path("/perfil")
    public TemplateInstance perfil(){
        if(sessao.getNome().isEmpty())
            return ErroTemplates.proibido();
        return perfil.instance();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/criarperfil")
    public Response criarperfil(CadastroPerfilDTO dto) {
        PerfilRetornoDTO respostaDTO = perfilBO.salvar(dto);
        return Response
                .status(respostaDTO.getStatus())
                .entity(respostaDTO)
                .build();
    }


}
