package svattask.demo.application;

import org.springframework.stereotype.Service;
import svattask.demo.dto.OperationMetricsDto;
import svattask.demo.dto.OverallComparisonDto;
import svattask.demo.dto.PerformanceResultDto;
import svattask.demo.enums.CrudMethods;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class PerformanceService {

    public PerformanceResultDto runPerformance(long listSize) {
        // Start CompletableFuture tasks asynchronously
        List<CompletableFuture<Map<String, Map<CrudMethods, OperationMetricsDto>>>> threads = new ArrayList<>();
        PerformanceData.COLLECTION_TYPES
                .forEach(collection -> {
                    var thread = collectStatistics(collection, listSize);
                    threads.add(thread);
                });
        // Wait for both tasks to complete
        CompletableFuture.allOf(threads.toArray(new CompletableFuture[0])).join();

        // Return the results
        return mergeMeasuringResults(threads.stream()
                .map(x -> {
                    try {
                        return x.get();
                    }
                    catch (InterruptedException |
                            ExecutionException e) {
                        throw new RuntimeException(e);
                    }
                }).toList()
        );
    }

    private CompletableFuture<Map<String, Map<CrudMethods, OperationMetricsDto>>> collectStatistics(
            List<String> list, long size
    ) {
        AtomicReference<List<String>> list2 = new AtomicReference<>(list);
        Map<String, Map<CrudMethods, OperationMetricsDto>> details = new HashMap<>();
        Map<CrudMethods, OperationMetricsDto> map = new HashMap<>();
        return CompletableFuture.supplyAsync(() -> {
            Arrays.stream(CrudMethods.values())
                    .forEach(method -> {
                        var operationMetrics = new OperationMetricsDto();
                        for (int i = 0; i < size; i++) {
                            Instant start = Instant.now();
                            list2.set(listModification(list2.get(), method));
                            Instant end = Instant.now();
                            long executionTime = Duration.between(start, end).getNano();
                            if (i == 0) {
                                operationMetrics.setStart(executionTime);
                            }
                            else if (i == size / 2) {
                                operationMetrics.setMiddle(executionTime);
                            }
                            else if (i == size - 1) {
                                operationMetrics.setEnd(executionTime);
                            }
                        }

                        map.put(method, operationMetrics);
                        details.put(list.getClass().getSimpleName(), map);
                    });
            return details;
        });

    }

    private List<String> listModification(List<String> list, CrudMethods crudMethod) {
        switch (crudMethod) {
            case CREATE -> list.addFirst("a");
            case READ -> list.getFirst();
            case UPDATE -> list.set(0, "b");
            case DELETE -> list.removeLast();
            default -> throw new RuntimeException("Method is not found");
        }
        return list;
    }

    private PerformanceResultDto mergeMeasuringResults(
            List<Map<String, Map<CrudMethods, OperationMetricsDto>>> details
    ) {
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
