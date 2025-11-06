package com.isaiascosta.agendadortarefas.infrastructure.business.mapper;

import com.isaiascosta.agendadortarefas.infrastructure.business.dto.TarefasDTORecord;
import com.isaiascosta.agendadortarefas.infrastructure.entity.TarefasEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TarefaUpdateConverter {

   void updateTarefas(TarefasDTORecord dto, @MappingTarget TarefasEntity entity);
}
