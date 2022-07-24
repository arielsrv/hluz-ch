package com.hluz;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.hluz.readings.application.ReadingDto;
import com.hluz.readings.application.ReadingsDto;
import com.hluz.readings.application.SuspiciousReadings;

import static java.lang.System.out;

public class Program {
    public static void main(String[] args) {
        String filename = "/Users/ariel.pineiro/projects/personal/hluz-ch/2016-readings.xml";
        Injector injector = Guice.createInjector(new AppModule(filename));

        SuspiciousReadings suspiciousReadings = injector.getInstance(SuspiciousReadings.class);

        ReadingsDto readingsDto = suspiciousReadings.getSuspiciousReadings();

        showHeader(readingsDto);
        for (ReadingDto readingDto : readingsDto.readingDto) {
            showRow(readingDto);
        }
    }

    private static void showRow(ReadingDto readingDto) {
        out.printf("| %s\t\t | %s\t\t | %s\t\t | %s\t\t ", readingDto.clientId, readingDto.month, readingDto.value, readingDto.median);
        out.println();
    }

    private static void showHeader(ReadingsDto readingsDto) {
        if (readingsDto.readingDto.stream().findAny().isPresent()) {
            out.println("| Client\t\t | Month\t\t | Suspicious\t\t | Median\t\t ");
        }
    }
}
