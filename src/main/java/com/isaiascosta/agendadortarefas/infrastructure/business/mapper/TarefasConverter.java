package com.isaiascosta.agendadortarefas.infrastructure.business.mapper;

import com.isaiascosta.agendadortarefas.infrastructure.business.dto.TarefasDTO;
import com.isaiascosta.agendadortarefas.infrastructure.entity.TarefasEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring") // Indica que o MapStruct gerará uma implementação gerenciada pelo Spring
public interface TarefasConverter {
   TarefasEntity paraTarefaEntity(TarefasDTO dto); // Converte um objeto DTO em uma entidade para persistência
   TarefasDTO paraTarefaDTO(TarefasEntity entity); // Converte uma entidade em DTO para retorno ao cliente
}
