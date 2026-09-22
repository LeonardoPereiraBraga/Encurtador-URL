package io.github.Leonardo.Encurtador.URL.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(UrlExpiradaException.class)
    private ResponseEntity<String> urlExpiradaHanlder(UrlExpiradaException exception){
        return ResponseEntity.status(HttpStatus.GONE).body(exception.getMessage());
    }
    @ExceptionHandler(EmailExistenteException.class)
    private ResponseEntity<String> urlExpiradaHanlder(EmailExistenteException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }
}
