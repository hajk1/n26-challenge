package ir.hajk1.n26.challenge.service;

import ir.hajk1.n26.challenge.model.Transaction;

import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by k1 on 6/29/18.
 * email:<k1.tehrani@gmail.com>
 */
public interface TransactionService {
    void persist(@Valid Transaction transaction);

    ConcurrentMap<Long, List<Double>> getTransactionMap();

}
