package com.isaiascosta.agendadortarefas.infrastructure.business;

import com.isaiascosta.agendadortarefas.infrastructure.business.dto.TarefasDTORecord;
import com.isaiascosta.agendadortarefas.infrastructure.business.mapper.TarefaUpdateConverter;
import com.isaiascosta.agendadortarefas.infrastructure.business.mapper.TarefasConverter;
import com.isaiascosta.agendadortarefas.infrastructure.entity.TarefasEntity;
import com.isaiascosta.agendadortarefas.infrastructure.enums.StatusNotificacaoEnum;
import com.isaiascosta.agendadortarefas.infrastructure.exceptions.ResourceNotFoundExecption;
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

   public TarefasDTORecord gravarTarefa(String token, TarefasDTORecord dto) {
      String email = jwtUtil.extraiEmailToken(token.substring(7)); // Extrai o e-mail do token JWT (remove "Bearer ")
//      dto.setDataCriacao(LocalDateTime.now()); // Define a data e hora de criação
//      dto.setStatus(StatusNotificacaoEnum.PENDENTE); // Define o status inicial como PENDENTE
//      dto.setEmailUsuario(email); // Associa o e-mail do usuário à tarefa
      TarefasDTORecord dtoFinal = new TarefasDTORecord(null, dto.nomeTarefa(), dto.descricao(),
              LocalDateTime.now(), dto.dataEvento(), email,
              null, StatusNotificacaoEnum.PENDENTE);
      TarefasEntity entity = tarefasConverter.paraTarefaEntity(dtoFinal); // Converte o DTO para entidade
      return tarefasConverter.paraTarefaDTO(tarefasRepository.save(entity)); // Salva a entidade no banco MongoDB

   }

   public List<TarefasDTORecord> buscarTarefasAgendadasPorPeriodo(LocalDateTime dataInicial, LocalDateTime dataFinal) {
      return tarefasConverter.paraListaTarefasDTORecord(
              tarefasRepository.findByDataEventoBetweenAndStatus(dataInicial,
                      dataFinal, StatusNotificacaoEnum.PENDENTE));
   }

   public List<TarefasDTORecord> buscarTarefasPorEmail(String token) {
      String email = jwtUtil.extraiEmailToken(token.substring(7));
      List<TarefasEntity> listaTarefas = tarefasRepository.findByEmailUsuario(email);
      return tarefasConverter.paraListaTarefasDTORecord(listaTarefas);
   }

   public void excluirTarefaPorId(String id) {
      if (!tarefasRepository.existsById(id)) {
         throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID não encontrado !" + id);
      }
      tarefasRepository.deleteById(id);
   }

   public TarefasDTORecord alterarStatusTarefa(StatusNotificacaoEnum status, String id) {

      try {
         TarefasEntity entity = tarefasRepository.findById(id)
                 .orElseThrow(() -> new ResourceNotFoundExecption("Tarefa não encontrada " + id));
         entity.setStatus(status);
         return tarefasConverter.paraTarefaDTO(tarefasRepository.save(entity));
      } catch (ResourceNotFoundExecption e) {
         throw new ResourceNotFoundExecption(" Erro ao alterar status da Tarefa " + e.getCause());
      }
   }

   public TarefasDTORecord updateTarefasDTORecord(TarefasDTORecord dto, String id) {
      try {
         TarefasEntity entity = tarefasRepository.findById(id)
                 .orElseThrow(() -> new ResourceNotFoundExecption("Tarefa não encontrada ! " + id));
         tarefaUpdateConverter.updateTarefas(dto, entity);
         return tarefasConverter.paraTarefaDTO(tarefasRepository.save(entity));
      } catch (ResourceNotFoundExecption e) {
         throw new ResourceNotFoundExecption(" Erro ao alterar status da Tarefa " + e.getCause());
      }
   }
}