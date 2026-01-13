package com.NoletoTech.Agendadortarefas.business.mapper;

import com.NoletoTech.Agendadortarefas.business.dto.TarefasDTO;
import com.NoletoTech.Agendadortarefas.infrastucture.entity.TarefasEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TarefasConverter {

    TarefasEntity paraTarefasEntity (TarefasDTO dto);
    TarefasDTO paraTarefasDTO (TarefasEntity tarefasEntity);

}
