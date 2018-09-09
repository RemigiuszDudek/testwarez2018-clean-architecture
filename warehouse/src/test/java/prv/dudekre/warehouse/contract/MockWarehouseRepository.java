package prv.dudekre.warehouse.contract;

import prv.dudekre.warehouse.domain.Warehouse;
import prv.dudekre.warehouse.domain.WarehouseRepository;

public class MockWarehouseRepository implements WarehouseRepository {
    private Warehouse warehouse;

    public MockWarehouseRepository(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    @Override
    public Warehouse get() {
        return warehouse;
    }

    @Override
    public void save(Warehouse warehouse) {
        this.warehouse = warehouse;
    }
}
