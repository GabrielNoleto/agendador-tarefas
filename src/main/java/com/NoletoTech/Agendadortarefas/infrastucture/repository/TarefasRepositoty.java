package com.NoletoTech.Agendadortarefas.infrastucture.repository;

import com.NoletoTech.Agendadortarefas.infrastucture.entity.TarefasEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TarefasRepositoty extends MongoRepository<TarefasEntity, String> {
}
