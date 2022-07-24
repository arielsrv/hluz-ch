package com.hluz.readings.application;

import static java.util.stream.Collectors.groupingBy;

import com.hluz.readings.domain.Reading;
import com.hluz.readings.domain.ReadingsRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.inject.Inject;
import lombok.experimental.ExtensionMethod;

@ExtensionMethod({DoubleExtensions.class})
public class SuspiciousReadings {

	private final ReadingsRepository readingsRepository;

	@Inject
	public SuspiciousReadings(ReadingsRepository readingsRepository) {
		this.readingsRepository = readingsRepository;
	}

	public ReadingsDto getSuspiciousReadings() {

		ReadingsDto readingsDto = new ReadingsDto();
		readingsDto.readingDto = new ArrayList<>();

		List<Reading> readings = this.readingsRepository.getAll();

		Map<String, List<Reading>> groupedReadingsByClientId = readings.stream()
			.collect(groupingBy(reading -> reading.clientId));

		for (Map.Entry<String, List<Reading>> readingByClientId : groupedReadingsByClientId.entrySet()) {
			double median = readings.stream()
				.map(reading -> reading.value)
				.collect(Collectors.toList())
				.toArray(new Double[readings.size()])
				.median();

			for (Reading reading : readingByClientId.getValue()) {
				double value = (median - reading.value) / median * 100;
				if (Math.abs(value) > 50) {
					ReadingDto readingDto = new ReadingDto();
					readingDto.clientId = readingByClientId.getKey();
					readingDto.median = median;
					readingDto.value = reading.value;
					readingDto.month = reading.month;
					readingsDto.readingDto.add(readingDto);
				}
			}
		}

		return readingsDto;
	}
}
