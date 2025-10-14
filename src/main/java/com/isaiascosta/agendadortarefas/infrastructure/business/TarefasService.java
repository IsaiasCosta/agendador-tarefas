package com.isaiascosta.agendadortarefas.infrastructure.business;

import com.isaiascosta.agendadortarefas.infrastructure.business.dto.TarefasDTO;
import com.isaiascosta.agendadortarefas.infrastructure.business.mapper.TarefaUpdateConverter;
import com.isaiascosta.agendadortarefas.infrastructure.business.mapper.TarefasConverter;
import com.isaiascosta.agendadortarefas.infrastructure.entity.TarefasEntity;
import com.isaiascosta.agendadortarefas.infrastructure.enums.StatusNotificacaoEnum;
import com.isaiascosta.agendadortarefas.infrastructure.exceptions.ResourceNotFoundException;
import com.isaiascosta.agendadortarefas.infrastructure.security.JwtUtil;
import com.isaiascosta.agendadortarefas.repository.TarefasRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor

public class TarefasService {
   private final TarefasRepository tarefasRepository;
   private final TarefasConverter tarefasConverter;
   private final JwtUtil jwtUtil;
   private final TarefaUpdateConverter tarefaUpdateConverter;

   public TarefasDTO gravarTarefa(String token, TarefasDTO dto) {
      String email = jwtUtil.extraiEmailToken(token.substring(7)); // Extrai o e-mail do token JWT (remove "Bearer ")
      dto.setDataCriacao(LocalDateTime.now()); // Define a data e hora de criação
      dto.setStatus(StatusNotificacaoEnum.PENDENTE); // Define o status inicial como PENDENTE
      dto.setEmailUsuario(email); // Associa o e-mail do usuário à tarefa
      TarefasEntity entity = tarefasConverter.paraTarefaEntity(dto); // Converte o DTO para entidade
      return tarefasConverter.paraTarefaDTO(tarefasRepository.save(entity)); // Salva a entidade no banco MongoDB

   }

   public List<TarefasDTO> buscarTarefasAgendadasPorPeriodo(LocalDateTime dataInicial, LocalDateTime dataFinal) {
      return tarefasConverter.paraListaTarefasDTO(
              tarefasRepository.findByDataEventoBetween(dataInicial, dataFinal));
   }

   public List<TarefasDTO> buscarTarefasPorEmail(String token) {
      String email = jwtUtil.extraiEmailToken(token.substring(7));
      List<TarefasEntity> listaTarefas = tarefasRepository.findByEmailUsuario(email);
      return tarefasConverter.paraListaTarefasDTO(listaTarefas);
   }

   public void excluirTarefaPorId(String id) {
      if (!tarefasRepository.existsById(id)) {
         throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID não encontrado !" + id);
      }
      tarefasRepository.deleteById(id);
   }

   public TarefasDTO alterarStatusTarefa(StatusNotificacaoEnum status, String id) {

      try {
         TarefasEntity entity = tarefasRepository.findById(id)
                 .orElseThrow(()-> new ResourceNotFoundException("Tarefa não encontrada " + id));
         entity.setStatus(status);
         return tarefasConverter.paraTarefaDTO(tarefasRepository.save(entity));
      } catch (ResourceNotFoundException e) {
         throw new ResourceNotFoundException(" Erro ao alterar status da Tarefa " + e.getCause());
      }
   }

   public TarefasDTO updateTarefasDTO(TarefasDTO dto, String id) {
      try {
         TarefasEntity entity = tarefasRepository.findById(id)
                 .orElseThrow(() -> new ResourceNotFoundException("Tarefa não encontrada ! " + id));
         tarefaUpdateConverter.updateTarefas(dto, entity);
         return tarefasConverter.paraTarefaDTO(tarefasRepository.save(entity));
      } catch (ResourceNotFoundException e) {
         throw new ResourceNotFoundException(" Erro ao alterar status da Tarefa " + e.getCause());
      }
   }
}