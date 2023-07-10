package br.edu.ifg.luziania.controller;

import br.edu.ifg.luziania.model.bo.RegistroBO;
import br.edu.ifg.luziania.model.dto.RegistroDTO;
import br.edu.ifg.luziania.model.entity.Perfil;
import br.edu.ifg.luziania.model.util.ErroTemplates;
import br.edu.ifg.luziania.model.util.Sessao;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Objects;

@Path("")
public class PrincipalController {
    @Inject
    RegistroBO registroBO;
    @Inject
    Sessao sessao;
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
}
