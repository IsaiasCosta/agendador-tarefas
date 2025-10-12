package com.isaiascosta.agendadortarefas.repository;

import com.isaiascosta.agendadortarefas.infrastructure.entity.TarefasEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TarefasRepository  extends MongoRepository<TarefasEntity,String> {
}
