package svattask.demo.application;

import lombok.Getter;
import svattask.demo.enums.CrudMethods;
import svattask.demo.dto.OperationMetricsDto;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MeasuringService {

    private final List<String> list;

    private final long size;

    @Getter
    private Map<String, Map<CrudMethods, OperationMetricsDto>> details = new HashMap<>();

    public MeasuringService(List<String> list, long size) {
        this.list = list;
        this.size = size;
    }

    public void collectStatistics() {
        Map<CrudMethods, OperationMetricsDto> map = new HashMap<>();
        Arrays.stream(CrudMethods.values())
                .forEach(method -> {
                    var operationMetrics = new OperationMetricsDto();
                    for (int i = 0; i < size; i++) {
                        if (i == 0) {
                            long executionTime = getExecutionTime(method);
                            operationMetrics.setStart(executionTime);
                        }
                        else if (i == size / 2) {
                            Long executionTime = getExecutionTime(method);
                            operationMetrics.setMiddle(executionTime);
                        }
                        else if (i == size - 1) {
                            Long executionTime = getExecutionTime(method);
                            operationMetrics.setEnd(executionTime);
                        }
                        else {
                            listModification(method);
                        }
                    }
                    map.put(method, operationMetrics);
                    details.put(list.getClass().getSimpleName(), map);
                });
    }

    private void listModification(CrudMethods crudMethod) {
        switch (crudMethod) {
            case CREATE -> list.addFirst("c");
            case READ -> list.getFirst();
            case UPDATE -> list.set(0, "u");
            case DELETE -> list.removeLast();
            default -> throw new RuntimeException("Method is not found");
        }
    }

    private long getExecutionTime(CrudMethods crudMethod) {
        Instant start = Instant.now();
        listModification(crudMethod);
        Instant end = Instant.now();
        return Duration.between(start, end).getNano();
    }

}
