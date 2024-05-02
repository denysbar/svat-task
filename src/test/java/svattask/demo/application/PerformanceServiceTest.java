package svattask.demo.application;

import com.google.gson.Gson;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import svattask.demo.DemoApplication;
import svattask.demo.dto.PerformanceResultDto;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import static java.lang.String.format;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DemoApplication.class)
public class PerformanceServiceTest {

    @Autowired
    private MockMvc mvc;

    @Mock
    private PerformanceService service;

    public static String readTestFile(String fileName) {
        try {
            return Files.readString(Paths.get(fileName), Charset.defaultCharset());
        }
        catch (IOException var2) {
            throw new RuntimeException("" + var2 + " Cannot read test file ");
        }
    }

    private PerformanceResultDto getMeasureResponse() {
        var json = readTestFile("src/test/resources/data/application/performanceResultDto.json");
        Gson gson = new Gson(); // Or use new GsonBuilder().create();
        return gson.fromJson(json, PerformanceResultDto.class);
    }

    @Test
    @SneakyThrows
    public void testReturns200Ok() {
        when(service.runPerformance(10)).thenReturn(getMeasureResponse());

        mvc.perform(MockMvcRequestBuilders
                        .get("/performance")
                        .accept(MediaType.APPLICATION_JSON)
                        .param("listSize", "10")
                )
                .andDo(print())
                .andExpect(status().isOk());
    }
}
