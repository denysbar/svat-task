package svattask.demo.draft;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import svattask.demo.application.PerformanceService;

@SpringBootTest
@RequiredArgsConstructor
class DemoApplicationTests {

    private final PerformanceService performanceService;

    @Test
    void contextLoads2() {
        var re = performanceService.runPerformance(100000);
        System.out.println(re);
    }

}
