package ir.hajk1.n26.challenge.service;

import ir.hajk1.n26.challenge.model.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.BiConsumer;

/**
 * Created by k1 on 6/29/18.
 * email:<k1.tehrani@gmail.com>
 */
@Service
public class TransactionServiceImpl implements TransactionService {
    private static final Logger logger = LoggerFactory.getLogger(TransactionServiceImpl.class);

    private ConcurrentMap<Long, List<Double>> transactionMap;

    public TransactionServiceImpl() {
        transactionMap = new ConcurrentHashMap<>(60);
    }

    public ConcurrentMap<Long, List<Double>> getTransactionMap() {
        return transactionMap;
    }

    private void addTransaction(Transaction transaction) {
        byte second = extractSecondPortion(transaction.getTimestamp());
        final BiConsumer<? super Long, ? super List<Double>> action =
                (key, value) -> transactionMap.computeIfAbsent(key, x -> new ArrayList<>())
                        .add(transaction.getAmount());
        action.accept((long) second, new ArrayList<>());
    }

    private byte extractSecondPortion(long timestamp) {
        LocalDateTime transactionDate =
                LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault());
        return (byte) transactionDate.getSecond();
    }

    @Override
    public void persist(@Valid Transaction transaction) {
        if (logger.isInfoEnabled())
            logger.info("new transaction has been received: " + transaction);
        addTransaction(transaction);
    }

    @Scheduled(cron = "0/1 * * * * *")
    public void cronJob() {
        byte currentSecond = (byte) LocalDateTime.now().getSecond();
        transactionMap.put((long) currentSecond, new ArrayList<>());
        if (logger.isDebugEnabled())
            logger.debug("resetting Amounts for expired second index:" + currentSecond);
    }
}
