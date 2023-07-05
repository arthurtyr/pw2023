package br.edu.ifg.luziania.controller;

import br.edu.ifg.luziania.model.bo.UsuarioBO;
import br.edu.ifg.luziania.model.dto.CadastrarDTO;
import br.edu.ifg.luziania.model.dto.RespostaDTO;
import br.edu.ifg.luziania.model.dto.RetornoDTO;
import br.edu.ifg.luziania.model.dto.UsuarioDTO;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("usuario")
public class UsuarioController {

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
        return cadastro.instance();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/cadastrar")
    public Response salvar(UsuarioDTO dto){
        RespostaDTO respostaDTO = usuarioBO.salvar(dto);
        return Response
                .status(respostaDTO.getStatus())
                .entity(respostaDTO)
                .build();
    }


}
