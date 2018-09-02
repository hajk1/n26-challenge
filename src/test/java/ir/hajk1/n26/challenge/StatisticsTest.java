package ir.hajk1.n26.challenge;

import ir.hajk1.n26.challenge.controller.StatisticsController;
import ir.hajk1.n26.challenge.service.StatisticService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Created by k1 on 6/29/18.
 * email:<k1.tehrani@gmail.com>
 */
@RunWith(SpringRunner.class)
@WebMvcTest(StatisticsController.class)
public class StatisticsTest {

  @MockBean
  private StatisticService statisticService;
  @Autowired
  private MockMvc mvc;

  @Test
  public void sampleStatisticsTest() throws Exception {
    //todo : be fixed
//    List<Double> doubleList = Arrays.asList(1d, 2d, 3d, 4d, 5d);
//    DoubleSummaryStatistics doubleSummaryStatistics = doubleList.stream()
//        .collect(Collectors.summarizingDouble(Double::doubleValue));
//
//    when(statisticService.getStatistics()).thenReturn(doubleSummaryStatistics);

//    mvc.perform(get("/statistics").accept("application/json"))
//        .andExpect(status().isOk())
//        .andExpect(content().contentTypeCompatibleWith("application/json"))
//        .andExpect(jsonPath("count").value(5))
//        .andExpect(jsonPath("sum").value(15))
//        .andExpect(jsonPath("avg").value(3))
//        .andExpect(jsonPath("max").value(5))
//        .andExpect(jsonPath("min").value(1));
  }
}


