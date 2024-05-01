package svattask.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import svattask.demo.application.PerformanceService;

@SpringBootTest
class DemoApplicationTests {

    @Test
    void contextLoads() {
        var re = new PerformanceService().runPerformance(100);
        System.out.println(re);
    }

}
