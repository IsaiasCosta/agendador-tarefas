package com.isaiascosta.agendadortarefas.infrastructure.security;


import com.isaiascosta.agendadortarefas.infrastructure.business.dto.UsuarioDTO;
import com.isaiascosta.agendadortarefas.infrastructure.client.UsuarioClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl {

   @Autowired
   private UsuarioClient client;

   // Implementação do método para carregar detalhes do usuário pelo e-mail e token

   public UserDetails carregaDadosUsuarioEmailToken(String email, String token) {
      UsuarioDTO usuarioDTO = client.buscarPorEmail(email,token);
      return User
              .withUsername(usuarioDTO.getEmail()) // Define o nome de usuário como o e-mail
              .password(usuarioDTO.getSenha()) // Define a senha do usuário
              .build(); // Constrói o objeto UserDetails
   }
}
