package br.edu.ifg.luziania.controller;

import br.edu.ifg.luziania.model.bo.FichaBO;
import br.edu.ifg.luziania.model.bo.RegistroBO;
import br.edu.ifg.luziania.model.dto.RegistroDTO;
import br.edu.ifg.luziania.model.dto.FichaDTO;
import br.edu.ifg.luziania.model.dto.FichaRetornoDTO;
import br.edu.ifg.luziania.model.util.ErroTemplates;
import br.edu.ifg.luziania.model.util.Sessao;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Objects;

@Path("")
public class PrincipalController {
    @Inject
    RegistroBO registroBO;
    @Inject
    Sessao sessao;
    @Inject
    FichaBO fichaBO;
    private final Template principal;

    public PrincipalController(Template principal) {
        this.principal = principal;
    }
    @GET
    @Produces(MediaType.TEXT_HTML)
    @Path("/principal")
    public TemplateInstance principal(){
        List<String> lista = sessao.getPermissoes();
        RegistroDTO registroDTO = new RegistroDTO();
        registroDTO.setUsuario(sessao.getNome());

        if(sessao.getNome().isEmpty()) {
            registroDTO.setAcao("Acessou Sem Permissao a pagina Principal");
            registroBO.salvar(registroDTO);
            return ErroTemplates.proibido();
        }else{
            registroDTO.setAcao("Acessou a pagina Principal");
            registroBO.salvar(registroDTO);
            if(lista.isEmpty()){
                return principal
                        .data("acessoLog", false);
            }
            return principal.data("acessoLog", Objects.equals(lista.get(0), "sim"));
        }

    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/criarficha")
    public Response salvar(FichaDTO dto){
        FichaRetornoDTO retornoDTO = fichaBO.salvar(dto);
        return Response
                .status(retornoDTO.getStatus())
                .entity(retornoDTO)
                .build();
    }
}
