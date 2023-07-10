package br.edu.ifg.luziania.controller;

import br.edu.ifg.luziania.model.bo.PerfilBO;
import br.edu.ifg.luziania.model.bo.RegistroBO;
import br.edu.ifg.luziania.model.dto.PerfilRetornoDTO;
import br.edu.ifg.luziania.model.dto.RegistroDTO;
import br.edu.ifg.luziania.model.dto.RegistroRetornoDTO;
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
    RegistroBO registroBO;
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
        RegistroDTO registroDTO = new RegistroDTO();
        registroDTO.setUsuario(sessao.getNome());
        if(sessao.getPermissoes().isEmpty() || sessao.getPermissoes().get(0).equals("nao")){
            registroDTO.setAcao("Acessou Sem Permissao a pagina Registros");
            registroBO.salvar(registroDTO);
            return ErroTemplates.proibido();
        }
        registroDTO.setAcao("Acessou a pagina Registros");
        registroBO.salvar(registroDTO);
        return registro.instance();
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/listarlog")
    public Response tabela(){
        RegistroRetornoDTO retornoDTO = registroBO.listarlog();

        return Response
                .status(retornoDTO.getStatus())
                .entity(retornoDTO)
                .build();
    }

}