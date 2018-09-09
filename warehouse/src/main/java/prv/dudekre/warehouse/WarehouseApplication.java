package prv.dudekre.warehouse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import prv.dudekre.warehouse.domain.Warehouse;
import prv.dudekre.warehouse.domain.WarehouseRepository;

import static java.lang.Integer.parseInt;
import static java.util.Objects.requireNonNull;

@SpringBootApplication
public class WarehouseApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(WarehouseApplication.class, args);
        bootstrapWarehouse(context);
    }

    private static void bootstrapWarehouse(ConfigurableApplicationContext context) {
        WarehouseRepository warehouseRepository = context.getBean(WarehouseRepository.class);
        if (warehouseRepository.get() == null) {
            int initialVolume = parseInt(requireNonNull(context.getEnvironment().getProperty("prv.dudekre.warehouse.initial-volume")));
            Warehouse warehouse = new Warehouse(initialVolume);
            warehouseRepository.save(warehouse);
        }
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
