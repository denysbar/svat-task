package svattask.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import svattask.demo.application.PerformanceService;

import java.util.concurrent.ExecutionException;

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
    void contextLoads2() throws ExecutionException, InterruptedException {
        var re = performanceService.runPerformance(100000);
        System.out.println(re);
    }

}
