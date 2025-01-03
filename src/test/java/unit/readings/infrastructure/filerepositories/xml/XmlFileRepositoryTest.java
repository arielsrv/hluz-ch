package unit.readings.infrastructure.filerepositories.xml;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.hluz.readings.domain.Reading;
import com.hluz.readings.infrastructure.filerepositories.xml.XmlFileRepository;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;

public class XmlFileRepositoryTest {

	@Test
	public void get_values_from_xml_file() throws IOException {

		String xml = """
			<?xml version="1.0"?>
			<readings>
			\t<reading clientID="583ef6329df6b" period="2016-01">66</reading>
			</readings>""";

		File file = File.createTempFile("repository", ".xml");
		FileWriter writer = new FileWriter(file);
		writer.write(xml);
		writer.close();

		XmlFileRepository xmlFileRepository = new XmlFileRepository(file.getAbsolutePath());

		List<Reading> actual = xmlFileRepository.getAll();
		assertFalse(actual.isEmpty());
		assertEquals(1, (long) actual.size());

		Optional<Reading> first = actual.stream().findFirst();
		assertTrue(first.isPresent());
		assertEquals("583ef6329df6b", first.get().clientId);
		assertEquals(1, first.get().month);
		assertEquals(66, first.get().value);
	}
}
