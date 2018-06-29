package ir.hajk1.n26.challenge;

import ir.hajk1.n26.challenge.controller.TransactionController;
import ir.hajk1.n26.challenge.service.TransactionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Instant;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by k1 on 6/29/18.
 * email:<k1.tehrani@gmail.com>
 */
@RunWith(SpringRunner.class)
@WebMvcTest(TransactionController.class)
public class TransactionTest {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private TransactionService transactionService;

//    @Before
//    public void setUp() throws Exception {
//        WithinLastMinuteValidator.CURRENT_MILLIS = () -> 1478192205010L;
//    }


    @Test
    public void shouldAcceptValidRequest() throws Exception {
        mvc.perform(post("/transactions")
                .contentType("application/json")
                .content("{\"amount\": 12.3,\"timestamp\": " + Instant.now().toEpochMilli() + "}"))
                .andExpect(status().isCreated())
                .andExpect(content().bytes(new byte[0]));
    }

//    @Test
//    public void shouldValidateRequest() throws Exception {
//        mvc.perform(post("/transactions")
//                .contentType("application/json")
//                .content("{\"timestamp\": 0}"))
//                .andExpect(status().isNoContent())
//                .andExpect(content().bytes(new byte[0]));
//
//        verifyZeroInteractions(statisticsService);
//    }

    @Test
    public void shouldHandleInvalidTimestampException() throws Exception {
//        doThrow(new InvalidTimestampException(""))
//                .when(statisticsService).record(any(Transaction.class));

        mvc.perform(post("/transactions")
                .contentType("application/json")
                .content("{\"amount\": 12.3,\"timestamp\": 1478192204000}"))
                .andExpect(status().isNoContent())
                .andExpect(content().bytes(new byte[0]));
    }

}
