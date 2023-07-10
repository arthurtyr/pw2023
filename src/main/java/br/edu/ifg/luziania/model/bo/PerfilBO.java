package br.edu.ifg.luziania.model.bo;

import br.edu.ifg.luziania.model.dao.PerfilDAO;
import br.edu.ifg.luziania.model.dto.PerfilRetornoDTO;
import br.edu.ifg.luziania.model.dto.CadastroPerfilDTO;
import br.edu.ifg.luziania.model.entity.Perfil;
import br.edu.ifg.luziania.model.util.Sessao;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;

@Dependent
public class PerfilBO {

    @Inject
    PerfilDAO perfilDAO;

    @Inject
    Sessao sessao;

    @Transactional
    public PerfilRetornoDTO salvar(CadastroPerfilDTO dto) {

        PerfilRetornoDTO respostaDTO = new PerfilRetornoDTO();

        Perfil entity = new Perfil();

        entity.setNome(dto.getNome());
        entity.setAcessolog(dto.getAcessolog());
        entity.setExcluirusuario(dto.getExcluirusuario());

        try {
            perfilDAO.save(entity);
            respostaDTO.setStatus(200);
            respostaDTO.setMensagem("Perfil salvo com sucesso!");
        }catch (Exception e){
            respostaDTO.setStatus(500);
            respostaDTO.setMensagem("Falha ao salvar perfil!");
        }
        return respostaDTO;
    }
    public PerfilRetornoDTO listar() {
        PerfilRetornoDTO respostaDTO = new PerfilRetornoDTO();

        if (perfilDAO.getPerfis() == null){
            respostaDTO.setStatus(500);
            respostaDTO.setMensagem("Nenhum perfil cadastrado");
        }else{
            respostaDTO.setStatus(200);
            respostaDTO.setMensagem("Perfis recuperados!");
            respostaDTO.setEntity(perfilDAO.getPerfis());
        }
        return respostaDTO;
    }
}