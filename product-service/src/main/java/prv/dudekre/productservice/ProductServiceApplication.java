package prv.dudekre.productservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class ProductServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductServiceApplication.class, args);
    }

    @RestController
    class ProductServiceController {
        private Map<String, Product> products = new HashMap<>();
        private Random random  = new Random();
        private List<String> productNames;

        public ProductServiceController(@Value("#{'${prv.dudekre.product-service.product.names}'.split(',')}") List<String> productNames) {
            this.productNames = productNames;
        }

        @RequestMapping(method = RequestMethod.GET, path = "/product/{id}")
        public Product getProduct(@PathVariable("id") String id) {
            if (!products.containsKey(id)) {
                products.put(id, randomProduct(id));
            }
            return products.get(id);
        }

        private Product randomProduct(String id) {
            int volume = random.nextInt(10) + 1;
            int idx = random.nextInt(productNames.size());
            return new Product(id, productNames.get(idx), volume);
        }
    }
}
