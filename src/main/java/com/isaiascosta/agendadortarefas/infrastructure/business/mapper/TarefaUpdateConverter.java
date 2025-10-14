package com.isaiascosta.agendadortarefas.infrastructure.business.mapper;

import com.isaiascosta.agendadortarefas.infrastructure.business.dto.TarefasDTO;
import com.isaiascosta.agendadortarefas.infrastructure.entity.TarefasEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TarefaUpdateConverter {

   void updateTarefas(TarefasDTO dto, @MappingTarget TarefasEntity entity);
}
