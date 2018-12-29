package ir.hajk1.n26.challenge.service;

import ir.hajk1.n26.challenge.model.EnquiryResult;
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
    public EnquiryResult getStatistics() {
      EnquiryResult enquiryResult = transactionService.getCurrentResult();
        if (logger.isInfoEnabled()) {
            logger.info("summaryStatistics=" + enquiryResult);
        }
        return enquiryResult;
    }
}
