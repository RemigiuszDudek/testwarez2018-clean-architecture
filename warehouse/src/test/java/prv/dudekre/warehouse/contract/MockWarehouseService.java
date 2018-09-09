package prv.dudekre.warehouse.contract;

import prv.dudekre.warehouse.app.WarehouseService;
import prv.dudekre.warehouse.domain.Product;
import prv.dudekre.warehouse.domain.Warehouse;

import java.util.List;

public class MockWarehouseService extends WarehouseService {
    public MockWarehouseService(Warehouse warehouse, List<Product> products) {
        super(new MockWarehouseRepository(warehouse), new MockProductRepository(products));
    }
}
