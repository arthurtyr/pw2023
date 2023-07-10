package br.edu.ifg.luziania.controller;

import br.edu.ifg.luziania.model.bo.FichaBO;
import br.edu.ifg.luziania.model.bo.PerfilBO;
import br.edu.ifg.luziania.model.bo.RegistroBO;
import br.edu.ifg.luziania.model.dto.FichaRetornoDTO;
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
public class FichasController {
    @Inject
    RegistroBO registroBO;
    @Inject
    Sessao sessao;
    @Inject
    FichaBO fichaBO;
    private final Template fichas;

    public FichasController(Template fichas) {
        this.fichas = fichas;
    }
    @GET
    @Produces(MediaType.TEXT_HTML)
    @Path("/fichas")
    public TemplateInstance fichas(){
        RegistroDTO registroDTO = new RegistroDTO();
        registroDTO.setUsuario(sessao.getNome());

        if(sessao.getNome().isEmpty()) {
            registroDTO.setAcao("Acessou Sem Permissao a pagina Fichas");
            registroBO.salvar(registroDTO);
            return ErroTemplates.proibido();
        }
        registroDTO.setAcao("Acessou a pagina Fichas");
        registroBO.salvar(registroDTO);
        return fichas.instance();
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/listarfichas")
    public Response tabela(){
        FichaRetornoDTO retornoDTO = fichaBO.listarfichas();

        return Response
                .status(retornoDTO.getStatus())
                .entity(retornoDTO)
                .build();
    }

}