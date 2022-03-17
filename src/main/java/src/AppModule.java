package src;

import com.google.inject.AbstractModule;
import src.readings.domain.ReadingsRepository;
import src.readings.infrastructure.filerepositories.FileRepository;
import src.readings.infrastructure.filerepositories.FileRepositoryFactory;

public class AppModule extends AbstractModule {

    private final String filename;

    public AppModule(String filename) {
        this.filename = filename;
    }

    @Override
    protected void configure() {
        bind(ReadingsRepository.class).toInstance(new FileRepository(new FileRepositoryFactory(this.filename)));
    }
}
