package svattask.demo.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class OperationMetricsDto {
    private Long start;
    private Long middle;
    private Long end;

}
