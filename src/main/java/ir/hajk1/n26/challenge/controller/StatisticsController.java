package ir.hajk1.n26.challenge.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import ir.hajk1.n26.challenge.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.DoubleSummaryStatistics;

/**
 * Created by k1 on 6/30/18.
 * email:<k1.tehrani@gmail.com>
 */
@RestController
@RequestMapping("/statistics")
public class StatisticsController {
    private final StatisticService statisticService;
    @Autowired
    ObjectMapper mapper;

    public StatisticsController(@Autowired StatisticService statisticService) {
        this.statisticService = statisticService;
    }

    @GetMapping(produces = "application/json")
    public ObjectNode getTransactionStatistics() {
        DoubleSummaryStatistics summaryStatistics = statisticService.getStatistics();
        ObjectNode objectNode = mapper.createObjectNode();
        objectNode.put("sum", summaryStatistics.getSum());
        objectNode.put("avg", summaryStatistics.getAverage());
        objectNode.put("max", summaryStatistics.getMax());
        objectNode.put("min", summaryStatistics.getMin());
        objectNode.put("count", summaryStatistics.getCount());
        return objectNode;
    }

}
