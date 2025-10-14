package com.isaiascosta.agendadortarefas.infrastructure.controller;

import com.isaiascosta.agendadortarefas.infrastructure.business.TarefasService;
import com.isaiascosta.agendadortarefas.infrastructure.business.dto.TarefasDTO;
import com.isaiascosta.agendadortarefas.infrastructure.enums.StatusNotificacaoEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/tarefas")
@RequiredArgsConstructor
public class TarefasController {
   @Autowired
   private final TarefasService tarefasService;


   @PostMapping // Mapeia requisições HTTP POST para este endpoint
   public ResponseEntity<TarefasDTO> gravarTarefas(@RequestBody TarefasDTO dto,
                                                   @RequestHeader("Authorization") String token) { // Corrigido: "Authorization"
      return ResponseEntity.ok(tarefasService.gravarTarefa(token, dto)); // Chama o serviço para salvar a tarefa e retorna 200 OK com o DTO
   }

   //Buscar dados em periodo de tempo usando o between
   @GetMapping("/eventos")
   public ResponseEntity<List<TarefasDTO>> buscaListaDeTarefasPorPeriodo(@RequestParam
                                                                         @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                                                         LocalDateTime dataInicial,
                                                                         @RequestParam
                                                                         @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                                                         LocalDateTime dataFinal) {
      return ResponseEntity.ok(tarefasService.buscarTarefasAgendadasPorPeriodo(dataInicial, dataFinal));
   }
   //Buscar dados por email
   @GetMapping
   public ResponseEntity<List<TarefasDTO>> buscarTarefasPorEmail(@RequestHeader("Authorization") String token) {
      return ResponseEntity.ok(tarefasService.buscarTarefasPorEmail(token));

   }
   @DeleteMapping
   public  ResponseEntity<Void>excluirTarefaPorId(@RequestParam("id") String id){
      tarefasService.excluirTarefaPorId(id);
      return ResponseEntity.ok().build();

   }
   @PatchMapping
   public  ResponseEntity<TarefasDTO>alterarStatusTarefa(@RequestParam("status") StatusNotificacaoEnum status,
                                                         @RequestParam("id") String id){
     return ResponseEntity.ok( tarefasService.alterarStatusTarefa(status, id));

   }
   @PutMapping
   public ResponseEntity<TarefasDTO> updateTarefasDTO(@RequestBody TarefasDTO dto,
                                                      @RequestParam ("id") String id){
      return  ResponseEntity.ok(tarefasService.updateTarefasDTO(dto,id));
   }
}
