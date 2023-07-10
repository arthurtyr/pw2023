package br.edu.ifg.luziania.model.bo;

import br.edu.ifg.luziania.model.dao.FichaDAO;
import br.edu.ifg.luziania.model.dto.FichaDTO;
import br.edu.ifg.luziania.model.dto.PerfilRetornoDTO;
import br.edu.ifg.luziania.model.dto.RegistroDTO;
import br.edu.ifg.luziania.model.dto.FichaRetornoDTO;
import br.edu.ifg.luziania.model.entity.Ficha;
import br.edu.ifg.luziania.model.util.Sessao;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;

@Dependent
public class FichaBO {
    @Inject
    Sessao sessao;
    @Inject
    RegistroBO registroBO;
    @Inject
    FichaDAO fichaDAO;

    @Transactional
    public FichaRetornoDTO salvar(FichaDTO dto){
        FichaRetornoDTO retornoDTO = new FichaRetornoDTO();
        RegistroDTO logDTO = new RegistroDTO();

        Ficha entity = new Ficha();

        entity.setNome(dto.getNome());
        entity.setUsuario(sessao.getNome());
        entity.setNivel(dto.getNivel());
        entity.setDestreza(dto.getDestreza());
        entity.setConstituicao(dto.getConstituicao());
        entity.setInteligencia(dto.getInteligencia());

        try{
            fichaDAO.save(entity);
            retornoDTO.setStatus(200);
            retornoDTO.setMensagem("Ficha salva!");
            logDTO.setAcao("Usuario criou uma ficha");

        }catch (Exception e){
            retornoDTO.setStatus(500);
            retornoDTO.setMensagem("Falha ao salvar a ficha");
            logDTO.setAcao("Falha na criação de ficha");
        }
        registroBO.salvar(logDTO);
        return retornoDTO;
    }

    public FichaRetornoDTO listarfichas(){
        FichaRetornoDTO respostaDTO = new FichaRetornoDTO();

        if (fichaDAO.getFichasdeUsuario(sessao.getNome()) == null){
            respostaDTO.setStatus(500);
            respostaDTO.setMensagem("Nenhuma ficha cadastrada");
        }else{
            respostaDTO.setStatus(200);
            respostaDTO.setMensagem("Fichas recuperadas!");
            respostaDTO.setEntity(fichaDAO.getFichasdeUsuario(sessao.getNome()));
        }
        return respostaDTO;
    }
    }

