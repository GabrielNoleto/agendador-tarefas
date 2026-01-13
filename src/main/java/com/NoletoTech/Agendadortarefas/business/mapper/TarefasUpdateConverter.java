package com.NoletoTech.Agendadortarefas.business.mapper;

import com.NoletoTech.Agendadortarefas.business.dto.TarefasDTO;
import com.NoletoTech.Agendadortarefas.infrastucture.entity.TarefasEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TarefasUpdateConverter {

    void updateDeTarefas(TarefasDTO dto, @MappingTarget TarefasEntity entity);
}
