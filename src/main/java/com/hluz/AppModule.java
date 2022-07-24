package com.hluz;

import com.google.inject.AbstractModule;
import com.hluz.readings.domain.ReadingsRepository;
import com.hluz.readings.infrastructure.filerepositories.FileRepository;
import com.hluz.readings.infrastructure.filerepositories.FileRepositoryFactory;

public class AppModule extends AbstractModule {

	private final String filename;

	public AppModule(String filename) {
		this.filename = filename;
	}

	@Override
	protected void configure() {
		bind(ReadingsRepository.class).toInstance(
			new FileRepository(new FileRepositoryFactory(this.filename)));
	}
}
