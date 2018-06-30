package ir.hajk1.n26.challenge.service;

import java.util.Collection;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by k1 on 6/30/18.
 * email:<k1.tehrani@gmail.com>
 */
@Service
public class StatisticsServiceImpl implements StatisticService {

  private static final Logger logger = LoggerFactory.getLogger(StatisticsServiceImpl.class);

  private final TransactionService transactionService;

  @Autowired
  public StatisticsServiceImpl(TransactionService transactionService) {
    this.transactionService = transactionService;
  }

  @Override
  public DoubleSummaryStatistics getStatistics() {
    List<Double> validTransactions = transactionService.getTransactionMap()
        .tailMap(beforeOneMinuteAgo())
        .values().stream()
        .flatMap(Collection::stream)
        .collect(Collectors.toList());
    if (validTransactions == null || validTransactions.isEmpty()) {
      return null;
    }

    DoubleSummaryStatistics summaryStatistics = validTransactions
        .parallelStream().collect(Collectors.summarizingDouble(Double::doubleValue));
    if (logger.isInfoEnabled()) {
      logger.info("summaryStatistics=" + summaryStatistics);
    }
    return summaryStatistics;
  }
}
