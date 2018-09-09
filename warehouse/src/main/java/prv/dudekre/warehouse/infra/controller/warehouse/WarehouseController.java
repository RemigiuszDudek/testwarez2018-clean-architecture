package prv.dudekre.warehouse.infra.controller.warehouse;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import prv.dudekre.warehouse.app.WarehouseService;
import prv.dudekre.warehouse.domain.WarehouseEntry;
import prv.dudekre.warehouse.domain.WarehousePlacementResult;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

@RestController
public final class WarehouseController {
    private final WarehouseService warehouseService;

    public WarehouseController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @RequestMapping(method = PUT, value = "/products")
    public List<WarehousePlacementResult> putProducts(@RequestBody List<ProductPlacementRequest> entries) {
        return entries.stream()
                .map(entry -> warehouseService.putProduct(entry.getProductId(), entry.getQuantity()))
                .collect(Collectors.toList());
    }

    @RequestMapping(method = GET, value = "/content")
    public List<WarehouseEntry> content() {
        return warehouseService.getContent();
    }
}
