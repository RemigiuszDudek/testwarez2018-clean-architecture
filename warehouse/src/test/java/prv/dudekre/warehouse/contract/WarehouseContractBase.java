package prv.dudekre.warehouse.contract;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.springframework.boot.test.context.SpringBootTest;
import prv.dudekre.warehouse.app.WarehouseService;
import prv.dudekre.warehouse.domain.Product;
import prv.dudekre.warehouse.domain.Warehouse;
import prv.dudekre.warehouse.infra.controller.warehouse.WarehouseController;

import java.util.List;

import static com.google.common.collect.ImmutableList.of;

@SuppressWarnings("PMD.AbstractClassWithoutAbstractMethod")
@SpringBootTest
public abstract class WarehouseContractBase {
    private static final int INITIAL_VOLUME = 10;
    private final Warehouse warehouse = new Warehouse(INITIAL_VOLUME);
    private final Product product1 = new Product("id_1", 1);
    private final Product product2 = new Product("id_2", 1);
    private final List<Product> products = of(product1, product2);
    private final WarehouseService warehouseService = new MockWarehouseService(warehouse, products);

    @Before
    public void setup() {
        int product1Quantity = 1;
        int product2Quantity = 2;
        warehouse.placeProduct(product1, product1Quantity);
        warehouse.placeProduct(product2, product2Quantity);
        RestAssuredMockMvc.standaloneSetup(new WarehouseController(warehouseService)
        );
    }
}
