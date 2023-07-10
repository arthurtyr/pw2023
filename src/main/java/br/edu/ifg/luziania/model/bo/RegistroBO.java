package br.edu.ifg.luziania.model.bo;

import br.edu.ifg.luziania.model.dao.RegistroDAO;
import br.edu.ifg.luziania.model.dto.PerfilRetornoDTO;
import br.edu.ifg.luziania.model.dto.RegistroDTO;
import br.edu.ifg.luziania.model.dto.RegistroRetornoDTO;
import br.edu.ifg.luziania.model.entity.Registro;
import br.edu.ifg.luziania.model.util.Sessao;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Dependent
public class RegistroBO {
    @Inject
    RegistroDAO registroDAO;

    @Inject
    Sessao sessao;

    @Transactional
    public RegistroRetornoDTO salvar(RegistroDTO dto){
        LocalDateTime time = LocalDateTime.now();
        RegistroRetornoDTO respostaDTO = new RegistroRetornoDTO();

        Registro entity = new Registro();

        entity.setUsuario(dto.getUsuario());
        entity.setAcao(dto.getAcao());
        entity.setData(time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        try {
            registroDAO.save(entity);
            respostaDTO.setStatus(200);
            respostaDTO.setMensagem("Acao registrada com sucesso!");
        }catch (Exception e){
            respostaDTO.setStatus(500);
            respostaDTO.setMensagem("Falha ao registrar acao!");
        }
        return respostaDTO;
    }
    public RegistroRetornoDTO listarlog() {
        RegistroRetornoDTO respostaDTO = new RegistroRetornoDTO();

        if (registroDAO.getLogs() == null){
            respostaDTO.setStatus(500);
            respostaDTO.setMensagem("Nenhum log registrado");
        }else{
            respostaDTO.setStatus(200);
            respostaDTO.setMensagem("Logs recuperados!");
            respostaDTO.setEntity(registroDAO.getLogs());
        }
        return respostaDTO;
    }
}
