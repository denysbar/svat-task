package svattask.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import svattask.demo.dto.PerformanceResultDto;

@RestController
@RequestMapping("/performance")
public class PerformanceController {

    @GetMapping
    public void performance(
            @RequestParam long listSize
    ) {

    }

}
