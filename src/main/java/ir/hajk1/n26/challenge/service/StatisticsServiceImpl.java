package ir.hajk1.n26.challenge.service;

import ir.hajk1.n26.challenge.model.TransactionAmountListPerSecond;
import java.util.Arrays;
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

  /**
   * Used for generating statistics
   *
   * @return DoubleSummaryStatistics which contains desired information
   */
  @Override
  public DoubleSummaryStatistics getStatistics() {
    List<TransactionAmountListPerSecond> list = Arrays
        .asList(transactionService.getTransactionAmountListPerSeconds());
    List<Double> validTransactions = list.stream()
        .flatMap(f -> f.getAmountList()
            .stream())
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
