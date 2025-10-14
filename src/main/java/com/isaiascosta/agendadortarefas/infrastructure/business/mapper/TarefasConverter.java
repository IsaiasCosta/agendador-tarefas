package com.isaiascosta.agendadortarefas.infrastructure.business.mapper;

import com.isaiascosta.agendadortarefas.infrastructure.business.dto.TarefasDTO;
import com.isaiascosta.agendadortarefas.infrastructure.entity.TarefasEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring") // Indica que o MapStruct gerará uma implementação gerenciada pelo Spring
public interface TarefasConverter {
   @Mapping(source = "id", target = "id")
   @Mapping(source ="dataEvento", target = "dataEvento")
   @Mapping(source ="dataCriacao", target = "dataCriacao")
   TarefasEntity paraTarefaEntity(TarefasDTO dto); // Converte um objeto DTO em uma entidade para persistência
   TarefasDTO paraTarefaDTO(TarefasEntity entity); // Converte uma entidade em DTO para retorno ao cliente
   List<TarefasEntity> paraListaTarefasEntity(List<TarefasDTO> dtos);
   List<TarefasDTO>paraListaTarefasDTO(List<TarefasEntity>entities);
}
