package com.NoletoTech.Agendadortarefas.business.mapper;

import com.NoletoTech.Agendadortarefas.business.dto.TarefasDTORecord;
import com.NoletoTech.Agendadortarefas.infrastucture.entity.TarefasEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TarefasConverter {

    TarefasEntity paraTarefasEntity (TarefasDTORecord dto);
    TarefasDTORecord paraTarefasDTORecord (TarefasEntity tarefasEntity);

    List<TarefasEntity> paraListaTarefasEntity(List<TarefasDTORecord> dtos);
    List<TarefasDTORecord> paraListaTarefasDTORecord(List<TarefasEntity> entities);

}
