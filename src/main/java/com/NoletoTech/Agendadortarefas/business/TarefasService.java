package com.NoletoTech.Agendadortarefas.business;

import com.NoletoTech.Agendadortarefas.business.dto.TarefasDTO;
import com.NoletoTech.Agendadortarefas.business.mapper.TarefasConverter;
import com.NoletoTech.Agendadortarefas.infrastucture.entity.TarefasEntity;
import com.NoletoTech.Agendadortarefas.infrastucture.enums.StatusNotificacaoEnum;
import com.NoletoTech.Agendadortarefas.infrastucture.repository.TarefasRepositoty;
import com.NoletoTech.Agendadortarefas.infrastucture.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TarefasService {
    private final TarefasRepositoty tarefasRepositoty;
    private final TarefasConverter tarefasConverter;
    private final JwtUtil jwtUtil;


    public TarefasDTO gravarTarefa (String token, TarefasDTO dto){
        String email = jwtUtil.extractUsername(token.substring(7));
        dto.setDataCriacao(LocalDateTime.now());
        dto.setStatusNotificacaoEnum(StatusNotificacaoEnum.PENDENTE);
        dto.setEmailUsuario(email);
       TarefasEntity entity = tarefasConverter.paraTarefasEntity(dto);
        return tarefasConverter.paraTarefasDTO(tarefasRepositoty.save(entity));
    }



}
