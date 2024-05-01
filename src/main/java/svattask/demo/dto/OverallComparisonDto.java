package svattask.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.Map;

@Data
@AllArgsConstructor
@ToString
public class OverallComparisonDto {

    private Map<String, Long> averageTime;

    private String recommendation;

}
