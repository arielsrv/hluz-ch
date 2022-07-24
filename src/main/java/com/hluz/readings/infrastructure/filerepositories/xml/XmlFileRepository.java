package com.hluz.readings.infrastructure.filerepositories.xml;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.hluz.readings.domain.Reading;
import com.hluz.readings.domain.ReadingsRepository;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class XmlFileRepository implements ReadingsRepository {

	private final String filename;

	public XmlFileRepository(String filename) {
		this.filename = filename;
	}

	@Override
	public List<Reading> getAll() {

		List<Reading> readings = new ArrayList<>();

		try {
			File file = new File(this.filename);
			XmlMapper xmlMapper = new XmlMapper();
			ReadingsDto readingsDto = xmlMapper.readValue(new FileInputStream(file),
				ReadingsDto.class);

			readings = readingsDto.readings.stream().map(readingDto -> {
				Reading reading = new Reading();

				reading.clientId = readingDto.clientID;
				reading.month = YearMonth.parse(readingDto.period).getMonth().getValue();
				reading.value = Double.parseDouble(readingDto.value);

				return reading;
			}).collect(Collectors.toList());

		} catch (IOException e) {
			e.printStackTrace();
		}

		return readings;
	}
}


