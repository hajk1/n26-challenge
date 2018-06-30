package ir.hajk1.n26.challenge.controller;

import ir.hajk1.n26.challenge.model.Transaction;
import ir.hajk1.n26.challenge.service.TransactionService;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by k1 on 6/29/18.
 * email:<k1.tehrani@gmail.com>
 */
@RestController
@RequestMapping("/transactions")
public class TransactionController {
    private final TransactionService transactionService;


    public TransactionController(@Autowired TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<Void> saveTransaction(@Valid @RequestBody Transaction transaction) {
        if (transaction.getTimestamp() != null &&
                transaction.getTimestamp() > Instant.now().minus(60, ChronoUnit.SECONDS).toEpochMilli()) {
            transactionService.persist(transaction);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
}
