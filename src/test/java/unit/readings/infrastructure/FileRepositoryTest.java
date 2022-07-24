package unit.readings.infrastructure;

import org.junit.jupiter.api.Test;
import com.hluz.readings.domain.ReadingsRepository;
import com.hluz.readings.infrastructure.filerepositories.FileRepositoryFactory;
import com.hluz.readings.infrastructure.filerepositories.xml.XmlFileRepository;

import static org.junit.jupiter.api.Assertions.*;

public class FileRepositoryTest {

	@Test
	public void get_instance_from_file_extension() {
		FileRepositoryFactory fileRepositoryFactory = new FileRepositoryFactory("file.xml");

		ReadingsRepository actual = fileRepositoryFactory.get();
		assertNotNull(actual);
		assertInstanceOf(XmlFileRepository.class, actual);
	}

	@Test
	public void get_error_from_invalid_extension() {
		FileRepositoryFactory fileRepositoryFactory = new FileRepositoryFactory("file.invalid_extension");

		IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> {
			fileRepositoryFactory.get();
		});

		assertNotNull(e);
		assertEquals(e.getMessage(), "Extension invalid_extension not supported");
	}
}
