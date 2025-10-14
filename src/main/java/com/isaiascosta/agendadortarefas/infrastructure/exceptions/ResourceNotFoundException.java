package com.isaiascosta.agendadortarefas.infrastructure.exceptions;

import org.springframework.data.crossstore.ChangeSetPersister;

public class ResourceNotFoundException extends  RuntimeException{
   public ResourceNotFoundException(String mensagem){
      super(mensagem);
   }
   public  ResourceNotFoundException(String mensagem,Throwable throwable){
      super(mensagem,throwable);
   }

}
