package prv.dudekre.warehouse.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import prv.dudekre.warehouse.domain.Product;
import prv.dudekre.warehouse.domain.ProductRepository;
import prv.dudekre.warehouse.domain.Warehouse;
import prv.dudekre.warehouse.domain.WarehouseEntry;
import prv.dudekre.warehouse.domain.WarehousePlacementResult;
import prv.dudekre.warehouse.domain.WarehouseRepository;

import java.util.List;

@Service
public class WarehouseService {
    private final WarehouseRepository warehouseRepository;
    private final ProductRepository productRepository;

    public WarehouseService(@Autowired WarehouseRepository warehouseRepository,
                            @Autowired ProductRepository productRepository) {
        this.warehouseRepository = warehouseRepository;
        this.productRepository = productRepository;
    }

    public WarehousePlacementResult putProduct(String productId, int quantity) {
        Product product = productRepository.getById(productId);
        Warehouse warehouse = warehouseRepository.get();
        WarehousePlacementResult warehousePlacementResult = warehouse.placeProduct(product, quantity);
        warehouseRepository.save(warehouse);
        return warehousePlacementResult;
    }

    public List<WarehouseEntry> getContent() {
        Warehouse warehouse = warehouseRepository.get();
        return warehouse.getContent();
    }
}
