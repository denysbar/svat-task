package svattask.demo.testing;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import svattask.demo.application.PerformanceService;

@SpringBootTest
class DemoApplicationTests {

    @Test
    void contextLoads() {
        var re = new PerformanceService().runPerformance(100000);
        System.out.println(re);
    }

    @Autowired
    private PerformanceService performanceService;

    @Test
    void contextLoads2() {
        var re = performanceService.runPerformance(100000);
        System.out.println(re);
    }

}
