package ir.hajk1.n26.challenge.service;

import ir.hajk1.n26.challenge.model.Transaction;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Created by k1 on 6/29/18.
 * email:<k1.tehrani@gmail.com>
 */
@Service
public class TransactionServiceImpl implements TransactionService {
    private static final Logger logger = LoggerFactory.getLogger(TransactionServiceImpl.class);
    private ConcurrentNavigableMap<Long, List<Double>> transactionMap;

    public TransactionServiceImpl() {
        transactionMap = new ConcurrentSkipListMap<>();
    }

    public ConcurrentNavigableMap<Long, List<Double>> getTransactionMap() {
        return transactionMap;
    }

    //todo k1: Is it really necessary to be synchronized?
    private void addTransaction(Transaction transaction) {
        List<Double> transactionAtGivenTime = transactionMap.get(transaction.getTimestamp());
        if (transactionAtGivenTime == null)
            transactionAtGivenTime = new ArrayList<>();
        transactionAtGivenTime.add(transaction.getAmount());
        transactionMap.put(transaction.getTimestamp(), transactionAtGivenTime);
    }


    @Override
    public void persist(@Valid Transaction transaction) {
        if (logger.isInfoEnabled())
            logger.info("new transaction has been received: " + transaction);
        addTransaction(transaction);
    }
}
