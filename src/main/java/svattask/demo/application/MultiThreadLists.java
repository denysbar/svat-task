package svattask.demo.application;

import lombok.Getter;
import svattask.demo.dto.OperationMetricsDto;
import svattask.demo.enums.CrudMethods;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class MultiThreadLists extends Thread {

    private final long size;

    private final List<String> list;

    private Map<String, Map<CrudMethods, OperationMetricsDto>> details = new HashMap<>();

    public MultiThreadLists(List<String> list, long size) {
        this.size = size;
        this.list = list;
    }

    @Override
    public void run() {
        MeasuringService measuringService = new MeasuringService(list, size);
        measuringService.collectStatistics();
        details = measuringService.getDetails();
    }

}
