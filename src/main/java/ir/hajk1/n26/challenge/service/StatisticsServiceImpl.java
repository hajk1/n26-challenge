package ir.hajk1.n26.challenge.service;

import ir.hajk1.n26.challenge.model.EnquiryResult;
import ir.hajk1.n26.challenge.model.TransactionAmountListPerSecond;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

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
    public EnquiryResult getStatistics() {
        List<TransactionAmountListPerSecond> list = Arrays
                .asList(transactionService.getTransactionAmountListPerSeconds());
        EnquiryResult enquiryResult = new EnquiryResult();
        list.stream().filter(p -> p.getTransactionCount() > 0)
                .forEach(p -> {
                    enquiryResult.addCount(p.getTransactionCount());
                    enquiryResult.addSum(p.getTotalAmountPerSecond());
                    enquiryResult.checkMax(p.getMaxAmountPerSecond());
                    enquiryResult.chekMin(p.getMinAmountPerSecond());
                });

//    if (validTransactions == null || validTransactions.isEmpty()) {
//      return null;
//    }
//    DoubleSummaryStatistics summaryStatistics = validTransactions
//        .parallelStream().collect(Collectors.summarizingDouble(Double::doubleValue));
        if (logger.isInfoEnabled()) {
            logger.info("summaryStatistics=" + enquiryResult);
        }
        return enquiryResult;
    }
}
