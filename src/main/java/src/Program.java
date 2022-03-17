package src;

import com.google.inject.Guice;
import com.google.inject.Injector;
import src.readings.application.ReadingDto;
import src.readings.application.ReadingsDto;
import src.readings.application.SuspiciousReadings;
import src.readings.domain.ReadingsRepository;
import src.readings.infrastructure.filerepositories.FileRepository;
import src.readings.infrastructure.filerepositories.FileRepositoryFactory;

import static java.lang.System.out;

public class Program {
    public static void main(String[] args) {
        String filename = "/Users/arielpineiro/projects/personal/holaluz-challenge/2016-readings.xml";
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
