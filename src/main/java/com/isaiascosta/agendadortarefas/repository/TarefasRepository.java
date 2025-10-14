package com.isaiascosta.agendadortarefas.repository;

import com.isaiascosta.agendadortarefas.infrastructure.entity.TarefasEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TarefasRepository  extends MongoRepository<TarefasEntity,String> {

   //Buscar tarefas em um periodo de tempo
   List<TarefasEntity> findByDataEventoBetween(LocalDateTime dataInicial, LocalDateTime dataFinal);
   List<TarefasEntity>findByEmailUsuario(String email);

}
