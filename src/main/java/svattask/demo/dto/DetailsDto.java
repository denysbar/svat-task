package svattask.demo.dto;

import lombok.Data;

import java.util.Map;

@Data
public class DetailsDto {

    private Map<String, OperationMetricsDto> arrayList;

    private Map<String, OperationMetricsDto> linkedList;

}
