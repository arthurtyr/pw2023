package br.edu.ifg.luziania.model.bo;

import br.edu.ifg.luziania.model.dao.UsuarioDAO;
import br.edu.ifg.luziania.model.dto.RespostaDTO;
import br.edu.ifg.luziania.model.dto.RetornoAutenticacaoDTO;
import br.edu.ifg.luziania.model.dto.UsuarioDTO;
import br.edu.ifg.luziania.model.entity.Usuario;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;

import static java.util.Objects.isNull;

@Dependent
public class UsuarioBO {

    @Inject
    UsuarioDAO usuarioDAO;

    public RetornoAutenticacaoDTO autenticar(String email, String senha){
        Usuario usuario = usuarioDAO.getByEmailAndSenha(email, senha);
        RetornoAutenticacaoDTO retorno = new RetornoAutenticacaoDTO();
        if (isNull(usuario)){
            retorno.setUrl("/");
            retorno.setAutenticado(false);
            retorno.setMensagem("Email ou senha inválido!");
        } else {
            retorno.setUrl("/principal");
            retorno.setAutenticado(true);
            retorno.setMensagem("Olá "+usuario.getNome()+"!");
        }
        return retorno;
    }

    @Transactional
    public RespostaDTO salvar(UsuarioDTO dto) {

        RespostaDTO respostaDTO = new RespostaDTO();

        Usuario entity = new Usuario();

        entity.setEmail(dto.getEmail());
        entity.setNome(dto.getNome());
        entity.setSenha(dto.getSenha());

        try {
            usuarioDAO.save(entity);
            respostaDTO.setStatus(200);
            respostaDTO.setMensagem("Usuário salvo com sucesso!");
            respostaDTO.setUrl("/");
        }catch (Exception e){
            respostaDTO.setStatus(500);
            respostaDTO.setMensagem("Falha ao salvar usuário!");
            respostaDTO.setUrl("/usuario");
        }

        return respostaDTO;
    }
}
