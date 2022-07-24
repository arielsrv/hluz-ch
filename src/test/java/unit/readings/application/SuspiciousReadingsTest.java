package unit.readings.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.hluz.readings.application.ReadingsDto;
import com.hluz.readings.application.SuspiciousReadings;
import com.hluz.readings.domain.Reading;
import com.hluz.readings.domain.ReadingsRepository;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SuspiciousReadingsTest {

    private ReadingsRepository readingsRepository;
    private SuspiciousReadings suspiciousReadings;

    @BeforeEach
    public void setUp() {
        this.readingsRepository = mock(ReadingsRepository.class);
        this.suspiciousReadings = new SuspiciousReadings(this.readingsRepository);
    }

    @Test
    public void get_suspicious_readings() {
        when(this.readingsRepository.getAll()).thenReturn(getAllReadings());

        ReadingsDto actual = this.suspiciousReadings.getSuspiciousReadings();

        assertNotNull(actual);
        assertNotNull(actual.readingDto);
        assertEquals("1", actual.readingDto.get(0).clientId);
        assertEquals(1, actual.readingDto.get(0).month);
        assertEquals(5.0, actual.readingDto.get(0).value);
        assertEquals(300, actual.readingDto.get(0).median);
    }

    private ArrayList<Reading> getAllReadings() {
        ArrayList<Reading> readings = new ArrayList<>();

        Reading reading1 = new Reading();
        reading1.clientId = "1";
        reading1.month = 1;
        reading1.value = 5;

        Reading reading2 = new Reading();
        reading2.clientId = "1";
        reading2.month = 2;
        reading2.value = 300;

        Reading reading3 = new Reading();
        reading3.clientId = "1";
        reading3.month = 3;
        reading3.value = 300;

        readings.add(reading1);
        readings.add(reading2);
        readings.add(reading3);

        return readings;
    }
}
