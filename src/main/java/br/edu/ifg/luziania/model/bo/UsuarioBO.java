package br.edu.ifg.luziania.model.bo;

import br.edu.ifg.luziania.model.dao.PerfilDAO;
import br.edu.ifg.luziania.model.dao.UsuarioDAO;
import br.edu.ifg.luziania.model.dto.CadastroUsuarioRetornoDTO;
import br.edu.ifg.luziania.model.dto.AutenticacaoUsuarioRetornoDTO;
import br.edu.ifg.luziania.model.dto.CadastroUsuarioDTO;
import br.edu.ifg.luziania.model.entity.Perfil;
import br.edu.ifg.luziania.model.entity.Usuario;
import br.edu.ifg.luziania.model.util.Sessao;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;

@Dependent
public class UsuarioBO {

    @Inject
    UsuarioDAO usuarioDAO;
    @Inject
    PerfilDAO perfilDAO;

    @Inject
    Sessao sessao;

    public AutenticacaoUsuarioRetornoDTO autenticar(String email, String senha){
        Usuario usuario = usuarioDAO.getByEmailAndSenha(email, senha);
        List<Perfil> perfis = perfilDAO.getPerfis();
        List<String> permissoes = new ArrayList<>();
        AutenticacaoUsuarioRetornoDTO retorno = new AutenticacaoUsuarioRetornoDTO();
        if (isNull(usuario)){
            retorno.setUrl("/");
            retorno.setAutenticado(false);
            retorno.setMensagem("Email ou senha inválido!");
        } else {
            retorno.setUrl("/principal");
            retorno.setAutenticado(true);
            retorno.setMensagem("Olá "+usuario.getNome()+"!");
            sessao.setNome(usuario.getNome());
            boolean contemPerfil = false;
            for (Perfil perfil : perfis) {
                if (perfil.getNome().equals(sessao.getNome())) {
                    contemPerfil = true;
                    break;
                }
            }
            if(contemPerfil) {
                Perfil perfil = perfilDAO.getByNome(sessao.getNome());
                permissoes.add(perfil.getAcessolog());
                permissoes.add(perfil.getExcluirusuario());
            }
            sessao.setPermissoes(permissoes);


        }
        return retorno;
    }

    @Transactional
    public CadastroUsuarioRetornoDTO salvar(CadastroUsuarioDTO dto) {

        CadastroUsuarioRetornoDTO respostaDTO = new CadastroUsuarioRetornoDTO();

        Usuario entity = new Usuario();

        entity.setEmail(dto.getEmail());
        entity.setNome(dto.getNome());
        entity.setSenha(dto.getSenha());

        try {
            usuarioDAO.save(entity);
            respostaDTO.setStatus(200);
            respostaDTO.setMensagem("Usuário salvo com sucesso!");
            respostaDTO.setUrl("/login");
        }catch (Exception e){
            respostaDTO.setStatus(500);
            respostaDTO.setMensagem("Falha ao salvar usuário!");
            respostaDTO.setUrl("/usuario");
        }

        return respostaDTO;
    }
}