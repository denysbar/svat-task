package svattask.demo.dto;

import lombok.Data;
import lombok.ToString;
import svattask.demo.enums.CrudMethods;

import java.util.List;
import java.util.Map;

@Data
@ToString
public class PerformanceResultDto {

    private List<Map<String, Map<CrudMethods, OperationMetricsDto>>> details;
    private OverallComparisonDto overallComparison;

}
