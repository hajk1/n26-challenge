package ir.hajk1.n26.challenge.service;

import ir.hajk1.n26.challenge.model.Transaction;
import ir.hajk1.n26.challenge.model.TransactionAmountListPerSecond;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * Created by k1 on 6/29/18.
 * email:<k1.tehrani@gmail.com>
 */
@Service
public class TransactionServiceImpl implements TransactionService {

  private static final Logger logger = LoggerFactory.getLogger(TransactionServiceImpl.class);

  /**
   * This 60 length array holds each second transactions
   */
  private TransactionAmountListPerSecond[] transactionAmountListPerSeconds = new TransactionAmountListPerSecond[60];

  public TransactionServiceImpl() {
    for (int i = 0; i < transactionAmountListPerSeconds.length; i++) {
      transactionAmountListPerSeconds[i] = new TransactionAmountListPerSecond();
    }
  }

  public TransactionAmountListPerSecond[] getTransactionAmountListPerSeconds() {
    return transactionAmountListPerSeconds;
  }

  private void addTransaction(Transaction transaction) {
    byte second = extractSecondPortion(transaction.getTimestamp());
      transactionAmountListPerSeconds[second].addNewTransaction(transaction.getAmount());
//    synchronized (transactionAmountListPerSeconds[second]) {
//      transactionAmountListPerSeconds[second].getAmountList().add(transaction.getAmount());
//    }
  }

  /**
   * Extract Second portion of timestamp
   *
   * @return (0 - 59)
   */
  private byte extractSecondPortion(long timestamp) {
    LocalDateTime transactionDate =
        LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault());
    return (byte) transactionDate.getSecond();
  }

  @Override
  public void persist(@Valid Transaction transaction) {
    if (logger.isInfoEnabled()) {
      logger.info("new transaction has been received: " + transaction);
    }
    addTransaction(transaction);
  }

  /**
   * Runs Every second and resets transaction amounts for that special second
   */
  @Scheduled(cron = "0/1 * * * * *")
  public void expireCacheCron() {
    byte currentSecond = (byte) LocalDateTime.now().getSecond();
    synchronized (transactionAmountListPerSeconds[currentSecond]) {
      transactionAmountListPerSeconds[currentSecond].reset();
    }
    if (logger.isDebugEnabled()) {
      logger.debug("resetting Amounts for expired second index:" + currentSecond);
    }
  }
}
