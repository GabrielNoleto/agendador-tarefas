package com.NoletoTech.Agendadortarefas.business.mapper;

import com.NoletoTech.Agendadortarefas.business.dto.TarefasDTORecord;
import com.NoletoTech.Agendadortarefas.infrastucture.entity.TarefasEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TarefasUpdateConverter {

    void updateDeTarefas(TarefasDTORecord dto, @MappingTarget TarefasEntity entity);
}
