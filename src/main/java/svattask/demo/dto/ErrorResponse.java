package svattask.demo.dto;

public record ErrorResponse(
    String errorCode,
    String message
) {
}
