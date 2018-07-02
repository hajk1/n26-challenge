package ir.hajk1.n26.challenge.service;

import ir.hajk1.n26.challenge.model.Transaction;
import ir.hajk1.n26.challenge.model.TransactionAmountListPerSecond;
import javax.validation.Valid;

/**
 * Created by k1 on 6/29/18.
 * email:<k1.tehrani@gmail.com>
 */
public interface TransactionService {

  void persist(@Valid Transaction transaction);

  TransactionAmountListPerSecond[] getTransactionAmountListPerSeconds();

}
