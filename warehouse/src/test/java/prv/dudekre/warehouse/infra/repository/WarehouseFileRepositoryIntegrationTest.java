package prv.dudekre.warehouse.infra.repository;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import prv.dudekre.warehouse.domain.Product;
import prv.dudekre.warehouse.domain.Warehouse;
import prv.dudekre.warehouse.domain.WarehouseRepository;

import static org.assertj.core.api.Assertions.assertThat;

public class WarehouseFileRepositoryIntegrationTest {
    private static final int DEFAULT_INITIAL_VOLUME = 1;
    private static final int INITIAL_VOLUME = 10;

    @Rule public TemporaryFolder temporaryFolder = new TemporaryFolder();
    private WarehouseRepository repository;

    @Before
    public void setupRepository() {
        System.out.println(temporaryFolder.getRoot().getAbsoluteFile());
        repository = new WarehouseFileRepository(temporaryFolder.getRoot().getAbsolutePath(), String.valueOf(DEFAULT_INITIAL_VOLUME));
    }

    @Test
    public void getDefaultWarehouseWhenNothingWasPreviouslySaved() {
        Warehouse warehouse = repository.get();
        assertThat(warehouse.getRemainingVolume()).isEqualTo(DEFAULT_INITIAL_VOLUME);
    }

    @Test
    public void getSavedEmptyWarehouse() {

        Warehouse warehouse = new Warehouse(INITIAL_VOLUME);

        repository.save(warehouse);
        Warehouse warehouseFromRepository = repository.get();

        assertThat(warehouseFromRepository).isEqualTo(warehouse);
    }

    @Test
    public void getSavedFullWarehouse() {
        Warehouse warehouse = new Warehouse(INITIAL_VOLUME);
        warehouse.placeProduct(new Product("1", 1), 2);
        warehouse.placeProduct(new Product("2", 2), 1);

        repository.save(warehouse);
        Warehouse warehouseFromRepository = repository.get();

        assertThat(warehouseFromRepository).isEqualTo(warehouse);
    }

    @Test
    public void overrideSavedWarehouseWithNewOne() {
        Warehouse oldWarehouse = new Warehouse(1);
        Warehouse newWareHouse = new Warehouse(2);

        repository.save(oldWarehouse);
        repository.save(newWareHouse);
        Warehouse warehouse = repository.get();

        assertThat(warehouse).isEqualTo(newWareHouse);
    }
}