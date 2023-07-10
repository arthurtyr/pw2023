package br.edu.ifg.luziania.controller;

import br.edu.ifg.luziania.model.bo.RegistroBO;
import br.edu.ifg.luziania.model.bo.UsuarioBO;
import br.edu.ifg.luziania.model.dto.CadastroUsuarioRetornoDTO;
import br.edu.ifg.luziania.model.dto.CadastroUsuarioDTO;
import br.edu.ifg.luziania.model.dto.RegistroDTO;
import br.edu.ifg.luziania.model.util.Sessao;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("usuario")
public class UsuarioController {
    @Inject
    RegistroBO registroBO;
    @Inject
    Sessao sessao;
    @Inject
    UsuarioBO usuarioBO;
    private final Template cadastro;
    //cria uma variavel Template com o mesmo nome do arquivo html(cadastro)

    public UsuarioController(Template cadastro){
        this.cadastro = cadastro;
    }

    //produz o html cadastro
    @GET
    @Produces(MediaType.TEXT_HTML)
    @Path("/cadastro")
    public TemplateInstance cadastro(){
        RegistroDTO registroDTO = new RegistroDTO();
        registroDTO.setUsuario(sessao.getNome());
        registroDTO.setAcao("Acessou a pagina Cadastro");
        registroBO.salvar(registroDTO);
        if(sessao.getNome().isEmpty()){
            return cadastro.data("acessoPrincipal", false);
        }
        return cadastro.data("acessoPrincipal", true);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/cadastrar")
    public Response salvar(CadastroUsuarioDTO dto){
        CadastroUsuarioRetornoDTO respostaDTO = usuarioBO.salvar(dto);
        return Response
                .status(respostaDTO.getStatus())
                .entity(respostaDTO)
                .build();
    }


}
