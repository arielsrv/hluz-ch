package com.hluz.readings.infrastructure.filerepositories;

import com.google.common.io.Files;
import com.hluz.readings.domain.ReadingsRepository;
import com.hluz.readings.infrastructure.filerepositories.csv.CsvFileRepository;
import com.hluz.readings.infrastructure.filerepositories.xml.XmlFileRepository;

import java.util.HashMap;
import java.util.Map;

import static java.lang.String.*;

public class FileRepositoryFactory {

    private final String filename;
    private Map<String, ReadingsRepository> repositories;

    public FileRepositoryFactory(String filename) {
        this.filename = filename;
        this.repositories = new HashMap<>();
        this.repositories.put("xml", new XmlFileRepository(filename));
        this.repositories.put("csv", new CsvFileRepository(filename));
    }

    public ReadingsRepository get() {
        String extension = Files.getFileExtension(this.filename);

        if (!this.repositories.containsKey(extension)) {
            throw new IllegalArgumentException(format("Extension %s not supported", extension));
        }

        return this.repositories.get(extension);
    }
}
