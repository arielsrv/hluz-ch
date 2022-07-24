package com.hluz.readings.infrastructure.filerepositories.csv;

import com.hluz.readings.domain.Reading;
import com.hluz.readings.domain.ReadingsRepository;

import java.util.List;

public class CsvFileRepository implements ReadingsRepository {

	private final String filename;

	public CsvFileRepository(String filename) {
		this.filename = filename;
	}

	@Override
	public List<Reading> getAll() {
		throw new RuntimeException("Not implemented exception. ");
	}
}
