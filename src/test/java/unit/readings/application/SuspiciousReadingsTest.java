package unit.readings.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.hluz.readings.application.ReadingDto;
import com.hluz.readings.application.ReadingsDto;
import com.hluz.readings.application.SuspiciousReadings;
import com.hluz.readings.domain.Reading;
import com.hluz.readings.domain.ReadingsRepository;
import java.util.ArrayList;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
		assertTrue(actual.readingDto.stream().findFirst().isPresent());
		ReadingDto readingDto = actual.readingDto.stream().findFirst().get();
		assertEquals("1", readingDto.clientId);
		assertEquals(1, readingDto.month);
		assertEquals(5.0, readingDto.value);
		assertEquals(300, readingDto.median);
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
