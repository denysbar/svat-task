package svattask.demo.dto;

import lombok.Data;
import lombok.ToString;

import java.util.Map;

@Data
@ToString
public class OverallComparisonDto {
    private Map<String, Long> averageTime;
    private String recommendation;
}
