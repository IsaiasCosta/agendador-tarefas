package com.isaiascosta.agendadortarefas.infrastructure.business;

import com.isaiascosta.agendadortarefas.infrastructure.business.dto.TarefasDTO;
import com.isaiascosta.agendadortarefas.infrastructure.business.mapper.TarefasConverter;
import com.isaiascosta.agendadortarefas.infrastructure.entity.TarefasEntity;
import com.isaiascosta.agendadortarefas.infrastructure.enums.StatusNotificacaoEnum;
import com.isaiascosta.agendadortarefas.infrastructure.security.JwtUtil;
import com.isaiascosta.agendadortarefas.repository.TarefasRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor

public class TarefasService {
   private final TarefasRepository tarefasRepository;
   private final TarefasConverter tarefasConverter;
   private final JwtUtil jwtUtil;

   public TarefasDTO gravarTarefa(String token, TarefasDTO dto) {
      String email = jwtUtil.extraiEmailToken(token.substring(7)); // Extrai o e-mail do token JWT (remove "Bearer ")
      dto.setDataCriacao(LocalDateTime.now()); // Define a data e hora de criação
      dto.setStatus(StatusNotificacaoEnum.PENDENTE); // Define o status inicial como PENDENTE
      dto.setEmailUsuario(email); // Associa o e-mail do usuário à tarefa
      TarefasEntity entity = tarefasConverter.paraTarefaEntity(dto); // Converte o DTO para entidade
      return tarefasConverter.paraTarefaDTO(tarefasRepository.save(entity)); // Salva a entidade no banco MongoDB

   }
}
