package src.readings.infrastructure.filerepositories.csv;

import src.readings.domain.Reading;

import java.util.List;

public class CsvFileRepository implements src.readings.domain.ReadingsRepository {

    private final String filename;

    public CsvFileRepository(String filename) {
        this.filename = filename;
    }

    @Override
    public List<Reading> getAll() {
        return null;
    }
}
