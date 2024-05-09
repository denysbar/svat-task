package svattask.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import svattask.demo.application.PerformanceService;
import svattask.demo.domain.exception.PerformanceWrongListSizeException;
import svattask.demo.dto.PerformanceResultDto;

@RestController
@RequestMapping("/performance")
@RequiredArgsConstructor
public class PerformanceController {

    private final PerformanceService performanceService;

    @GetMapping
    public ResponseEntity<PerformanceResultDto> runPerformance(@RequestParam long listSize) {
        if (listSize <= 2 || listSize > 1000000) {
            throw new PerformanceWrongListSizeException();
        }
        return ResponseEntity.ok(performanceService.runPerformance(listSize));
    }

}
