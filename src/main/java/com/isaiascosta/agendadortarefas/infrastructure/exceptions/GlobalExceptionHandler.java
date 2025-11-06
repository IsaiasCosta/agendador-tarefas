package com.isaiascosta.agendadortarefas.infrastructure.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

   @ExceptionHandler(ResourceNotFoundExecption.class)
   public ResponseEntity<String> handleResourceNotFoundExecption(ResourceNotFoundExecption ex) {
      return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);

   }

   @ExceptionHandler(UnauthorizedException.class)
   public ResponseEntity<String> handleUnauthorizedException(UnauthorizedException ex) {
      return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNAUTHORIZED);

   }

}
