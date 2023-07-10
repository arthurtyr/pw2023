package br.edu.ifg.luziania.model.bo;

import br.edu.ifg.luziania.model.dao.PerfilDAO;
import br.edu.ifg.luziania.model.dao.UsuarioDAO;
import br.edu.ifg.luziania.model.dto.CadastroUsuarioRetornoDTO;
import br.edu.ifg.luziania.model.dto.AutenticacaoUsuarioRetornoDTO;
import br.edu.ifg.luziania.model.dto.CadastroUsuarioDTO;
import br.edu.ifg.luziania.model.dto.RegistroDTO;
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
    RegistroBO registroBO;
    @Inject
    Sessao sessao;

    public AutenticacaoUsuarioRetornoDTO autenticar(String email, String senha){
        Usuario usuario = usuarioDAO.getByEmailAndSenha(email, senha);
        List<Perfil> perfis = perfilDAO.getPerfis();
        List<String> permissoes = new ArrayList<>();
        AutenticacaoUsuarioRetornoDTO retornoDTO = new AutenticacaoUsuarioRetornoDTO();
        RegistroDTO logDTO = new RegistroDTO();
        if (isNull(usuario)){
            retornoDTO.setUrl("/login");
            retornoDTO.setAutenticado(false);
            retornoDTO.setMensagem("Email ou senha inválido!");
            logDTO.setAcao("Email ou senha invalido");
        } else {
            retornoDTO.setUrl("/principal");
            retornoDTO.setAutenticado(true);
            retornoDTO.setMensagem("Olá "+usuario.getNome()+"!");
            logDTO.setAcao("Autenticacao realizada com sucesso");
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
        logDTO.setUsuario(sessao.getNome());
        registroBO.salvar(logDTO);
        return retornoDTO;
    }

    @Transactional
    public CadastroUsuarioRetornoDTO salvar(CadastroUsuarioDTO dto) {

        CadastroUsuarioRetornoDTO respostaDTO = new CadastroUsuarioRetornoDTO();
        RegistroDTO logDTO = new RegistroDTO();

        Usuario entity = new Usuario();

        entity.setEmail(dto.getEmail());
        entity.setNome(dto.getNome());
        entity.setSenha(dto.getSenha());

        try {
            usuarioDAO.save(entity);
            respostaDTO.setStatus(200);
            respostaDTO.setMensagem("Usuário salvo com sucesso!");
            logDTO.setAcao("Usuario salvo com sucesso");
            respostaDTO.setUrl("/login");
        }catch (Exception e){
            respostaDTO.setStatus(500);
            respostaDTO.setMensagem("Falha ao salvar usuário!");
            logDTO.setAcao("Falha ao salvar usuario");
            respostaDTO.setUrl("/usuario");
        }
        logDTO.setUsuario(sessao.getNome());
        registroBO.salvar(logDTO);
        return respostaDTO;
    }
}