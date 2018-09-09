package prv.dudekre.warehouse.infra.repository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import prv.dudekre.warehouse.domain.Warehouse;
import prv.dudekre.warehouse.domain.WarehouseRepository;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.FileSystems;

@Repository
public class WarehouseFileRepository implements WarehouseRepository {
    private static final String WAREHOUSE_REPO_FILENAME = "warehouse.rpo";
    private final int initialVolume;
    private final File repositoryFile;

    public WarehouseFileRepository(@Value("${prv.dudekre.repository.rootDir}") final String repositoryRootDir,
                                   @Value("${prv.dudekre.warehouse.initial-volume}") final String initialVolume) {
        this.repositoryFile = FileSystems.getDefault().getPath(repositoryRootDir, WAREHOUSE_REPO_FILENAME).toFile();
        repositoryFile.getParentFile().mkdir();
        this.initialVolume = Integer.parseInt(initialVolume);
    }

    @Override
    public Warehouse get() {
        if (!repositoryFile.exists()) {
            return new Warehouse(initialVolume);
        }
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(repositoryFile))) {
            return (Warehouse) objectInputStream.readObject();
        } catch (ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(final Warehouse warehouse) {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(repositoryFile))) {
            objectOutputStream.writeObject(warehouse);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
