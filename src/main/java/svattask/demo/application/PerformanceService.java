package svattask.demo.application;

import org.springframework.stereotype.Service;
import svattask.demo.dto.OperationMetricsDto;
import svattask.demo.dto.OverallComparisonDto;
import svattask.demo.dto.PerformanceResultDto;
import svattask.demo.enums.CrudMethods;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PerformanceService {

    public PerformanceResultDto runPerformance(long listSize) {
        List<MultiThreadLists> threads = new ArrayList<>();
        // Run all threads
        PerformanceData.COLLECTION_TYPES
                .forEach(collection -> {
                    var listThread = new MultiThreadLists(collection, listSize);
                    threads.add(listThread);
                    listThread.start();
                });
        // Wait for all thread completion
        threads.forEach(thread -> {
            try {
                thread.join();
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        return mergeMeasuringResults(threads);
    }

    private PerformanceResultDto mergeMeasuringResults(List<MultiThreadLists> threads) {
        var details = threads
                .stream()
                .map(MultiThreadLists::getDetails)
                .toList();
        Map<String, Long> averageMap = new HashMap<>();
        for (var detail : details) {
            String key = detail.keySet().iterator().next();
            var averageValue = countAverage(detail.get(key));
            averageMap.put(key, averageValue);
        }
        return PerformanceResultDto.builder()
                .details(details)
                .overallComparison(new OverallComparisonDto(averageMap, "I don't know. Ask ChatGPT."))
                .build();
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
