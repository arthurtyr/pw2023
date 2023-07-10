package br.edu.ifg.luziania.controller;

import br.edu.ifg.luziania.model.bo.PerfilBO;
import br.edu.ifg.luziania.model.bo.RegistroBO;
import br.edu.ifg.luziania.model.dto.CadastroPerfilDTO;
import br.edu.ifg.luziania.model.dto.PerfilRetornoDTO;
import br.edu.ifg.luziania.model.dto.RegistroDTO;
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
    @Inject
    RegistroBO registroBO;
    private final Template perfil;

    public PerfilController(Template perfil){
        this.perfil = perfil;
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    @Path("/perfil")
    public TemplateInstance perfil(){
        RegistroDTO registroDTO = new RegistroDTO();
        registroDTO.setUsuario(sessao.getNome());

        if(sessao.getNome().isEmpty()) {
            registroDTO.setAcao("Acessou Sem Permissao a pagina Cadastrar Perfil");
            registroBO.salvar(registroDTO);
            return ErroTemplates.proibido();
        }
        registroDTO.setAcao("Acessou a pagina Cadastrar Perfil");
        registroBO.salvar(registroDTO);
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
