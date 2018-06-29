package ir.hajk1.n26.challenge;

import ir.hajk1.n26.challenge.controller.TransactionController;
import ir.hajk1.n26.challenge.model.Transaction;
import ir.hajk1.n26.challenge.service.StatisticService;
import ir.hajk1.n26.challenge.service.TransactionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Instant;
import java.util.DoubleSummaryStatistics;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

/**
 * Created by k1 on 6/29/18.
 * email:<k1.tehrani@gmail.com>
 */
@RunWith(SpringRunner.class)
@WebMvcTest(TransactionController.class)
public class StatisticsTest {
    @MockBean
    private TransactionService transactionService;
    @MockBean
    private StatisticService statisticService;
    @Autowired
    private MockMvc mvc;

    @Test
    public void sampleStatisticsTest() throws Exception {
        transactionService.persist(new Transaction(5.5, System.currentTimeMillis() - 10000));
        transactionService.persist(new Transaction(15.5, System.currentTimeMillis() - 9000));
        transactionService.persist(new Transaction(25.2, System.currentTimeMillis() - 8000));
        transactionService.persist(new Transaction(65.5, System.currentTimeMillis() - 7000));
        transactionService.persist(new Transaction(5.7, System.currentTimeMillis() - 6000));
        transactionService.persist(new Transaction(5.8, System.currentTimeMillis() - 5000));
        transactionService.persist(new Transaction(3.5, System.currentTimeMillis() - 4000));
        transactionService.persist(new Transaction(2.8, System.currentTimeMillis() - 3000));
        transactionService.persist(new Transaction(9.5, System.currentTimeMillis() - 2000));
        transactionService.persist(new Transaction(12.3, System.currentTimeMillis() - 1000));
        mvc.perform(post("/transactions")
                .contentType("application/json")
                .content("{\"amount\": 5.5,\"timestamp\": " + (Instant.now().toEpochMilli() - 10000) + "}"));


        DoubleSummaryStatistics summary = statisticService.getStatistics();
        assertEquals(summary.getCount(), 10l);
        assertEquals(summary.getSum(), 151.3, 0.0);
        assertEquals(summary.getMax(), 65.5, 0.0);
        assertEquals(summary.getMin(), 2.8, 0.0);
        assertEquals(summary.getAverage(), 151.3 / 10, 0.0);
    }

}


