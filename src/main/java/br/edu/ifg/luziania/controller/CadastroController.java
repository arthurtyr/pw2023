package br.edu.ifg.luziania.controller;

import br.edu.ifg.luziania.model.dto.CadastrarDTO;
import br.edu.ifg.luziania.model.dto.RetornoDTO;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("")
public class CadastroController {
    private final Template cadastro;
    //cria uma variavel Template com o mesmo nome do arquivo html(cadastro)
    private CadastrarDTO cadastroob;
    //cria uma variável CadastrarDTO que armazenará os dados no servidor
    private RetornoDTO mensagem;
    //cria uma variável RetornoDTO que armazenará a mensagem no servidor
    public CadastroController(Template cadastro){
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
    public Response cadastrar(CadastrarDTO cadastrarob){ //"cadastrarob(email,nome,senha,confsenha)" recebe os valores do html
        //cria um retorno(mensagem)
        RetornoDTO retorno = new RetornoDTO();
        //se o email de cadastrarob(email que veio do html) for igual a aninha@gmail.com, a mensagem de retorno
        //recebe "utilizado"
        if(cadastrarob.getEmail().equals("aninha@gmail.com")){
            retorno.setMensagem("utilizado");
        }else{//senao, quer dizer que pode cadastrar e a mensagem de retorno recebe "cadastrado"
            retorno.setMensagem("cadastrado");
            cadastroob = cadastrarob;//aqui, a variável local cadastroob recebe o valor de cadastrarob, para
            //simular a persistência de dados(os valores vao ficar salvos no servidor)
        }
        //a variável local de mensagem também recebe o valor de retorno para ser usada futuramente pelo servidor
        this.mensagem = retorno;
        //retorna um response created(201) com a entidade retorno
        return Response.status(Response.Status.CREATED).entity(retorno).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/cadastrar")
    public Response cadastros(){
        //se a mensagem salva no servidor for igual a "cadastrado" retorna o objeto salvo no servidor
        if(mensagem.getMensagem().equals("cadastrado")){
            return Response.ok().entity(cadastroob).build();
        }else{//senao, retorna a mensagem salva no servidor(que será "utilizado)
            return Response.ok(mensagem, MediaType.APPLICATION_JSON).build();
        }
    }
}
