package svattask.demo.application;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import svattask.demo.domain.MeasurementsRepository;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PerformanceServiceTest {

    @Mock
    private PerformanceService service;

    @Mock
    private MeasurementsRepository measurementsRepository;

    @Test
    public void testDataSavedInDb() {
        when(measurementsRepository.save(any())).thenReturn(any());

        service.runPerformance(10);

        verify(measurementsRepository, times(1)).save(any());
    }

}
