package svattask.demo.application;

import lombok.SneakyThrows;
import svattask.demo.dto.OperationMetricsDto;
import svattask.demo.dto.OverallComparisonDto;
import svattask.demo.dto.PerformanceResultDto;
import svattask.demo.enums.CrudMethods;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class PerformanceService {

    @SneakyThrows
    public PerformanceResultDto runPerformance(long listSize) {
        var arrayListThread = new MultiThreadLists(new ArrayList<>(), listSize);
        var linkedListThread = new MultiThreadLists(new LinkedList<>(), listSize);
        arrayListThread.start();
        linkedListThread.start();
        arrayListThread.join();
        linkedListThread.join();
        return mergeMeasuringResults(arrayListThread.getDetails(), linkedListThread.getDetails());
    }

    @SafeVarargs
    private PerformanceResultDto mergeMeasuringResults(Map<String, Map<CrudMethods, OperationMetricsDto>>... maps) {
        PerformanceResultDto resultDto = new PerformanceResultDto();
        resultDto.setDetails(new ArrayList<>());
        resultDto.setOverallComparison(new OverallComparisonDto());
        Map<String, Long> averageMap = new HashMap<>();
        for (var map : maps) {
            resultDto.getDetails().add(map);
            String key = map.keySet().iterator().next();
            var averageValue = countAverage(map.get(key));
            averageMap.put(key, averageValue);
        }
        resultDto.getOverallComparison().setAverageTime(averageMap);
        resultDto.getOverallComparison().setRecommendation("I don't know. Ask ChatGPT.");
        return resultDto;
    }

    private long countAverage(Map<CrudMethods, OperationMetricsDto> map) {
        long sum = 0;
        for (var crudMethod : CrudMethods.values()) {
            var operationMetricsDto = map.get(crudMethod);
            sum += operationMetricsDto.getStart() + operationMetricsDto.getEnd() + operationMetricsDto.getMiddle();
        }
        return sum / 12;
    }

}
