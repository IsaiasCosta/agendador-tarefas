package com.isaiascosta.agendadortarefas.infrastructure.client;

import com.isaiascosta.agendadortarefas.infrastructure.business.dto.UsuarioDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "usuario", url = "${usuario.url}") // Define um cliente Feign para comunicar com o microsserviço de usuário
public interface UsuarioClient {


   @GetMapping("/usuario/por-email") // Faz uma requisição GET para o endpoint /usuario do serviço remoto
   UsuarioDTO buscarPorEmail(@RequestParam("email") String email, // Envia o e-mail como parâmetro na URL
                             @RequestHeader("Authorization") String token); // Envia o token JWT no cabeçalho para autenticação
}
