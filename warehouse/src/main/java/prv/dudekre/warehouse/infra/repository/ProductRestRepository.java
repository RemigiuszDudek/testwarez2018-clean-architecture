package prv.dudekre.warehouse.infra.repository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import prv.dudekre.warehouse.domain.Product;
import prv.dudekre.warehouse.domain.ProductRepository;

import java.util.HashMap;
import java.util.Map;

import static com.google.common.base.Preconditions.checkState;
import static java.util.Objects.requireNonNull;

@Repository
public class ProductRestRepository implements ProductRepository {
    private final Map<String, Product> products = new HashMap<>();
    private final RestTemplate restTemplate = new RestTemplate();
    private final UriComponentsBuilder productServiceUri;

    public ProductRestRepository(
            @Value("${prv.dudekre.product-service.host}") String host,
            @Value("${prv.dudekre.product-service.port}") String port) {
        productServiceUri = UriComponentsBuilder.newInstance()
                .scheme("http")
                .host(host)
                .port(port)
                .path("product/{id}");
    }

    @Override
    public Product getById(String id) {
        if (!products.containsKey(id)) {
            ProductResponse response = restTemplate.getForObject(productServiceUri.build(id), ProductResponse.class);
            checkState(requireNonNull(response).getProductId() != null);
            Product product = new Product(response.getProductId(), response.getVolume());
            products.put(product.getId(), product);
        }
        return products.get(id);
    }
}
