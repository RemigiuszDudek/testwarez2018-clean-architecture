package prv.dudekre.warehouse.contract;

import prv.dudekre.warehouse.app.WarehouseService;
import prv.dudekre.warehouse.domain.Product;
import prv.dudekre.warehouse.domain.Warehouse;

import java.util.List;

class MockWarehouseService extends WarehouseService {
    MockWarehouseService(Warehouse warehouse, List<Product> products) {
        super(new MockWarehouseRepository(warehouse), new MockProductRepository(products));
    }
}
