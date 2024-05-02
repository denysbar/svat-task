package svattask.demo.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import svattask.demo.enums.CrudMethods;

import java.util.List;
import java.util.Map;

@Data
@Builder
@ToString
public class PerformanceResultDto {

    private List<Map<String, Map<CrudMethods, OperationMetricsDto>>> details;

    private OverallComparisonDto overallComparison;

    public String convertDtoToJson() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(this);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
