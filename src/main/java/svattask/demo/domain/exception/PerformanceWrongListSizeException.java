package svattask.demo.domain.exception;

import lombok.Getter;
import svattask.demo.dto.ErrorResponse;

@Getter
public class PerformanceWrongListSizeException extends RuntimeException {

    private final ErrorResponse errorResponse = new ErrorResponse("INVALID_ARGUMENT_VALUE", "Wrong list size value. It should be between 3 and 10000000");

}
