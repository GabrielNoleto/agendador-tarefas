package com.NoletoTech.Agendadortarefas.business;

import com.NoletoTech.Agendadortarefas.business.dto.TarefasDTORecord;
import com.NoletoTech.Agendadortarefas.business.mapper.TarefasUpdateConverter;
import com.NoletoTech.Agendadortarefas.business.mapper.TarefasConverter;
import com.NoletoTech.Agendadortarefas.infrastucture.entity.TarefasEntity;
import com.NoletoTech.Agendadortarefas.infrastucture.enums.StatusNotificacaoEnum;
import com.NoletoTech.Agendadortarefas.infrastucture.exceptions.ResourceNotFoundException;
import com.NoletoTech.Agendadortarefas.infrastucture.repository.TarefasRepositoty;
import com.NoletoTech.Agendadortarefas.infrastucture.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefasService {
    private final TarefasRepositoty tarefasRepositoty;
    private final TarefasConverter tarefasConverter;
    private final JwtUtil jwtUtil;
    private final TarefasUpdateConverter tarefasUpdateConverter;


    public TarefasDTORecord gravarTarefa(String token, TarefasDTORecord dto) {
        String email = jwtUtil.extractUsername(token.substring(7));
        TarefasDTORecord dtoFinal = new TarefasDTORecord(
                null,
                dto.nomeTarefa(),
                dto.descricao(),
                LocalDateTime.now(),
                dto.dataEvento(),
                email,
                null,
                StatusNotificacaoEnum.PENDENTE);

        TarefasEntity entity = tarefasConverter.paraTarefasEntity(dtoFinal);
        return tarefasConverter.paraTarefasDTORecord(tarefasRepositoty.save(entity));
    }

    public List<TarefasDTORecord> buscaTarefasAgendadasPorPeriodo(LocalDateTime dataInicial, LocalDateTime dataFinal) {
        return tarefasConverter.paraListaTarefasDTORecord(tarefasRepositoty.findByDataEventoBetweenAndStatusNotificacaoEnum(dataInicial, dataFinal, StatusNotificacaoEnum.PENDENTE));
    }

    public List<TarefasDTORecord> buscaTarefasPorEmail(String token) {
        String email = jwtUtil.extractUsername(token.substring(7));
        return tarefasConverter.paraListaTarefasDTORecord(tarefasRepositoty.findByEmailUsuario(email));
    }

    public void deletaTarefaPorId(String id) {
        try {
            tarefasRepositoty.deleteById(id);
        } catch (ResourceNotFoundException e) {
            throw new RuntimeException("Erro ao deletar tarefa por id, id inexistente " + id, e.getCause());
        }
    }

    public TarefasDTORecord alteraStatus (StatusNotificacaoEnum status, String id) {
        try {
            TarefasEntity entity = tarefasRepositoty.findById(id).orElseThrow(() -> new ResourceNotFoundException("Tarefa não encontrada" + id));
            entity.setStatusNotificacaoEnum(status);
            return tarefasConverter.paraTarefasDTORecord(tarefasRepositoty.save(entity));
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Erro ao alterar o status da tarefa " + e.getCause());
        }
    }

    public TarefasDTORecord updateTarefas(TarefasDTORecord dto, String id){
        try {
            TarefasEntity entity = tarefasRepositoty.findById(id).orElseThrow(() -> new ResourceNotFoundException("Tarefa não encontrada" + id));
            tarefasUpdateConverter.updateDeTarefas(dto, entity);
            return tarefasConverter.paraTarefasDTORecord(tarefasRepositoty.save(entity));
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Erro ao alterar o status da tarefa " + e.getCause());
        }
    }



}
