package svattask.demo.application;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import svattask.demo.domain.MeasurementsRepository;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PerformanceServiceTest {

    @InjectMocks
    private PerformanceService service;

    @Mock
    private MeasurementsRepository measurementsRepository;

    @Test
    public void testDataSavedInDb() {
        when(measurementsRepository.save(any())).thenReturn(null);

        service.runPerformance(10);

        verify(measurementsRepository, times(8)).save(any());
    }

}
