package ir.hajk1.n26.challenge.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import ir.hajk1.n26.challenge.model.EnquiryResult;
import ir.hajk1.n26.challenge.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by k1 on 6/30/18.
 * email:<k1.tehrani@gmail.com>
 */
@RestController
@RequestMapping("/statistics")
public class StatisticsController {

  private final StatisticService statisticService;
  private final ObjectMapper mapper;

  @Autowired
  public StatisticsController(StatisticService statisticService, ObjectMapper mapper) {
    this.statisticService = statisticService;
    this.mapper = mapper;
  }

  /**
   * Generates summary of current minute transaction amounts
   *
   * @return ObjectNode beacuase avg 'field' is different from 'Average' field in  DoubleSummaryStatistics
   */
  @GetMapping(produces = "application/json")
  public ObjectNode getTransactionStatistics() {
      EnquiryResult summaryStatistics = statisticService.getStatistics();
    ObjectNode objectNode = mapper.createObjectNode();
    objectNode.put("sum", summaryStatistics == null ? 0 : summaryStatistics.getSum());
      objectNode.put("avg", summaryStatistics == null ? 0 : summaryStatistics.getAvg());
    objectNode.put("max", summaryStatistics == null ? 0 : summaryStatistics.getMax());
    objectNode.put("min", summaryStatistics == null ? 0 : summaryStatistics.getMin());
    objectNode.put("count", summaryStatistics == null ? 0 : summaryStatistics.getCount());
    return objectNode;
  }

}
