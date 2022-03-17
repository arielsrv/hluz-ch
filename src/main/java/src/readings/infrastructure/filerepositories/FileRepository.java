package src.readings.infrastructure.filerepositories;

import src.readings.domain.Reading;
import src.readings.domain.ReadingsRepository;

import java.util.List;

public class FileRepository implements ReadingsRepository {

    private final FileRepositoryFactory fileRepositoryFactory;

    public FileRepository(FileRepositoryFactory fileRepositoryFactory) {
        this.fileRepositoryFactory = fileRepositoryFactory;
    }

    @Override
    public List<Reading> getAll() {
        ReadingsRepository readingsRepository = this.fileRepositoryFactory.get();
        return readingsRepository.getAll();
    }
}

