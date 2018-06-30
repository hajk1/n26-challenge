package ir.hajk1.n26.challenge.controller;


import ir.hajk1.n26.challenge.service.TransactionServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;


@ControllerAdvice
public class ExceptionController {
  private static final Logger logger = LoggerFactory.getLogger(ExceptionController.class);

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @ExceptionHandler(Exception.class)
  public ResponseEntity<Void> handleException() {
    if(logger.isErrorEnabled())
      logger.error("Invalid Request received");
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
