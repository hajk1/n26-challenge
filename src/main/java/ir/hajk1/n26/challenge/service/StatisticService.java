package ir.hajk1.n26.challenge.service;

import ir.hajk1.n26.challenge.model.EnquiryResult;

import java.time.Instant;

/**
 * Created by k1 on 6/29/18.
 * email:<k1.tehrani@gmail.com>
 */
public interface StatisticService {
    EnquiryResult getStatistics();

    default Long beforeOneMinuteAgo() {
        return Instant.now().minusSeconds(60L).toEpochMilli();
    }


}
