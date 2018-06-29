package ir.hajk1.n26.challenge.service;

import ir.hajk1.n26.challenge.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by k1 on 6/30/18.
 * email:<k1.tehrani@gmail.com>
 */
@Service
public class StatisticsServiceImpl implements StatisticService {
    private final TransactionService transactionService;

    @Autowired
    public StatisticsServiceImpl(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @Override
    public DoubleSummaryStatistics getStatistics() {
        List<Transaction> validTransactions = transactionService.getTransactionMap()
                .tailMap(beforeOneMinuteAgo())
                .values().stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
        if (validTransactions == null || validTransactions.isEmpty())
            return null;

        return validTransactions
                .parallelStream().collect(Collectors.summarizingDouble(Transaction::getAmount));
    }
}
