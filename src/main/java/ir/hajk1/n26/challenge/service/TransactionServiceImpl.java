package ir.hajk1.n26.challenge.service;

import ir.hajk1.n26.challenge.model.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * Created by k1 on 6/29/18.
 * email:<k1.tehrani@gmail.com>
 */
@Service
public class TransactionServiceImpl implements TransactionService {
    private static final Logger logger = LoggerFactory.getLogger(TransactionServiceImpl.class);
    //    private ConcurrentNavigableMap<Long, List<Transaction>> transactions;
    //todo k1: must be removed later
    // Space Complexity: O(${windowInMs} / 1000 + ${removeExpiredStatisticsInMs} / 1000) -> O(1)
    private ConcurrentNavigableMap<Long, List<Transaction>> transactionMap;

    public TransactionServiceImpl() {
        transactionMap = new ConcurrentSkipListMap<>();
    }

    public ConcurrentNavigableMap<Long, List<Transaction>> getTransactionMap() {
        return transactionMap;
    }

    //todo k1: Is it really necessary to be synchronized?
    private synchronized void addTransaction(Transaction transaction) {
        List<Transaction> transactionAtGivenTime = transactionMap.get(transaction.getTimestamp());
        if (transactionAtGivenTime == null)
            transactionAtGivenTime = new ArrayList<>();
        transactionAtGivenTime.add(transaction);
        transactionMap.put(transaction.getTimestamp(), transactionAtGivenTime);
    }


    @Override
    public void persist(@Valid Transaction transaction) {
        if (logger.isInfoEnabled())
            logger.info("new transaction has been received: " + transaction);
        addTransaction(transaction);
    }
}
