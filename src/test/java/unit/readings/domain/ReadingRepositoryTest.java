package unit.readings.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.hluz.readings.domain.Reading;
import com.hluz.readings.infrastructure.filerepositories.FileRepository;
import com.hluz.readings.infrastructure.filerepositories.FileRepositoryFactory;
import com.hluz.readings.infrastructure.filerepositories.xml.XmlFileRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ReadingRepositoryTest {

	private FileRepositoryFactory fileRepositoryFactory;
	private FileRepository readingsRepository;
	private XmlFileRepository xmlFileRepository;

	@BeforeEach
	public void setUp() {
		this.fileRepositoryFactory = mock(FileRepositoryFactory.class);
		this.xmlFileRepository = mock(XmlFileRepository.class);
		this.readingsRepository = new FileRepository(this.fileRepositoryFactory);
	}

	@Test
	public void get_all() {
		when(this.fileRepositoryFactory.get()).thenReturn(this.xmlFileRepository);
		when(this.xmlFileRepository.getAll()).thenReturn(getAll());

		List<Reading> readings = this.readingsRepository.getAll();

		assertFalse(readings.isEmpty());
		assertEquals(1, (long) readings.size());
		assertTrue(readings.stream().findFirst().isPresent());
		Reading actual = readings.stream().findFirst().get();
		assertEquals("1", actual.clientId);
		assertEquals(1.0, actual.value);
		assertEquals(1, actual.month);
	}

	private List<Reading> getAll() {
		Reading reading = new Reading();
		reading.clientId = "1";
		reading.value = 1.0;
		reading.month = 1;

		List<Reading> readings = new ArrayList<>();
		readings.add(reading);

		return readings;
	}
}
