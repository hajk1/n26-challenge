package ir.hajk1.n26.challenge.controller;

import ir.hajk1.n26.challenge.exception.InvalidTimestampException;
import ir.hajk1.n26.challenge.model.Transaction;
import ir.hajk1.n26.challenge.service.TransactionService;
import java.time.Duration;
import java.util.function.Supplier;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by k1 on 6/29/18.
 * email:<k1.tehrani@gmail.com>
 */
@RestController
@RequestMapping("/transactions")
public class TransactionController {

  private static final Supplier<Long> CURRENT_TIME_MILLIS = System::currentTimeMillis;
  private static final long ONE_MINUTE = Duration.ofMinutes(1).toMillis();

  private final TransactionService transactionService;


  public TransactionController(@Autowired TransactionService transactionService) {
    this.transactionService = transactionService;
  }

  @PostMapping(consumes = "application/json")
  @ResponseStatus(HttpStatus.CREATED)
  public void saveTransaction(@Valid @RequestBody Transaction transaction) {
    validate(transaction.getTimestamp());
    transactionService.persist(transaction);
  }

  private void validate(Long timestamp) {
    if (timestamp == null) {
      throw new InvalidTimestampException("Null timestamp is invalid");
    }
    if (timestamp < (CURRENT_TIME_MILLIS.get() - ONE_MINUTE)) {
      throw new InvalidTimestampException("Older than 60 second is not valid timestamp");
    }
    if (timestamp > CURRENT_TIME_MILLIS.get()) {
      throw new InvalidTimestampException("future timestmap is not valid");
    }
  }
}
