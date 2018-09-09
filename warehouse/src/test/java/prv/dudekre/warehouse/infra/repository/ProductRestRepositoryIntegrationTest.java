package prv.dudekre.warehouse.infra.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.test.context.junit4.SpringRunner;
import prv.dudekre.warehouse.domain.Product;
import prv.dudekre.warehouse.domain.ProductRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static prv.dudekre.warehouse.infra.repository.ProductRestRepositoryIntegrationTest.PRODUCT_SERVICE_TEST_PORT;

@RunWith(SpringRunner.class)
@AutoConfigureStubRunner(
        ids = "prv.dudekre:product-service-contract:0.0.1-SNAPSHOT:stubs:" + PRODUCT_SERVICE_TEST_PORT,
        stubsMode = StubRunnerProperties.StubsMode.LOCAL
)
public class ProductRestRepositoryIntegrationTest {
    static final String PRODUCT_SERVICE_TEST_PORT = "7181";
    private final ProductRepository productRepository = new ProductRestRepository("localhost", PRODUCT_SERVICE_TEST_PORT);

    @Test
    public void getsProduct() {
        Product expectedProduct = new Product("123", 2);
        Product product = productRepository.getById("123");
        assertThat(product).isEqualTo(expectedProduct);
    }
}