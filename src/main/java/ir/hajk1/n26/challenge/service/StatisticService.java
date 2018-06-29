package ir.hajk1.n26.challenge.service;

import java.time.Instant;
import java.util.DoubleSummaryStatistics;

/**
 * Created by k1 on 6/29/18.
 * email:<k1.tehrani@gmail.com>
 */
public interface StatisticService {
    DoubleSummaryStatistics getStatistics();

    default Long beforeOneMinuteAgo() {
        return Instant.now().minusSeconds(60L).toEpochMilli();
    }


}
