package unit.readings.infrastructure.filerepositories.xml;

import org.junit.jupiter.api.Test;
import com.hluz.readings.domain.Reading;
import com.hluz.readings.infrastructure.filerepositories.xml.XmlFileRepository;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class XmlFileRepositoryTest {

	@Test
	public void get_values_from_xml_file() throws IOException {

		String content = "<?xml version=\"1.0\"?>\n" +
			"<readings>\n" +
			"\t<reading clientID=\"583ef6329df6b\" period=\"2016-01\">66</reading>\n" +
			"</readings>";

		File file = File.createTempFile("repository", ".xml");
		FileWriter writer = new FileWriter(file);
		writer.write(content);
		writer.close();

		XmlFileRepository xmlFileRepository = new XmlFileRepository(file.getAbsolutePath());

		List<Reading> actual = xmlFileRepository.getAll();
		assertFalse(actual.isEmpty());
		assertEquals(actual.stream().count(), 1);

		Optional<Reading> first = actual.stream().findFirst();
		assertTrue(first.isPresent());
		assertEquals(first.get().clientId, "583ef6329df6b");
		assertEquals(first.get().month, 1);
		assertEquals(first.get().value, 66);
	}
}
