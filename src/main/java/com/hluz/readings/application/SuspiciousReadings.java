package com.hluz.readings.application;

import com.hluz.readings.domain.Reading;
import com.hluz.readings.domain.ReadingsRepository;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

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
            double median = this.getMedian(readingByClientId.getValue());
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

    private double getMedian(List<Reading> readings) {

        Double[] values = readings.stream()
                .map(reading -> reading.value)
                .collect(Collectors.toList())
                .toArray(new Double[readings.size()]);

        Arrays.sort(values);

        int length = values.length;
        if (length % 2 == 0) {
            return (values[length / 2 - 1] + values[length / 2]) / 2;
        } else {
            return values[length / 2];
        }
    }
}
