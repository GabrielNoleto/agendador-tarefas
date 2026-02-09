package com.NoletoTech.Agendadortarefas.infrastucture.repository;

import com.NoletoTech.Agendadortarefas.infrastucture.entity.TarefasEntity;
import com.NoletoTech.Agendadortarefas.infrastucture.enums.StatusNotificacaoEnum;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TarefasRepositoty extends MongoRepository<TarefasEntity, String> {

    List<TarefasEntity> findByDataEventoBetweenAndStatusNotificacaoEnum(LocalDateTime dataInicial, LocalDateTime dataFinal, StatusNotificacaoEnum status);

    List<TarefasEntity> findByEmailUsuario(String email);
}