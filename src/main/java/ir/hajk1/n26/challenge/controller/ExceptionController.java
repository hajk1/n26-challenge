package ir.hajk1.n26.challenge.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;


@ControllerAdvice
public class ExceptionController {

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @ExceptionHandler(Exception.class)
  public ResponseEntity<Void> handleException() {
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
