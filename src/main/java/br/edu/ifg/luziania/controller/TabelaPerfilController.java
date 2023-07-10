package br.edu.ifg.luziania.controller;

import br.edu.ifg.luziania.model.bo.PerfilBO;
import br.edu.ifg.luziania.model.bo.RegistroBO;
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
public class TabelaPerfilController {
    @Inject
    RegistroBO registroBO;
    @Inject
    Sessao sessao;
    @Inject
    PerfilBO perfilBO;
    private final Template tabelaperfil;

    public TabelaPerfilController(Template tabelaperfil) {
        this.tabelaperfil = tabelaperfil;
    }
    @GET
    @Produces(MediaType.TEXT_HTML)
    @Path("/tabelaperfil")
    public TemplateInstance tabelaperfil(){
        RegistroDTO registroDTO = new RegistroDTO();
        registroDTO.setUsuario(sessao.getNome());

        if(sessao.getNome().isEmpty()) {
            registroDTO.setAcao("Acessou Sem Permissao a pagina Perfis");
            registroBO.salvar(registroDTO);
            return ErroTemplates.proibido();
        }
        registroDTO.setAcao("Acessou a pagina Perfis");
        registroBO.salvar(registroDTO);
        return tabelaperfil.instance();
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/listarperfis")
    public Response tabela(){
        PerfilRetornoDTO retornoDTO = perfilBO.listarperfis();

        return Response
                .status(retornoDTO.getStatus())
                .entity(retornoDTO)
                .build();
    }

}