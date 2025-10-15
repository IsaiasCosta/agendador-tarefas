package com.isaiascosta.agendadortarefas.infrastructure.business.dto;

import lombok.*;

import java.util.List;

//Lombok
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioDTO {
   //DTO: use somente o que fornecessario par  aplicação

   private String email;
   private String senha;


}
