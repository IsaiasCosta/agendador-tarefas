package com.isaiascosta.agendadortarefas.infrastructure.controller;

import com.isaiascosta.agendadortarefas.infrastructure.business.TarefasService;
import com.isaiascosta.agendadortarefas.infrastructure.business.dto.TarefasDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tarefas")
@RequiredArgsConstructor
public class TarefasController {
   private final TarefasService tarefasService;


   @PostMapping // Mapeia requisições HTTP POST para este endpoint
   public ResponseEntity<TarefasDTO> gravarTarefas(@RequestBody TarefasDTO dto,
                                                   @RequestHeader("Authorization") String token) { // Corrigido: "Authorization"
      return ResponseEntity.ok(tarefasService.gravarTarefa(token, dto)); // Chama o serviço para salvar a tarefa e retorna 200 OK com o DTO
   }
}
