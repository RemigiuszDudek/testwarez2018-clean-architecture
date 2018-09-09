package prv.dudekre.warehouse.contract;

import prv.dudekre.warehouse.domain.Product;
import prv.dudekre.warehouse.domain.ProductRepository;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

public class MockProductRepository implements ProductRepository {
    private final Map<String, Product> products;

    public MockProductRepository(List<Product> products) {
        this.products = products.stream()
                .collect(toMap(Product::getId, product -> product));
    }

    @Override
    public Product getById(String id) {
        return products.get(id);
    }
}
